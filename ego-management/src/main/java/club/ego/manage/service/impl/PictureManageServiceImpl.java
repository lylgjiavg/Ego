package club.ego.manage.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import club.ego.commons.utils.FtpUtil;
import club.ego.commons.utils.IDUtils;
import club.ego.manage.service.PictureManageService;

public class PictureManageServiceImpl implements PictureManageService {

	@Value("${ftpclient.host}")
	private String host;
	@Value("${ftpclient.port}")
	private int port;
	@Value("${ftpclient.username}")
	private String username;
	@Value("${ftpclient.password}")
	private String password;
	@Value("${ftpclient.basepath}")
	private String basePath;
	@Value("${ftpclient.filepath}")
	private String filePath;

	@Override
	public Map<String, Object> uploadPicture(MultipartFile file) {
		Map<String, Object> map = new HashMap<String, Object>();

		String filename = IDUtils.genItemId()
				+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		try {
			FtpUtil.uploadFile(host, port, username, password, basePath, filePath, filename, file.getInputStream());
		} catch (IOException e) {
			map.put("error", 1);
			map.put("message", "流错误!");
		}
		map.put("error", 0);
		map.put("url", "http://" + host + "/" + filename);
		
		return map;
	}

}
