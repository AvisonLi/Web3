package hkmu.gp.online_course.controller;
import hkmu.gp.online_course.dto.CommentDto;
import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.service.PollService;
import hkmu.gp.online_course.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String viewPoll(@PathVariable Long id, Model model,
                           @AuthenticationPrincipal UserDetails currentUser) {
        var poll = pollService.findById(id);
        model.addAttribute("poll", poll);
        if (currentUser != null) {
            User user = userService.findByUsername(currentUser.getUsername());
            var vote = pollService.getUserVote(user, poll);
            if (vote != null) {
                model.addAttribute("selectedOptionId", vote.getSelectedOption().getId());
            }
        }
        model.addAttribute("newComment", new CommentDto());
        return "poll";
    }

    @PostMapping("/{id}/vote")
    public String vote(@PathVariable Long id, @RequestParam Long optionId,
                       @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        pollService.vote(id, optionId, user);
        return "redirect:/poll/" + id;
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable Long id, @Valid CommentDto dto,
                             @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.findByUsername(currentUser.getUsername());
        pollService.addComment(id, user, dto.getContent());
        return "redirect:/poll/" + id;
    }
}