package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.utils.FastDFSClient;

/**
 * 图片上传
 * @author zhouzhu
 *
 */

@Controller
public class PictureController {
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String picUpload(MultipartFile uploadFile){
	
		try {
			String originalFilename=uploadFile.getOriginalFilename();
			String extName=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
			FastDFSClient fastDFSClient;
			fastDFSClient = new FastDFSClient("classpath:resource/client.conf");
			String url=fastDFSClient.uploadFile(uploadFile.getBytes(),extName);
			url=IMAGE_SERVER_URL+url;
			Map map=new HashMap<>();
			map.put("error",0);
			map.put("url",url);
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Map map=new HashMap<>();
			map.put("error",0);
			map.put("message","图片上传失败");
			return JsonUtils.objectToJson(map);
		}
		
	}
}
