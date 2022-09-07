window.onload = function getAllTag() {
    let tagTable = document.getElementsByClassName("tag_table")[0]
    myAxios.get('/tag/getAll').then(res => {
        if (res.code === 200) {
            let tagList = res.data
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
