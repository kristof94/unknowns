package hld.coins.manager;

import java.io.FileDescriptor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import hld.coins.wrapper.Image;
import hld.coins.wrapper.Images;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.Config;

public final class BitmapManager {

	private final static Map<Integer, Bitmap> globalMap = new HashMap<Integer, Bitmap>();
	private final static Map<Class<?>, List<Bitmap>> viewMap = new HashMap<Class<?>, List<Bitmap>>();

	private Activity activity;
	private static BitmapManager instance;

	public Image getViewImage(Class<?> view, int drawableId) {
		return getViewScaledImage(view, drawableId, 1, false);
	}

	/**
	 * 长度与宽度使用同一种缩放比例
	 * 
	 * @param view
	 * @param drawableId
	 * @param scale
	 *            长度与宽度使用同一种缩放比例
	 * @param filter
	 * @return
	 */
	public Image getViewScaledImage(Class<?> view, int drawableId,
			double scale, boolean filter) {
		Image image = new Image();
		Bitmap resource = BitmapFactory.decodeResource(activity.getResources(),
				drawableId);
		if (scale != 1) {
			Bitmap bitmap = Bitmap.createScaledBitmap(resource,
					(int) (resource.getWidth() * scale),
					(int) (resource.getHeight() * scale), filter);
			put(view, bitmap);
			resource.recycle();
			image.setImage(bitmap);
			return image;
		}
		put(view, resource);
		image.setImage(resource);
		return image;
	}

	/**
	 * 长度与宽度分开使用各自缩放比例
	 * 
	 * @param view
	 * @param drawableId
	 * @param widthRate
	 *            宽度缩放比例
	 * @param heightRate
	 *            长度缩放比例
	 * @param filter
	 * @return
	 */
	public Image getViewScaledImage(Class<?> view, int drawableId,
			double widthRate, double heightRate, boolean filter) {
		Image image = new Image();
		Bitmap resource = BitmapFactory.decodeResource(activity.getResources(),
				drawableId);
		if (widthRate != 1 || heightRate != 1) {
			Bitmap bitmap = Bitmap.createScaledBitmap(resource,
					(int) (resource.getWidth() * widthRate),
					(int) (resource.getHeight() * heightRate), filter);
			put(view, bitmap);
			resource.recycle();
			image.setImage(bitmap);
			return image;
		}
		put(view, resource);
		image.setImage(resource);
		return image;
	}

	// //////////////////////////////////////////////////////////////////

	public Image getViewImage(Class<?> view, Image resource) {
		return getViewScaledImage(view, resource, 1, false);
	}

	/**
	 * 长度与宽度分开使用各自缩放比例
	 * 
	 * @param view
	 * @param resource
	 * @param widthRate
	 *            宽度缩放比例
	 * @param heightRate
	 *            长度缩放比例
	 * @param filter
	 * @return
	 */
	public Image getViewScaledImage(Class<?> view, Image resource,
			double widthRate, double heightRate, boolean filter) {
		Image image = new Image();
		if (widthRate != 1 || heightRate != 1) {
			Bitmap bitmap = Bitmap.createScaledBitmap(resource.imgae,
					(int) (resource.width * widthRate),
					(int) (resource.height * heightRate), filter);
			put(view, bitmap);
			image.setImage(bitmap);
			return image;
		}
		return resource;
	}

	/**
	 * 长度与宽度使用同一种缩放比例
	 * 
	 * @param view
	 * @param resource
	 * @param scale
	 *            长度与宽度使用同一种缩放比例
	 * @param filter
	 * @return
	 */
	public Image getViewScaledImage(Class<?> view, Image resource,
			double scale, boolean filter) {
		Image image = new Image();
		if (scale != 1) {
			Bitmap bitmap = Bitmap.createScaledBitmap(resource.imgae,
					(int) (resource.width * scale),
					(int) (resource.height * scale), filter);
			put(view, bitmap);
			image.setImage(bitmap);
			return image;
		}
		return resource;
	}
	
	// ///////////////////////////////////////////////////////////////////////
	
	/**
	 * 获取多帧图片
	 * @param view
	 * @param scale 长度与宽度使用同一种缩放比例
	 * @param filter
	 * @param drawableId
	 * @param col 帧的列数
	 * @param row 帧的行数
	 * @return
	 */
	public Images getViewScaledImages(Class<?> view, int col, int row,
			double scale, boolean filter, int drawableId) {
		Bitmap resource = BitmapFactory.decodeResource(activity.getResources(), drawableId);
		if(scale!=1) {
			Bitmap bitmap = Bitmap.createScaledBitmap(resource,
					(int) (resource.getWidth() * scale),
					(int) (resource.getHeight() * scale), filter);
			resource.recycle();
			resource = bitmap;
		}
		put(view, resource);
		return Images.createImages(resource, col, row);
	}
	
	/**
	 * 获取多帧图片
	 * @param view
	 * @param scale 长度与宽度使用同一种缩放比例
	 * @param filter
	 * @param drawableId
	 * @param drawable
	 * @return
	 */
	public Images getViewScaledImages(Class<?> view,
			double scale, boolean filter, int drawableId, int... drawable) {
		Bitmap resource = BitmapFactory.decodeResource(activity.getResources(), drawableId);
		if(scale!=1) {
			Bitmap bitmap = Bitmap.createScaledBitmap(resource,
					(int) (resource.getWidth() * scale),
					(int) (resource.getHeight() * scale), filter);
			resource.recycle();
			resource = bitmap;
		}
		put(view, resource);
		Bitmap[] images = new Bitmap[drawable.length];
		for(int i = 0; i<images.length; i++) {
			Bitmap image = BitmapFactory.decodeResource(activity.getResources(), drawable[i]);
			if(scale!=1) {
				Bitmap bitmap = Bitmap.createScaledBitmap(image,
						(int) (image.getWidth() * scale),
						(int) (image.getHeight() * scale), filter);
				image.recycle();
				image = bitmap;
			}
			put(view, image);
			images[i] = image;
		}
		return Images.createImages(resource, images);
	}

	// ///////////////////////////////////////////////////////////////////////
	public Image createBitmap(Class<?> view, int[] pixels, int width,
			int height, Bitmap.Config config) {
		return createBitmap(view, pixels, width, height,
				Bitmap.Config.ARGB_4444, false);
	}

	public Image createBitmap(Class<?> view, int[] pixels, int width,
			int height, Bitmap.Config config, boolean isMutable) {
		return createBitmap(view, pixels, 0, width, width, height, config,
				isMutable);
	}

	public Image createBitmap(Class<?> view, int[] pixels, int offset,
			int stride, int width, int height, Config config, boolean isMutable) {
		Image image = new Image();
		Bitmap bitmap = Bitmap.createBitmap(pixels, offset, stride, width,
				height, config);
		if (isMutable) {
			Bitmap copy = bitmap.copy(config, isMutable);
			put(view, copy);
			bitmap.recycle();
			image.setImage(copy);
			return image;
		}
		put(view, bitmap);
		image.setImage(bitmap);
		return image;
	}

	/**
	 * @param view
	 * @param offset
	 *            Number of values to skip before the first color in the array
	 *            of colors.
	 * 
	 * @param stride
	 *            Number of colors in the array between rows (must be >= width
	 *            or <= -width).
	 * @param width
	 * @param height
	 * @param config
	 *            The bitmap config to create. If the config does not support
	 *            per-pixel alpha (e.g. RGB_565), then the alpha bytes in the
	 *            colors[] will be ignored (assumed to be FF)
	 * @param isMutable
	 *            The bitmap config to create. If the config does not support
	 *            per-pixel alpha (e.g. RGB_565), then the alpha bytes in the
	 *            colors[] will be ignored (assumed to be FF)
	 * @return
	 */
	public Image createBitmap(Class<?> view, int offset, int stride, int width,
			int height, Config config, boolean isMutable) {
		Image image = new Image();
		Bitmap bitmap = Bitmap.createBitmap(new int[width * height], offset,
				stride, width, height, config);
		if (isMutable) {
			Bitmap copy = bitmap.copy(config, isMutable);
			put(view, copy);
			bitmap.recycle();
			image.setImage(copy);
			return image;
		}
		put(view, bitmap);
		image.setImage(bitmap);
		return image;
	}

	public Image createBitmap(Class<?> view, Image resource, int width,
			int height, Config config, boolean isMutable, boolean filter) {
		Image image = new Image();
		Bitmap bitmap = Bitmap.createBitmap(resource.imgae, 0, 0, width,
				height, new Matrix(), true);
		put(view, bitmap);
		if (isMutable) {
			Bitmap copy = bitmap.copy(config, isMutable);
			put(view, copy);
			image.setImage(copy);
			return image;
		}
		put(view, bitmap);
		image.setImage(bitmap);
		return image;
	}

	private void put(Class<?> view, Bitmap resource) {
		List<Bitmap> list = viewMap.get(view);
		if (list == null) {
			list = new ArrayList<Bitmap>();
		}
		list.add(resource);
		viewMap.put(view, list);
	}

	// //////////////////////////////////////全局图片//////////////////////////////////////////////
	public Image getGolbalImage(int drawableId) {
		return getGlobalScaledImage(drawableId, 1, false);
	}

	public Image getGlobalScaledImage(int drawableId, double scale,
			boolean filter) {
		Image image = new Image();
		Bitmap bitmap = globalMap.get(drawableId);
		if(bitmap==null) {
			bitmap = BitmapFactory.decodeResource(activity.getResources(),
					drawableId);
			if (scale != 1) {
				Bitmap resource = Bitmap.createScaledBitmap(bitmap,
						(int) (bitmap.getWidth() * scale),
						(int) (bitmap.getHeight() * scale), filter);
				bitmap.recycle();
				bitmap = resource;
			}
			put(drawableId, bitmap);
		}
		image.setImage(bitmap);
		return image;
	}
	
	public Images getGolbalImages(int drawableId, int... drawable) {
		Bitmap[] images = new Bitmap[drawable.length];
		for(int i = 0; i<images.length; i++) {
			images[i] = globalMap.get(drawable[i]);
		}
		return Images.createImages(globalMap.get(drawableId), images);
	}

	public Image createGobalBitmap(int id, int[] pixels, int width, int height,
			Bitmap.Config config) {
		return createGobalBitmap(id, pixels, width, height,
				Bitmap.Config.ARGB_4444, false);
	}

	public Image createGobalBitmap(int id, int[] pixels, int width, int height,
			Bitmap.Config config, boolean isMutable) {
		return createGobalBitmap(id, pixels, 0, width, width, height, config, false);
	}

	public Image createGobalBitmap(int id, int[] pixels, int offset, int stride,
			int width, int height, Config config, boolean isMutable) {
		Image image = new Image();
		Bitmap bitmap = globalMap.get(id);
		if(bitmap==null) {
			bitmap = Bitmap.createBitmap(pixels, offset, stride, width,
					height, config);
			if (isMutable) {
				Bitmap copy = bitmap.copy(config, isMutable);
				bitmap.recycle();
				bitmap = copy;
			}
			put(id, bitmap);
		}
		image.setImage(bitmap);
		return image;
	}

	public int[] getImagePixels(int drawableId, double scale, boolean filter) {
		int[] pixels;
		Bitmap resource = BitmapFactory.decodeResource(activity.getResources(),
				drawableId);
		if (scale != 1) {
			Bitmap bitmap = Bitmap.createScaledBitmap(resource,
					(int) (resource.getWidth() * scale),
					(int) (resource.getHeight() * scale), filter);
			resource.recycle();
			pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
			bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0,
					bitmap.getWidth(), bitmap.getHeight());
			bitmap.recycle();
			return pixels;
		}
		pixels = new int[resource.getWidth() * resource.getHeight()];
		resource.getPixels(pixels, 0, resource.getWidth(), 0, 0,
				resource.getWidth(), resource.getHeight());
		resource.recycle();
		return pixels;

	}
	
	public Bitmap getGoablBitmap(int id, FileDescriptor fd, double scale, boolean filter) {
		Bitmap bitmap = globalMap.get(id);
		if(bitmap!=null) return bitmap;
		Bitmap src = BitmapFactory.decodeFileDescriptor(fd);
		Bitmap createScaledBitmap = Bitmap.createScaledBitmap(src,
				(int) (src.getWidth() * scale),
				(int) (src.getHeight() * scale), filter);
		src.recycle();
		put(id, createScaledBitmap);
		return createScaledBitmap;
	}

	public Bitmap getGoablBitmap(int id, InputStream in, double scale, boolean filter) {
		Bitmap bitmap = globalMap.get(id);
		if(bitmap!=null) return bitmap;
		Bitmap src = BitmapFactory.decodeStream(in);
		Bitmap createScaledBitmap = Bitmap.createScaledBitmap(src,
				(int) (src.getWidth() * scale),
				(int) (src.getHeight() * scale), filter);
		src.recycle();
		put(id, createScaledBitmap);
		return createScaledBitmap;
	}

	private void put(Integer id, Bitmap bitmap) {
		globalMap.put(id, bitmap);
	}
	// //////////////////////////////////清理图片/////////////////////////////////////////

	public void releaseGobalImage() {
		for (Bitmap bitmap : globalMap.values()) {
			bitmap.recycle();
		}
		globalMap.clear();
	}

	public void releaseAllImage() {
		releaseGobalImage();
		Set<Entry<Class<?>, List<Bitmap>>> entrySet = viewMap.entrySet();
		for (Entry<Class<?>, List<Bitmap>> entry : entrySet) {
			List<Bitmap> list = entry.getValue();
			for (Bitmap bitmap : list) {
				bitmap.recycle();
			}
		}
		viewMap.clear();
	}

	public void releaseViewImage(Class<?> view) {
		List<Bitmap> list = viewMap.get(view);
		if (list == null || list.size() == 0)
			return;
		for (Bitmap bitmap : list) {
			bitmap.recycle();
		}
		viewMap.remove(view);
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public static BitmapManager getInstance() {
		if (instance == null) {
			instance = new BitmapManager();
		}
		return instance;
	}

}
