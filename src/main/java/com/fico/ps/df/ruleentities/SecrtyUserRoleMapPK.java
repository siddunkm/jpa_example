/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fico.ps.df.ruleentities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Siddu
 */
@Embeddable
public class SecrtyUserRoleMapPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Basic(optional = false)
	@Column(name = "ROLE_ID")
	private int roleID;
	@Basic(optional = false)
	@Column(name = "USER_PARTY_ID")
	private long userPartyID;

	public SecrtyUserRoleMapPK() {
	}

	public SecrtyUserRoleMapPK(int roleID, long userPartyID) {
		this.roleID = roleID;
		this.userPartyID = userPartyID;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public long getUserPartyID() {
		return userPartyID;
	}

	public void setUserPartyID(long userPartyID) {
		this.userPartyID = userPartyID;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += roleID;
		hash += userPartyID;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		SecrtyUserRoleMapPK other = (SecrtyUserRoleMapPK) object;
		if (this.roleID != other.roleID) {
			return false;
		}
		if (this.userPartyID != other.userPartyID) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("SecrtyUserRoleMapPK[roleID=%d, userPartyID=%d", roleID, userPartyID);
	}

}
