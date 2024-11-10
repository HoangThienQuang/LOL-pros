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
    currentPage = 1;
    fetch(page + '.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
            
            // if (page === 'player' || page === 'team' || page === 'region' || page === 'tournament') {
            //     setUpSubPage();
            // }
            // if(page === 'player')
            //     loadAllPlayer();
            // if(page === 'team')
            //     loadAllTeam();
            // if(page === 'tournament')
            //     loadpageData();
            LoadSubPage();
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
function addPaginationEvent(event)
{
    event.preventDefault();
    const text = link.innerText;
    if (text === '«' && currentPage > 1){
        currentPage--;
    }
    else if (text === '»'){
        const totalPages = Math.ceil(data.length / rowsPerPage);
        if (currentPage < totalPages) currentPage++;
    }
    else if (!isNaN(text)) {
        currentPage = Number(text);    
    }
    displayTable(data,currentPage);
    updatePagination2();
}
//----------------------------------------testLoadPage----------------------------------------
let currentPage = 1;
const rowsPerPage = 5;
let data = [];
function LoadSubPage()
{
    data =[ 
        { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
        { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" },
        { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
        { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" },
        { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
        { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" },
        { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
        { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" }
    ];
    displayTable(data, currentPage);
    setupPaginationEvents2();
    let pageName = document.getElementById('sub-page').getAttribute('current-page');
    updatePagination2(pageName);
}

function displayTable(data, currentPage)
{
    const startIndex = (currentPage - 1) * rowsPerPage;
    const endIndex = startIndex + rowsPerPage;
    const currentPageData = data.slice(startIndex, endIndex);

    let itemClicked = document.getElementById('sub-page');
    let elementId = itemClicked.getAttribute('current-page');
    let tableId = elementId + '-table-body';
    let keyItem = Object.keys(data[0]);

    
    const tbody = document.getElementById(tableId);
    tbody.innerHTML = '';//xóa dữ liệu cũ trước khi thêm dữ liệu mới

    currentPageData.forEach((item, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <th scope="row">${startIndex + index + 1}</th>
            <td>${item[keyItem[0]]}</td>
            <td>${item[keyItem[1]]}</td>
            <td>${item[keyItem[2]]}</td>
        `;
        tbody.appendChild(row);
    });
}

function setupPaginationEvents2()
{
    const pagination = document.getElementById('pagination');//lấy phần tử có id pagination
    const pageLinks = pagination.querySelectorAll('.page-link');//lấy tất cả các phần tử có thuộc tính page-link của pagination

    pageLinks.forEach(link => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            const text = link.innerText;
            if (text === '«' && currentPage > 1){
                currentPage--;
            }
            else if (text === '»'){
                const totalPages = Math.ceil(data.length / rowsPerPage);
                if (currentPage < totalPages) currentPage++;
            }
            else if (!isNaN(text)) {
                currentPage = Number(text);    
            }
            displayTable(data,currentPage);
            updatePagination2();
        });
    });
}

function addPaginationEvent(event)
{
    event.preventDefault();
            const text = link.innerText;
            if (text === '«' && currentPage > 1){
                currentPage--;
            }
            else if (text === '»'){
                const totalPages = Math.ceil(data.length / rowsPerPage);
                if (currentPage < totalPages) currentPage++;
            }
            else if (!isNaN(text)) {
                currentPage = Number(text);    
            }
            displayTable(data,currentPage);
            updatePagination2();
}
// function wrapAddPaginationEvent()
// {

// }

function updatePagination2(pageName)
{
    //const pagination2 = document.getElementById('pagination');
    let paginationTag = `[pagination-page="${pageName}-pagination"]`;
    const pagination = document.querySelector(paginationTag);
    const totalPages = Math.ceil(data.length / rowsPerPage);

    if(totalPages <= 1)
    {
        pagination.style.display = 'none';
    }
    else
    {
        pagination.style.display = 'flex';
    }

    let paginationHTML = `
    <li class="page-item">
        <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
        </a>
    </li>`;

    if (totalPages <= 4) {
        for (let i = 1; i <= totalPages; i++) {
            paginationHTML += `<li class="page-item"><a class="page-link" href="#">${i}</a></li>`;
        }
    } else {
        paginationHTML += `<li class="page-item"><a class="page-link" href="#">1</a></li>
                           <li class="page-item"><a class="page-link" href="#">2</a></li>
                           <li class="page-item"><a class="page-link" href="#">3</a></li>
                           <li class="page-item">...</li>
                           <li class="page-item"><a class="page-link" href="#">${totalPages}</a></li>`;
    }

    paginationHTML += `
        <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>`;

    pagination.innerHTML = paginationHTML;
    setupPaginationEvents2()
}


// //---------------------------------- player table function--------------------------------------
// // let currentPage = 1;
// // const rowsPerPage = 5;
// // let data = [];
// function loadAllPlayer()
// {
//     data = [
//         { "ingameName": "Levi", "National": "VietNam", "Team": "GAM" },
//         { "ingameName": "Bang", "National": "Korea", "Team": "T1" },
//         { "ingameName": "Faker", "National": "Korea", "Team": "T1" },
//         { "ingameName": "Levi", "National": "VietNam", "Team": "GAM" },
//         { "ingameName": "Bang", "National": "Korea", "Team": "T1" },
//         { "ingameName": "Faker", "National": "Korea", "Team": "T1" },
//         { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
//         { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
//         { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
//         { "ingameName": "Levi", "National": "VietNam", "Team": "GAM" },
//         { "ingameName": "Bang", "National": "Korea", "Team": "T1" },
//         { "ingameName": "Faker", "National": "Korea", "Team": "T1" },
//         { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
//         { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
//         { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
//         { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
//         { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
//         { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
//         { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
//         { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
//         { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
//         { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
//         { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
//         { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
//         { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
//         { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
//         { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
//         { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
//         { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
//         { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
//         { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
//         { "ingameName": "Uzi", "National": "China", "Team": "RNG" },
//         { "ingameName": "Caps", "National": "Denmark", "Team": "G2" },
//         { "ingameName": "Perkz", "National": "Croatia", "Team": "Cloud9" },
//         { "ingameName": "Doublelift", "National": "USA", "Team": "TSM" }
//     ];
//     displayPlayerTable(data, currentPage);
//     setupPaginationEvents();
//     updatePagination();
//     // fetch('player-data-api-endpoint')
//     // .then(response => response.json())
//     // .then(data => {
//     //      const tbody = document.getElementById('player-table-body');
//     //      // Đổ dữ liệu vào bảng
//     //          displayTableData(data, currentPage);
//     //         })
//     //         .catch(error => { console.error('Error fetching player data:', error); });
// }

// function displayPlayerTable(data, page) {
//     const startIndex = (page - 1) * rowsPerPage;
//     const endIndex = startIndex + rowsPerPage;
//     const currentPageData = data.slice(startIndex, endIndex);

//     let itemClicked = document.getElementById('sub-page');
//     let elementId = itemClicked.getAttribute('current-page');
//     let tableId = elementId + '-table-body';
//     let keyItem = Object.keys(data[0]);

    
//     const tbody = document.getElementById(tableId);
//     tbody.innerHTML = '';//xóa dữ liệu cũ trước khi thêm dữ liệu mới

//     currentPageData.forEach((item, index) => {
//         const row = document.createElement('tr');
//         row.innerHTML = `
//             <th scope="row">${startIndex + index + 1}</th>
//             <td>${item[keyItem[0]]}</td>
//             <td>${item[keyItem[1]]}</td>
//             <td>${item[keyItem[2]]}</td>
//         `;
//         tbody.appendChild(row);
//     });
// }

// function setupPaginationEvents()
// {
//     const pagination = document.getElementById('pagination');//lấy phần tử có id pagination
//     const pageLinks = pagination.querySelectorAll('.page-link');//lấy tất cả các phần tử có thuộc tính page-link của pagination

//     pageLinks.forEach(link => {
//         link.addEventListener('click', (event) => {
//             event.preventDefault();
//             const text = link.innerText;
//             if (text === '«' && currentPage > 1){
//                 currentPage--;
//             }
//             else if (text === '»'){
//                 const totalPages = Math.ceil(data.length / rowsPerPage);
//                 if (currentPage < totalPages) currentPage++;
//             }
//             else if (!isNaN(text)) {
//                 currentPage = Number(text);    
//             }
//             displayPlayerTable(data,currentPage);
//             updatePagination();
//         });
//     });
// }


// function updatePagination()
// {
//     //const pagination2 = document.getElementById('pagination');
//     const pagination = document.querySelector('[pagination-page="player-pagination"]')
//     const totalPages = Math.ceil(data.length / rowsPerPage);

//     if(totalPages <= 1)
//     {
//         pagination.style.display = 'none';
//     }
//     else
//     {
//         pagination.style.display = 'flex';
//     }

//     let paginationHTML = `
//     <li class="page-item">
//         <a class="page-link" href="#" aria-label="Previous">
//             <span aria-hidden="true">&laquo;</span>
//         </a>
//     </li>`;

//     if (totalPages <= 4) {
//         for (let i = 1; i <= totalPages; i++) {
//             paginationHTML += `<li class="page-item"><a class="page-link" href="#">${i}</a></li>`;
//         }
//     } else {
//         paginationHTML += `<li class="page-item"><a class="page-link" href="#">1</a></li>
//                            <li class="page-item"><a class="page-link" href="#">2</a></li>
//                            <li class="page-item"><a class="page-link" href="#">3</a></li>
//                            <li class="page-item">...</li>
//                            <li class="page-item"><a class="page-link" href="#">${totalPages}</a></li>`;
//     }

//     paginationHTML += `
//         <li class="page-item">
//             <a class="page-link" href="#" aria-label="Next">
//                 <span aria-hidden="true">&raquo;</span>
//             </a>
//         </li>`;

//     pagination.innerHTML = paginationHTML;
//     setupPaginationEvents()
// }

// //----------------------------------team table function --------------------------------------
// let teamCurrentSheet = 1;
// const teamMaxRow = 5;
// let teamData = [];
// function loadAllTeam()
// {
//     teamData = [
//         { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
//         { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Faker", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
//         { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Faker", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Uzi", "Region": "China", "Sponsor": "RNG" },
//         { "TeamName": "Caps", "Region": "Denmark", "Sponsor": "G2" },
//         { "TeamName": "Perkz", "Region": "Croatia", "Sponsor": "Cloud9" },
//         { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
//         { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Faker", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
//         { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Faker", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Uzi", "Region": "China", "Sponsor": "RNG" },
//         { "TeamName": "Caps", "Region": "Denmark", "Sponsor": "G2" },
//         { "TeamName": "Perkz", "Region": "Croatia", "Sponsor": "Cloud9" },
//         { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
//         { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Faker", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Levi", "Region": "VietNam", "Sponsor": "GAM" },
//         { "TeamName": "Bang", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Faker", "Region": "Korea", "Sponsor": "T1" },
//         { "TeamName": "Uzi", "Region": "China", "Sponsor": "RNG" },
//         { "TeamName": "Caps", "Region": "Denmark", "Sponsor": "G2" },
//         { "TeamName": "Perkz", "Region": "Croatia", "Sponsor": "Cloud9" }
//     ];
//     displayTeamTable(teamData, teamCurrentSheet);
//     setupTeamPaginationEvents();
//     updateTeamPagination();
//     // fetch('player-data-api-endpoint')
//     // .then(response => response.json())
//     // .then(data => {
//     //      const tbody = document.getElementById('player-table-body');
//     //      // Đổ dữ liệu vào bảng
//     //          displayTableData(data, currentPage);
//     //         })
//     //         .catch(error => { console.error('Error fetching player data:', error); });
// }

// function displayTeamTable(teamData, teamCurrentSheet) {
//     const startIndex = (teamCurrentSheet - 1) * rowsPerPage;
//     const endIndex = startIndex + rowsPerPage;
//     const currentPageData = teamData.slice(startIndex, endIndex);

    
//     const tbody = document.getElementById('team-table-body');
//     tbody.innerHTML = '';//xóa dữ liệu cũ trước khi thêm dữ liệu mới

//     currentPageData.forEach((item, index) => {
//         const row = document.createElement('tr');
//         row.innerHTML = `
//             <th scope="row">${startIndex + index + 1}</th>
//             <td>${item.TeamName}</td>
//             <td>${item.Region}</td>
//             <td>${item.Sponsor}</td>
//         `;
//         tbody.appendChild(row);
//     });
// }

// function setupTeamPaginationEvents()
// {
//     //const pagination = document.getElementById('pagination');//lấy phần tử có id pagination
//     const pagination = document.querySelector('[pagination-page="team-pagination"]')
//     const pageLinks = pagination.querySelectorAll('.page-link');//lấy tất cả các phần tử có thuộc tính page-link của pagination

//     pageLinks.forEach(link => {
//         link.addEventListener('click', (event) => {
//             event.preventDefault();
//             const text = link.innerText;
//             if (text === '«' && teamCurrentSheet > 1){
//                 teamCurrentSheet--;
//             }
//             else if (text === '»'){
//                 const totalPages = Math.ceil(teamData.length / rowsPerPage);
//                 if (teamCurrentSheet < totalPages) teamCurrentSheet++;
//             }
//             else if (!isNaN(text)) {
//                 teamCurrentSheet = Number(text);    
//             }
//             displayTeamTable(teamData,teamCurrentSheet);
//             updateTeamPagination();
//         });
//     });
// }

// function updateTeamPagination()
// {
//     //const pagination2 = document.getElementById('pagination');
//     const pagination = document.querySelector('[pagination-page="team-pagination"]')
//     const totalPages = Math.ceil(teamData.length / rowsPerPage);

//     if(totalPages <= 1)
//     {
//         pagination.style.display = 'none';
//     }
//     else
//     {
//         pagination.style.display = 'flex';
//     }

//     let paginationHTML = `
//     <li class="page-item">
//         <a class="page-link" href="#" aria-label="Previous">
//             <span aria-hidden="true">&laquo;</span>
//         </a>
//     </li>`;

//     if (totalPages <= 4) {
//         for (let i = 1; i <= totalPages; i++) {
//             paginationHTML += `<li class="page-item"><a class="page-link" href="#">${i}</a></li>`;
//         }
//     } else {
//         paginationHTML += `<li class="page-item"><a class="page-link" href="#">1</a></li>
//                            <li class="page-item"><a class="page-link" href="#">2</a></li>
//                            <li class="page-item"><a class="page-link" href="#">3</a></li>
//                            <li class="page-item">...</li>
//                            <li class="page-item"><a class="page-link" href="#">${totalPages}</a></li>`;
//     }

//     paginationHTML += `
//         <li class="page-item">
//             <a class="page-link" href="#" aria-label="Next">
//                 <span aria-hidden="true">&raquo;</span>
//             </a>
//         </li>`;

//     pagination.innerHTML = paginationHTML;
//     setupTeamPaginationEvents()
// }