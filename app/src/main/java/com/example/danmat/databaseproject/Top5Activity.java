package com.example.danmat.databaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.danmat.databaseproject.adapters.PetAdapter;
import com.example.danmat.databaseproject.pojo.Pet;

import java.util.ArrayList;

public class Top5Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView top5RecyclerView;
    ArrayList<Pet> petsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top5);

        toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*top5RecyclerView =(RecyclerView) findViewById(R.id.main_recyclerView_top5);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        top5RecyclerView.setLayoutManager(linearLayoutManager);

        initializePetsList();
        initializeAdapter();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optionsMenu_item_contact:
                Intent contactIntent = new Intent(this, ContactActivity.class);
                startActivity(contactIntent);
                break;
            case R.id.optionsMenu_item_about:
                Intent aboutIntent = new Intent (this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case R.id.optionsMenu_item_top5:
                Intent top5Intent = new Intent(this, Top5Activity.class);
                startActivity(top5Intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initializePetsList(){
        petsList = new ArrayList<Pet>();
        petsList.add(new Pet(1, "1", R.drawable.dog_bark_icon));
        petsList.add(new Pet(2, "2", R.drawable.dog_chihuahua_bone_icon));
        petsList.add(new Pet(3, "3", R.drawable.dog_dalmatian_king_icon));
        petsList.add(new Pet(4, "4", R.drawable.dog_einstein_icon));
        petsList.add(new Pet(5, "5", R.drawable.dog_haski_icon));
        petsList.add(new Pet(6, "6", R.drawable.dog_einstein_icon));
        petsList.add(new Pet(7, "7", R.drawable.dog_haski_icon));
    }

    private void initializeAdapter(){
        PetAdapter petAdapter = new PetAdapter(petsList, this);
        top5RecyclerView.setAdapter(petAdapter);
    }
}
