@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService (ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void saveReview(ReviewForm reviewForm) {
        Review review = new Review();
        review.setRating(reviewForm.getRating());
        review.setComment(reviewForm.getComment())
        reviewRepository.save(review);
    }
}