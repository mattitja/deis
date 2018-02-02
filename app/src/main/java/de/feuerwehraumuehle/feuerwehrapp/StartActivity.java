package de.feuerwehraumuehle.feuerwehrapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class StartActivity extends AppCompatActivity {

    public final String RESUMED = "RESUMED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Handler handler = new Handler();

        if (savedInstanceState == null) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1700);
        }

        ImageView imageView = (ImageView) findViewById(R.id.start_image);

        int orientation = getResources().getConfiguration().orientation;
        String imageName = "";
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            imageName = "start_portrait.jpg";
        } else {
            imageName = "start_landscape.jpg";
        }

        loadImage(imageName, imageView);

    }

    private void loadImage(String iconName, ImageView view) {
        File imgFile = new  File("/sdcard/feuerwehr/config/icons/" + iconName);
        if(imgFile.exists()){
            Bitmap loadedIcon = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            view.setImageBitmap(loadedIcon);
        } else {
            View viewById = findViewById(R.id.empty_text);
            viewById.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putBoolean(RESUMED, true);
    }
}
