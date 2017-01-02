package edu.upc.pda.clemente.laura.menmed;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class IngredientAdapter extends ArrayAdapter<Ingredient> {
    public IngredientAdapter(Context context, int layout_resource, ArrayList<Ingredient> data) {
        super(context, layout_resource, R.id.nom_ingr, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = super.getView(position, convertView, parent);
        Ingredient ingredient = getItem(position);

        // Obtenim referències a les parts de l'item de la llista
        // a la posició 'position'
        TextView nou_item = (TextView) convertView.findViewById(R.id.nom_ingr);
        CheckBox checked  = (CheckBox) convertView.findViewById(R.id.check_ingr);

        // Transferim dades del item al view que sortirà a la llista
        nou_item.setText(ingredient.getNom());
        checked.setChecked(ingredient.isChecked());

        return convertView;
    }
}
