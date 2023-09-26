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
            // ckeditor는 이미지 업로드 후 이미지 표시하기 위해 uploaded 와 url을 json 형식으로 받아야 함
            // modelandview를 사용하여 json 형식으로 보내기위해 모델앤뷰 생성자 매개변수로 jsonView 라고 써줌
            // jsonView 라고 쓴다고 무조건 json 형식으로 가는건 아니고 @Configuration 어노테이션을 단 
            // WebConfig 파일에 MappingJackson2JsonView 객체를 리턴하는 jsonView 매서드를 만들어서 bean으로 등록해야 함 
            ModelAndView mav = new ModelAndView("jsonView");
            
            List<ImgsVO> list = new ArrayList<ImgsVO>();
			
			
            // ckeditor 에서 파일을 보낼 때 upload : [파일] 형식으로 해서 넘어오기 때문에 upload라는 키의 밸류를 받아서 uploadFile에 저장함
            List<MultipartFile> uploadFile = request.getFiles("upload");
            for(int i = 0; i<uploadFile.size(); i++) {
            	ImgsVO imgVO = new ImgsVO();
            	
            	// 파일의 오리지널 네임
            	String originalName = uploadFile.get(i).getOriginalFilename();
            	imgVO.setImgName(originalName);
            	
            	// 파일의 확장자
            	// String ext = originalFileName.substring(originalFileName.indexOf("."));
            	
            	String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
            	
            	
            	String folderPath = makeFolder();
            	imgVO.setUploadPath(folderPath);
            	String uuid = UUID.randomUUID().toString();
            	imgVO.setUploadName(uuid);
            	
            	String uploadFileName = folderPath + "/" + uuid + "_" + fileName;
            	
            	String saveName = uploadPath + File.separator + uploadFileName;
            	// 서버에 저장될 때 중복된 파일 이름인 경우를 방지하기 위해 UUID에 확장자를 붙여 새로운 파일 이름을 생성
            	Path savePath = Paths.get(saveName);
            	// String newFileName = UUID.randomUUID() + ext;
            	
            	// 이미지를 현재 경로와 연관된 파일에 저장하기 위해 현재 경로를 알아냄
            	//String realPath = request.getServletContext().getRealPath("/");
            	
            	// 현재경로/upload/파일명이 저장 경로
            	// String savePath = realPath + "upload/" + newFileName;
            	
            	// 브라우저에서 이미지 불러올 때 절대 경로로 불러오면 보안의 위험 있어 상대경로를 쓰거나 이미지 불러오는 jsp 또는 클래스 파일을 만들어 가져오는 식으로 우회해야 함
            	// 때문에 savePath와 별개로 상대 경로인 uploadPath 만들어줌
            	// String uploadPath1 = uploadPath + newFileName; 
            	
            	// 저장 경로로 파일 객체 생성
            	// File file = new File(savePath);
            	
            	// 파일 업로드
            	uploadFile.get(i).transferTo(savePath);
            	String save = uploadFileName.replace("\\","/");
            	// uploaded, url 값을 modelandview를 통해 보냄
            	// mav.setViewName("productContent");
            	mav.addObject("uploaded", true); // 업로드 완료
            	mav.addObject("url", "upload/" +save); // 업로드 파일의 경로
            	
            	list.add(imgVO);
            }
            
            System.out.println(list);
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
