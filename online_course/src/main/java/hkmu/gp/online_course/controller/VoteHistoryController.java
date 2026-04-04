package hkmu.gp.online_course.controller;
import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.service.UserService;
import hkmu.gp.online_course.service.VoteRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/history")
public class VoteHistoryController {

    @Autowired
    private VoteRecordService voteRecordService;

    @Autowired
    private UserService userService;

    @GetMapping("/votes")
    public String voteHistory(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findByUsername(currentUser.getUsername());
        model.addAttribute("records", voteRecordService.findByUser(user));
        return "voteHistory";
    }
}