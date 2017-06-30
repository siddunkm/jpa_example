package com.fico.ps.df.ruleentities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Siddu
 */
@Entity
// @Cache(shared=false)//Coherence does the caching we need
@Table(name = "SECRTY_USER_ROLE")
@NamedQueries({
		@NamedQuery(name = "SecrtyUserRole.findAll", query = "SELECT s FROM SecrtyUserRole s"),
		@NamedQuery(name = "SecrtyUserRole.findByRoleId", query = "SELECT s FROM SecrtyUserRole s WHERE s.roleID = :roleID"),
		@NamedQuery(name = "SecrtyUserRole.findByRoleUniqueName", query = "SELECT s FROM SecrtyUserRole s WHERE s.roleUniqueName = :roleUniqueName"),
		@NamedQuery(name = "SecrtyUserRole.findByRoleName", query = "SELECT s FROM SecrtyUserRole s WHERE s.roleName = :roleName"),
		@NamedQuery(name = "SecrtyUserRole.findByRoleNames", query = "SELECT s FROM SecrtyUserRole s WHERE s.roleName in :roleNames") })
public class SecrtyUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ROLE_ID", nullable = false)
	private int roleID;

	@Basic(optional = false)
	@Column(name = "TENANT_CD", nullable = false)
	private int tenantCD;

	@Basic(optional = false)
	@Column(name = "LAST_UPDATED_BY_USER_ID", nullable = false)
	private int lastUpdatedByUserID;

	@Basic(optional = false)
	@Column(name = "CREATED_BY_USER_ID", nullable = false)
	private int createdByUserID;

	@Basic(optional = false)
	@Column(name = "ROW_STATUS_CD", nullable = false, length = 96)
	private String rowStatusCD;

	@Basic(optional = false)
	@Column(name = "ROLE_UNIQUE_NAME", nullable = false, length = 200)
	private String roleUniqueName;

	@Basic(optional = false)
	@Column(name = "ROLE_NAME", nullable = false, length = 200)
	private String roleName;

	public SecrtyUserRole() {
	}

	public SecrtyUserRole(int roleID, int tenantCD, int lastUpdatedByUserID, int createdByUserID, String rowStatusCD,
			String roleUniqueName, String roleName) {
		this.roleID = roleID;
		this.tenantCD = tenantCD;
		this.lastUpdatedByUserID = lastUpdatedByUserID;
		this.createdByUserID = createdByUserID;
		this.rowStatusCD = rowStatusCD;
		this.roleUniqueName = roleUniqueName;
		this.roleName = roleName;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
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

	public String getRowStatusCD() {
		return rowStatusCD;
	}

	public void setRowStatusCD(String rowStatusCD) {
		this.rowStatusCD = rowStatusCD;
	}

	public String getRoleUniqueName() {
		return roleUniqueName;
	}

	public void setRoleUniqueName(String roleUniqueName) {
		this.roleUniqueName = roleUniqueName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) roleID;
		hash += (int) tenantCD;
		hash += (int) lastUpdatedByUserID;
		hash += (int) createdByUserID;
		hash += (rowStatusCD != null ? rowStatusCD.hashCode() : 0);
		hash += (roleUniqueName != null ? roleUniqueName.hashCode() : 0);
		hash += (roleName != null ? roleName.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SecrtyUserRole)) {
			return false;
		}
		SecrtyUserRole other = (SecrtyUserRole) object;
		if (this.roleID != other.roleID) {
			return false;
		}
		if (this.tenantCD != other.tenantCD) {
			return false;
		}
		if (this.lastUpdatedByUserID != other.lastUpdatedByUserID) {
			return false;
		}
		if (this.createdByUserID != other.createdByUserID) {
			return false;
		}
		if ((this.rowStatusCD == null && other.rowStatusCD != null)
				|| (this.rowStatusCD != null && !this.rowStatusCD.equals(other.rowStatusCD))) {
			return false;
		}
		if ((this.roleUniqueName == null && other.roleUniqueName != null)
				|| (this.roleUniqueName != null && !this.roleUniqueName.equals(other.roleUniqueName))) {
			return false;
		}
		if ((this.roleName == null && other.roleName != null)
				|| (this.roleName != null && !this.roleName.equals(other.roleName))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format(
				"SecrtyUserRole [roleID = %s, tenantCD = %s, lastUpdatedByUserID = %s,  createdByUserID = %s, rowStatusCD = %s, roleUniqueName = %s, roleName = %s",
				roleID, tenantCD, lastUpdatedByUserID, createdByUserID, rowStatusCD, roleUniqueName, roleName);
	}
}
