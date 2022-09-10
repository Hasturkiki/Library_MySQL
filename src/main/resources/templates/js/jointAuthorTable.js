window.onload = function () {
    getJointAuthorTableVoListVoByPage(1)
}

function getJointAuthorTableVoListVoByPage(page) {
    let jointAuthorTableTable = document.getElementsByClassName("jointAuthorTable_table")[0]
    myAxios.post('/jointAuthorTable/getJointAuthorTableVoListVoByPage?page=' + page).then(res => {
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
                    '            <th>共同作者表ID</th>\n' +
                    '            <th>书籍名称</th>\n' +
                    '            <th>共同作者</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'

                for (const jointAuthorTableVo of jointAuthorTableVoList) {
                    let jointAuthorTable = jointAuthorTableVo["jointAuthorTable"]

                    let tr = document.createElement("tr")
                    tr.className = "search_result_content"

                    let td_jointAuthorTableId = document.createElement("td")
                    td_jointAuthorTableId.innerText = jointAuthorTable["tableId"]

                    let td_book = document.createElement("td")
                    let td_book_a = document.createElement("a")
                    td_book_a.innerText = jointAuthorTableVo["bookName"]
                    td_book_a.href = '/book/getOne?bookId=' + jointAuthorTableVo["bookId"]
                    td_book.appendChild(td_book_a)

                    let td_author = document.createElement("td")
                    let authorNames = jointAuthorTableVo["authorNames"].split(';')
                    for(let authorName of authorNames) {
                        let td_authorName_a = document.createElement("a")
                        td_authorName_a.innerText = authorName
                        td_authorName_a.href = '/author/getOne?authorName=' + authorName
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
                            getJointAuthorTableVoListVoByPage(Number(page) + 1)
                        })
                        pageLink_ul.appendChild(pageLink_right)
                    }

                    if (pagesNumber > 5) {
                        if (page + 2 <= pagesNumber) {
                            let pageLink_right_2 = document.createElement("li")
                            pageLink_right_2.innerText = String(page + 2)
                            pageLink_right_2.addEventListener('click', function () {
                                getJointAuthorTableVoListVoByPage(Number(page) + 2)
                            })
                            pageLink_ul.appendChild(pageLink_right_2)
                        }
                        if (page + 1 <= pagesNumber) {
                            let pageLink_right_1 = document.createElement("li")
                            pageLink_right_1.innerText = String(page + 1)
                            pageLink_right_1.addEventListener('click', function () {
                                getJointAuthorTableVoListVoByPage(Number(page) + 1)
                            })
                            pageLink_ul.appendChild(pageLink_right_1)
                        }
                        let pageLink_now = document.createElement("li")
                        pageLink_now.innerText = page
                        pageLink_now.style.color = '#1aa'
                        pageLink_now.style.borderBottom = '1px solid #aa1'
                        pageLink_now.addEventListener('click', function () {
                            getJointAuthorTableVoListVoByPage(Number(page))
                        })
                        pageLink_ul.appendChild(pageLink_now)
                        if (page - 1 >= 1) {
                            let pageLink_left_1 = document.createElement("li")
                            pageLink_left_1.innerText = String(page - 1)
                            pageLink_left_1.addEventListener('click', function () {
                                getJointAuthorTableVoListVoByPage(Number(page) - 1)
                            })
                            pageLink_ul.appendChild(pageLink_left_1)
                        }
                        if (page - 2 >= 1) {
                            let pageLink_left_2 = document.createElement("li")
                            pageLink_left_2.innerText = String(page - 2)
                            pageLink_left_2.addEventListener('click', function () {
                                getJointAuthorTableVoListVoByPage(Number(page) - 2)
                            })
                            pageLink_ul.appendChild(pageLink_left_2)
                        }
                    } else {
                        while (pagesNumber > 0) {
                            let pageLink_li = document.createElement("li")
                            pageLink_li.innerText = pagesNumber
                            pageLink_li.addEventListener('click', function () {
                                getJointAuthorTableVoListVoByPage(Number(this.innerText))
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
                            getJointAuthorTableVoListVoByPage(Number(page) - 1)
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

$('.table_button_update').click({
//    todo update jointAuthorTable
})

$('.table_button_delete').click({
//    todo delete jointAuthorTable
})
