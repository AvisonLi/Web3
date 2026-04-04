package hkmu.gp.online_course.service;

import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.entity.VoteRecord;
import hkmu.gp.online_course.repository.VoteRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteRecordService {

    @Autowired
    private VoteRecordRepository voteRepo;

    public List<VoteRecord> findByUser(User user) {
        return voteRepo.findByUser(user);
    }
}