@Entity
public class Poll {
    @Id @GeneratedValue
    private Long id;
    private String question;
    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<PollOption> options;
    @OneToMany(mappedBy = "poll")
    private List<Comment> comments;
}