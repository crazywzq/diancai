package com.diancai.activity;

import java.util.ArrayList;

import com.diancai.adapter.FoodAdapter;
import com.diancai.database.DatabaseService;
import com.diancai.model.Food;
import com.example.yaoyiyaodemo.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ZhuoActivity extends Activity implements OnClickListener{
	private TextView left_btn,top_title,right_btn,huanyihuan,jia_cai;
	private ListView food_list_view;
	private ArrayList<Food> foodlist;
	private int zhuo_total = 10;
	DatabaseService database = new DatabaseService(this);
	FoodAdapter  foodadpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuo);
		initView();
		initEvent();
    }
    void initView()
    {
    	left_btn = (TextView) findViewById(R.id.left_icon);
    	left_btn.setVisibility(View.VISIBLE);
    	top_title = (TextView) findViewById(R.id.top_title);
    	top_title.setText("点菜吧 - 整桌");
    	right_btn = (TextView) findViewById(R.id.right_icon);
    	right_btn.setVisibility(View.GONE);
    	huanyihuan = (TextView) findViewById(R.id.huanyihuan);
    	jia_cai = (TextView) findViewById(R.id.jia_cai);
    	setView();

    }

    void initEvent()
    {
    	left_btn.setOnClickListener(this);
    	right_btn.setOnClickListener(this);
    	huanyihuan.setOnClickListener(this);
    	jia_cai.setOnClickListener(this);
    }
    void setView()
    {
    	foodlist = database.randomZhuo(zhuo_total);
    	
    	food_list_view = (ListView) findViewById(R.id.zhuo_list);
    	
		if(foodadpter==null){
			foodadpter = new FoodAdapter(ZhuoActivity.this, foodlist);
			food_list_view.setAdapter(foodadpter);
		}else{
			foodadpter.setFoodList(foodlist);
			foodadpter.notifyDataSetChanged();
		}    	
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.left_icon:
			finish();
			break;
		case R.id.right_icon:
			Intent intent = new Intent(ZhuoActivity.this,FoodAddActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.huanyihuan:
			foodlist = database.randomZhuo(zhuo_total);
			if(foodadpter==null){
				foodadpter = new FoodAdapter(ZhuoActivity.this, foodlist);
				food_list_view.setAdapter(foodadpter);
			}else{
				foodadpter.setFoodList(foodlist);
				foodadpter.notifyDataSetChanged();
			}
			
			//Toast.makeText(ZhuoActivity.this, "更换成功", Toast.LENGTH_LONG).show();
			break;
		case R.id.jia_cai:
			ArrayList<Food> new_foodlist = new ArrayList<Food>();
			new_foodlist = database.randomFood();
			if(new_foodlist != null && new_foodlist.size() > 0)
			{
				foodlist.add(new_foodlist.get(0));
				if(foodadpter==null){
					foodadpter = new FoodAdapter(ZhuoActivity.this, foodlist);
					food_list_view.setAdapter(foodadpter);
				}else{
					foodadpter.setFoodList(foodlist);
					foodadpter.notifyDataSetChanged();
				}
			}
			//Toast.makeText(ZhuoActivity.this, "加菜成功", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}
}
