package com.example.danmat.databaseproject.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.danmat.databaseproject.R;
import com.example.danmat.databaseproject.pojo.Pet;

import java.util.ArrayList;

public class PetsConstructor {
    private Context context;

    public PetsConstructor(Context context) {
        this.context = context;
    }

    public ArrayList<Pet> getData() {
        //DataBase db = new DataBase(context);
        //return db.getAll();
        ArrayList<Pet> petsList = new ArrayList<Pet>();
        petsList.add(new Pet(1, "Mortis", R.drawable.dog_bark_icon));
        petsList.add(new Pet(2, "Vato Loco", R.drawable.dog_chihuahua_bone_icon));
        petsList.add(new Pet(3, "Gordo", R.drawable.dog_dalmatian_king_icon));
        petsList.add(new Pet(4, "Rita", R.drawable.dog_einstein_icon));
        petsList.add(new Pet(5, "Laika", R.drawable.dog_haski_icon));
        petsList.add(new Pet(6, "Dogo", R.drawable.dog_einstein_icon));
        petsList.add(new Pet(7, "Linda", R.drawable.dog_haski_icon));

        return petsList;
    }

    public void insertPet(DataBase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_PETS_NAME, "Kareem");
        contentValues.put(DataBaseConstants.TABLE_PETS_AVATAR, R.drawable.cat_avatar);

        db.insertPet(contentValues);
    }

    public void likePet(Pet pet) {
        DataBase db = new DataBase(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseConstants.TABLE_RATE_PET_ID, pet.getPetId());
        contentValues.put(DataBaseConstants.TABLE_RATE_VALUE, 1);

        db.insertLikePet(contentValues);
    }

    public int getPetRate(Pet pet) {
        DataBase db = new DataBase(context);
        return db.getPetRate(pet);
    }
}
