package hkmu.gp.online_course.dto;

import jakarta.validation.constraints.NotBlank;

public class LectureDto {
    @NotBlank
    private String title;
    @NotBlank
    private String summary;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}