package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.pojo.TbContentExample;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	TbContentCategoryMapper contentCategoryMapper;
	
	@Autowired
	TbContentMapper contentMapper;

	@Override
	public List<EasyUITreeNode> getContentCategroyList(long parentId) {
		//创建查询对象
		TbContentCategoryExample example=new TbContentCategoryExample();
		
		//得到设置条件对象，并设置参数
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		//执行查询，得到列表List<TbContentCategory>
		List<TbContentCategory> list=contentCategoryMapper.selectByExample(example);
		
		//把列表List<TbContentCategory>转换成List<EasyUITreeNode>
		List<EasyUITreeNode> resultList=new ArrayList<>();
		for(TbContentCategory tbContentCategory:list){
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult addContentCategroy(Long parentId, String name) {
		TbContentCategory tbContentCategory=new TbContentCategory();
		tbContentCategory.setParentId(parentId);
		tbContentCategory.setName(name);
		tbContentCategory.setStatus(1);
		tbContentCategory.setIsParent(false);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setCreated(new Date());
		tbContentCategory.setUpdated(new Date());
		contentCategoryMapper.insert(tbContentCategory);
		TbContentCategory parent=contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()){
			contentCategoryMapper.updateByPrimaryKey(parent);
		}
		return TaotaoResult.ok(tbContentCategory);
	}

	@Override
	public void editContentCategory(Long id, String name) {
		TbContentCategory record=new TbContentCategory();
		record.setId(id);
		record.setName(name);
		contentCategoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteContentCategory(Long id) {
		contentCategoryMapper.deleteByPrimaryKey(id);
	}

}
