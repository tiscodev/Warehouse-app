package com.warehouse.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基类BaseFragment,�?有Fragment继承此类 1.定义Activity常量，方便自类使�? 2.定义抽象方法initView()
 * 初始化布�?，必须实�? 3.定义方法initData()，初始化数据，可以不实现 Created by Administrator on 2016/2/4
 * 0004.
 */
public abstract class BaseFragment extends Fragment {
	public Activity mActivity;

	// fragment创建
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	// 处理fragment的布�?
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = initView();
		return view;
	}

	/**
	 * 初始化布�?
	 * 
	 * @return
	 */
	public abstract View initView();

	/**
	 * Fragment�?依赖的Activity创建完成
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	/**
	 * 初始化数�?
	 */
	private void initData() {

	}

}
