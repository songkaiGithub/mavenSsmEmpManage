package com.po;

import java.io.Serializable;

public class Dep implements Serializable {
	//部门编号
   private Integer depid;
   //部门名称
   private String depname;
public Dep() {
	super();
	// TODO Auto-generated constructor stub
}

public Dep(Integer depid, String depname) {
	super();
	this.depid = depid;
	this.depname = depname;
}

public Integer getDepid() {
	return depid;
}

public void setDepid(Integer depid) {
	this.depid = depid;
}

public String getDepname() {
	return depname;
}

public void setDepname(String depname) {
	this.depname = depname;
}

@Override
public String toString() {
	return "Dep [depid=" + depid + ", deoname=" + depname + "]";
}
}
