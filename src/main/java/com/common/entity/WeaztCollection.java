package com.common.entity;

import java.util.List;

public abstract class WeaztCollection<E> {


	private List<E> collection;
	private boolean hasMore;
	private long size;
	
	public WeaztCollection(List<E> collection, long size,boolean hasMore) {
		super();
		this.collection = collection;
		this.size = size;
		this.hasMore = hasMore;
	}
	
	
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	public List<E> getCollection() {
		return collection;
	}
	public void setCollection(List<E> collection) {
		this.collection = collection;
	}
	
	public boolean isHasMore() {
		return hasMore;
	}
	
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
}
