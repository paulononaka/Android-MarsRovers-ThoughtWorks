package com.thoughtworks.enums;

import java.io.Serializable;

import com.thoughtworks.entities.Position;

public enum Command implements Serializable {
	L {
		@Override
		public void move(Position position) {
			
			if (position == null)
				throw new IllegalArgumentException("Position cannot be null!");
			
			position.turnLeft();
		}
	},
	R {
		@Override
		public void move(Position position) {
			
			if (position == null)
				throw new IllegalArgumentException("Position cannot be null!");
			
			position.turnRight();
		}
	},
	M {
		@Override
		public void move(Position position) {
			
			if (position == null)
				throw new IllegalArgumentException("Position cannot be null!");
			
			position.goForward();
		}
	};

	/**
	 * The possible letters are 'L', 'R' and 'M'. 'L' and 'R' makes the rover
	 * spin 90 degrees left or right respectively, without moving from its
	 * current spot. 'M' means move forward one grid point, and maintain the
	 * heading.
	 * 
	 * @param position
	 */
	public abstract void move(Position position);
}
