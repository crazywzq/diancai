package com.diancai.activity;

import java.util.ArrayList;
import java.util.Random;

import com.diancai.database.DatabaseService;
import com.diancai.model.Food;
import com.diancai.util.ShakeListener;
import com.diancai.util.ShakeListener.OnShakeListener;
import com.example.yaoyiyaodemo.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	 ShakeListener mShakeListener = null;
	 private TextView food_tip,foodname1,foodname2,btn_showlist,left_btn,top_title;
	 private ImageView favorite,favorite_active;
	 private TextView yizhuo,dandian;
	 private ImageView iv;
	 private ArrayList<Food> foodlist;
	 private int count =-2;
	 private TranslateAnimation animation1,animation2;
	 //保存上一个菜名
	 private String last_foodname = "摇一摇";
	 DatabaseService database = new DatabaseService(this);
	 private Handler handler = new Handler(){
	    	public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case 1:
					foodlist = database.randomFood();
					if(foodlist != null && foodlist.size() > 0)
					{
						//foodname.setText("主人，来个“"+foodlist.get(0).getName()+"”怎么样");
						//foodname.setText(foodlist.get(0).getName());
						
						animation2 = new TranslateAnimation(0, 1000, 0, 0); 
						animation2.reset();
		                animation2.setInterpolator(new LinearInterpolator());  
		                animation2.setDuration(500);  
		                animation2.setFillAfter(true);  
		                foodname2.startAnimation(animation2);
		                foodname2.setText(last_foodname);
		                
		                
						animation1 = new TranslateAnimation(-1000, 0, 0, 0); 
						animation1.reset();
		                animation1.setInterpolator(new LinearInterpolator());  
		                animation1.setDuration(500);  
		                animation1.setFillAfter(true); 
		                foodname1.setText(foodlist.get(0).getName());
		                foodname1.startAnimation(animation1);	
		                last_foodname = foodlist.get(0).getName();
		                
					}
					else
					{
						food_tip.setText("主人，菜单被清空了，赶紧去添加吧");
					}
					break;
				default:
					break;
				}   		
	    	}
	 };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 // TODO Auto-generated method stub
		  super.onCreate(savedInstanceState);
		  this.setContentView(R.layout.main);
		  initView();
		  initEvent();
    }
	
	void initView()
	{
		 foodname1=(TextView)this.findViewById(R.id.foodname1);
		 foodname2=(TextView)this.findViewById(R.id.foodname2);
		  food_tip = (TextView) findViewById(R.id.food_tip);
		  left_btn = (TextView) findViewById(R.id.left_icon);
		  left_btn.setVisibility(View.GONE);
		  top_title = (TextView) findViewById(R.id.top_title);
		  top_title.setText("点菜吧");
		  btn_showlist = (TextView) findViewById(R.id.btn_showlist);
		  favorite = (ImageView) findViewById(R.id.favorite);
		  favorite_active = (ImageView) findViewById(R.id.favorite_avtive);
		  yizhuo = (TextView) findViewById(R.id.yizhuo);
		  dandian = (TextView) findViewById(R.id.dandian);
		
	}
	
	void initEvent()
	{
		favorite.setOnClickListener(this);
		  yizhuo.setOnClickListener(this);
		  dandian.setOnClickListener(this);
		  btn_showlist.setOnClickListener(this);
		  //mShakeListener = new ShakeListener(this);
		  //mShakeListener.setOnShakeListener(new shakeLitener());		
	}
	protected void onRestart()
	{
		super.onRestart();
		//Log.v("restart", "restart");
		//Toast.makeText(MainActivity.this, "onrestart", Toast.LENGTH_LONG).show();
		//mShakeListener.start();
	}
	protected void onStop()
	{
		super.onStop();
		//Log.v("stop", "stop");
		//Toast.makeText(MainActivity.this, "onstop", Toast.LENGTH_LONG).show();
		//mShakeListener.stop();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.favorite:
			
			favorite.setImageDrawable(getResources().getDrawable(R.drawable.favorite_80));
			//Toast.makeText(MainActivity.this, "喜欢了", Toast.LENGTH_LONG).show();
			//TranslateAnimation animation = new TranslateAnimation(0, 100, 0, -1000); 
			//获取点击的item的坐标
			int[] location = new int[2];
			v.getLocationOnScreen(location);
			int favorite_x = location[0];
			int favorite_y = location[1]-v.getHeight()/2;
			
			int[] location_to = new int[2];
			btn_showlist.getLocationOnScreen(location_to);
			int collect_x = location_to[0]+10;
			int collect_y = location_to[1]-btn_showlist.getHeight()/2;
			//Log.v("test", msg)
			TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, favorite_x, Animation.ABSOLUTE, collect_x, Animation.ABSOLUTE, favorite_y, Animation.ABSOLUTE, collect_y);
			animation.reset();
            animation.setInterpolator(new LinearInterpolator());
            //animation.setInterpolator(new BounceInterpolator());
            animation.setDuration(300);  
            //animation.setFillAfter(true);  
            favorite_active.setVisibility(View.VISIBLE);
            animation.setAnimationListener(new AnimationListener() {				
				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub					
				}			
				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub		
				}
				@Override
				public void onAnimationEnd(Animation animation) {
					// TODO Auto-generated method stub
					favorite_active.setVisibility(View.INVISIBLE);
				}
			});           
            favorite_active.startAnimation(animation);
			break;
		case R.id.yizhuo:
			Intent intentZhuo = new Intent(MainActivity.this,ZhuoActivity.class);
			startActivity(intentZhuo);
			break;			
		case R.id.dandian:
			handler.sendEmptyMessage(1);
			break;
		case R.id.btn_showlist:
			Intent intent = new Intent(MainActivity.this,FoodListActivity.class);
			startActivity(intent);
			break;		
		default:
			break;
		}
		
	}
	
	private class shakeLitener implements OnShakeListener{
		  @Override
		  public void onShake() {
		   // TODO Auto-generated method stub
		   //tv.setText("摇一摇成功啦！");
			  //Toast.makeText(MainActivity.this, "别晃我了", Toast.LENGTH_LONG).show();
			  handler.sendEmptyMessage(1);
		   //iv.setImageResource(R.drawable.attitude_laugh);
		   //mShakeListener.stop();
		  }
		  
	}


}
