package com.example.samuraitravel.controller;

/* リポジトリとサービスの宣言、コンストラクタ */
@Controller
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController (ReviewRepository reviewRepository, HouseRepository houseRepository, UserRepository userRepository, ReviewService reviewService){
        this.favoriteRepository = favoriteRepository;
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.favoriteService = favoriteService;
    }

    /* お気に入り一覧表示 */
    @GetMapping("/favorites")
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
        User user = userDetailsImpl.getUser();
        Page<Favorite> favoritePage = favoriteRepository.findByUserId(user.getId(), pageable);
        model.addAttribute("favoritePage", favoritePage);
        return "favorites/index";
    }

    /* お気に入り追加機能 */
    @PostMapping("houses/{houseId}/favorites/add")
    public String add (@PathVariable("houseId") Integer houseId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        User user = userDetailsImpl.getUser();
        favoriteService.add(houseId, user.getId());
        return "redirect:/houses/" + houseId;
    }


     /* お気に入り解除機能 */
     @PostMapping("houses/{houseId}/favorites/delete")
     public String add (@PathVariable("houseId") Integer houseId, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
        User user = userDetailsImpl.getUser();
         favoriteService.delete(houseId, user.getId());
         return "redirect:/houses/" + houseId;
     }
}