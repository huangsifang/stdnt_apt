package com.hsf.stdntapt.service;

import java.util.List;
import java.util.Set;

import com.hsf.stdntapt.entity.Resource;

public interface ResourceService {

	public long createResource(Resource resource);

	public long updateResource(Resource resource);

	public void deleteResource(Long resourceId);

	Resource findOne(Long resourceId);

	List<Resource> findAll();

	/**
	 * 得到资源对应的权限字符串
	 *
	 * @param resourceIds
	 * @return
	 */
	Set<String> findPermissions(Set<Long> resourceIds);

	/**
	 * 根据用户权限得到菜单
	 *
	 * @param permissions
	 * @return
	 */
	List<Resource> findMenus(Set<String> permissions);
}
