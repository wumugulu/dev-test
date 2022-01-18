
## api 接口文档

### user模块

* 用户列表: /user/list?isDeleted=?
```shell
method: get
params: isDeleted=1
说明: (isDeleted可选值:0/1/null)
```

* 锁定/解锁用户: /user/lock
```shell
method: post
content-type: application/json
body: {
  id: 123, (必填)
  status: 0 (必填,可选值(0/1))
}
说明: (id:必填, status必填[可选值:0/1])
```

* 删除用户: /user/delete
```shell
method: post
type: form
params: id=345
说明: (id必填)
```

* 恢复删除用户: /user/recovery
```shell
method: post
type: form
params: id=456
说明: id必填
```

* 批量删除用户: /user/deleteBatch
```shell
method: post
type: form
params: ids=3,4,5
说明: ids必填
```

* 编辑用户: /user/update
```shell
method: post
content-type: application/json
body: {
  id: 1,
  password: "newpass",
  status: 1,
  isDeleted: 0,
  lastLogin: "2022-01-11 23:34:45",
  loginTimes: 88
}
说明: id必填
```

* 新用户注册: /user/reg
```shell
method: post
content-type: application/type
body: {
  email: "xxxxxx@yyyy.com",
  username: "iamnewuser"
}
说明: emai必填, username必填
```


### mail模块

* 新用户发送邮件: /mail/send
```shell
method: post
content-type: applicaiton/json
body: {
  id: 11,
  email: "xxxxxx@sina.com",
  username: "yyyyyyyy",
  
}
```

* 邮件历史搜索: /mail/query
```shell
method: post
parmas: keyword=sina
说明: keyword为搜索关键字,会和email地址进行模糊匹配
```
