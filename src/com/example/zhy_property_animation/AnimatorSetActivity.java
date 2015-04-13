package com.example.zhy_property_animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class AnimatorSetActivity extends Activity
{
	private ImageView mBlueBall;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anim_set);

		mBlueBall = (ImageView) findViewById(R.id.id_ball);

	}

	public void togetherRun(View view)
	{
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX",
				1.0f, 2f);
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY",
				1.0f, 2f);
		
		AnimatorSet animSet = new AnimatorSet();
		animSet.setDuration(2000);
		animSet.setInterpolator(new BounceInterpolator());
		//两个动画同时执行
		animSet.playTogether(anim1, anim2);
//		animSet.playSequentially(items)
		animSet.start();
	}

	public void playWithAfter(View view)
	{
		float cx = mBlueBall.getX();
		LogUtils.i("cx--"+cx);

		ObjectAnimator anim1 = ObjectAnimator.ofFloat(mBlueBall, "scaleX",
				1.0f, 2f);
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(mBlueBall, "scaleY",
				1.0f, 2f);
		ObjectAnimator anim3 = ObjectAnimator.ofFloat(mBlueBall,
				"x",  cx ,  0f);
		ObjectAnimator anim4 = ObjectAnimator.ofFloat(mBlueBall,
				"x", cx);
		
		////
		PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
				0f, 1f);
		PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
				0, 1f);
		PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
				0, 1f);
		ObjectAnimator anim5 = ObjectAnimator.ofPropertyValuesHolder(mBlueBall, pvhX, pvhY, pvhZ);
		////
		
		PropertyValuesHolder pvhX2 = PropertyValuesHolder.ofFloat("alpha", 1f,
				0f, 1f);
		PropertyValuesHolder pvhY2 = PropertyValuesHolder.ofFloat("scaleX", 1f,
				0, 1f);
		PropertyValuesHolder pvhZ2 = PropertyValuesHolder.ofFloat("scaleY", 1f,
				0, 1f);
		ObjectAnimator anim6 = ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ);
		
		/**
		 * anim1，anim2,anim3同时执行
		 * anim4接着执行
		 */
		AnimatorSet animSet = new AnimatorSet();
		animSet.play(anim1).with(anim2);
		animSet.play(anim2).with(anim3);
		animSet.play(anim4).after(anim3);
		animSet.play(anim5).after(anim4);
		animSet.play(anim6).after(anim4);
		animSet.setDuration(1000);
		animSet.start();
	}
}
