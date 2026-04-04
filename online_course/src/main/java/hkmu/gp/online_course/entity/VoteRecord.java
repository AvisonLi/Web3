package hkmu.gp.online_course.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VoteRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private PollOption selectedOption;

    private LocalDateTime votedAt;

    public VoteRecord() {}

    public VoteRecord(User user, Poll poll, PollOption selectedOption) {
        this.user = user;
        this.poll = poll;
        this.selectedOption = selectedOption;
        this.votedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Poll getPoll() { return poll; }
    public void setPoll(Poll poll) { this.poll = poll; }

    public PollOption getSelectedOption() { return selectedOption; }
    public void setSelectedOption(PollOption selectedOption) { this.selectedOption = selectedOption; }

    public LocalDateTime getVotedAt() { return votedAt; }
    public void setVotedAt(LocalDateTime votedAt) { this.votedAt = votedAt; }
}