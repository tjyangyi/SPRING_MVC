package com.fhzz.core.dao.impl.support;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	/**
	 * 
	 * @param sql
	 *            查询语句
	 * @param sqlArgs
	 *            查询参数List,顺序需要与sql中的?一一对应
	 * @param mappedClass
	 *            查询的结果返回的封装CLASS
	 * @param pageParam
	 *            page的参数，包括pageIndex,pageSize
	 * @return
	 */
	public <T> PageResult<T> pagedQuery(String sql, List<Object> sqlArgs,
			Class<T> mappedClass, PageParam pageParam) {
		return this.pagedQuery(sql, sqlArgs.toArray(), mappedClass, pageParam);
	}

	/**
	 * 
	 * @param sql
	 *            查询语句
	 * @param sqlArgs
	 *            查询参数List,顺序需要与sql中的?一一对应
	 * @param mappedClass
	 *            查询的结果返回的封装CLASS
	 * @param page
	 *            查询的页码
	 * @param rows
	 *            一页显示的行数
	 * @return
	 */
	public <T> PageResult<T> pagedQuery(String sql, List<Object> sqlArgs,
			Class<T> mappedClass, int page, int rows) {
		return this.pagedQuery(sql, sqlArgs.toArray(), mappedClass, page, rows);
	}

	/**
	 * 
	 * @param sql
	 *            查询语句
	 * @param sqlArgs
	 *            查询参数Object数组,顺序需要与sql中的?一一对应
	 * @param mappedClass
	 *            查询的结果返回的封装CLASS
	 * @param page
	 *            查询的页码
	 * @param rows
	 *            一页显示的行数
	 * @return
	 */
	public <T> PageResult<T> pagedQuery(String sql, Object[] sqlArgs,
			Class<T> mappedClass, int page, int rows) {
		return this.pagedQuery(sql, sqlArgs, mappedClass, new PageParam(page,
				rows));
	}

	/**
	 * 
	 * @param sql
	 *            查询语句
	 * @param sqlArgs
	 *            查询参数Object数组,顺序需要与sql中的?一一对应
	 * @param mappedClass
	 *            查询的结果返回的封装CLASS
	 * @param pageParam
	 *            page的参数，包括pageIndex,pageSize
	 * @return
	 */
	public <T> PageResult<T> pagedQuery(String sql, Object[] sqlArgs,
			Class<T> mappedClass, PageParam pageParam) {
		// 查询总条数
		int totalCount = this.queryForObject(this.buildCountSql(sql),
				Integer.class, sqlArgs);
		// 查询数据
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(mappedClass);
		List<T> list = this.query(this.buildDataSql(sql, pageParam), sqlArgs,
				rowMapper);
		// 组装PageResult
		PageResult<T> page = new PageResult<T>(totalCount, list);
		return page;
	}

	/**
	 * 
	 * @param sql
	 *            查询语句
	 * @param mappedClass
	 *            查询的结果返回的封装CLASS
	 * @param pageParam
	 *            page的参数，包括pageIndex,pageSize
	 * @param sqlArgs
	 *            查询语句传入的参数
	 * @return
	 */
	public <T> PageResult<T> pagedQuery(String sql, Class<T> mappedClass,
			PageParam pageParam, Object... sqlArgs) {
		// 查询总条数
		int totalCount = this.queryForObject(this.buildCountSql(sql),
				Integer.class, sqlArgs);
		// 查询数据
		RowMapper<T> rowMapper = BeanPropertyRowMapper.newInstance(mappedClass);
		List<T> list = this.query(this.buildDataSql(sql, pageParam), rowMapper,
				sqlArgs);
		// 组装PageResult
		PageResult<T> page = new PageResult<T>(totalCount, list);
		return page;
	}

	/**
	 * 
	 * @param sql
	 *            查询语句
	 * @param mappedClass
	 *            查询的结果返回的封装CLASS
	 * @param page
	 *            查询的页码
	 * @param rows
	 *            一页显示的行数
	 * @param sqlArgs
	 *            查询语句传入的参数
	 * @return
	 */
	public <T> PageResult<T> pagedQuery(String sql, Class<T> mappedClass,
			int page, int rows, Object... sqlArgs) {
		return this.pagedQuery(sql, mappedClass, new PageParam(page, rows),
				sqlArgs);
	}

	/**
	 * 去除SELECT *语句，便于SELECT count(*)
	 */
	private String removeSelect(String sql) {
		int beginPosition = sql.toLowerCase().indexOf("from");
		return sql.substring(beginPosition);
	}

	/**
	 * 去除order by 提高select count(*)的速度
	 */
	private String removeOrders(String sql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	private String buildCountSql(String sql) {
		StringBuffer countSql = new StringBuffer();
		countSql.append("SELECT COUNT(*) ");
		countSql.append(this.removeOrders(this.removeSelect(sql)));
		return countSql.toString();
	}

	private String buildDataSql(String sql, PageParam pageParam) {
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
		return dataSql.toString();
	}

}
