package com.function.api.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import com.function.api.AuthorizationBackend;
import com.function.api.AuthorizationProvider;
import com.function.api.PrincipalDirectory;
import com.function.bean.Resources;

public class AuthorizationProviderImpl implements AuthorizationProvider{
	
	//Use to test with main function
	private static PrincipalDirectory PrincipalDirectory = new ExternalSecurityAPI.constructPrincipalDirectory();
	private static AuthorizationBackend AuthorizationBackend = new ExternalSecurityAPI.constructAuthorizationBackend();	

	//Use to test with JUnit
//	private static ExternalSecurityAPI.constructPrincipalDirectory PrincipalDirectory;
//	private static ExternalSecurityAPI.constructAuthorizationBackend AuthorizationBackend;
	
	public String[] getUserRoles(String userName){
		
		Resources resources = new Resources();

		//Find all groups
	    findAllGroupsByUser(userName, resources);
	    
		//Find all roles under groups
	    findAllRolesByGroups(resources);
   
	    //Convert set to array
		return convertSetToArray(resources.getSynRoles());
	}
	
	//Find all groups
	private void findAllGroupsByUser(String root, Resources resources){
		
		String[] leafGroups = PrincipalDirectory.getPrincipalGroups(root);
		Set<String> sysGroups = resources.getSynGroups();

		if(leafGroups != null){
			for(String newGroup : leafGroups){
				sysGroups.add(newGroup);	
				
				findAllGroupsByUser(newGroup, resources);
			}
			resources.setSynGroups(sysGroups);
		}
	}
	
	//Find all roles under groups
	private void findAllRolesByGroups(Resources resources){

		Iterator<String> it = resources.getSynGroups().iterator();
		Set<String> sysRoles = resources.getSynRoles();
		
	    while(it.hasNext()){
	    	String[] roles = AuthorizationBackend.getPrincipalRoles(it.next());

	    	if(roles != null){
	    		for(String newRole : roles){
	    			sysRoles.add(newRole);
				}
	    		resources.setSynRoles(sysRoles);
	    	}
	    }
	}
	
	//Convert set to array
	private String[] convertSetToArray(Set<String> set){
		
		String[] returnArray = new String[set.size()];
		
	    Iterator<String> itRole = set.iterator();
	    int counter = 0;
	    while(itRole.hasNext()){
	    	returnArray[counter] = itRole.next().toString();
	    	counter ++;
	    }
	    
	    return returnArray;
	}
	
	public static void main(String[] args) {
		
		AuthorizationProviderImpl ap = new AuthorizationProviderImpl();

		System.out.println(Arrays.toString(ap.getUserRoles("johndoe")));
		
	}

}
