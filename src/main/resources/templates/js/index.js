$(document).keyup(function (e) {
    // let key = String.fromCharCode(e.keyCode || e.which)
    // console.log(key)
    let searchString = $('#search_string')
    if (searchString.is(":focus"))
        switch (e.keyCode) {
            case 13:
                search();
                return;
            case 27:
                searchString.blur()
                return;
        }
    else {
        switch (e.keyCode) {
            case 191:
                searchString.focus()
                return;
        }
    }
})

function search() {
    let searchValue = document.getElementById("search_string").value;
    let resultBox = document.getElementById("search_result").firstElementChild;
    if (resultBox.childNodes.length !== 0)
        resultBox.innerHTML = "";
    if (document.getElementsByClassName("search_result_emptyHind")[0])
        document.getElementsByClassName("search_result_emptyHind")[0].remove();
    $.ajax({
        url: '/search',
        type: 'post',
        async: true,
        data: 'searchValue=' + searchValue,
        dataType: 'json',
        success: (res) => {
            if (res.code === 200) {
                let data = res.data;
                if (data[0] != null) {
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    // let td_authorId = document.createElement("td");
                    // td_authorId.innerText = data[0].authorId;

                    let td_authorName = document.createElement("td");
                    td_authorName.innerText = data[0].authorName;

                    let td_authorSex = document.createElement("td");
                    switch (data[0].authorSex) {
                        case 0:
                            td_authorSex.innerText = '女';
                            break
                        case 1:
                            td_authorSex.innerText = '男';
                            break
                        case 2:
                            td_authorSex.innerText = '保密';
                            break
                        default:
                            td_authorSex.innerText = '？'
                    }

                    let td_authorAge = document.createElement("td");
                    td_authorAge.innerText = data[0].authorAge + '岁';

                    // tr.appendChild(td_authorId);
                    tr.appendChild(td_authorName);
                    tr.appendChild(td_authorSex);
                    tr.appendChild(td_authorAge);

                    resultBox.appendChild(tr);
                }
                if (data[1] != null) {
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    // let td_bookId = document.createElement("td");
                    // td_bookId.innerText = data[1].bookId;

                    let td_bookName = document.createElement("td");
                    td_bookName.innerText = data[1].bookName;

                    let td_authorId = document.createElement("td");
                    td_authorId.innerText = data[1].authorId;

                    let td_ibsn = document.createElement("td");
                    td_ibsn.innerText = data[1].ibsn;

                    let td_publishingCompanyId = document.createElement("td");
                    td_publishingCompanyId.innerText = data[1].publishingCompanyId;

                    let td_tagId = document.createElement("td");
                    td_tagId.innerText = data[1].tagId;

                    let td_quantity = document.createElement("td");
                    td_quantity.innerText = data[1].quantity;

                    let td_price = document.createElement("td");
                    td_price.innerText = data[1].price;

                    let td_isBeingBorrowed = document.createElement("td");
                    td_isBeingBorrowed.innerText = data[1].isBeingBorrowed;

                    let td_publicationDate = document.createElement("td");
                    td_publicationDate.innerText = data[1].publicationDate;

                    let td_jointAuthorTableId = document.createElement("td");
                    td_jointAuthorTableId.innerText = data[1].jointAuthorTableId;

                    // tr.appendChild(td_bookId);
                    tr.appendChild(td_bookName);
                    tr.appendChild(td_authorId);
                    tr.appendChild(td_ibsn);
                    tr.appendChild(td_publishingCompanyId);
                    tr.appendChild(td_tagId);
                    tr.appendChild(td_quantity);
                    tr.appendChild(td_price);
                    tr.appendChild(td_isBeingBorrowed);
                    tr.appendChild(td_publicationDate);
                    tr.appendChild(td_jointAuthorTableId);

                    resultBox.appendChild(tr);
                }
                if (data[2] != null) {
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    // let td_publishingCompanyId = document.createElement("td");
                    // td_publishingCompanyId.innerText = data[2].publishingCompanyId;

                    let td_publishingCompanyName = document.createElement("td");
                    td_publishingCompanyName.innerText = data[2].publishingCompanyName;

                    let td_publishingCompanyTelephoneNumber = document.createElement("td");
                    td_publishingCompanyTelephoneNumber.innerText = data[2].publishingCompanyTelephoneNumber;

                    let td_publishingCompanyAddress = document.createElement("td");
                    td_publishingCompanyAddress.innerText = data[2].publishingCompanyAddress;

                    // tr.appendChild(td_publishingCompanyId);
                    tr.appendChild(td_publishingCompanyName);
                    tr.appendChild(td_publishingCompanyTelephoneNumber);
                    tr.appendChild(td_publishingCompanyAddress);

                    resultBox.appendChild(tr);
                }
                if (data[3] != null) {
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    // let td_readerId = document.createElement("td");
                    // td_readerId.innerText = data[3].readerId;

                    let td_readerName = document.createElement("td");
                    td_readerName.innerText = data[3].readerName;
                    td_readerName.style.width = "25%";

                    let td_readerSex = document.createElement("td");
                    td_readerSex.innerText = data[3].readerSex;

                    let td_readerAge = document.createElement("td");
                    td_readerAge.innerText = data[3].readerAge;

                    let td_saving = document.createElement("td");
                    td_saving.innerText = data[3].saving;

                    // tr.appendChild(td_readerId);
                    tr.appendChild(td_readerName);
                    tr.appendChild(td_readerSex);
                    tr.appendChild(td_readerAge);
                    tr.appendChild(td_saving);

                    resultBox.appendChild(tr);
                }
                if (data[4] != null) {
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    // let td_tagId = document.createElement("td");
                    // td_tagId.innerText = data[4].tagId;

                    let td_tagName = document.createElement("td");
                    td_tagName.innerText = data[4].tagName;

                    // tr.appendChild(td_tagId);
                    tr.appendChild(td_tagName);

                    resultBox.appendChild(tr);
                }
                if (data[0] == null && data[1] == null && data[2] == null && data[3] == null && data[4] == null) {
                    let p = document.createElement("p");
                    p.className = "search_result_emptyHind";
                    p.innerText = "无对应内容，请确认后重试。";
                    resultBox.appendChild(p);
                }
            } else {
                let p = document.createElement("p");
                p.className = "search_result_emptyHind";
                p.innerText = "无对应内容，请确认后重试。";
                resultBox.appendChild(p);
                console.log(res.message)
            }
        },
        error: () => {
            let p = document.createElement("p");
            p.className = "search_result_emptyHind";
            p.innerText = "无对应内容，请确认后重试。";
            resultBox.appendChild(p);
        }
    });
}
