package com.example.samuraitravel.controller;

@Controller
@RequestMapping("/admin/houses")
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

    @GetMapping
    public String index(Model model, Pageable pageable) {
        List<Review> reviewPage = reviewRepository.findAll(pageable);
        model.addAttribute("reviewPage", reviewPage);
        return "admin/houses/index";
    }

    @GetMapping("/register")
    public String register (Model model) {
        model.addAttribute("reviewRegisterForm", new ReviewRegisterForm())
        return "admin/houses/register";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
        Review review = reviewRepository.get
    }
}