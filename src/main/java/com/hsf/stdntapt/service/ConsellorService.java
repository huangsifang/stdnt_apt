package com.hsf.stdntapt.service;

import com.hsf.stdntapt.entity.Consellor;

public interface ConsellorService {
	public void insertConsellorList(int consellId, String consellName, int consellSex, String consellTel);

	public int deleteOne(int consellId);

	public Consellor findOneConsellor(int consellId);

	public int updateConsellor(Consellor consellor);
}
