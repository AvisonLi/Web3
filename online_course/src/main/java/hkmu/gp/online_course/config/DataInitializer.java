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

import java.util.Arrays;
import java.util.List;

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

            createUsers();

            createLectures();

            createPolls();

            System.out.println(">>> Initial data for HKMU Online Course has been successfully loaded!");
        }
    }

    private void createUsers() {
        UserDto teacher = new UserDto();
        teacher.setUsername("teacher1");
        teacher.setPassword("123");
        teacher.setFullName("Dr. Chan Tai Man");
        teacher.setEmail("teacher@hkmu.edu.hk");
        teacher.setPhone("12345678");
        userService.register(teacher, "ROLE_TEACHER");

        UserDto student1 = new UserDto();
        student1.setUsername("student1");
        student1.setPassword("123");
        student1.setFullName("Wong Siu Ming");
        student1.setEmail("student1@hkmu.edu.hk");
        student1.setPhone("87654321");
        userService.register(student1, "ROLE_STUDENT");

        UserDto student2 = new UserDto();
        student2.setUsername("student2");
        student2.setPassword("123");
        student2.setFullName("Lee Mei Yee");
        student2.setEmail("student2@hkmu.edu.hk");
        student2.setPhone("55667788");
        userService.register(student2, "ROLE_STUDENT");
    }

    private void createLectures() {
        List<Lecture> lectures = Arrays.asList(
                new Lecture("L1: Introduction to Spring Boot", "Overview of the Spring ecosystem and setup."),
                new Lecture("L2: Dependency Injection & IoC", "Understanding how Spring manages object lifecycles."),
                new Lecture("L3: Spring Data JPA", "Connecting to H2/MySQL databases using repositories."),
                new Lecture("L4: REST Controllers", "Building endpoints and handling JSON requests."),
                new Lecture("L5: Spring Security", "Implementing authentication and JWT tokens."),
                new Lecture("L6: Deployment with Docker", "Containerizing your Spring application for the cloud.")
        );
        lectureRepo.saveAll(lectures);
    }

    private void createPolls() {
        Poll poll1 = new Poll("Which advanced topic would you like a guest lecture on?");
        pollRepo.save(poll1);
        pollOptionRepo.saveAll(Arrays.asList(
                new PollOption("Microservices Architecture", poll1),
                new PollOption("Cloud Native (AWS/Azure)", poll1),
                new PollOption("Reactive Programming (WebFlux)", poll1)
        ));

        Poll poll2 = new Poll("How do you find the current pace of the course?");
        pollRepo.save(poll2);
        pollOptionRepo.saveAll(Arrays.asList(
                new PollOption("Too fast - I'm struggling", poll2),
                new PollOption("Just right - Keep going", poll2),
                new PollOption("Too slow - Let's speed up", poll2)
        ));

        Poll poll3 = new Poll("What kind of project would you prefer for the final assessment?");
        pollRepo.save(poll3);
        pollOptionRepo.saveAll(Arrays.asList(
                new PollOption("E-commerce Website", poll3),
                new PollOption("Social Media Platform", poll3),
                new PollOption("Real-time Chat App", poll3),
                new PollOption("Inventory Management System", poll3)
        ));
    }
}