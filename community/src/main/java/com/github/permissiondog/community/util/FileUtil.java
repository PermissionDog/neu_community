package com.github.permissiondog.community.util;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

public class FileUtil {
	/**
	 * 读取文件
	 * 
	 * @param fileName 文件名
	 * @return 读入文本字符串
	 */
	public static String readFile(String fileName) {
		try {
			return new String(Files.readAllBytes(Paths.get(fileName)), Charset.forName("utf-8"));
		} catch (IOException e) {
			System.out.println("读取 " + fileName + " 时出错");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	
	/**
	 * 写入文件
	 * 
	 * @param fileName 文件名
	 * @param fileData 要写入的文本数据
	 */
	public static void writeFile(String fileName, String fileData) {
		try {
			Files.writeString(Paths.get(fileName), fileData);
		} catch (IOException e) {
			System.out.println("写到 " + fileName + " 时出错, 数据");
			System.out.println(fileData);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void writeResource(String resource, String fileName) {
		URL url = FileUtil.class.getResource(resource);
		
		try {
			/*
			 * 这里如果直接转 path 会报 java.nio.file.FileSystemNotFoundException
			 * https://stackoverflow.com/a/22605905/14936035
			 */
			final Map<String, String> env = new HashMap<>();
			final String[] array = url.toURI().toString().split("!");
			final FileSystem fs = FileSystems.newFileSystem(URI.create(array[0]), env);
			final Path path = fs.getPath(array[1]);
			
			byte[] data = Files.readAllBytes(path);
			Files.write(Paths.get(fileName), data);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
