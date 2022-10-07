const sortItems = ['bookId', 'bookName', 'authorName', 'ibsn', 'publishingCompanyName', 'tagName', 'quantity', 'price', 'isBeingBorrowed', 'publicationDate', 'jointAuthorTableId']
const sortTypes = ['none', 'asc', 'desc']
let select_url

window.onload = function () {
    let url = window.location.href
    let domain = url.split('http://localhost:8080')[1].split('?')[0]
    let key = url.split('=')[1]
    select_url = domain + 'Key?key=' + key
    getBookVoListVo(1, sortItems[0], sortTypes[0])
}

function getBookVoListVo(page, sortItem, sortType) {
    let bookTable = document.getElementsByClassName("book_table")[0]
    myAxios.get(select_url + '&page=' + page + '&sortItem=' + sortItem + '&sortType=' + sortType).then(res => {
        if (res.code === 200) {
            let bookVoListVo = res.data
            let bookVoList = bookVoListVo["bookVoList"]
            let pagesNumber = bookVoListVo["pagesNumber"]
            if (bookVoList.length !== 0) {

                bookTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="10" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '            <th onclick="tableSort(this)">书籍ID</th>\n' +
                    '            <th onclick="tableSort(this)">书名</th>\n' +
                    '            <th onclick="tableSort(this)">作者</th>\n' +
                    '            <th onclick="tableSort(this)">IBSN号</th>\n' +
                    '            <th onclick="tableSort(this)">出版社</th>\n' +
                    '            <th onclick="tableSort(this)">标签</th>\n' +
                    '            <th onclick="tableSort(this)">库存</th>\n' +
                    '            <th onclick="tableSort(this)">价格</th>\n' +
                    '            <th onclick="tableSort(this)">借出情况</th>\n' +
                    '            <th onclick="tableSort(this)">出版日期</th>\n' +
                    '            <th onclick="tableSort(this)">备注</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'

                let sortIndex = sortItems.indexOf(sortItem)
                let sortItemTh = $($(bookTable).find('tr')[0]).find('th')[sortIndex]
                if (bookVoList.length > 1)
                    switch (sortType) {
                        case 'asc':
                            sortItemTh.innerText = sortItemTh.innerText.split(' ')[0] + ' ▲'
                            break
                        case 'desc':
                            sortItemTh.innerText = sortItemTh.innerText.split(' ')[0] + ' ▼'
                            break
                        default:
                            $($(bookTable).find('tr')[0]).find('th')[0].innerText += ' ▲'
                    }

                for (const bookVo of bookVoList) {
                    let book = bookVo["book"]

                    let tr = document.createElement("tr")
                    tr.className = "search_result_content"

                    let td_bookId = document.createElement("td")
                    td_bookId.innerText = book["bookId"]

                    let td_bookName = document.createElement("td")
                    let td_bookName_a = document.createElement("a")
                    td_bookName_a.innerText = book["bookName"]
                    td_bookName_a.href = '/book/getOne?bookKey=' + book["bookId"]
                    td_bookName.appendChild(td_bookName_a)

                    let td_author = document.createElement("td")
                    if (book["jointAuthorTableId"] !== 0) {
                        let td_authorName_a = document.createElement("a")
                        td_authorName_a.innerText = bookVo["authorName"]
                        td_authorName_a.href = '/author/getOne?authorKey=' + book["authorId"]
                        td_author.appendChild(td_authorName_a)
                        let td_jointAuthorTable_a = document.createElement("a")
                        td_jointAuthorTable_a.innerText = ' 等'
                        td_jointAuthorTable_a.href = '/jointAuthorTable/getOne?jointAuthorTableKey=' + book["jointAuthorTableId"]
                        td_author.appendChild(td_jointAuthorTable_a)
                    } else {
                        let td_authorName_a = document.createElement("a")
                        td_authorName_a.innerText = bookVo["authorName"]
                        td_authorName_a.href = '/author/getOne?authorKey=' + book["authorId"]
                        td_author.appendChild(td_authorName_a)
                    }

                    let td_ibsn = document.createElement("td")
                    td_ibsn.innerText = book["ibsn"]
                    td_ibsn.style.width = '13%'

                    let td_publishingCompany = document.createElement("td")
                    let td_publishingCompanyName_a = document.createElement("a")
                    td_publishingCompanyName_a.innerText = bookVo["publishingCompanyName"]
                    td_publishingCompanyName_a.href = '/publishingCompany/getOne?publishingCompanyKey=' + book["publishingCompanyId"]
                    td_publishingCompany.appendChild(td_publishingCompanyName_a)

                    let td_tag = document.createElement("td")
                    let td_tagName_a = document.createElement("a")
                    td_tagName_a.innerText = bookVo["tagName"]
                    td_tagName_a.href = '/tag/getOne?tagKey=' + book["tagId"]
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
                            td_bookBorrowTable_a.innerText = '借出' + book["isBeingBorrowed"] + '本'
                            if (book["isBeingBorrowed"] === book["quantity"])
                                td_isBeingBorrowed.title = '已全部借出'
                    }
                    td_bookBorrowTable_a.href = '/bookBorrowTable/selectByBook?bookId=' + book["bookId"]
                    td_isBeingBorrowed.appendChild(td_bookBorrowTable_a)
                    td_isBeingBorrowed.style.width = '7%'

                    let td_publicationDate = document.createElement("td")
                    td_publicationDate.innerText = book["publicationDate"]
                    td_publicationDate.style.width = '8%'

                    let td_jointAuthorTable = document.createElement("td")
                    let td_jointAuthorTable_a = document.createElement("a")
                    switch (book["jointAuthorTableId"]) {
                        case 0:
                            td_jointAuthorTable_a.innerText = '无'
                            td_jointAuthorTable_a.style.color = '#000'
                            break
                        default:
                            td_jointAuthorTable_a.innerText = '合著'
                            td_jointAuthorTable_a.href = '/jointAuthorTable/getOne?jointAuthorTableKey=' + book["jointAuthorTableId"]
                    }
                    td_jointAuthorTable.appendChild(td_jointAuthorTable_a)
                    td_jointAuthorTable.style.width = '4%'

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

                let pageLink_ul = document.getElementsByClassName("pageLink_ul")[0]
                pageLink_ul.innerHTML = ''
                if (pagesNumber > 1) {
                    if (page !== pagesNumber) {
                        let pageLink_right = document.createElement("li")
                        pageLink_right.innerText = '>'
                        pageLink_right.addEventListener('click', function () {
                            getBookVoListVo(Number(page) + 1, sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_right)
                    }

                    if (pagesNumber > 5) {
                        if (page + 2 <= pagesNumber) {
                            let pageLink_right_2 = document.createElement("li")
                            pageLink_right_2.innerText = String(page + 2)
                            pageLink_right_2.addEventListener('click', function () {
                                getBookVoListVo(Number(page) + 2, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_right_2)
                        }
                        if (page + 1 <= pagesNumber) {
                            let pageLink_right_1 = document.createElement("li")
                            pageLink_right_1.innerText = String(page + 1)
                            pageLink_right_1.addEventListener('click', function () {
                                getBookVoListVo(Number(page) + 1, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_right_1)
                        }
                        let pageLink_now = document.createElement("li")
                        pageLink_now.innerText = page
                        pageLink_now.style.color = '#1aa'
                        pageLink_now.style.borderBottom = '1px solid #aa1'
                        pageLink_now.addEventListener('click', function () {
                            getBookVoListVo(Number(page), sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_now)
                        if (page - 1 >= 1) {
                            let pageLink_left_1 = document.createElement("li")
                            pageLink_left_1.innerText = String(page - 1)
                            pageLink_left_1.addEventListener('click', function () {
                                getBookVoListVo(Number(page) - 1, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_left_1)
                        }
                        if (page - 2 >= 1) {
                            let pageLink_left_2 = document.createElement("li")
                            pageLink_left_2.innerText = String(page - 2)
                            pageLink_left_2.addEventListener('click', function () {
                                getBookVoListVo(Number(page) - 2, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_left_2)
                        }
                    } else {
                        while (pagesNumber > 0) {
                            let pageLink_li = document.createElement("li")
                            pageLink_li.innerText = pagesNumber
                            pageLink_li.addEventListener('click', function () {
                                getBookVoListVo(Number(this.innerText), sortItem, sortType)
                            })
                            if (pagesNumber === page) {
                                pageLink_li.style.color = '#1aa'
                                pageLink_li.style.borderBottom = '1px solid #aa1'
                            }
                            pageLink_ul.appendChild(pageLink_li)
                            pagesNumber--
                        }
                    }

                    if (page !== 1) {
                        let pageLink_left = document.createElement("li")
                        pageLink_left.innerText = '<'
                        pageLink_left.addEventListener('click', function () {
                            getBookVoListVo(Number(page) - 1, sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_left)
                    }
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

function tableSort(sortItem) {
    let sortIndex = $(sortItem).index()
    let sortKey = sortItems[sortIndex]
    let sortTable = sortItem.parentNode.parentNode.parentNode
    let tableContent = $(sortTable).find('.search_result_content')
    // 表格数据只有一条的不予排序
    if (tableContent.length <= 1)
        return

    // 排序方式
    let sortType
    switch (sortItem.innerText.split(' ')[1]) {
        case '▲':
            sortType = 'desc'
            break
        case '▼':
            sortType = 'none'
            break
        default:
            sortType = 'asc'
    }
    getBookVoListVo(1, sortKey, sortType)
}

$('.table_button_update').click({
//    todo update book
})

$('.table_button_delete').click({
//    todo delete book
})
