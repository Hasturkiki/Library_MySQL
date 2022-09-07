window.onload = function getAllJointAuthorTable() {
    let jointAuthorTableTable = document.getElementsByClassName("jointAuthorTable_table")[0]
    myAxios.get('/jointAuthorTable/getAll').then(res => {
        if (res.code === 200) {
            let jointAuthorTableVoList = res.data
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
