/**
 * 
 */
package com.fhzz.business.dao.db.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fhzz.business.dao.db.HibernateDemoDao;
import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.dao.impl.BaseDaoImpl;
import com.fhzz.core.vo.PageParam;
import com.fhzz.core.vo.PageResult;

/**
 * @author YangYi
 * 
 */
@Repository
public class HibernateDemoDaoImpl extends BaseDaoImpl<DemoTable> implements HibernateDemoDao {

	@Override
	public PageResult<DemoTable> pagedQuery(PageParam pageParam) {
		String hql = "from DemoTable order by createTime desc";
		return this.getHibernateTemplate().pagedQuery(hql, null, pageParam);
	}

	@Override
	public PageResult<DemoTable> pagedQuery(DatagridDemoParam datagridDemoParam) {
		List<Object> hqlArgs = new ArrayList<Object>();
		StringBuffer hqlSb = new StringBuffer();
		hqlSb.append("FROM DemoTable WHERE 1=1 ");
		if (datagridDemoParam.getStartTime() != null) {
			hqlSb.append("AND customTime > ? ");
			hqlArgs.add(datagridDemoParam.getStartTime());
		}
		if (datagridDemoParam.getEndTime() != null) {
			hqlSb.append("AND customTime < ? ");
			hqlArgs.add(datagridDemoParam.getEndTime());
		}
		if (!StringUtils.isEmpty(datagridDemoParam.getName())) {
			hqlSb.append("AND name LIKE ? ");
			hqlArgs.add("%" + datagridDemoParam.getName() + "%");
		}
		if (datagridDemoParam.getCountNum() != null) {
			hqlSb.append("AND countNum = ? ");
			hqlArgs.add(datagridDemoParam.getCountNum());
		}
		hqlSb.append("ORDER BY updateTime DESC");
		return this.getHibernateTemplate().pagedQuery(hqlSb.toString(), hqlArgs.toArray(), datagridDemoParam);
	}
	
	public List<DemoTable> findBy(String propertyName, Object value){
		return this.getHibernateTemplate().findBy(DemoTable.class, propertyName, value);
	}

	public List<DemoTable> findBy(String propertyName, Object value,String orderBy,boolean isAsc){
		return this.getHibernateTemplate().findBy(DemoTable.class, propertyName, value, orderBy, isAsc);
	}
	
	public List<DemoTable> findByValues(String propertyName, Object[] values){
		return this.getHibernateTemplate().findByValues(DemoTable.class, propertyName, values);
	}
	
	
}
