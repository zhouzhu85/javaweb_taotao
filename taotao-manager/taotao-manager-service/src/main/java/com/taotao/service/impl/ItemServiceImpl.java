package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
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

}
