package de.feuerwehraumuehle.feuerwehrapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.feuerwehraumuehle.feuerwehrapp.helper.Utils;
import es.voghdev.pdfviewpager.library.PDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;

public class PDFActivity extends AppCompatActivity {

    String pdfPath;

    PDFViewPager pdfViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.setStatusBarColor(this);

        pdfPath = getIntent().getStringExtra("pdf_path");
        if (pdfPath == null) {
            return;
        }
        pdfViewPager = new PDFViewPager(this, pdfPath);
        setContentView(pdfViewPager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ((PDFPagerAdapter) pdfViewPager.getAdapter()).close();
    }
}
