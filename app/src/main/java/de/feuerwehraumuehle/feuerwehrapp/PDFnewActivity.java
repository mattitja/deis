package de.feuerwehraumuehle.feuerwehrapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.shockwave.pdfium.PdfDocument;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.List;

import de.feuerwehraumuehle.feuerwehrapp.manager.ConfigurationManager;
import de.feuerwehraumuehle.feuerwehrapp.helper.Utils;
import de.feuerwehraumuehle.feuerwehrapp.view.CustomScrollHandle;

@EActivity(R.layout.activity_pdfnew)
@OptionsMenu(R.menu.menu_pdf)
public class PDFnewActivity extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

	private static final String TAG = PDFnewActivity.class.getSimpleName();

	private final static int REQUEST_CODE = 42;
	public static final int PERMISSION_CODE = 42042;

	public static final String SAMPLE_FILE = "sample.pdf";
	public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

	String pdfPath;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ConfigurationManager.globalDefaults.defaultMenuBackgroundColor));
	}

	@Override
	protected void onResume() {
		super.onResume();
		Utils.setStatusBarColor(this);
		pdfPath = getIntent().getStringExtra("pdf_path");
		displayName = getIntent().getStringExtra("displayName");
		Uri uri = Uri.fromFile(new File(pdfPath));
		displayFromUri(uri);
	}

	@ViewById
	PDFView pdfView;

	@NonConfigurationInstance
	Uri uri;

	@NonConfigurationInstance
	Integer pageNumber = 0;

	String pdfFileName;

	String displayName;

	void launchPicker() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("application/pdf");
		try {
			startActivityForResult(intent, REQUEST_CODE);
		} catch (ActivityNotFoundException e) {
			//alert user that file manager not working
			Toast.makeText(this, "Fehler", Toast.LENGTH_SHORT).show();
		}
	}

	@AfterViews
	void afterViews() {
		pdfView.setBackgroundColor(Color.LTGRAY);
		if (uri != null) {
			displayFromUri(uri);
		} else {
			displayFromAsset(pdfFileName);
		}
		setTitle(displayName);
	}

	CustomScrollHandle customScrollHandle;

	private void displayFromAsset(String assetFileName) {
		pdfFileName = assetFileName;

		customScrollHandle = new CustomScrollHandle(this);

		pdfView.fromAsset(assetFileName)
				.defaultPage(pageNumber)
				.onPageChange(this)
				.enableAnnotationRendering(true)
				.onLoad(this)
				.scrollHandle(customScrollHandle)
				.spacing(10) // in dp
				.onLoad(new OnLoadCompleteListener() {
					@Override
					public void loadComplete(int nbPages) {
						customScrollHandle.setPageCount(pdfView.getPageCount());
					}
				})
				.load();
	}

	private void displayFromUri(Uri uri) {
		pdfFileName = getFileName(uri);

		customScrollHandle = new CustomScrollHandle(this);

		pdfView.fromUri(uri)
				.defaultPage(pageNumber)
				.onPageChange(this)
				.enableAnnotationRendering(true)
				.onLoad(this)
				.scrollHandle(customScrollHandle)
				.spacing(10) // in dp
				.onLoad(new OnLoadCompleteListener() {
					@Override
					public void loadComplete(int nbPages) {
						customScrollHandle.setPageCount(pdfView.getPageCount());
					}
				})
				.load();
	}

	@OnActivityResult(REQUEST_CODE)
	public void onResult(int resultCode, Intent intent) {
		if (resultCode == RESULT_OK) {
			uri = intent.getData();
			displayFromUri(uri);
		}
	}

	@Override
	public void onPageChanged(int page, int pageCount) {
		pageNumber = page;
		if (pageCount == 1) {
			setTitle(displayName);
		} else {
			setTitle(String.format("%s (Seite %s von %s)", displayName, page + 1, pageCount));
		}
	}

	public String getFileName(Uri uri) {
		String result = null;
		if (uri.getScheme().equals("content")) {
			Cursor cursor = getContentResolver().query(uri, null, null, null, null);
			try {
				if (cursor != null && cursor.moveToFirst()) {
					result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
				}
			} finally {
				if (cursor != null) {
					cursor.close();
				}
			}
		}
		if (result == null) {
			result = uri.getLastPathSegment();
		}
		return result;
	}

	@Override
	public void loadComplete(int nbPages) {
		PdfDocument.Meta meta = pdfView.getDocumentMeta();
		Log.e(TAG, "title = " + meta.getTitle());
		Log.e(TAG, "author = " + meta.getAuthor());
		Log.e(TAG, "subject = " + meta.getSubject());
		Log.e(TAG, "keywords = " + meta.getKeywords());
		Log.e(TAG, "creator = " + meta.getCreator());
		Log.e(TAG, "producer = " + meta.getProducer());
		Log.e(TAG, "creationDate = " + meta.getCreationDate());
		Log.e(TAG, "modDate = " + meta.getModDate());

		printBookmarksTree(pdfView.getTableOfContents(), "-");

	}

	public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
		for (PdfDocument.Bookmark b : tree) {

			Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

			if (b.hasChildren()) {
				printBookmarksTree(b.getChildren(), sep + "-");
			}
		}
	}

	/**
	 * Listener for response to user permission request
	 *
	 * @param requestCode  Check that permission request code matches
	 * @param permissions  Permissions that requested
	 * @param grantResults Whether permissions granted
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
										   @NonNull int[] grantResults) {
		if (requestCode == PERMISSION_CODE) {
			if (grantResults.length > 0
					&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				launchPicker();
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		} else if (item.getItemId() == R.id.root) {
			Intent intent = new Intent(PDFnewActivity.this, MainActivity.class);

			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

			startActivity(intent);
		} else if(item.getItemId() == R.id.scrolltop) {
			pdfView.jumpTo(0);
		}
		return false;
	}

}