package com.slide.qq.com.fastindex.adapter;

import java.util.ArrayList;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.slide.qq.com.R;
import com.slide.qq.com.fastindex.beans.Person;


public class MyAdapter extends BaseAdapter {

	private final ArrayList<Person> persons;

	public MyAdapter(ArrayList<Person> persons) {
		this.persons = persons;
	}

	@Override
	public int getCount() {
		return persons.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view;
		if(convertView == null){
			view = View.inflate(parent.getContext(), R.layout.item_list_person, null);
		}else {
			view = convertView;
		}
		
		TextView tv_index = (TextView) view.findViewById(R.id.tv_index);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
		
		Person person = persons.get(position);
		String currentIndexStr = person.getPinyin().charAt(0) + "";
		
		// 分组
		String indexStr = null;
		if(position == 0){
			// 第一个条目显示
			indexStr = currentIndexStr;
		}else {
			// 判断当前首字母和上一个首字母是否一致, 不一致
			String lastIndexStr = persons.get(position - 1).getPinyin().charAt(0) + "";
			if(!TextUtils.equals(lastIndexStr, currentIndexStr)){
				indexStr = currentIndexStr;
			}
		}
		
		tv_index.setVisibility(indexStr == null ? View.GONE : View.VISIBLE);
		tv_index.setText(indexStr);
		tv_name.setText(person.getName());
		
		return view;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

}
