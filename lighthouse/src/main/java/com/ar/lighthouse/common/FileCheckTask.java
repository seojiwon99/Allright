package com.ar.lighthouse.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;



@Controller
@Log4j2
public class FileCheckTask {
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@Autowired
	UploadFileChkMapper chkMapper;
	
	private String getFolderYesterDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", "/");
	}
	
	
	// 스케줄러
	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() throws Exception{

		List<ImgsVO> fileList = chkMapper.getImgsFiles();
		System.out.println(fileList);
		
		System.out.println("test" + fileList.stream().map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUploadName())));

		List<Path> fileListPaths = fileList.stream()
				.map(vo -> Paths.get("C:\\upload", vo.getUploadPath(), vo.getUploadName()))
				.collect(Collectors.toList());
		
		File targetDir = Paths.get(uploadPath, getFolderYesterDay()).toFile();
		System.out.println("target" + targetDir);
		
		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);
		if(removeFiles  != null) {
			for(File file : removeFiles) {
				file.delete();
			}
		}
	}
}
