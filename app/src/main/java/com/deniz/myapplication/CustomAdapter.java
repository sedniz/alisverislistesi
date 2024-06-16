package com.deniz.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private boolean[] checkedPositions;

    public CustomAdapter(Context context, List<String> items) {
        super(context, 0, items);
        checkedPositions = new boolean[items.size()];
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final String item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_with_checkbox, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        checkBox.setText(item);
        checkBox.setChecked(checkedPositions[position]);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkedPositions[position] = isChecked;
            }
        });

        return convertView;
    }

    // Method to toggle all checkboxes
    public void toggleAll(boolean checked) {
        Arrays.fill(checkedPositions, checked);
        notifyDataSetChanged();
    }
}
