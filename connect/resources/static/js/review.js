document.addEventListener('DOMContentLoaded', function () {
    var deleteModalElement = document.getElementById('deleteModal');
    var confirmDeleteButton = document.getElementById('confirmDeleteButton');
    var currentReviewId = null;
    var currentHouseId = null;

    // 各削除ボタンにイベントリスナーを追加
    document.querySelectorAll('.delete-button').forEach(button => {
        button.addEventListener('click', function () {
            currentReviewId = button.getAttribute('data-review-id');
            currentHouseId = button.getAttribute('data-house-id');
        });
    });

    // 削除確認ボタンがクリックされたとき
    confirmDeleteButton.addEventListener('click', function () {
        if (!currentReviewId || !currentHouseId) {
            alert('レビューIDまたは民宿IDが見つかりません。');
            return;
        }

        var deleteUrl = '/houses/' + currentHouseId + '/reviews/' + currentReviewId + '/delete';
        var csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');

        fetch(deleteUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfToken
            }
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                return response.json().then(data => {
                    alert('削除に失敗しました。エラー: ' + data.message);
                });
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('削除中にエラーが発生しました。詳細はコンソールを確認してください。');
        });
    });
});
