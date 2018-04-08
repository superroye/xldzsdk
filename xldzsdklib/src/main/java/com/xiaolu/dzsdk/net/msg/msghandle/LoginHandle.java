package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.net.bean.Login;
import com.xiaolu.dzsdk.common.bean.EventRet;

/**
 * Created by Roye on 2018/4/4.
 */

public class LoginHandle extends AbsHandle<Login> {
    
    @Override
    public void onSuccess(Login data) {
        EventRet ret = new EventRet();
        ret.data = data;
        XLDzSdk.getCallback().onLoginResult(ret);
    }

    @Override
    public void onFail(Login data) {
        EventRet ret = new EventRet();
        ret.data = data;
        ret.result = 0;
        XLDzSdk.getCallback().onLoginResult(ret);
    }
}
