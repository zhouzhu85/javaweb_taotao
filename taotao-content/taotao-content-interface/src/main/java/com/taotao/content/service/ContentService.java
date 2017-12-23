package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	//获取内容
	EasyUIDataGridResult getContentList(Long parentId,int page,int rows);
	
	TaotaoResult addContent(TbContent content);
	
	TaotaoResult editContent(TbContent content);
	
	TaotaoResult deleteContent(String ids);
	
	List<TbContent> getContentByCid(long cid);
}
