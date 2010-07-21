package com.yingzheng.android.wrapper;



import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.FontMetrics;

public class Font {
	public static final int STYLE_PLAIN = 0;
	public static final int STYLE_BOLD = 1;
	public static final int STYLE_ITALIC = 2;
	public static final int STYLE_UNDERLINED = 4;
	public static final int SIZE_SMALL = 22;
	public static final int SIZE_MEDIUM = 31;
	public static final int SIZE_LARGE = 40;
	public static final int FACE_SYSTEM = 0;
	Paint mPaint = new Paint();
	Rect rect = new Rect();
	private int face;
	private int size;
	private int baseline;
	private int height;

	public int getHeight() {
		return height;
	}

	private Typeface iTypeface = Typeface.DEFAULT;

	Typeface getTypeface() {
		return iTypeface;
	}

	public int getSize() {
		return size;
	}

	private static final Font DEFAULT_FONT = new Font(null, SIZE_SMALL);

	public Font(Typeface aTypeface, int aSize) {
		if (aTypeface != null) {
			iTypeface = aTypeface;
			mPaint.setTypeface(aTypeface);
		} else {
			mPaint.setTypeface(iTypeface);
		}
		size = aSize;
		mPaint.setTextSize(aSize);
		FontMetrics fontMetrics = mPaint.getFontMetrics();
		height = (int) (fontMetrics.bottom - fontMetrics.top);
	}

	public static Font getFont(int fontSepcifier) {
		switch (fontSepcifier) {
		case SIZE_SMALL:
		case SIZE_MEDIUM:
		case SIZE_LARGE:
			return new Font(null, fontSepcifier);
		}

		return DEFAULT_FONT;
	}

	public static Font getDefaultFont() {
		return DEFAULT_FONT;
	}

	public static Font getFont(int face, int style, int size) {
		switch (style) {
		case STYLE_PLAIN:
			return new Font(Typeface.defaultFromStyle(Typeface.NORMAL), size);
		case STYLE_BOLD:
			return new Font(Typeface.defaultFromStyle(Typeface.BOLD), size);
		case STYLE_ITALIC:
			return new Font(Typeface.defaultFromStyle(Typeface.ITALIC), size);
		}

		return DEFAULT_FONT;
	}

	public int getFace() {
		return face;
	}

	public int getBaselinePosition() {
		return baseline;
	}

	public int charWidth(char arg) {
		mPaint.getTextBounds(String.valueOf(arg), 0, 1, rect);
		return rect.width();
	}

	public int stringWidth(String str) {
		mPaint.getTextBounds(str, 0, str.length(), rect);
		return rect.width();
	}
	
	public int substringWidth(String str, int offset, int len){
		mPaint.getTextBounds(str, offset, len, rect);
		return rect.width();
	}
	
	public int charsWidth(char[] ch, int offset, int length) {
		mPaint.getTextBounds(ch, offset, length, rect);
		return rect.width();
	}
}