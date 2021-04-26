package tn.esprit.spring.services;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import tn.esprit.spring.entity.Photo;
import tn.esprit.spring.repository.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService{
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Override
	public Photo getPhoto(String id) {
		return photoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Photo With ID: " + id));
		 
	}

	@Override
	public Photo addPhoto(MultipartFile photo) throws IOException {
		 String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
	        String fileType = photo.getContentType();
	        byte[] data = photo.getBytes();
	        Photo pht = new Photo(null, fileName, fileType, data);
	        return photoRepository.save(pht);
	}

}
