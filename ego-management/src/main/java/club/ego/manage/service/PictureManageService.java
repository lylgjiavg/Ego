package club.ego.manage.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * easyui 图片上传服务
 * @author 姜立成
 * @date:   2019年4月21日 下午2:48:11
 *
 */
public interface PictureManageService {
	
	/**
	 * 图片上传
	 * @param file
	 * @return
	 */
	Map<String, Object> uploadPicture(MultipartFile file);
	
}
