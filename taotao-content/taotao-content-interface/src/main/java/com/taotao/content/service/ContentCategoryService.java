package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {
	//获取内容管理分类
	public List<EasyUITreeNode> getContentCategroyList(long parentId);
	//添加内容管理分类
	TaotaoResult addContentCategroy(Long parentId,String name);
	//编辑内容管理分类
	void editContentCategory(Long parentId,String name);
	//删除内容管理分类
	void deleteContentCategory(Long id);

}
