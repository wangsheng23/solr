/**
 * 
 */
package com.wsheng.solr.managers;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class IndexManager {
	
	/**
	 * Start the 3 timers after the searcher started 1 minutes
	 */
	private static final int START_TIME = 60000;
	
	private static IndexManager manager = new IndexManager();
	
	public static void startIndexTimers() {
		manager.new P1Timer().scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}, START_TIME, P1Timer.P1_INDEX_FREQUENCY);
		
		
		manager.new P2Timer().scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}, START_TIME, P2Timer.P2_INDEX_FREQUENCY);
		
		manager.new P3Timer().scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		}, START_TIME, P3Timer.P3_INDEX_FREQUENCY);
	}
	
	public class P1Timer extends Timer {
		// every one minute to delta  p1(p=1) level index
		private static final int P1_INDEX_FREQUENCY = 60000;
	}
	
	
	public class P2Timer extends Timer {
		// every 3 minutes to delta p2(p=2) level index
		private static final int P2_INDEX_FREQUENCY = 180000;
	}
	
	
	public class P3Timer extends Timer {
		// every 5 minutes to delta p3(p=3) level index
		private static final int P3_INDEX_FREQUENCY	= 300000;
	}
	
}
