package bauernhof.preset;

/**
 * A data type that can only contain either the left or the right value but not both nor neither.
 *
 * <p>
 * Either is a concept from the world of functional programming.
 * </p>
 */
public class Either<L,R> {
	protected final L left;
	protected final R right;
	protected final boolean isLeft;

	/**
	 * Construct a new either.
	 * @param left The left value or null if it is right.
	 * @param right The right value or null if it is left.
	 *
	 * @throws RuntimeException Both left and right were null or both left and right were not null.
	 *
	 */
	public Either(L left, R right) {
		this.left = left;
		this.right = right;
		if (left == null && right == null)
			throw new RuntimeException("Either cannot be created with both left and right being null.");
		else if (left != null && right != null)
			throw new RuntimeException("Either cannot be created with both left and right not being null.");
		else if (left == null)
			this.isLeft = false;
		else
			this.isLeft = true;
	}

	/**
	 * Get the left value.
	 *
	 * @return The left value or null if the right value is set.
	 */
	public L getLeft() {

		return left;
	}


	/**
	 * Get the right value.
	 *
	 * @return The right value or null if the left value is set.
	 */
	public R getRight() {
		return right;
	}
	/**
	 * Get the value.
	 *
	 * @return The right value or the left value, whichever is set.
	 */
	public Object get() {
		if (isLeft()) {
			return getLeft();
		} else {
			return getRight();
		}
	}

	/**
	 * Is this a representation of the left value.
	 * @return Whether this is a representation of the left value.
	 */
	public boolean isLeft() {
		return isLeft;
	}

	/**
	 * Is this a representation of the right value.
	 * @return Whether this is a representation of the right value.
	 */
	public boolean isRight() {
		return !isLeft();
	}

	/**
	 * Compares two Eithers by their sides and values.
	 *
	 * <p>
	 * Both eithers must have the same type for L and the same type for R!<br>
	 * First compares if they are both left or both right. Both must have the same "side".<br>
	 * Than compares their values.
	 * </p>
	 *
	 * @return Whether both Eithers have the same side and if their values are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		// Don't look at this code. It's hacky and ugly.
		try {
			if (obj instanceof Either) {
				Either<? extends Object,? extends Object> e = (Either<? extends Object, ? extends Object>)obj;
				if (isLeft() && e.isLeft()) {
					return getLeft().equals(e.getLeft());
				} else if (isRight() && e.isRight()) {
					return getRight().equals(e.getRight());
				} else {
					return false;
				}
			}
		} catch (ClassCastException e) {
			return false;
		}
		return false;
	}

	/**
	 * Get the hash code of the value.
	 *
	 * @return <code>get().hashCode()</code>
	 */
	@Override
	public int hashCode() {
		return get().hashCode();
	}

	/**
	 * The string representation of the value surrounded by brackets and prefixed with either "Left" or "Right".
	 * @return <code>(isLeft() ? "Left" : "Right") + "(" + get().toString() + ")"</code>
	 */
	@Override
	public String toString() {
		return (isLeft() ? "Left" : "Right") + "(" + get().toString() + ")";
	}
}
