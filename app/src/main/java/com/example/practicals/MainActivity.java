package com.example.practicals;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.wear.widget.WearableLinearLayoutManager;

import com.example.practicals.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    MenuAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        init();


    }
    private void init(){
        mainBinding.wrcView.setHasFixedSize(true);
        mainBinding.wrcView.setEdgeItemsCenteringEnabled(true);
        mainBinding.wrcView.setLayoutManager(new WearableLinearLayoutManager(this));
        ArrayList<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem("Item 1", R.drawable.icon_1));
        menuItems.add(new MenuItem("Item 2", R.drawable.icon_2));
        menuItems.add(new MenuItem("Item 3", R.drawable.icon_3));
        menuItems.add(new MenuItem("Item 4", R.drawable.icon_4));

        mAdapter = new MenuAdapter(menuItems,getApplicationContext());
        mainBinding.wrcView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}