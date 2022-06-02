package com.github.permissiondog.community.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.github.permissiondog.community.model.enumeration.Cycle;
import com.github.permissiondog.community.model.enumeration.Direction;
import com.github.permissiondog.community.model.enumeration.Gender;
import com.github.permissiondog.community.model.enumeration.Period;
import com.github.permissiondog.community.model.enumeration.Role;
import com.github.permissiondog.community.model.enumeration.RouteType;
import com.google.gson.Gson;

/**
 * Gson 测试
 * 
 * @author PermissionDog
 *
 */
public class GsonUtilTest {
	@Test
	@DisplayName("测试时间")
	void testDateTime() {
		Gson gson = GsonUtil.gson;
		
		LocalDate nowDate = LocalDate.now();
		LocalDate tDate = gson.fromJson(gson.toJson(nowDate), LocalDate.class);
		assertEquals(tDate.getYear(), nowDate.getYear());
		assertEquals(tDate.getMonth(), nowDate.getMonth());
		assertEquals(tDate.getDayOfMonth(), nowDate.getDayOfMonth());
		LocalTime nowTime = LocalTime.now();
		LocalTime tTime = gson.fromJson(gson.toJson(nowTime), LocalTime.class);
		assertEquals(tTime.getHour(), nowTime.getHour());
		assertEquals(tTime.getMinute(), nowTime.getMinute());
	}
	
	@Test
	@DisplayName("测试Cycle")
	void testCycle() {
		Gson gson = GsonUtil.gson;
		
		assertEquals(gson.fromJson(gson.toJson(Cycle.EVERY_DAY), Cycle.class), Cycle.EVERY_DAY);
		assertEquals(gson.fromJson(gson.toJson(Cycle.MONDAY), Cycle.class), Cycle.MONDAY);
		assertEquals(gson.fromJson(gson.toJson(Cycle.TUESDAY), Cycle.class), Cycle.TUESDAY);
		assertEquals(gson.fromJson(gson.toJson(Cycle.WEDNESDAY), Cycle.class), Cycle.WEDNESDAY);
		assertEquals(gson.fromJson(gson.toJson(Cycle.THURSDAY), Cycle.class), Cycle.THURSDAY);
		assertEquals(gson.fromJson(gson.toJson(Cycle.FRIDAY), Cycle.class), Cycle.FRIDAY);
		assertEquals(gson.fromJson(gson.toJson(Cycle.SATURDAY), Cycle.class), Cycle.SATURDAY);
		assertEquals(gson.fromJson(gson.toJson(Cycle.SUNDAY), Cycle.class), Cycle.SUNDAY);
	}

	@Test
	@DisplayName("测试Period")
	void testPeriod() {
		Gson gson = GsonUtil.gson;
		
		assertEquals(gson.fromJson(gson.toJson(Period.MORNING), Period.class), Period.MORNING);
		assertEquals(gson.fromJson(gson.toJson(Period.AFTERNOON), Period.class), Period.AFTERNOON);
	}

	@Test
	@DisplayName("测试RouteType")
	void testRouteType() {
		Gson gson = GsonUtil.gson;
		
		assertEquals(gson.fromJson(gson.toJson(RouteType.IN), RouteType.class), RouteType.IN);
		assertEquals(gson.fromJson(gson.toJson(RouteType.OUT), RouteType.class), RouteType.OUT);
	}

	@Test
	@DisplayName("测试Direction")
	void testDirection() {
		Gson gson = GsonUtil.gson;
		
		assertEquals(gson.fromJson(gson.toJson(Direction.UP), Direction.class), Direction.UP);
		assertEquals(gson.fromJson(gson.toJson(Direction.DOWN), Direction.class), Direction.DOWN);
	}

	@Test
	@DisplayName("测试Gender")
	void testGender() {
		Gson gson = GsonUtil.gson;
		
		assertEquals(gson.fromJson(gson.toJson(Gender.FEMALE), Gender.class), Gender.FEMALE);
		assertEquals(gson.fromJson(gson.toJson(Gender.MALE), Gender.class), Gender.MALE);
	}

	@Test
	@DisplayName("测试Role")
	void testRole() {
		Gson gson = GsonUtil.gson;
		
		assertEquals(gson.fromJson(gson.toJson(Role.ADMINISTRATOR), Role.class), Role.ADMINISTRATOR);
		assertEquals(gson.fromJson(gson.toJson(Role.HOUSEKEEPER), Role.class), Role.HOUSEKEEPER);
		assertEquals(gson.fromJson(gson.toJson(Role.LOGISTICS_MANAGER), Role.class), Role.LOGISTICS_MANAGER);
	}
	
}
