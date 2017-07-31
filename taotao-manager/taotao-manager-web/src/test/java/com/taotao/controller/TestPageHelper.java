package com.taotao.controller;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

/**
 * 
 * @Description:TODO 测试 Mybatis 分页插件 PageHelper
 * @author: <a href="http://doctordeng.vip/">DoctorDeng</a> 
 * @date:   2017年7月31日 下午9:44:21   
 * @version:1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext*.xml")
@Transactional
public class TestPageHelper {
	private Logger log = LoggerFactory.getLogger(TestPageHelper.class);
	@Resource
	private TbItemMapper tbItemMapper;
	@Test
	public void testQuery() {
		PageHelper.startPage(1, 10);
		TbItemExample example = new TbItemExample();
		example.createCriteria().andImageIsNotNull();
		List<TbItem> items = tbItemMapper.selectByExample(example);
		assertEquals(10, items.size());
	}
}
