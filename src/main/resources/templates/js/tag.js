window.onload = function () {
    getTagListVoByPage(1)
}

function getTagListVoByPage(page) {
    let tagTable = document.getElementsByClassName("tag_table")[0]
    myAxios.post('/tag/getTagListVoByPage?page=' + page).then(res => {
        if (res.code === 200) {
            let tagListVo = res.data
            let tagList = tagListVo["tagList"]
            let pagesNumber = tagListVo["pagesNumber"]
            if (tagList.length !== 0) {

                tagTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="2" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '            <th>标签ID</th>\n' +
                    '            <th>标签名称</th>\n' +
                    '            <th>作品数</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'

                for (const tag of tagList) {
                    let tr = document.createElement("tr")
                    tr.className = "search_result_content"

                    let td_tagId = document.createElement("td")
                    td_tagId.innerText = tag["tagId"]

                    let td_tagName = document.createElement("td")
                    let td_tagName_a = document.createElement("a")
                    td_tagName_a.innerText = tag["tagName"]
                    td_tagName_a.href = '/tag/getOne?tagId=' + tag["tagId"]
                    td_tagName.appendChild(td_tagName_a)

                    let td_bookNumber = document.createElement("td")
                    let td_bookNumber_a = document.createElement("a")
                    td_bookNumber_a.innerText = tag["bookNumber"] + '作'
                    td_bookNumber_a.href = '/book/selectByTag?tagId=' + tag["tagId"]
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

                    tr.appendChild(td_tagId)
                    tr.appendChild(td_tagName)
                    tr.appendChild(td_bookNumber)
                    tr.appendChild(td_operate)

                    tagTable.appendChild(tr)
                }

                let pageLink_ul = document.getElementsByClassName("pageLink_ul")[0]
                pageLink_ul.innerHTML = ''
                if (pagesNumber > 1) {
                    if (page !== pagesNumber) {
                        let pageLink_right = document.createElement("li")
                        pageLink_right.innerText = '>'
                        pageLink_right.addEventListener('click', function () {
                            getTagListVoByPage(Number(page) + 1)
                        })
                        pageLink_ul.appendChild(pageLink_right)
                    }

                    if (pagesNumber > 5) {
                        if (page + 2 <= pagesNumber) {
                            let pageLink_right_2 = document.createElement("li")
                            pageLink_right_2.innerText = String(page + 2)
                            pageLink_right_2.addEventListener('click', function () {
                                getTagListVoByPage(Number(page) + 2)
                            })
                            pageLink_ul.appendChild(pageLink_right_2)
                        }
                        if (page + 1 <= pagesNumber) {
                            let pageLink_right_1 = document.createElement("li")
                            pageLink_right_1.innerText = String(page + 1)
                            pageLink_right_1.addEventListener('click', function () {
                                getTagListVoByPage(Number(page) + 1)
                            })
                            pageLink_ul.appendChild(pageLink_right_1)
                        }
                        let pageLink_now = document.createElement("li")
                        pageLink_now.innerText = page
                        pageLink_now.style.color = '#1aa'
                        pageLink_now.style.borderBottom = '1px solid #aa1'
                        pageLink_now.addEventListener('click', function () {
                            getTagListVoByPage(Number(page))
                        })
                        pageLink_ul.appendChild(pageLink_now)
                        if (page - 1 >= 1) {
                            let pageLink_left_1 = document.createElement("li")
                            pageLink_left_1.innerText = String(page - 1)
                            pageLink_left_1.addEventListener('click', function () {
                                getTagListVoByPage(Number(page) - 1)
                            })
                            pageLink_ul.appendChild(pageLink_left_1)
                        }
                        if (page - 2 >= 1) {
                            let pageLink_left_2 = document.createElement("li")
                            pageLink_left_2.innerText = String(page - 2)
                            pageLink_left_2.addEventListener('click', function () {
                                getTagListVoByPage(Number(page) - 2)
                            })
                            pageLink_ul.appendChild(pageLink_left_2)
                        }
                    } else {
                        while (pagesNumber > 0) {
                            let pageLink_li = document.createElement("li")
                            pageLink_li.innerText = pagesNumber
                            pageLink_li.addEventListener('click', function () {
                                getTagListVoByPage(Number(this.innerText))
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
                            getTagListVoByPage(Number(page) - 1)
                        })
                        pageLink_ul.appendChild(pageLink_left)
                    }
                }
            } else {
                let p = document.createElement("p")
                p.className = "search_result_emptyHind"
                p.innerText = "无对应内容，请确认后重试。"
                tagTable.appendChild(p)
            }
        } else {
            let p = document.createElement("p")
            p.className = "search_result_emptyHind"
            p.innerText = res["msg"].split(';')[0]
            console.log(res["msg"].split(';')[1])
            tagTable.appendChild(p)
        }
    })
}

$('.table_button_update').click({
//    todo update tag
})

$('.table_button_delete').click({
//    todo delete tag
})
