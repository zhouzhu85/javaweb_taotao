package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

public interface ItemService {
	
	TbItem getItemById(long itemId);
	
	EasyUIDataGridResult getItemList(int page,int rows);
	
	
	TaotaoResult addItem(TbItem item,String desc);
	
	TbItemDesc getItemDescById(long itemId);
	
	TbItemParamItem getItemParamItemById(long id);
	
	EasyUIDataGridResult getItemParamItemList(int page,int rows);
	
	void instock(String itemIds);
	
	void reshelf(String itemIds);
	
	void delete(String itemIds);
}
