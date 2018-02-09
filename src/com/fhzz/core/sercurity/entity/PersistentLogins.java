package com.fhzz.core.sercurity.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PersistentLogins entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogins implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 8470653294948803949L;
	private String series;
	private String username;
	private String token;
	private Date lastUsed;

	// Constructors

	/** default constructor */
	public PersistentLogins() {
	}

	/** minimal constructor */
	public PersistentLogins(String series) {
		this.series = series;
	}

	/** full constructor */
	public PersistentLogins(String series, String username, String token,
			Date lastUsed) {
		this.series = series;
		this.username = username;
		this.token = token;
		this.lastUsed = lastUsed;
	}

	// Property accessors
	@Id
	@Column(name = "SERIES", unique = true, nullable = false, length = 64)
	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	@Column(name = "USERNAME", length = 64)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "TOKEN", length = 64)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "LAST_USED", length = 11)
	public Date getLastUsed() {
		return this.lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}