package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemService;

/**
 * 商品管理服务
 * @author zhouzhu
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	TbItemMapper itemMapper;
	
	@Autowired
	TbItemDescMapper itemDesMapper;
	
	@Autowired
	TbItemDescMapper tbItemDescMapper;
	
	@Autowired
	TbItemParamItemMapper tbItemParamItemMapper;
	
	@Autowired
	TbItemParamMapper tbItemParamMapper;

	@Override
	public TbItem getItemById(long itemId) {
		// TODO Auto-generated method stub
		TbItem item=itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example=new TbItemExample();
		List<TbItem> list=itemMapper.selectByExample(example);
		//取查询结果
		PageInfo<TbItem> pageInfo=new PageInfo<>(list);
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		//返回结果
		return result;
	}

	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		//生成商品ID
		long itemId=IDUtils.getItemId();
		//补全Item的属性
		item.setId(itemId);
		//商品状态：1-正常，2-下架，3-删除
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//插入数据
		itemMapper.insert(item);
		//商品描述表的pojo
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}

	@Override
	public TbItemDesc getItemDescById(long itemId) {
		//获取商品详情
		return tbItemDescMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public TbItemParamItem getItemParamItemById(long id) {
		//通过商品id获取商品规格
		return tbItemParamItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public EasyUIDataGridResult getItemParamItemList(int page, int rows) {
		// TODO Auto-generated method stub
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemParamItemExample example=new TbItemParamItemExample();
		List<TbItemParamItem> list=tbItemParamItemMapper.selectByExample(example);
		//取结果
		PageInfo<TbItemParamItem> pageinfo=new PageInfo<>(list);
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageinfo.getTotal());
		//返回结果
		return result;
	}

	@Override
	public void instock(String itemIds) {
		// TODO Auto-generated method stub
		TbItem item=new TbItem();
		item.setStatus(new Byte("2"));
		String[] ids=itemIds.split(",");
		List<Long> list=new ArrayList<Long>();
		for(int i=0;i<ids.length;i++){
			list.add(Long.parseLong(ids[i]));
		}
		TbItemExample example=new TbItemExample();
		Criteria c=example.createCriteria();
		c.andIdIn(list);
		itemMapper.updateByExampleSelective(item, example);
	}

	@Override
	public void reshelf(String itemIds) {
		// TODO Auto-generated method stub
		TbItem item=new TbItem();
		item.setStatus(new Byte("1"));
		String[] ids=itemIds.split(",");
		List<Long> list=new ArrayList<Long>();
		for(int i=0;i<ids.length;i++){
			list.add(Long.parseLong(ids[i]));
		}
		TbItemExample example=new TbItemExample();
		Criteria c=example.createCriteria();
		c.andIdIn(list);
		itemMapper.updateByExampleSelective(item, example);
	}

	@Override
	public void delete(String itemIds) {
		String[] ids=itemIds.split(",");
		List<Long> list=new ArrayList<Long>();
		for(int i=0;i<ids.length;i++){
			list.add(Long.parseLong(ids[i]));
		}
		TbItemExample example=new TbItemExample();
		Criteria c=example.createCriteria();
		c.andIdIn(list);
		itemMapper.deleteByExample(example);
	}
	
	


}
