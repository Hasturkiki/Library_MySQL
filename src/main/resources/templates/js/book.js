window.onload = function getAllBook() {
    let bookTable = document.getElementsByClassName("book_table")[0];
    myAxios.get('/book/getAll').then(res => {
        if (res.code === 200) {
            let bookVoList = res.data
            if (bookVoList.length !== 0) {
                bookTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="10" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '            <th>书籍ID</th>\n' +
                    '            <th>书名</th>\n' +
                    '            <th>作者</th>\n' +
                    '            <th>IBSN号</th>\n' +
                    '            <th>出版社</th>\n' +
                    '            <th>标签</th>\n' +
                    '            <th>库存</th>\n' +
                    '            <th>价格</th>\n' +
                    '            <th>借出情况</th>\n' +
                    '            <th>出版日期</th>\n' +
                    '            <th>备注</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'
                for (const bookVo of bookVoList) {
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    const book = bookVo.book

                    let td_bookId = document.createElement("td");
                    td_bookId.innerText = book.bookId;

                    let td_bookName = document.createElement("td");
                    td_bookName.innerText = book.bookName;

                    let td_reader = document.createElement("td");
                    td_reader.innerText = bookVo.authorName
                    td_reader.authorId = book.authorId

                    let td_ibsn = document.createElement("td");
                    td_ibsn.innerText = book.ibsn
                    td_ibsn.style.fontSize = '12pt'

                    let td_publishingCompany = document.createElement("td");
                    td_publishingCompany.innerText = bookVo.publishingCompanyName
                    td_publishingCompany.publishingCompanyId = book.publishingCompanyId

                    let td_tag = document.createElement("td");
                    td_tag.innerText = bookVo.tagName
                    td_tag.tagId = book.tagId

                    let td_quantity = document.createElement("td");
                    td_quantity.innerText = book.quantity

                    let td_price = document.createElement("td");
                    switch (book.price) {
                        case 0:
                            td_price.innerText = 'free'
                            break
                        default:
                            td_price.innerText = '$' + book.price
                    }

                    let td_isBeingBorrowed = document.createElement("td");
                    switch (book.isBeingBorrowed) {
                        case 0:
                            td_isBeingBorrowed.innerText = '无借出'
                            break
                        default:
                            if (book.isBeingBorrowed < book.quantity)
                                td_isBeingBorrowed.innerText = '借出' + book.isBeingBorrowed + '本'
                            else
                                td_isBeingBorrowed.innerText = '全部借出'
                    }

                    let td_publicationDate = document.createElement("td");
                    td_publicationDate.innerText = book.publicationDate
                    td_publicationDate.style.fontSize = '12pt'

                    let td_jointAuthorTable = document.createElement("td");
                    switch (book.jointAuthorTableId) {
                        case 0:
                            td_jointAuthorTable.innerText = '无'
                            break
                        default:
                            td_jointAuthorTable.innerText = '合著'    //todo 共同作者链接
                    }

                    let td_operate = document.createElement("td")
                    let button_update = document.createElement('button')
                    button_update.innerText = '编辑'
                    button_update.className = 'table_button_update'
                    let button_delete = document.createElement('button')
                    button_delete.innerText = '删除'
                    button_delete.className = 'table_button_delete'
                    td_operate.appendChild(button_update)
                    td_operate.appendChild(button_delete)

                    tr.appendChild(td_bookId);
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
                    tr.appendChild(td_operate);

                    bookTable.appendChild(tr)
                }
            } else {
                let p = document.createElement("p");
                p.className = "search_result_emptyHind";
                p.innerText = "无对应内容，请确认后重试。";
                bookTable.appendChild(p);
            }
        } else {
            let p = document.createElement("p");
            p.className = "search_result_emptyHind";
            p.innerText = res.msg.split(';')[0]
            console.log(res.msg.split(';')[1])
            bookTable.appendChild(p);
        }
    })
}

$('.table_button_update').click({
//    todo update book
})

$('.table_button_delete').click({
//    todo delete book
})
