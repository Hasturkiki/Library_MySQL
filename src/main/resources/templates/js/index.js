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

                    // let td_readerId = document.createElement("td");
                    // td_readerId.innerText = data[0].authorId;

                    let td_readerName = document.createElement("td");
                    td_readerName.innerText = data[0].authorName

                    let td_readerSex = document.createElement("td");
                    switch (data[0].authorSex) {
                        case 0:
                            td_readerSex.innerText = '女'
                            break
                        case 1:
                            td_readerSex.innerText = '男'
                            break
                        case 2:
                            td_readerSex.innerText = '保密'
                            break
                        default:
                            td_readerSex.innerText = '？'
                    }

                    let td_readerAge = document.createElement("td");
                    td_readerAge.innerText = data[0].authorAge + '岁'

                    let td_bookNumber = document.createElement("td");
                    td_bookNumber.innerText = data[0].bookNumber + '作'  //todo 作品链接

                    // tr.appendChild(td_readerId);
                    tr.appendChild(td_readerName)
                    tr.appendChild(td_readerSex)
                    tr.appendChild(td_readerAge)
                    tr.appendChild(td_bookNumber)

                    resultBox.appendChild(tr)
                }
                if (data[1] != null) {
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    // let td_bookId = document.createElement("td");
                    // td_bookId.innerText = data[1].bookId;

                    let td_bookName = document.createElement("td");
                    td_bookName.innerText = data[1].book.bookName

                    let td_reader = document.createElement("td");
                    td_reader.innerText = data[1].authorName
                    td_reader.authorId = data[1].book.authorId

                    let td_ibsn = document.createElement("td");
                    td_ibsn.innerText = data[1].book.ibsn
                    td_ibsn.style.fontSize = '12pt'

                    let td_publishingCompany = document.createElement("td");
                    td_publishingCompany.innerText = data[1].publishingCompanyName
                    td_publishingCompany.publishingCompanyId = data[1].book.publishingCompanyId

                    let td_tag = document.createElement("td");
                    td_tag.innerText = data[1].tagName
                    td_tag.tagId = data[1].book.tagId

                    let td_quantity = document.createElement("td");
                    td_quantity.innerText = data[1].book.quantity

                    let td_price = document.createElement("td");
                    switch (data[1].book.price) {
                        case 0:
                            td_price.innerText = 'free'
                            break
                        default:
                            td_price.innerText = '$' + data[1].book.price
                    }

                    let td_isBeingBorrowed = document.createElement("td");
                    switch (data[1].book.isBeingBorrowed) {
                        case 0:
                            td_isBeingBorrowed.innerText = '无借出'
                            break
                        default:
                            if (data[1].book.isBeingBorrowed < data[1].book.quantity)
                                td_isBeingBorrowed.innerText = '借出' + data[1].book.isBeingBorrowed + '本'
                            else
                                td_isBeingBorrowed.innerText = '全部借出'
                    }

                    let td_publicationDate = document.createElement("td");
                    td_publicationDate.innerText = data[1].book.publicationDate
                    td_publicationDate.style.fontSize = '12pt'

                    let td_jointAuthorTable = document.createElement("td");
                    switch (data[1].book.jointAuthorTableId) {
                        case 0:
                            td_jointAuthorTable.innerText = ''
                            break
                        default:
                            td_jointAuthorTable.innerText = '合著'    //todo 共同作者链接
                    }

                    // tr.appendChild(td_bookId);
                    tr.appendChild(td_bookName);
                    tr.appendChild(td_reader);
                    tr.appendChild(td_ibsn);
                    tr.appendChild(td_publishingCompany);
                    tr.appendChild(td_tag);
                    tr.appendChild(td_quantity);
                    tr.appendChild(td_price);
                    tr.appendChild(td_isBeingBorrowed);
                    tr.appendChild(td_publicationDate);
                    tr.appendChild(td_jointAuthorTable);

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
                    switch (data[3].readerSex) {
                        case 0:
                            td_readerSex.innerText = '女';
                            break
                        case 1:
                            td_readerSex.innerText = '男';
                            break
                        case 2:
                            td_readerSex.innerText = '保密';
                            break
                        default:
                            td_readerSex.innerText = '？'
                    }

                    let td_readerAge = document.createElement("td");
                    td_readerAge.innerText = data[3].readerAge + '岁';

                    let td_saving = document.createElement("td");
                    td_saving.innerText = '$' + data[3].saving;

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
