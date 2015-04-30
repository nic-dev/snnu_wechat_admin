/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-11  Create	
 */
package snnu.wechat.framework.dao.hql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import snnu.wechat.framework.log.Log;


public class Hql {
	private String hql;
	//TODO Parameter 分为UpdateParemeter 和 QueryParameter
	private List<Parameter>  parameterList;
	public Hql(String hql, List<Parameter> parameterList) {
		this.hql = hql;
		this.parameterList = parameterList;
	}
	public Hql(String hql, Parameter ... parameters) {
		this.hql = hql;
		this.parameterList = Arrays.asList(parameters);
	}
	public String getHql() {
		return hql;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public List<Parameter>  getParameterList() {
		return parameterList;
	}
	public void setParameterList(List<Parameter>  parameterList) {
		this.parameterList = parameterList;
	}
	//TODO 目前只针对查询语句进行编译
	public void compile(){
		StringBuilder hqlSb=new StringBuilder(hql);
		StringBuilder conditionSb=new StringBuilder();
		List<Parameter> newParameterList=new ArrayList<Parameter>();
		for (Parameter parameter : parameterList) {
			if(parameter.getValue()!=null){
			//	conditionSb.append(" ").append(parameter.getName()).append(parameter.getOpt()).append(parameter.getValue()).append(" ");
				conditionSb.append(" ").append(parameter.getName()).append(parameter.getOpt()).append(":").append(parameter.getName()).append(" ");
				newParameterList.add(parameter);
			}
			
		}
		if(conditionSb.length()>0){
			if (hqlSb.indexOf("where")<0){
				hqlSb.append(" where");
			}
			hqlSb.append(conditionSb);
			hql=hqlSb.toString();
			setParameterList(newParameterList);
		}
		Log.logInfo("compiled hqlStr="+hql);
	}
	
}

