package cn.edu.tongji.anliantest.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

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
	
	public static void deleteFile(String path, String fileName) {
		File targetFile = new File(path, fileName);
		if(targetFile.exists()) {
			targetFile.delete();
		}
	}
	
	public static void deleteDirectory(String path) {
		File targetDirect = new File(path);
		if(targetDirect.exists()) {
			targetDirect.delete();
		}
	}
	
	public static void downloadFile(File file, HttpServletResponse response) throws Exception{
		String fileName = URLEncoder.encode(file.getName(), "UTF-8");
		
		response.setHeader("Content-disposition", "attachment; filename=" + fileName);
		response.setContentType("application/msword");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(response.getOutputStream());

			byte[] buff = new byte[2048];
			int bytesRead;

			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}

		} catch (final IOException e) {
			System.out.println("IOException." + e);
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
}
