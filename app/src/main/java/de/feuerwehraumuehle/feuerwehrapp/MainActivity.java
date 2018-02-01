package de.feuerwehraumuehle.feuerwehrapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.androidannotations.annotations.App;

import java.util.ArrayList;

import de.feuerwehraumuehle.feuerwehrapp.adapter.MenuItemAdapter;
import de.feuerwehraumuehle.feuerwehrapp.data.FileManager;
import de.feuerwehraumuehle.feuerwehrapp.helper.Utils;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;
import de.feuerwehraumuehle.feuerwehrapp.model.ItemType;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utils.setStatusBarColor(this);

        ArrayList<Integer> pos = getIntent().getIntegerArrayListExtra("pos");
        if (pos == null) {
            pos = new ArrayList<>();
        }

        Item currentFile = null;
        try {
            currentFile = FileManager.getInstance(this).getRootItem();
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent(this, EmptyActivity.class);
            String msg = "";
            String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            msg = "Entweder " + sdcardPath + "/feuerwehr existiert nicht bzw. beinhaltet nichts oder " +
                    "die " +
                    "Berechtigung " +
                    "\"Speicher\" muss erst noch in den App-Einstellungen gegeben werden.";
            intent.putExtra("msg", msg);
            startActivity(intent);
            finish();
            return;
        }

        if (currentFile == null) {
            Intent intent = new Intent(this, EmptyActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        for (int p : pos) {
            currentFile = currentFile.getChildren().get(p);
        }

        MenuItemAdapter adapter = new MenuItemAdapter(this, currentFile);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
        View view = findViewById(R.id.background);
        view.setBackgroundColor(FeuerwehrApp.globalDefaults.defaultBackgroundColor);

        final ArrayList<Integer> posClone = new ArrayList<>(pos);
        final Item finalCurrentFile = currentFile;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item item = finalCurrentFile.getChildren().get(position);
                if (item.getType() == ItemType.DIRECTORY) {
                    Bundle bundle = new Bundle();
                    posClone.add(position);
                    bundle.putIntegerArrayList("pos", posClone);
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    posClone.clear();
                } else if (item.getType() == ItemType.PDF) {
                    Intent intent = new Intent(MainActivity.this, PDFnewActivity_.class);
                    intent.putExtra("pdf_path", item.getAbsolutePath());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), item.getType().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
