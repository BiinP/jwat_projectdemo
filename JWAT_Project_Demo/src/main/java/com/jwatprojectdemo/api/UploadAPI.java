package com.jwatprojectdemo.api;

import java.io.File;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jwatprojectdemo.service.UploadService;

@RestController
@RequestMapping("/api/upload")
public class UploadAPI {
	@Autowired UploadService upload;
	@PostMapping("/{folder}")
	public JsonNode uploadImg(@PathVariable("folder") String folder, @PathParam("file") MultipartFile file) {
		File fileUpload = upload.save(file, folder);
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("filename", fileUpload.getName());
		node.put("size", fileUpload.length());
		return node;
	}
}
