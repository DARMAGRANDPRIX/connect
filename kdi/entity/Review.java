 @Entity
 @Table(name = "reviews")
 @Data
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @Column (name = "rating")
    private Integer rating;

    @Column (name = "comment")
    private String comment;

    @Column (name = "created_at" , insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column (name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;
}