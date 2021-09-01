package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.po.Emp;

@Service("IEmpMapper")
public interface IEmpMapper {
	//Ա�����
	public int save(Emp emp);
	//��ȡԱ��id�����ı��
	public Integer findMaxEid();
	//��ҳ��ѯ
	public List<Emp> findAllpage(@Param(value = "page") Integer page, @Param(value = "rows") Integer rows);
	//��ѯ�ܼ�¼��
	public Integer findMaxRows();
	//ɾ��Ա��
	public int delById(Integer eid);
	//��ѯ����Ա��
    public Emp findById(Integer eid);
    //�޸�
    public int update(Emp emp);	
   

}
