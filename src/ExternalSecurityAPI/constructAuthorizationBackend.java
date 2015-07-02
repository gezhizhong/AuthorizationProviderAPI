package ExternalSecurityAPI;

import com.function.api.AuthorizationBackend;

public class constructAuthorizationBackend implements AuthorizationBackend{

	//Use to test with JUnit
	//AuthorizationBackend AuthorizationBackend;

	public String[] getPrincipalRoles(String principalName) {

		 //Use to test with main function
		 String[] out = null;
		 if(principalName.equals("johndoe")){
		 out = new String[]{"RoleA"};
		 }else if(principalName.equals("G1")){
		 out = new String[]{"RoleB"};
		 }else if(principalName.equals("G50")){
		 out = new String[]{"RoleA", "RoleC"};
		 }
		
		 return out;
		//Use to test with JUnit
//		return AuthorizationBackend.getPrincipalRoles(principalName);
	}

	//Use to test with JUnit
//	public AuthorizationBackend getAuthorizationBackend() {
//		return AuthorizationBackend;
//	}
//
//	public void setAuthorizationBackend(
//			AuthorizationBackend authorizationBackend) {
//		AuthorizationBackend = authorizationBackend;
//	}

}
