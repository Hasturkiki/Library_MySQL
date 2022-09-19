const sortItems = ['booksBorrowTableId', 'readerName', 'bookName', 'isBorrowing', 'borrowTime', 'backTime']
const sortTypes = ['none', 'asc', 'desc']

window.onload = function () {
    getBookBorrowTableVoListVo(1, sortItems[0], sortTypes[0])
}

function getBookBorrowTableVoListVo(page, sortItem, sortType) {
    let bookBorrowTableTable = document.getElementsByClassName("bookBorrowTable_table")[0]
    myAxios.post('/bookBorrowTable/getBookBorrowTableVoListVo?page=' + page + '&sortItem=' + sortItem + '&sortType=' + sortType).then(res => {
        if (res.code === 200) {
            let bookBorrowTableVoListVo = res.data
            let bookBorrowTableVoList = bookBorrowTableVoListVo["bookBorrowTableVoList"]
            let pagesNumber = bookBorrowTableVoListVo["pagesNumber"]
            if (bookBorrowTableVoList.length !== 0) {

                bookBorrowTableTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="5" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '            <th onclick="tableSort(this)">借书表ID</th>\n' +
                    '            <th onclick="tableSort(this)">借书者</th>\n' +
                    '            <th onclick="tableSort(this)">所借书</th>\n' +
                    '            <th onclick="tableSort(this)">借书状态</th>\n' +
                    '            <th onclick="tableSort(this)">外借时间</th>\n' +
                    '            <th onclick="tableSort(this)">归还时间</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'

                let sortIndex = sortItems.indexOf(sortItem)
                let sortItemTh = $($(bookBorrowTableTable).find('tr')[0]).find('th')[sortIndex]
                switch (sortType) {
                    case 'asc':
                        sortItemTh.innerText = sortItemTh.innerText.split(' ')[0] + ' ▲'
                        break
                    case 'desc':
                        sortItemTh.innerText = sortItemTh.innerText.split(' ')[0] + ' ▼'
                        break
                    default:
                        $($(bookBorrowTableTable).find('tr')[0]).find('th')[0].innerText += ' ▲'
                }

                for (const bookBorrowTableVo of bookBorrowTableVoList) {
                    let bookBorrowTable = bookBorrowTableVo["bookBorrowTable"]

                    let tr = document.createElement("tr")
                    tr.className = "search_result_content"

                    let td_bookBorrowTableId = document.createElement("td")
                    td_bookBorrowTableId.innerText = bookBorrowTable["booksBorrowTableId"]

                    let td_reader = document.createElement("td")
                    let td_reader_a = document.createElement("a")
                    td_reader_a.innerText = bookBorrowTableVo["readerName"]
                    td_reader_a.href = '/reader/getOne?readerId=' + bookBorrowTable["readerId"]
                    td_reader.appendChild(td_reader_a)

                    let td_book = document.createElement("td")
                    let td_book_a = document.createElement("a")
                    td_book_a.innerText = bookBorrowTableVo["bookName"]
                    td_book_a.href = '/book/getOne?bookId=' + bookBorrowTable["bookId"]
                    td_book.appendChild(td_book_a)

                    let td_isBorrowing = document.createElement("td")
                    if (bookBorrowTable["isBorrowing"] === 1) {
                        td_isBorrowing.innerText = '借用中'
                    } else {
                        td_isBorrowing.innerText = '已归还'
                    }

                    let td_borrowTime = document.createElement("td")
                    td_borrowTime.innerText = bookBorrowTable["borrowTime"].split('T')[0] + ' ' + bookBorrowTable["borrowTime"].split('T')[1]

                    let td_backTime = document.createElement("td")
                    if (bookBorrowTable["backTime"] === null) {
                        td_backTime.innerText = '无'
                    } else {
                        td_backTime.innerText = bookBorrowTable["backTime"].split('T')[0] + ' ' + bookBorrowTable["backTime"].split('T')[1]
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

                    tr.appendChild(td_bookBorrowTableId)
                    tr.appendChild(td_reader)
                    tr.appendChild(td_book)
                    tr.appendChild(td_isBorrowing)
                    tr.appendChild(td_borrowTime)
                    tr.appendChild(td_backTime)
                    tr.appendChild(td_operate)

                    bookBorrowTableTable.appendChild(tr)
                }

                let pageLink_ul = document.getElementsByClassName("pageLink_ul")[0]
                pageLink_ul.innerHTML = ''
                if (pagesNumber > 1) {
                    if (page !== pagesNumber) {
                        let pageLink_right = document.createElement("li")
                        pageLink_right.innerText = '>'
                        pageLink_right.addEventListener('click', function () {
                            getBookBorrowTableVoListVo(Number(page) + 1, sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_right)
                    }

                    if (pagesNumber > 5) {
                        if (page + 2 <= pagesNumber) {
                            let pageLink_right_2 = document.createElement("li")
                            pageLink_right_2.innerText = String(page + 2)
                            pageLink_right_2.addEventListener('click', function () {
                                getBookBorrowTableVoListVo(Number(page) + 2, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_right_2)
                        }
                        if (page + 1 <= pagesNumber) {
                            let pageLink_right_1 = document.createElement("li")
                            pageLink_right_1.innerText = String(page + 1)
                            pageLink_right_1.addEventListener('click', function () {
                                getBookBorrowTableVoListVo(Number(page) + 1, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_right_1)
                        }
                        let pageLink_now = document.createElement("li")
                        pageLink_now.innerText = page
                        pageLink_now.style.color = '#1aa'
                        pageLink_now.style.borderBottom = '1px solid #aa1'
                        pageLink_now.addEventListener('click', function () {
                            getBookBorrowTableVoListVo(Number(page), sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_now)
                        if (page - 1 >= 1) {
                            let pageLink_left_1 = document.createElement("li")
                            pageLink_left_1.innerText = String(page - 1)
                            pageLink_left_1.addEventListener('click', function () {
                                getBookBorrowTableVoListVo(Number(page) - 1, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_left_1)
                        }
                        if (page - 2 >= 1) {
                            let pageLink_left_2 = document.createElement("li")
                            pageLink_left_2.innerText = String(page - 2)
                            pageLink_left_2.addEventListener('click', function () {
                                getBookBorrowTableVoListVo(Number(page) - 2, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_left_2)
                        }
                    } else {
                        while (pagesNumber > 0) {
                            let pageLink_li = document.createElement("li")
                            pageLink_li.innerText = pagesNumber
                            pageLink_li.addEventListener('click', function () {
                                getBookBorrowTableVoListVo(Number(this.innerText), sortItem, sortType)
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
                            getBookBorrowTableVoListVo(Number(page) - 1, sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_left)
                    }
                }
            } else {
                let p = document.createElement("p")
                p.className = "search_result_emptyHind"
                p.innerText = "无对应内容，请确认后重试。"
                bookBorrowTableTable.appendChild(p)
            }
        } else {
            let p = document.createElement("p")
            p.className = "search_result_emptyHind"
            p.innerText = res["msg"].split(';')[0]
            console.log(res["msg"].split(';')[1])
            bookBorrowTableTable.appendChild(p)
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
    getBookBorrowTableVoListVo(1, sortKey, sortType)
}

$('.table_button_update').click({
//    todo update bookBorrowTable
})

$('.table_button_delete').click({
//    todo delete bookBorrowTable
})
