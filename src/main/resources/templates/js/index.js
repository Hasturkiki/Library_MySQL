window.onload = function () {
    window.scrollTo(0, 0)
    // 预先进行一次空查询，减少后续响应时间
    myAxios.post('/search?searchKey=null').then(res => {
        console.log(res["msg"])
    })
}

$(document).keyup(function (e) {
    let searchString = $('#search_string')
    if (searchString.is(":focus"))
        switch (e.keyCode) {
            case 13:
                search()
                return
            case 27:
                searchString.blur()
                return
        }
    else
        switch (e.keyCode) {
            case 191:
                searchString.focus()
                return
        }
})

let TableDataList = {}
let now = 0

function search() {
    let searchKey = document.getElementById("search_string").value
    let resultBox = document.getElementById("search_result")
    resultBox.style.backgroundColor = '#ffe7'
    if (resultBox.childNodes.length !== 0)
        resultBox.innerHTML = ""
    if (document.getElementsByClassName("search_result_emptyHind")[0])
        document.getElementsByClassName("search_result_emptyHind")[0].remove()
    $.ajax({
        url: '/search',
        type: 'post',
        async: true,
        data: 'searchKey=' + searchKey,
        dataType: 'json',
        success: (res) => {
            if (res.code === 200) {
                let data = res.data
                if (data[0] != null) {

                    let hint_p = document.createElement('p')
                    hint_p.className = 'hint_p'
                    hint_p.innerText = '作者'
                    resultBox.appendChild(hint_p)

                    let table = document.createElement('table')
                    table.className = 'search_result_table'
                    table.title = 'author'
                    table.innerHTML =
                        '<colgroup>\n' +
                        '    <col style="background-color: #def">\n' +
                        '  </colgroup>\n' +
                        '  <tr>\n' +
                        '    <th onclick="tableSort(this)">作者名</th>\n' +
                        '    <th onclick="tableSort(this)">性别</th>\n' +
                        '    <th onclick="tableSort(this)">年龄</th>\n' +
                        '    <th onclick="tableSort(this)">作品数</th>\n' +
                        '  </tr>'

                    let authorList = data[0]
                    for (const author of authorList) {
                        let tr = document.createElement("tr")
                        tr.className = "search_result_content"

                        let td_authorName = document.createElement("td")
                        let td_author_authorName_a = document.createElement("a")
                        td_author_authorName_a.innerText = author["authorName"]
                        td_author_authorName_a.href = '/author/getOne?authorId=' + author["authorId"]
                        td_authorName.appendChild(td_author_authorName_a)

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

                        let td_author_bookNumber = document.createElement("td")
                        let td_author_bookNumber_a = document.createElement("a")
                        td_author_bookNumber_a.innerText = author["bookNumber"] + '作'
                        td_author_bookNumber_a.href = '/book/selectByAuthor?authorId=' + author["authorId"]
                        td_author_bookNumber.appendChild(td_author_bookNumber_a)

                        tr.appendChild(td_authorName)
                        tr.appendChild(td_authorSex)
                        tr.appendChild(td_authorAge)
                        tr.appendChild(td_author_bookNumber)
                        table.appendChild(tr)
                    }
                    resultBox.appendChild(table)
                }
                if (data[1] != null) {

                    let hint_p = document.createElement('p')
                    hint_p.className = 'hint_p'
                    hint_p.innerText = '书籍'
                    resultBox.appendChild(hint_p)

                    let table = document.createElement('table')
                    table.className = 'search_result_table'
                    table.title = 'book'
                    table.innerHTML = '<colgroup>\n' +
                        '    <col style="background-color: #def">\n' +
                        '  </colgroup>\n' +
                        '  <tr>\n' +
                        '    <th onclick="tableSort(this)">书名</th>\n' +
                        '    <th onclick="tableSort(this)">作者</th>\n' +
                        '    <th onclick="tableSort(this)">IBSN号</th>\n' +
                        '    <th onclick="tableSort(this)">出版社</th>\n' +
                        '    <th onclick="tableSort(this)">标签</th>\n' +
                        '    <th onclick="tableSort(this)">库存</th>\n' +
                        '    <th onclick="tableSort(this)">价格</th>\n' +
                        '    <th onclick="tableSort(this)">借出情况</th>\n' +
                        '    <th onclick="tableSort(this)">出版日期</th>\n' +
                        '    <th onclick="tableSort(this)">备注</th>\n' +
                        '  </tr>'

                    let bookVoList = data[1]
                    for (const bookVo of bookVoList) {
                        let book = bookVo["book"]

                        let tr = document.createElement("tr")
                        tr.className = "search_result_content"

                        let td_bookName = document.createElement("td")
                        let td_bookName_a = document.createElement("a")
                        td_bookName_a.innerText = book["bookName"]
                        td_bookName_a.href = '/book/getOne?bookId=' + book["bookId"]
                        td_bookName.appendChild(td_bookName_a)

                        let td_author = document.createElement("td")
                        if (book["jointAuthorTableId"] !== 0) {
                            let td_book_authorName_a = document.createElement("a")
                            td_book_authorName_a.innerText = bookVo["authorName"].split(' ')[0]
                            td_book_authorName_a.href = '/author/getOne?authorId=' + book["authorId"]
                            td_author.appendChild(td_book_authorName_a)
                            let td_book_jointAuthorTable_a = document.createElement("a")
                            td_book_jointAuthorTable_a.innerText = ' 等'
                            td_book_jointAuthorTable_a.href = '/jointAuthorTableVo/getOne?jointAuthorTableId=' + book["jointAuthorTableId"]
                            td_author.appendChild(td_book_jointAuthorTable_a)
                        } else {
                            let td_book_authorName_a = document.createElement("a")
                            td_book_authorName_a.innerText = bookVo["authorName"]
                            td_book_authorName_a.href = '/author/getOne?authorId=' + book["authorId"]
                            td_author.appendChild(td_book_authorName_a)
                        }

                        let td_ibsn = document.createElement("td")
                        td_ibsn.innerText = book["ibsn"]

                        let td_publishingCompany = document.createElement("td")
                        let td_book_publishingCompanyName_a = document.createElement("a")
                        td_book_publishingCompanyName_a.innerText = bookVo["publishingCompanyName"]
                        td_book_publishingCompanyName_a.href = '/publishingCompany/getOne?publishingCompanyId=' + book["publishingCompanyId"]
                        td_publishingCompany.appendChild(td_book_publishingCompanyName_a)

                        let td_tag = document.createElement("td")
                        let td_book_tagName_a = document.createElement("a")
                        td_book_tagName_a.innerText = bookVo["tagName"]
                        td_book_tagName_a.href = '/tag/getOne?tagId=' + book["tagId"]
                        td_tag.appendChild(td_book_tagName_a)

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
                        let td_book_bookBorrowTable_a = document.createElement("a")
                        switch (book["isBeingBorrowed"]) {
                            case 0:
                                td_book_bookBorrowTable_a.innerText = '无借出'
                                break
                            default:
                                if (book["isBeingBorrowed"] < book["quantity"])
                                    td_book_bookBorrowTable_a.innerText = '借出' + book["isBeingBorrowed"] + '本'
                                else
                                    td_book_bookBorrowTable_a.innerText = '全部借出'
                        }
                        td_book_bookBorrowTable_a.href = '/bookBorrowTable/selectByBook?bookId=' + book["bookId"]
                        td_isBeingBorrowed.appendChild(td_book_bookBorrowTable_a)

                        let td_publicationDate = document.createElement("td")
                        td_publicationDate.innerText = book["publicationDate"]

                        let td_jointAuthorTable = document.createElement("td")
                        let td_book_jointAuthorTable_a = document.createElement("a")
                        switch (book["jointAuthorTableId"]) {
                            case 0:
                                td_book_jointAuthorTable_a.innerText = '无'
                                td_book_jointAuthorTable_a.style.color = '#000'
                                break
                            default:
                                td_book_jointAuthorTable_a.innerText = '合著'
                                td_book_jointAuthorTable_a.href = '/jointAuthorTableVo/getOne?jointAuthorTableId=' + book["jointAuthorTableId"]
                        }
                        td_jointAuthorTable.appendChild(td_book_jointAuthorTable_a)

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
                        table.appendChild(tr)
                    }
                    resultBox.appendChild(table)
                }
                if (data[2] != null) {

                    let hint_p = document.createElement('p')
                    hint_p.className = 'hint_p'
                    hint_p.innerText = '出版社'
                    resultBox.appendChild(hint_p)

                    let table = document.createElement('table')
                    table.className = 'search_result_table'
                    table.title = 'publishingCompany'
                    table.innerHTML = '<colgroup>\n' +
                        '    <col style="background-color: #def">\n' +
                        '  </colgroup>\n' +
                        '  <tr>\n' +
                        '    <th onclick="tableSort(this)">出版社名称</th>\n' +
                        '    <th onclick="tableSort(this)">联系方式</th>\n' +
                        '    <th onclick="tableSort(this)">通信地址</th>\n' +
                        '    <th onclick="tableSort(this)">作品数</th>\n' +
                        '  </tr>'

                    let publishingCompanyList = data[2]
                    for (const publishingCompany of publishingCompanyList) {
                        let tr = document.createElement("tr")
                        tr.className = "search_result_content"

                        let td_publishingCompanyName = document.createElement("td")
                        let td_publishingCompany_publishingCompanyName_a = document.createElement("a")
                        td_publishingCompany_publishingCompanyName_a.innerText = publishingCompany["publishingCompanyName"]
                        td_publishingCompany_publishingCompanyName_a.href = '/publishingCompany/getOne?publishingCompanyId=' + publishingCompany["publishingCompanyId"]
                        td_publishingCompanyName.appendChild(td_publishingCompany_publishingCompanyName_a)

                        let td_publishingCompanyTelephoneNumber = document.createElement("td")
                        td_publishingCompanyTelephoneNumber.innerText = publishingCompany["publishingCompanyTelephoneNumber"]

                        let td_publishingCompanyAddress = document.createElement("td")
                        td_publishingCompanyAddress.innerText = publishingCompany["publishingCompanyAddress"]

                        let td_publishingCompany_bookNumber = document.createElement("td")
                        let td_publishingCompany_bookNumber_a = document.createElement("a")
                        td_publishingCompany_bookNumber_a.innerText = publishingCompany["bookNumber"] + '作'
                        td_publishingCompany_bookNumber_a.href = '/book/selectByPublishingCompany?publishingCompanyId=' + publishingCompany["publishingCompanyId"]
                        td_publishingCompany_bookNumber.appendChild(td_publishingCompany_bookNumber_a)

                        tr.appendChild(td_publishingCompanyName)
                        tr.appendChild(td_publishingCompanyTelephoneNumber)
                        tr.appendChild(td_publishingCompanyAddress)
                        tr.appendChild(td_publishingCompany_bookNumber)
                        table.appendChild(tr)
                    }
                    resultBox.appendChild(table)
                }
                if (data[3] != null) {

                    let hint_p = document.createElement('p')
                    hint_p.className = 'hint_p'
                    hint_p.innerText = '读者'
                    resultBox.appendChild(hint_p)

                    let table = document.createElement('table')
                    table.className = 'search_result_table'
                    table.title = 'reader'
                    table.innerHTML = '<colgroup>\n' +
                        '    <col style="background-color: #def">\n' +
                        '  </colgroup>\n' +
                        '  <tr>\n' +
                        '    <th onclick="tableSort(this)">用户名</th>\n' +
                        '    <th onclick="tableSort(this)">性别</th>\n' +
                        '    <th onclick="tableSort(this)">年龄</th>\n' +
                        '    <th onclick="tableSort(this)">余额</th>\n' +
                        '  </tr>'

                    let readerList = data[3]
                    for (const reader of readerList) {
                        let tr = document.createElement("tr")
                        tr.className = "search_result_content"

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

                        tr.appendChild(td_readerName)
                        tr.appendChild(td_readerSex)
                        tr.appendChild(td_readerAge)
                        tr.appendChild(td_saving)
                        table.appendChild(tr)
                    }
                    resultBox.appendChild(table)
                }
                if (data[4] != null) {

                    let hint_p = document.createElement('p')
                    hint_p.className = 'hint_p'
                    hint_p.innerText = '标签'
                    resultBox.appendChild(hint_p)

                    let table = document.createElement('table')
                    table.className = 'search_result_table'
                    table.title = 'tag'
                    table.innerHTML = '<colgroup>\n' +
                        '    <col style="background-color: #def">\n' +
                        '  </colgroup>\n' +
                        '  <tr>\n' +
                        '    <th onclick="tableSort(this)">标签名称</th>\n' +
                        '    <th onclick="tableSort(this)">作品数</th>\n' +
                        '  </tr>'

                    let tagList = data[4]
                    for (const tag of tagList) {

                        let tr = document.createElement("tr")
                        tr.className = "search_result_content"

                        let td_tagName = document.createElement("td")
                        let td_tag_tagName_a = document.createElement("a")
                        td_tag_tagName_a.innerText = tag["tagName"]
                        td_tag_tagName_a.href = '/tag/getOne?tagId=' + tag["tagId"]
                        td_tagName.appendChild(td_tag_tagName_a)

                        let td_tag_bookNumber = document.createElement("td")
                        let td_tag_bookNumber_a = document.createElement("a")
                        td_tag_bookNumber_a.innerText = tag["bookNumber"] + '作'
                        td_tag_bookNumber_a.href = '/book/selectByTag?tagId=' + tag["tagId"]
                        td_tag_bookNumber.appendChild(td_tag_bookNumber_a)

                        tr.appendChild(td_tagName)
                        tr.appendChild(td_tag_bookNumber)
                        table.appendChild(tr)
                    }
                    resultBox.appendChild(table)
                }
                window.scrollTo(0, 100)
                TableDataList = {}
                now = 0
                if (data[0] == null && data[1] == null && data[2] == null && data[3] == null && data[4] == null) {
                    let p = document.createElement("p")
                    p.className = "search_result_emptyHind"
                    p.innerText = "无对应内容，请确认后重试。"
                    resultBox.appendChild(p)
                }
            } else {
                let p = document.createElement("p")
                p.className = "search_result_emptyHind"
                p.innerText = res["msg"].split(';')[0]
                resultBox.appendChild(p)
                console.log(res["msg"])
            }
        },
        error: () => {
            let p = document.createElement("p")
            p.className = "search_result_emptyHind"
            p.innerText = "搜索失败，请确认后重试。"
            resultBox.appendChild(p)
        }
    })
}

function tableSort(sortKey) {
    let tableItemIndex = $(sortKey).index()
    let sortTable = sortKey.parentNode.parentNode.parentNode
    let tableContent = $(sortTable).find('.search_result_content')
    if (tableContent.length <= 1)
        return
    let sortType
    let tableDataListIndex
    let tableDataListIndexNew
    let tableDataListIndexNewIsEnd = false
    switch (sortKey.innerText.split(' ')[1]) {
        case '▲':
            sortKey.innerText = sortKey.innerText.split(' ')[0] + ' ▼'
            sortKey.tableDataListIndexNew = now
            sortType = 'desc'
            break
        case '▼':
            sortKey.innerText = sortKey.innerText.split(' ')[0]
            tableDataListIndex = sortKey.tableDataListIndex
            tableDataListIndexNew = sortKey.tableDataListIndexNew
            tableDataListIndexNewIsEnd = true
            sortType = 'none'
            break
        default:
            sortKey.innerText = sortKey.innerText.split(' ')[0] + ' ▲'
            sortKey.tableDataListIndex = now
            sortType = 'asc'
    }
    let tableData = []
    tableContent.each(function () {
        let td = $(this).find('td')
        tableData.push(sortTable.title + '.space' + td[tableItemIndex].innerText + '.space' + $(this).html())
        $(this).html('')
    })
    let tableDataNow = JSON.parse(JSON.stringify(tableData))
    let tableDataAfterSort = []
    switch (sortType) {
        case 'asc':
            TableDataList[now] = tableDataNow
            now += 1
            tableDataAfterSort = tableData.sort(sortAsc)
            break
        case 'desc':
            TableDataList[now] = tableDataNow
            now += 1
            tableDataAfterSort = tableData.sort(sortDesc)
            break
        case 'none':
            tableDataAfterSort = TableDataList[tableDataListIndex]
            let oldTableData = {}
            let oldTableDataIndex = 0
            let isBackToFirst = true
            if (now > 1)
                for (let tableDataIndex in TableDataList) {
                    if (TableDataList[tableDataIndex].length !== 0)
                        if (TableDataList[tableDataIndex][0].split('.space')[0] === tableDataAfterSort[0].split('.space')[0])
                            if (parseInt(tableDataIndex) > tableDataListIndex && parseInt(tableDataIndex) !== tableDataListIndexNew) {
                                isBackToFirst = false
                                if (parseInt(tableDataIndex) > tableDataListIndexNew)
                                    tableDataAfterSort = tableDataNow
                                else
                                    tableDataAfterSort = JSON.parse(JSON.stringify(TableDataList[tableDataListIndexNew]))
                            } else if (parseInt(tableDataIndex) < tableDataListIndex)
                                oldTableData[oldTableDataIndex++] = TableDataList[tableDataIndex]
                }
            if (isBackToFirst && Object.keys(oldTableData).length !== 0)
                tableDataAfterSort = oldTableData[0]
            TableDataList[tableDataListIndexNew] = []
            break
        default:
            console.log('tableSort failed.\n' + tableData)
            break
    }
    tableContent.each(function () {
        $(this).html(tableDataAfterSort[$(this).index() - 2].split('.space')[2])
    })
}

function sortAsc(a, b) {
    let flagA = a.split('.space')[1].replaceAll('-', '').replace('岁', '').replace('$', '')
    let flagB = b.split('.space')[1].replaceAll('-', '').replace('岁', '').replace('$', '')
    if (0 < flagA[0] && flagA[0] < 9 && 0 < flagB[0] && flagB[0] < 9) {
        flagA = parseFloat(flagA)
        flagB = parseFloat(flagB)
    }
    if (flagA < flagB)
        return -1
    else if (flagA > flagB)
        return 1
    else
        return 0
}

function sortDesc(a, b) {
    let flagA = a.split('.space')[1].replaceAll('-', '').replace('岁', '').replace('$', '')
    let flagB = b.split('.space')[1].replaceAll('-', '').replace('岁', '').replace('$', '')
    if (0 < flagA[0] && flagA[0] < 9 && 0 < flagB[0] && flagB[0] < 9) {
        flagA = parseFloat(flagA)
        flagB = parseFloat(flagB)
    }
    if (flagA < flagB)
        return 1
    else if (flagA > flagB)
        return -1
    else
        return 0
}
