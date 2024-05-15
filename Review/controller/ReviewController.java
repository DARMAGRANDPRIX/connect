package com.example.samuraitravel.controller;

/* リポジトリとサービスの宣言、コンストラクタ */
@Controller
@RequestMapping("/houses/{houseId}/reviews")
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
    @GetMapping
    public String index(@PathVariable("houseId") Integer houseId, Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findAll(pageable);
        User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
        model.addAttribute("reviewPage", reviewPage);
        model.addAttribute("houseId", houseId);
        model.addAttribute("userId", user.getId());
        return "reviews/index";
    }

    /* レビュー投稿画面表示 */
    @GetMapping("/register")
    public String register (@PathVariable("houseId") Integer houseId, Model houseId) {
        model.addAttribute("reviewRegisterForm", new ReviewRegisterForm());
        model.addAttribute("houseId", houseId);
        return "reviews/register";
    }

    /* レビュー投稿機能 */
    @PostMapping("/create")
    public String create (@PathVariable("houseId") Integer houseId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @ModelAttribute @Validated ReviewRegisterForm reviewRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "reviews/register";
        }
        User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
        reviewService.create(houseId, reviewRegisterForm, user);
        redirectAttributes.addFlashAttribute("successMessage", "レビューを投稿しました。");
        return "redirect:/houses/" + houseId + "/reviews"
    }

    /* レビュー編集画面表示 */
    @GetMapping("/edit")
    public String edit(@PathVariable(name = "houseId") Integer houseId, Model model) {
        Review review = reviewRepository.getReferenceById(houseId);
        ReviewEditForm reviewEditForm = new ReviewEditForm(review.getId(), review.getRating(), review.getComment());
        model.addAttribute("reviewEditForm" ,reviewEditForm);
        model.addAttribute("houseId", houseId);
        return "reviews/edit";
    }

    /* レビュー更新機能 */
    @PostMapping("/update")
    public String update(@PathVariable("houseId") Integer houseId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @ModelAttribute @Validated ReviewEditForm reviewEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "reviews/edit";
        }
        User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
        reviewService.update(houseId, reviewEditForm, user);
        redirectAttributes.addFlashAttribute("successMessage", "レビューを編集しました。");
        return "redirect:/houses/" + houseId + "/reviews"
    }

    /* 削除用モーダル表示機能 */
    @GetMapping("/modal")
    public String modal(@PathVariable("houseId") Integer houseId, Model model) {
        Review review = reviewRepository.getReferenceById(houseId);
        model.addAttribute("houseId", houseId);
        return "reviews/index";
    }

     /* レビュー削除機能 */
     @PostMapping("{reviewId}/delete")
     public String delete(@PathVariable("houseId") Integer houseId, @PathVariable("reviewId") Integer reviewId, Model model, RedirectAttributes redirectAttributes) {
        reviewService.delete(reviewId);
        redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
        return "redirect:/houses/" + houseId + "/reviews";
     }
}