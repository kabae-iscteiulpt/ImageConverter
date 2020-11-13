package com.glintt.imageconverter.services;

import org.springframework.http.ResponseEntity;

import com.glintt.imageconverter.dto.ImageEntityDTO;

public interface ImagerConvertorService {

	ResponseEntity<?> converteImage(final ImageEntityDTO img);

}
