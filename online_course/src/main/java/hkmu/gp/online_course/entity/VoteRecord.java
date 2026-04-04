@Entity
public class VoteRecord {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Poll poll;
    @ManyToOne
    private PollOption selectedOption;
    private LocalDateTime votedAt;
}