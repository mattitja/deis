package de.miroit.deis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import de.miroit.deis.helper.Utils;
import de.miroit.deis.manager.GlobalConfigurationsManager;

public class BackToHomeActivity extends AppCompatActivity {

	public static final int WOULD_WE_GO_BACK_TO_HOME = 42;
	public static final int USER_SAID_YES = 1337;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_back_to_home);

		View yesView = findViewById(R.id.yes);
		yesView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(USER_SAID_YES);
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

	private void activityStyling() {
		Utils.activateActionBarBackButton(getSupportActionBar(), true);
		Utils.setActionBarTitle(getSupportActionBar(), "zurück ins Hauptmenü");
		Utils.setStatusBarBlack(this);
		Utils.setActionBarBackgroundColor(getSupportActionBar(),
				GlobalConfigurationsManager.globalDefaults.defaultMenuBackgroundColor);
	}

	public static void askIfWeShouldGoBackToHome(AppCompatActivity activity) {
		Intent intent = new Intent(activity, BackToHomeActivity.class);
		activity.startActivityForResult(intent, WOULD_WE_GO_BACK_TO_HOME);
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
