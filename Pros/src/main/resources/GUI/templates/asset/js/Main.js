document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('nav ul li a').forEach(function(link) {//chọn hết các thẻ nav,ul,li,a của navbar
        link.addEventListener('click', homepageNav);//thêm event cho từng thẻ đó
    });

    // Tải trang chủ mặc định khi tải trang
    loadPage('home');
});

function homepageNav(event) {
    event.preventDefault();// loại bỏ các sự kiện mặc định của thẻ
    const page = event.target.getAttribute('data-main-page');//kiểm tra xem dữ liệu biến
    loadPage(page);
}

function loadPage(page) {
    currentPage = 1;//gán lại current page mỗi khi load lại sub item mới
    fetch(page + '.html')
        .then(response => response.text())
        .then(html => {
            document.getElementById('main-content').innerHTML = html;
            
            // if (page === 'player' || page === 'team' || page === 'region' || page === 'tournament') {
            //     setUpSubPage();
            // }
            if(page !== 'home')
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
//----------------------------------------testLoadPage----------------------------------------
let currentPage = 1;
const rowsPerPage = 5;
let data = [];
function LoadSubPage()
{
    data = loadAPIData(document.getElementById('sub-page').getAttribute('current-page'));
    displayTable(data, currentPage);
    setupPaginationEvents2();
    let pageName = document.getElementById('sub-page').getAttribute('current-page');
    updatePagination2(pageName);
    SearchAction();
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
        //<td><a href="playerInfo.html" onclick="loadPlayerInfo('${item[keyItem[0]]}', event)">${item[keyItem[0]]}</a></td>
        row.innerHTML = `
            <th scope="row">${startIndex + index + 1}</th>
            <td><a href="playerInfo.html" onclick="loadPlayerInfo(event)">${item[keyItem[0]]}</a></td>
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
        // document.addEventListener("DOMContentLoaded", function() {
        //     pagination.style.display = 'flex';
        // });
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

function loadAPIData(page)
{
    switch(page)
    {
        // hardcode data in each case will replace with response API json
        case 'player':
            data =[ 
                { "PlayerName": "Levi", "Region": "VietNam", "Team": "GAM" },
                { "PlayerName": "Bang", "Region": "Korea", "Team": "T1" },
            ];
            //data = getDataFromAPI(page);
            return data;
        case 'team':
            data =[ 
                { "TeamName": "GAM", "Region": "VietNam", "Sponsor": "GAM" },
                { "TeamName": "T1", "Region": "Korea", "Sponsor": "T1" },
                { "TeamName": "TW", "Region": "VietNam", "Sponsor": "GAM" },
                { "TeamName": "GENG", "Region": "Korea", "Sponsor": "T1" },
                { "TeamName": "VKE", "Region": "VietNam", "Sponsor": "GAM" },
                { "TeamName": "GAM", "Region": "VietNam", "Sponsor": "GAM" },
                { "TeamName": "T1", "Region": "Korea", "Sponsor": "T1" },
                { "TeamName": "TW", "Region": "VietNam", "Sponsor": "GAM" },
                { "TeamName": "GENG", "Region": "Korea", "Sponsor": "T1" },
                { "TeamName": "VKE", "Region": "VietNam", "Sponsor": "GAM" },
                { "TeamName": "HLE", "Region": "Korea", "Sponsor": "T1" },
                { "TeamName": "SGB", "Region": "VietNam", "Sponsor": "GAM" },
                { "TeamName": "KT", "Region": "Korea", "Sponsor": "T1" }
            ];
            return data;
        case 'region':
            data =[ 
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "LCK", "Country": "Korea", "Team": "T1" },
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "LCK", "Country": "Korea", "Team": "T1" },
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "LCK", "Country": "Korea", "Team": "T1" },
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "LCK", "Country": "Korea", "Team": "T1" },
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "LCK", "Country": "Korea", "Team": "T1" },
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "LCK", "Country": "Korea", "Team": "T1" },
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "LCK", "Country": "Korea", "Team": "T1" },
                { "RegionName": "VCS", "Country": "VietNam", "Team": "GAM" },
                { "RegionName": "LCK", "Country": "Korea", "Team": "T1" }
            ];
            return data;
        case 'tournament':
            data =[ 
                { "TournamentName": "GAM", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "T1", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "TW", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GENG", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "VKE", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GAM", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "T1", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "TW", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GENG", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "VKE", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GAM", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "T1", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "TW", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GENG", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "VKE", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GAM", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "T1", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "TW", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GENG", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "VKE", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GAM", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "T1", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "TW", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GENG", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "VKE", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GAM", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "T1", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "TW", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "GENG", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "VKE", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "HLE", "Region": "Korea", "Sponsor": "T1" },
                { "TournamentName": "SGB", "Region": "VietNam", "Sponsor": "GAM" },
                { "TournamentName": "KT", "Region": "Korea", "Sponsor": "T1" }
            ];
            return data;

    }
}

async function getDataFromAPI(pageName)
{
    let apiRequest = 'https://localhost/getAll' + pageName;
    try{
        const response = await fetch(apiRequest);
        if(!response.ok)
            throw new Error(`${response.status} ... Opzz we can't get anydata from that !`)
        return await response.json();
    }
    catch(error)
    {
        console.log("Error when getting data from api: ", error);
    }
}

function loadPlayerInfo(event)
{
    event.preventDefault();
    fetch('playerInfo.html')
    .then(response => response.text())
    .then(html => {
        document.getElementById('main-content').innerHTML = html;

    })
    .catch(error=>{
        console.log("error when loading player Info : ", error);
    });
}

//------------------------------------Player page srcript ----------------------------------------
//Handle Search Action
function SearchAction()
{
    var searchBtnId = document.getElementById('sub-page').getAttribute('current-page') + "-table-search-btn";
    document.getElementById(searchBtnId).addEventListener("click",(event) => {SearchBtnAction()});
    //document.getElementsByClassName("search-btn").addEventListener("click",(event) => {SearchBtnAction()});
}

function SearchBtnAction()
{
    var optionSelected = document.getElementById("search-option").value;
    //--- search data based on selected item
    var data = searchOptionData(optionSelected);
    displayTable(data, currentPage);
    let pageName = document.getElementById('sub-page').getAttribute('current-page');
    updatePagination2(pageName);
}

function searchOptionData(optionSelected)
{
    switch(optionSelected.toUpperCase())
    {
        // hardcode data in each case will replace with response API json
        case 'ALL':
            data =[ 
                { "PlayerName": "Levi", "Region": "VietNam", "Team": "GAM" },
                { "PlayerName": "Kiaya", "Region": "VietNam", "Team": "GAM" },
                { "PlayerName": "Faker", "Region": "Korea", "Team": "T1" },
                { "PlayerName": "Bang", "Region": "Korea", "Team": "T1" },
                { "PlayerName": "Uzi", "Region": "China", "Team": "BLG" },
                { "PlayerName": "Knight", "Region": "China", "Team": "BLG" },
                { "PlayerName": "Caps", "Region": "EU", "Team": "C9" },
                { "PlayerName": "Rekkles", "Region": "EU", "Team": "C9" },
                { "PlayerName": "SOFM", "Region": "VietNam", "Team": "VKE" },
                { "PlayerName": "Chovy", "Region": "Korea", "Team": "Gen" }
            ];
            //data = getDataFromAPI(page);
            return data;
        case 'NAME':
            data =[ 
                { "PlayerName": "Levi", "Region": "VietNam", "Team": "GAM" }
            ];
            return data;
        case 'NATION':
            data =[ 
                { "PlayerName": "Levi", "Region": "VietNam", "Team": "GAM" },
                { "PlayerName": "Kiaya", "Region": "VietNam", "Team": "GAM" },
                { "PlayerName": "SOFM", "Region": "VietNam", "Team": "VKE" }
            ];
            return data;
        case 'TEAM':
            data =[ 
                { "PlayerName": "Faker", "Region": "Korea", "Team": "T1" },
                { "PlayerName": "Bang", "Region": "Korea", "Team": "T1" }
            ];
            return data;
        case 'ROLE':
            data =[
                { "PlayerName": "Uzi", "Region": "China", "Team": "BLG" },
                { "PlayerName": "Knight", "Region": "China", "Team": "BLG" }
            ];
            return data;
        default:
            data =[ 
                { "PlayerName": "Levi", "Region": "VietNam", "Team": "GAM" },
                { "PlayerName": "Kiaya", "Region": "VietNam", "Team": "GAM" },
                { "PlayerName": "Faker", "Region": "Korea", "Team": "T1" },
                { "PlayerName": "Bang", "Region": "Korea", "Team": "T1" },
                { "PlayerName": "Uzi", "Region": "China", "Team": "BLG" },
                { "PlayerName": "Knight", "Region": "China", "Team": "BLG" },
                { "PlayerName": "Caps", "Region": "EU", "Team": "C9" },
                { "PlayerName": "Rekkles", "Region": "EU", "Team": "C9" },
                { "PlayerName": "SOFM", "Region": "VietNam", "Team": "VKE" },
                { "PlayerName": "Chovy", "Region": "Korea", "Team": "Gen" }
            ];
            //data = getDataFromAPI(page);
            return data;

    }
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
