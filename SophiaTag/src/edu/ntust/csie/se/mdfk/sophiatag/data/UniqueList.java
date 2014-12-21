/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.data;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

/**
 * @author maeglin89273
 *
 */
public class UniqueList<E> extends AbstractList<E> implements  Set<E>, RandomAccess, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8282639214426785635L;
	private Map<Integer, E> forward;
	private Map<E, Integer> backward;
	/**
	 * 
	 */
	
	public UniqueList(int capacity) {
		this.forward = new HashMap<Integer, E>(capacity);
		this.backward = new HashMap<E, Integer>(capacity);
	}
	
	public UniqueList(Collection<? extends E> c) {
		int capacity = optimialCapacity(c.size());
		this.forward = new HashMap<Integer, E>(capacity);
		this.backward = new HashMap<E, Integer>(capacity);
		this.addAll(c);
		
	}
	
	private static int optimialCapacity(int requirededSize) {
		//the formula is refered from HashSet
		return Math.max((int) (requirededSize/.75f) + 1, 16);
	}
	
	public UniqueList() {
		this.forward = new HashMap<Integer, E>();
		this.backward = new HashMap<E, Integer>();
	}

	@Override
	public int size() {
		return this.forward.size();
	}

	@Override
	public boolean isEmpty() {
		return this.forward.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return this.backward.containsKey(o);
	}

	@Override
	public Object[] toArray() {
		Object[] copy = new Object[this.forward.size()];
		for (int i = 0; i < this.size(); i++) {
			copy[i] = this.forward.get(i);
		}
		
		return copy;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] array) {
		if (array.length < this.size()) {
			return (T[])this.toArray();
		}
		
		Object[] result = array;
		for (int i = 0; i < this.size(); i++) {
			result[i] = this.forward.get(i);
		}
		
		if (array.length > this.size()) {
			array[forward.size()] = null;
		}
		
		return array;
	}
		
	private void putIntoBothMaps(Integer index, E element) {
		this.forward.put(index, element);
		this.backward.put(element, index);
	}
	
	@Override
	public boolean remove(Object o) {
		if (!this.contains(o)) {
			return false;
		}
		
		int index = this.backward.get(o);
		this.removeFromBothMapsWithAdjuctment(index, o);
		return true;
	}
	
	private void removeFromBothMapsWithAdjuctment(Integer index, Object o) {
		
		E nextElement;
		int newSize = this.size() - 1;
		for (int i = index; i < newSize; i++) {
			nextElement = this.forward.get(i + 1);
			putIntoBothMaps(i, nextElement);
		}
		
		this.removeFromBothMaps(newSize, o);
	}
	
	private void removeFromBothMaps(Integer index, Object o) {
		this.forward.remove(index);
		this.backward.remove(o);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		c = filterAddAll(c);
		this.backwardMove(index, c.size());
		fillAll(index, c);
		return c.size() > 0;
	}
	
	private Collection<? extends E> filterAddAll(Collection<? extends E> c) {
		LinkedList<E> result = new LinkedList<E>();
		for (E element: c) {
			if (!this.contains(element)) {
				result.add(element);
			}
		}
		return result;
	}
	
	private void fillAll(int index, Collection<? extends E> c) {		
		for (E element: c) {
			this.putIntoBothMaps(index++, element);
		}
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		return batchRemove(c, false);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return batchRemove(c, true);
	}
	
	private boolean batchRemove(Collection<?> c, boolean complement) {
		boolean modified = false;
		E query;
		int readIndex = 0 , writeIndex = 0;
		for (;readIndex < this.size(); readIndex++) {
			query = this.forward.get(readIndex);
			if (c.contains(query) == complement) { // should query be retained?
				this.putIntoBothMaps(writeIndex++, query);
				
			}
		}
		
		if (writeIndex != this.size()) {
			dumpRest(writeIndex);
			modified = true;
			
		}
		return modified;
	}
	
	private void dumpRest(int startIndex) {
		
		for (int i = startIndex; i < this.size(); i++) {
			this.backward.remove(this.forward.remove(startIndex));
		}
	}
	
	@Override
	public void clear() {
		this.forward.clear();
		this.backward.clear();
	}

	@Override
	public E get(int index) {
		this.rangeCheck(index);
		return this.forward.get(index);
	}

	@Override
	public E set(int index, E element) {
		
		E oldValue = this.get(index);
		this.putIntoBothMaps(index, element);
		
		return oldValue;
	}

	@Override
	public boolean add(E element) {
		if (this.contains(element)) {
			return false;
		}
		
		putIntoBothMaps(this.size(), element);
		return true;
	}
	
	@Override
	public void add(int index, E element) {
		this.rangeCheckForAdd(index);
		
		if (this.contains(element)) {
			return;
		}
		
		backwardMove(index, 1);
		
		putIntoBothMaps(index, element);
	}
	
	private void backwardMove(int endIndex, int stride) {
		E previousElement;
		for (int i = this.size() - 1; i >= endIndex; i--) {
			previousElement = this.forward.get(i);
			putIntoBothMaps(i + stride, previousElement);
		}
	}
	
	@Override
	public E remove(int index) {
		E element = this.get(index);
		this.removeFromBothMapsWithAdjuctment(index, element);
		return element;
	}

	@Override
	public int indexOf(Object o) {
		if (!this.contains(o)) {
			return -1;
		}
		
		return this.backward.get(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.indexOf(o);
	}

	private void rangeCheck(int index) {
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
	
	private void rangeCheckForAdd(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }
	
	private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.size();
    }
}


