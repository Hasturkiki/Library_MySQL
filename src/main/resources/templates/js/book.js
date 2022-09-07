window.onload = function getAllBook() {
    let bookTable = document.getElementsByClassName("book_table")[0]
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
                    let book = bookVo["book"]

                    let tr = document.createElement("tr")
                    tr.className = "search_result_content"

                    let td_bookId = document.createElement("td")
                    td_bookId.innerText = book["bookId"]

                    let td_bookName = document.createElement("td")
                    let td_bookName_a = document.createElement("a")
                    td_bookName_a.innerText = book["bookName"]
                    td_bookName_a.href = '/book/getOne?bookId=' + book["bookId"]
                    td_bookName.appendChild(td_bookName_a)

                    let td_author = document.createElement("td")
                    if (book["jointAuthorTableId"] !== 0) {
                        let td_authorName_a = document.createElement("a")
                        td_authorName_a.innerText = bookVo["authorName"]
                        td_authorName_a.href = '/author/getOne?authorId=' + book["authorId"]
                        td_author.appendChild(td_authorName_a)
                        let td_jointAuthorTable_a = document.createElement("a")
                        td_jointAuthorTable_a.innerText = ' 等'
                        td_jointAuthorTable_a.href = '/jointAuthorTableVo/getOne?jointAuthorTableId=' + book["jointAuthorTableId"]
                        td_author.appendChild(td_jointAuthorTable_a)
                    } else {
                        let td_authorName_a = document.createElement("a")
                        td_authorName_a.innerText = bookVo["authorName"]
                        td_authorName_a.href = '/author/getOne?authorId=' + book["authorId"]
                        td_author.appendChild(td_authorName_a)
                    }

                    let td_ibsn = document.createElement("td")
                    td_ibsn.innerText = book["ibsn"]

                    let td_publishingCompany = document.createElement("td")
                    let td_publishingCompanyName_a = document.createElement("a")
                    td_publishingCompanyName_a.innerText = bookVo["publishingCompanyName"]
                    td_publishingCompanyName_a.href = '/publishingCompany/getOne?publishingCompanyId=' + book["publishingCompanyId"]
                    td_publishingCompany.appendChild(td_publishingCompanyName_a)

                    let td_tag = document.createElement("td")
                    let td_tagName_a = document.createElement("a")
                    td_tagName_a.innerText = bookVo["tagName"]
                    td_tagName_a.href = '/tag/getOne?tagId=' + book["tagId"]
                    td_tag.appendChild(td_tagName_a)

                    let td_quantity = document.createElement("td")
                    td_quantity.innerText = book["quantity"]

                    let td_price = document.createElement("td")
                    switch (book["price"]) {
                        case 0:
                            td_price.innerText = 'free'
                            break
                        default:
                            td_price.innerText = '$' + book["price"]
                    }

                    let td_isBeingBorrowed = document.createElement("td")
                    let td_bookBorrowTable_a = document.createElement("a")
                    switch (book["isBeingBorrowed"]) {
                        case 0:
                            td_bookBorrowTable_a.innerText = '无借出'
                            break
                        default:
                            if (book["isBeingBorrowed"] < book["quantity"])
                                td_bookBorrowTable_a.innerText = '借出' + book["isBeingBorrowed"] + '本'
                            else
                                td_bookBorrowTable_a.innerText = '全部借出'
                    }
                    td_bookBorrowTable_a.href = '/bookBorrowTable/selectByBook?bookId=' + book["bookId"]
                    td_isBeingBorrowed.appendChild(td_bookBorrowTable_a)

                    let td_publicationDate = document.createElement("td")
                    td_publicationDate.innerText = book["publicationDate"]

                    let td_jointAuthorTable = document.createElement("td")
                    let td_jointAuthorTable_a = document.createElement("a")
                    switch (book["jointAuthorTableId"]) {
                        case 0:
                            td_jointAuthorTable_a.innerText = '无'
                            td_jointAuthorTable_a.style.color = '#000'
                            break
                        default:
                            td_jointAuthorTable_a.innerText = '合著'
                            td_jointAuthorTable_a.href = '/jointAuthorTableVo/getOne?jointAuthorTableId=' + book["jointAuthorTableId"]
                    }
                    td_jointAuthorTable.appendChild(td_jointAuthorTable_a)

                    let td_operate = document.createElement("td")
                    let button_update = document.createElement('button')
                    button_update.innerText = '编辑'
                    button_update.className = 'table_button_update'
                    let button_delete = document.createElement('button')
                    button_delete.innerText = '删除'
                    button_delete.className = 'table_button_delete'
                    td_operate.appendChild(button_update)
                    td_operate.appendChild(button_delete)
                    td_operate.style.width = '9%'

                    tr.appendChild(td_bookId)
                    tr.appendChild(td_bookName)
                    tr.appendChild(td_author)
                    tr.appendChild(td_ibsn)
                    tr.appendChild(td_publishingCompany)
                    tr.appendChild(td_tag)
                    tr.appendChild(td_quantity)
                    tr.appendChild(td_price)
                    tr.appendChild(td_isBeingBorrowed)
                    tr.appendChild(td_publicationDate)
                    tr.appendChild(td_jointAuthorTable)
                    tr.appendChild(td_operate)

                    bookTable.appendChild(tr)
                }
            } else {
                let p = document.createElement("p")
                p.className = "search_result_emptyHind"
                p.innerText = "无对应内容，请确认后重试。"
                bookTable.appendChild(p)
            }
        } else {
            let p = document.createElement("p")
            p.className = "search_result_emptyHind"
            p.innerText = res["msg"].split(';')[0]
            console.log(res["msg"].split(';')[1])
            bookTable.appendChild(p)
        }
    })
}

$('.table_button_update').click({
//    todo update book
})

$('.table_button_delete').click({
//    todo delete book
})
