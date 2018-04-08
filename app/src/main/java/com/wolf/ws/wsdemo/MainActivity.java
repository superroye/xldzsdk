package com.wolf.ws.wsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xiaolu.dzsdk.base.util.L;
import com.xiaolu.dzsdk.net.bean.RoomRst;
import com.xiaolu.dzsdk.sdk.XLDzApi;
import com.xiaolu.dzsdk.common.bean.EventRet;
import com.xiaolu.dzsdk.sdk.listener.ISysEvent;
import com.xiaolu.dzsdk.sdk.listener.IXLCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        XLDzApi.setDebug();
        XLDzApi.setTest();

        /**
         * 基本动作回调
         */
        XLDzApi.setCallback(new IXLCallback() {
            @Override
            public void onLoginResult(EventRet ret) {
                L.i("onLoginResult " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRegResult(EventRet ret) {
                L.i("onRegResult " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRoomCreate(EventRet<RoomRst> ret) {
                L.i("onRoomCreate " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, String.format("创建房间，房号 %s", String.valueOf(ret.data.roomId)), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "创建房间失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRoomEcho(EventRet ret) {
                L.i("onRoomEcho " + ret.data);

            }

            @Override
            public void onRoomLimit(EventRet ret) {
                L.i("onRoomLimit " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, "房间已满", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRoomTick(EventRet ret) {
                L.i("onRoomTick " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, "某人成功被踢", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, String.valueOf(ret.data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRoomRefresh(EventRet ret) {
                L.i("onRoomRefresh " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, "更新房间", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRoomTicked(EventRet ret) {
                L.i("onRoomTicked " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, "你已被踢", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRoomLeave(EventRet ret) {
                L.i("onRoomLeave " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, "你已下线", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, String.valueOf(ret.data), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRoomUserOnline(EventRet ret) {
                L.i("onRoomUserOnline " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, "某人上线了", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onRoomJoin(EventRet ret) {
                L.i("onRoomJoin " + ret.data);
                if (ret.result == 1) {
                    Toast.makeText(MainActivity.this, "你已进入房间", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, String.valueOf(ret.data), Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * 系统动作回调（非必须实现）
         */
        XLDzApi.setSysCallback(new ISysEvent() {
            @Override
            public void onConnected(String desc) {

            }

            @Override
            public void onDisconnected(String desc) {

            }

            @Override
            public void onError(String desc) {

            }
        });

        XLDzApi.exitRoom();

    }

    void login(View view) {
        XLDzApi.login();
    }

    void register(View view) {
        XLDzApi.register();
    }

    void create(View view) {
        XLDzApi.openRoom(null);
    }

    void join(View view) {
        XLDzApi.enterRoom(String.valueOf(1000), "");
    }

    void exit(View view) {
        XLDzApi.exitRoom();
    }

    void tick(View view) {
        XLDzApi.tickRoom(0L);
    }

    void stopJoin(View view) {
        XLDzApi.stopJoinRoom();
    }

    void rejoin(View view) {
        XLDzApi.rejoinRoom(); 
    }

    void echo(View view) {
        XLDzApi.roomEcho("haha " + new java.util.Random());
    }
}
