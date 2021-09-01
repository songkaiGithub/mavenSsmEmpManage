package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Dep;
import com.po.PageBean;
import com.service.IDepService;
import com.util.DaoServiceUtil;
@Service("DepServiceImpl")
@Transactional
public class DepServiceImpl implements IDepService {
	@Resource(name="DaoService")
     private DaoServiceUtil daoservice;
     
	public DaoServiceUtil getDaoservice() {
		return daoservice;
	}

	public void setDaoservice(DaoServiceUtil daoservice) {
		this.daoservice = daoservice;
	}

	@Override
	public List<Dep> findAll() {
		
		return daoservice.getDepMapper().findAll();
	}

	@Override
	public boolean depsave(Dep dep) {
	int code=daoservice.getDepMapper().depsave(dep);
	if(code>0){
		return true;
	}else{
		return false;
	}
	}

	@Override
	public boolean depupdate(Dep dep) {
		int code=daoservice.getDepMapper().depupdate(dep);
		if(code>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Dep find(Integer depid) {
		
		return daoservice.getDepMapper().find(depid);
	}

	@Override
	public int findmaxrows() {
		
		return daoservice.getDepMapper().findmaxrows();
	}

	@Override
	public List<Dep> findpage(PageBean pb) {
		System.out.println("Service‘¯≤È—Ø");
		return daoservice.getDepMapper().findpage(pb.getPage(), pb.getRows());
	};
}

