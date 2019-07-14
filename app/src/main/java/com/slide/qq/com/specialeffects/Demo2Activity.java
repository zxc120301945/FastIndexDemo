package com.slide.qq.com.specialeffects;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.slide.qq.com.R;
import com.slide.qq.com.specialeffects.data.Cheeses;
import com.slide.qq.com.specialeffects.widgets.ParallaxListView;

/**
 * Created by hero on 2019/7/14.
 */

public class Demo2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);

        final ParallaxListView plv = (ParallaxListView) findViewById(R.id.plv);

        View mHeaderView = View.inflate(this, R.layout.layout_list_header, null);
        final ImageView iv_image = (ImageView) mHeaderView.findViewById(R.id.iv_image);
        // 添加头布局
        plv.addHeaderView(mHeaderView);


        // 获取Observer添加监听
        iv_image.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // 此时可以获取宽高, 已经被添加到界面上了
                // 把ImageView设置到ListView里
                plv.setParallaxImage(iv_image);
                iv_image.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        plv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Cheeses.NAMES));


    }


}