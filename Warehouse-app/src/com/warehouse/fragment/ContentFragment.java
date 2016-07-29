package com.warehouse.fragment;

import java.util.ArrayList;
import java.util.List;

import com.base.utils.FileUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.warehouse.app.LoginActivity;
import com.warehouse.app.MainActivity;
import com.warehouse.app.R;
import com.warehouse.constant.AppConstant;
import com.warehouse.dao.LocalDao;
import com.warehouse.entity.TbUser;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 主页面Fragment Created by Administrator on 2016/2/4 0004.
 */
public class ContentFragment extends BaseFragment {

	private String userNameAll;
	private String userNameLocal;
	private String passWord;
	private LocalDao localDao;

	@Override
	public View initView() {

		localDao = new LocalDao(mActivity);
		
		ViewGroup group = (ViewGroup) View.inflate(mActivity, R.layout.fragment_content, null);

		Button setButton = (Button) group.findViewById(R.id.setting_btn);
		setButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LoginActivity loginActivity = (LoginActivity) mActivity;
				SlidingMenu slidingMenu = loginActivity.getSlidingMenu();
				slidingMenu.toggle();
			}
		});

		List<TbUser> allUserList = localDao.userInfoList();
		final String allUserNameArray[] = new String[allUserList.size() + 1];
		allUserNameArray[0] = "请选择用户";
		for (int i = 0; i < allUserList.size(); i++) {
			allUserNameArray[i + 1] = allUserList.get(i).getLoginName();
		}

		Spinner allUsreSpinner = (Spinner) group.findViewById(R.id.all_user);
		ArrayAdapter<String> allUserSpinnerAdapter = new ArrayAdapter<String>(mActivity,
				android.R.layout.simple_dropdown_item_1line, allUserNameArray);
		allUserSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		allUsreSpinner.setAdapter(allUserSpinnerAdapter);
		allUsreSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				userNameAll = allUserNameArray[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		// 常用用户
		DbUtils db = DbUtils.create(mActivity, FileUtils.getExternalStoragePath(),AppConstant.APP_DB_NORMAL_NAME);
		List<TbUser> userList = new ArrayList<TbUser>();
		int size = 0;
		try {
			userList = db.findAll(TbUser.class);
			if(userList!=null){
				size = userList.size();
			}
		} catch (DbException e) {
			size = 0;
		}

		final String loginNameArray[] = new String[size];
		for (int i = 0; i < size; i++) {
			loginNameArray[i] = userList.get(i).getLoginName();
		}
		Spinner localUserSpinner = (Spinner) group.findViewById(R.id.local_user);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_dropdown_item_1line,
				loginNameArray);
		adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		localUserSpinner.setAdapter(adapter);
		localUserSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				userNameLocal = loginNameArray[position];

			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		// 密码
		final EditText passEdit = (EditText) group.findViewById(R.id.passEditText);

		
		// 登陆
		Button loginBtn = (Button) group.findViewById(R.id.login_btn);
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean result = false;
				passWord = passEdit.getText().toString();
				boolean userFlag = userNameAll.startsWith("请") && userNameLocal.startsWith("请");
				if (passWord.equals("") || userFlag) {
					Toast.makeText(mActivity, "请选择常用用户?", Toast.LENGTH_SHORT).show();
				} else {
					if (!userNameLocal.startsWith("请")) {
						result = localDao.login(userNameLocal, passWord);
					}else{
						result = localDao.login(userNameAll, passWord);
					}
				}
				
				if(result){
					Intent intent = mActivity.getIntent();
					intent.setClass(mActivity, MainActivity.class);
					mActivity.startActivity(intent);
				}else{
					passEdit.setText("");
				}

			}
		});
		
		//重置
		Button resetBtn = (Button) group.findViewById(R.id.reset_btn);
		resetBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				passEdit.setText("");
			}
		});

		return group;
	}
}