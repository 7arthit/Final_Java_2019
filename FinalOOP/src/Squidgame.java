import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Squidgame {
	public static void main(String[] args) {
		SQgame sqg = new SQgame();
		sqg.setVisible(true);
	}
}

@SuppressWarnings("serial")
class SQgame extends JFrame {
	MySQgame msqg = new MySQgame();

	public SQgame() {
		setTitle("Squid Game");
		setSize(500, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		add(msqg);
	}
}

@SuppressWarnings("serial")
class MySQgame extends JPanel {
	Image imblack = Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir") + File.separator + "black.jpg");
	Image imdollb = Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir") + File.separator + "dollback.png");
	Image imdollf = Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir") + File.separator + "dollfront.png");
	Image imruner = Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir") + File.separator + "runner.png");
	Image imfinish = Toolkit.getDefaultToolkit()
			.createImage(System.getProperty("user.dir") + File.separator + "finish.png");
	int number = 20;
	int x[] = new int[number];
	int y[] = new int[number];

	public MySQgame() {
		setSize(500, 700);
		setLocation(0, 0);
		for (int i = 0; i < x.length; i++) {
			x[i] = new Random().nextInt(450);
			y[i] = new Random().nextInt(10) + 600;
		}
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new task(this), 0, 50);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(imblack, 0, 0, this);
		g.drawImage(imdollb, 200, 10, 600, 375, 0, 0, 1000, 1000, this);
		g.drawImage(imdollf, 200, 10, 600, 375, 0, 0, 1000, 1000, this);
		g.drawImage(imfinish, 0, 200, 1000, 700, 0, 0, 1000, 1000, this);
		for (int i = 0; i < x.length; i++) {
			g.drawImage(imruner, x[i], y[i], x[i] + 300, y[i] + 300, 0, 0, 1000, 1000, this);
		}
	}
}

class task extends TimerTask {
	MySQgame pnmysqgame;
	int count = 0;

	public task(MySQgame mySQgame) {
		this.pnmysqgame = mySQgame;
	}

	@Override
	public void run() {
		for (int i = 0; i < pnmysqgame.number; i++) {
			int ranx = new Random().nextInt(450);
			int rany = new Random().nextInt(10) + 600;
			rany = pnmysqgame.y[i] - 1;
			pnmysqgame.y[i] += rany;
			if (pnmysqgame.y[i] < 325) {
				rany = -100;
				ranx = -100;
				pnmysqgame.x[i] = ranx;
			}
			pnmysqgame.y[i] = rany;
			pnmysqgame.repaint();
		}
	}
}