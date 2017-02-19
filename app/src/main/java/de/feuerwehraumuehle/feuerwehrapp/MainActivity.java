package de.feuerwehraumuehle.feuerwehrapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import de.feuerwehraumuehle.feuerwehrapp.adapter.FFileAdapter;
import de.feuerwehraumuehle.feuerwehrapp.data.FileManager;
import de.feuerwehraumuehle.feuerwehrapp.model.FFile;
import de.feuerwehraumuehle.feuerwehrapp.model.FFileType;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        ArrayList<Integer> pos = getIntent().getIntegerArrayListExtra("pos");
        if (pos == null) {
            pos = new ArrayList<>();
        }

        FFile currentFile = null;
        try {
            currentFile = FileManager.getInstance(this).getRootFFile();
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent = new Intent(this, EmptyActivity.class);
            String msg = "";
            String sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (e instanceof FileManager.StartFolderContainsNoItems) {
                msg = sdcardPath + "/feuerwehr/ beinhaltet keine Items.";
            } else {
                msg = sdcardPath + "/feuerwehr/ beinhaltet keine Items.";
            }
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

        FFileAdapter adapter = new FFileAdapter(this, currentFile);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        final ArrayList<Integer> posClone = new ArrayList<>(pos);
        final FFile finalCurrentFile = currentFile;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FFile fFile = finalCurrentFile.getChildren().get(position);
                if (fFile.getType() == FFileType.DIRECTORY) {
                    Bundle bundle = new Bundle();
                    posClone.add(position);
                    bundle.putIntegerArrayList("pos", posClone);
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    posClone.clear();
                } else if (fFile.getType() == FFileType.PDF) {
                    Intent intent = new Intent(MainActivity.this, PDFActivity.class);
                    intent.putExtra("pdf_path", fFile.getAbsolutePath());
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), fFile.getType().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
