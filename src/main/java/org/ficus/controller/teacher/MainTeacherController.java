package org.ficus.controller.teacher;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.ficus.auth.services.AuthService;
import org.ficus.data.entity.Day;
import org.ficus.data.entity.Groups;
import org.ficus.data.entity.Teacher;
import org.ficus.data.entity.Time;
import org.ficus.dto.GroupDTO;
import org.ficus.dto.ScheduleDTO;
import org.ficus.dto.TeacherDTO;
import org.ficus.service.GroupsService;
import org.ficus.service.JournalService;
import org.ficus.service.TeacherService;
import org.ficus.service.converter.GroupsToGroupsDTO;
import org.ficus.service.converter.ScheduleToScheduleDTO;
import org.ficus.service.converter.TeacherToTeacherDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class MainTeacherController {

    private final TeacherService teacherService;
    private final AuthService authService;
    private final JournalService journalService;
    private final GroupsService groupsService;

    @GetMapping("/main")
    public String showMainTeacherForm(Model model, @RequestHeader("Cookie") String cookie) {
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
    public String showJournalTeacherForm(Model model, @RequestHeader("Cookie") String cookie){

       // model.addAttribute("dayTitles", Day.getDayTitles());
        model.addAttribute("timeTitles", Time.getTimeTitles());

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

        return "teacher_journal";
    }

    @PostMapping("/grades/students")
    public String showStudents(@ModelAttribute("groups") String filter, Model model, RedirectAttributes redirectAttributes){




        findJournalByGroupIdAndLessonDate
//        Set<ProjectDTO> projectDTOs = projectCatalogService.findProjectsBySubstring(filter);
//        redirectAttributes.addFlashAttribute("projectsList", projectDTOs);
//        redirectAttributes.addFlashAttribute("search", filter);
//        return "redirect:/teacher/main";



        return "redirect:/teacher/grades";
    }
}
