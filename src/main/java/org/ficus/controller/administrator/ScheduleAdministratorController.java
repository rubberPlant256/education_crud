package org.ficus.controller.administrator;

import lombok.RequiredArgsConstructor;
import org.ficus.auth.services.AuthService;
import org.ficus.data.entity.Day;
import org.ficus.data.entity.Teacher;
import org.ficus.data.entity.Time;
import org.ficus.dto.ScheduleDTO;
import org.ficus.dto.TeacherDTO;
import org.ficus.service.GroupsService;
import org.ficus.service.JournalService;
import org.ficus.service.ScheduleService;
import org.ficus.service.TeacherService;
import org.ficus.service.converter.ScheduleToScheduleDTO;
import org.ficus.service.converter.TeacherToTeacherDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/administrator")
public class ScheduleAdministratorController {

    private final TeacherService teacherService;
    private final AuthService authService;
    private final JournalService journalService;
    private final GroupsService groupsService;
    private final ScheduleService scheduleService;

    @GetMapping("/main")
    public String showMainAdministratorForm(Model model, @RequestHeader("Cookie") String cookie) {
        model.addAttribute("dayTitles", Day.getDayTitles());
        model.addAttribute("timeTitles", Time.getTimeTitles());
//        Long userId = authService.getUserIdFromCookie(cookie);
//        Teacher foundTeacher = teacherService.findTeacherByUserId(userId);
//
//        TeacherDTO teacherDTO = TeacherToTeacherDTO.convertTeacherToTeacherDTO(foundTeacher);
//        model.addAttribute("teacher", teacherDTO);

        List<ScheduleDTO> scheduleDTOs =  Optional.ofNullable(scheduleService.getAllSchedules())
                .orElseGet(Collections::emptyList).stream()
                .map(ScheduleToScheduleDTO::convertScheduleToScheduleDTO).toList();

        model.addAttribute("schedules", scheduleDTOs);
        return "administrator_schedule";
    }

}
