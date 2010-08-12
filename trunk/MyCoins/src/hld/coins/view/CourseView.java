package hld.coins.view;

import hld.coins.wrapper.Graphics;

public class CourseView extends MainView {
	private Graphics graphics;
	
	public CourseView() {
		super(false);
		
	}
	
	@Override
	public void onDraw(Graphics graphics) {
		super.onDraw(graphics);
		this.graphics = graphics;
		
		this.graphics = null;
	}
	
	private void rect(int x, int y, int width, int height) {
		graphics.drawRect(x, y, width, height);
	}
	
	private void arc(int x, int y, int width, int height, int startAngle, int sweepAngle) {
		graphics.drawArc(x, y, width, height, startAngle, sweepAngle);
	}
	
	private void move(int id, int x, int y) {
		
	}
}
