package com.po;

import java.io.Serializable;

public class EmpWelfare implements Serializable {
	//Ա����������
	private Integer weid;
	//Ա������
	private Integer eid;
	//��������
	private Integer wid;
	public EmpWelfare() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmpWelfare(Integer weid, Integer eid, Integer wid) {
		super();
		this.weid = weid;
		this.eid = eid;
		this.wid = wid;
	}
	//���ר��
	public EmpWelfare(Integer eid, Integer wid) {
		super();
		this.eid = eid;
		this.wid = wid;
	}
	public Integer getWeid() {
		return weid;
	}
	public void setWeid(Integer weid) {
		this.weid = weid;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	@Override
	public String toString() {
		return "empwelfare [weid=" + weid + ", eid=" + eid + ", wid=" + wid + "]";
	}
   
}
