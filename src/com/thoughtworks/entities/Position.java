package com.thoughtworks.entities;

import com.thoughtworks.enums.Orientation;

/**
 * A position of the rover. It has a coordenate and orientation.
 * 
 * @author nonaka
 */
public class Position {

	private Coordenate coordenate;
	private Orientation orientation;

	public Position(Coordenate coordenate, Orientation orientation) {
		
		if (coordenate == null)
			throw new IllegalArgumentException("Coordenate cannot be null!");
		
		if (orientation == null)
			throw new IllegalArgumentException("Orientation cannot be null!");
		
		this.coordenate = coordenate;
		this.orientation = orientation;
	}

	/**
	 * Change the position 90 degrees rigth.
	 */
	public void turnRight() {
		orientation = orientation.nextRight();
	}

	/**
	 * Change the position 90 degrees left.
	 */
	public void turnLeft() {
		orientation = orientation.nextLeft();
	}

	/**
	 * Move the position forward one grid point, and maintain the same heading.
	 */
	public void goForward() {
		orientation.goFoward(coordenate);
	}

	/**
	 * Return an output of the position.
	 */
	public String toString() {
		return coordenate.getX() + " " + coordenate.getY() + " " + orientation;
	}

	public Coordenate getCoordenate() {
		return coordenate;
	}

	public Orientation getOrientation() {
		return orientation;
	}
}
