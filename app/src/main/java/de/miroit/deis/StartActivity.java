package de.miroit.deis;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import de.miroit.deis.manager.GlobalConfigurationsManager;
import de.miroit.deis.manager.FileManager;

public class StartActivity extends AppCompatActivity {

	public final String RESUMED = "RESUMED";

	public final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1337;

	public final int START_SCREEN_DURATION = 2000;

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
		loadAllData();
	}

	private void loadAllData() {
		GlobalConfigurationsManager configManager = GlobalConfigurationsManager.getInstance();
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
