package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.jedis.JedisClient;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	TbContentMapper contentMapper;
	
	@Autowired
	JedisClient jedisclient;
	
	@Value("${INDEX_CONTENT}")
	private String INDEX_CONTENT;
	
	@Override
	public EasyUIDataGridResult getContentList(Long parentId,int page, int rows) {
		PageHelper.startPage(page, rows);
		TbContentExample example=new TbContentExample();
		Criteria c=example.createCriteria();
		c.andCategoryIdEqualTo(parentId);
		List<TbContent> list=contentMapper.selectByExampleWithBLOBs(example);
		//取查询结果
		PageInfo<TbContent> pageInfo=new PageInfo<>(list);
		EasyUIDataGridResult result=new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		//返回结果
		return result;
	}

	@Override
	public TaotaoResult addContent(TbContent content) {
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		//同步缓存
		//删除对应的缓存信息
		jedisclient.hdel(INDEX_CONTENT, content.getCategoryId().toString());
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult editContent(TbContent content) {
		content.setUpdated(new Date());
		contentMapper.updateByPrimaryKeySelective(content);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteContent(String ids) {
		String[] idStr=ids.split(",");
		List<Long> idsList=new ArrayList<Long>();
		for(int i=0;i<idStr.length;i++){
			idsList.add(Long.parseLong(idStr[i]));
		}
		TbContentExample example=new TbContentExample();
		Criteria c=example.createCriteria();
		c.andIdIn(idsList);
		contentMapper.deleteByExample(example);
		return TaotaoResult.ok();
	}

	@Override
	public List<TbContent> getContentByCid(long cid) {
		//查询缓存
		try {
			String json=jedisclient.hget(INDEX_CONTENT,cid+"");
			if(StringUtils.isNotBlank(json)){
				List<TbContent> lists=JsonUtils.jsonToList(json,TbContent.class);
				return lists;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbContentExample example=new TbContentExample();
		Criteria c=example.createCriteria();
		c.andCategoryIdEqualTo(cid);
		List<TbContent> list=contentMapper.selectByExample(example);
		//添加结果到缓存
		try {
			jedisclient.hset(INDEX_CONTENT,cid+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
