package com.wumugulu.devtest.common.utils;

import cn.hutool.core.lang.Dict;

import java.io.Serializable;

public class DictUtil extends Dict implements Serializable {

    private final static String KEY_CODE = "code";
    private final static String KEY_MSG = "msg";
    private final static String KEY_DATA = "data";

    private final static Integer SUCCESS_CODE = 0;
    private final static String SUCCESS_MSG = "success";

    public static Dict error(Integer code, String msg) {
        if (code == SUCCESS_CODE)
            throw new RuntimeException("error code can not be 0");
        return Dict.create().set(KEY_CODE, code).set(KEY_MSG, msg);
    }

    public static Dict success() {
        return Dict.create().set(KEY_CODE, SUCCESS_CODE).set(KEY_MSG, SUCCESS_MSG);
    }

    public static Dict success(Object obj) {
        return success().set(KEY_DATA, obj);
    }

    @Override
    public Dict set(String key, Object value) {
        if (KEY_CODE.equals(key) || KEY_MSG.equals(key) || KEY_DATA.equals(key))
            throw new RuntimeException("invalid key name: " + key);

        return super.set(key, value);
    }

    @Override
    public Object put(String key, Object value) {
        if (KEY_CODE.equals(key) || KEY_MSG.equals(key) || KEY_DATA.equals(key)) {
            throw new RuntimeException("invalid key name: " + key);
        }
        return super.put(key, value);
    }

//    public Dict put(String key, Object value) {
//        if (KEY_CODE.equals(key) || KEY_MSG.equals(key) || KEY_CODE.equals(key)) {
//            throw new RuntimeException("invalid key name: " + key);
//        }
//        set(key, value);
//        return this;
//    }

}
