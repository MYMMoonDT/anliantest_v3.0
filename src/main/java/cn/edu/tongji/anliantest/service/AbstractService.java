package cn.edu.tongji.anliantest.service;

import java.util.List;

import cn.edu.tongji.anliantest.util.DataWrapper;

public interface AbstractService<E> {
	
	public DataWrapper<E> getEById(Long id);
	
	public DataWrapper<E> addE(E e);
	
	public DataWrapper<E> updateE(E e);
	
	public DataWrapper<Void> deleteE(Long id);
	
	public DataWrapper<List<E>> getEList(int currPageNum, int numPerPage);

}
