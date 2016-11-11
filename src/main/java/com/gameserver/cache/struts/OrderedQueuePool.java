package com.gameserver.cache.struts;

import java.util.concurrent.ConcurrentHashMap;

public class OrderedQueuePool<K, V> {

	ConcurrentHashMap<K, TasksQueue<V>> map = new ConcurrentHashMap<K, TasksQueue<V>>();

	/**
	 * 获得任务队列
	 * 
	 * @param key
	 * @return
	 */
	public TasksQueue<V> getTasksQueue(K key) {
		TasksQueue<V> queue = map.get(key);

		if (queue == null) {
			TasksQueue<V> newQueue = new TasksQueue<V>();
			queue = map.putIfAbsent(key, newQueue);
			if (queue == null) {
				queue = newQueue;
			}
		}

		return queue;
	}

	/**
	 * 获得全部任务队列
	 * 
	 * @param key
	 * @return
	 */
	public ConcurrentHashMap<K, TasksQueue<V>> getTasksQueues() {
		return map;
	}

	/**
	 * 移除任务队列
	 * 
	 * @param key
	 * @return
	 */
	public void removeTasksQueue(K key) {
		map.remove(key);
	}
}