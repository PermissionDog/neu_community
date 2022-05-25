package com.github.permissiondog.community;

public class Config {
	
	protected static Config config;
	
	public static Config getConfig() {
		return config;
	}
	
	private String passwordAlgorithm;

	public String getPasswordAlgorithm() {
		return passwordAlgorithm;
	}

	public void setPasswordAlgorithm(String passwordAlgorithm) {
		this.passwordAlgorithm = passwordAlgorithm;
	}
	
}
