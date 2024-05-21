@Service
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final HouseRepository houseRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, HouseRepository houseRepository) {
        this.favoriteRepository = favoriteRepository;
        this.houseRepository = houseRepository;
    }

    /* お気に入り追加機能 */
    @Transactional
    public void add(Integer houseId, Integer userId) {
        Favorite favorite = new Favorite();
        User user = userRepository.getReferenceById(userId)
        House house = houseRepository.getReferenceById(houseId);
        favorite.setUser(user);
        favorite.setHouse(house);
        favoriteRepository.save(favorite);
    }

    /* お気に入り削除機能 */
    @Transactional
    public void delete(Integer houseId, Integer userId) {
        Favorite favorite = favoriteRepository.findByHouseIdAndUserId(houseId, userId);
        favoriteRepository.delete(favorite);
    }
}