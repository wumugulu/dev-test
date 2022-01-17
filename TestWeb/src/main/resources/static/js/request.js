function netRequest(actionObj) {
    console.info('netRequest, actionObj = ', actionObj);
    let url = getUrl(actionObj.action);

    // 默认提交方式: json
    let submitObj = {
        method: actionObj.method || 'JSON',
        url: url
    }

    console.info('try to submit post form 0', submitObj, submitObj.method.toLowerCase());
    if ('get' == submitObj.method.toLowerCase() || 'post' == submitObj.method.toLowerCase()) {
        console.info('try to submit post form 1');
        submitObj.params = actionObj.param;
        if ('post' == submitObj.method.toLowerCase()) {
            console.info('try to submit post form 2');
            submitObj.headers = {
                withCredentials: true,
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }
    }
    else if ('json' == submitObj.method.toLowerCase()) {
        submitObj.method = 'post';
        submitObj.data = actionObj.param;
    }
    else
        console.info('method not supported !!!');

    console.info('netRequest, submitObj = ', submitObj);
    return new Promise((resolve, reject) => {
        axios(submitObj)
            .then(ret=> {
                return resolve(ret)
            })
            .catch(err=>{
                return reject(err)
            });
    })
}

function getUrl(actionCmd) {
    var url;
    switch (actionCmd) {
        case "USER_LIST":
            url = '/api/user/list';
            break;
        case "USER_LOCK":
            url = '/api/user/lock';
            break;
        case "USER_DELETE":
            url = '/api/user/delete';
            break;
        case "USER_REG":
            url = '/api/user/reg';
            break;
        case "USER_RECOVERY":
            url = '/api/user/recovery';
            break;
        case "USER_DELETE_BATCH":
            url = '/api/user/deleteBatch';
            break;
        case "MAIL_QUERY":
            url = '/api/mail/query';
            break;
        case "listConfig":
            url = '/zkcenter/api/listConfig';
            break;
        case "add":
            url = '/zkcenter/api/add';
            break;
        case "update":
            url = '/zkcenter/api/update';
            break;
        case "remove":
            url = '/zkcenter/api/remove';
            break;
        default:
            break;
    }
    return url;
}
