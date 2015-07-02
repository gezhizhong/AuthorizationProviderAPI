package com.function.api;

public interface AuthorizationBackend {
	
	public String[] getPrincipalRoles(String principalName);

}
