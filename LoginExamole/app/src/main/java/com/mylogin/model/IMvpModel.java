package com.mylogin.model;

import com.mylogin.presenter.ICallBack;

/**
 * Created by smark on 2016/12/16.
 */

public interface IMvpModel {
    void handModel(String obj, ICallBack callBack);
}
