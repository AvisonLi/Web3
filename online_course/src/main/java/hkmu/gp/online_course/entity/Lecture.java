@Entity
public class Lecture {
    @Id @GeneratedValue
    private Long id;
    private String title;
    @Column(length = 2000)
    private String summary;
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Material> materials;
    @OneToMany(mappedBy = "lecture")
    private List<Comment> comments;}