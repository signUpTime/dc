package com.qq.common.data.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qq.common.domain.Goods;

public class DcDataMapperTest {
	private ApplicationContext applicationContext;
	
	GoodsMapper goodsMapper;
	
	@Before
	public void init() {
		String[] paths={"classpath*:com/qq/*/META-INF/spring/*.xml"};
		applicationContext=new ClassPathXmlApplicationContext(paths);
		goodsMapper=applicationContext.getBean(GoodsMapper.class);
	}
	
	@Test
	public void test() {
		List<Goods> list = goodsMapper.selectGoodsList();
		Assert.assertTrue(!list.isEmpty());
	}
}
