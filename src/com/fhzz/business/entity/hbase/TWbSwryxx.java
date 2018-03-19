package com.fhzz.business.entity.hbase;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fhzz.core.hbase.entity.HBaseEntity;

/**
 * TWbSwryxx entity. @author MyEclipse Persistence Tools
 */
@Table(name = "T_WB_SWRYXX")
public class TWbSwryxx extends HBaseEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 6547314207865409413L;
	private String dszykzj;
	private String swryxm;
	private String zjlx;
	private String zjhm;
	private String swsj;
	private String xwsj;
	private String zdh;
	private String yycsdm;
	private String scrzyksj;
	private String zykzhxgsj;
	private String dsbs;
	private String scrhjksj;
	private String updateTime;

	// Constructors

	/** default constructor */
	public TWbSwryxx() {
	}

	/** minimal constructor */
	public TWbSwryxx(String dszykzj, String zjhm, String swsj, String scrzyksj, String dsbs, String updateTime) {
		this.dszykzj = dszykzj;
		this.zjhm = zjhm;
		this.swsj = swsj;
		this.scrzyksj = scrzyksj;
		this.dsbs = dsbs;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public TWbSwryxx(String dszykzj, String swryxm, String zjlx, String zjhm, String swsj, String xwsj, String zdh,
			String yycsdm, String scrzyksj, String zykzhxgsj, String dsbs, String scrhjksj, String updateTime) {
		this.dszykzj = dszykzj;
		this.swryxm = swryxm;
		this.zjlx = zjlx;
		this.zjhm = zjhm;
		this.swsj = swsj;
		this.xwsj = xwsj;
		this.zdh = zdh;
		this.yycsdm = yycsdm;
		this.scrzyksj = scrzyksj;
		this.zykzhxgsj = zykzhxgsj;
		this.dsbs = dsbs;
		this.scrhjksj = scrhjksj;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@Column(name = "DSZYKZJ", unique = true, nullable = false, length = 32)
	public String getDszykzj() {
		return this.dszykzj;
	}

	public void setDszykzj(String dszykzj) {
		this.dszykzj = dszykzj;
	}

	@Column(name = "SWRYXM", length = 50)
	public String getSwryxm() {
		return this.swryxm;
	}

	public void setSwryxm(String swryxm) {
		this.swryxm = swryxm;
	}

	@Column(name = "ZJLX", length = 4)
	public String getZjlx() {
		return this.zjlx;
	}

	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}

	@Column(name = "ZJHM", nullable = false, length = 30)
	public String getZjhm() {
		return this.zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	@Column(name = "SWSJ", nullable = false, length = 14)
	public String getSwsj() {
		return this.swsj;
	}

	public void setSwsj(String swsj) {
		this.swsj = swsj;
	}

	@Column(name = "XWSJ", length = 14)
	public String getXwsj() {
		return this.xwsj;
	}

	public void setXwsj(String xwsj) {
		this.xwsj = xwsj;
	}

	@Column(name = "ZDH", length = 32)
	public String getZdh() {
		return this.zdh;
	}

	public void setZdh(String zdh) {
		this.zdh = zdh;
	}

	@Column(name = "YYCSDM", length = 14)
	public String getYycsdm() {
		return this.yycsdm;
	}

	public void setYycsdm(String yycsdm) {
		this.yycsdm = yycsdm;
	}

	@Column(name = "SCRZYKSJ", nullable = false, length = 14)
	public String getScrzyksj() {
		return this.scrzyksj;
	}

	public void setScrzyksj(String scrzyksj) {
		this.scrzyksj = scrzyksj;
	}

	@Column(name = "ZYKZHXGSJ", length = 14)
	public String getZykzhxgsj() {
		return this.zykzhxgsj;
	}

	public void setZykzhxgsj(String zykzhxgsj) {
		this.zykzhxgsj = zykzhxgsj;
	}

	@Column(name = "DSBS", nullable = false, length = 4)
	public String getDsbs() {
		return this.dsbs;
	}

	public void setDsbs(String dsbs) {
		this.dsbs = dsbs;
	}

	@Column(name = "SCRHJKSJ", length = 14)
	public String getScrhjksj() {
		return this.scrhjksj;
	}

	public void setScrhjksj(String scrhjksj) {
		this.scrhjksj = scrhjksj;
	}

	@Column(name = "UPDATE_TIME", nullable = false, length = 7)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}