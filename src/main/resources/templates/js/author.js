const sortItems = ['authorId', 'authorName', 'authorSex', 'authorAge', 'bookNumber']
const sortTypes = ['none', 'asc', 'desc']

let nowPage = 1
let nowSortItem = sortItems[0]
let nowSortType = sortTypes[0]
let pagesNumber = 0

$(document).ready(function () {
    getAuthorListVo(nowPage, nowSortItem, nowSortType)
})

function getAuthorListVo(page, sortItem, sortType) {
    let authorTable = document.getElementsByClassName("author_table")[0]
    $('.itemEdit').remove()
    myAxios.get('/author/getAuthorListVo?page=' + page + '&sortItem=' + sortItem + '&sortType=' + sortType).then(res => {
        if (res.code === 200) {
            let authorListVo = res.data
            let authorList = authorListVo["authorList"]
            pagesNumber = authorListVo["pagesNumber"]
            nowPage = page
            nowSortItem = sortItem
            nowSortType = sortType

            if (authorList.length !== 0) {

                authorTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="4" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '           <th onclick="tableSort(this)">作者ID</th>\n' +
                    '           <th onclick="tableSort(this)">作者名</th>\n' +
                    '           <th onclick="tableSort(this)">性别</th>\n' +
                    '           <th onclick="tableSort(this)">年龄</th>\n' +
                    '           <th onclick="tableSort(this)">作品数</th>\n' +
                    '           <th>操作</th>\n' +
                    '        </tr>'

                let sortIndex = sortItems.indexOf(sortItem)
                let sortItemTh = $($(authorTable).find('tr')[0]).find('th')[sortIndex]
                switch (sortType) {
                    case 'asc':
                        sortItemTh.innerText = sortItemTh.innerText.split(' ')[0] + ' ▲'
                        break
                    case 'desc':
                        sortItemTh.innerText = sortItemTh.innerText.split(' ')[0] + ' ▼'
                        break
                    default:
                        $($(authorTable).find('tr')[0]).find('th')[0].innerText += ' ▲'
                }

                for (const author of authorList) {
                    let tr = document.createElement("tr")
                    tr.className = "search_result_content"
                    $(tr).data('author', author)

                    let td_authorId = document.createElement("td")
                    td_authorId.innerText = author["authorId"]

                    let td_authorName = document.createElement("td")
                    let td_authorName_a = document.createElement("a")
                    td_authorName_a.innerText = author["authorName"]
                    td_authorName_a.href = '/author/getOne?authorKey=' + author["authorId"]
                    td_authorName.appendChild(td_authorName_a)

                    let td_authorSex = document.createElement("td")
                    switch (author["authorSex"]) {
                        case 0:
                            td_authorSex.innerText = '女'
                            break
                        case 1:
                            td_authorSex.innerText = '男'
                            break
                        case 2:
                            td_authorSex.innerText = '保密'
                            break
                        default:
                            td_authorSex.innerText = '？'
                    }

                    let td_authorAge = document.createElement("td")
                    td_authorAge.innerText = author["authorAge"] + '岁'

                    let td_bookNumber = document.createElement("td")
                    let td_author_bookNumber_a = document.createElement("a")
                    td_author_bookNumber_a.innerText = author["bookNumber"] + '作'
                    td_author_bookNumber_a.href = '/book/selectByAuthor?authorId=' + author["authorId"]
                    td_bookNumber.appendChild(td_author_bookNumber_a)

                    let td_operate = document.createElement("td")
                    let button_update = document.createElement('button')
                    button_update.innerText = '编辑'
                    button_update.className = 'table_button_update'
                    let button_delete = document.createElement('button')
                    button_delete.innerText = '删除'
                    button_delete.className = 'table_button_delete'
                    td_operate.appendChild(button_update)
                    td_operate.appendChild(button_delete)

                    tr.appendChild(td_authorId)
                    tr.appendChild(td_authorName)
                    tr.appendChild(td_authorSex)
                    tr.appendChild(td_authorAge)
                    tr.appendChild(td_bookNumber)
                    tr.appendChild(td_operate)

                    authorTable.appendChild(tr)
                }

                let pageLink_ul = document.getElementsByClassName("pageLink_ul")[0]
                pageLink_ul.innerHTML = ''
                if (pagesNumber > 1) {
                    let pageJump = document.createElement('input')
                    pageJump.className = 'pageJump'
                    pageLink_ul.appendChild(pageJump)

                    if (page !== pagesNumber) {
                        let pageLink_right = document.createElement("li")
                        pageLink_right.innerText = '>'
                        pageLink_right.addEventListener('click', function () {
                            getAuthorListVo(Number(page) + 1, sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_right)
                    }

                    if (pagesNumber > 5) {
                        if (page + 2 <= pagesNumber) {
                            let pageLink_right_2 = document.createElement("li")
                            pageLink_right_2.innerText = String(page + 2)
                            pageLink_right_2.addEventListener('click', function () {
                                getAuthorListVo(Number(page) + 2, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_right_2)
                        }
                        if (page + 1 <= pagesNumber) {
                            let pageLink_right_1 = document.createElement("li")
                            pageLink_right_1.innerText = String(page + 1)
                            pageLink_right_1.addEventListener('click', function () {
                                getAuthorListVo(Number(page) + 1, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_right_1)
                        }
                        let pageLink_now = document.createElement("li")
                        pageLink_now.innerText = page
                        pageLink_now.style.color = '#1aa'
                        pageLink_now.style.borderBottom = '1px solid #aa1'
                        pageLink_now.addEventListener('click', function () {
                            getAuthorListVo(Number(page), sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_now)
                        if (page - 1 >= 1) {
                            let pageLink_left_1 = document.createElement("li")
                            pageLink_left_1.innerText = String(page - 1)
                            pageLink_left_1.addEventListener('click', function () {
                                getAuthorListVo(Number(page) - 1, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_left_1)
                        }
                        if (page - 2 >= 1) {
                            let pageLink_left_2 = document.createElement("li")
                            pageLink_left_2.innerText = String(page - 2)
                            pageLink_left_2.addEventListener('click', function () {
                                getAuthorListVo(Number(page) - 2, sortItem, sortType)
                            })
                            pageLink_ul.appendChild(pageLink_left_2)
                        }
                    } else {
                        let innerPagesNumber = pagesNumber
                        while (innerPagesNumber > 0) {
                            let pageLink_li = document.createElement("li")
                            pageLink_li.innerText = innerPagesNumber
                            pageLink_li.addEventListener('click', function () {
                                getAuthorListVo(Number(this.innerText), sortItem, sortType)
                            })
                            if (innerPagesNumber === page) {
                                pageLink_li.style.color = '#1aa'
                                pageLink_li.style.borderBottom = '1px solid #aa1'
                            }
                            pageLink_ul.appendChild(pageLink_li)
                            innerPagesNumber--
                        }
                    }

                    if (page !== 1) {
                        let pageLink_left = document.createElement("li")
                        pageLink_left.innerText = '<'
                        pageLink_left.addEventListener('click', function () {
                            getAuthorListVo(Number(page) - 1, sortItem, sortType)
                        })
                        pageLink_ul.appendChild(pageLink_left)
                    }
                }

                if ($(authorTable.parentNode).find('.pagesNumberShow').length === 0) {
                    let pagesNumberShow = document.createElement('p')
                    pagesNumberShow.className = 'pagesNumberShow'
                    pagesNumberShow.innerText = '共' + pagesNumber + '页'
                    authorTable.parentNode.appendChild(pagesNumberShow)
                } else {
                    let pagesNumberShow = document.getElementsByClassName('pagesNumberShow')[0]
                    pagesNumberShow.innerText = '共' + pagesNumber + '页'
                }

                $(".pageJump").keyup(function (e) {
                    if (e.keyCode === 13) {
                        if (!isNaN(parseInt(this.value))) {
                            nowPage = parseInt(this.value)
                            getAuthorListVo(nowPage, nowSortItem, nowSortType)
                        }
                    }
                })
                $(".table_button_update").click(function () {
                    authorEdit(this)
                })
                $('.table_button_delete').click(function () {
                    authorDelete(this)
                })
            } else {
                let p = document.createElement("p")
                p.className = "search_result_emptyHind"
                p.innerText = "无对应内容，请确认后重试。"
                $(authorTable).html(p)
            }
        } else {
            let p = document.createElement("p")
            p.className = "search_result_emptyHind"
            p.innerText = res["msg"].split(';')[0]
            console.log(res["msg"].split(';')[1])
            $(authorTable).html(p)
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
    getAuthorListVo(1, sortKey, sortType)
}

function authorEdit(button) {
    let authorItem = button.parentNode.parentNode
    let authorData = $(authorItem).data('author')
    let authorEdit = document.createElement('div')
    authorEdit.className = 'itemEdit'
    let authorId = document.createElement('p')
    authorId.innerText = 'ID: ' + authorData["authorId"]
    authorEdit.appendChild(authorId)
    let authorName = document.createElement('div')
    let authorName_p = document.createElement('p')
    authorName_p.innerText = '姓名: '
    authorName.appendChild(authorName_p)
    let authorName_input = document.createElement('input')
    authorName_input.value = authorData["authorName"]
    authorName.appendChild(authorName_input)
    authorEdit.appendChild(authorName)
    let authorSex = document.createElement('div')
    let authorSex_p = document.createElement('p')
    authorSex_p.innerText = '性别: '
    authorSex.appendChild(authorSex_p)
    let authorSex_select = document.createElement('select')
    authorSex_select.add(new Option('女',0))
    authorSex_select.add(new Option('男',1))
    authorSex_select.add(new Option('保密',2))
    authorSex_select.value = authorData["authorSex"]
    authorSex_select.style.width = '17%'
    authorSex_select.style.height = '22px'
    authorSex.appendChild(authorSex_select)
    authorEdit.appendChild(authorSex)
    let authorAge = document.createElement('div')
    let authorAge_p = document.createElement('p')
    authorAge_p.innerText = '年龄: '
    authorAge.appendChild(authorAge_p)
    let authorAge_input = document.createElement('input')
    authorAge_input.value = authorData["authorAge"]
    authorAge.appendChild(authorAge_input)
    authorEdit.appendChild(authorAge)
    let update = document.createElement('button')
    update.innerText = '更新'
    update.onclick = function () {
        authorData["authorName"] = authorName_input.value
        authorData["authorSex"] = authorSex_select.value
        authorData["authorAge"] = authorAge_input.value
        authorUpdate(authorData)
    }
    authorEdit.appendChild(update)
    let cancel = document.createElement('button')
    cancel.innerText = '取消'
    cancel.onclick = function () {
        authorEdit.remove()
    }
    authorEdit.appendChild(cancel)
    $('body')[0].appendChild(authorEdit)
}

function authorUpdate(authorData) {
    myAxios.put('/author/update', authorData).then(res => {
        if (res.code === 200) {
            confirm('更新成功')
            getAuthorListVo(nowPage, nowSortItem, nowSortType)
        } else {
            confirm('更新失败' + res["msg"].split(';')[0])
            console.log(res["msg"].split(';')[1])
        }
    })
}

function authorDelete(button) {
    let authorItem = button.parentNode.parentNode
    let authorTable = authorItem.parentNode
    myAxios.delete('/author/delete?id=' + authorItem.firstElementChild.innerText).then(res => {
        if (res.code === 200) {
            confirm('删除成功')
            $(authorItem).remove()
            if ($(authorTable).find('.search_result_content').length === 0) {
                if (pagesNumber === 1) {
                    confirm('作者信息已全部删除')
                    location.href = '/home'
                }
                if (pagesNumber === nowPage)
                    nowPage--
                getAuthorListVo(nowPage, nowSortItem, nowSortType)
            }
        } else {
            confirm('删除失败' + res["msg"].split(';')[0])
            console.log(res["msg"].split(';')[1])
        }
    })
}
