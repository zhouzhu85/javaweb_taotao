package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

/**
 * 商品管理Controller
 * @author zhouzhu
 *
 */

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId){
		return itemService.getItemById(itemId);
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows){
	 	return itemService.getItemList(page, rows);
	}
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamItemList(Integer page,Integer rows){
		return itemService.getItemParamItemList(page, rows);
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult addItem(TbItem item,String desc){
		return itemService.addItem(item, desc);
	}
	
	@RequestMapping("/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public TbItemDesc getItemDesc(@PathVariable long itemId){
		return itemService.getItemDescById(itemId);
	}
	@RequestMapping("/rest/item/param/item/query/{id}")
	@ResponseBody
	public TbItemParamItem getItemParamIte(@PathVariable long id){
		return itemService.getItemParamItemById(id);
	}
	@RequestMapping(value="/rest/item/instock",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult instock(String ids){
		 itemService.instock(ids);
		 return TaotaoResult.build(200,"");
	}
	@RequestMapping(value="/rest/item/reshelf",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult reshelf(String ids){
		itemService.reshelf(ids);
		return TaotaoResult.build(200,"");
	}
	@RequestMapping(value="/rest/item/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult delete(String ids){
		itemService.delete(ids);
		return TaotaoResult.build(200,"");
	}
}
