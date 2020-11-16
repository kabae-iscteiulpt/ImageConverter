package com.glintt.imageconverter.services;

import com.glintt.imageconverter.dto.ImageEntityDTO;
import com.glintt.imageconverter.dto.ResultMessageDTO;

public interface ImagerConvertorService {

	ResultMessageDTO converteImage(final ImageEntityDTO img);

}
