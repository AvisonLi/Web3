package hkmu.gp.online_course.repository;
import hkmu.gp.online_course.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}