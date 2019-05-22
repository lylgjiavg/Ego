package club.ego.manage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import club.ego.manage.service.PictureManageService;

@Controller
public class PictureController {
	
	@Autowired
	private PictureManageService pictureManageServiceImpl;
	
	@RequestMapping("pic/upload")
	@ResponseBody
	public Map<String, Object> upload(MultipartFile uploadFile) {
		
		return pictureManageServiceImpl.uploadPicture(uploadFile);
	}
	
}
