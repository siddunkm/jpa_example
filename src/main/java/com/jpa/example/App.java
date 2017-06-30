package com.jpa.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.fico.ps.df.ruleentities.SecrtyAuthorization;
import com.fico.ps.df.ruleentities.SecrtyUser;
import com.fico.ps.df.ruleentities.SecrtyUserRole;
import com.fico.ps.df.ruleentities.SecrtyUserRoleMap;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		 //m1();
		//m2(false);
		System.out.println("Java");
		System.out.println("Git");
	}

	static void m1() {
		String userID = "siddu";
		SecrtyUser secrtyUser = new SecrtyUser(1 /* version */, "Active" /* rowStatusCD */, userID /* userName */,
				1 /* activeTenantCD */ );

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA_Oracle");

		EntityManager entitymanager = emfactory.createEntityManager();

		System.out.println(" inside storeNewUser SecrtyUser " + "string" + "" + userID);

		SecrtyUser res = null;
		try {
			res = (SecrtyUser) entitymanager.createNamedQuery("SecrtyUser.findByUserName")
					.setParameter("userName", secrtyUser.getUserName()).getSingleResult();
		} catch (NoResultException e) {
			System.out.println("User does not exist in the db");
		}

		System.out.println(" got SecrtyUser " + res);
		entitymanager.getTransaction().begin();
		if (res == null) {
			try {
				System.out.println(" inserting SecrtyUser %s" + secrtyUser.toString());
				entitymanager.persist(secrtyUser);
				entitymanager.flush();
				System.out.println(" SecrtyUser inserted %s" + secrtyUser.toString());
			} catch (Throwable t) {
				System.out.println("error storing SecrtyUser");
				return;
			}
		} else {
			System.out.println("SecrtyUser is already exists ");
		}
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	static void m2(boolean cr) {
		String userID = "siddu";
		SecrtyUser secrtyUser = new SecrtyUser(1 /* version */, "Active" /* rowStatusCD */, userID /* userName */,
				1 /* activeTenantCD */ );

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA_Oracle");

		EntityManager entitymanager = emfactory.createEntityManager();

		System.out.println(" updating user role mapping table for user % " + "string" + "" + userID);
		SecrtyUser res = null;
		try {
			res = (SecrtyUser) entitymanager.createNamedQuery("SecrtyUser.findByUserName")
					.setParameter("userName", secrtyUser.getUserName()).getSingleResult();
		} catch (NoResultException e) {
			System.out.println("error User does not exist in the db while merging SecrtyUserRoleMap");
			return;
		}
		System.out.println(" got SecrtyUser " + res);

		List<String> inList = new ArrayList<String>();
		//inList.add("RmaAdmin");
		inList.add("Author");

		String[] inArray = new String[2];
		inArray[0] = "Author";
		inArray[1] = "Author";

		List<String> roleslist = new ArrayList<String>(Arrays.asList(inArray));
				
		List<SecrtyUserRole> listSecrtyUserRole = entitymanager.createNamedQuery("SecrtyUserRole.findByRoleNames")
				.setParameter("roleNames", inList).getResultList();
		
		if(listSecrtyUserRole.isEmpty()){
			System.out.println("no roles list  %s" + listSecrtyUserRole);
			return;
		}
		System.out.println("got roles list  %s" + listSecrtyUserRole);

		List<SecrtyUserRoleMap> secrtyUserRoleMapList = new ArrayList<SecrtyUserRoleMap>();
		try {
			entitymanager.getTransaction().begin();
		for (SecrtyUserRole secrtyUserRole : listSecrtyUserRole) {
			System.out.println("user  %s" + secrtyUserRole.getRoleName());
			SecrtyUserRoleMap secrtyUserRoleMap = new SecrtyUserRoleMap(secrtyUserRole.getRoleID(),
					res.getUserPartyID());
			secrtyUserRoleMap.setTenantCD(res.getActiveTenantCD());
			secrtyUserRoleMap.setCreatedByUserID(res.getUserPartyID());
			secrtyUserRoleMap.setLastUpdatedByUserID(res.getUserPartyID());
			secrtyUserRoleMapList.add(secrtyUserRoleMap);	
		}
		res.setSecrtyUserRoleMapList(secrtyUserRoleMapList);

			System.out.println(" merging SecrtyUserRoleMap %s" + res);
			entitymanager.merge(res);
			entitymanager.flush();
			System.out.println(" completed merging SecrtyUserRoleMap %s" + res);
			boolean includeCSR = true;
		SecrtyAuthorization secrtyAuthorization = getSecrtyAuthorization(res.getUserPartyID(), inList, includeCSR);
		try {
			SecrtyAuthorization resAuth = (SecrtyAuthorization) entitymanager.createNamedQuery("SecrtyAuthorization.findByUserPermisssion")
					.setParameter("userPartyID", secrtyAuthorization.getUserPartyID())
					.setParameter("tenantCD", secrtyAuthorization.getTenantCD()).getSingleResult();
			System.out.println("SecrtyAuthorization is already exist");
			if(resAuth.getPermissionsetID() != secrtyAuthorization.getPermissionsetID()){
			 final int changes =
					 entitymanager.createNamedQuery("SecrtyAuthorization.UpdateByUserPermisssion")
				            .setParameter("permissionsetID", secrtyAuthorization.getPermissionsetID())
				            .setParameter("lastUpdatedDttm", new Date())
				            .setParameter("userPartyID" , secrtyAuthorization.getUserPartyID())
				            .executeUpdate();
		  System.out.println(changes + " rows changed");
		  }
		} catch (NoResultException e) {
			System.out.println("Inserting SecrtyAuthorization");
			entitymanager.persist(secrtyAuthorization);
		}
		
		} catch (Throwable t) {
			t.printStackTrace();
			System.out.println("error not able to merg SecrtyUserRoleMap");
			return;
		}
		
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}
	
	  private static SecrtyAuthorization getSecrtyAuthorization(long userPartyID, List<String> roleslist, boolean includeCSR){
		  	SecrtyAuthorization secrtyAuthorization = new SecrtyAuthorization();
			secrtyAuthorization.setUserPartyID(userPartyID);
			secrtyAuthorization.setVersion(1);
			secrtyAuthorization.setPermissionsetID((roleslist.size() == 1 && includeCSR) ? 2 : 1);
			secrtyAuthorization.setTenantCD(1);
			secrtyAuthorization.setLastUpdatedByUserID((int)secrtyAuthorization.getUserPartyID());
			secrtyAuthorization.setCreatedByUserID((int)secrtyAuthorization.getUserPartyID());
			secrtyAuthorization.setRowStatusCD("Active");
			secrtyAuthorization.setAuthorizationTypeStrg("random");
			secrtyAuthorization.setCreatedDttm(new Date());
			secrtyAuthorization.setLastUpdatedDttm(new Date());
			return secrtyAuthorization;
	  }
}
