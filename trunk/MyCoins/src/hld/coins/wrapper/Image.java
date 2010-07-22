package hld.coins.wrapper;

import android.graphics.Bitmap;

public class Image {
	public Bitmap imgae;
	public int width;
	public int height;
	
	public Image() {
	}
	
	public Image(Bitmap image) {
		setImage(image);
	}
	
	public void setImage(Bitmap image){
		this.imgae = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
}
