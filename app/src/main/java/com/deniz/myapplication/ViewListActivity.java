package com.deniz.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewListActivity extends AppCompatActivity {

    private ListView listView;
    private Button shareButton;
    private Button selectAllButton;
    private Button deleteButton;
    private DatabaseHelper dbHelper;
    private List<String> selectedCategories;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        listView = findViewById(R.id.listView);
        shareButton = findViewById(R.id.shareButton);
        selectAllButton = findViewById(R.id.selectAllButton);
        deleteButton = findViewById(R.id.deleteButton);
        dbHelper = new DatabaseHelper(this);
        selectedCategories = new ArrayList<>();

        loadSelectedCategories();

        adapter = new CustomAdapter(this, selectedCategories);
        listView.setAdapter(adapter);

        selectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.selectAll();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedCategories();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File pdfFile = createPdf();
                    sharePdf(pdfFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadSelectedCategories() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String SELECT_QUERY = "SELECT * FROM " + DatabaseHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME);
            if (columnIndex != -1) {
                do {
                    String category = cursor.getString(columnIndex);
                    selectedCategories.add(category);
                } while (cursor.moveToNext());
            }
            cursor.close(); // Cursor'ı kapatmayı unutmayın
        }
        db.close();
    }

    private File createPdf() throws IOException {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        int x = 10, y = 25;
        for (String category : selectedCategories) {
            page.getCanvas().drawText(category, x, y, new Paint());
            y += 25;
        }

        document.finishPage(page);

        File directory = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "pdfs");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "selected_categories.pdf");
        FileOutputStream fos = new FileOutputStream(file);
        document.writeTo(fos);
        document.close();
        fos.close();

        return file;
    }

    private void sharePdf(File pdfFile) {
        Uri pdfUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", pdfFile);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "PDF Dosyasını Paylaş"));
    }

    private void deleteSelectedCategories() {
        // Silme işlemi için seçilen kategorileri işleyin
        List<String> categoriesToRemove = new ArrayList<>();
        for (int i = 0; i < selectedCategories.size(); i++) {
            if (adapter.isChecked(i)) {
                categoriesToRemove.add(selectedCategories.get(i));
            }
        }

        // Veritabanından ve listeden seçilen kategorileri kaldır
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        for (String category : categoriesToRemove) {
            db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_NAME + " = ?", new String[]{category});
            selectedCategories.remove(category);
        }
        db.close();

        // Adapter'a değişiklikleri bildir ve ListView'ı güncelle
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Seçilen kategoriler silindi", Toast.LENGTH_SHORT).show();
    }

    private class CustomAdapter extends ArrayAdapter<String> {

        private boolean[] checkedItems;

        public CustomAdapter(ViewListActivity context, List<String> items) {
            super(context, 0, items);
            checkedItems = new boolean[items.size()];
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_list_with_checkbox, parent, false);
            }

            CheckBox checkBox = convertView.findViewById(R.id.checkBox);
            checkBox.setText(getItem(position));
            checkBox.setChecked(checkedItems[position]);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v;
                    checkedItems[position] = checkBox.isChecked();
                }
            });

            return convertView;
        }

        public void selectAll() {
            for (int i = 0; i < checkedItems.length; i++) {
                checkedItems[i] = true;
            }
            notifyDataSetChanged();
        }

        public boolean isChecked(int position) {
            return checkedItems[position];
        }
    }
}
