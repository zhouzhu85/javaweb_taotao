package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;

@Controller
public class ContentController {

	@Autowired
	ContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(Long categoryId,int page,int rows){
		return contentService.getContentList(categoryId,page, rows);
	}
	
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content){
		return contentService.addContent(content);
	}
	
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public TaotaoResult editContent(TbContent content){
		return contentService.editContent(content);
	}
	
	@RequestMapping("/content/delete")
	@ResponseBody
	public TaotaoResult deleteContent(String ids){
		return contentService.deleteContent(ids);
	}
	
}
