@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final HouseRepository houseRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, HouseRepository houseRepository) {
        this.reviewRepository = reviewRepository;
        this.houseRepository = houseRepository;
    }

    @Transactional
    public void create(Integer houseId, ReviewRegisterForm reviewRegisterForm) {
        Review review = new Review();
        review.setRating(reviewRegisterForm.getRating());
        review.setComment(reviewRegisterForm.getComment())
        House house = houseRepository.getReferenceById(houseId);
        review.setHouse(house);
        reviewRepository.save(review);
    }

    @Transactional
    public void update(Integer houseId, ReviewEditForm reviewEditForm) {
        Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
        review.setRating(reviewEditForm.getRating());
        review.setComment(reviewEditForm.getComment());
        House house = houseRepository.getReferenceById(houseId);
        review.setHouse(house);
        reviewRepository.save(review);
    }
}