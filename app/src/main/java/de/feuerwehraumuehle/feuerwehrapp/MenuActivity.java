package de.feuerwehraumuehle.feuerwehrapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import de.feuerwehraumuehle.feuerwehrapp.adapter.MenuItemAdapter;
import de.feuerwehraumuehle.feuerwehrapp.helper.Utils;
import de.feuerwehraumuehle.feuerwehrapp.manager.ConfigurationManager;
import de.feuerwehraumuehle.feuerwehrapp.manager.FileManager;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;
import de.feuerwehraumuehle.feuerwehrapp.model.ItemType;
import de.feuerwehraumuehle.feuerwehrapp.model.Link;

public class MenuActivity extends AppCompatActivity {

	private final static String INTENT_NUMERIC_PATH_TO_CURRENT_ITEM = "numericPathToCurrentItem";

	private boolean mainMenu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		determineIfMainMenu();
		ArrayList<Integer> numericPathToCurrentItem = checkForExistingPath();
		Item currentRootItem = navigateToCurrentItem(numericPathToCurrentItem);

		activityStyling(currentRootItem.getDisplayName());

		buildMenuView(numericPathToCurrentItem, currentRootItem);
	}

	private void determineIfMainMenu() {
		mainMenu = !getIntent().hasExtra(INTENT_NUMERIC_PATH_TO_CURRENT_ITEM);
	}

	private ArrayList<Integer> checkForExistingPath() {
		ArrayList<Integer> numericPathToCurrentItem = getIntent().getIntegerArrayListExtra(INTENT_NUMERIC_PATH_TO_CURRENT_ITEM);
		if (mainMenu) {
			numericPathToCurrentItem = new ArrayList<>();
		}
		return numericPathToCurrentItem;
	}

	private Item navigateToCurrentItem(ArrayList<Integer> numericPathToCurrentItem) {
		Item currentItem = FileManager.getInstance().getRootItem();

		for (int position : numericPathToCurrentItem) {
			currentItem = currentItem.getChildren().get(position);
		}
		return currentItem;
	}

	private void activityStyling(String currentItemDisplayName) {
		if (mainMenu) {
			Utils.activateActionBarBackButton(getSupportActionBar(), true);
			Utils.setActionBarTitle(getSupportActionBar(), "Hauptmenü");
		} else {
			Utils.setActionBarTitle(getSupportActionBar(), currentItemDisplayName);
		}
		Utils.setStatusBarBlack(this);
		Utils.setActionBarBackgroundColor(getSupportActionBar(),
				ConfigurationManager.globalDefaults.defaultMenuBackgroundColor);
		View backgroundLayout = findViewById(R.id.background);
		backgroundLayout.setBackgroundColor(ConfigurationManager.globalDefaults.defaultBackgroundColor);

	}

	private void buildMenuView(ArrayList<Integer> numericPathToCurrentItem, final Item currentRootItem) {
		final ArrayList<Integer> numericPathToNextItem = new ArrayList<>(numericPathToCurrentItem);

		MenuItemAdapter adapter = new MenuItemAdapter(this, currentRootItem);
		GridView gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Item clickedItem = currentRootItem.getChildren().get(position);
				if (clickedItem.getType() == ItemType.DIRECTORY) {
					numericPathToNextItem.add(position);
					startMenuActivityDeeper(numericPathToNextItem);
					numericPathToNextItem.clear();
				} else if (clickedItem.getType() == ItemType.PDF) {
					startPDFActivity(clickedItem);
				} else if ((clickedItem.getType() == ItemType.LINK)) {
					openExternalApp(MenuActivity.this, ((Link) clickedItem).getPackageName());
				} else {
					Toast.makeText(getApplicationContext(), clickedItem.getType().toString(), Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	private void startMenuActivityDeeper(ArrayList<Integer> numericPathToNextItem) {
		Bundle bundle = new Bundle();
		bundle.putIntegerArrayList(INTENT_NUMERIC_PATH_TO_CURRENT_ITEM, numericPathToNextItem);
		Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	private void startPDFActivity(Item clickedItem) {
		Intent intent = new Intent(MenuActivity.this, PDFnewActivity_.class);
		intent.putExtra("pdf_path", clickedItem.getAbsolutePath());
		intent.putExtra("displayName", clickedItem.getDisplayName());
		startActivity(intent);
	}

	public void openExternalApp(Context context, String packageName) {
		PackageManager manager = context.getPackageManager();
		try {
			Intent intentToExternal = manager.getLaunchIntentForPackage(packageName);
			if (intentToExternal == null) {
				throw new ActivityNotFoundException();
			}
			intentToExternal.addCategory(Intent.CATEGORY_LAUNCHER);
			context.startActivity(intentToExternal);
		} catch (ActivityNotFoundException e) {
			String messageAppNotFound = "App mit dem Package-Namen \"" + packageName + "\" nicht gefunden.";
			Toast.makeText(getApplicationContext(), messageAppNotFound, Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		MenuItem item = menu.findItem(R.id.root);
		if (mainMenu) {
			item.setVisible(false);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		} else if (item.getItemId() == R.id.root) {
			Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
		}
		return true;
	}
}
