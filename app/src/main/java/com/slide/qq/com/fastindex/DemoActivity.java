package com.slide.qq.com.fastindex;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.slide.qq.com.R;
import com.slide.qq.com.fastindex.adapter.MyAdapter;
import com.slide.qq.com.fastindex.beans.Person;
import com.slide.qq.com.fastindex.data.Cheeses;
import com.slide.qq.com.fastindex.widgets.QuickIndexBar;

import java.util.ArrayList;
import java.util.Collections;

public class DemoActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayList<Person> persons;
    private TextView tv_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_demo);

        tv_center = (TextView) findViewById(R.id.tv_center);

        QuickIndexBar qib = (QuickIndexBar) findViewById(R.id.qib);

        qib.setOnLetterUpdateListener(new QuickIndexBar.OnLetterUpdateListener() {

            @Override
            public void onLetterUpdate(String letter) {
//				Utils.showToast(MainActivity.this, letter);
                showLetter(letter);

                for (int i = 0; i < persons.size(); i++) {
                    Person person = persons.get(i);
                    String l = person.getPinyin().charAt(0) + "";
                    if(TextUtils.equals(letter, l)){
                        lv.setSelection(i);
                        break;
                    }
                }
            }
        });

        lv = (ListView) findViewById(R.id.lv);

        persons = new ArrayList<Person>();

        fillAndSortData(persons);

        lv.setAdapter(new MyAdapter(persons));
    }

    private Handler mHandler = new Handler();

    // 显示字母
    protected void showLetter(String letter) {
        tv_center.setText(letter);
        tv_center.setVisibility(View.VISIBLE);

        // 已经有延时隐藏操作 取消掉.
        mHandler.removeCallbacksAndMessages(null);
        // 再往后延时2秒
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                tv_center.setVisibility(View.GONE);
            }
        }, 2000);

    }

    /**
     * 填充并给数据排序
     * @param persons
     */
    private void fillAndSortData(ArrayList<Person> persons) {
        // 填充数据
        for (int i = 0; i < Cheeses.NAMES.length; i++) {
            persons.add(new Person(Cheeses.NAMES[i]));
        }

        // 排序数据
        Collections.sort(persons);

    }
}
