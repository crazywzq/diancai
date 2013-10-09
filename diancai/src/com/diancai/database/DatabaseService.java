package com.diancai.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.diancai.model.Food;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseService {
	private DatabaseHelper helper;
	private Context context;
	
	
	public DatabaseService(Context _context){
		this.context = _context;
		helper = new DatabaseHelper(context);
	}
	public SQLiteDatabase GetDb(){
		SQLiteDatabase db = helper.getWritableDatabase();
		return db;
	}
	
	public void ExecSQL(String sql){
		SQLiteDatabase db = null;
		try{
			db = helper.getWritableDatabase();
			db.execSQL(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if(db != null) {
				db.close();
			}
		}
	}
	
	public void inser(Food food)
	{
		SQLiteDatabase db = null;
		try
		{
			db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			if(food != null)
			{
				if(!"".equals(food.getName()))
				{
					values.put("name", food.getName());
				}
				db.insert("food", null, values);
			}
			db.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Food> query()
	{
		SQLiteDatabase db = null;
		ArrayList<Food> foodList = null;
		try
		{
			db = helper.getWritableDatabase();
			Cursor c =  db.query("food", null, "", new String[]{}, "", "", "");
			if(c != null){
				foodList = new ArrayList<Food>();
				while(c.moveToNext()){
					Food food = new Food();
					food.setId(c.getInt(0));					
					food.setName(c.getString(1));
					foodList.add(food);
				}
				
			}
			c.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return foodList;
	}
	
	public ArrayList<Food> randomFood()
	{
		SQLiteDatabase db = null;
		ArrayList<Food> foodList = null;
		try
		{
			db = helper.getWritableDatabase();
			Cursor cursor_all =  db.query("food", null, "", new String[]{}, "", "", "");
			Random random = new Random();
			int start = random.nextInt(cursor_all.getCount());
			//String sql = "select * from food limit 3,1";
			Cursor cursor_part =  db.query("food", null, "", new String[]{}, "", "", "", start+",1");
			if(cursor_part != null){
				foodList = new ArrayList<Food>();
				while(cursor_part.moveToNext()){
					Food food = new Food();
					food.setId(cursor_part.getInt(0));					
					food.setName(cursor_part.getString(1));
					foodList.add(food);
				}
				cursor_part.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return foodList;
	}
	
	public ArrayList<Food> randomZhuo(int total)
	{
		SQLiteDatabase db = null;
		ArrayList<Food> foodList = new ArrayList<Food>();
		//保证不重复
		Set set = new HashSet();
		if(total < 1)
		{
			total = 1;
		}
		try
		{
			db = helper.getWritableDatabase();
			Cursor cursor_all =  db.query("food", null, "", new String[]{}, "", "", "");
			Cursor cursor_part = null;
			for(int i=0;foodList.size() < total;i++)
			{
				if(i > total * 10)
				{
					break;
				}
				Random random = new Random();
				int start = random.nextInt(cursor_all.getCount());
				//String sql = "select * from food limit 3,1";
				cursor_part =  db.query("food", null, "", new String[]{}, "", "", "", start+",1");
				if(cursor_part != null){
					while(cursor_part.moveToNext()){
						Food food = new Food();
						food.setId(cursor_part.getInt(0));					
						food.setName(cursor_part.getString(1));
			            if (set.add(food.getName().toString()))
			            {
			            	foodList.add(food);
			            }
					}
					
				}
				
			}
			if(cursor_part != null)
			{
				cursor_part.close();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return foodList;
	}
}
