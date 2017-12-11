package com.example.danmat.databaseproject.presenter;

import android.content.Context;

import com.example.danmat.databaseproject.db.PetsConstructor;
import com.example.danmat.databaseproject.fragments.IRecyclerViewFragmentView;
import com.example.danmat.databaseproject.pojo.Pet;

import java.util.ArrayList;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private PetsConstructor petsConstructor;
    private ArrayList<Pet> petsList;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        getDatabasePets();
    }

    @Override
    public void getDatabasePets() {
        petsConstructor = new PetsConstructor(context);
        petsList = petsConstructor.getData();
        displayRecyclerViewPets();
    }

    @Override
    public void displayRecyclerViewPets() {
        iRecyclerViewFragmentView.initializeRVAdapter(iRecyclerViewFragmentView.createAdapter(petsList));
        iRecyclerViewFragmentView.generateVerticalLinearLayout();
    }
}
