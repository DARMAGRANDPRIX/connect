document.addEventListener('DOMContentLoaded', function () {
    // 削除確認ボタンを取得
    var confirmDeleteButton = document.getElementById('confirmDeleteButton');

    // 削除確認ボタンがクリックされたときのイベントリスナーを設定
    confirmDeleteButton.addEventListener('click', function () {
        // モーダルからレビューIDを取得
        var reviewId = confirmDeleteButton.getAttribute('data-review-id');
        // 削除リクエストを送信するURLを構築（houseIdは適切な値に置き換える）
        var deleteUrl = '/houses/' + houseId + '/reviews/' + reviewId + '/delete';

        // Ajaxリクエストを使用してサーバーに削除をリクエスト
        fetch(deleteUrl, {
            method: 'POST',
            headers: {
                'X-CSRF-TOKEN': csrfToken // CSRFトークンをヘッダーに追加（必要に応じて）
            }
        })
            .then(response => {
                if (response.ok) {
                    // 削除が成功した場合、ページをリロードするか、モーダルを閉じてUIを更新
                    location.reload();
                } else {
                    // エラーが発生した場合、ユーザーに通知
                    alert('削除に失敗しました。');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});