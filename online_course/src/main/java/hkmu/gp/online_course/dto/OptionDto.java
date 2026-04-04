package hkmu.gp.online_course.dto;

public class OptionDto {
    private Long id;
    private String text;
    private int votes;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public int getVotes() { return votes; }
    public void setVotes(int votes) { this.votes = votes; }
}