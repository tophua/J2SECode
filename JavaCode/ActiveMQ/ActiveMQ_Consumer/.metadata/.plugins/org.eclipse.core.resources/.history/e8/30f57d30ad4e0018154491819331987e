package com.Test;

import com.ActiveMQ.Comsumer;

public class TestComsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Comsumer comsumer = new Comsumer();
		comsumer.init();
		TestComsumer testConsumer = new TestComsumer();
		new Thread(testConsumer.new ConsumerMq(comsumer)).start();
		new Thread(testConsumer.new ConsumerMq(comsumer)).start();
		new Thread(testConsumer.new ConsumerMq(comsumer)).start();
		new Thread(testConsumer.new ConsumerMq(comsumer)).start();
	}

	private class ConsumerMq implements Runnable {
		Comsumer comsumer;

		public ConsumerMq(Comsumer comsumer) {
			this.comsumer = comsumer;
		}

		@Override
		public void run() {
			while (true) {
				try {
					comsumer.getMessage("Jaycekon-MQ");
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
