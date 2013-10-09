package com.diancai.adapter;
import java.util.ArrayList;

import com.diancai.model.Food;
import com.example.yaoyiyaodemo.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FoodAdapter extends BaseAdapter{
	private ArrayList<Food> foodlist;
	private Context context;
	
	public FoodAdapter(Context context,ArrayList<Food> foodlist)
	{
		this.context = context;
		this.foodlist = foodlist;
	}
	
	public void setFoodList(ArrayList<Food> foodlist)
	{
		this.foodlist = foodlist;
	}
	@Override
	public int getCount() {
		if(foodlist == null)
		{
			return 0;
		}
		return foodlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parentView) {
		ViewHolder holder;
		if(convertView == null)
		{
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.food_item_view, null);
			holder.foodname = (TextView)convertView.findViewById(R.id.foodname);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.foodname.setText((position+1)+") "+foodlist.get(position).getName());
		return convertView;
	}
	private static class ViewHolder
	{
	    TextView foodname;
	}
}
