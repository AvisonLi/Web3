package hkmu.gp.online_course.config;

import hkmu.gp.online_course.entity.*;
import hkmu.gp.online_course.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LectureRepository lectureRepo;

    @Autowired
    private PollRepository pollRepo;

    @Autowired
    private PollOptionRepository optionRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepo.count() == 0) {
            User teacher = new User();
            teacher.setUsername("teacher1");
            teacher.setPassword(passwordEncoder.encode("123"));
            teacher.setFullName("Mr. Smith");
            teacher.setEmail("smith@course.com");
            teacher.setPhone("12345678");
            teacher.setRole("ROLE_TEACHER");
            userRepo.save(teacher);

            User teacher2 = new User();
            teacher2.setUsername("teacher2");
            teacher2.setPassword(passwordEncoder.encode("123"));
            teacher2.setFullName("Dr. Johnson");
            teacher2.setEmail("johnson@course.com");
            teacher2.setPhone("23456789");
            teacher2.setRole("ROLE_TEACHER");
            userRepo.save(teacher2);

            User student1 = new User();
            student1.setUsername("student1");
            student1.setPassword(passwordEncoder.encode("123"));
            student1.setFullName("Alice Lee");
            student1.setEmail("alice@course.com");
            student1.setPhone("87654321");
            student1.setRole("ROLE_STUDENT");
            userRepo.save(student1);

            User student2 = new User();
            student2.setUsername("student2");
            student2.setPassword(passwordEncoder.encode("123"));
            student2.setFullName("Bob Chan");
            student2.setEmail("bob@course.com");
            student2.setPhone("99887766");
            student2.setRole("ROLE_STUDENT");
            userRepo.save(student2);

            User student3 = new User();
            student3.setUsername("student3");
            student3.setPassword(passwordEncoder.encode("123"));
            student3.setFullName("Cathy Wong");
            student3.setEmail("cathy@course.com");
            student3.setPhone("55667788");
            student3.setRole("ROLE_STUDENT");
            userRepo.save(student3);
        }

        if (lectureRepo.count() == 0) {
            Lecture lecture1 = new Lecture("Introduction to Spring Boot",
                    "Covers the basics of Spring Boot and how to quickly set up a project.");
            lectureRepo.save(lecture1);

            Lecture lecture2 = new Lecture("Spring MVC Fundamentals",
                    "Explains the MVC pattern and how to build controllers, services, and views.");
            lectureRepo.save(lecture2);

            Lecture lecture3 = new Lecture("Working with JPA and H2",
                    "Shows how to use Spring Data JPA with the H2 in-memory database.");
            lectureRepo.save(lecture3);

            Lecture lecture4 = new Lecture("Spring Security Basics",
                    "Introduces authentication, authorization, and role-based access control.");
            lectureRepo.save(lecture4);
        }

        if (pollRepo.count() == 0) {
            Poll poll1 = new Poll("Which topic should be covered next?");
            pollRepo.save(poll1);
            for (String opt : Arrays.asList("Spring Security", "Advanced JPA", "REST API", "Microservices", "Testing")) {
                PollOption option = new PollOption(opt, poll1);
                optionRepo.save(option);
            }

            Poll poll2 = new Poll("Which framework do you prefer for web development?");
            pollRepo.save(poll2);
            for (String opt : Arrays.asList("Spring Boot", "Jakarta EE", "Django", "Ruby on Rails", "ASP.NET Core")) {
                PollOption option = new PollOption(opt, poll2);
                optionRepo.save(option);
            }

            Poll poll3 = new Poll("What is the most challenging part of learning backend development?");
            pollRepo.save(poll3);
            for (String opt : Arrays.asList("Database design", "Security", "API design", "Deployment", "Testing")) {
                PollOption option = new PollOption(opt, poll3);
                optionRepo.save(option);
            }
        }
    }
}

