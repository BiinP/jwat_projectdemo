package com.jwatprojectdemo.service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	//LƯU FILE VÀO THƯ MỤC "FOLDER
	File save(MultipartFile file, String folder);
	
	//XÓA FILE "NAME" Ở THƯ MỤC "FOLDER"
	void delete(String folder, String name);
	
	//LƯU DANH SÁCH NHIỀU FILE VÀO THƯ MỤC "FOLDER"
	List<File> save(MultipartFile[] files, String folder);
}
