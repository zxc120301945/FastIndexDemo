package com.slide.qq.com.fastindex.beans;


import com.slide.qq.com.fastindex.util.PinyinUtil;

public class Person implements Comparable<Person>{

	private String name; // 名字
	private String pinyin; // 拼音
	
	public Person(String name) {
		super();
		this.name = name;
		this.pinyin = PinyinUtil.getPinyin(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	@Override
	public int compareTo(Person another) {
		return this.pinyin.compareTo(another.pinyin);
	}
	
}
