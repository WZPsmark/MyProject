package com.mylogin.model;

import com.mylogin.presenter.ICallBack;

import java.util.Random;

/**
 * Created by smark on 2016/12/16.
 */

public class LoginModel extends BaseModel {


    @Override
    public void handModel(String obj, ICallBack callBack) {
        loginAsync(obj, callBack);
    }

    /**
     * 模拟登录
     */
    private void loginAsync(final String obj, final ICallBack onCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int i = new Random().nextInt(1000);

                onCallback.callback(obj + i);
            }
        }).run();
    }
}
