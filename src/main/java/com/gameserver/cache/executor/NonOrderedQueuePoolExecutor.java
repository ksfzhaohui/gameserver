package com.gameserver.cache.executor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.gameserver.cache.AbstractWork;

/**
 * 无序队列线程池
 * @author zhaohui
 *
 */
public class NonOrderedQueuePoolExecutor extends ThreadPoolExecutor {

	public NonOrderedQueuePoolExecutor(int corePoolSize) {
		super(corePoolSize, corePoolSize, 30, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	public void execute(AbstractWork work) {
		execute(work);
	}

}
