package com.voleypro;

/**
 * Created by smark on 2016/12/14.
 */

public interface IHttpService {

    void setUrl(String url);

    void setRequesData(byte[] requesData);

    void excute();

    void setHttpCallBack(IHttpListener httpListener);


}
