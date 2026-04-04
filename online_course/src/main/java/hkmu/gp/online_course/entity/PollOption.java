package hkmu.gp.online_course.entity;

import jakarta.persistence.*;

@Entity
public class PollOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String optionText;

    private int voteCount;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    public PollOption() {}

    public PollOption(String optionText, Poll poll) {
        this.optionText = optionText;
        this.poll = poll;
        this.voteCount = 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOptionText() { return optionText; }
    public void setOptionText(String optionText) { this.optionText = optionText; }

    public int getVoteCount() { return voteCount; }
    public void setVoteCount(int voteCount) { this.voteCount = voteCount; }

    public Poll getPoll() { return poll; }
    public void setPoll(Poll poll) { this.poll = poll; }
}