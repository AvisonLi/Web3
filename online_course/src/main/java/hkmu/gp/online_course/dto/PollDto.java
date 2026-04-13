package hkmu.gp.online_course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class PollDto {
    @NotBlank(message = "{poll.question.required}")
    private String question;

    @NotNull(message = "{poll.options.required}")
    @Size(min = 5, max = 5, message = "{poll.options.size}")
    private List<@NotBlank String> options;

    // getters and setters
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
}