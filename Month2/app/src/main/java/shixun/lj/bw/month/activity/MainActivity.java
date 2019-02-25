package shixun.lj.bw.month.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import shixun.lj.bw.month.R;
import shixun.lj.bw.month.presenter.Loginpresenter;
import shixun.lj.bw.month.view.Regutils;

import static shixun.lj.bw.month.R.mipmap.login_icon_eye_n_hdhpi;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Regutils {

    private EditText pwd;
    private EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.zhuce).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
        pwd = findViewById(R.id.pwd);
        name = findViewById(R.id.name);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuce:
                startActivity(new Intent(MainActivity.this, Regactivity.class));
                finish();
                break;
            case R.id.login:
                String phone1 = name.getText().toString();
                String pwd1 = pwd.getText().toString();
                Loginpresenter loginpresenter = new Loginpresenter(MainActivity.this);
                Map<String, String> map = new HashMap<>();
                map.put("phone", phone1);
                map.put("pwd", pwd1);
                loginpresenter.Login(map);
                break;
        }

    }


    //密码可见的犯法
    public void pwdShow(EditText editText, ImageView imageView) {

        int type = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;
        if (editText.getInputType() == type) {//密码可见
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            imageView.setImageDrawable(getResources().getDrawable(R.mipmap.login_icon_eye_n_xhdpi));
            editText.setSelection(editText.getText().length());     //把光标设置到当前文本末尾

        } else {
            editText.setInputType(type);
            imageView.setImageDrawable(getResources().getDrawable(login_icon_eye_n_hdhpi));
            editText.setSelection(editText.getText().length());
        }

    }

    @Override
    public void Regutils(String staues) {
        if (staues.equals("0000")) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ShowActivity.class);
            startActivity(intent);
            finish();
        } else if (staues.equals("1001")) {
            Toast.makeText(this, "手机号或密码错误", Toast.LENGTH_SHORT).show();
        }

    }
}
