package com.api.test;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ExternalSecurityAPI.constructAuthorizationBackend;
import ExternalSecurityAPI.constructPrincipalDirectory;

import com.function.api.AuthorizationBackend;
import com.function.api.PrincipalDirectory;
import com.function.api.impl.AuthorizationProviderImpl;
import com.function.bean.Resources;

public class AuthorizationProviderImplTest {

	AuthorizationProviderImpl ap;
	Resources resources;
	constructPrincipalDirectory constructPrincipalDirectory;
	constructAuthorizationBackend constructAuthorizationBackend;
	
	@Before
	public void setUp(){
		
		ap = new AuthorizationProviderImpl();
		
		PrincipalDirectory principalDirectory = mock(PrincipalDirectory.class);
		when(principalDirectory.getPrincipalGroups("johndoe")).thenReturn(new String[]{"G1","G2","G4","G5"});
		when(principalDirectory.getPrincipalGroups("G1")).thenReturn(new String[]{});
		when(principalDirectory.getPrincipalGroups("G2")).thenReturn(new String[]{});
		when(principalDirectory.getPrincipalGroups("G4")).thenReturn(new String[]{});
		when(principalDirectory.getPrincipalGroups("G5")).thenReturn(new String[]{"G50"});
		when(principalDirectory.getPrincipalGroups("G50")).thenReturn(new String[]{});
		constructPrincipalDirectory = new constructPrincipalDirectory();
		constructPrincipalDirectory.setPrincipalDirectory(principalDirectory);
		
		
		AuthorizationBackend authorizationBackend = mock(AuthorizationBackend.class);
		when(authorizationBackend.getPrincipalRoles("johndoe")).thenReturn(new String[]{"RoleA"});
		when(authorizationBackend.getPrincipalRoles("G1")).thenReturn(new String[]{"RoleB"});
		when(authorizationBackend.getPrincipalRoles("G50")).thenReturn(new String[]{"RoleA", "RoleC"});
		constructAuthorizationBackend = new constructAuthorizationBackend();
		constructAuthorizationBackend.setAuthorizationBackend(authorizationBackend);

	}
	
	@Test
	public void testConvertSetToArrayWithNULL() {
		
		Set<String> set = new HashSet<String>();

		assertArrayEquals(new String[]{}, ap.convertSetToArray(set));
	}
	
	@Test
	public void testConvertSetToArray() {
		
		Set<String> set = new HashSet<String>();
		set.add("A");
		set.add("A");
		set.add("B");
		
		assertArrayEquals(new String[]{"A", "B"}, ap.convertSetToArray(set));
	}
	
	@Test
	public void testIPrincipalDirectory() {
		
		assertArrayEquals(new String[]{"G1","G2","G4","G5"}, constructPrincipalDirectory.getPrincipalGroups("johndoe"));
	}
	
	@Test
	public void testIAuthorizationBackend() {
		
		assertArrayEquals(new String[]{"RoleA"}, constructAuthorizationBackend.getPrincipalRoles("johndoe"));
	}
	
//	@Test
//	public void testGetUserRoles() {
//		fail("Not yet implemented");
//	}
//	
	@Test
	public void testFindAllGroupsByUser() {
		
		resources = new Resources();
		ap.findAllGroupsByUser("johndoe", resources);

		assertArrayEquals(new String[]{"G1", "G2", "G4", "G5", "G50"}, ap.convertSetToArray(resources.getSynGroups()));
	}

	@Test
	public void testFindAllRolesByGroups() {
		
		resources = new Resources();
		ap.findAllGroupsByUser("johndoe", resources);
		ap.findAllRolesByGroups(resources);

		assertArrayEquals(new String[]{"RoleA", "RoleB", "RoleC"}, ap.convertSetToArray(resources.getSynRoles()));
		
	}
	
}
