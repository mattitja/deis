package de.feuerwehraumuehle.feuerwehrapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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
		} else {
			view = convertView;
		}
		TextView name = (TextView) view.findViewById(R.id.category_name);
		View container = view.findViewById(R.id.category_container);
		ImageView type = (ImageView) view.findViewById(R.id.category_type);
		ImageView icon = (ImageView) view.findViewById(R.id.icon);
		FFile item = getItem(position);
		name.setText(item.getDisplayName());

		int buttonColor = item.getButtonColor() != null ? Color.parseColor(item.getButtonColor()) : Color.parseColor
				("gray");
		int textColor = item.getTextColor() != null ? Color.parseColor(item.getTextColor()) : Color.argb(255, 255, 255, 255);
		container.setBackgroundColor(buttonColor);
		name.setTextColor(textColor);
		type.setImageDrawable(mContext.getResources().getDrawable(item.getType() == FFileType.DIRECTORY ? R.mipmap
				.ic_folder_black_48dp : R
				.mipmap
				.ic_picture_as_pdf_black_48dp));
		if (item.getIcon() != null) {
			int iconId = mContext.getResources().getIdentifier(item.getIcon(), "drawable",
					mContext.getPackageName());
			if (iconId != 0) {
				icon.setImageDrawable(mContext.getDrawable(iconId));
			}
		} else {
			icon.setImageDrawable(null);
		}


		return view;
	}
}
