// function search() {
//     let searchValue = parseInt(document.getElementById("search_string").value);
//     let resultBox = document.getElementById("search_result").firstElementChild;
//     let resultTitle = resultBox.firstElementChild;
//     if(resultTitle.style.display == 'none')
//         resultTitle.style.display = 'block';
//     else
//         resultBox.lastElementChild.remove();
//     if(document.getElementsByClassName("search_result_emptyHind")[0])
//         document.getElementsByClassName("search_result_emptyHind")[0].remove();
//     $.ajax({
//         url: '/search',
//         type: 'post',
//         async: true,
//         data: 'authorId='+searchValue,
//         dataType: 'json',
//         success: (data) => {
//             let li = document.createElement("li");
//             li.className = "search_result_content";
//             let p_authorId = document.createElement("p");
//             p_authorId.innerText = data.authorId;
//             p_authorId.style.margin = "0 2%";
//
//             let p_authorName = document.createElement("p");
//             p_authorName.innerText = data.authorName;
//             p_authorName.style.width = "52%";
//
//             let p_authorSex = document.createElement("p");
//             p_authorSex.innerText = data.authorSex;
//             p_authorSex.style.margin = "0 2%";
//
//             let p_authorAge = document.createElement("p");
//             p_authorAge.innerText = data.authorAge;
//             p_authorAge.style.margin = "0 7.5%";
//
//             li.appendChild(p_authorId);
//             li.appendChild(p_authorName);
//             li.appendChild(p_authorSex);
//             li.appendChild(p_authorAge);
//             resultBox.appendChild(li);
//         },
//         error: () => {
//             resultTitle.style.display = "none";
//             let li = document.createElement("li");
//             li.className = "search_result_emptyHind";
//             li.innerText = "该id无对应作者，请重试。";
//             resultBox.appendChild(li);
//         }
//     });
// }

//  监听键盘输入
// document.onkeydown = function (e) {
//     let keyNum = window.event ? e.keyCode : e.which;
//     if(keyNum == 13)
//         search();
// }

$(document).keyup(function (e) {
    if($('#search_string').is(":focus"))
        switch (e.keyCode) {
            case 13:
                search();
                return;
        }
})

function search() {
    let searchValue = document.getElementById("search_string").value;
    let resultBox = document.getElementById("search_result").firstElementChild;
    if(resultBox.childNodes.length != 0)
        resultBox.innerHTML="";
    if(document.getElementsByClassName("search_result_emptyHind")[0])
        document.getElementsByClassName("search_result_emptyHind")[0].remove();
    $.ajax({
        url: '/search',
        type: 'post',
        async: true,
        data: 'searchValue='+searchValue,
        dataType: 'json',
        success: (data) => {
            if(data[0] != null) {
                let li = document.createElement("li");
                li.className = "search_result_content";

                let p_authorId = document.createElement("p");
                p_authorId.innerText = data[0].authorId;

                let p_authorName = document.createElement("p");
                p_authorName.innerText = data[0].authorName;
                p_authorName.style.width = "52%";

                let p_authorSex = document.createElement("p");
                p_authorSex.innerText = data[0].authorSex;

                let p_authorAge = document.createElement("p");
                p_authorAge.innerText = data[0].authorAge;

                li.appendChild(p_authorId);
                li.appendChild(p_authorName);
                li.appendChild(p_authorSex);
                li.appendChild(p_authorAge);

                resultBox.appendChild(li);
            }
            if(data[1] != null) {
                let li = document.createElement("li");
                li.className = "search_result_content";

                let p_bookId = document.createElement("p");
                p_bookId.innerText = data[1].bookId;

                let p_bookName = document.createElement("p");
                p_bookName.innerText = data[1].bookName;
                p_bookName.style.width = "52%";

                let p_authorId = document.createElement("p");
                p_authorId.innerText = data[1].authorId;

                let p_ibsn = document.createElement("p");
                p_ibsn.innerText = data[1].ibsn;
                p_ibsn.style.width = "25%";

                let p_publishingCompanyId = document.createElement("p");
                p_publishingCompanyId.innerText = data[1].publishingCompanyId;

                let p_tagId = document.createElement("p");
                p_tagId.innerText = data[1].tagId;

                let p_quantity = document.createElement("p");
                p_quantity.innerText = data[1].quantity;
                p_quantity.style.marginLeft = "10%"

                let p_price = document.createElement("p");
                p_price.innerText = data[1].price;

                let p_isBeingBorrowed = document.createElement("p");
                p_isBeingBorrowed.innerText = data[1].isBeingBorrowed;

                let p_publicationDate = document.createElement("p");
                p_publicationDate.innerText = data[1].publicationDate;
                p_publicationDate.style.width = "25%";

                let p_jointAuthorTableId = document.createElement("p");
                p_jointAuthorTableId.innerText = data[1].jointAuthorTableId;

                li.appendChild(p_bookId);
                li.appendChild(p_bookName);
                li.appendChild(p_authorId);
                li.appendChild(p_ibsn);
                li.appendChild(p_publishingCompanyId);
                li.appendChild(p_tagId);
                li.appendChild(p_quantity);
                li.appendChild(p_price);
                li.appendChild(p_isBeingBorrowed);
                li.appendChild(p_publicationDate);
                li.appendChild(p_jointAuthorTableId);

                resultBox.appendChild(li);
            }
            if(data[2] != null) {
                let li = document.createElement("li");
                li.className = "search_result_content";

                let p_publishingCompanyId = document.createElement("p");
                p_publishingCompanyId.innerText = data[2].publishingCompanyId;

                let p_publishingCompanyName = document.createElement("p");
                p_publishingCompanyName.innerText = data[2].publishingCompanyName;
                p_publishingCompanyName.style.width = "25%";

                let p_publishingCompanyTelephoneNumber = document.createElement("p");
                p_publishingCompanyTelephoneNumber.innerText = data[2].publishingCompanyTelephoneNumber;
                p_publishingCompanyTelephoneNumber.style.width = "16%";

                let p_publishingCompanyAddress = document.createElement("p");
                p_publishingCompanyAddress.innerText = data[2].publishingCompanyAddress;
                p_publishingCompanyAddress.style.width = "52%";

                li.appendChild(p_publishingCompanyId);
                li.appendChild(p_publishingCompanyName);
                li.appendChild(p_publishingCompanyTelephoneNumber);
                li.appendChild(p_publishingCompanyAddress);

                resultBox.appendChild(li);
            }
            if(data[3] != null) {
                let li = document.createElement("li");
                li.className = "search_result_content";

                let p_readerId = document.createElement("p");
                p_readerId.innerText = data[3].readerId;

                let p_readerName = document.createElement("p");
                p_readerName.innerText = data[3].readerName;
                p_readerName.style.width = "25%";

                let p_readerSex = document.createElement("p");
                p_readerSex.innerText = data[3].readerSex;

                let p_readerAge = document.createElement("p");
                p_readerAge.innerText = data[3].readerAge;

                let p_saving = document.createElement("p");
                p_saving.innerText = data[3].saving;

                li.appendChild(p_readerId);
                li.appendChild(p_readerName);
                li.appendChild(p_readerSex);
                li.appendChild(p_readerAge);
                li.appendChild(p_saving);

                resultBox.appendChild(li);
            }
            if(data[4] != null) {
                let li = document.createElement("li");
                li.className = "search_result_content";

                let p_tagId = document.createElement("p");
                p_tagId.innerText = data[4].tagId;

                let p_tagName = document.createElement("p");
                p_tagName.innerText = data[4].tagName;
                p_tagName.style.width = "25%";

                li.appendChild(p_tagId);
                li.appendChild(p_tagName);

                resultBox.appendChild(li);
            }
            if(data[0] == null && data[1] == null && data[2] == null && data[3] == null && data[4] == null) {
                let li = document.createElement("li");
                li.className = "search_result_emptyHind";
                li.innerText = "无对应内容，请确认后重试。";
                resultBox.appendChild(li);
            }
        },
        error: () => {
            let li = document.createElement("li");
            li.className = "search_result_emptyHind";
            li.innerText = "无对应内容，请确认后重试。";
            resultBox.appendChild(li);
        }
    });
}