package hkmu.gp.online_course.config;

import hkmu.gp.online_course.dto.UserDto;
import hkmu.gp.online_course.entity.Lecture;
import hkmu.gp.online_course.entity.Poll;
import hkmu.gp.online_course.entity.PollOption;
import hkmu.gp.online_course.repository.LectureRepository;
import hkmu.gp.online_course.repository.PollOptionRepository;
import hkmu.gp.online_course.repository.PollRepository;
import hkmu.gp.online_course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private LectureRepository lectureRepo;

    @Autowired
    private PollRepository pollRepo;

    @Autowired
    private PollOptionRepository pollOptionRepo;

    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()) {

            UserDto teacher = new UserDto();
            teacher.setUsername("teacher");
            teacher.setPassword("123456");
            teacher.setFullName("Chan Tai Man (Teacher)");
            teacher.setEmail("teacher@hkmu.edu.hk");
            teacher.setPhone("12345678");
            userService.register(teacher, "ROLE_TEACHER");

            UserDto student1 = new UserDto();
            student1.setUsername("student1");
            student1.setPassword("123456");
            student1.setFullName("Wong Siu Ming");
            student1.setEmail("student1@hkmu.edu.hk");
            student1.setPhone("87654321");
            userService.register(student1, "ROLE_STUDENT");

            Lecture lecture = new Lecture("Introduction to Spring Boot", "Learn the basics of Spring Boot, Dependency Injection, and Auto-configuration.");
            lectureRepo.save(lecture);

            Poll poll = new Poll("Which topic should be introduced in the next class?");
            pollRepo.save(poll);

            pollOptionRepo.save(new PollOption("Spring Security", poll));
            pollOptionRepo.save(new PollOption("Spring Data JPA", poll));
            pollOptionRepo.save(new PollOption("RESTful APIs", poll));
            pollOptionRepo.save(new PollOption("Thymeleaf / JSP", poll));
            pollOptionRepo.save(new PollOption("Microservices", poll));

            System.out.println("Initial data has been successfully loaded!");
        }
    }
}
