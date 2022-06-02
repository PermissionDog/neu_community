package com.github.permissiondog.community.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import com.github.permissiondog.community.Constants;
import com.github.permissiondog.community.model.enumeration.Cycle;
import com.github.permissiondog.community.model.enumeration.Direction;
import com.github.permissiondog.community.model.enumeration.Gender;
import com.github.permissiondog.community.model.enumeration.Period;
import com.github.permissiondog.community.model.enumeration.Role;
import com.github.permissiondog.community.model.enumeration.RouteType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Gson 工具类
 * 主要对注册自定义类型进行了注册
 * 
 * @author PermissionDog
 *
 */
public class GsonUtil {
	public static final Gson gson = new GsonBuilder().setPrettyPrinting()
			.registerTypeHierarchyAdapter(LocalTime.class, new TypeAdapter<LocalTime>() {
				
				@Override
				public void write(JsonWriter out, LocalTime value) throws IOException {
					if (value == null) {
						out.nullValue();
					} else {
						out.value(value.format(Constants.TIME_FORMATTER));
					}
				}

				@Override
				public LocalTime read(JsonReader in) throws IOException {

					if (in.peek() == JsonToken.NULL) {
						in.nextNull();
						return null;
					}
					return LocalTime.parse(in.nextString(), Constants.TIME_FORMATTER);
					
				}
			}).registerTypeHierarchyAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {
				
				@Override
				public void write(JsonWriter out, LocalDate value) throws IOException {
					out.value(value.format(Constants.DATE_FORMATTER));
				}

				@Override
				public LocalDate read(JsonReader in) throws IOException {
					return LocalDate.parse(in.nextString(), Constants.DATE_FORMATTER);
				}

			}).registerTypeHierarchyAdapter(Gender.class, new TypeAdapter<Gender>() {

				@Override
				public void write(JsonWriter out, Gender value) throws IOException {
					if (Gender.MALE.equals(value)) {
						out.value("male");
					} else {
						out.value("female");
					}
				}

				@Override
				public Gender read(JsonReader in) throws IOException {
					if ("male".equals(in.nextString())) {
						return Gender.MALE;
					} else {
						return Gender.FEMALE;
					}
				}
			}).registerTypeHierarchyAdapter(Role.class, new TypeAdapter<Role>() {

				@Override
				public void write(JsonWriter out, Role value) throws IOException {
					switch (value) {
					case ADMINISTRATOR:
						out.value("administrator");
						break;
					case HOUSEKEEPER:
						out.value("housekeeper");
						break;
					default:
						out.value("logistics_manager");
					}
				}

				@Override
				public Role read(JsonReader in) throws IOException {
					switch (in.nextString()) {
					case "administrator":
						return Role.ADMINISTRATOR;
					case "housekeeper":
						return Role.HOUSEKEEPER;
					default:
						return Role.LOGISTICS_MANAGER;
					}
				}
			})
			.registerTypeHierarchyAdapter(Cycle.class, new TypeAdapter<Cycle>() {

				@Override
				public void write(JsonWriter out, Cycle value) throws IOException {
					switch (value) {
					case EVERY_DAY:
						out.value("every_day");
						break;
					case MONDAY:
						out.value("monday");
						break;
					case TUESDAY:
						out.value("tuesday");
						break;
					case WEDNESDAY:
						out.value("wednesday");
						break;
					case THURSDAY:
						out.value("thursday");
						break;
					case FRIDAY:
						out.value("friday");
						break;
					case SATURDAY:
						out.value("saturday");
						break;
					default:
						out.value("sunday");
					}
				}

				@Override
				public Cycle read(JsonReader in) throws IOException {
					switch (in.nextString()) {
						case "every_day":
							return Cycle.EVERY_DAY;
						case "monday":
							return Cycle.MONDAY;
						case "tuesday":
							return Cycle.TUESDAY;
						case "wednesday":
							return Cycle.WEDNESDAY;
						case "thursday":
							return Cycle.THURSDAY;
						case "friday":
							return Cycle.FRIDAY;
						case "saturday":
							return Cycle.SATURDAY;
						default:
							return Cycle.SUNDAY;
					}
				}
				
			}).registerTypeHierarchyAdapter(Period.class, new TypeAdapter<Period>() {

				@Override
				public void write(JsonWriter out, Period value) throws IOException {
					switch (value) {
						case MORNING:
							out.value("morning");
							break;
						default:
							out.value("afternoon");
					}
				}

				@Override
				public Period read(JsonReader in) throws IOException {
					switch (in.nextString()) {
						case "morning":
							return Period.MORNING;
						default:
							return Period.AFTERNOON;
					}
				}
				
			}).registerTypeHierarchyAdapter(RouteType.class, new TypeAdapter<RouteType>() {

				@Override
				public void write(JsonWriter out, RouteType value) throws IOException {
					switch (value) {
						case IN:
							out.value("in");
							break;
						default:
							out.value("out");
					}
				}

				@Override
				public RouteType read(JsonReader in) throws IOException {
					switch (in.nextString()) {
						case "in":
							return RouteType.IN;
						default:
							return RouteType.OUT;
					}
				}
				
			}).registerTypeHierarchyAdapter(Direction.class, new TypeAdapter<Direction>() {

				@Override
				public void write(JsonWriter out, Direction value) throws IOException {
					switch (value) {
						case UP:
							out.value("up");
							break;
						default:
							out.value("down");
					}
				}

				@Override
				public Direction read(JsonReader in) throws IOException {
					switch (in.nextString()) {
						case "up":
							return Direction.UP;
						default:
							return Direction.DOWN;
					}
				}
				
			}).create();

}
