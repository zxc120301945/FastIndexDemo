package com.slide.qq.com.swipelayout.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.slide.qq.com.R;
import com.slide.qq.com.swipelayout.data.Cheeses;
import com.slide.qq.com.swipelayout.util.Utils;
import com.slide.qq.com.swipelayout.widgets.SwipeLayout;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<SwipeLayout> openedItems = new ArrayList<SwipeLayout>();

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Cheeses.NAMES.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_list_person2, null);
        } else {
            view = convertView;
        }

        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_name.setText(Cheeses.NAMES[position]);

        SwipeLayout sl = (SwipeLayout) view;

        sl.setOnSwipeListener(new MySwipeListener());

        view.findViewById(R.id.tvCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.showToast(view.getContext(), "点击" + Cheeses.NAMES[position] + "Call");
            }
        });

        view.findViewById(R.id.tvDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.showToast(view.getContext(), "点击" + Cheeses.NAMES[position] + "Delete");
            }
        });

        return view;
    }

    class MySwipeListener implements SwipeLayout.OnSwipeListener {

        @Override
        public void onStartOpen(SwipeLayout layout) {
            closeAllItem();

        }

        @Override
        public void onStartClose(SwipeLayout layout) {

        }

        @Override
        public void onOpen(SwipeLayout layout) {
            openedItems.add(layout);

        }

        @Override
        public void onClose(SwipeLayout layout) {
            openedItems.remove(layout);
        }
    }

    public void closeAllItem() {
        for (int i = 0; i < openedItems.size(); i++) {
            openedItems.get(i).close();
        }
        openedItems.clear();
    }

}
