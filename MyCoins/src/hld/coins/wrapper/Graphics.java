package hld.coins.wrapper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.graphics.Region.Op;

public class Graphics {
	public static final int HCENTER = 1;
	public static final int VCENTER = 2;
	public static final int LEFT = 4;
	public static final int RIGHT = 8;
	public static final int TOP = 16;
	public static final int BOTTOM = 32;
	public static final int BASELINE = 64;
	public static final int SOLID = 0;
	public static final int DOTTED = 1;
	private Canvas canvas;
	private Paint paint;
	private RectF rectF;
	private Rect rectI;
	private Font currentFont = Font.getDefaultFont();
	
	private void init() {
		rectI = new Rect();
		rectF = new RectF();
		paint = new Paint();
		paint.setColor(Color.WHITE);
		currentFont = new Font(paint.getTypeface(), 10);
	}
	
	public Graphics() {
		init();
	}
	
	public void setStyle(Style style) {
		paint.setStyle(style);
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	
	public void drawColor(int color) {
		this.canvas.drawColor(color);
	}
	
	public void setARGBColor(int ARGB) {
		paint.setColor(ARGB);
	}
	
	public void setAntiAlias(boolean b) {
		paint.setAntiAlias(b);
	}
	
	public void setFilterBitmap(boolean filter) {
		paint.setFilterBitmap(filter);
	}
	
	public void clipRect(int x, int y, int width, int height) {
		rectI.set(x, y, x + width, y + height);
		canvas.clipRect(rectI, Op.REPLACE);
	}
	
	public void drawLine(float stratX, float stratY, float endX, float endY) {
		canvas.drawLine(stratX, stratY, endX, endY, paint);
	}
	
	public void drawPoint(float x, float y) {
		canvas.drawPoint(x, y, paint);
	}
	
	public void drawPath(Path path) {
		canvas.drawPath(path, paint);
	}
	
	public void fillRect(int x, int y, int width, int height) {
		rectF.set(x, y, x + width, y + height);
		Style style = paint.getStyle();
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(rectF, paint);
		paint.setStyle(style);
	}
	
	public void drawRect(int x, int y, int width, int height) {
		rectF.set(x, y, x + width, y + height);
		Style style = paint.getStyle();
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(rectF, paint);
		paint.setStyle(style);
	}
	
	public void drawRect(Rect r) {
		Style style = paint.getStyle();
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(new RectF(r), paint);
		paint.setStyle(style);
	}
	
	public void drawRoundRect(int x, int y, int width, int height, int rx, int ry) {
		rectF.set(x, y, x + width, y + height);
		Style style = paint.getStyle();
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRoundRect(rectF, rx, ry, paint);
		paint.setStyle(style);
	}
	
	public void fillRoundRect(int x, int y, int width, int height, int rx, int ry) {
		rectF.set(x, y, x + width, y + height);
		Style style = paint.getStyle();
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRoundRect(rectF, rx, ry, paint);
		paint.setStyle(style);
	}
	
	public void fillArc(int x, int y, int width, int height, int startAngle, int sweepAngle) {
		fillArc(x, y, width, height, startAngle, sweepAngle, false);
	}
	
	public void fillArc(int x, int y, int width, int height, int startAngle, int sweepAngle,
			boolean useCenter) {
		rectF.set(x, y, x + width, y + height);
		Style style = paint.getStyle();
		paint.setStyle(Paint.Style.FILL);
		canvas.drawArc(rectF, startAngle, sweepAngle, useCenter, paint);
		paint.setStyle(style);
	}
	
	public void drawArc(int x, int y, int width, int height, int startAngle, int sweepAngle) {
		drawArc(x, y, width, height, startAngle, sweepAngle, false);
	}
	
	public void drawArc(int x, int y, int width, int height, int startAngle, int sweepAngle,
			boolean useCenter) {
		rectF.set(x, y, x + width, y + height);
		Style style = paint.getStyle();
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawArc(rectF, startAngle, sweepAngle, useCenter, paint);
		paint.setStyle(style);
	}
	
	// //////////////////////////////////////////////////////////////////////
	public void drawString(String paramString, int x, int y, int align) {
		switch(align) {
		case LEFT | TOP:
			y += currentFont.getHeight();
		case LEFT | BOTTOM:
			paint.setTextAlign(Paint.Align.LEFT);
			break;
		case TOP | RIGHT:
			y += currentFont.getHeight();
		case RIGHT | BOTTOM:
			paint.setTextAlign(Paint.Align.RIGHT);
			break;
		case TOP | HCENTER:
			y += currentFont.getHeight();
		case BOTTOM | HCENTER:
			paint.setTextAlign(Paint.Align.CENTER);
			break;
		default:
			throw new IllegalArgumentException();
		}
		canvas.drawText(paramString, x, y, paint);
	}
	
	public void drawSubstring(String text, int start, int end, int x, int y, int align) {
		switch(align) {
		case LEFT | TOP:
			end += currentFont.getHeight();
		case LEFT | BOTTOM:
			paint.setTextAlign(Paint.Align.LEFT);
			break;
		case TOP | RIGHT:
			end += currentFont.getHeight();
		case RIGHT | BOTTOM:
			paint.setTextAlign(Paint.Align.RIGHT);
			break;
		case TOP | HCENTER:
			end += currentFont.getHeight();
		case BOTTOM | HCENTER:
			paint.setTextAlign(Paint.Align.CENTER);
			break;
		default:
			throw new IllegalArgumentException();
		}
		canvas.drawText(text, start, end, x, y, paint);
	}
	
	public void drawChar(char paramChar, int x, int y, int align) {
		drawString(String.valueOf(paramChar), x, y, align);
	}
	
	public void drawChars(char[] paramArrayOfChar, int index, int count, int x, int y, int align) {
		switch(align) {
		case LEFT | TOP:
			y += currentFont.getHeight();
		case LEFT | BOTTOM:
			paint.setTextAlign(Paint.Align.LEFT);
			break;
		case TOP | RIGHT:
			y += currentFont.getHeight();
		case RIGHT | BOTTOM:
			paint.setTextAlign(Paint.Align.RIGHT);
			break;
		case TOP | HCENTER:
			y += currentFont.getHeight();
		case BOTTOM | HCENTER:
			paint.setTextAlign(Paint.Align.CENTER);
			break;
		default:
			throw new IllegalArgumentException();
		}
		canvas.drawText(paramArrayOfChar, index, count, x, y, paint);
	}
	
	public void drawImage(Bitmap paramImage, Point point) {
		drawImage(paramImage, point.x, point.y);
	}
	
	public void drawImage(Bitmap paramImage, int x, int y) {
		canvas.drawBitmap(paramImage, x, y, paint);
	}
	
	public void drawImage(Bitmap paramImage, int x, int y, int align) {
		byte ud = 0;
		byte lr = 0;
		if((align & TOP) == TOP) {
			ud++;
		}
		if((align & VCENTER) == VCENTER) {
			ud++;
			y -= (paramImage.getHeight() >> 1);
		}
		if((align & BOTTOM) == BOTTOM) {
			ud++;
			y -= paramImage.getHeight();
		}
		if((align & LEFT) == LEFT) {
			lr++;
		}
		if((align & HCENTER) == HCENTER) {
			lr++;
			x -= (paramImage.getWidth() >> 1);
		}
		if((align & RIGHT) == RIGHT) {
			lr++;
			x -= paramImage.getWidth();
		}
		if(lr != 1 || ud != 1) {
			throw new IllegalArgumentException();
		}
		canvas.drawBitmap(paramImage, x, y, paint);
	}
	
	public void drawImage(Images images, Point point, boolean flag) {
		images.draw(this, point, flag);
	}
	
	public void drawImage(Images images, int x, int y, int index) {
		images.draw(this, x, y, index);
	}
	
	public void drawRGB(ImageArray image, int x, int y, int align) {
		drawRGB(image.piexls, 0, image.width, x, y, image.width, image.height, true, align);
	}
	
	public void drawRGB(int[] colors, int x, int y, int width, int height, boolean hasAlpha,
			int align) {
		drawRGB(colors, 0, width, x, y, width, height, hasAlpha, align);
	}
	
	public void drawRGB(int[] colors, int x, int y, int width, int height, int align) {
		drawRGB(colors, 0, width, x, y, width, height, true, align);
	}
	
	public void drawRGB(int[] colors, int offset, int stride, int x, int y, int width, int height,
			boolean hasAlpha, int align) {
		byte ud = 0;
		byte lr = 0;
		if((align & TOP) == TOP) {
			ud++;
		}
		if((align & VCENTER) == VCENTER) {
			ud++;
			y -= (height >> 1);
		}
		if((align & BOTTOM) == BOTTOM) {
			ud++;
			y -= height;
		}
		if((align & LEFT) == LEFT) {
			lr++;
		}
		if((align & HCENTER) == HCENTER) {
			lr++;
			x -= (width >> 1);
		}
		if((align & RIGHT) == RIGHT) {
			lr++;
			x -= width;
		}
		if(lr != 1 || ud != 1) {
			throw new IllegalArgumentException();
		}
		canvas.drawBitmap(colors, offset, stride, x, y, width, height, hasAlpha, paint);
	}
	
	public void drawImage(int x, int y, Bitmap bsrc, int sx, int sy, int w, int h) {
		Rect rect_src = new Rect();
		rect_src.left = sx;
		rect_src.right = sx + w;
		rect_src.top = sy;
		rect_src.bottom = sy + h;
		Rect rect_dst = new Rect();
		rect_dst.left = x;
		rect_dst.right = x + w;
		rect_dst.top = y;
		rect_dst.bottom = y + h;
		canvas.drawBitmap(bsrc, rect_src, rect_dst, null);
		rect_src = null;
		rect_dst = null;
	}
	
	public void setFont(Font font) {
		currentFont = ((font == null)?Font.getDefaultFont():font);
		font = currentFont;
		paint.setTypeface(font.getTypeface());
		paint.setTextSize(font.getSize());
	}
}
