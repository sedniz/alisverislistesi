package com.deniz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.deniz.myapplication.R;

import java.util.List;

public class SecondLevelCategoryActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> secondLevelCategories;
    private String firstLevelCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_level_category);

        listView = findViewById(R.id.listView);

        // Seçilen ana kategoriyi al
        firstLevelCategory = getIntent().getStringExtra("firstLevelCategory");

        // Seçilen ana kategoriye göre alt kategorileri al
        secondLevelCategories = MainActivity.firstLevelCategoryMap.get(firstLevelCategory);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, secondLevelCategories);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SecondLevelCategoryActivity.this, ThirdLevelCategoryActivity.class);
                intent.putExtra("secondLevelCategory", secondLevelCategories.get(position));
                startActivity(intent);
            }
        });
    }
}
