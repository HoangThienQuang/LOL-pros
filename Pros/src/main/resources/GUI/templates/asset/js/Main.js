document.addEventListener('DOMContentLoaded', function() {//thêm event đợi DOM load xong
    document.querySelectorAll('nav ul li a').forEach(function(link) {//chọn tất cả các phần tử nav ul li a sau đó thực hiện function link cho từng phần tử
        link.addEventListener('click', handleNavClick);//link thực hiện thao tác lick phần tử và thực thi hàm handleNavClick
    });

    // Tải trang chủ mặc định khi tải trang
    fetch('home.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
        });
});