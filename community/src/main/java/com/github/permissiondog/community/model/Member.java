package com.github.permissiondog.community.model;

import java.time.LocalDate;

import com.github.permissiondog.community.model.enumeration.Gender;

public class Member implements Identifiable {
	private int id;
	private String name;
	private Gender gender;
	private LocalDate birthday;
	private String phone;
	private int houseKeeperID;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getHouseKeeperID() {
		return houseKeeperID;
	}
	public void setHouseKeeperID(int houseKeeperID) {
		this.houseKeeperID = houseKeeperID;
	}
}
