@Entity
public class Comment {
    @Id @GeneratedValue
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne
    private User author;
    @ManyToOne
    private Lecture lecture;
    @ManyToOne
    private Poll poll;
}