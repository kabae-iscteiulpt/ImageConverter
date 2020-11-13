package com.glintt.imageconverter.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.glintt.imageconverter.dto.ImageEntityDTO;

@Service
public interface ImagerConvertorService {

	ResponseEntity<?> converteImage(final ImageEntityDTO img);

}
