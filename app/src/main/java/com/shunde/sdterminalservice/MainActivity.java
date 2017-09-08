package com.shunde.sdterminalservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(R.id.button)
    Button   mButton;
    @BindView(R.id.editText2)
    EditText mEditText2;
    @BindView(R.id.button2)
    Button   mButton2;
    private EventHandler eventHandler;
    private String phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        // 处理你自己的逻辑
                        Log.e(TAG, "afterEvent: 获取验证码成功");
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功;
                        Log.e(TAG, "afterEvent: 提交验证码成功");
                    }
                }
            }
        };
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @OnClick({R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button://点击获取验证码

                phonenumber = mEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(phonenumber)) {
                    SMSSDK.getVerificationCode("86", phonenumber);//获取短信
                    //SMSSDK.getVoiceVerifyCode("86", phonenumber);

                }else {
                    Toast.makeText(MainActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();

                }
                break;

            case R.id.button2:  //点击提交验证码
                String number = mEditText2.getText().toString().trim();
                if (!TextUtils.isEmpty(number)) {
                    SMSSDK.submitVerificationCode("86", phonenumber,number);//验证短信

                }else {
                    Toast.makeText(MainActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }
}
