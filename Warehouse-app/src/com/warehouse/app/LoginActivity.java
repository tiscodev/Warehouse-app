package com.warehouse.app;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.base.utils.FileUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.warehouse.constant.AppConstant;
import com.warehouse.fragment.ContentFragment;
import com.warehouse.fragment.LeftMenuFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class LoginActivity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setBehindContentView(R.layout.left_menu);
		dataBase();
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setBehindOffset(100);
		initFragment();
	}
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.fl_left_menu, new LeftMenuFragment());
		ft.replace(R.id.fl_content, new ContentFragment());
		ft.commit();
	}

	// 关于数据库文件的处理
	private void dataBase() {
		String external = FileUtils.getExternalStoragePath();
		String dataFileName = external + AppConstant.APP_DB_LOC + AppConstant.APP_DB_NAME;
		File f = new File(dataFileName);
		if (!f.exists()) {
			try {
				InputStream is = LoginActivity.this.getAssets().open(AppConstant.APP_DB_NAME);
				FileUtils.createDirs(external + AppConstant.APP_DB_LOC);
				File destFileInfoDB3 = new File(
						external + AppConstant.APP_DB_LOC + "/" + AppConstant.APP_DB_NAME);
				FileUtils.copyFile(is, destFileInfoDB3, false);
			} catch (IOException ie) {
			}

		}

	}

}
