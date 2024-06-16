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

public class ThirdLevelCategoryActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> thirdLevelCategories;
    private String secondLevelCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_level_category);

        listView = findViewById(R.id.listView);

        // Seçilen ikinci seviye kategoriyi al
        secondLevelCategory = getIntent().getStringExtra("secondLevelCategory");

        // Seçilen ikinci seviye kategoriye göre üçüncü seviye kategorileri al
        thirdLevelCategories = MainActivity.secondLevelCategoryMap.get(secondLevelCategory);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thirdLevelCategories);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ThirdLevelCategoryActivity.this, FourthLevelCategoryActivity.class);
                intent.putExtra("thirdLevelCategory", thirdLevelCategories.get(position));
                startActivity(intent);
            }
        });
    }
}
