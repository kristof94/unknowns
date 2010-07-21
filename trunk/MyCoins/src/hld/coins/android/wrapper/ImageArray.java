package com.yingzheng.android.wrapper;

public class ImageArray {

	public short width;
	public short height;
	public int[] piexls;

	public ImageArray(short width, short height) {
		this.width = width;
		this.height = height;
		piexls = new int[width * height];
	}

	public ImageArray(int width, int height) {
		this.width = (short) width;
		this.height = (short) height;
	}
}
