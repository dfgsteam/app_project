package bauernhof.preset;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * An immutable view on a list.
 */
public final class ImmutableList<E> extends AbstractList<E> {
	private final List<E> src;
	/**
	 * Construct an immutable view on a list.
	 *
	 * @param src The list that should be viewed by this instance.
	 */
	public ImmutableList(List<E> src) {
		this.src = Collections.unmodifiableList(src);
	}
	/**
	 * @hidden Construct an immutable view on a collection.
	 *
	 * <p>
	 * This constructor only exists as it is recommended by the documentation of {@link Collection}.
	 * </p>
	 *
	 * @param src The src collection. It will be used to create a new {@link ArrayList} which is then used on {@link ImmutableList#ImmutableList(List)}.
	 */
	public ImmutableList(Collection<E> src) {
		this(new ArrayList<>(src));
	}
	/**
	 * @hidden Construct an immutable view on an empty list.
	 *
	 * <p>
	 * This constructor only exists as it is recommended by the documentation of {@link Collection}.
	 * </p>
	 *
	 * <p>
	 * Yes, this constructor is rather pointless in this case.
	 * </p>
	 */
	public ImmutableList() {
		this(new ArrayList<>());
	}


	/**
	 * {@inheritDoc}
	 */
	public int size() {
		return src.size();
	}

	/**
	 * {@inheritDoc}
	 */
	public E get(int index) {
		return src.get(index);
	}

}
