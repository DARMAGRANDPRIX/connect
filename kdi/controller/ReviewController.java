package com.example.samuraitravel.controller;

/* リポジトリとサービスの宣言、コンストラクタ */
@Controller
@RequestMapping("/houses/{id}")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController (ReviewRepository reviewRepository, HouseRepository houseRepository, UserRepository userRepository, ReviewService reviewService){
        this.reviewRepository = reviewRepository;
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.reviewService = reviewService;
    }

    /* レビュー一覧表示 */
    @GetMapping("/reviews")
    public String index(@PathVariable("id") Integer id, Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findAll(pageable);
        model.addAttribute("reviewPage", reviewPage);
        return "reviews/index";
    }

    /* レビュー投稿画面表示 */
    @GetMapping("/register")
    public String register (@PathVariable("id") Integer id, Model model) {
        model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
        return "reviews/register";
    }

    /* レビュー投稿機能 */
    @PostMapping("/create")
    public String create (@PathVariable("id") Integer id, @ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "reviews/register";
        }
        reviewService.create(id, reviewRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
        return "redirect:/houses/" + id + "/reviews"
    }

    /* レビュー編集画面表示 */
    @GetMapping("/edit")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
        Review review = reviewRepository.getReferenceById(id);
        ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getRating(), review.getComment());
        model.addAttribute("reviewEditForm" ,reviewEditForm);
        return "reviews/edit";
    }

    /* レビュー更新機能 */
    @PostMapping("/update")
    public String update(@PathVariable("id") Integer id, @ModelAttribute @Validated ReviewEditForm reviewEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "reviews/edit";
        }
        reviewService.update(id, reviewEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");
        return "redirect:/houses/" + id + "/reviews"
    }
}