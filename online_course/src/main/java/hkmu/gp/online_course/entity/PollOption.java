@Entity
public class PollOption {
    @Id @GeneratedValue
    private Long id;
    private String optionText;
    private int voteCount;
    @ManyToOne
    private Poll poll;
}