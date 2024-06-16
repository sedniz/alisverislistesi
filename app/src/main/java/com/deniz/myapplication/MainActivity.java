package com.deniz.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.deniz.myapplication.R;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateList;
    private Button btnViewList;
    public static HashMap<String, List<String>> firstLevelCategoryMap;
    public static HashMap<String, List<String>> secondLevelCategoryMap;
    public static HashMap<String, List<String>> thirdLevelCategoryMap;
    public static HashMap<String, List<String>> fourthLevelCategoryMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateList = findViewById(R.id.btnCreateList);
        btnViewList = findViewById(R.id.btnViewList);

        // İlk ve ikinci seviye kategorilerin alt kategorilerini tanımlama
        firstLevelCategoryMap = new HashMap<>();
        firstLevelCategoryMap.put("Temel Gıda",Arrays.asList("Makarna","Pirinç","Bulgur","Bakliyat","Salça","Yağ","Un","Şeker"));
        firstLevelCategoryMap.put("Et & Tavuk", Arrays.asList("Et", "Tavuk"));
        firstLevelCategoryMap.put("Kahvaltılık", Arrays.asList("Peynir","Zeytin","Yumurta"));
        firstLevelCategoryMap.put("İçecek", Arrays.asList("Su", "Meyve Suyu","Gazlı İçecek","Maden Suyu"));
        firstLevelCategoryMap.put("Bebek Bakım",Arrays.asList("Bebek Bezi","Bebek Maması"));
        firstLevelCategoryMap.put("Meyve & Sebze", Arrays.asList("Meyve", "Sebze"));
        firstLevelCategoryMap.put("Atıştırmalık",Arrays.asList("Cips","Bisküvi","Çikolata","Kek"));



        // İkinci ve üçüncü seviye kategorilerin alt kategorilerini tanımlama
        secondLevelCategoryMap = new HashMap<>();
        secondLevelCategoryMap.put("Makarna",Arrays.asList("Burgu","Arpa Şehriye","Spagetti","Tel Şehriye","Kelebek","Fiyonk"));
        secondLevelCategoryMap.put("Pirinç",Arrays.asList("Baldo","Osmancık","Kırık","Jasmine","Pilavlık"));
        secondLevelCategoryMap.put("Bulgur",Arrays.asList("Pilavlık","Köftelik","Esmer"));
        secondLevelCategoryMap.put("Bakliyat",Arrays.asList("Fasulye","Mısır","Nohut","Mercimek"));
        secondLevelCategoryMap.put("Salça",Arrays.asList("Domates Salçası","Biber Salçası"));
        secondLevelCategoryMap.put("Yağ",Arrays.asList("Zeytin yağı","Ayçiçek yağı"));
        secondLevelCategoryMap.put("Un",Arrays.asList("Böreklik Un","Tam Buğday Unu","Galeta Unu"));
        secondLevelCategoryMap.put("Şeker",Arrays.asList("Küp Şeker","Toz Şeker"));
        secondLevelCategoryMap.put("Et",Arrays.asList("Koyun","Dana"));
        secondLevelCategoryMap.put("Tavuk & Balık",Arrays.asList("Tavuk","Balık"));
        secondLevelCategoryMap.put("Peynir",Arrays.asList("Beyaz Peynir","Kaşar Peyniri"));
        secondLevelCategoryMap.put("Zeytin",Arrays.asList("Yeşil Zeytin","Siyah Zeytin"));
        secondLevelCategoryMap.put("Yumurta",Arrays.asList("Large","Medium","Small"));
        secondLevelCategoryMap.put("Su",Arrays.asList("Su"));
        secondLevelCategoryMap.put("Gazlı İçecek",Arrays.asList("Kola","Gazoz"));
        secondLevelCategoryMap.put("Maden Suyu",Arrays.asList("Maden Suyu"));
        secondLevelCategoryMap.put("Bebek Bezi",Arrays.asList("Bebek Bezi"));
        secondLevelCategoryMap.put("Bebek Maması",Arrays.asList("Bebek Maması"));
        secondLevelCategoryMap.put("Meyve",Arrays.asList("Elma","Portakal","Çilek","Muz"));
        secondLevelCategoryMap.put("Sebze",Arrays.asList("Marul","Maydonuz"));
        secondLevelCategoryMap.put("Cips",Arrays.asList("Baharatlı","Tuzlu","Sade"));
        secondLevelCategoryMap.put("Bisküvi",Arrays.asList("Sütlü","Kremalı"));
        secondLevelCategoryMap.put("Çikolata",Arrays.asList("Gofret","Tablet"));
        secondLevelCategoryMap.put("Kek",Arrays.asList("Kakaolu","Çikolatalı"));

        // Üçüncü ve dördüncü seviye kategorilerin alt kategorilerini tanımlama
        thirdLevelCategoryMap = new HashMap<>();
        thirdLevelCategoryMap.put("Burgu", Arrays.asList("Nuhun Ankara 'Burgu Makarna'", "Barilla 'Burgu Makarna'","Mutlu 'Burgu Makarna'","Arbella 'Burgu Makarna'"));
        thirdLevelCategoryMap.put("Arpa Şehriye", Arrays.asList("Nuhun Ankara 'Arpa Şehriye'", "Barilla 'Arpa Şehriye'","Mutlu 'Arpa Şehriye'","Arbella 'Arpa Şehriye'"));
        thirdLevelCategoryMap.put("Spagetti", Arrays.asList("Nuhun Ankara 'Spagetti'", "Barilla 'Spagetti'","Mutlu 'Spagetti'","Arbella 'Spagetti"));
        thirdLevelCategoryMap.put("Tel Şehirye", Arrays.asList("Nuhun Ankara 'Tel Şehirye'", "Barilla 'Tel Şehirye'","Mutlu 'Tel Şehirye'","Arbella 'Tel Şehirye'"));
        thirdLevelCategoryMap.put("Kelebek", Arrays.asList("Nuhun Ankara 'Kelebek Makarna'", "Barilla 'Kelebek Makarna'","Mutlu 'Kelebek Makarna'","Arbella 'Kelebek Makarna"));
        thirdLevelCategoryMap.put("Fiyonk", Arrays.asList("Nuhun Ankara 'Fiyonk Makarna'", "Barilla 'Fiyonk Makarna'","Mutlu 'Fiyonk Makarna'","Arbella 'Fiyonk Makarna'"));
        thirdLevelCategoryMap.put("Baldo", Arrays.asList("Yayla 'Baldo Pirinç'", "Duru 'Baldo Pirinç'"));
        thirdLevelCategoryMap.put("Osmancık", Arrays.asList("Yayla 'Osmancık Pirinç'", "Duru 'Osmancık Pirinç'"));
        thirdLevelCategoryMap.put("Kırık", Arrays.asList("Yayla 'Kırık Pirinç'", "Duru 'Kırık Pirinç'"));
        thirdLevelCategoryMap.put("Jasmine", Arrays.asList("Yayla 'Jasmine Pirinç'", "Duru 'Jasmine Pirinç'"));
        thirdLevelCategoryMap.put("Pilavlık", Arrays.asList("Yayla 'Pilavlık Pirinç'", "Duru 'Pilavlık Pirinç'"));
        thirdLevelCategoryMap.put("Pilavlık",Arrays.asList("Yayla 'Pilavlık Bulgur'","Duru 'Pilavlık Bulgur","Tat 'Pilavlık Bulgu'"));
        thirdLevelCategoryMap.put("Köftelik",Arrays.asList("Yayla 'Köftelik Bulgur'","Duru 'Köftelik Bulgur","Tat 'Köftelik Bulgu'"));
        thirdLevelCategoryMap.put("Esmer",Arrays.asList("Yayla 'Esmer Bulgur'","Duru 'Esmer Bulgur","Tat 'Esmer Bulgu'"));
        thirdLevelCategoryMap.put("Fasulye",Arrays.asList("Yayla Fasulye","Bizim Fasulye","Tat Fasulye"));
        thirdLevelCategoryMap.put("Mısır",Arrays.asList("Yayla Mısır","Bizim Mısır","Tat Mısır"));
        thirdLevelCategoryMap.put("Nohut",Arrays.asList("Yayla Nohut","Bizim Nohut","Tat Nohut","Duru Nohut"));
        thirdLevelCategoryMap.put("Mercimek",Arrays.asList("Yayla Mercimek","Bizim Mercimek","Tat Mercimek"));
        thirdLevelCategoryMap.put("Domates Salçası",Arrays.asList("Öncü Domates Salçası","Tamek Domates Salçası","Tat Domates Salçası"));
        thirdLevelCategoryMap.put("Biber Salçası",Arrays.asList("Tamek Biber Salçası","Tat Biber Salçası","Burcu Biber Salçası"));
        thirdLevelCategoryMap.put("Zeytin Yağı",Arrays.asList("Komili Sızma Zeytin Yağı","Yudum Natürel Zeytin Yağı"));
        thirdLevelCategoryMap.put("Ayçiçek Yağı",Arrays.asList("Yudum Ayçiçek Yağı","Komili Ayçiçek Yağı"));
        thirdLevelCategoryMap.put("Böreklik Un",Arrays.asList("Eriş Böreklik Un","Oba Böreklik Un"));
        thirdLevelCategoryMap.put("Galeta Unu",Arrays.asList("Bağdat Galeta Unu","Arifoğlu Galeta Unu"));
        thirdLevelCategoryMap.put("Küp Şeker",Arrays.asList("Balküpü","Kristal"));
        thirdLevelCategoryMap.put("Toz Şeker",Arrays.asList("Balküpü","Kristal"));
        thirdLevelCategoryMap.put("Koyun",Arrays.asList("Kuzu Pirzola","Kuyruk yağı","Kuzu kıyma"));
        thirdLevelCategoryMap.put("Dana",Arrays.asList("Dana kıyma","Dana Kuşbaşı"));
        thirdLevelCategoryMap.put("Tavuk",Arrays.asList("Baget","Bonfile","Kanat"));
        thirdLevelCategoryMap.put("Balık",Arrays.asList("Somon","Hamsi","İstavrit"));
        thirdLevelCategoryMap.put("Beyaz peynir",Arrays.asList("Altınkılıç Beyaz Peynir","İçim Beyaz Peynir"));
        thirdLevelCategoryMap.put("Kaşar Peyniri",Arrays.asList("Cebel Kaşar Peynir","Sütaş Kaşar Peynir"));
        thirdLevelCategoryMap.put("Yeşil Zeytin",Arrays.asList("Fora Yeşil Zeytin","Marmarabirlik Yeşil Zeytin"));
        thirdLevelCategoryMap.put("Siyah Zeytin",Arrays.asList("Fora Siyah Zeytin","Komili Siyah Zeytin"));
        thirdLevelCategoryMap.put("Large",Arrays.asList("Ortival Yumurta(L)","Güres Yumurta(L)"));
        thirdLevelCategoryMap.put("Medium",Arrays.asList("Naturaköy Yumurta(M)","Flotty Yumurta(M)"));
        thirdLevelCategoryMap.put("Small",Arrays.asList("Naturaköy Yumurta(S)","Gürata Yumurta(S)"));
        thirdLevelCategoryMap.put("Su",Arrays.asList("Sırma Su","Erikli Su","Pınar Su"));
        thirdLevelCategoryMap.put("Kola",Arrays.asList("Coca Cola","Pepsi","Fanta","Yedigün"));
        thirdLevelCategoryMap.put("Gazoz",Arrays.asList("Sprite","Fruko","Niğde Gazozu"));
        thirdLevelCategoryMap.put("Maden Suyu",Arrays.asList("Beypazarı","Sırma Maden Suyu","Kızılay Maden Suyu"));
        thirdLevelCategoryMap.put("Bebek Bezi",Arrays.asList("Prima","Molfix","Sleepy"));
        thirdLevelCategoryMap.put("Bebek Maması",Arrays.asList("Bebelac","SMA","Aptamil"));
        thirdLevelCategoryMap.put("Elma",Arrays.asList("1Kg ELma","2Kg Elma","3Kg Elma"));
        thirdLevelCategoryMap.put("Portakal",Arrays.asList("1Kg Portakal","2Kg Portakal","3Kg Portakal"));
        thirdLevelCategoryMap.put("Çilek",Arrays.asList("1Kg Çilek","2Kg Çilek","3Kg Çilek"));
        thirdLevelCategoryMap.put("Muz",Arrays.asList("1Kg Muz","2Kg Muz","3Kg Muz"));
        thirdLevelCategoryMap.put("Marul",Arrays.asList("1 tane Marul","2 tane Marul","3 tane Marul"));
        thirdLevelCategoryMap.put("Maydonoz",Arrays.asList("1tane Maydonoz","2 tane Maydonoz","3 tane Maydonoz"));
        thirdLevelCategoryMap.put("Baharatlı",Arrays.asList("Doritos","Lays","Ruffles"));
        thirdLevelCategoryMap.put("Tuzlu",Arrays.asList("Ruffles","Lays"));
        thirdLevelCategoryMap.put("Sade",Arrays.asList("Pringles","Doritos"));
        thirdLevelCategoryMap.put("Sütlü",Arrays.asList("Burçak Sütlü Çikolatalı"));
        thirdLevelCategoryMap.put("Kremalı",Arrays.asList("Burçak","Ülker Rondo"));
        thirdLevelCategoryMap.put("Gofret",Arrays.asList("Ülker Çikolatalı Gofret","Eti Çikolatalı Gofret"));
        thirdLevelCategoryMap.put("Tablet",Arrays.asList("Eti Çikolata","Nestle Damak"));
        thirdLevelCategoryMap.put("Kakaolu",Arrays.asList("Popkek","Dankek"));
        thirdLevelCategoryMap.put("Çikolatalı",Arrays.asList("Popkek Çikolatalı","Dankek Çikolatalı"));








        btnCreateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstLevelCategoryActivity.class);
                startActivity(intent);
            }
        });

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewListActivity.class);
                startActivity(intent);
            }
        });
    }
}
