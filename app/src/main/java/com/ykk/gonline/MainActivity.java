package com.ykk.gonline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_01)
    Button btn01;
    @BindView(R.id.btn_02)
    Button btn02;
    @BindView(R.id.tv_val)
    TextView tvVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        get();
    }

    @OnClick({R.id.btn_01, R.id.btn_02})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_01:
                tvVal.setText("我是一");

                break;
            case R.id.btn_02:
                tvVal.setText("我是二");

                break;
        }
    }

    public void get() {
        OkHttpClient client = new OkHttpClient();
        //构造Request对象 //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url("http://183.62.46.37:8008/api/t_stock").build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                Log.e("EEEEEEEE", responseStr);
            }
        });
    }


}
