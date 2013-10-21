package com.outofjungle.apps.quack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TwitterStorageHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_TWEETS = "tweets";
	public static final String TWEET_ID = "id";
	public static final String TWEET_TEXT = "text";
	public static final String USER_ID = "user_id";
	public static final String USER_NAME = "user_name";
	public static final String USER_SCREEN_NAME = "user_screen_name";
	public static final String USER_IMG_URL = "user_profile_image_url";
	
	
	private static final String DATABASE_NAME = "quack.db";
	private static final int DATABASE_VERSION = 3;
	private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_TWEETS + " (" +
    		TWEET_ID + " UNSIGNED BIG INT PRIMARY KEY, " +
    		TWEET_TEXT + " TEXT, " +
    		USER_ID + " UNSIGNED BIG INT, " +
    		USER_NAME + " TEXT, " +
    		USER_SCREEN_NAME + " TEXT, " +
    		USER_IMG_URL + " TEXT);";
	
	TwitterStorageHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TWEETS);
	    onCreate(db);
	}
}
