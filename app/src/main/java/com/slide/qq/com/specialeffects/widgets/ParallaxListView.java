package com.slide.qq.com.specialeffects.widgets;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * 包含视差特效的ListView
 * @author poplar
 *
 */
public class ParallaxListView extends ListView {

	private ImageView iv_image; 
	private int orignalHeight;  
	private int drawableHeight; 

	public ParallaxListView(Context context) {
		super(context);
	}

	public ParallaxListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ParallaxListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setParallaxImage(ImageView iv_image) {
		this.iv_image = iv_image;

		// 获取原始的高度
		orignalHeight = iv_image.getHeight(); // 160
		
		// 获取图片的高度
		drawableHeight = iv_image.getDrawable().getIntrinsicHeight();
	}
	

	// ListView滑动到两端时候才会被调用
	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, 
			int scrollX, int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		
		System.out.println("deltaY: " + deltaY + " scrollY: " + scrollY 
				+ " scrollRangeY: " + scrollRangeY
				+ " maxOverScrollY: " + maxOverScrollY 
				+ " isTouchEvent: " + isTouchEvent);
		
		// deltaY : 竖直方向的[瞬时偏移量] , 顶部下拉-, 底部上拉+
		// scrollY : 竖直方向的滚动距离, 顶部-, 底部+
		// scrollRangeY : 竖直方向的滚动范围
		// maxOverScrollY : 最大超出滚动距离
		// isTouchEvent : 是否是手指触摸到两端, true 触摸, false 惯性
		
		// 顶部下拉, 并且是用户手指触摸
		if(deltaY < 0 && isTouchEvent){
			// 放大头部, y轴 [瞬时偏移量] 的绝对值 累加给Header, 使其高度增加
			int newHeight = (int) (iv_image.getHeight() + Math.abs(deltaY) / 2.0f);
			
			if(newHeight <= drawableHeight){
				// 让新的高度生效
				setImageHeight(newHeight);
			}
		}
		
		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
				scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
	}

	private void setImageHeight(int newHeight) {
		iv_image.getLayoutParams().height = newHeight;
		iv_image.requestLayout();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		
		if(ev.getAction() == MotionEvent.ACTION_UP){
			
			// 把Header当前的高度iv_image.getHeight()设置回原始高度orignalHeight: 160
//			iv_image.setTranslationX(translationX)
//			ObjectAnimator.ofFloat(iv_image, "translationX", 100f);
			ValueAnimator animator = ValueAnimator.ofInt(iv_image.getHeight(), orignalHeight);
			
			animator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
//					float animatedFraction = animation.getAnimatedFraction();
					Integer newHeight = (Integer) animation.getAnimatedValue();
					
					// 让新的高度生效
					setImageHeight(newHeight);
				}
			});
			animator.setInterpolator(new OvershootInterpolator(3));
			
			animator.setDuration(500);
			animator.start();
			
		}
		
		return super.onTouchEvent(ev);
	}

}



