package com.startwork.snake.dbhelper;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.startwork.snake.utils.gameManagementModels.PlayerEntityModel;

import java.util.ArrayList;
import java.util.List;


public class PlayerScoreDB extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "SCR";
    public static final String TABLE_NAME = "scorelst";
    public static final String NAME = "name";
    public static final String SCORE = "score";


    public PlayerScoreDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LST_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + NAME + " TEXT,"
                + SCORE + " TEXT" + ")";
        db.execSQL(CREATE_LST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addLst(PlayerEntityModel playerEntityModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, playerEntityModel.getName());
        values.put(SCORE, playerEntityModel.getScore());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }



    public List<PlayerEntityModel> getAllLists() {
        List<PlayerEntityModel> lstModel = new ArrayList<PlayerEntityModel>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                PlayerEntityModel listResult = new PlayerEntityModel();
                listResult.setName(cursor.getString(0));
                listResult.setScore(cursor.getString(1));
                lstModel.add(listResult);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lstModel;
    }
    public void deleteScore(List<PlayerEntityModel> playerEntityModels,int Pos) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, NAME + " =?", new String[]{String.valueOf(playerEntityModels.get(Pos).getName())});
        db.close();
    }



}
