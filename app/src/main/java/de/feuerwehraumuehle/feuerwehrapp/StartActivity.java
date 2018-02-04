package de.feuerwehraumuehle.feuerwehrapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import de.feuerwehraumuehle.feuerwehrapp.manager.ConfigurationManager;
import de.feuerwehraumuehle.feuerwehrapp.manager.FileManager;

public class StartActivity extends AppCompatActivity {

	public final String RESUMED = "RESUMED";

	public final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1337;

	public final int START_SCREEN_DURATION = 1700;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		if (isInitialStart(savedInstanceState)) {
			if (ContextCompat.checkSelfPermission(this,
					Manifest.permission.READ_EXTERNAL_STORAGE)
					!= PackageManager.PERMISSION_GRANTED) {

				ActivityCompat.requestPermissions(this,
						new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
						MY_PERMISSIONS_REQUEST_READ_CONTACTS);
			} else {
				proceedWithGivenStoragePermission();
			}
		}
	}

	private boolean isInitialStart(Bundle savedInstanceState) {
		return savedInstanceState == null || !savedInstanceState.getBoolean(RESUMED);
	}

	private void proceedWithGivenStoragePermission() {
		loadActivityBackgroundImage();
		loadAllData();
	}

	private void loadActivityBackgroundImage() {
		ImageView imageView = (ImageView) findViewById(R.id.start_image);

		int currentOrientation = getResources().getConfiguration().orientation;
		String imageToShow = "";
		if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
			imageToShow = "start_portrait.jpg";
		} else {
			imageToShow = "start_landscape.jpg";
		}

		loadImage(imageToShow, imageView);
	}

	private void loadAllData() {
		ConfigurationManager configManager = ConfigurationManager.getInstance();
		FileManager fileManager = FileManager.getInstance();

		try {
			configManager.init();
			fileManager.init();
		} catch (Exception e) {
			ErrorActivity.handleExceptionAndCloseCurrentActivity(this, e);
			return;
		}

		startMainActivityAfterShortBreak();
	}


	private void startMainActivityAfterShortBreak() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(StartActivity.this, MenuActivity.class);
				startActivity(intent);
				finish();
			}
		}, START_SCREEN_DURATION);
	}

	private void loadImage(String iconName, ImageView view) {
		File imgFile = new File("/sdcard/feuerwehr/config/icons/" + iconName);
		if (imgFile.exists()) {
			Bitmap loadedIcon = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			view.setImageBitmap(loadedIcon);
		} else {
			View viewById = findViewById(R.id.empty_text);
			viewById.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
			case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					proceedWithGivenStoragePermission();
				} else {
					finish();
				}
			}
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
		super.onSaveInstanceState(outState, outPersistentState);
		outState.putBoolean(RESUMED, true);
	}
}
