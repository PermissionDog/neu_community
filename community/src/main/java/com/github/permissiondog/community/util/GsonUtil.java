package com.github.permissiondog.community.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.github.permissiondog.community.model.Gender;
import com.github.permissiondog.community.model.Role;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class GsonUtil {
	public static final Gson gson;
	static {
		gson = new GsonBuilder()
				.registerTypeAdapter(LocalTime.class, new TypeAdapter<LocalTime>() {

					@Override
					public void write(JsonWriter out, LocalTime value) throws IOException {
						// TODO Auto-generated method stub
						
					}

					@Override
					public LocalTime read(JsonReader in) throws IOException {
						// TODO Auto-generated method stub
	
						return null;
					}
				})
				.registerTypeAdapter(LocalDate.class, new TypeAdapter<LocalDate>() {

					@Override
					public void write(JsonWriter out, LocalDate value) throws IOException {
						// TODO Auto-generated method stub
						
					}

					@Override
					public LocalDate read(JsonReader in) throws IOException {
						return LocalDate.parse(in.nextString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					}
					
				})
				.registerTypeAdapter(Gender.class, new TypeAdapter<Gender>() {

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
				})
				.registerTypeAdapter(Role.class, new TypeAdapter<Role>() {

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
				.create();
	}
}
