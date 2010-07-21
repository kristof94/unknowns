/**
 * <p>Title: Sprite.java</p>
 *
 * <p>Description: Sprite MIDP2.0</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * @author fengsheng.yang
 *
 * @version 1.0
 *
 * @Date 2009-5-14
 */
package com.yingzheng.android.interfaces.sprite;

import com.yingzheng.android.interfaces.AbstractView;
import com.yingzheng.android.wrapper.Graphics;
import com.yingzheng.android.wrapper.Image;

/**
 * 
 * @author Administrator
 * 
 */
public class Sprite extends AbstractView {

	private static final int FRAME = 1;
	private static final int BITMAPS = 2;
	public int current_mode;

	public int x;
	public int y;

	/**
	 * Source image
	 */
	private Image sourceImage;

	/**
	 * 图的总帧数
	 */
	int numberFrames; // = 0;

	/**
	 * list of X coordinates of individual frames
	 */
	int[] frameCoordsX;
	/**
	 * list of Y coordinates of individual frames
	 */
	int[] frameCoordsY;

	/**
	 * Width of each frame in the source image 在大图中，截取每一幅小图的宽度
	 */
	int srcFrameWidth;

	/**
	 * Height of each frame in the source image 在大图中，截取每一幅小图的高度
	 */
	int srcFrameHeight;

	/**
	 * The sequence in which to display the Sprite frames 存储所有帧的数组
	 */
	int[] frameSequence;

	/**
	 * The sequence index 数组下标
	 */
	private int sequenceIndex; // = 0

	/**
	 * Set to true if custom sequence is used.
	 */
	private boolean customSequenceDefined; // = false;

	public Image[] images;

	public Image[] getImages() {
		return images;
	}

	public void setImages(Image[] images) {
		this.images = images;
	}

	public Sprite(Image image, int frameWidth, int frameHeight,
			boolean isJoinViewManager) {
		super(isJoinViewManager);
		if ((frameWidth < 1 || frameHeight < 1)
				|| ((image.width % frameWidth) != 0)
				|| ((image.height % frameHeight) != 0)) {
			throw new IllegalArgumentException();
		}
		current_mode = FRAME;
		initializeFrames(image, frameWidth, frameHeight, false);

	}

	public Sprite(Image[] images, boolean isJoinViewManager) {
		super(isJoinViewManager);
		current_mode = BITMAPS;
		this.images = images;
		initializeFrames(images);

	}

	public void setFrame(int sequenceIndex) {
		if (sequenceIndex < 0 || sequenceIndex >= frameSequence.length) {
			throw new IndexOutOfBoundsException();
		}
		this.sequenceIndex = sequenceIndex;
	}

	public final int getFrame() {
		return sequenceIndex;
	}

	/**
	 * Gets the number of raw frames for this Sprite. The value returned
	 * reflects the number of frames; it does not reflect the length of the
	 * Sprite's frame sequence. However, these two values will be the same if
	 * the default frame sequence is used.
	 * 
	 * @return the number of raw frames for this Sprite
	 * @see #getFrameSequenceLength
	 */
	public int getRawFrameCount() {
		return numberFrames;
	}

	public int getFrameSequenceLength() {
		return frameSequence.length;
	}

	public void nextFrame(int current_mode) {
		if (current_mode == FRAME) {
			sequenceIndex = (sequenceIndex + 1) % frameSequence.length;
		} else if (current_mode == BITMAPS) {
			if (sequenceIndex < images.length - 1) {
				sequenceIndex++;
			} else if (sequenceIndex == images.length - 1) {
				sequenceIndex = 0;
			}
		}
	}

	@Override
	public void onDraw(Graphics graphics) {
		super.onDraw(graphics);
		if (current_mode == FRAME)
			graphics.drawImage(this.x, this.y, sourceImage.imgae,
					frameCoordsX[frameSequence[sequenceIndex]],
					frameCoordsY[frameSequence[sequenceIndex]], srcFrameWidth,
					srcFrameHeight);
		else if (current_mode == BITMAPS) {
			graphics.drawImage(images[sequenceIndex].imgae, x, y);
		}
	}

	/**
	 * Set the frame sequence for this Sprite.
	 * <p>
	 * 
	 * All Sprites have a default sequence that displays the Sprites frames in
	 * order. This method allows for the creation of an arbitrary sequence using
	 * the available frames. The current index in the frame sequence is reset to
	 * zero as a result of calling this method.
	 * <p>
	 * The contents of the sequence array are copied when this method is called;
	 * thus, any changes made to the array after this method returns have no
	 * effect on the Sprite's frame sequence.
	 * <P>
	 * Passing in <code>null</code> causes the Sprite to revert to the default
	 * frame sequence.
	 * <p>
	 * 
	 * @param sequence
	 *            an array of integers, where each integer represents a frame
	 *            index
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if seq is non-null and any member of the array has a value
	 *             less than <code>0</code> or greater than or equal to the
	 *             number of frames as reported by {@link #getRawFrameCount()}
	 * @throws IllegalArgumentException
	 *             if the array has less than <code>1</code> element
	 * @see #nextFrame
	 * @see #prevFrame
	 * @see #setFrame
	 * @see #getFrame
	 * 
	 */
	public void setFrameSequence(int sequence[]) {

		if (sequence == null) {
			// revert to the default sequence
			sequenceIndex = 0;
			customSequenceDefined = false;
			frameSequence = new int[numberFrames];
			// copy frames indices into frameSequence
			for (int i = 0; i < numberFrames; i++) {
				frameSequence[i] = i;
			}
			return;
		}

		if (sequence.length < 1) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < sequence.length; i++) {
			if (sequence[i] < 0 || sequence[i] >= numberFrames) {
				throw new ArrayIndexOutOfBoundsException();
			}
		}
		customSequenceDefined = true;
		frameSequence = new int[sequence.length];
		System.arraycopy(sequence, 0, frameSequence, 0, sequence.length);
		sequenceIndex = 0;
	}

	public void setImage(Image img, int frameWidth, int frameHeight) {

		// if image is null image.getWidth() will throw NullPointerException
		if ((frameWidth < 1 || frameHeight < 1)
				|| ((img.width % frameWidth) != 0)
				|| ((img.height % frameHeight) != 0)) {
			throw new IllegalArgumentException();
		}

		int noOfFrames = (img.width / frameWidth) * (img.height / frameHeight);

		boolean maintainCurFrame = true;
		if (noOfFrames < numberFrames) {
			// use default frame , sequence index = 0
			maintainCurFrame = false;
			customSequenceDefined = false;
		}

		if (!((srcFrameWidth == frameWidth) && (srcFrameHeight == frameHeight))) {

		} else {
			// just reinitialize the animation frames.
			initializeFrames(img, frameWidth, frameHeight, maintainCurFrame);
		}

	}

	private void initializeFrames(Image[] images) {
		numberFrames = images.length;
	}

	private void initializeFrames(Image image, int fWidth, int fHeight,
			boolean maintainCurFrame) {

		int imageW = image.width;
		int imageH = image.height;

		// 宽度拥有帧数
		int numHorizontalFrames = imageW / fWidth;
		// 高度拥有帧数
		int numVerticalFrames = imageH / fHeight;

		sourceImage = image;

		srcFrameWidth = fWidth;
		srcFrameHeight = fHeight;

		numberFrames = numHorizontalFrames * numVerticalFrames;

		frameCoordsX = new int[numberFrames];
		frameCoordsY = new int[numberFrames];

		if (!maintainCurFrame) {
			sequenceIndex = 0;
		}

		if (!customSequenceDefined) {
			frameSequence = new int[numberFrames];
		}

		int currentFrame = 0;

		for (int yy = 0; yy < imageH; yy += fHeight) {
			for (int xx = 0; xx < imageW; xx += fWidth) {

				frameCoordsX[currentFrame] = xx;
				frameCoordsY[currentFrame] = yy;

				if (!customSequenceDefined) {
					frameSequence[currentFrame] = currentFrame;
				}
				currentFrame++;
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
