/**
 * 
 */
package com.fhzz.core.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.fhzz.core.vo.PageParam;
import com.fhzz.core.vo.PageResult;

/**
 * @author: YangYi
 * @CreateTime: 2018年2月13日 上午11:35:19
 * @Copyright: FHZZ
 */
public class JdbcTemplageSupport extends JdbcTemplate {
	public <T> PageResult<T> pagedQuery(String sql, Class<T> mappedClass, PageParam pageParam, Object... sqlArgs) {
		// 查询总条数
		StringBuffer countSql = new StringBuffer();
		countSql.append("SELECT COUNT(*) FROM (");
		countSql.append(sql);
		countSql.append(")");
		int totalCount = this.queryForObject(countSql.toString(), Integer.class, sqlArgs);
		// 查询数据
		StringBuffer dataSql = new StringBuffer();
		dataSql.append("SELECT");
		dataSql.append("	*");
		dataSql.append("FROM");
		dataSql.append("	(");
		dataSql.append("		SELECT");
		dataSql.append("			temp.*,");
		dataSql.append("			ROWNUM num");
		dataSql.append("		FROM");
		dataSql.append("			(").append(sql).append(") temp");
		dataSql.append("		WHERE");
		dataSql.append("			ROWNUM <= ").append(pageParam.getEndIndex());
		dataSql.append("	) WHERE　num > ").append(pageParam.getStartIndex());
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(mappedClass);
		List<T> list = this.query(dataSql.toString(), rowMapper, sqlArgs);
		// 组装PageResult
		PageResult<T> page = new PageResult<T>(totalCount, list);
		return page;
	}

	public <T> PageResult<T> pagedQuery(String sql, Class<T> mappedClass, int pageIndex, int pageSize,
			Object... sqlArgs) {
		return (PageResult<T>) this.pagedQuery(sql, mappedClass, new PageParam(pageIndex, pageSize), sqlArgs);
	}
}
