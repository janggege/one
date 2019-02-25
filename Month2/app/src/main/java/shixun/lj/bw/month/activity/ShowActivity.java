package shixun.lj.bw.month.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;

import shixun.lj.bw.month.R;
import shixun.lj.bw.month.fragment.Fragment1;
import shixun.lj.bw.month.fragment.Fragment2;
import shixun.lj.bw.month.fragment.Fragment3;
import shixun.lj.bw.month.fragment.Fragment4;
import shixun.lj.bw.month.fragment.Fragment5;

public class ShowActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager pager;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        pager = findViewById(R.id.pager);
        radioGroup = findViewById(R.id.group);
        radioGroup.check(R.id.bt1);
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt5).setOnClickListener(this);
        final ArrayList<Fragment> list = new ArrayList<>();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        Fragment4 fragment4 = new Fragment4();
        Fragment5 fragment5 = new Fragment5();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);
        list.add(fragment5);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.bt1:
                        pager.setCurrentItem(0);

                        break;
                    case R.id.bt2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.bt3:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.bt4:
                        pager.setCurrentItem(3);
                        break;
                    case R.id.bt5:
                        pager.setCurrentItem(4);
                        break;


                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                break;
            case R.id.bt2:
                break;
            case R.id.bt3:
                break;
            case R.id.bt4:
                break;
            case R.id.bt5:
                break;


        }
    }
}
