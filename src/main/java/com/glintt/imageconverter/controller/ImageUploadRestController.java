package com.glintt.imageconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glintt.imageconverter.dto.ImageEntityDTO;
import com.glintt.imageconverter.servicesimplements.ImagerConvertorImpl;

@RestController
public class ImageUploadRestController {

	@Autowired
	ImagerConvertorImpl imageConversor;

	@PostMapping(value = "/converteImage", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> processImage(@RequestBody(required = true) ImageEntityDTO img) {

		return imageConversor.converteImage(img);
	}
}
