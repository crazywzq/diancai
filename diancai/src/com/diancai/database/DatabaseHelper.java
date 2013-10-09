package com.diancai.database;
import com.diancai.activity.MainActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String dbname = "food.db";
    private static final int version = 1;
	public DatabaseHelper(Context context) {
		super(context, dbname, null, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		createFood(db);
		initFood(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS food");
	}
	
	private void createFood(SQLiteDatabase db)
	{
		String sql = "";
		try {
			sql = "CREATE TABLE food(id integer primary key AUTOINCREMENT" +
					",name varchar(20) " +
					",sort varchar(20)" +
					")";
			db.execSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void initFood(SQLiteDatabase db)
	{
		try
		{
			ContentValues values = new ContentValues();
			values.put("name", "糖醋排骨");			
			db.insert("food", null, values);
			values.put("name", "香菇炒芹菜");			
			db.insert("food", null, values);
			values.put("name", "菠萝咕噜肉");			
			db.insert("food", null, values);
			values.put("name", "手撕包菜 ");			
			db.insert("food", null, values);
			values.put("name", "鱼香肉丝");			
			db.insert("food", null, values);
			values.put("name", "鱼香茄子");			
			db.insert("food", null, values);
			values.put("name", "酱汁杏鲍菇 ");			
			db.insert("food", null, values);
			values.put("name", "番茄土豆片");			
			db.insert("food", null, values);
			values.put("name", "香脆炒黄瓜 ");			
			db.insert("food", null, values);
			values.put("name", "蒜蓉粉丝娃娃菜 ");			
			db.insert("food", null, values);
			values.put("name", "糖醋菠萝鸡翅 ");			
			db.insert("food", null, values);
			values.put("name", "麻婆豆腐 ");			
			db.insert("food", null, values);
			values.put("name", "酱香肉末烧茄子 ");			
			db.insert("food", null, values);
			values.put("name", "红烧狮子头");			
			db.insert("food", null, values);
			values.put("name", "香菇鸡块 ");			
			db.insert("food", null, values);
			values.put("name", "麻辣香锅 ");			
			db.insert("food", null, values);
			values.put("name", "红烧鸡翅");			
			db.insert("food", null, values);
			values.put("name", "辣白菜炒饭");			
			db.insert("food", null, values);
			values.put("name", "盐爆鱿鱼卷");			
			db.insert("food", null, values);
			values.put("name", "芹菜木耳炒肉丝");			
			db.insert("food", null, values);
			values.put("name", "尖椒肥肠");			
			db.insert("food", null, values);
			values.put("name", "手抓饼 ");			
			db.insert("food", null, values);
			values.put("name", "青椒回锅肉");			
			db.insert("food", null, values);			
			values.put("name", "麻辣水煮鱼");			
			db.insert("food", null, values);
			values.put("name", "秘制红烧肉 ");			
			db.insert("food", null, values);
			values.put("name", "可乐鸡翅");			
			db.insert("food", null, values);
			values.put("name", "红烧排骨 ");			
			db.insert("food", null, values);
			values.put("name", "水煮肉片");			
			db.insert("food", null, values);
			values.put("name", "干煸四季豆 ");			
			db.insert("food", null, values);
			values.put("name", "酸辣土豆丝 ");			
			db.insert("food", null, values);
			values.put("name", "酱烧茄子");			
			db.insert("food", null, values);
			
			//db.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
