window.onload = function getAllReader() {
    let readerTable = document.getElementsByClassName("reader_table")[0]
    myAxios.get('/reader/getAll').then(res => {
        if (res.code === 200) {
            let readerList = res.data
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
