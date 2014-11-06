package cn.edu.tongji.anliantest.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static File saveFile(String path, MultipartFile file) {
		File targetFile = new File(path, file.getOriginalFilename());
		if(!targetFile.exists()){
			targetFile.mkdirs();
		}
		
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return targetFile;
	}
}
