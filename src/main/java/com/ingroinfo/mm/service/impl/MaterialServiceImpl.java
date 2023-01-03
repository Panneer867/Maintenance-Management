package com.ingroinfo.mm.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.mm.dao.InwardItemRepo;
import com.ingroinfo.mm.entity.InwardItem;
import com.ingroinfo.mm.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private InwardItemRepo inwardItemRepo;

	@Override
	public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}

	@Override
	public void saveInwardItem(InwardItem inwardItem) {
		inwardItemRepo.save(inwardItem);
	}

	@Override
	public List<InwardItem> getInwardItemList() {
		return inwardItemRepo.findAll();
	}

}
