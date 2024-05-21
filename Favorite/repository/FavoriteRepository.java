public interface FavoriteRepository extends JpaRepository<favorite, Integer> {
    Page<Favorite> findByUserId(Integer userId, Pageable pagenable);
    List<Favorite> findByHouseIdAndUserId(Integer houseId, Integer userId);
}