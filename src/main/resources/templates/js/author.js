window.onload = function getAllAuthor() {
    let authorTable = document.getElementsByClassName("author_table")[0];
    myAxios.get('/author/getAll').then(res => {
        if (res.code === 200) {
            let authorList = res.data
            if (authorList.length !== 0) {
                authorTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="4" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '           <th>作者ID</th>\n' +
                    '           <th>作者名</th>\n' +
                    '           <th>性别</th>\n' +
                    '           <th>年龄</th>\n' +
                    '           <th>作品数</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'
                for (const author of authorList) {
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    let td_readerId = document.createElement("td");
                    td_readerId.innerText = author.authorId;

                    let td_readerName = document.createElement("td");
                    td_readerName.innerText = author.authorName

                    let td_readerSex = document.createElement("td");
                    switch (author.authorSex) {
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

                    let td_readerAge = document.createElement("td");
                    td_readerAge.innerText = author.authorAge + '岁'

                    let td_bookNumber = document.createElement("td");
                    td_bookNumber.innerText = author.bookNumber + '作'  //todo 作品链接

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
                    tr.appendChild(td_bookNumber)
                    tr.appendChild(td_operate);

                    authorTable.appendChild(tr)
                }
            } else {
                let p = document.createElement("p");
                p.className = "search_result_emptyHind";
                p.innerText = "无对应内容，请确认后重试。";
                authorTable.appendChild(p);
            }
        } else {
            let p = document.createElement("p");
            p.className = "search_result_emptyHind";
            p.innerText = res.msg.split(';')[0]
            console.log(res.msg.split(';')[1])
            authorTable.appendChild(p);
        }
    })
}

$('.table_button_update').click({
//    todo update author
})

$('.table_button_delete').click({
//    todo delete author
})
