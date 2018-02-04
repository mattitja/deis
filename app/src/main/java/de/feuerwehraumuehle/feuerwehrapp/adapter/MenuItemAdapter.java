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

import de.feuerwehraumuehle.feuerwehrapp.DEISApplication;
import de.feuerwehraumuehle.feuerwehrapp.R;
import de.feuerwehraumuehle.feuerwehrapp.manager.GlobalConfigurationsManager;
import de.feuerwehraumuehle.feuerwehrapp.model.Item;
import de.feuerwehraumuehle.feuerwehrapp.model.ItemType;

/**
 * Created by Matze on 18.02.2017.
 */

public class MenuItemAdapter extends BaseAdapter {

	private final String ICON_FOLDER_IMAGE_FILE_NAME = "icon_folder";
	private final String ICON_PDF_IMAGE_FILE_NAME = "icon_pdf";
	private final String ICON_LINK_IMAGE_FILE_NAME = "icon_link";

	private Context mContext;
	private Item currentItem;

	public MenuItemAdapter(Context c, Item currentItem) {
		mContext = c;
		this.currentItem = currentItem;
	}

	public int getCount() {
		return currentItem.getChildren().size();
	}

	public Item getItem(int position) {
		return currentItem.getChildren().get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

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
		loadImage(item.getIcon(), GlobalConfigurationsManager.globalDefaults.defaultIcon, icon);

		return view;
	}

	private void loadFolderImage(ImageView type, Item item) {
		if (item.getType() == ItemType.DIRECTORY) {
			loadImage(ICON_FOLDER_IMAGE_FILE_NAME, null, type);
		} else if (item.getType() == ItemType.PDF) {
			loadImage(ICON_PDF_IMAGE_FILE_NAME, null, type);
		} else if (item.getType() == ItemType.LINK) {
			loadImage(ICON_LINK_IMAGE_FILE_NAME, null, type);
		}
	}

	private void loadImage(String iconFileName, String defaultIconName, ImageView view) {
		File imgFile = new  File(DEISApplication.getIconsPath(), iconFileName + ".png");
		if(imgFile.exists()){
			Bitmap loadedIcon = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
			view.setImageBitmap(loadedIcon);
		} else if (defaultIconName != null){
			imgFile = new  File(DEISApplication.getIconsPath(), defaultIconName + ".png");
			if(imgFile.exists()){
				Bitmap loadedIcon = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				view.setImageBitmap(loadedIcon);
			} else {
				view.setImageDrawable(null);
			}
		} else {
			view.setImageDrawable(null);
		}
	}
}
