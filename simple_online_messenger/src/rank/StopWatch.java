package rank;

public class StopWatch extends Thread{
	static String timeText;
	static long time = 0l;
	static long preTime = 0l;
	boolean play = true;
	int showTime = 0;
	public void run() {
		preTime = System.currentTimeMillis();
		while(play) {
			try {
				sleep(10);
				if(GamePanel.isStopWatch) {
					time = System.currentTimeMillis() - preTime;
					int m = (int)(time/1000.0/60.0);
					int s = (int)(time%(1000.0*60)/1000.0);
					int ms = (int)(time%1000/10.0);
					timeText = String.format("%02d:%02d:%02d", m,s,ms);
					showTime += 1;
					if(showTime%5 == 0) {
						GamePanel.timeTextLb.setText(timeText);
					}
				}
			}catch(Exception e) {}
		}
	}

}