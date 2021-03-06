package com.alecgorge.minecraft.jsonapi.permissions;

import java.util.ArrayList;
import java.util.List;

import com.alecgorge.minecraft.jsonapi.config.ConfigObject;

public class JSONAPIUser extends ConfigObject {
	public String username;
	public String password;
	public List<JSONAPIGroup> groups;
	
	public JSONAPIUser(String username, String password, List<String> groups) {
		this.username = username;
		this.password = password;
		
		this.groups = new ArrayList<JSONAPIGroup>();
		for(String s : groups) {
			this.groups.add(new JSONAPIGroup(s));
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public List<JSONAPIGroup> getGroups() {
		return groups;
	}
	
	public boolean canUseStream(String streamName) {
		for(JSONAPIGroup g : getGroups()) {
			if(g.canUseStream(streamName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean canUseMethod(String methodName) {
		if(methodName.startsWith("jsonapi.")) {
			return true;
		}
		for(JSONAPIGroup g : getGroups()) {
			if(g.canUseMethod(methodName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasPermission(String permission) {
		for(JSONAPIGroup g : getGroups()) {
			if(g.hasPermission(permission)) {
				return true;
			}
		}
		return false;
	}
}
