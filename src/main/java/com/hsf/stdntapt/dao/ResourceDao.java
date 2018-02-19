package com.hsf.stdntapt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsf.stdntapt.entity.Resource;

/**
 * <p>
 * Resource: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface ResourceDao {

	public long createResource(@Param("resource") Resource resource);

	public long updateResource(@Param("resource") Resource resource);

	public void deleteResource(@Param("resourceId") Long resourceId);

	Resource findOne(@Param("resourceId") Long resourceId);

	List<Resource> findAll();

}
