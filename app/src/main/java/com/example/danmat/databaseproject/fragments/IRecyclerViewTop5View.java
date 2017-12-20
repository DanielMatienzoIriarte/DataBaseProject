package com.example.danmat.databaseproject.fragments;

import com.example.danmat.databaseproject.adapters.Top5Adapter;
import com.example.danmat.databaseproject.pojo.Pet;

import java.util.ArrayList;

public interface IRecyclerViewTop5View {
    public void generateVerticalLinearLayout();

    public Top5Adapter createAdapter(ArrayList<Pet> petsList);

    public void initializeRVAdapter(Top5Adapter top5Adapter);
}
