package org.ficus.controller.teacher;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.ficus.auth.services.AuthService;
import org.ficus.data.entity.Day;
import org.ficus.data.entity.Teacher;
import org.ficus.data.entity.Time;
import org.ficus.dto.ScheduleDTO;
import org.ficus.dto.TeacherDTO;
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
@RequestMapping("/teacher")
public class MainTeacherController {

    private final TeacherService teacherService;
    private final AuthService authService;


    @GetMapping("/main")
    public String showMainTeacherForm(HttpServletRequest request, Model model, @RequestHeader("Cookie") String cookie) {
    //    model.addAttribute("request", request);
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
    public String showJournalTeacherForm(){

        return "test";
    }
}
