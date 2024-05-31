// delete-review.js
document.addEventListener('DOMContentLoaded', function () {
    const deleteButtons = document.querySelectorAll('.delete-review-button');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
            const reviewId = this.getAttribute('data-review-id');
            const houseId = this.getAttribute('data-house-id');
            const confirmDeleteButton = document.getElementById('confirmDeleteButton');
            confirmDeleteButton.addEventListener('click', function () {
                deleteReview(reviewId, houseId);
            }, { once: true });
            var deleteReviewModal = new bootstrap.Modal(document.getElementById('deleteReviewModal'));
            deleteReviewModal.show();
        });
    });
});

function deleteReview(reviewId, houseId) {
    fetch(`/houses/${houseId}/reviews/${reviewId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                window.location.href = `/houses/${houseId}`;
            } else {
                alert('削除に失敗しました。');
            }
        })
        .catch(error => console.error('Error:', error));
}