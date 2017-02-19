package de.feuerwehraumuehle.feuerwehrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import de.feuerwehraumuehle.feuerwehrapp.adapter.CategoryAdapter;
import de.feuerwehraumuehle.feuerwehrapp.adapter.ItemAdapter;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;

public class ItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ArrayList<Item> items = (ArrayList<Item>) getIntent().getExtras().getSerializable("items");

        ItemAdapter adapter = new ItemAdapter(this, items);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("items", items.get(position).getFullPath());
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
