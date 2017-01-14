package edu.upc.pda.clemente.laura.menmed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Laura on 14/1/2017.
 */

public class ListActivityAdapter extends ArrayAdapter<IngrList> {
    public ListActivityAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result = convertView;
        if (result==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            result = inflater.inflate(R.layout.shopping_item, null);
        }

        CheckBox prod_check = (CheckBox) result.findViewById(R.id.prod_check);
        TextView product = (TextView) result.findViewById(R.id.product);
        TextView quantity = (TextView) result.findViewById(R.id.quantity);
        TextView units = (TextView) result.findViewById(R.id.units);

        IngrList item = getItem(position);

        product.setText(item.getNom());
        quantity.setText(item.getQuant().toString());
        units.setText(item.getUnitats());

        return result;
    }
}
