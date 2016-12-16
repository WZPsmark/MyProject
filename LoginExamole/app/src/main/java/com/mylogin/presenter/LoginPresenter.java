package com.mylogin.presenter;

import com.mylogin.model.IMvpModel;
import com.mylogin.model.LoginModel;
import com.mylogin.view.IMvpView;

/**
 * Created by smark on 2016/12/16.
 */

public class LoginPresenter implements IMvpModel,ICallBack,IMvpPresenter {
    private IMvpModel model;
    private ICallBack viewCallBack;

    public LoginPresenter(ICallBack viewCallBack) {
        model = new LoginModel();
        this.viewCallBack = viewCallBack;
    }

    public void operateView(String obj){
        opearateModel(obj,this);
    }

    @Override
    public void handModel(String obj, ICallBack callBack) {

        model.handModel(obj,callBack);
    }

    @Override
    public void callback(String obj) {
        viewCallBack.callback(obj);
    }

    @Override
    public void opearateModel(String obj, ICallBack callBack) {
        handModel(obj,callBack);

    }
}
