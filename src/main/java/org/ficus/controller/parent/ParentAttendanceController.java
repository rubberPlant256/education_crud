package org.ficus.controller.parent;

import lombok.RequiredArgsConstructor;
import org.ficus.auth.services.AuthService;
import org.ficus.data.entity.Journal;
import org.ficus.data.entity.Parent;
import org.ficus.data.entity.Student;
import org.ficus.dto.ChildDTO;
import org.ficus.dto.JournalDTO;
import org.ficus.dto.ParentDTO;
import org.ficus.service.JournalService;
import org.ficus.service.ParentService;
import org.ficus.service.StudentService;
import org.ficus.service.converter.JournalToJournalDTO;
import org.ficus.service.converter.ParentToParentDTO;
import org.ficus.service.converter.StudentToChildDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parent/attendance")
public class ParentAttendanceController {

    private final AuthService authService;
    private final ParentService parentService;
    private final StudentService studentService;
    private final JournalService journalService;

    @GetMapping
    public String showParentAttendanceForm(Model model, @RequestHeader("Cookie") String cookie) {
        Long userId = authService.getUserIdFromCookie(cookie);
        Parent foundParent = parentService.findParentByUserId(userId);
        ParentDTO parentDTO = ParentToParentDTO.convertParentToParentDTO(foundParent);
        model.addAttribute("parent", parentDTO);

        List<Student> studentList = studentService.findChildrenByParentId(foundParent.getId());
        List<ChildDTO> childDTOS = new ArrayList<>();
        for (Student student : studentList){
            childDTOS.add(StudentToChildDTO.convertStudentToChildDTO(student));
        }
        model.addAttribute("children", childDTOS);

        return "parent_attendance";
    }


//    @RequestParam("groupId") Long groupId,
//    @RequestParam("lessonDate") String lessonDate,
//    Model model,
//    RedirectAttributes redirectAttributes
    @PostMapping
    public String showAttendanceTable( Model model,
                                       RedirectAttributes redirectAttributes,
                                       @RequestParam("childId") Long studentId,
                                       @RequestParam("monthYear") String monthYear){

        YearMonth yearMonth = YearMonth.parse(monthYear);
        // Начало месяца (первое число)
        Date startDate = Date.from(
                yearMonth.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
        // Конец месяца (последнее число)
        Date endDate = Date.from(
                yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        List<Journal> journals = journalService.findJournalByStudentIdAndMonth(studentId, startDate, endDate);
        List<JournalDTO> journalDTOS = journals.stream()
                .map(JournalToJournalDTO::convertJournalToJournalDTO)
                .collect(Collectors.toList());
        redirectAttributes.addFlashAttribute("journals", journalDTOS);

        return "redirect:/parent/attendance";
    }
}
