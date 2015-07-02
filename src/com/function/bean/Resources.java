package com.function.bean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Resources {

	private HashSet<String> hashSetGroup = new HashSet<String>();
	private Set<String> synGroups = Collections.synchronizedSet(hashSetGroup);

	private HashSet<String> hashSetRole = new HashSet<String>();
	private Set<String> synRoles = Collections.synchronizedSet(hashSetRole);

	public Set<String> getSynGroups() {
		return synGroups;
	}

	public void setSynGroups(Set<String> synGroups) {
		this.synGroups.addAll(synGroups);
	}

	public Set<String> getSynRoles() {
		return synRoles;
	}

	public void setSynRoles(Set<String> synRoles) {
		this.synRoles.addAll(synRoles);
	}

}
