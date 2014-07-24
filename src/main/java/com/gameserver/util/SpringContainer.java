package com.gameserver.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 加载spring配置文件
 * 
 * @author ksfzhaohui
 * 
 */
public class SpringContainer {
	private static SpringContainer _instance = new SpringContainer();
	private ApplicationContext ctx = null;

	private SpringContainer() {
	}

	public void loadSpring() {
		String[] cfigPath = { "gs-handlers.xml" };
		ctx = new ClassPathXmlApplicationContext(cfigPath);
	}

	public static SpringContainer getInstance() {
		return _instance;
	}

	public Object getBeanById(String beanId) {
		return ctx.getBean(beanId);
	}
}
