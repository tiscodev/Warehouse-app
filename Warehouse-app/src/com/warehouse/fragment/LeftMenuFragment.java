package com.warehouse.fragment;

import java.util.ArrayList;
import java.util.List;

import com.base.utils.FileUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.warehouse.app.LoginActivity;
import com.warehouse.app.R;
import com.warehouse.constant.AppConstant;
import com.warehouse.dao.LocalDao;
import com.warehouse.entity.TbUser;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class LeftMenuFragment extends BaseFragment {

	private List<String> loginName;

	@Override
	public View initView() {

		loginName = new ArrayList<String>();

		ViewGroup view = (ViewGroup) View.inflate(mActivity, R.layout.fragment_left_menu, null);
		RelativeLayout linear = (RelativeLayout) view.findViewById(R.id.scrollView1);

		LocalDao localDao = new LocalDao(mActivity);
		List<TbUser> tbUserList = localDao.userInfoList();

		ListView listView = (ListView) view.findViewById(R.id.userInfoListView);
		listView.setAdapter(new UserAdapter(mActivity, tbUserList));

		Button button = (Button) linear.findViewById(R.id.button1);
		button.setText(R.string.confirm);
		button.setBackground(this.getResources().getDrawable(R.drawable.button_selector));
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DbUtils db = DbUtils.create(mActivity,FileUtils.getExternalStoragePath(), AppConstant.APP_DB_NORMAL_NAME);
				try {
					db.createTableIfNotExist(TbUser.class);
					db.deleteAll(TbUser.class);
					for (int i = -1; i < loginName.size(); i++) {
						TbUser user = new TbUser();
						if(-1 == i){
							user.setLoginName("请选择常用用户");
						}else{
							user.setLoginName(loginName.get(i));
						}
						db.save(user);
					}

				} catch (DbException e) {
					e.printStackTrace();
				}
				LoginActivity activity = (LoginActivity) mActivity;
				SlidingMenu slidingMenu = activity.getSlidingMenu();
				slidingMenu.toggle();
				
				FragmentManager fm = activity.getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				ft.replace(R.id.fl_content, new ContentFragment());
				ft.commit();
			}
		});
		return view;
	}

	private class UserAdapter extends BaseAdapter {

		private Context context;
		private List<TbUser> list;

		public UserAdapter(Context context, List<TbUser> list) {
			super();
			this.context = context;
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			ViewGroup group = (ViewGroup) inflater.inflate(R.layout.user_info, null);
			CheckBox checkBox = (CheckBox) group.findViewById(R.id.checkBox1);
			checkBox.setText(this.list.get(position).getLoginName());
			checkBox.setTextColor(this.context.getResources().getColor(R.color.black));
			checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					String text = buttonView.getText().toString();
					if (isChecked) {
						loginName.add(text);
					} else {
						if (loginName.contains(text)) {
							loginName.remove(text);
						}
					}
				}
			});
			return group;
		}

	}

}
