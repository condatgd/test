package de.condat.gd;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

public class TwoLists<T1, T2> {

	private List<T1> l1;
	private List<T2> l2;

	public TwoLists(List<T1> a, List<T2> b) {
		this.l1 = a;
		this.l2 = b;
	}

	public void forEach(BiConsumer<T1, T2> action) {
		Objects.requireNonNull(action);
		for (int i = 0; i < l1.size(); i++) {
			action.accept(l1.get(i), l2.get(i));
		}
	}

}
