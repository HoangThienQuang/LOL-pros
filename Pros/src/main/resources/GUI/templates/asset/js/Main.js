document.addEventListener('DOMContentLoaded', function() {//thêm event đợi DOM load xong
    document.querySelectorAll('nav ul li a').forEach(function(link) {//chọn tất cả các phần tử nav ul li a sau đó thực hiện function link cho từng phần tử
        link.addEventListener('click', homepageNav);//link thực hiện thao tác lick phần tử và thực thi hàm handleNavClick
    });

    // Tải trang chủ mặc định khi tải trang
    fetch('home.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
        });
});

function homepageNav(event)
{
    event.preventDefault();
    const page = event.target.getAttribute('data-page');
    fetch(page + '.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
        })
        .catch(error => 
        {
            console.error('Error when loading page: ', error);
        }
        )
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