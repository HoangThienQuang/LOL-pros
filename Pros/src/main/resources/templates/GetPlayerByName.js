function submitData() {
    const data = document.getElementById('playerInput').value;

    fetch('/player', {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain',
        },
        body: data,
    })
    .then(response => response.json())
    .then(json => {
        console.log('Thành công:', json);
        if (json.code === 100) {
            // Lưu dữ liệu vào localStorage
            localStorage.setItem('playerData', JSON.stringify(json.data));
            // Chuyển hướng sang player.html
            window.location.href = 'player.html';
        } else {
            console.error('Lỗi:', json.message);
        }
    })
    .catch((error) => {
        console.error('Lỗi:', error);
    });
}
