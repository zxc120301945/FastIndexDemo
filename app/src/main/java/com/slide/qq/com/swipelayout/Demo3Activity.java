package com.slide.qq.com.swipelayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import com.slide.qq.com.R;
import com.slide.qq.com.swipelayout.adapter.MyAdapter;

/**
 * Created by hero on 2019/7/14.
 */

public class Demo3Activity extends Activity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);

        lv = (ListView) findViewById(R.id.lv);

        final MyAdapter adapter = new MyAdapter();
        lv.setAdapter(adapter);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            //	        public static int SCROLL_STATE_IDLE = 0;
//	        public static int SCROLL_STATE_TOUCH_SCROLL = 1;
//	        public static int SCROLL_STATE_FLING = 2;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub
                if(scrollState == SCROLL_STATE_TOUCH_SCROLL){
                    adapter.closeAllItem();
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });

    }


}
