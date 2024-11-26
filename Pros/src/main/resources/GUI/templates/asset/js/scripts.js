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
                    document.getElementById('main-content').innerHTML = html;
                    document.querySelectorAll('#main-content nav ul li a').forEach(function(link) {
                        link.addEventListener('click', function(event) {
                            event.preventDefault();
                            const subPage = event.target.getAttribute('data-page');

                            fetch(subPage + '.html')
                                .then(response => response.text())
                                .then(html => {
                                    document.getElementById('main-content').innerHTML = html;
                                })
                                .catch(error => {
                                    console.error('Error loading page:', error);
                                    document.getElementById('main-content').innerHTML = '<p>Error loading page.</p>';
                                });
                        });
                    });
                })
                .catch(error => {
                    console.error('Error loading page:', error);
                    document.getElementById('main-content').innerHTML = '<p>Error loading page.</p>';
                });
        });
    });

    // Tải trang chủ mặc định khi tải trang
    fetch('home.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
        });
});
