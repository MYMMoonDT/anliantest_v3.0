package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.FileGroupDao;
import cn.edu.tongji.anliantest.model.FileGroup;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.PageResult;

@Repository
public class FileGroupDaoImpl extends AbstractHibernateDao<FileGroup, Long> implements FileGroupDao{

	protected FileGroupDaoImpl() {
		super(FileGroup.class);
	}

	@Override
	public FileGroup getFileGroupById(Long fileGroupId) {
		return findById(fileGroupId);
	}

	@Override
	public Long addFileGroup(FileGroup fileGroup) {
		return add(fileGroup);
	}

	@Override
	public void updateFileGroup(FileGroup fileGroup) {
		saveOrUpdate(fileGroup);
	}

	@Override
	public void deleteFileGroup(Long fileGroupId) {
		delete(findById(fileGroupId));
	}

	@Override
	public DataWrapper<List<FileGroup>> getFileGroupList(Long projectId, int currPageNum,
			int numPerPage) {
		DataWrapper<List<FileGroup>> ret = new DataWrapper<List<FileGroup>>();
		
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.eq("project.id", projectId));
		
		PageResult<FileGroup> pageResult = findByCriteriaByPage(criterions, currPageNum, numPerPage);
		
		ret.setData(pageResult.getData());
		ret.setNumPerPage(pageResult.getNumPerPage());
		ret.setCurrPageNum(pageResult.getCurrPageNum());
		ret.setTotalItemNum(pageResult.getTotalItemNum());
		ret.setTotalPageNum(pageResult.getTotalPageNum());
		
		return ret;
	}

}
