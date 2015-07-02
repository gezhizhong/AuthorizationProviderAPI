package ExternalSecurityAPI;

import com.function.api.PrincipalDirectory;

public class constructPrincipalDirectory implements PrincipalDirectory{

	//Use to test with JUnit
	//PrincipalDirectory PrincipalDirectory;

	public String[] getPrincipalGroups(String principalName) {

		 //Use to test with main function
		 String[] out = null;
		 if(principalName.equals("johndoe")){
		 out = new String[]{"G1", "G2", "G4", "G5"};
		 }else if(principalName.equals("janedoe")){
		 out = new String[]{"G1", "G2"};
		 }else if(principalName.equals("G2")){
		 out = new String[]{"G20", "G21", "G22", "G23", "G24", "G25", "G26",
		 "G27", "G28", "G29"};
		 }else if(principalName.equals("G5")){
		 out = new String[]{"G50"};
		 }
		
		 return out;
		//Use to test with JUnit
		//return PrincipalDirectory.getPrincipalGroups(principalName);
	}

	//Use to test with JUnit
//	public PrincipalDirectory getPrincipalDirectory() {
//		return PrincipalDirectory;
//	}
//
//	public void setPrincipalDirectory(PrincipalDirectory principalDirectory) {
//		PrincipalDirectory = principalDirectory;
//	}

}
