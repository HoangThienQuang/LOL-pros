function handleNavClick(event) {// tạo 1 function handleNavClick nhận 1 event đầu vào
    event.preventDefault(); //chặn các thực thi mặc định của event đó
    const page = event.target.getAttribute('data-page'); //lấy giá trị từ thuộc tính có tên data-page từ event đó
    fetch(page + '.html') // gọi fletch để tải nội dung trang có tên "giá trị.hmtl"
        .then(response => { //lấy phản hồi
            if (!response.ok) { // kiểm tra phản hồi
                throw new Error('Network response was not ok');
            }
            return response.text();// thành công thì chuyển phản hồi thành dạng chuỗi HTML
        })
        .then(html => { //sau đó thay đổi nội dung trang HTML
            document.getElementById('main-content').innerHTML = html; // thay đổi nội dung bên trong của phần tử có id
            loadSubPageLinks(); // gọi hàm loadSubPageLinks để thiết lập các sự kiện cho liên kết con bên trong id
        })
        .catch(error => {// bắt lỗi nếu xảy ra lỗi
            console.error('Error loading page:', error);
            document.getElementById('main-content').innerHTML = '<p>Error loading page.</p>';
        });
}