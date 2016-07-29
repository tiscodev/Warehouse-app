package com.warehouse.dao;

import java.util.ArrayList;
import java.util.List;

import com.warehouse.entity.TbUser;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import android.content.Context;
import android.database.Cursor;

public class LocalDao {

	private DbUtils dataBase;

	public LocalDao(Context context) {
		super();
		dataBase = LocalDataBase.getInstance(context);
	}
	
	public List<TbUser> userInfoList(){
		List<TbUser> userInfo = new ArrayList<TbUser>();
		try {
			Cursor cursor = dataBase.execQuery("select C_USERID,C_USERNAME,C_LOGINNAME,C_PASSWORD,C_DEPTID from tb_user order by C_LOGINNAME");
			int columnCount = cursor.getColumnCount();
			String tbUserInfo[]  = new String[5];
			while(cursor.moveToNext()){
				TbUser tbUser = new TbUser();
				for(int i=0;i<columnCount;i++){
					tbUserInfo[i] = cursor.getString(i);
				}
				tbUser.setUserId(tbUserInfo[0]);
				tbUser.setCuserName(tbUserInfo[1]);
				tbUser.setLoginName(tbUserInfo[2]);
				tbUser.setPassword(tbUserInfo[3]);
				tbUser.setDeptId(tbUserInfo[4]);
				userInfo.add(tbUser);
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
		return userInfo;
	}
	
	public boolean login(String userName,String passWord){
		boolean result = false;
		try {
			Cursor cursor = dataBase.execQuery("select * from tb_user where c_loginname = '"+userName+"' and c_password = '"+passWord+"' ");
			result = cursor.moveToNext();
		} catch (DbException e) {
			e.printStackTrace();
		}
		return result;
	}

}
