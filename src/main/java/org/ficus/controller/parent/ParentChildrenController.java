package org.ficus.controller.parent;

import lombok.RequiredArgsConstructor;
import org.ficus.auth.services.AuthService;
import org.ficus.data.entity.Parent;
import org.ficus.data.entity.Student;
import org.ficus.data.repository.StudentRepository;
import org.ficus.dto.ChildDTO;
import org.ficus.dto.ParentDTO;
import org.ficus.service.ParentService;
import org.ficus.service.StudentService;
import org.ficus.service.converter.ParentToParentDTO;
import org.ficus.service.converter.StudentToChildDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/parent/children")
public class ParentChildrenController {

    private final AuthService authService;
    private final ParentService parentService;
    private final StudentService studentService;

    @GetMapping
    public String showParentChildrenForm(Model model, @RequestHeader("Cookie") String cookie) {
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


        return "parent_kids";
    }


}
