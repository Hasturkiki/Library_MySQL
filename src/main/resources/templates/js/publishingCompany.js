window.onload = function getAllPublishingCompanyTable() {
    let publishingCompanyTable = document.getElementsByClassName("publishingCompany_table")[0];
    myAxios.get('/publishingCompany/getAll').then(res => {
        if (res.code === 200) {
            let publishingCompanyList = res.data
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
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    let td_publishingCompanyId = document.createElement("td");
                    td_publishingCompanyId.innerText = publishingCompany.publishingCompanyId

                    let td_publishingCompanyName = document.createElement("td");
                    td_publishingCompanyName.innerText = publishingCompany.publishingCompanyName

                    let td_publishingCompanyTelephoneNumber = document.createElement("td");
                    td_publishingCompanyTelephoneNumber.innerText = publishingCompany.publishingCompanyTelephoneNumber

                    let td_publishingCompanyAddress = document.createElement("td");
                    td_publishingCompanyAddress.innerText = publishingCompany.publishingCompanyAddress

                    let td_bookNumber = document.createElement("td");
                    td_bookNumber.innerText = publishingCompany.bookNumber

                    let td_operate = document.createElement("td")
                    let button_update = document.createElement('button')
                    button_update.innerText = '编辑'
                    button_update.className = 'table_button_update'
                    let button_delete = document.createElement('button')
                    button_delete.innerText = '删除'
                    button_delete.className = 'table_button_delete'
                    td_operate.appendChild(button_update)
                    td_operate.appendChild(button_delete)

                    tr.appendChild(td_publishingCompanyId);
                    tr.appendChild(td_publishingCompanyName);
                    tr.appendChild(td_publishingCompanyTelephoneNumber);
                    tr.appendChild(td_publishingCompanyAddress);
                    tr.appendChild(td_bookNumber);
                    tr.appendChild(td_operate);

                    publishingCompanyTable.appendChild(tr)
                }
            } else {
                let p = document.createElement("p");
                p.className = "search_result_emptyHind";
                p.innerText = "无对应内容，请确认后重试。";
                publishingCompanyTable.appendChild(p);
            }
        } else {
            let p = document.createElement("p");
            p.className = "search_result_emptyHind";
            p.innerText = res.msg.split(';')[0]
            console.log(res.msg.split(';')[1])
            publishingCompanyTable.appendChild(p);
        }
    })
}

$('.table_button_update').click({
//    todo update publishingCompany
})

$('.table_button_delete').click({
//    todo delete publishingCompany
})
