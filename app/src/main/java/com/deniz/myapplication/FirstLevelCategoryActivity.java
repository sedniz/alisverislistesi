package com.deniz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.deniz.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FirstLevelCategoryActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> firstLevelCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_level_category);

        listView = findViewById(R.id.listView);

        // Ana kategorilerin listesini oluşturma
        firstLevelCategories = new ArrayList<>(MainActivity.firstLevelCategoryMap.keySet());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, firstLevelCategories);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FirstLevelCategoryActivity.this, SecondLevelCategoryActivity.class);
                intent.putExtra("firstLevelCategory", firstLevelCategories.get(position));
                startActivity(intent);
            }
        });
    }
}
