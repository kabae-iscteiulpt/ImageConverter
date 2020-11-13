package com.glintt.imageconverter.servicesimplements;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.glintt.imageconverter.dto.ImageEntityDTO;
import com.glintt.imageconverter.dto.ResultMessageDTO;
import com.glintt.imageconverter.services.ImagerConvertorService;

public class ImagerConvertorImpl implements ImagerConvertorService {

	/**
	 * Convert the imageEntity received into jpeg format.
	 * 
	 * @param ImageEntityDTO
	 * @return ImageEntity
	 */
	public ResponseEntity<?> converteImage(final ImageEntityDTO img) {

		final String pathNameOfOriginalFileToDeliver = System.getProperty("user.dir") + "\\imageToDeliver.jpg";
		ResultMessageDTO result = new ResultMessageDTO();

		try {

			final ByteArrayInputStream bais = new ByteArrayInputStream(img.getImageString());
			final File outputfile = new File(pathNameOfOriginalFileToDeliver);

			ImageIO.write(ImageIO.read(bais), "jpg", outputfile);

			final FileInputStream fileInputStreamReader = new FileInputStream(outputfile);
			final byte[] bytes = new byte[(int) outputfile.length()];
			fileInputStreamReader.read(bytes);

			final String encodedfile = Base64.encodeBase64String(bytes);

			result.setCode("000");
			result.setMessage(encodedfile);

			fileInputStreamReader.close();
			outputfile.delete();

		} catch (FileNotFoundException e) {
			result.setCode("001");
			result.setMessage("NOT_FOUND");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		} catch (IOException e) {
			result.setCode("002");
			result.setMessage("INTERNAL_SERVER_ERROR");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		} catch (IllegalStateException e) {
			result.setCode("003");
			result.setMessage("BAD_REQUEST");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		} catch (IllegalArgumentException e) {
			result.setCode("004");
			result.setMessage("BAD_REQUEST");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
