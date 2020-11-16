package com.glintt.imageconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glintt.imageconverter.dto.ImageEntityDTO;
import com.glintt.imageconverter.dto.ResultMessageDTO;
import com.glintt.imageconverter.servicesimplements.ImagerConvertorImpl;

@RestController
public class ImageUploadRestController {

	@Autowired
	ImagerConvertorImpl imageConversor;

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@PostMapping(value = "/converteImage", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResultMessageDTO> processImage(@RequestBody(required = true) ImageEntityDTO img) {

		final ResultMessageDTO result = imageConversor.converteImage(img);

		final String successCode = "000";
		final String badRequestCode = "001";

		if (result.getCode() == successCode) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if (result.getCode() == badRequestCode) {
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
