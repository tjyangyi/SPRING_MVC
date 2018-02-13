/**
 * 
 */
package com.fhzz.business.dao.demo;

import com.fhzz.business.entity.DemoTable;
import com.fhzz.core.dao.BaseDao;
import com.fhzz.core.vo.PageResult;
import com.fhzz.core.vo.PageParam;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月11日 下午1:36:15
 * @Copyright: FHZZ
 */
public interface DatabaseOperationExampleDao extends BaseDao<DemoTable> {

	PageResult<DemoTable> queryDemoTable(PageParam pageParam);
}
