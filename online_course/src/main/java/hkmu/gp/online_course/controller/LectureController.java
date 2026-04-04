package hkmu.gp.online_course.controller;
import hkmu.gp.online_course.dto.CommentDto;
import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.service.LectureService;
import hkmu.gp.online_course.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lecture")
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String viewLecture(@PathVariable Long id, Model model) {
        model.addAttribute("lecture", lectureService.findById(id));
        model.addAttribute("newComment", new CommentDto());
        return "lecture";
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable Long id, @Valid CommentDto dto,
                             @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        lectureService.addComment(id, user, dto.getContent());
        return "redirect:/lecture/" + id;
    }
}