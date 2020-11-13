package com.glintt.imageconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glintt.imageconverter.dto.ImageEntityDTO;
import com.glintt.imageconverter.services.ImagerConvertorService;

@RestController
public class ImageUploadRestController {

	@Autowired
	ImagerConvertorService imageConversor;

	@PostMapping(value = "/converteImage", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> processImage(@RequestBody ImageEntityDTO img) {

		return imageConversor.converteImage(img);
	}
}
