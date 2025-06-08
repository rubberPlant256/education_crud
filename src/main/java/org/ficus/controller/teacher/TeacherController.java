package org.ficus.controller.teacher;

import lombok.RequiredArgsConstructor;
import org.ficus.auth.services.AuthService;
import org.ficus.data.entity.*;
import org.ficus.dto.*;
import org.ficus.service.GroupsService;
import org.ficus.service.JournalService;
import org.ficus.service.TeacherService;
import org.ficus.service.converter.GroupsToGroupsDTO;
import org.ficus.service.converter.JournalToJournalDTO;
import org.ficus.service.converter.ScheduleToScheduleDTO;
import org.ficus.service.converter.TeacherToTeacherDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final AuthService authService;
    private final JournalService journalService;
    private final GroupsService groupsService;

    @GetMapping("/main")
    public String showMainTeacherForm(Model model, @RequestHeader("Cookie") String cookie) {
        model.addAttribute("dayTitles", Day.getDayTitles());
        model.addAttribute("timeTitles", Time.getTimeTitles());
        Long userId = authService.getUserIdFromCookie(cookie);
        Teacher foundTeacher = teacherService.findTeacherByUserId(userId);

        TeacherDTO teacherDTO = TeacherToTeacherDTO.convertTeacherToTeacherDTO(foundTeacher);
        model.addAttribute("teacher", teacherDTO);

        List<ScheduleDTO> scheduleDTOs =  Optional.ofNullable(foundTeacher.getSchedules())
                .orElseGet(Collections::emptyList).stream()
                .map(ScheduleToScheduleDTO::convertScheduleToScheduleDTO).toList();

       model.addAttribute("schedules", scheduleDTOs);
        return "teacher_main";
    }

    @GetMapping("/grades")
    public String showJournalTeacherForm(Model model, @RequestHeader("Cookie") String cookie){

        Long userId = authService.getUserIdFromCookie(cookie);
        Teacher foundTeacher = teacherService.findTeacherByUserId(userId);
        TeacherDTO teacherDTO = TeacherToTeacherDTO.convertTeacherToTeacherDTO(foundTeacher);
        model.addAttribute("teacher", teacherDTO);

        List<Groups> groupsList = groupsService.findGroupsByTeacherId(foundTeacher.getId());
        List<GroupDTO> groupDTOList = GroupsToGroupsDTO.convertGroupListToGroupDTOList(groupsList);
        if (!model.containsAttribute("groups")) {
            model.addAttribute("groups", groupDTOList);
        }

        if (!model.containsAttribute("dateLesson")) {
            model.addAttribute("dateLesson", new Date());
        }

        // model.addAttribute("GroupDateDTO", new GroupIdDateDTO());
        return "teacher_journal";
    }

    @GetMapping("/grades/students")
    public String showStudents(
            @RequestParam("groupId") Long groupId,
            @RequestParam("lessonDate") String lessonDate,
            Model model,
            RedirectAttributes redirectAttributes){

        LocalDate localDate = LocalDate.parse(lessonDate);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Journal> journals = journalService.findJournalByGroupIdAndLessonDate(groupId, date);
        List<JournalDTO> journalDTOS = journals.stream()
                .map(JournalToJournalDTO::convertJournalToJournalDTO)
                .collect(Collectors.toList());
        Groups groups = groupsService.findGroupById(groupId);
        List<Groups> groupsList = new ArrayList<>();
        groupsList.add(groups);
        List<GroupDTO> groupDTOList = GroupsToGroupsDTO.convertGroupListToGroupDTOList(groupsList);

        redirectAttributes.addFlashAttribute("journalDTOS", journalDTOS);
        redirectAttributes.addFlashAttribute("groups", groupDTOList);
        redirectAttributes.addFlashAttribute("lessonDate", lessonDate);

        return "redirect:/teacher/grades";
    }

    @PostMapping("/grades")
    public ResponseEntity<?> updateTableJournal(@RequestBody List<JournalDTO> journalDTOS) {
        for (JournalDTO journal : journalDTOS) {
            journalService.updateJournalEntry(
                    journal.getSchedule().getId(),
                    journal.getStudent().getId(),
                    journal.isAttendance(),
                    journal.getScore());
        }
        return ResponseEntity.ok().body(Map.of("success", true));
    }

//    @PostMapping("/grades")
//    public String updateTableJournal( @ModelAttribute("journalDTOS")  List<JournalDTO> journalDTOS){
//        for (JournalDTO journal : journalDTOS){
//            journalService.updateJournalEntry(
//                    journal.getSchedule().getId(),
//                    journal.getStudent().getId(),
//                    journal.isAttendance(),
//                    journal.getScore());
//        }
//        return "redirect:/teacher/grades";
//    }

//        @PostMapping("/update")
//        public ResponseEntity<Void> updateJournalEntry(
//                @RequestParam Long scheduleId,
//                @RequestParam Long studentId,
//                @RequestParam Boolean attendance,
//                @RequestParam Score score) {
//            journalService.updateJournalEntry(scheduleId, studentId, attendance, score);
//            return ResponseEntity.ok().build();
//        }


}
