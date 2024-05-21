 @Entity
 @Table(name = "favorites")
 @Data
public class Favorite {
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

    @Column (name = "created_at" , insertable = false, updatable = false)
    private Timestamp createdAt;
}