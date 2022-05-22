package com.github.permissiondog.community.util;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;

public class FileUtil {
	/**
	 * ��ȡ�ļ�
	 * 
	 * @param fileName �ļ���
	 * @return �����ı��ַ���
	 */
	public static String readFile(String fileName) {
		try {
			return new String(Files.readAllBytes(Paths.get(fileName)), Charset.forName("utf-8"));
		} catch (IOException e) {
			System.out.println("��ȡ " + fileName + " ʱ����");
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	
	/**
	 * д���ļ�
	 * 
	 * @param fileName �ļ���
	 * @param fileData Ҫд����ı�����
	 */
	public static void writeFile(String fileName, String fileData) {
		try {
			Files.writeString(Paths.get(fileName), fileData);
		} catch (IOException e) {
			System.out.println("д�� " + fileName + " ʱ����, ����");
			System.out.println(fileData);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void writeResource(String resource, String fileName) {
		URL url = FileUtil.class.getResource(resource);
		
		try {
			/*
			 * �������ֱ��ת path �ᱨ java.nio.file.FileSystemNotFoundException
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
