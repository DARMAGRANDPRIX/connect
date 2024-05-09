@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void create(ReviewRegisterForm reviewRegisterForm) {
        Review review = new Review();
        review.setRating(reviewRegisterForm.getRating());
        review.setComment(reviewRegisterForm.getComment())
        reviewRepository.save(review);
    }

    @Transactional
    public void update(ReviewEditForm reviewEditForm) {
        Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
        review.setRating(reviewEditForm.getRating());
        review.setComment(reviewEditForm.getComment());
        reviewRepository.save(review);
    }
}