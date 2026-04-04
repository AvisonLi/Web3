package hkmu.gp.online_course.controller;
import hkmu.gp.online_course.service.LectureService;
import hkmu.gp.online_course.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private PollService pollService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("courseName", "Enterprise Java Development Online Course");
        model.addAttribute("courseDesc", "Learn core technologies such as Spring Boot, Spring Security, JPA, and JSP.");
        model.addAttribute("lectures", lectureService.findAll());
        model.addAttribute("polls", pollService.findAll());
        return "index";
    }
}

