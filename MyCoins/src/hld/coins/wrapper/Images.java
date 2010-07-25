package hld.coins.wrapper;

import android.graphics.Bitmap;
import android.graphics.Point;

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
	 * 绘制指定下标的图片，下标从0开始
	 * @param graphics
	 * @param x
	 * @param y
	 * @param index
	 */
	public abstract void draw(Graphics graphics, int x, int y, int index);
	
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
	 * 获取指定帧的宽度，下标从0开始
	 * @param index
	 * @return
	 */
	public abstract int getWidth(int index);
	
	/**
	 * 获取指定帧的高度，下标从0开始
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
		framesWidth = sourceImage.getWidth() / col;
		framesHeight = sourceImage.getHeight() / row;
		int count = row * col;
		framesX = new int[count];
		framesY = new int[count];
		int imageHeight = sourceImage.getHeight();
		int imageWidth = sourceImage.getWidth();
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
		graphics.drawImage(x, y, sourceImage, framesX[index], framesY[index], framesWidth,
				framesHeight);
	}
	
	@Override
	public int getWidth(int index) {
		return framesWidth;
	}
	
	@Override
	public int getHeight(int index) {
		return framesHeight;
	}
}

class MultiImages extends Images {
	private Bitmap[] sourceImage;
	
	public MultiImages(Bitmap image, Bitmap... images) {
		sourceImage = new Bitmap[images.length + 1];
		sourceImage[0] = image;
		for(int i = 1; i < sourceImage.length; i++) {
			sourceImage[i] = images[i - 1];
		}
	}
	
	@Override
	public void draw(Graphics graphics, int x, int y, int index) {
		graphics.drawImage(sourceImage[index], x, y);
	}
	
	@Override
	public int getWidth(int index) {
		return sourceImage[index].getWidth();
	}
	
	@Override
	public int getHeight(int index) {
		return sourceImage[index].getHeight();
	}
}
