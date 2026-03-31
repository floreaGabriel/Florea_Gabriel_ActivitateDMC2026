package com.florea_gabriel.labs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MagazinAdapter extends ArrayAdapter<GFMagazin> {

    private final Activity context;
    private final List<GFMagazin> magazine;

    public MagazinAdapter(Activity context, List<GFMagazin> magazine) {
        super(context, R.layout.item_magazin, magazine);
        this.context = context;
        this.magazine = magazine;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.item_magazin, parent, false);
        }

        GFMagazin magazin = magazine.get(position);

        TextView tvNume = view.findViewById(R.id.textViewItemNume);
        TextView tvTip = view.findViewById(R.id.textViewItemTip);
        TextView tvProfit = view.findViewById(R.id.textViewItemProfit);
        TextView tvFaliment = view.findViewById(R.id.textViewItemFaliment);

        tvNume.setText(magazin.getNume());
        tvTip.setText("Tip: " + magazin.getTipMagazin());
        tvProfit.setText("Profit: " + magazin.getProfit());
        tvFaliment.setText("Faliment: " + (magazin.getFaliment() ? "Da" : "Nu"));

        return view;
    }
}
