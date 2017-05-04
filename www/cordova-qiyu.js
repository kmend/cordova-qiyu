let exec = require('cordova/exec');

let QiYu = {
    openQiYu: function(params = {}, onSuccess, onError){
    
    console.log( "openQiYu===============>" );


    let defineUserInfo = [];
    defineUserInfo.push({"key":"real_name", "value":"博博土豪"});
    defineUserInfo.push({"key":"mobile_phone", "hidden":true});
    //defineUserInfo.push({"key":"email", "value":"admin@bobol.com"});
    defineUserInfo.push({"index":0, "key":"sex", "label":"手机号", "value":"188888888"});

    params['userId'] = params['UserId'] || "";
    params['Url'] = params['Url'] || "";
    params['Data'] = params['Data'] || defineUserInfo;
    params['UrlTitle'] = params['UrlTitle'] || "";
    params['UrlDesc'] = params['UrlDesc'] || "";
    params['Title'] = params['Title'] || "博博客服";
    exec(onSuccess, onError, 'QiYuPlugin', 'openQiYu', [params]);

    }
};

module.exports = QiYu;

/*
cordova plugins rm cordova-qiyu
cordova plugins add /words/plugins/cordova-qiyu 
*/