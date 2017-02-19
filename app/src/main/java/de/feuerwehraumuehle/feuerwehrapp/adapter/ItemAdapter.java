package de.feuerwehraumuehle.feuerwehrapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import de.feuerwehraumuehle.feuerwehrapp.R;
import de.feuerwehraumuehle.feuerwehrapp.model.Category;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;

/**
 * Created by Matze on 18.02.2017.
 */

public class ItemAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Item> items;

    public ItemAdapter(Context c, ArrayList<Item> items) {
        mContext = c;
        this.items = items;
    }

    public int getCount() {
        return items.size();
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.category_item, null);

        } else {
            view = convertView;
        }
        TextView name = (TextView) view.findViewById(R.id.category_name);
        LinearLayout container = (LinearLayout) view.findViewById(R.id.category_container);
        name.setText(getItem(position).getName());
        Random random = new Random();
        int r = random.nextInt(254);
        int g = random.nextInt(254);
        int b = random.nextInt(254);
        container.setBackgroundColor(Color.argb(255,r,g,b));

        return view;
    }
}
