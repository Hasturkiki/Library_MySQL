window.onload = function () {
    getReaderListVoByPage(1)
}

function getReaderListVoByPage(page) {
    let readerTable = document.getElementsByClassName("reader_table")[0]
    myAxios.post('/reader/getReaderListVoByPage?page=' + page).then(res => {
        if (res.code === 200) {
            let readerListVo = res.data
            let readerList = readerListVo["readerList"]
            let pagesNumber = readerListVo["pagesNumber"]
            if (readerList.length !== 0) {

                readerTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="4" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '            <th>读者ID</th>\n' +
                    '            <th>用户名</th>\n' +
                    '            <th>性别</th>\n' +
                    '            <th>年龄</th>\n' +
                    '            <th>余额</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'

                for (const reader of readerList) {
                    let tr = document.createElement("tr")
                    tr.className = "search_result_content"

                    let td_readerId = document.createElement("td")
                    td_readerId.innerText = reader["readerId"]

                    let td_readerName = document.createElement("td")
                    let td_readerName_a = document.createElement("a")
                    td_readerName_a.innerText = reader["readerName"]
                    td_readerName_a.href = '/reader/getOne?readerId=' + reader["readerId"]
                    td_readerName.appendChild(td_readerName_a)

                    let td_readerSex = document.createElement("td")
                    switch (reader["readerSex"]) {
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

                    let td_readerAge = document.createElement("td")
                    td_readerAge.innerText = reader["readerAge"] + '岁'

                    let td_saving = document.createElement("td")
                    td_saving.innerText = '$' + reader["saving"]

                    let td_operate = document.createElement("td")
                    let button_update = document.createElement('button')
                    button_update.innerText = '编辑'
                    button_update.className = 'table_button_update'
                    let button_delete = document.createElement('button')
                    button_delete.innerText = '删除'
                    button_delete.className = 'table_button_delete'
                    td_operate.appendChild(button_update)
                    td_operate.appendChild(button_delete)

                    tr.appendChild(td_readerId)
                    tr.appendChild(td_readerName)
                    tr.appendChild(td_readerSex)
                    tr.appendChild(td_readerAge)
                    tr.appendChild(td_saving)
                    tr.appendChild(td_operate)

                    readerTable.appendChild(tr)
                }

                let pageLink_ul = document.getElementsByClassName("pageLink_ul")[0]
                pageLink_ul.innerHTML = ''
                if (pagesNumber > 1) {
                    if (page !== pagesNumber) {
                        let pageLink_right = document.createElement("li")
                        pageLink_right.innerText = '>'
                        pageLink_right.addEventListener('click', function () {
                            getReaderListVoByPage(Number(page) + 1)
                        })
                        pageLink_ul.appendChild(pageLink_right)
                    }

                    if (pagesNumber > 5) {
                        if (page + 2 <= pagesNumber) {
                            let pageLink_right_2 = document.createElement("li")
                            pageLink_right_2.innerText = String(page + 2)
                            pageLink_right_2.addEventListener('click', function () {
                                getReaderListVoByPage(Number(page) + 2)
                            })
                            pageLink_ul.appendChild(pageLink_right_2)
                        }
                        if (page + 1 <= pagesNumber) {
                            let pageLink_right_1 = document.createElement("li")
                            pageLink_right_1.innerText = String(page + 1)
                            pageLink_right_1.addEventListener('click', function () {
                                getReaderListVoByPage(Number(page) + 1)
                            })
                            pageLink_ul.appendChild(pageLink_right_1)
                        }
                        let pageLink_now = document.createElement("li")
                        pageLink_now.innerText = page
                        pageLink_now.style.color = '#1aa'
                        pageLink_now.style.borderBottom = '1px solid #aa1'
                        pageLink_now.addEventListener('click', function () {
                            getReaderListVoByPage(Number(page))
                        })
                        pageLink_ul.appendChild(pageLink_now)
                        if (page - 1 >= 1) {
                            let pageLink_left_1 = document.createElement("li")
                            pageLink_left_1.innerText = String(page - 1)
                            pageLink_left_1.addEventListener('click', function () {
                                getReaderListVoByPage(Number(page) - 1)
                            })
                            pageLink_ul.appendChild(pageLink_left_1)
                        }
                        if (page - 2 >= 1) {
                            let pageLink_left_2 = document.createElement("li")
                            pageLink_left_2.innerText = String(page - 2)
                            pageLink_left_2.addEventListener('click', function () {
                                getReaderListVoByPage(Number(page) - 2)
                            })
                            pageLink_ul.appendChild(pageLink_left_2)
                        }
                    } else {
                        while (pagesNumber > 0) {
                            let pageLink_li = document.createElement("li")
                            pageLink_li.innerText = pagesNumber
                            pageLink_li.addEventListener('click', function () {
                                getReaderListVoByPage(Number(this.innerText))
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
                            getReaderListVoByPage(Number(page) - 1)
                        })
                        pageLink_ul.appendChild(pageLink_left)
                    }
                }
            } else {
                let p = document.createElement("p")
                p.className = "search_result_emptyHind"
                p.innerText = "无对应内容，请确认后重试。"
                readerTable.appendChild(p)
            }
        } else {
            let p = document.createElement("p")
            p.className = "search_result_emptyHind"
            p.innerText = res["msg"].split(';')[0]
            console.log(res["msg"].split(';')[1])
            readerTable.appendChild(p)
        }
    })
}

$('.table_button_update').click({
//    todo update reader
})

$('.table_button_delete').click({
//    todo delete reader
})
