package com.xiaolu.dzsdk.net.msg.msghandle;

import com.xiaolu.dzsdk.base.util.ToastUtils;
import com.xiaolu.dzsdk.base.util.UIRunner;
import com.xiaolu.dzsdk.common.XLData;
import com.xiaolu.dzsdk.common.XLDzSdk;
import com.xiaolu.dzsdk.net.bean.Register;
import com.xiaolu.dzsdk.common.bean.EventRet;

/**
 * Created by Roye on 2018/4/4.
 */

public class RegisterHandle extends AbsHandle<Register> {

    @Override
    public void onSuccess(Register data) {
        XLData.setUid(data.userId);
        XLData.setToken(data.token);
        XLData.setGameId(String.valueOf(data.gameId));

        UIRunner.runOnUI(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast("正在登录..");
            }
        });

        XLDzSdk.getInstance().login();

        EventRet ret = new EventRet();
        ret.data = data;
        XLDzSdk.getCallback().onRegResult(ret);
    }

    @Override
    public void onFail(Register data) {
        EventRet ret = new EventRet();
        ret.result = 0;
        ret.data = data;
        XLDzSdk.getCallback().onRegResult(ret);
    }
}
