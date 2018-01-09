package de.feuerwehraumuehle.feuerwehrapp.view;

import android.content.Context;

import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

/**
 * Created by Matze on 09.01.2018.
 */

public class CustomScrollHandle extends DefaultScrollHandle {

    private long pageCount = 0;

    public CustomScrollHandle(Context context) {
        super(context);
        setTextSize(12);
    }

    @Override
    public void setPageNum(int pageNum) {
        String text = String.valueOf(pageNum) + " von " + pageCount;
        if (!textView.getText().equals(text)) {
            textView.setText(text);
        }
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public void hideDelayed() {

    }
}
