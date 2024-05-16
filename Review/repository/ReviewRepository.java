public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findByHouseId(Integer houseId, Pageable pageable);
}