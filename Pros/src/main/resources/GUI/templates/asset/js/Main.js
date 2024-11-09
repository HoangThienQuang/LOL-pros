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
            
            // if (page === 'player' || page === 'team' || page === 'region' || page === 'tournament') {
            //     setUpSubPage();
            // }
            if(page === 'player')
                loadAllPlayer();
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
        .catch(error => {
            console.error('Loading subcontent error: ', error);
        })
    //console.log(subPage);
}
//--------------------------------------------------------------------------------

//---------------------------------- player table function--------------------------------------
let currentPage = 1;
const rowsPerPage = 5;
let data = [];
function loadAllPlayer()
{
    data = [
        { "ingameName": "Levi", "National": "VietNam", "Team": "GAM" },
        { "ingameName": "Bang", "National": "Korea", "Team": "T1" },
        { "ingameName": "Faker", "National": "Korea", "Team": "T1" },
        { "ingameName": "Levi", "National": "VietNam", "Team": "GAM" },
        { "ingameName": "Bang", "National": "Korea", "Team": "T1" },
        { "ingameName": "Faker", "National": "Korea", "Team": "T1" },
        { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
        { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
        { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
        { "ingameName": "Levi", "National": "VietNam", "Team": "GAM" },
        { "ingameName": "Bang", "National": "Korea", "Team": "T1" },
        { "ingameName": "Faker", "National": "Korea", "Team": "T1" },
        { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
        { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
        { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
        { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
        { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
        { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
        { "ingameName": "Doublelift", "National": "USA", "Team": "TSM" }
    ];
    displayPage(data, currentPage);
    setupPaginationEvents();
    // fetch('player-data-api-endpoint')
    // .then(response => response.json())
    // .then(data => {
    //      const tbody = document.getElementById('player-table-body');
    //      // Đổ dữ liệu vào bảng
    //          displayTableData(data, currentPage);
    //         })
    //         .catch(error => { console.error('Error fetching player data:', error); });
}

function setupPaginationEvents()
{
    const pagination = document.getElementById('pagination');//lấy phần tử có id pagination
    const pageLinks = pagination.querySelectorAll('.page-link');//lấy tất cả các phần tử có thuộc tính page-link của pagination

    pageLinks.forEach(link => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            const text = link.innerText;
            if (text === '«'){
                if (currentPage > 1) currentPage--;
            }
            else if (text === '»'){
                const totalPages = Math.ceil(data.length / rowsPerPage);
                if (currentPage < totalPages) currentPage++;
            }
            else if (!isNaN(text)) {
                currentPage = Number(text);    
            }
            displayPage(data,currentPage);
        });
    });
}

function displayPage(data, page) {
    const startIndex = (page - 1) * rowsPerPage;
    const endIndex = startIndex + rowsPerPage;
    const currentPageData = data.slice(startIndex, endIndex);

    
    const tbody = document.getElementById('player-table-body');
    tbody.innerHTML = '';

    currentPageData.forEach((player, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <th scope="row">${startIndex + index + 1}</th>
            <td>${player.ingameName}</td>
            <td>${player.National}</td>
            <td>${player.Team}</td>
        `;
        tbody.appendChild(row);
    });
}