document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('nav ul li a').forEach(function(link) {
        link.addEventListener('click', homepageNav);
    });

    // Tải trang chủ mặc định khi tải trang
    loadPage('home');
});

function homepageNav(event) {
    event.preventDefault();
    const page = event.target.getAttribute('data-main-page');
    loadPage(page);
}

function loadPage(page) {
    fetch(page + '.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
            
            if (page === 'player' || page === 'team' || page === 'region' || page === 'tournament') {
                setUpSubPage();
            }
        })
        .catch(error => {
            console.error('Error when loading page: ', error);
        });
}

 function setUpSubPage() {
//     // Đảm bảo rằng các sự kiện click chỉ được gắn một lần duy nhất
     const subNavLinks = document.querySelectorAll('#navbarNav .nav-link');
     subNavLinks.forEach(link => {
        link.removeEventListener('click', handleSubNavClick); // Xóa event listener cũ nếu có
        link.addEventListener('click', handleSubNavClick);
    });
}

function handleSubNavClick(event) {
    //event.preventDefault();
    const subPage = event.target.getAttribute('sub-data-page');
    fetch(subPage + '.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('sub-content').innerHTML = html;
        })
        .catch(error)
        {
            console.error('Loading subcontent error: ', error);
        }
    //console.log(subPage);

}
//--------------------------------------------------------------------------------

//----------------------------------function--------------------------------------
// function handleNavClick(event) {
//     event.preventDefault();
//     const page = event.target.getAttribute('data-page');
//     fetch(page + '.html')
//         .then(response => response.text())
//         .then(html => {
//             document.getElementById('main-content').innerHTML = html;
//                 setupSubPageLinks();
//         })
//         .catch(error => {
//             console.error('Error loading page:', error);
//         });
// }

// function setupSubPageLinks() {
//     const subNavLink = document.querySelectorAll('#navbarNav .nav-link');
//     subNavLink.forEach(function(link) {
//         link.addEventListener('click', function(event) {
//             event.preventDefault();
//             const subPage = event.target.getAttribute('data-page');
//             fetch(subPage + '.html')
//                 .then(response => response.text())
//                 .then(html => {
//                     document.getElementById('sub-content').innerHTML = html;
//                 })
//                 .catch(error => {
//                     console.error('Error loading sub-page:', error);
//                     document.getElementById('sub-content').innerHTML = '<p>Error loading sub-page.</p>';
//                 });
//         });
//     });
// }