package de.feuerwehraumuehle.feuerwehrapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;

import de.feuerwehraumuehle.feuerwehrapp.adapter.CategoryAdapter;
import de.feuerwehraumuehle.feuerwehrapp.model.Category;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;
import de.feuerwehraumuehle.feuerwehrapp.model.ItemType;

public class MainActivity extends AppCompatActivity {

    ArrayList<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String sdcardPath = externalStorageDirectory.getAbsolutePath();

        File mainFolder = new File(sdcardPath, "feuerwehr");
        File[] categories = mainFolder.listFiles();
        for (File categoryFolder : categories) {
            if (categoryFolder.isDirectory()) {
                Category category = new Category(categoryFolder.getName());
                File[] items = categoryFolder.listFiles();
                for (File itemFile : items) {
                    Item item = new Item();
                    item.setName(itemFile.getName());
                    item.setFullPath(itemFile.getAbsolutePath());
                    if (item.getFullPath().toUpperCase().endsWith(".PDF")) {
                        item.setType(ItemType.PDF);
                    } else if (item.getFullPath().toUpperCase().endsWith(".JPG")) {
                        item.setType(ItemType.IMAGE);
                    } else {
                        item.setType(ItemType.UNDEFINED);
                    }
                    category.addItem(item);
                }
                categoryList.add(category);
            }
        }

        CategoryAdapter adapter = new CategoryAdapter(this, categoryList);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("items", categoryList.get(position));
                Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
