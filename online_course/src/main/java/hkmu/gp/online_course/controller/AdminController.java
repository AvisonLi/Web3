package hkmu.gp.online_course.controller;
import hkmu.gp.online_course.dto.LectureDto;
import hkmu.gp.online_course.dto.PollDto;
import hkmu.gp.online_course.dto.UserDto;
import hkmu.gp.online_course.entity.Material;
import hkmu.gp.online_course.repository.MaterialRepository;
import hkmu.gp.online_course.service.LectureService;
import hkmu.gp.online_course.service.PollService;
import hkmu.gp.online_course.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private PollService pollService;

    @Autowired
    private MaterialRepository materialRepo;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("/user/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        UserDto dto = new UserDto();
        var user = userService.findById(id);
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        model.addAttribute("userDto", dto);
        model.addAttribute("userId", id);
        return "editUser";
    }

    @PostMapping("/user/edit/{id}")
    public String updateUser(@PathVariable Long id, @Valid UserDto dto) {
        userService.updateUser(id, dto);
        return "redirect:/admin/users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/lecture/add")
    public String addLectureForm(Model model) {
        model.addAttribute("lecture", new LectureDto());
        return "addLecture";
    }

    @PostMapping("/lecture/add")
    public String addLecture(@Valid LectureDto dto) {
        lectureService.save(dto);
        return "redirect:/";
    }

    @GetMapping("/lecture/delete/{id}")
    public String deleteLecture(@PathVariable Long id) {
        lectureService.deleteLecture(id);
        return "redirect:/";
    }

    @GetMapping("/poll/add")
    public String addPollForm(Model model) {
        model.addAttribute("poll", new PollDto());
        return "addPoll";
    }

    @PostMapping("/poll/add")
    public String addPoll(@Valid PollDto dto) {
        pollService.createPoll(dto);
        return "redirect:/";
    }

    @GetMapping("/poll/delete/{id}")
    public String deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
        return "redirect:/";
    }

    @PostMapping("/lecture/{id}/upload")
    public String uploadMaterial(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        String uploadDir = "./uploads";
        Files.createDirectories(Paths.get(uploadDir));
        String originalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originalName;
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), filePath);
        lectureService.addMaterial(id, originalName, fileName);
        return "redirect:/lecture/" + id;
    }

    @GetMapping("/material/delete/{id}")
    public String deleteMaterial(@PathVariable Long id) {
        Material material = materialRepo.findById(id).orElse(null);
        if (material != null) {
            Long lectureId = material.getLecture().getId();
            materialRepo.deleteById(id);
            return "redirect:/lecture/" + lectureId;
        }
        return "redirect:/";
    }
}