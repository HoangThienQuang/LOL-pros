function loadSubPageLinks() {
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
}