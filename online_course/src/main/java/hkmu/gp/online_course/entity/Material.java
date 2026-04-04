@Entity
public class Material {
    @Id @GeneratedValue
    private Long id;
    private String fileName;
    private String filePath;
    @ManyToOne
    private Lecture lecture;
}