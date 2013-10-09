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

public class FoodListActivity extends Activity implements OnClickListener{

	private ListView food_list_view;
	private ArrayList<Food> foodlist;
	private TextView left_btn,top_title,right_btn;
	DatabaseService database = new DatabaseService(this);
	FoodAdapter  foodadpter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foodlist_view);
		initView();
		initEvent();
	}

    void initView()
    {
    	left_btn = (TextView) findViewById(R.id.left_icon);
    	left_btn.setVisibility(View.VISIBLE);
    	top_title = (TextView) findViewById(R.id.top_title);
    	top_title.setText("全部");
    	right_btn = (TextView) findViewById(R.id.right_icon);
    	right_btn.setVisibility(View.VISIBLE);
    	setView();

    }

    void initEvent()
    {
    	left_btn.setOnClickListener(this);
    	right_btn.setOnClickListener(this);
    }
    
    void setView()
    {
    	foodlist = database.query();
    	food_list_view = (ListView) findViewById(R.id.food_list_view);
    	
		if(foodadpter==null){
			foodadpter = new FoodAdapter(FoodListActivity.this, foodlist);
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
			Intent intent = new Intent(FoodListActivity.this,FoodAddActivity.class);
			startActivityForResult(intent, 1);
		default:
			break;
		}
	}
	
	protected void onRestart()
	{
		super.onRestart();
		//Toast.makeText(FoodListActivity.this, "restart！", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 1){
			if(resultCode == RESULT_OK){
				//food_list_view.refreshDrawableState();
				//setView();
			}
		}
	}

}
