package com.jwatprojectdemo.service;

import java.util.List;

public interface BaseService<T, ID> {
	List<T> findAll();
	T findById(ID id);
	List<T> findByKeyword(String kw);
	T save(T t);
	void deleteById(ID id);
	Boolean existById(ID id);
}
