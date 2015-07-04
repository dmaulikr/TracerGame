import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;


public class Sidebar {
	
	int maxHeight;
	int minHeight = 0;
	int height;
	long barDropRate;
	Timer timer;
	int level;
	boolean leveledUp;
	
	public Sidebar(int height){
		level = 0;
		leveledUp = false;
		maxHeight = height;
		this.height = maxHeight / 2;
		this.barDropRate = 200;
		timer = new Timer();
		timer.schedule(new LevelUpTask(), 0, 5000);
	}
	
	
	class LevelUpTask extends TimerTask{

		@Override
		public void run() {
			if(height > 0){
				levelUp();
				timer.scheduleAtFixedRate(new LowerBarTask(), 500, barDropRate);
			}
		}
		
	}
	class LowerBarTask extends TimerTask{

		@Override
		public void run() {
			lower();
		}
	}
	
	public void levelUp(){
		if(height > 0 && level > 8){
			barDropRate = (long) (barDropRate * 0.95);
			this.level++;
			leveledUp = true;
		}
		if(level >= 8){
			barDropRate = (long) (barDropRate * 0.975);
		}
	}
	
	public void move(){

	}
	
	public void paint(Graphics2D g) {
		if (height < maxHeight * 0.2)
			g.setColor(Color.RED);
		else if(height < maxHeight * 0.5)
			g.setColor(Color.ORANGE);
		else 
			g.setColor(Color.GREEN);	
		
		g.fillRect(0, 0, 20, maxHeight);
		g.setColor(Color.white);
		g.fillRect(0, 0, 20, maxHeight - height);
	}
	
	public void lower() {
		
		if (height > minHeight){
			height-=1;
			System.out.println("BAR LOWERED");
		}
	}
	
	public void raise() {
		if (height < maxHeight)
			height+=10;
		else if (height == maxHeight){
			levelUp();
			resetBar();
		}
	}
	
	public void resetBar(){
		height = (int) (maxHeight * 0.8);
	}
	
}
