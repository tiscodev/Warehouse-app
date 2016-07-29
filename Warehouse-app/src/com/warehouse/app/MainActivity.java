package com.warehouse.app;

import com.base.app.BaseActivity;
import com.base.app.BaseApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;

import android.view.KeyEvent;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

	@Override
	public void initView() {
		ViewUtils.inject(MainActivity.this);
	}

	@Override
	public void init() {
		BaseApplication.getInstance().addActivity(MainActivity.this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		BaseApplication.getInstance().exit();
		return false;
	}
	
	
	
	
}
