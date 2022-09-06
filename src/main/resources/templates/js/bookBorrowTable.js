window.onload = function getAllBookBorrowTable() {
    let bookBorrowTableTable = document.getElementsByClassName("bookBorrowTable_table")[0];
    myAxios.get('/bookBorrowTable/getAll').then(res => {
        if (res.code === 200) {
            let bookBorrowTableVoList = res.data
            if (bookBorrowTableVoList.length !== 0) {
                bookBorrowTableTable.innerHTML = '<colgroup>\n' +
                    '            <col style="background-color: #bcc">\n' +
                    '            <col span="5" style="background-color: #cdd">\n' +
                    '            <col style="background-color: #dee">\n' +
                    '        </colgroup>\n' +
                    '        <tr>\n' +
                    '            <th>借书表ID</th>\n' +
                    '            <th>借书者</th>\n' +
                    '            <th>所借书</th>\n' +
                    '            <th>借书状态</th>\n' +
                    '            <th>外借时间</th>\n' +
                    '            <th>归还时间</th>\n' +
                    '            <th>操作</th>\n' +
                    '        </tr>'
                for (const bookBorrowTableVo of bookBorrowTableVoList) {
                    let tr = document.createElement("tr");
                    tr.className = "search_result_content";

                    const bookBorrowTable = bookBorrowTableVo.bookBorrowTable

                    let td_bookBorrowTableId = document.createElement("td");
                    td_bookBorrowTableId.innerText = bookBorrowTable.booksBorrowTableId

                    let td_readerId = document.createElement("td");
                    td_readerId.innerText = bookBorrowTableVo.readerName
                    td_readerId.readerId = bookBorrowTable.readerId

                    let td_bookId = document.createElement("td");
                    td_bookId.innerText = bookBorrowTableVo.bookName
                    td_bookId.bookId = bookBorrowTable.bookId

                    let td_isBorrowing = document.createElement("td");
                    if (bookBorrowTable.isBorrowing === 1) {
                        td_isBorrowing.innerText = '借用中'
                    } else {
                        td_isBorrowing.innerText = '已归还'
                    }


                    let td_borrowTime = document.createElement("td");
                    td_borrowTime.innerText = bookBorrowTable.borrowTime.split('T')[0] + ' ' + bookBorrowTable.borrowTime.split('T')[1]

                    let td_backTime = document.createElement("td");
                    if(bookBorrowTable.backTime === null) {
                        td_backTime.innerText = '无'
                    } else {
                        td_backTime.innerText = bookBorrowTable.borrowTime.split('T')[0] + ' ' + bookBorrowTable.borrowTime.split('T')[1]
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

                    tr.appendChild(td_bookBorrowTableId);
                    tr.appendChild(td_readerId);
                    tr.appendChild(td_bookId);
                    tr.appendChild(td_isBorrowing);
                    tr.appendChild(td_borrowTime);
                    tr.appendChild(td_backTime);
                    tr.appendChild(td_operate);

                    bookBorrowTableTable.appendChild(tr)
                }
            } else {
                let p = document.createElement("p")
                p.className = "search_result_emptyHind"
                p.innerText = "无对应内容，请确认后重试。"
                bookBorrowTableTable.appendChild(p)
            }
        } else {
            let p = document.createElement("p")
            p.className = "search_result_emptyHind"
            p.innerText = res.msg.split(';')[0]
            console.log(res.msg.split(';')[1])
            bookBorrowTableTable.appendChild(p)
        }
    })
}

$('.table_button_update').click({
//    todo update bookBorrowTable
})

$('.table_button_delete').click({
//    todo delete bookBorrowTable
})
