package shixun.lj.bw.month.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import shixun.lj.bw.month.R;
import shixun.lj.bw.month.presenter.Loginpresenter;
import shixun.lj.bw.month.presenter.Regpresenter;
import shixun.lj.bw.month.utils.Utils;
import shixun.lj.bw.month.view.Regutils;

public class Regactivity extends AppCompatActivity implements Regutils {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regactivity);
        final EditText zname = findViewById(R.id.zname);
        final EditText zpwd = findViewById(R.id.zpwd);
        Button zhuce = findViewById(R.id.reg);
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone1 = zname.getText().toString();
                String pwd1 = zpwd.getText().toString();
                if (!Utils.isMobileNO(phone1)) {
                    Toast.makeText(Regactivity.this, "手机号格式不对", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (pwd1.length() < 4) {
                    Toast.makeText(Regactivity.this, "密码不能小于4位", Toast.LENGTH_SHORT).show();
                    return;
                }

                Regpresenter regpresenter = new Regpresenter(Regactivity.this);
                Map<String, String> map = new HashMap<>();
                map.put("phone", phone1);
                map.put("pwd", pwd1);
                regpresenter.reg(map);


            }
        });
    }


    @Override
    public void Regutils(String staues) {
        if (staues.equals("0000")) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Regactivity.this, MainActivity.class);
            startActivity(intent);

        } else if (staues.equals("1001")) {
            Toast.makeText(this, "该手机号已注册，不能重复注册！", Toast.LENGTH_SHORT).show();
        }

    }
}
