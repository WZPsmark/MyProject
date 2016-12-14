package com.voleypro;

import java.io.InputStream;

/**
 * Created by smark on 2016/12/14.
 */

public interface IJsonListener<M> {
    void onSuccess(InputStream inputStream);
    void onError();
}
