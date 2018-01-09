package de.feuerwehraumuehle.feuerwehrapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import de.feuerwehraumuehle.feuerwehrapp.R;
import de.feuerwehraumuehle.feuerwehrapp.model.FFile;
import de.feuerwehraumuehle.feuerwehrapp.model.FFileType;

/**
 * Created by Matze on 18.02.2017.
 */

public class FFileAdapter extends BaseAdapter {
	private Context mContext;
	private FFile FDirectory;

	public FFileAdapter(Context c, FFile FDirectory) {
		mContext = c;
		this.FDirectory = FDirectory;
	}

	public int getCount() {
		return FDirectory.getChildren().size();
	}

	public FFile getItem(int position) {
		return FDirectory.getChildren().get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.ffile_item, null);
			view.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 600));
		} else {
			view = convertView;
		}
		TextView name = (TextView) view.findViewById(R.id.category_name);
		FrameLayout container = (FrameLayout) view.findViewById(R.id.category_container);
		ImageView type = (ImageView) view.findViewById(R.id.category_type);
		FFile item = getItem(position);
		name.setText(item.getName());

		int color = item.getColor() != null ? Color.parseColor(item.getColor()) : Color.argb(255, 255, 255, 255);
		int textColor = item.getTextColor() != null ? Color.parseColor(item.getColor()) : Color.argb(255, 255, 255, 255);
		container.setBackgroundColor(color);
		name.setTextColor(textColor);
		type.setImageDrawable(mContext.getResources().getDrawable(item.getType() == FFileType.DIRECTORY ? R.mipmap
				.ic_folder_black_48dp : R
				.mipmap
				.ic_picture_as_pdf_black_48dp));

		return view;
	}
}
