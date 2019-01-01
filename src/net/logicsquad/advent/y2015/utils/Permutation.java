package net.logicsquad.advent.y2015.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Permutation<T> implements Iterable<List<T>> {
	private Iterator<List<T>> iterator;
	private final Collection<T> source;

	public Permutation(Collection<T> source) {
		this.source = source;
		return;
	}

	@Override
	public Iterator<List<T>> iterator() {
		if (iterator == null) {
			iterator = new ListIterator();
		}
		return iterator;
	}

	private class ListIterator implements Iterator<List<T>> {
		private final List<List<T>> list;
		private int next = 0;

		public ListIterator() {
			list = permute(source);
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

		private List<List<T>> permute(Collection<T> source) {
			List<List<T>> result = new ArrayList<List<T>>();
			if (source.size() == 1) {
				List<T> sourceList = new ArrayList<T>();
				sourceList.addAll(source);
				result.add(sourceList);
				return result;
			} else if (source.size() == 2) {
				List<T> sourceList = new ArrayList<T>();
				sourceList.addAll(source);
				List<T> list1 = new ArrayList<T>();
				list1.add(sourceList.get(0));
				list1.add(sourceList.get(1));
				List<T> list2 = new ArrayList<T>();
				list2.add(sourceList.get(1));
				list2.add(sourceList.get(0));
				result.add(list1);
				result.add(list2);
				return result;
			} else {
				for (T s : source) {
					List<T> perm = new ArrayList<T>(source);
					perm.remove(s);

					for (List<T> l : permute(perm)) {
						List<T> list = new ArrayList<T>();
						list.add(s);
						list.addAll(l);
						result.add(list);
					}
				}
			}
			return result;
		}
	}
}
