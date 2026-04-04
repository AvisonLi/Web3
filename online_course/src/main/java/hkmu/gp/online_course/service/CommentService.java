package hkmu.gp.online_course.service;

import hkmu.gp.online_course.entity.Comment;
import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    public List<Comment> findCommentsByUser(User user) {
        return commentRepo.findByAuthorOrderByCreatedAtDesc(user);
    }
}