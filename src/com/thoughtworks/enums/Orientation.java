package com.thoughtworks.enums;

import com.thoughtworks.entities.Coordenate;

public enum Orientation {
	N {
		@Override
		public Orientation nextRight() {
			return E;
		}

		@Override
		public Orientation nextLeft() {
			return W;
		}

		@Override
		public void goFoward(Coordenate coordenate) {
			
			if (coordenate == null)
				throw new IllegalArgumentException("Coordenate cannot be null!");
			
			coordenate.setY(coordenate.getY() + 1);
		}
	},
	E {
		@Override
		public Orientation nextRight() {
			return S;
		}

		@Override
		public Orientation nextLeft() {
			return N;
		}

		@Override
		public void goFoward(Coordenate coordenate) {
			
			if (coordenate == null)
				throw new IllegalArgumentException("Coordenate cannot be null!");
			
			coordenate.setX(coordenate.getX() + 1);
		}
	},
	S {
		@Override
		public Orientation nextRight() {
			return W;
		}

		@Override
		public Orientation nextLeft() {
			return E;
		}

		@Override
		public void goFoward(Coordenate coordenate) {
			
			if (coordenate == null)
				throw new IllegalArgumentException("Coordenate cannot be null!");
			
			coordenate.setY(coordenate.getY() - 1);
		}
	},
	W {
		@Override
		public Orientation nextRight() {
			return N;
		}

		@Override
		public Orientation nextLeft() {
			return S;
		}

		@Override
		public void goFoward(Coordenate coordenate) {
			
			if (coordenate == null)
				throw new IllegalArgumentException("Coordenate cannot be null!");
			
			coordenate.setX(coordenate.getX() - 1);
		}
	};

	/**
	 * Returns the next direction in the right accordance with the current
	 * orientation. Each orientation know what your correspondent.
	 * 
	 * @return Orientation
	 */
	public abstract Orientation nextRight();

	/**
	 * Returns the next direction in the left accordance with the current
	 * orientation. Each orientation know what your correspondent.
	 * 
	 * @return Orientation
	 */
	public abstract Orientation nextLeft();

	/**
	 * Means move forward one grid point, and maintain the heading. Assume that
	 * the square directly North from (x, y) is (x, y+1).
	 * 
	 * @param coordenate
	 */
	public abstract void goFoward(Coordenate coordenate);
}
