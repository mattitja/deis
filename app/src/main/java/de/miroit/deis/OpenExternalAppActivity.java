package de.miroit.deis;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import de.miroit.deis.helper.Utils;
import de.miroit.deis.manager.GlobalConfigurationsManager;

public class OpenExternalAppActivity extends AppCompatActivity {

	public static final String INTENT_PACKAGE_NAME = "packageName";
	public static final String INTENT_APP_NAME = "appName";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_open_external_app);

		final String appName = getIntent().getExtras().getString(INTENT_APP_NAME);
		final String packageName = getIntent().getExtras().getString(INTENT_PACKAGE_NAME);

		TextView textView = (TextView) findViewById(R.id.question);
		textView.setText("Externe App \"" + appName + "\" starten?");

		View yesView = findViewById(R.id.yes);
		yesView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openExternalApp(packageName);
				finish();
			}
		});

		View noView = findViewById(R.id.no);
		noView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		activityStyling();
	}

	public void openExternalApp(String packageName) {
		PackageManager manager = getPackageManager();
		try {
			Intent intentToExternal = manager.getLaunchIntentForPackage(packageName);
			if (intentToExternal == null) {
				throw new ActivityNotFoundException();
			}
			intentToExternal.addCategory(Intent.CATEGORY_LAUNCHER);
			startActivity(intentToExternal);
		} catch (ActivityNotFoundException e) {
			String messageAppNotFound = "App mit dem Package-Namen \"" + packageName + "\" nicht gefunden.";
			Toast.makeText(getApplicationContext(), messageAppNotFound, Toast.LENGTH_SHORT).show();
		}
	}

	private void activityStyling() {
		Utils.activateActionBarBackButton(getSupportActionBar(), true);
		Utils.setActionBarTitle(getSupportActionBar(), "externe App starten");
		Utils.setStatusBarBlack(this);
		Utils.setActionBarBackgroundColor(getSupportActionBar(),
				GlobalConfigurationsManager.globalDefaults.defaultMenuBackgroundColor);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return false;
	}
}
