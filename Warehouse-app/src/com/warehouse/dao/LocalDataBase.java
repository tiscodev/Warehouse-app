package com.warehouse.dao;

import com.base.utils.FileUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DaoConfig;

import com.warehouse.constant.AppConstant;

import android.content.Context;

public class LocalDataBase {
	
	
	private static DbUtils dbInstance;
	
	
	private LocalDataBase(Context context){		
		String dataFileName = FileUtils.getExternalStoragePath() + AppConstant.APP_DB_LOC ;
		DaoConfig daoConfig = new DaoConfig(context);
		daoConfig.setDbDir(dataFileName);
		daoConfig.setDbName(AppConstant.APP_DB_NAME);
		daoConfig.setDbVersion(1);
		dbInstance = DbUtils.create(daoConfig);
	}
	public static DbUtils getInstance(Context context){
		if(null == dbInstance){
			new LocalDataBase(context);
		}
		return dbInstance;
	}
	
}
