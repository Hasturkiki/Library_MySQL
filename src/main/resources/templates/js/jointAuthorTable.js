const sortItems = ['jointAuthorTableId', 'bookName', 'authorNames']
const sortTypes = ['none', 'asc', 'desc']

window.onload = function () {
    getJointAuthorTableVoListVo(1, sortItems[0], sortTypes[0])
}

function getJointAuthorTableVoListVo(page, sortItem, sortType) {
    let jointAuthorTableTable = document.getElementsByClassName("jointAuthorTable_table")[0]
    myAxios.get('/jointAuthorTable/getJointAuthorTableVoListVo?page=' + page + '&sortItem=' + sortItem + '&sortType=' + sortType).then(res => {
        if (res.code === 200) {
            let jointAuthorTableVoListVo = res.data
            let jointAuthorTableVoList = jointAuthorTableVoListVo["jointAuthorTableVoList"]
            let pagesNumber = jointAuthorTableVoListVo["pagesNumber"]
            if (jointAuthorTableVoList.length !== 0) {

                jointAuthorTableTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="2" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '            <th onclick="tableSort(this)">共同作者表ID</th>\n' +
                    '            <th onclick="tableSort(this)">书籍名称</th>\n' +
                    '            <th onclick="tableSort(this)">共同作者</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'

                let sortIndex = sortItems.indexOf(sortItem)
                let sortItemTh = $($(jointAuthorTableTable).find('tr')[0]).find('th')[sortIndex]
                switch (sortType) {
                    case 'asc':
                        sortItemTh.innerText = sortItemTh.innerText.split(' ')[0] + ' ▲'
                        break
                    case 'desc':
                        sortItemTh.innerText = sortItemTh.innerText.split(' ')[0] + ' ▼'
                        break
                    default:
                        $($(jointAuthorTableTable).find('tr')[0]).find('th')[0].innerText += ' ▲'
                }

                for (const jointAuthorTableVo of jointAuthorTableVoList) {
                    let jointAuthorTable = jointAuthorTableVo["jointAuthorTable"]

                    let tr = document.createElement("tr")
                    tr.className = "search_result_content"

                    let td_jointAuthorTableId = document.createElement("td")
                    td_jointAuthorTableId.innerText = jointAuthorTable["tableId"]

                    let td_book = document.createElement("td")
                    let td_book_a = document.createElement("a")
                    td_book_a.innerText = jointAuthorTableVo["bookName"]
                    td_book_a.href = '/book/getOne?bookKey=' + jointAuthorTableVo["bookName"]
                    td_book.appendChild(td_book_a)

                    let td_author = document.createElement("td")
                    let authorNames = jointAuthorTableVo["authorNames"].split(';')
                    for(let authorName of authorNames) {
                        let td_authorName_a = document.createElement("a")
                        td_authorName_a.innerText = authorName
                        td_authorName_a.href = '/author/getOne?authorKey=' + authorName
                        td_author.appendChild(td_authorName_a)
                        td_author.appendChild(document.createElement("br"))
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

                    tr.appendChild(td_jointAuthorTableId)
                    tr.appendChild(td_book)
                    tr.appendChild(td_author)
                    tr.appendChild(td_operate)

                    jointAuthorTableTable.appendChild(tr)
                }

                let pageLink_ul = document.getElementsByClassName("pageLink_ul")[0]
                pageLink_ul.innerHTML = ''
                if (pagesNumber > 1) {
                    if (page !== pagesNumber) {
                        let pageLink_right = document.createElement("li")
                        pageLink_right.innerText = '>'
                        pageLink_right.addEventListener('click', function () {
                            getJointAuthorTableVoListVo(Number(page) + 1, sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_right)
                    }

                    if (pagesNumber > 5) {
                        if (page + 2 <= pagesNumber) {
                            let pageLink_right_2 = document.createElement("li")
                            pageLink_right_2.innerText = String(page + 2)
                            pageLink_right_2.addEventListener('click', function () {
                                getJointAuthorTableVoListVo(Number(page) + 2, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_right_2)
                        }
                        if (page + 1 <= pagesNumber) {
                            let pageLink_right_1 = document.createElement("li")
                            pageLink_right_1.innerText = String(page + 1)
                            pageLink_right_1.addEventListener('click', function () {
                                getJointAuthorTableVoListVo(Number(page) + 1, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_right_1)
                        }
                        let pageLink_now = document.createElement("li")
                        pageLink_now.innerText = page
                        pageLink_now.style.color = '#1aa'
                        pageLink_now.style.borderBottom = '1px solid #aa1'
                        pageLink_now.addEventListener('click', function () {
                            getJointAuthorTableVoListVo(Number(page), sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_now)
                        if (page - 1 >= 1) {
                            let pageLink_left_1 = document.createElement("li")
                            pageLink_left_1.innerText = String(page - 1)
                            pageLink_left_1.addEventListener('click', function () {
                                getJointAuthorTableVoListVo(Number(page) - 1, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_left_1)
                        }
                        if (page - 2 >= 1) {
                            let pageLink_left_2 = document.createElement("li")
                            pageLink_left_2.innerText = String(page - 2)
                            pageLink_left_2.addEventListener('click', function () {
                                getJointAuthorTableVoListVo(Number(page) - 2, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_left_2)
                        }
                    } else {
                        while (pagesNumber > 0) {
                            let pageLink_li = document.createElement("li")
                            pageLink_li.innerText = pagesNumber
                            pageLink_li.addEventListener('click', function () {
                                getJointAuthorTableVoListVo(Number(this.innerText), sortItem, sortType)
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
                            getJointAuthorTableVoListVo(Number(page) - 1, sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_left)
                    }
                }
            } else {
                let p = document.createElement("p")
                p.className = "search_result_emptyHind"
                p.innerText = "无对应内容，请确认后重试。"
                jointAuthorTableTable.appendChild(p)
            }
        } else {
            let p = document.createElement("p")
            p.className = "search_result_emptyHind"
            p.innerText = res["msg"].split(';')[0]
            console.log(res["msg"].split(';')[1])
            jointAuthorTableTable.appendChild(p)
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
    getJointAuthorTableVoListVo(1, sortKey, sortType)
}

$('.table_button_update').click({
//    todo update jointAuthorTable
})

$('.table_button_delete').click({
//    todo delete jointAuthorTable
})
