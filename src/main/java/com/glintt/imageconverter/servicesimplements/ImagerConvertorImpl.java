package com.glintt.imageconverter.servicesimplements;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import com.glintt.imageconverter.dto.ImageEntityDTO;
import com.glintt.imageconverter.dto.ResultMessageDTO;
import com.glintt.imageconverter.services.ImagerConvertorService;
import com.glintt.imageconverter.typemessages.TypeMessage;

@Service
public class ImagerConvertorImpl implements ImagerConvertorService {

	/**
	 * Convert the imageEntity received into jpg format.
	 * 
	 * @param ImageEntityDTO
	 * @return ImageEntity
	 */
	public ResultMessageDTO converteImage(final ImageEntityDTO img) {

		final ResultMessageDTO result = new ResultMessageDTO();

		try {
			final ByteArrayInputStream bais = new ByteArrayInputStream(img.getImageToConvert());
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ImageIO.write(ImageIO.read(bais), "jpg", bos);

			byte[] data = bos.toByteArray();

			final String encodedfile = Base64.encodeBase64String(data);

			result.setCode(TypeMessage.SUCCESS.getCode());
			result.setMessage(encodedfile);

		} catch (IOException e) {
			result.setCode(TypeMessage.INTERNAL_SERVER_ERROR.getCode());
			result.setMessage(TypeMessage.INTERNAL_SERVER_ERROR.name());
		} catch (IllegalArgumentException e) {
			result.setCode(TypeMessage.BAD_REQUEST.getCode());
			result.setMessage(TypeMessage.BAD_REQUEST.name());
		}

		return result;
	}

}