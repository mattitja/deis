package de.feuerwehraumuehle.feuerwehrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import de.feuerwehraumuehle.feuerwehrapp.helper.Utils;
import de.feuerwehraumuehle.feuerwehrapp.manager.GlobalConfigurationsManager;

public class ImpressActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		activityStyling();
	}

	private void activityStyling() {
		Utils.activateActionBarBackButton(getSupportActionBar(), true);
		Utils.setActionBarTitle(getSupportActionBar(), "Impressum");
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
