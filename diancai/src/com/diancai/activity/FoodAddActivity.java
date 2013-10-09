package com.diancai.activity;

import com.diancai.adapter.FoodAdapter;
import com.diancai.database.DatabaseService;
import com.diancai.model.Food;
import com.example.yaoyiyaodemo.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodAddActivity extends Activity implements OnClickListener{
	private TextView left_btn,right_icon,btn_submit;
	private EditText foodname;
	DatabaseService database = new DatabaseService(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.food_add_view);
		initView();
		initEvent();
	}
    void initView()
    {
    	left_btn = (TextView) findViewById(R.id.left_icon);
    	left_btn.setOnClickListener(this);
    	btn_submit = (TextView) findViewById(R.id.btn_submit);
    	foodname = (EditText) findViewById(R.id.foodname);
    }

    void initEvent()
    {
    	left_btn.setOnClickListener(this);
    	btn_submit.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.left_icon:
			setResult(RESULT_OK);
			finish();
			break;
		case R.id.btn_submit:
			Food food = new Food();
			String str_food_name = foodname.getText().toString().trim();
			food.setName(str_food_name);
			database.inser(food);
			Toast.makeText(FoodAddActivity.this, "添加成功！", Toast.LENGTH_LONG).show();
			finish();
			
			break;
		default:
			break;
		}
	}
}
