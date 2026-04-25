package org.ficus.controller.parent;

import lombok.RequiredArgsConstructor;
import org.ficus.auth.services.AuthService;
import org.ficus.data.entity.Parent;
import org.ficus.dto.ParentDTO;
import org.ficus.service.ParentService;
import org.ficus.service.converter.ParentToParentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parent")
public class ParentProfileController {

    private final AuthService authService;
    private final ParentService parentService;

    @GetMapping("/profile")
    public String showParentProfileForm(Model model, @RequestHeader("Cookie") String cookie) {
        Long userId = authService.getUserIdFromCookie(cookie);
        Parent foundParent = parentService.findParentByUserId(userId);
        ParentDTO parentDTO = ParentToParentDTO.convertParentToParentDTO(foundParent);
        model.addAttribute("parent", parentDTO);

        return "parent_profile";
    }

    @PostMapping("/profile")
    public String saveParentProfileData(@ModelAttribute("parent") ParentDTO parent) {
        parentService.updateParent(
                parent.getId(),
                parent.getLastName(),
                parent.getFirstName(),
                parent.getMiddleName(),
                parent.getPhone()
        );

        return "redirect:/parent/profile";
    }
}
