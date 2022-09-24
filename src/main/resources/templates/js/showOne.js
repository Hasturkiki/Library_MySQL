window.onload = function () {
    let url = window.location.href
    let domain = '/' + url.split('http://localhost:8080/')[1].split('getOne')[0]
    let key = url.split('http://localhost:8080/')[1].split('Key=')[1]
    let showOneUrl = domain + 'showOne?key=' + key
    showOne(showOneUrl)
}

function showOne(showOneUrl) {
    let showBox = $('#showOne')[0]
    myAxios.post(showOneUrl).then(res => {
        if (res.code === 200) {
            let showData = res.data
            if (showData.length == null) {
                let item_table = document.createElement('table')
                for (let itemIndex in showData) {
                    if (showData[itemIndex] == null || (showData[itemIndex].toString().length > 0 && showData[itemIndex].toString() !== '[object Object]')) {
                        let item_tr = document.createElement('tr')
                        item_tr.innerText = showData[itemIndex]

                        if (itemIndex.indexOf('Id') !== -1)
                            item_tr.innerText = 'ID:\t' + item_tr.innerText
                        if (itemIndex.indexOf('Sex') !== -1)
                            switch (showData[itemIndex]) {
                                case 0:
                                    item_tr.innerText = '女'
                                    break
                                case 1:
                                    item_tr.innerText = '男'
                                    break
                                case 2:
                                    item_tr.innerText = '保密'
                                    break
                                default:
                                    item_tr.innerText = '？'
                            }
                        if (itemIndex.indexOf('Age') !== -1)
                            item_tr.innerText += '岁'
                        if (itemIndex.indexOf('bookNumber') !== -1)
                            item_tr.innerText += '作'
                        if (itemIndex.indexOf('saving') !== -1)
                            item_tr.innerText = '$' + item_tr.innerText
                        if (itemIndex.indexOf('createTime') === 0 || itemIndex.indexOf('updateTime') === 0 || itemIndex.indexOf('isDelete') === 0)
                            continue

                        item_table.appendChild(item_tr)
                    } else {
                        for (let innerIndex in showData[itemIndex]) {
                            let item_tr = document.createElement('tr')
                            item_tr.innerText = showData[itemIndex][innerIndex]

                            if (innerIndex.indexOf('Id') !== -1)
                                switch (itemIndex) {
                                    case 'bookBorrowTable':
                                        if (innerIndex === 'booksBorrowTableId')
                                            item_tr.innerText = 'ID:\t' + item_tr.innerText
                                        else
                                            item_tr.innerText = ''
                                        break
                                    case 'book':
                                        if (innerIndex === 'bookId')
                                            item_tr.innerText = 'ID:\t' + item_tr.innerText
                                        else
                                            item_tr.innerText = ''
                                        break
                                    case 'jointAuthorTable':
                                        if (innerIndex === 'tableId')
                                            item_tr.innerText = 'ID:\t' + item_tr.innerText
                                        else
                                            item_tr.innerText = ''
                                        break
                                }
                            if (innerIndex.indexOf('isBorrowing') !== -1)
                                if (showData[itemIndex][innerIndex] === 1) {
                                    item_tr.innerText = '借用中'
                                } else {
                                    item_tr.innerText = '已归还'
                                }
                            if (innerIndex.indexOf('borrowTime') !== -1 || innerIndex.indexOf('backTime') !== -1) {
                                if (item_tr.innerText.length === 0)
                                    item_tr.innerText = ''
                                else
                                    item_tr.innerText = item_tr.innerText.split('T')[0] + ' ' + item_tr.innerText.split('T')[1]
                            }
                            if (innerIndex.indexOf('bookNumber') !== -1)
                                item_tr.innerText += '作'
                            if (innerIndex.indexOf('price') !== -1)
                                switch (showData[itemIndex][innerIndex]) {
                                    case 0:
                                        item_tr.innerText = 'free'
                                        break
                                    default:
                                        item_tr.innerText = '$' + item_tr.innerText
                                }
                            if (innerIndex.indexOf('isBeingBorrowed') !== -1)
                                switch (showData[itemIndex][innerIndex]) {
                                    case 0:
                                        item_tr.innerText = '无借出'
                                        break
                                    default:
                                        item_tr.innerText = '借出' + item_tr.innerText + '本'
                                }
                            if (innerIndex.indexOf('createTime') === 0 || innerIndex.indexOf('updateTime') === 0 || innerIndex.indexOf('isDelete') === 0)
                                continue

                            item_table.appendChild(item_tr)
                        }
                    }
                }
                showBox.appendChild(item_table)
            } else {
                for (let itemsIndex in showData) {
                    if (showData[itemsIndex] != null) {
                        let items_table = document.createElement('table')
                        for (let itemIndex in showData[itemsIndex]) {
                            if (showData[itemsIndex][itemIndex] == null || (showData[itemsIndex][itemIndex].toString().length > 0 && showData[itemsIndex][itemIndex].toString() !== '[object Object]')) {
                                let item_tr = document.createElement('tr')
                                item_tr.innerText = showData[itemsIndex][itemIndex]

                                if (itemIndex.indexOf('Id') !== -1)
                                    item_tr.innerText = 'ID:\t' + item_tr.innerText
                                if (itemIndex.indexOf('Sex') !== -1)
                                    switch (showData[itemsIndex][itemIndex]) {
                                        case 0:
                                            item_tr.innerText = '女'
                                            break
                                        case 1:
                                            item_tr.innerText = '男'
                                            break
                                        case 2:
                                            item_tr.innerText = '保密'
                                            break
                                        default:
                                            item_tr.innerText = '？'
                                    }
                                if (itemIndex.indexOf('Age') !== -1)
                                    item_tr.innerText += '岁'
                                if (itemIndex.indexOf('bookNumber') !== -1)
                                    item_tr.innerText += '作'
                                if (itemIndex.indexOf('saving') !== -1)
                                    item_tr.innerText = '$' + item_tr.innerText
                                if (itemIndex.indexOf('createTime') === 0 || itemIndex.indexOf('updateTime') === 0 || itemIndex.indexOf('isDelete') === 0)
                                    continue

                                items_table.appendChild(item_tr)
                            } else {
                                for (let innerIndex in showData[itemsIndex][itemIndex]) {
                                    let item_tr = document.createElement('tr')
                                    item_tr.innerText = showData[itemsIndex][itemIndex][innerIndex]

                                    if (innerIndex.indexOf('Id') !== -1)
                                        switch (itemsIndex) {
                                            case '1':
                                                if (innerIndex === 'booksBorrowTableId')
                                                    item_tr.innerText = 'ID:\t' + item_tr.innerText
                                                else
                                                    item_tr.innerText = ''
                                                break
                                            case '2':
                                                if (innerIndex === 'bookId')
                                                    item_tr.innerText = 'ID:\t' + item_tr.innerText
                                                else
                                                    item_tr.innerText = ''
                                                break
                                            case '3':
                                                if (innerIndex === 'tableId')
                                                    item_tr.innerText = 'ID:\t' + item_tr.innerText
                                                else
                                                    item_tr.innerText = ''
                                                break
                                        }
                                    if (innerIndex.indexOf('isBorrowing') !== -1)
                                        if (showData[itemsIndex][itemIndex][innerIndex] === 1) {
                                            item_tr.innerText = '借用中'
                                        } else {
                                            item_tr.innerText = '已归还'
                                        }
                                    if (innerIndex.indexOf('borrowTime') !== -1 || innerIndex.indexOf('backTime') !== -1) {
                                        if (item_tr.innerText.length === 0)
                                            item_tr.innerText = ''
                                        else
                                            item_tr.innerText = item_tr.innerText.split('T')[0] + ' ' + item_tr.innerText.split('T')[1]
                                    }
                                    if (innerIndex.indexOf('bookNumber') !== -1)
                                        item_tr.innerText += '作'
                                    if (innerIndex.indexOf('price') !== -1)
                                        switch (showData[itemsIndex][itemIndex][innerIndex]) {
                                            case 0:
                                                item_tr.innerText = 'free'
                                                break
                                            default:
                                                item_tr.innerText = '$' + item_tr.innerText
                                        }
                                    if (innerIndex.indexOf('isBeingBorrowed') !== -1)
                                        switch (showData[itemsIndex][itemIndex][innerIndex]) {
                                            case 0:
                                                item_tr.innerText = '无借出'
                                                break
                                            default:
                                                item_tr.innerText = '借出' + item_tr.innerText + '本'
                                        }
                                    if (innerIndex.indexOf('createTime') === 0 || innerIndex.indexOf('updateTime') === 0 || innerIndex.indexOf('isDelete') === 0)
                                        continue

                                    items_table.appendChild(item_tr)
                                }
                            }
                        }
                        showBox.appendChild(items_table)
                    }
                }
            }
        } else {
            let p = document.createElement("p")
            p.className = "showOne_result_emptyHind"
            p.innerText = res["msg"].split(';')[0] + "，请确认后重试。"
            showBox.appendChild(p)
        }
    })
}
