package de.miroit.deis.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by mmi on 31.01.2018.
 */

public class SquareView extends RelativeLayout {

	public SquareView(Context context) {
		super(context);
	}

	public SquareView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SquareView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
	}
}
