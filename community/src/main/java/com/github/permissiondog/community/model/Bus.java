package com.github.permissiondog.community.model;

import java.time.LocalTime;
import java.util.List;

import com.github.permissiondog.community.model.enumeration.Cycle;
import com.github.permissiondog.community.model.enumeration.Direction;
import com.github.permissiondog.community.model.enumeration.Period;
import com.github.permissiondog.community.model.enumeration.RouteType;

public class Bus extends Entity {
	private int id;
	private String code;
	private String name;
	private RouteType type;
	private Direction direction;
	private Cycle cycle;
	private Period period;
	private LocalTime departureTime;
	private LocalTime expireTime;
	private String comment;
	private List<Integer> passengers;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RouteType getType() {
		return type;
	}
	public void setType(RouteType type) {
		this.type = type;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Cycle getCycle() {
		return cycle;
	}
	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	public LocalTime getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(LocalTime expireTime) {
		this.expireTime = expireTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<Integer> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Integer> passengers) {
		this.passengers = passengers;
	}
}
