package hkmu.gp.online_course.repository;

import hkmu.gp.online_course.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollOptionRepository extends JpaRepository<PollOption, Long> {
}