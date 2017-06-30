/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fico.ps.df.ruleentities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Siddu
 */
@Entity
@Table(name = "SECRTY_USER_ROLE_MAP")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "SecrtyUserRoleMap.findAll", query = "SELECT s FROM SecrtyUserRoleMap s"),
		@NamedQuery(name = "SecrtyUserRoleMap.findByRoleId", query = "SELECT s FROM SecrtyUserRoleMap s WHERE s.secrtyUserRoleMapPK.roleID = :roleID"),
		@NamedQuery(name = "SecrtyUserRoleMap.findByUserPartyID", query = "SELECT s FROM SecrtyUserRoleMap s WHERE s.secrtyUserRoleMapPK.userPartyID = :userPartyID") })
public class SecrtyUserRoleMap implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected SecrtyUserRoleMapPK secrtyUserRoleMapPK;

	@Basic(optional = false)
	@Column(name = "TENANT_CD")
	private int tenantCD;

	@Basic(optional = false)
	@Column(name = "LAST_UPDATED_BY_USER_ID")
	private long lastUpdatedByUserID;

	@Basic(optional = false)
	@Column(name = "CREATED_BY_USER_ID")
	private long createdByUserID;

	@Basic(optional = false)
	@Column(name = "ROW_STATUS_CD", length = 96)
	private String rowStatusCD;

	@Column(name = "ACTIVE_FROM_DT")
	@Temporal(TemporalType.DATE)
	private Date activeFromDt;

	@Column(name = "ACTIVE_TO_DT")
	@Temporal(TemporalType.DATE)
	private Date activeToDt;

	@Basic(optional = false)
	@Column(name = "PRIMARY_ROLE_FLG", length = 2)
	private String primaryRoleFlag;

	@JoinColumn(name = "USER_PARTY_ID", referencedColumnName = "USER_PARTY_ID", insertable = false, updatable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private SecrtyUser secrtyUser;

	public SecrtyUserRoleMap() {
	}

	public SecrtyUserRoleMap(SecrtyUserRoleMapPK secrtyUserRoleMapPK) {
		this.secrtyUserRoleMapPK = secrtyUserRoleMapPK;
	}

	public SecrtyUserRoleMap(int roleID, long userPartyID) {
		this.secrtyUserRoleMapPK = new SecrtyUserRoleMapPK(roleID, userPartyID);
	}

	public SecrtyUserRoleMapPK getSecrtyUserRoleMapPK() {
		return secrtyUserRoleMapPK;
	}

	public void setSecrtyUserRoleMapPK(SecrtyUserRoleMapPK secrtyUserRoleMapPK) {
		this.secrtyUserRoleMapPK = secrtyUserRoleMapPK;
	}

	public int getTenantCD() {
		return tenantCD;
	}

	public void setTenantCD(int tenantCD) {
		this.tenantCD = tenantCD;
	}

	public long getLastUpdatedByUserID() {
		return lastUpdatedByUserID;
	}

	public void setLastUpdatedByUserID(long lastUpdatedByUserID) {
		this.lastUpdatedByUserID = lastUpdatedByUserID;
	}

	public long getCreatedByUserID() {
		return createdByUserID;
	}

	public void setCreatedByUserID(long createdByUserID) {
		this.createdByUserID = createdByUserID;
	}

	public String getRowStatusCD() {
		return rowStatusCD;
	}

	public void setRowStatusCD(String rowStatusCD) {
		this.rowStatusCD = rowStatusCD;
	}

	public Date getActiveFromDt() {
		return activeFromDt;
	}

	public void setActiveFromDt(Date activeFromDt) {
		this.activeFromDt = activeFromDt;
	}

	public Date getActiveToDt() {
		return activeToDt;
	}

	public void setActiveToDt(Date activeToDt) {
		this.activeToDt = activeToDt;
	}

	public String getPrimaryRoleFlag() {
		return primaryRoleFlag;
	}

	public void setPrimaryRoleFlag(String primaryRoleFlag) {
		this.primaryRoleFlag = primaryRoleFlag;
	}

	public SecrtyUser getSecrtyUser() {
		return secrtyUser;
	}

	public void setSecrtyUser(SecrtyUser secrtyUser) {
		this.secrtyUser = secrtyUser;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (secrtyUserRoleMapPK != null ? secrtyUserRoleMapPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		SecrtyUserRoleMap other = (SecrtyUserRoleMap) object;
		if ((this.secrtyUserRoleMapPK == null && other.secrtyUserRoleMapPK != null)
				|| (this.secrtyUserRoleMapPK != null && !this.secrtyUserRoleMapPK.equals(other.secrtyUserRoleMapPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("SecrtyUserRoleMapPK[ PK=%s]", secrtyUserRoleMapPK);
	}

	// as of Jan 25 2013, Blaze doesn't support Comparable or Comparator -
	// causes "ArrayIndexOutOfBoundsException. Sort manually
	// @Override
	// public int compare(RuleTrackVersion o1, RuleTrackVersion o2) {
	//
	// if (o1.getVersionTime() == o2.getVersionTime()){
	// return 0;
	// }
	// if (o1.getVersionTime() < o2.getVersionTime()){
	// return -1;
	// }
	// return 1;
	// }

}
