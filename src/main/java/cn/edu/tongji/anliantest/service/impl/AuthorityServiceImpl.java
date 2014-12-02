package cn.edu.tongji.anliantest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tongji.anliantest.dao.AuthorityGroupDao;
import cn.edu.tongji.anliantest.dao.AuthorityItemDao;
import cn.edu.tongji.anliantest.dao.EmployeeAuthorityGroupDao;
import cn.edu.tongji.anliantest.model.AuthorityGroup;
import cn.edu.tongji.anliantest.model.AuthorityItem;
import cn.edu.tongji.anliantest.model.EmployeeAuthorityGroup;
import cn.edu.tongji.anliantest.model.EmployeeAuthorityGroupItem;
import cn.edu.tongji.anliantest.service.AuthorityService;
import cn.edu.tongji.anliantest.util.DataWrapper;
import cn.edu.tongji.anliantest.util.ErrorCodeEnum;

@Service("AuthorityService")
public class AuthorityServiceImpl implements AuthorityService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);
	
	@Autowired
	private AuthorityGroupDao groupDao;
	@Autowired
	private AuthorityItemDao itemDao;
	@Autowired
	private EmployeeAuthorityGroupDao employeeGroupDao;
	
	@Override
	public DataWrapper<List<AuthorityGroup>> getAllAuthorityGroups() {
		return groupDao.getAllAuthorityGroups();
	}

	@Override
	public DataWrapper<List<AuthorityItem>> getAllAuthorityItems() {
		return itemDao.getAllAuthorityItems();
	}

	@Override
	public DataWrapper<AuthorityGroup> updateAuthorityGroup(AuthorityGroup group) {
		AuthorityGroup g = groupDao.updateAuthorityGroup(group);
		
		DataWrapper<AuthorityGroup> ret = new DataWrapper<AuthorityGroup>();
		if (g == null) {
			ret.setErrorCode(ErrorCodeEnum.Unknown_Error);
			logger.info("更新权限组信息失败");
		} else {
			ret.setData(g);
			logger.info("更新权限组信息:"+g.getName()+"("+g.getId()+")");
			
			List<EmployeeAuthorityGroup> empGrps = employeeGroupDao.getEmployeeAuthorityGroupsByAuthorityGroupId(g.getId()).getData();
			
			for (EmployeeAuthorityGroup empGrp : empGrps) {
				List<AuthorityItem> items = new ArrayList<AuthorityItem>(g.getAuthorityItems());
				removeEmpAuthItems(empGrp.getEmployeeAuthorityGroupItems(), items);
				addEmpAuthItems(empGrp.getEmployeeAuthorityGroupItems(), items);
			}
		}
		
		return ret;
	}
	
	private void addEmpAuthItems(
			List<EmployeeAuthorityGroupItem> empItems,
			List<AuthorityItem> items) {
		List<EmployeeAuthorityGroupItem> newEmpList = new ArrayList<EmployeeAuthorityGroupItem>();
		for (AuthorityItem item : items) {
			boolean found = false;
			for (EmployeeAuthorityGroupItem empItem : empItems) {
				if (item.getId().equals(empItem.getAuthorityItem().getId())) {
					found = true;
					break;
				}
			}
			if (!found) {
				EmployeeAuthorityGroupItem newEmpItem = new EmployeeAuthorityGroupItem();
				newEmpItem.setAuthorityItem(item);
				newEmpList.add(newEmpItem);
			}
		}
		empItems.addAll(newEmpList);
	}

	private void removeEmpAuthItems(List<EmployeeAuthorityGroupItem> empItems, List<AuthorityItem> items) {
		ListIterator<EmployeeAuthorityGroupItem> it = empItems.listIterator();
		while(it.hasNext()) {
			boolean found = false;
			Long empItemAuthId = it.next().getAuthorityItem().getId(); 
			for (AuthorityItem item : items) {
				if (empItemAuthId.equals(item.getId())) {
					found = true;
					break;
				}
			}
			if (!found) {
				it.remove();
			}
		}
	}

	@Override
	public DataWrapper<AuthorityGroup> addAuthorityGroupByName(String groupName) {
		AuthorityGroup group = new AuthorityGroup();
		group.setName(groupName);
		Long id = groupDao.addAuthorityGroup(group);
		DataWrapper<AuthorityGroup> ret = new DataWrapper<AuthorityGroup>();
		ret.setData(groupDao.getAuthorityGroupById(id));
		return ret;
	}

	@Override
	public DataWrapper<AuthorityGroup> deleteAuthorityGroup(Long id) {
		DataWrapper<AuthorityGroup> ret = new DataWrapper<AuthorityGroup>();
		if (employeeGroupDao.getEmployeeAuthorityGroupsByAuthorityGroupId(id).getData().isEmpty()) {
			groupDao.deleteAuthorityGroup(id);
		} else {
			ret.setErrorCode(ErrorCodeEnum.Authority_Group_Used);
		}
		return ret;
	}
}
