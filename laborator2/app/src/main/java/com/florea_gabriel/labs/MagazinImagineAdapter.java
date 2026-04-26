package com.florea_gabriel.labs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MagazinImagineAdapter extends ArrayAdapter<MagazinImagine> {

    private final Context context;
    private final List<MagazinImagine> items;

    public MagazinImagineAdapter(Context context, List<MagazinImagine> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_magazin_imagine, parent, false);
        }

        MagazinImagine item = items.get(position);

        ImageView imgMagazin = convertView.findViewById(R.id.imgMagazin);
        TextView txtDescriere = convertView.findViewById(R.id.txtDescriere);

        txtDescriere.setText(item.getDescriere());

        if (item.getBitmap() != null) {
            imgMagazin.setImageBitmap(item.getBitmap());
        } else {
            imgMagazin.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        return convertView;
    }
}
