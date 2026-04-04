package hkmu.gp.online_course.repository;

import hkmu.gp.online_course.entity.VoteRecord;
import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {
    List<VoteRecord> findByUser(User user);
    Optional<VoteRecord> findByUserAndPoll(User user, Poll poll);
}