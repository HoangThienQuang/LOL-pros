document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('nav ul li a').forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault();
            const page = event.target.getAttribute('data-page');

            fetch(page + '.html')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                })
                .then(html => {
                    document.getElementById('main-content2').innerHTML = html;
                })
                .catch(error => {
                    console.error('Error loading page:', error);
                    document.getElementById('main-content2').innerHTML = '<p>Error loading page.</p>';
                });
        });
    });

    // Tải trang chủ mặc định khi tải trang
    fetch('Player.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content2').innerHTML = html;
        });
});
