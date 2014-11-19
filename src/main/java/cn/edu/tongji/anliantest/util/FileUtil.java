package cn.edu.tongji.anliantest.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static File saveFile(String path, MultipartFile file, String fileName) {
		File targetFile = new File(path, fileName);
		if(!targetFile.exists()){
			targetFile.mkdirs();
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			targetFile.delete();
			targetFile = new File(path, fileName);
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return targetFile;
	}
}
