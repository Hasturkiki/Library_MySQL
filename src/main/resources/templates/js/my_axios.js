// const { resolve, reject } = require("core-js/fn/promise")

function iaxios() {
    //保存拦截器中的回调函数
    this.saveRequest = []
    this.saveResponse = []
    //保存请求的数据
    this.data = {};
    let _this = this;
    this.interceptors = {
        request(cb) {
            _this.saveRequest.push(cb)
        },
        response(aa) {
            _this.saveResponse.push(aa)
        },
    }
}


iaxios.prototype.post = function (url, data) {
    this.data = data;
    let _this = this;
    // this.saveRequest && this.saveRequest(this)
    //请求之前调用请求拦截的回调函数
    if (this.saveRequest) {
        this.saveRequest.forEach(fn => {
            fn(this)
        })
    }

    //返回promise对象
    return new Promise((resolve, reject) => {
        var xhr = new XMLHttpRequest();
        xhr.open('post', url, true);
        //设置请求头的配置
        setHeader(xhr, this.headers);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if ((xhr.status == 200 || xhr.status == 304)) {
                    //用来保存返回的数据
                    let newRespose = new Object;
                    newRespose.data = JSON.parse(xhr.responseText);
                    // _this.saveResponse && _this.saveResponse(newRespose)
                    //在返回数据之前调用相应拦截器的回调函数
                    if (_this.saveResponse) {
                        _this.saveResponse.forEach(fn => {
                            fn(newRespose)
                        })
                    }
                    resolve(newRespose.data)
                } else {
                    reject(xhr.responseText)
                }
            }
        };
        xhr.send(JSON.stringify(data))
    })
}

iaxios.prototype.get = function (url) {
    let _this = this;
    // this.saveRequest && this.saveRequest(this)
    //请求之前调用请求拦截的回调函数
    if (this.saveRequest) {
        this.saveRequest.forEach(fn => {
            fn(this)
        })
    }

    //返回promise对象
    return new Promise((resolve, reject) => {
        var xhr = new XMLHttpRequest();
        xhr.open('get', url, true);
        //设置请求头的配置
        setHeader(xhr, this.headers);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if ((xhr.status == 200 || xhr.status == 304)) {
                    //用来保存返回的数据
                    let newRespose = new Object;
                    newRespose.data = JSON.parse(xhr.responseText);
                    // _this.saveResponse && _this.saveResponse(newRespose)
                    //在返回数据之前调用相应拦截器的回调函数
                    if (_this.saveResponse) {
                        _this.saveResponse.forEach(fn => {
                            fn(newRespose)
                        })
                    }
                    resolve(newRespose.data)
                } else {
                    reject(xhr.responseText)
                }
            }
        };
        xhr.send()
    })
}

//返回一个新的实例并且复制obj的属性
iaxios.prototype.create = function (obj) {
    var emaxios = new iaxios()
    emaxios.headers = obj.headers;
    emaxios.baseUrl = obj.baseUrl;
    return emaxios;
}

//设置请求头的方法
function setHeader(xhr, headers) {
    for (var i in headers) {
        xhr.setRequestHeader(i, headers[i]);
    }
}

const myAxios = new iaxios();
