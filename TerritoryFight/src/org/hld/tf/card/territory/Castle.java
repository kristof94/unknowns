package org.hld.tf.card.territory;

import org.hld.tf.card.base.Territory;

/**
 * 城堡
 */
public class Castle extends Territory {

	@Override
	public String getName() {
		return "城堡";
	}

	@Override
	public int getPoint() {
		return 5;
	}

	@Override
	public String getInfo() {
		return "城堡不会被消灭";
	}
	
	@Override
	public boolean canWipeOut() {
		return false;
	}
}
