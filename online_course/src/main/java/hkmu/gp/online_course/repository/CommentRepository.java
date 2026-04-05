package hkmu.gp.online_course.repository;
import hkmu.gp.online_course.entity.Poll;
import hkmu.gp.online_course.entity.Comment;
import hkmu.gp.online_course.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthorOrderByCreatedAtDesc(User user);
    List<Comment> findByPoll(Poll poll);
}