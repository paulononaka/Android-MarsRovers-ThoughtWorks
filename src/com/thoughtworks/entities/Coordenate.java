package com.thoughtworks.entities;

import com.thoughtworks.constants.Constant;

/**
 * A coordinate represented by a combination of x and y.
 * 
 * @author nonaka
 */
public class Coordenate {

	private int x;
	private int y;

	public Coordenate(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		
		if (x < 0)
			throw new IllegalArgumentException("X cannot less than zero!");
		
		if (x > Constant.RIGHT_MAX)
			throw new IllegalArgumentException("X cannot greater than " + Constant.RIGHT_MAX + "!");
		
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		
		if (y < 0)
			throw new IllegalArgumentException("Y cannot less than zero!");
		
		if (y > Constant.UPPER_MAX)
			throw new IllegalArgumentException("Y cannot greater than " + Constant.UPPER_MAX + "!");
		
		this.y = y;
	}
}
