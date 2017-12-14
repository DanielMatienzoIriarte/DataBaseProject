package com.example.danmat.databaseproject.db;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.danmat.databaseproject.pojo.Pet;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private Context context;

    public DataBase(Context context) {
        super(context, DataBaseConstants.DATABASE_NAME, null, DataBaseConstants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createPetTableQuery = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_PETS +
                "(" + DataBaseConstants.TABLE_PETS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.TABLE_PETS_NAME + " TEXT, " +
                DataBaseConstants.TABLE_PETS_AVATAR + " INTEGER" +
                ")";
        db.execSQL(createPetTableQuery);

        String createRateTableQuery = "CREATE TABLE IF NOT EXISTS " + DataBaseConstants.TABLE_RATE_PET +
                "(" + DataBaseConstants.TABLE_RATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.TABLE_RATE_PET_ID + " INTEGER, " +
                DataBaseConstants.TABLE_RATE_VALUE + " INTEGER, " +
                "FOREIGN KEY (" + DataBaseConstants.TABLE_RATE_PET_ID + ")" +
                "REFERENCES " + DataBaseConstants.TABLE_PETS + "(" + DataBaseConstants.TABLE_PETS_ID +
                "))";
        db.execSQL(createPetTableQuery);
        db.execSQL(createRateTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.TABLE_RATE_PET);
        onCreate(db);
    }

    public ArrayList<Pet> getAll() {
        ArrayList<Pet> petsList = new ArrayList<>();
        String query = "SELECT * FROM " + DataBaseConstants.TABLE_PETS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(query, null);

        while (result.moveToNext()) {
            Pet currentPet = new Pet();
            currentPet.setName(result.getString(1));
            currentPet.setAvatar(result.getInt(2));
            currentPet.setRate(13);

            petsList.add(currentPet);
        }

        result.close();
        db.close();

        return petsList;
    }

    public ArrayList<Pet> getTop5() {
        ArrayList<Pet> petsList = new ArrayList<>();
        String query = "SELECT p." + DataBaseConstants.TABLE_PETS_ID +
                ", p." + DataBaseConstants.TABLE_PETS_NAME +
                ", p." + DataBaseConstants.TABLE_PETS_AVATAR +
                ", COUNT(p." + DataBaseConstants.TABLE_PETS_ID + ") AS pet_rate " +
                "FROM " + DataBaseConstants.TABLE_PETS + " AS p JOIN " +
                DataBaseConstants.TABLE_RATE_PET + " AS r " +
                "ON r." + DataBaseConstants.TABLE_RATE_PET_ID + " = p." +
                DataBaseConstants.TABLE_PETS_ID +
                " GROUP BY p." + DataBaseConstants.TABLE_PETS_ID +
                " ORDER BY pet_rate DESC LIMIT 5;";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(query, null);

        while (result.moveToNext()) {
            Pet currentPet = new Pet();
            currentPet.setPetId(result.getInt(0));
            currentPet.setName(result.getString(1));
            currentPet.setAvatar(result.getInt(2));
            currentPet.setRate(result.getInt(3));

            petsList.add(currentPet);
        }

        result.close();
        db.close();

        return petsList;
    }

    public int getPetRate(Pet pet) {
        int rate = 0;
        ArrayList<Pet> petsList = new ArrayList<>();
        String query = "SELECT COUNT(" + DataBaseConstants.TABLE_RATE_VALUE + ") FROM " +
                DataBaseConstants.TABLE_RATE_PET +
                " AS r WHERE r." + DataBaseConstants.TABLE_RATE_PET_ID +
                " = ?;";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(query, new String[] {String.format(Integer.toString(pet.getPetId()))});

        if (result.moveToNext()) {
            rate = result.getInt(0);
        }

        result.close();
        db.close();

        return rate;
    }

    public void insertPet(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_PETS, null, contentValues);
        db.close();
    }

    public void insertLikePet(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseConstants.TABLE_RATE_PET, null, contentValues);
        db.close();
    }
}
