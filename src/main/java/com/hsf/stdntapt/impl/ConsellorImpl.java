package com.hsf.stdntapt.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hsf.stdntapt.dao.ConsellorDao;
import com.hsf.stdntapt.entity.Consellor;
import com.hsf.stdntapt.service.ConsellorService;

@Service
public class ConsellorImpl implements ConsellorService {
	@Resource
	ConsellorDao consellorDao;

	@Override
	public void insertConsellorList(int consellId, String consellName, int consellSex, String consellTel) {
		consellorDao.insertConsellorList(consellId, consellName, consellSex, consellTel);
	}

	@Override
	public int deleteOne(int consellId) {
		return consellorDao.deleteOne(consellId);
	}

	@Override
	public Consellor findOneConsellor(int consellId) {
		return consellorDao.findOneConsellor(consellId);
	}

	@Override
	public int updateConsellor(Consellor consellor) {
		return consellorDao.updateConsellor(consellor);
	}
}
