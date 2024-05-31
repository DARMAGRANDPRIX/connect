    /* レビュー削除機能 */
    @DleteMapping("/{reviewId}")
    public String delete(@PathVariable("houseId") Integer houseId, @PathVariable("reviewId") Integer reviewId) {
       reviewService.delete(reviewId);
       return "redirect:/houses/" + houseId;
    }

         /* レビュー削除機能 */
         @Transactional
         public void delete(Integer reviewId) {
            Review review = reviewRepository.findbyId(reviewId);
            reviewRepository.delete(review);
         }