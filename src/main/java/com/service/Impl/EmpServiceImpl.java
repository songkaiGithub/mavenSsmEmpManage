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
		//����Ա�����
		int code=daoservice.getEmpMapper().save(emp);
		//�����Ա����н�ʺ͸������
		  //��ȡ��Ա���ı��
		if(code>0){
			Integer eid=daoservice.getEmpMapper().findMaxEid();
			//н�ʱ���
			Salary sa=new Salary(eid,emp.getEmoney());
			daoservice.getSalaryMapper().save(sa);
			//Ա������
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
 //ɾ��
	@Override
	public boolean delById(Integer eid) {
		//�ȰѴӱ��еĽ���ɾ����ɾ��������
		daoservice.getSalaryMapper().delById(eid);
		daoservice.getEmpwelfareMapper().delById(eid);
		int code=daoservice.getEmpMapper().delById(eid);
		if(code>0){
			return true;
		}
		return false;
	}
	//��ѯ����

	@Override
	public Emp findById(Integer eid) {
		//��ȡԱ������
		Emp oldemp=daoservice.getEmpMapper().findById(eid);
		//��ȡ��Ա����н��
		Salary sa=daoservice.getSalaryMapper().findById(eid);
		if(sa!=null&&sa.getEmoney()!=null){
			oldemp.setEmoney(sa.getEmoney());
		}
		List<Welfare> lswf=daoservice.getEmpwelfareMapper().findById(eid);
		System.out.println("aaa"+lswf.size());
		//������Id
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
		//�޸�Ա����Ϣ
		int code=daoservice.getEmpMapper().update(emp);
		if(code>0){
			/**����н��****/
			 //��ȡԭ�е�н��
			Salary oldsa=daoservice.getSalaryMapper().findById(emp.getEid());
			if(oldsa!=null&&oldsa.getEmoney()!=null){
				//�޸�
				oldsa.setEmoney(emp.getEmoney());
				daoservice.getSalaryMapper().update(oldsa);
			}else{
				//н�ʱ���
				Salary sa=new Salary(emp.getEid(),emp.getEmoney());
				daoservice.getSalaryMapper().save(sa);
			}
			//����Ա���ĸ���
			List<Welfare> lswf=daoservice.getEmpwelfareMapper().findById(emp.getEid());
			if(lswf!=null&&lswf.size()>0){
				//ɾ��ԭ�еĸ���
				daoservice.getEmpwelfareMapper().delById(emp.getEid());
			}
			//���渣��
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
