package com.example.danmat.databaseproject.fragments;

import com.example.danmat.databaseproject.adapters.PetAdapter;
import com.example.danmat.databaseproject.pojo.Pet;

import java.util.ArrayList;

public interface IRecyclerViewFragmentView {
    public void generateVerticalLinearLayout();

    public PetAdapter createAdapter(ArrayList<Pet> petsList);

    public void initializeRVAdapter(PetAdapter petAdapter);
}
