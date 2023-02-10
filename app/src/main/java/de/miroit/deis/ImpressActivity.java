package de.miroit.deis;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import de.miroit.deis.helper.Utils;
import de.miroit.deis.manager.GlobalConfigurationsManager;

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
