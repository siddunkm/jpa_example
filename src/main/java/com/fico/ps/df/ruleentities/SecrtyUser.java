package com.fico.ps.df.ruleentities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author Siddu
 */
@Entity
@Table(name = "SECRTY_USER")
@SequenceGenerator(name = "SEQ_SECRTY_USER_ID", sequenceName = "SEQ_SECRTY_USER_ID", allocationSize = 1)
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "SecrtyUser.findAll", query = "SELECT s FROM SecrtyUser s"),
@NamedQuery(name = "SecrtyUser.findByUserName", query = "SELECT s FROM SecrtyUser s WHERE s.userName = :userName") })
public class SecrtyUser implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(generator = "SEQ_SECRTY_USER_ID", strategy = GenerationType.SEQUENCE)
	@Column(name = "USER_PARTY_ID", nullable = false, precision = 19)
	private long userPartyID;

	@Basic(optional = false)
	@Column(name = "VERSION", nullable = false, precision = 19)
	private int version;

	@Basic(optional = false)
	@Column(name = "ROW_STATUS_CD", nullable = false, length = 96)
	private String rowStatusCD;

	@Basic(optional = false)
	@Column(name = "USER_NAME", nullable = false, length = 120)
	private String userName;

	@Basic(optional = false)
	@Column(name = "FIRST_NAME", length = 96)
	private String firstName;

	@Basic(optional = false)
	@Column(name = "LAST_NAME", length = 120)
	private String lastName;

	@Basic(optional = false)
	@Column(name = "USER_PASSWORD", length = 96)
	private String userPassword;

	@Basic(optional = false)
	@Column(name = "EMAIL_ID", length = 120)
	private String emailID;

	@Basic(optional = false)
	@Column(name = "ACTIVE_TENANT_CD", precision = 19)
	private int activeTenantCD;

	@Basic(optional = false)
	@Column(name = "PRI_WORK_ROLE_ID", precision = 19)
	private int priWorkRoleID;

	@Column(name = "CREATED_DTTM")
	@Temporal(TemporalType.DATE)
	private Date createdDttm;

	@Column(name = "LAST_UPDATED_DTTM")
	@Temporal(TemporalType.DATE)
	private Date lastUpdatedDttm;

	@PrivateOwned
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "secrtyUser", fetch = FetchType.EAGER)
	private List<SecrtyUserRoleMap> secrtyUserRoleMapList;

	@Transient
	private int numVersionsExist;

	public SecrtyUser() {
	}

	public SecrtyUser(int version, String rowStatusCD, String userName, int activeTenantCD) {
		this.version = version;
		this.rowStatusCD = rowStatusCD;
		this.userName = userName;
		this.activeTenantCD = activeTenantCD;
	}

	public long getUserPartyID() {
		return userPartyID;
	}

	public void setUserPartyID(long userPartyID) {
		this.userPartyID = userPartyID;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public int getActiveTenantCD() {
		return activeTenantCD;
	}

	public void setActiveTenantCD(int activeTenantCD) {
		this.activeTenantCD = activeTenantCD;
	}

	public int getPriWorkRoleID() {
		return priWorkRoleID;
	}

	public void setPriWorkRoleID(int priWorkRoleID) {
		this.priWorkRoleID = priWorkRoleID;
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

	public List<SecrtyUserRoleMap> getSecrtyUserRoleMapList() {
		return secrtyUserRoleMapList;
	}

	public void setSecrtyUserRoleMapList(List<SecrtyUserRoleMap> secrtyUserRoleMapList) {
		this.secrtyUserRoleMapList = secrtyUserRoleMapList;
		if (this.secrtyUserRoleMapList != null && !secrtyUserRoleMapList.isEmpty()) {
			numVersionsExist = secrtyUserRoleMapList.size();
		}
	}

	private void updateVersionCount() {
		numVersionsExist = secrtyUserRoleMapList == null ? 0 : secrtyUserRoleMapList.size();
	}

	public int getNumVersionsExist() {
		updateVersionCount();
		return numVersionsExist;
	}

	public List<SecrtyUserRoleMap> getSecrtyUserRoleMaps() {
		return Collections.unmodifiableList(secrtyUserRoleMapList);
	}
 
	@Override
	public int hashCode() {
		int hash = 0;
		hash += userPartyID;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		SecrtyUser other = (SecrtyUser) object;
		if (this.userPartyID != other.userPartyID) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.fico.ps.df.ruleentities.SecrtyUser[ userPartyID =" + userPartyID + "version =" + version + "rowStatusCD =" + rowStatusCD + " userName =" + userName+"]";
	}
}
