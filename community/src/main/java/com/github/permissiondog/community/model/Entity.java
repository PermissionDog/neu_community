package com.github.permissiondog.community.model;

public abstract class Entity implements Identifiable {

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return this.getClass().equals(obj.getClass()) && ((Identifiable) obj).getId() == getId();
	}

}
