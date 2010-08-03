package hld.coins.wrapper;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

public abstract class Images {
	/**
	 * 将一张图片按照行列数分成若干帧
	 * @param image
	 * @param col
	 * @param row
	 * @return
	 */
	public static Images createImages(Bitmap image, int col, int row) {
		return new SingleImages(image, col, row);
	}
	
	/**
	 * 将每张图片当做一帧
	 * @param image
	 * @param images
	 * @return
	 */
	public static Images createImages(Bitmap image, Bitmap... images) {
		return new MultiImages(image, images);
	}
	
	/**
	 * 绘制图片，供只有两帧图片的类调用
	 * @param graphics
	 * @param x
	 * @param y
	 * @param flag false表示绘制第一帧图片，true表示绘制第二帧图片
	 */
	public void draw(Graphics graphics, Point point, boolean flag) {
		draw(graphics, point.x, point.y, flag?1:0);
	}
	
	/**
	 * 绘制指定索引的图片，索引从0开始
	 * @param graphics
	 * @param x
	 * @param y
	 * @param index
	 */
	public abstract void draw(Graphics graphics, int x, int y, int index);
	
	/**
	 * 在指定矩形内绘制图片，供只有两帧图片的类调用
	 * @param graphics
	 * @param rect
	 * @param flag false表示绘制第一帧图片，true表示绘制第二帧图片
	 */
	public void draw(Graphics graphics, Rect rect, boolean flag) {
		draw(graphics, rect, flag?1:0);
	}
	
	/**
	 * 在指定矩形内绘制指定索引的图片，索引从0开始
	 * @param graphics
	 * @param rect
	 * @param index
	 */
	public abstract void draw(Graphics graphics, Rect rect, int index);
	
	/**
	 * 获取首帧的宽度
	 * @return
	 */
	public int getWidth() {
		return getWidth(0);
	}
	
	/**
	 * 获取首帧的高度
	 * @return
	 */
	public int getHeight() {
		return getHeight(0);
	}
	
	/**
	 * 获取指定帧的宽度，索引从0开始
	 * @param index
	 * @return
	 */
	public abstract int getWidth(int index);
	
	/**
	 * 获取指定帧的高度，索引从0开始
	 * @param index
	 * @return
	 */
	public abstract int getHeight(int index);
}

class SingleImages extends Images {
	private Bitmap sourceImage;
	
	private int framesWidth;
	
	private int framesHeight;
	
	private int[] framesX;
	
	private int[] framesY;
	
	public SingleImages(Bitmap image, int col, int row) {
		sourceImage = image;
		int imageWidth = sourceImage.getWidth();
		int imageHeight = sourceImage.getHeight();
		framesWidth = imageWidth / col;
		if(imageWidth%col!=0) framesWidth++;
		framesHeight = imageHeight / row;
		if(imageHeight%row!=0) framesHeight++;
		int count = row * col;
		framesX = new int[count];
		framesY = new int[count];
		int i = 0;
		for(int y = 0; y < imageHeight; y += framesHeight) {
			for(int x = 0; x < imageWidth; x += framesWidth) {
				framesX[i] = x;
				framesY[i] = y;
				i++;
			}
		}
	}
	
	@Override
	public void draw(Graphics graphics, int x, int y, int index) {
		graphics.drawImage(x, y, sourceImage, framesX[index], framesY[index], framesWidth, framesHeight);
	}
	
	@Override
	public int getWidth(int index) {
		return framesWidth;
	}
	
	@Override
	public int getHeight(int index) {
		return framesHeight;
	}
	
	@Override
	public void draw(Graphics graphics, Rect rect, int index) {
		graphics.drawImage(rect.left, rect.top, sourceImage, framesX[index], framesY[index], framesWidth, framesHeight);
	}
}

class MultiImages extends Images {
	private Bitmap[] sourceImage;
	
	private int[] imageWidth;
	
	private int[] imageHeight;
	
	public MultiImages(Bitmap image, Bitmap... images) {
		sourceImage = new Bitmap[images.length + 1];
		imageWidth = new int[sourceImage.length];
		imageHeight = new int[sourceImage.length];
		sourceImage[0] = image;
		imageWidth[0] = image.getWidth();
		imageHeight[0] = image.getHeight();
		for(int i = 1; i < sourceImage.length; i++) {
			image = images[i - 1];
			sourceImage[i] = image;
			imageWidth[i] = image.getWidth();
			imageHeight[i] = image.getHeight();
		}
	}
	
	@Override
	public void draw(Graphics graphics, int x, int y, int index) {
		graphics.drawImage(sourceImage[index], x, y);
	}
	
	@Override
	public int getWidth(int index) {
		return imageWidth[index];
	}
	
	@Override
	public int getHeight(int index) {
		return imageHeight[index];
	}
	
	@Override
	public void draw(Graphics graphics, Rect rect, int index) {
		graphics.drawImage(rect.left, rect.top, sourceImage[index], 0, 0, imageWidth[index], imageHeight[index]);
	}
}
