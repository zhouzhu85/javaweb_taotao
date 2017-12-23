package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	@Autowired
	ContentCategoryService categoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EasyUITreeNode> list=categoryService.getContentCategroyList(parentId);
		return list;
	}
	
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult addContentCategory(Long parentId,String name){
		return categoryService.addContentCategroy(parentId, name);
	}
	@RequestMapping("/content/category/update")
	@ResponseBody
	public void editContentCategory(Long id,String name){
		 categoryService.editContentCategory(id, name);
	}
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public void deleteContentCategory(Long id){
		categoryService.deleteContentCategory(id);
	}
}
