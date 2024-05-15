@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final HouseRepository houseRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, HouseRepository houseRepository) {
        this.reviewRepository = reviewRepository;
        this.houseRepository = houseRepository;
    }

     /* レビュー投稿機能 */
    @Transactional
    public void create(Integer houseId, ReviewRegisterForm reviewRegisterForm, User user) {
        Review review = new Review();
        House house = houseRepository.getReferenceById(houseId);
        review.setRating(reviewRegisterForm.getRating());
        review.setComment(reviewRegisterForm.getComment())
        review.setHouse(house);
        review.setUser(user);
        reviewRepository.save(review);
    }

     /* レビュー更新機能 */
    @Transactional
    public void update(Integer houseId, ReviewEditForm reviewEditForm, User user) {
        Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
        House house = houseRepository.getReferenceById(houseId);
        review.setRating(reviewEditForm.getRating());
        review.setComment(reviewEditForm.getComment());
        review.setHouse(house);
        review.setUser(user);
        reviewRepository.save(review);
    }

     /* レビュー削除機能 */
     @Transactional
     public void delete(Integer reviewId) {
        Review review = reviewRepository.findbyId(reviewId);
        reviewRepository.delete(review);
     }
}