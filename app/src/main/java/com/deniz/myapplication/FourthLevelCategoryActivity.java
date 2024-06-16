package com.deniz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.deniz.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FourthLevelCategoryActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> fourthLevelCategories;
    private List<String> selectedCategories;
    private String thirdLevelCategory;
    private Button btnConfirm;
    private Button btnViewList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_level_category);

        listView = findViewById(R.id.listView);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnViewList = findViewById(R.id.btnViewList);
        selectedCategories = new ArrayList<>();
        dbHelper = new DatabaseHelper(this);

        // Seçilen üçüncü seviye kategoriyi al
        thirdLevelCategory = getIntent().getStringExtra("thirdLevelCategory");

        // Seçilen üçüncü seviye kategoriye göre dördüncü seviye kategorileri al
        fourthLevelCategories = MainActivity.thirdLevelCategoryMap.get(thirdLevelCategory);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, fourthLevelCategories);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = fourthLevelCategories.get(position);
                if (selectedCategories.contains(selectedCategory)) {
                    selectedCategories.remove(selectedCategory);
                } else {
                    selectedCategories.add(selectedCategory);
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String category : selectedCategories) {
                    dbHelper.addCategory(category);
                }
                Toast.makeText(FourthLevelCategoryActivity.this, "Seçilen kategoriler kaydedildi", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthLevelCategoryActivity.this, ViewListActivity.class);
                startActivity(intent);
            }
        });
    }
}
