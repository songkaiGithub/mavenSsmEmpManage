package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Emp;
import com.po.EmpWelfare;
import com.po.Welfare;

@Service("IEmpWelfareMapper")
public interface IEmpWelfareMapper {
	//Ա������������
	public int save(EmpWelfare empWelfare);
	//ɾ��
	public int delById(Integer eid);
	//ͨ����Ա���ı�Ų�ѯ��Ա���ĸ���
	public List<Welfare> findById(Integer eid);
}
