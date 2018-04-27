/**
 * 
 */
package com.fhzz.business.service.atomikos;

/**
 * @author YangYi
 * 
 */
public interface AtomikosService {
	void saveOracle();

	void saveMysql();

	void saveBoth();

	void saveBothWithOracleException();

	void saveBothWithMysqlException();
}
