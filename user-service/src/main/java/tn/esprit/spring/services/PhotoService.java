package tn.esprit.spring.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import tn.esprit.spring.entity.Photo;

public interface PhotoService {
	
	Photo getPhoto(String id);
	Photo addPhoto(MultipartFile photo) throws IOException;

}
