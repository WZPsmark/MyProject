package com.mylogin.model;

import com.mylogin.presenter.ICallBack;

/**基础model，相同的逻辑统一处理
 * 不同的逻辑，再由子类实现
 * Created by smark on 2016/12/16.
 */

public class BaseModel implements IMvpModel {
    @Override
    public void handModel(String obj, ICallBack callBack) {

    }
}
