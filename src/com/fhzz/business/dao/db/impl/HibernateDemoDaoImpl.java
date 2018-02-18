/**
 * 
 */
package com.fhzz.business.dao.db.impl;

import org.springframework.stereotype.Repository;

import com.fhzz.business.dao.db.HibernateDemoDao;
import com.fhzz.business.entity.DemoTable;
import com.fhzz.business.vo.datagrid.DatagridDemoParam;
import com.fhzz.core.dao.BaseDaoImpl;
import com.fhzz.core.vo.PageParam;
import com.fhzz.core.vo.PageResult;

/**
 * @author YangYi
 * 
 */
@Repository
public class HibernateDemoDaoImpl extends BaseDaoImpl<DemoTable> implements
		HibernateDemoDao {

	@Override
	public PageResult<DemoTable> queryDemoTable(PageParam pageParam) {
		String hql = "from DemoTable order by createTime desc";
		return this.getHibernateTemplate().pagedQuery(hql, pageParam.getPage(),
				pageParam.getRows(), null);
	}

	@Override
	public PageResult<DemoTable> queryDemoTable(
			DatagridDemoParam datagridDemoParam) {
		String hql = "from DemoTable";
		return this.getHibernateTemplate().pagedQuery(hql, 1, 10, null);
	}

}
