package com.glintt.imageconverter.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.glintt.imageconverter.entities.ImageEntity;

@Service
public class ImagerConvertorService {

	String encodedfile = "the base64 of image could not be generated!";

	String currentDirectory = System.getProperty("user.dir");
	String pathNameOfOriginalFile = currentDirectory + "\\imageReceived.jp2";
	String pathNameOfOriginalFileToDeliver = currentDirectory + "\\imageToDeliver.jpg";

	ImageEntity entity = new ImageEntity();

	public ResponseEntity<ImageEntity> converteImage(ImageEntity img) {

		try {

			// bytearray jp2
			byte[] imgBytes = Base64.decodeBase64(img.getImageString());

			//
			/*
			 * final ByteArrayOutputStream baos = new ByteArrayOutputStream(64000);
			 * ImageIO.write(image, "PNG", baos); final byte[] data = baos.toByteArray();
			 * 
			 * 
			 * 
			 * ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			 * ImageIO.write(image, "png", outputStream);
			 * 
			 */
			//

			File f = new File(pathNameOfOriginalFile);
			FileOutputStream osf = new FileOutputStream(f);

			osf.write(imgBytes);

			osf.flush();
			osf.close();

			File outputfile = new File(pathNameOfOriginalFileToDeliver);
			// image of jpg
			ImageIO.write(ImageIO.read(f), "jpg", outputfile);

			FileInputStream fileInputStreamReader = new FileInputStream(outputfile);
			byte[] bytes = new byte[(int) outputfile.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = Base64.encodeBase64String(bytes);

			entity = new ImageEntity(encodedfile);

			f.delete();
			fileInputStreamReader.close();
			outputfile.delete();

		} catch (FileNotFoundException e) {
			// este vai ter que sair assim que deixar de guardar o ficheiro
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(entity);
		} catch (IOException e) {
			entity = new ImageEntity(encodedfile);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(entity);
		} catch (IllegalStateException e) {
			entity = new ImageEntity(encodedfile);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
		} catch (IllegalArgumentException e) {
			entity = new ImageEntity(encodedfile);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
		}
		return new ResponseEntity<>(entity, HttpStatus.OK);

	}

}
