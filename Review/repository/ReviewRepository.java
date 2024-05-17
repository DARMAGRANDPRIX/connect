public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByHouseId(Integer houseId, Pageable pageable);
    Optical<Review> findByHouseIdAndUserId(Integer houseId, Integer userId);
    List<Review> findTop6ByHouseIdOrderByCreatedAtDesc(Integer houseId);
}