package com.harold.base;

import android.widget.Toast;

import com.harold.jstest.MainActivity;

public class JSKit {
	private MainActivity ma;
	
	public JSKit(MainActivity context) {
		this.ma = context;
	}
	
	public void showMsg(String msg) {
		Toast.makeText(ma, msg, Toast.LENGTH_SHORT).show();
	}
}
