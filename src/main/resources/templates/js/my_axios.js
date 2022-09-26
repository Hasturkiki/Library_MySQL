function Axios() {
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

Axios.prototype.get = function (url) {
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
        let xhr = new XMLHttpRequest();
        xhr.open('get', url, true);
        //设置请求头的配置
        setHeader(xhr, this.headers);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if ((xhr.status === 200 || xhr.status === 304)) {
                    //用来保存返回的数据
                    let newResponse = {};
                    newResponse.data = JSON.parse(xhr.responseText);
                    // _this.saveResponse && _this.saveResponse(newResponse)
                    //在返回数据之前调用相应拦截器的回调函数
                    if (_this.saveResponse) {
                        _this.saveResponse.forEach(fn => {
                            fn(newResponse)
                        })
                    }
                    resolve(newResponse.data)
                } else {
                    reject(xhr.responseText)
                }
            }
        };
        xhr.send()
    })
}

Axios.prototype.post = function (url, data) {
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
        let xhr = new XMLHttpRequest();
        xhr.open('post', url, true);
        //设置请求头的配置
        setHeader(xhr, this.headers);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if ((xhr.status === 200 || xhr.status === 304)) {
                    //用来保存返回的数据
                    let newResponse = {};
                    newResponse.data = JSON.parse(xhr.responseText);
                    // _this.saveResponse && _this.saveResponse(newResponse)
                    //在返回数据之前调用相应拦截器的回调函数
                    if (_this.saveResponse) {
                        _this.saveResponse.forEach(fn => {
                            fn(newResponse)
                        })
                    }
                    resolve(newResponse.data)
                } else {
                    reject(xhr.responseText)
                }
            }
        };
        xhr.send(JSON.stringify(data))
    })
}

Axios.prototype.put = function (url, data) {
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
        let xhr = new XMLHttpRequest();
        xhr.open('put', url, true);
        //设置请求头的配置
        setHeader(xhr, this.headers);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if ((xhr.status === 200 || xhr.status === 304)) {
                    //用来保存返回的数据
                    let newResponse = {};
                    newResponse.data = JSON.parse(xhr.responseText);
                    if (_this.saveResponse) {
                        _this.saveResponse.forEach(fn => {
                            fn(newResponse)
                        })
                    }
                    resolve(newResponse.data)
                } else {
                    reject(xhr.responseText)
                }
            }
        };
        xhr.send(JSON.stringify(data))
    })
}

Axios.prototype.delete = function (url) {
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
        let xhr = new XMLHttpRequest();
        xhr.open('delete', url, true);
        //设置请求头的配置
        setHeader(xhr, this.headers);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if ((xhr.status === 200 || xhr.status === 304)) {
                    //用来保存返回的数据
                    let newResponse = {};
                    newResponse.data = JSON.parse(xhr.responseText);
                    // _this.saveResponse && _this.saveResponse(newResponse)
                    //在返回数据之前调用相应拦截器的回调函数
                    if (_this.saveResponse) {
                        _this.saveResponse.forEach(fn => {
                            fn(newResponse)
                        })
                    }
                    resolve(newResponse.data)
                } else {
                    reject(xhr.responseText)
                }
            }
        };
        xhr.send()
    })
}

//返回一个新的实例并且复制obj的属性
Axios.prototype.create = function (obj) {
    let newAxios = new Axios()
    newAxios.headers = obj.headers;
    newAxios.baseUrl = obj.baseUrl;
    return newAxios;
}

//设置请求头的方法
function setHeader(xhr, headers) {
    for (let i in headers) {
        xhr.setRequestHeader(i, headers[i]);
    }
}

const myAxios = new Axios();
