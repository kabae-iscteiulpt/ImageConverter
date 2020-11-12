package com.glintt.imageconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glintt.imageconverter.Services.ImagerConvertorService;
import com.glintt.imageconverter.entities.ImageEntity;

@RestController
public class FileUploadRestController {

	@Autowired
	ImagerConvertorService imageConversor;

	@PostMapping(value = "/converteImage", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ImageEntity> processImage(@RequestBody ImageEntity img) {

		return imageConversor.converteImage(img);
	}
}
