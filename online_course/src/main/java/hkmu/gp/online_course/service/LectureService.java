package hkmu.gp.online_course.service;

import hkmu.gp.online_course.dto.LectureDto;
import hkmu.gp.online_course.entity.Lecture;
import hkmu.gp.online_course.entity.Material;
import hkmu.gp.online_course.entity.User;
import hkmu.gp.online_course.entity.Comment;
import hkmu.gp.online_course.repository.LectureRepository;
import hkmu.gp.online_course.repository.MaterialRepository;
import hkmu.gp.online_course.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LectureService {

    @Autowired
    private LectureRepository lectureRepo;

    @Autowired
    private MaterialRepository materialRepo;

    @Autowired
    private CommentRepository commentRepo;

    public List<Lecture> findAll() {
        return lectureRepo.findAll();
    }

    public Lecture findById(Long id) {
        return lectureRepo.findById(id).orElse(null);
    }

    @Transactional
    public void save(LectureDto dto) {
        Lecture lecture = new Lecture(dto.getTitle(), dto.getSummary());
        lectureRepo.save(lecture);
    }

    @Transactional
    public void deleteLecture(Long id) {
        lectureRepo.deleteById(id);
    }

    @Transactional
    public void addMaterial(Long lectureId, String fileName, String filePath) {
        Lecture lecture = findById(lectureId);
        Material material = new Material(fileName, filePath, lecture);
        materialRepo.save(material);
    }

    @Transactional
    public void deleteMaterial(Long materialId) {
        materialRepo.deleteById(materialId);
    }

    @Transactional
    public void addComment(Long lectureId, User author, String content) {
        Lecture lecture = findById(lectureId);
        Comment comment = new Comment(content, author, lecture, null);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepo.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}