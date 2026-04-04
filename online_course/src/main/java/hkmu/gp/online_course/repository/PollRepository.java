package hkmu.gp.online_course.repository;

import hkmu.gp.online_course.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}