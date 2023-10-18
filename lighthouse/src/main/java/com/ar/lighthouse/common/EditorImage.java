package com.ar.lighthouse.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EditorImage {
   
   @Value("${file.upload.path}")
   private String uploadPath;
   
   
   @PostMapping(value = "/image/upload")
   @ResponseBody
   public ModelAndView image(MultipartHttpServletRequest request, RedirectAttributes rttr)throws Exception{
            ModelAndView mav = new ModelAndView("jsonView");
            
            List<ImgsVO> list = new ArrayList<ImgsVO>();
			
            List<MultipartFile> uploadFile = request.getFiles("upload");
            for(int i = 0; i<uploadFile.size(); i++) {
            	ImgsVO imgVO = new ImgsVO();
            	
            	String originalName = uploadFile.get(i).getOriginalFilename();
            	imgVO.setImgName(originalName);
            	
            	String fileName = originalName.substring(originalName.lastIndexOf("//")+1);	
            	
            	String folderPath = makeFolder();
            	imgVO.setUploadPath(folderPath);
            	String uuid = UUID.randomUUID().toString();
            	imgVO.setUploadName(uuid);
            	String uploadFileName = folderPath + "/" + uuid + "_" + fileName;
            	String saveName = uploadPath + File.separator + uploadFileName;
            	Path savePath = Paths.get(saveName);
            	uploadFile.get(i).transferTo(savePath);
            	String save = uploadFileName.replace("\\","/");
            	mav.addObject("uploaded", true); // 업로드 완료
            	mav.addObject("url", "/upload/" +save); // 업로드 파일의 경로
            	
            	list.add(imgVO);
            }
            
            mav.addObject("list", list);
            return mav;
   }
   
   // 파일 업로드 처리
   private String getFolder() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();

      String str = sdf.format(date);

      return str.replace("-", File.separator);
   }
   
   private String makeFolder() {
      String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));   // 경로에서 사용하는 /는 인지 못함
      // LocalDate를 문자열로 포멧
      String folderPath = str;// <- 그래서 separator 사용
      File uploadPathFoler = new File(uploadPath, folderPath);
      // File newFile= new File(dir,"파일명");
      if (uploadPathFoler.exists() == false) {
         uploadPathFoler.mkdirs();
         // 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
         // mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
         // mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
      }
      return folderPath;
   }
   
   private String setImagePath(String uploadFileName) {
      return uploadFileName.replace(File.separator, "/");
   }
}
