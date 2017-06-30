package com.fico.ps.df.ruleentities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 *
 * @author Siddu
 */
@Entity
@Table(name = "SECRTY_AUTHORIZATION")
@SequenceGenerator(name = "SEQ_SECRTY_AUTHORIZATION_ID", sequenceName = "SEQ_SECRTY_AUTHORIZATION_ID", allocationSize = 1)
@NamedQueries({ @NamedQuery(name = "SecrtyAuthorization.findAll", query = "SELECT s FROM SecrtyAuthorization s"),
@NamedQuery(name = "SecrtyAuthorization.UpdateByUserPermisssion", query = "UPDATE SecrtyAuthorization s SET s.permissionsetID = :permissionsetID, s.lastUpdatedDttm = :lastUpdatedDttm WHERE s.userPartyID = :userPartyID"),
@NamedQuery(name = "SecrtyAuthorization.findByUserPermisssion", query = "SELECT s FROM SecrtyAuthorization s WHERE s.userPartyID = :userPartyID and s.tenantCD = :tenantCD") })
public class SecrtyAuthorization implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(generator = "SEQ_SECRTY_AUTHORIZATION_ID", strategy = GenerationType.SEQUENCE)
	@Column(name = "AUTHORIZATION_ID", nullable = false, precision = 19)
	private long authorizationID;

	@Basic(optional = false)
	@Column(name = "VERSION", nullable = false, precision = 19)
	private int version;
	
	@Basic(optional = false)
	@Column(name = "PERMISSIONSET_ID", nullable = false, precision = 19)
	private int permissionsetID;

	@Basic(optional = false)
	@Column(name = "USER_PARTY_ID", nullable = false, precision = 19)
	//@OneToOne(fetch=FetchType.LAZY)
	//@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="USER_PARTY_ID")
	private long userPartyID;
	
	@Basic(optional = false)
	@Column(name = "TENANT_CD", nullable = false, precision = 19)
	//@OneToOne(fetch=FetchType.LAZY)
	//@JoinColumn(name="TENANT_CD")
	private int tenantCD;
	
	@Basic(optional = false)
	@Column(name = "LAST_UPDATED_BY_USER_ID", nullable = false, precision = 19)
	private int lastUpdatedByUserID;

	@Basic(optional = false)
	@Column(name = "CREATED_BY_USER_ID", nullable = false, precision = 19)
	private int createdByUserID;
	
	@Basic(optional = false)
	@Column(name = "ROW_STATUS_CD", nullable = false, length = 96)
	private String rowStatusCD;

	@Basic(optional = false)
	@Column(name = "AUTHORIZATION_TYPE_STRG", nullable = false, length = 20)
	private String authorizationTypeStrg;

	@Basic(optional = false)
	@Column(name = "ROLE_ID", precision = 19)
	//@OneToOne(fetch=FetchType.LAZY)
	//@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="ROLE_ID")
	private int roleID;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DTTM")
	private Date createdDttm;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_DTTM")
	private Date lastUpdatedDttm;


	public SecrtyAuthorization() {
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getRowStatusCD() {
		return rowStatusCD;
	}

	public void setRowStatusCD(String rowStatusCD) {
		this.rowStatusCD = rowStatusCD;
	}

	public Date getCreatedDttm() {
		return createdDttm;
	}

	public void setCreatedDttm(Date createdDttm) {
		this.createdDttm = createdDttm;
	}

	public Date getLastUpdatedDttm() {
		return lastUpdatedDttm;
	}

	public void setLastUpdatedDttm(Date lastUpdatedDttm) {
		this.lastUpdatedDttm = lastUpdatedDttm;
	}

	public long getAuthorizationID() {
		return authorizationID;
	}

	public void setAuthorizationID(long authorizationID) {
		this.authorizationID = authorizationID;
	}

	public int getPermissionsetID() {
		return permissionsetID;
	}

	public void setPermissionsetID(int permissionsetID) {
		this.permissionsetID = permissionsetID;
	}

	public long getUserPartyID() {
		return userPartyID;
	}

	public void setUserPartyID(long userPartyID) {
		this.userPartyID = userPartyID;
	}

	public int getTenantCD() {
		return tenantCD;
	}

	public void setTenantCD(int tenantCD) {
		this.tenantCD = tenantCD;
	}

	public int getLastUpdatedByUserID() {
		return lastUpdatedByUserID;
	}

	public void setLastUpdatedByUserID(int lastUpdatedByUserID) {
		this.lastUpdatedByUserID = lastUpdatedByUserID;
	}

	public int getCreatedByUserID() {
		return createdByUserID;
	}

	public void setCreatedByUserID(int createdByUserID) {
		this.createdByUserID = createdByUserID;
	}

	public String getAuthorizationTypeStrg() {
		return authorizationTypeStrg;
	}

	public void setAuthorizationTypeStrg(String authorizationTypeStrg) {
		this.authorizationTypeStrg = authorizationTypeStrg;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += authorizationID;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		SecrtyAuthorization other = (SecrtyAuthorization) object;
		if (this.authorizationID != other.authorizationID) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.fico.ps.df.ruleentities.SecrtyAuthorization[ authorizationID =" + authorizationID + "version =" + version + "rowStatusCD =" + rowStatusCD + " permissionsetID =" + permissionsetID + " createdByUserID =" + createdByUserID+"]";
	}
}
