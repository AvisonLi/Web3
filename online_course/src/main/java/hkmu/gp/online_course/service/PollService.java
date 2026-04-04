package hkmu.gp.online_course.service;

import hkmu.gp.online_course.dto.PollDto;
import hkmu.gp.online_course.entity.*;
import hkmu.gp.online_course.repository.PollRepository;
import hkmu.gp.online_course.repository.PollOptionRepository;
import hkmu.gp.online_course.repository.VoteRecordRepository;
import hkmu.gp.online_course.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepo;

    @Autowired
    private PollOptionRepository optionRepo;

    @Autowired
    private VoteRecordRepository voteRepo;

    @Autowired
    private CommentRepository commentRepo;

    public List<Poll> findAll() {
        return pollRepo.findAll();
    }

    public Poll findById(Long id) {
        return pollRepo.findById(id).orElse(null);
    }

    @Transactional
    public void createPoll(PollDto dto) {
        Poll poll = new Poll(dto.getQuestion());
        pollRepo.save(poll);
        for (String optText : dto.getOptions()) {
            PollOption option = new PollOption(optText, poll);
            optionRepo.save(option);
        }
    }

    @Transactional
    public void deletePoll(Long id) {
        pollRepo.deleteById(id);
    }

    @Transactional
    public void vote(Long pollId, Long optionId, User user) {
        Poll poll = findById(pollId);
        PollOption selected = optionRepo.findById(optionId).orElseThrow();

        VoteRecord existing = voteRepo.findByUserAndPoll(user, poll).orElse(null);
        if (existing != null) {
            existing.getSelectedOption().setVoteCount(existing.getSelectedOption().getVoteCount() - 1);
            existing.setSelectedOption(selected);
            selected.setVoteCount(selected.getVoteCount() + 1);
            voteRepo.save(existing);
        } else {
            VoteRecord record = new VoteRecord(user, poll, selected);
            selected.setVoteCount(selected.getVoteCount() + 1);
            voteRepo.save(record);
        }
        optionRepo.save(selected);
    }

    @Transactional
    public void addComment(Long pollId, User author, String content) {
        Poll poll = findById(pollId);
        Comment comment = new Comment(content, author, null, poll);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepo.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
    }

    public VoteRecord getUserVote(User user, Poll poll) {
        return voteRepo.findByUserAndPoll(user, poll).orElse(null);
    }
}