package net.logicsquad.advent.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Combination<T> implements Iterable<List<T>> {
	private Iterator<List<T>> iterator;
	private final List<T> source;
	private final int count;

	public Combination(Collection<T> source, int count) {
		this.source = new ArrayList<T>(source);
		this.count = count;
		return;
	}

	@Override
	public Iterator<List<T>> iterator() {
		if (iterator == null) {
			iterator = new CombinationIterator();
		}
		return iterator;
	}

	private class CombinationIterator implements Iterator<List<T>> {
		private final List<List<T>> list;
		private int next = 0;

		public CombinationIterator() {
			list = choose(source, count);
		}

		@Override
		public boolean hasNext() {
			return next < list.size();
		}

		@Override
		public List<T> next() {
			List<T> result = list.get(next);
			next++;
			return result;
		}

		private List<List<T>> choose(List<T> source, int count) {
			List<List<T>> result = new ArrayList<List<T>>();
			for (int i = 0; i < source.size(); i++) {
				if (count == 1) {
					List<T> innerList = new ArrayList<T>();
					innerList.add(source.get(i));
					result.add(innerList);
				} else {
					List<T> sub = source.subList(i + 1, source.size());
					for (List<T> smallerList : choose(sub, count - 1)) {
						smallerList.add(0, source.get(i));
						result.add(smallerList);
					}
				}
			}
			return result;
		}
	}
}
