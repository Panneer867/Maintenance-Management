package com.ingroinfo.mm.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ingroinfo.mm.entity.InwardItem;

public interface MaterialService {

	void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException;

	void saveInwardItem(InwardItem inwardItem);

	List<InwardItem> getInwardItemList();

}
