package de.feuerwehraumuehle.feuerwehrapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import de.feuerwehraumuehle.feuerwehrapp.FeuerwehrApp;
import de.feuerwehraumuehle.feuerwehrapp.R;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;
import de.feuerwehraumuehle.feuerwehrapp.model.ItemType;

/**
 * Created by Matze on 18.02.2017.
 */

public class MenuItemAdapter extends BaseAdapter {

	private Context mContext;
	private Item FDirectory;

	public MenuItemAdapter(Context c, Item FDirectory) {
		mContext = c;
		this.FDirectory = FDirectory;
	}

	public int getCount() {
		return FDirectory.getChildren().size();
	}

	public Item getItem(int position) {
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
		Item item = getItem(position);
		name.setText(item.getDisplayName());

		container.setBackgroundColor(item.getButtonColor());
		name.setTextColor(item.getTextColor());

		loadFolderImage(type, item);

		loadImage(item.getIcon(), FeuerwehrApp.globalDefaults.defaultIcon, icon);

		return view;
	}

	private void loadFolderImage(ImageView type, Item item) {
		if (item.getType() == ItemType.DIRECTORY) {
			loadImage("icon_folder", "icon_folder", type);
		} else if (item.getType() == ItemType.PDF) {
			loadImage("icon_pdf", "icon_pdf", type);
		}
	}

	private void loadImage(String iconName, String defaultIconName, ImageView view) {
		File imgFile = new  File("/sdcard/feuerwehr/config/icons/" + iconName + ".png");
		if(imgFile.exists()){
			Bitmap loadedIcon = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			view.setImageBitmap(loadedIcon);
		} else {
			imgFile = new  File("/sdcard/feuerwehr/config/icons/" + defaultIconName + ".png");
			if(imgFile.exists()){
				Bitmap loadedIcon = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				view.setImageBitmap(loadedIcon);
			} else {
				view.setImageDrawable(null);
			}
		}
	}
}
