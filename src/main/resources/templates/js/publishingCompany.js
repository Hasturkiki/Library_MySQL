window.onload = function () {
    getPublishingCompanyListVoByPage(1)
}

function getPublishingCompanyListVoByPage(page) {
    let publishingCompanyTable = document.getElementsByClassName("publishingCompany_table")[0]
    myAxios.post('/publishingCompany/getPublishingCompanyListVoByPage?page=' + page).then(res => {
        if (res.code === 200) {
            let publishingCompanyListVo = res.data
            let publishingCompanyList = publishingCompanyListVo["publishingCompanyList"]
            let pagesNumber = publishingCompanyListVo["pagesNumber"]
            if (publishingCompanyList.length !== 0) {

                publishingCompanyTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="4" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '            <th>出版社ID</th>\n' +
                    '            <th>出版社名称</th>\n' +
                    '            <th>联系方式</th>\n' +
                    '            <th>通信地址</th>\n' +
                    '            <th>作品数</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'

                for (const publishingCompany of publishingCompanyList) {
                    let tr = document.createElement("tr")
                    tr.className = "search_result_content"

                    let td_publishingCompanyId = document.createElement("td")
                    td_publishingCompanyId.innerText = publishingCompany["publishingCompanyId"]

                    let td_publishingCompanyName = document.createElement("td")
                    let td_publishingCompanyName_a = document.createElement("a")
                    td_publishingCompanyName_a.innerText = publishingCompany["publishingCompanyName"]
                    td_publishingCompanyName_a.href = '/publishingCompany/getOne?publishingCompanyId=' + publishingCompany["publishingCompanyId"]
                    td_publishingCompanyName.appendChild(td_publishingCompanyName_a)

                    let td_publishingCompanyTelephoneNumber = document.createElement("td")
                    td_publishingCompanyTelephoneNumber.innerText = publishingCompany["publishingCompanyTelephoneNumber"]

                    let td_publishingCompanyAddress = document.createElement("td")
                    td_publishingCompanyAddress.innerText = publishingCompany["publishingCompanyAddress"]

                    let td_bookNumber = document.createElement("td")
                    let td_bookNumber_a = document.createElement("a")
                    td_bookNumber_a.innerText = publishingCompany["bookNumber"] + '作'
                    td_bookNumber_a.href = '/book/selectByPublishingCompany?publishingCompanyId=' + publishingCompany["publishingCompanyId"]
                    td_bookNumber.appendChild(td_bookNumber_a)

                    let td_operate = document.createElement("td")
                    let button_update = document.createElement('button')
                    button_update.innerText = '编辑'
                    button_update.className = 'table_button_update'
                    let button_delete = document.createElement('button')
                    button_delete.innerText = '删除'
                    button_delete.className = 'table_button_delete'
                    td_operate.appendChild(button_update)
                    td_operate.appendChild(button_delete)

                    tr.appendChild(td_publishingCompanyId)
                    tr.appendChild(td_publishingCompanyName)
                    tr.appendChild(td_publishingCompanyTelephoneNumber)
                    tr.appendChild(td_publishingCompanyAddress)
                    tr.appendChild(td_bookNumber)
                    tr.appendChild(td_operate)

                    publishingCompanyTable.appendChild(tr)
                }

                let pageLink_ul = document.getElementsByClassName("pageLink_ul")[0]
                pageLink_ul.innerHTML = ''
                if (pagesNumber > 1) {
                    if (page !== pagesNumber) {
                        let pageLink_right = document.createElement("li")
                        pageLink_right.innerText = '>'
                        pageLink_right.addEventListener('click', function () {
                            getPublishingCompanyListVoByPage(Number(page) + 1)
                        })
                        pageLink_ul.appendChild(pageLink_right)
                    }

                    if (pagesNumber > 5) {
                        if (page + 2 <= pagesNumber) {
                            let pageLink_right_2 = document.createElement("li")
                            pageLink_right_2.innerText = String(page + 2)
                            pageLink_right_2.addEventListener('click', function () {
                                getPublishingCompanyListVoByPage(Number(page) + 2)
                            })
                            pageLink_ul.appendChild(pageLink_right_2)
                        }
                        if (page + 1 <= pagesNumber) {
                            let pageLink_right_1 = document.createElement("li")
                            pageLink_right_1.innerText = String(page + 1)
                            pageLink_right_1.addEventListener('click', function () {
                                getPublishingCompanyListVoByPage(Number(page) + 1)
                            })
                            pageLink_ul.appendChild(pageLink_right_1)
                        }
                        let pageLink_now = document.createElement("li")
                        pageLink_now.innerText = page
                        pageLink_now.style.color = '#1aa'
                        pageLink_now.style.borderBottom = '1px solid #aa1'
                        pageLink_now.addEventListener('click', function () {
                            getPublishingCompanyListVoByPage(Number(page))
                        })
                        pageLink_ul.appendChild(pageLink_now)
                        if (page - 1 >= 1) {
                            let pageLink_left_1 = document.createElement("li")
                            pageLink_left_1.innerText = String(page - 1)
                            pageLink_left_1.addEventListener('click', function () {
                                getPublishingCompanyListVoByPage(Number(page) - 1)
                            })
                            pageLink_ul.appendChild(pageLink_left_1)
                        }
                        if (page - 2 >= 1) {
                            let pageLink_left_2 = document.createElement("li")
                            pageLink_left_2.innerText = String(page - 2)
                            pageLink_left_2.addEventListener('click', function () {
                                getPublishingCompanyListVoByPage(Number(page) - 2)
                            })
                            pageLink_ul.appendChild(pageLink_left_2)
                        }
                    } else {
                        while (pagesNumber > 0) {
                            let pageLink_li = document.createElement("li")
                            pageLink_li.innerText = pagesNumber
                            pageLink_li.addEventListener('click', function () {
                                getPublishingCompanyListVoByPage(Number(this.innerText))
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
                            getPublishingCompanyListVoByPage(Number(page) - 1)
                        })
                        pageLink_ul.appendChild(pageLink_left)
                    }
                }
            } else {
                let p = document.createElement("p")
                p.className = "search_result_emptyHind"
                p.innerText = "无对应内容，请确认后重试。"
                publishingCompanyTable.appendChild(p)
            }
        } else {
            let p = document.createElement("p")
            p.className = "search_result_emptyHind"
            p.innerText = res["msg"].split(';')[0]
            console.log(res["msg"].split(';')[1])
            publishingCompanyTable.appendChild(p)
        }
    })
}

$('.table_button_update').click({
//    todo update publishingCompany
})

$('.table_button_delete').click({
//    todo delete publishingCompany
})
