package cn.edu.tongji.anliantest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.edu.tongji.anliantest.dao.AbstractHibernateDao;
import cn.edu.tongji.anliantest.dao.FileItemDao;
import cn.edu.tongji.anliantest.model.FileItem;

@Repository
public class FileItemDaoImpl extends AbstractHibernateDao<FileItem, Long> implements FileItemDao {

	protected FileItemDaoImpl() {
		super(FileItem.class);
	}

	@Override
	public FileItem getFileById(Long fileId) {
		return(findById(fileId));
	}

	@Override
	public List<FileItem> getFileByProjectId(Long projectId) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		
		criterions.add(Restrictions.eq("project.id", projectId));
		
		List<FileItem> list = findByCriteria(criterions);
		return list;
	}

	@Override
	public Long addFile(FileItem file) {
		return add(file);
	}

	@Override
	public void deleteFile(Long fileId) {
		delete(findById(fileId));	
	}

	@Override
	public void updateFile(FileItem file) {
		
		saveOrUpdate(file);
		
	}

}
