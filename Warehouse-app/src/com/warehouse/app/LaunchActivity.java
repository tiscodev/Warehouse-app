package com.warehouse.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_launch);
		View target = findViewById(R.id.iv_bg);
		ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, "scaleX", 0.4f,1.0f);
		objectAnimator.setDuration(2000);
		objectAnimator.start();
		objectAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				startActivity(new Intent(LaunchActivity.this,LoginActivity.class));
				finish();
			}
		});
	}

}
