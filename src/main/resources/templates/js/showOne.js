window.onload = function () {
    let url = window.location.href

    let domain = '/' + url.split('http://localhost:8080/')[1].split('getOne')[0]
    let key = url.split('http://localhost:8080/')[1].split('=')[1]
    let showOneUrl = domain + 'showOne?key=' + key
    showOne(showOneUrl)
}

function showOne(showOneUrl) {
    let showBox = $('#showOne')[0]
    myAxios.post(showOneUrl).then(res => {
        if (res.code === 200) {
            let showData = res.data
            if (showData.length == null) {
                for (let itemIndex in showData) {
                    let item_a = document.createElement('a')
                    item_a.innerText = showData[itemIndex]
                    showBox.appendChild(item_a)
                }
            } else {
                for (let items of showData) {
                    let items_div = document.createElement('div')
                    for (let itemIndex in items) {
                        let item_a = document.createElement('a')
                        item_a.innerText = items[itemIndex]
                        items_div.appendChild(item_a)
                    }
                    showBox.appendChild(items_div)
                }
            }
        }
    })
}
