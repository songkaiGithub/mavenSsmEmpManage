package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Emp;
import com.po.EmpWelfare;
import com.po.PageBean;
import com.po.Salary;
import com.po.Welfare;
import com.service.IEmpService;
import com.util.DaoServiceUtil;
@Service("EmpServiceImpl")
@Transactional
public class EmpServiceImpl implements IEmpService {
	@Resource(name="DaoService")
    private DaoServiceUtil daoservice;
    
	public DaoServiceUtil getDaoservice() {
		return daoservice;
	}

	public void setDaoservice(DaoServiceUtil daoservice) {
		this.daoservice = daoservice;
	}


	@Override
	public boolean save(Emp emp) {
		//处理员工添加
		int code=daoservice.getEmpMapper().save(emp);
		//处理该员工的薪资和福利添加
		  //获取该员工的编号
		if(code>0){
			Integer eid=daoservice.getEmpMapper().findMaxEid();
			//薪资保存
			Salary sa=new Salary(eid,emp.getEmoney());
			daoservice.getSalaryMapper().save(sa);
			//员工福利
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for(int i=0;i<wids.length;i++){
					EmpWelfare ewf=new EmpWelfare(eid,Integer.parseInt(wids[i]));
					daoservice.getEmpwelfareMapper().save(ewf);
				}
			}
			return true;
		}
		
		return false;
	}

	@Override
	public List<Emp> findAllpage(PageBean pb) {
		
		return daoservice.getEmpMapper().findAllpage(pb.getPage(), pb.getRows());
	}

	@Override
	public Integer findMaxRows() {
		// TODO Auto-generated method stub
		return daoservice.getEmpMapper().findMaxRows();
	}
 //删除
	@Override
	public boolean delById(Integer eid) {
		//先把从表中的进行删除在删除主标中
		daoservice.getSalaryMapper().delById(eid);
		daoservice.getEmpwelfareMapper().delById(eid);
		int code=daoservice.getEmpMapper().delById(eid);
		if(code>0){
			return true;
		}
		return false;
	}
	//查询单个

	@Override
	public Emp findById(Integer eid) {
		//获取员工对象
		Emp oldemp=daoservice.getEmpMapper().findById(eid);
		//获取该员工的薪资
		Salary sa=daoservice.getSalaryMapper().findById(eid);
		if(sa!=null&&sa.getEmoney()!=null){
			oldemp.setEmoney(sa.getEmoney());
		}
		List<Welfare> lswf=daoservice.getEmpwelfareMapper().findById(eid);
		System.out.println("aaa"+lswf.size());
		//处理福利Id
		String[] wids=new String[lswf.size()];
		for(int i=0;i<wids.length;i++){
			Welfare wf=lswf.get(i);
			wids[i]=wf.getWid().toString();
		}
		oldemp.setWids(wids);
		oldemp.setLswf(lswf);
		return oldemp;
	}

	@Override
	public boolean update(Emp emp) {
		//修改员工信息
		int code=daoservice.getEmpMapper().update(emp);
		if(code>0){
			/**更新薪资****/
			 //获取原有的薪资
			Salary oldsa=daoservice.getSalaryMapper().findById(emp.getEid());
			if(oldsa!=null&&oldsa.getEmoney()!=null){
				//修改
				oldsa.setEmoney(emp.getEmoney());
				daoservice.getSalaryMapper().update(oldsa);
			}else{
				//薪资保存
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				daoservice.getSalaryMapper().save(sa);
			}
			//处理员工的福利
			List<Welfare> lswf=daoservice.getEmpwelfareMapper().findById(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				//删除原有的福利
				daoservice.getEmpwelfareMapper().delById(emp.getEid());
			}
			//保存福利
			String[] wids=emp.getWids();
			if(wids!=null&&wids.length>0){
				for(int i=0;i<wids.length;i++){
					EmpWelfare ewf=new EmpWelfare(emp.getEid(),Integer.parseInt(wids[i]));
					daoservice.getEmpwelfareMapper().save(ewf);
				}
			}
			return true;
		}
		return false;
	}
	

}
