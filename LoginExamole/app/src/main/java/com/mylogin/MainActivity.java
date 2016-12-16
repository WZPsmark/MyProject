package com.mylogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mylogin.presenter.ICallBack;
import com.mylogin.presenter.LoginPresenter;

/**
 * 简单MVC架构登录例子
 */
public class MainActivity extends AppCompatActivity implements ICallBack,View.OnClickListener{

    EditText mNameEt;
    TextView mPsdTv;
    Button email_sign_in_button;
    private LoginPresenter mPrsenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPsdTv = (TextView) findViewById(R.id.password);
        mNameEt = (EditText) findViewById(R.id.name);
        email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
        mPrsenter = new LoginPresenter(this);
        email_sign_in_button.setOnClickListener(this);
    }

    @Override
    public void callback(String obj) {
        mPsdTv.setText("密码：" + obj);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.email_sign_in_button:
                String obj = mNameEt.getText().toString();
                mPrsenter.operateView(obj);
                break;
        }


    }
}
