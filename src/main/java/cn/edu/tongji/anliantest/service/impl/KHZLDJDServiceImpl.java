package cn.edu.tongji.anliantest.service.impl;

import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.model.KHZLDJDTable;
import cn.edu.tongji.anliantest.service.KHZLDJDService;
import cn.edu.tongji.anliantest.util.DataWrapper;

@Service("khzldjService")
public class KHZLDJDServiceImpl implements KHZLDJDService{
	
	@Override
	public DataWrapper<KHZLDJDTable> getKHZLDJDById(Long khzldjdTableId) {
		return null;
	}

	@Override
	public DataWrapper<KHZLDJDTable> addKHZLDJD(KHZLDJDTable khzldjdTable) {
		return null;
	}

	@Override
	public DataWrapper<KHZLDJDTable> updateKHZLDJD(KHZLDJDTable khzldjdTable) {
		return null;
	}

	@Override
	public DataWrapper<Void> deleteKHZLDJD(Long khzldjdTableId) {
		return null;
	}
	
}
