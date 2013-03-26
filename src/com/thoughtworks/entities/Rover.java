package com.thoughtworks.entities;

import java.io.Serializable;

import com.thoughtworks.enums.Command;

/**
 * Represents a NASA rover.
 * 
 * @author nonaka
 */
public class Rover implements Serializable {

	private static final long serialVersionUID = -1745462525621431812L;
	
	private Position position;

	/**
	 * @param position
	 *            the initial position.
	 */
	public Rover(Position position) {
		
		if (position == null)
			throw new IllegalArgumentException("Position cannot be null!");
		
		this.position = position;
	}

	/**
	 * Move the rover through a command.
	 * 
	 * @param command
	 */
	public void move(Command command) {

		if (command == null)
			throw new IllegalArgumentException("Command cannot be null!");

		command.move(position);
	}

	/**
	 * Returns the current position of the rover.
	 * 
	 * @return Position
	 */
	public Position getPosition() {
		return position;
	}
}
