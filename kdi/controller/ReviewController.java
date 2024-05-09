package com.example.samuraitravel.controller;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final ReviewService reviewService;

    public ReviewController (ReviewRepository reviewRepository, HouseRepository houseRepository, UserRepository userRepository, ReviewService reviewService){
        this.reviewRepository = reviewRepository;
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.reviewService = reviewService;
    }

    /* レビュー一覧 */
    @GetMapping
    public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
        List<Review> reviewPage = reviewRepository.findAll(pageable);
        model.addAttribute("reviewPage", reviewPage);
        return "reviews/index";
    }

    /* レビュー登録 */
    @GetMapping("/register")
    public String register (Model model) {
        model.addAttribute("reviewRegisterForm", new ReviewRegisterForm())
        return "reviews/register";
    }

    /* レビュー編集 */
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
        Review review = reviewRepository.getReferenceById(id);
        ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getRating(), review.getComment());

        model.addAttribute("reviewEditForm" ,reviewEditForm);
        return "reviews/edit";
    }
}