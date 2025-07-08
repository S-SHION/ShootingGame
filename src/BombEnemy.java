
public class BombEnemy extends Enemy{
	 private int state = 0;         // 0: 降下中, 1: 停止中, 2: 爆発済み
	 private int timer = 0;         // 爆発までのカウント用
	 private int explodeWait = 25; // 爆発までの待機フレーム数
	 public boolean isExploded = false;
	 public int explodedTimer = 0;
	 
	 public void draw(MyFrame f) {
	        if (state == 2) {
	            f.setColor(180, 180, 180);
	            f.fillOval(x - 50, y - 50, 120, 120);
	        } else if (state == 1 && timer % 10 < 5) {
	            f.setColor(255, 100, 0); // 点滅演出
	            f.fillOval(x, y, 30, 30);
	            f.setColor(288, 288, 288);
	            f.fillOval(x, y + 10, 30, 20);
	            f.setColor(255, 100, 0);
	            f.fillOval(x + 10, y + 15, 10, 10);
	        } else {
	            f.setColor(0, 100, 220);
	            f.fillOval(x, y, 30, 30);
	            f.setColor(288, 288, 288);
	            f.fillOval(x, y + 10, 30, 20);
	            f.setColor(0, 100, 200);
	            f.fillOval(x + 10, y + 15, 10, 10);
	        }
	 }

	public BombEnemy(double x, double y, double vx, double vy) {
		super(x, y, vx ,vy);
		life = 3;
	}
	
    public void move() {
    	double py =GameWorld.player.y;
       //下降中
       if (state == 0) {
                super.move(); // 通常の移動
                if (Math.abs(py-y)<=50) {
                    vy = 0;
                    vx = 0;
                    state = 1;
                    timer = 0;
                }
       //停止　→　カウント
       }else if (state ==1) {
                timer++;
                if (timer >= explodeWait) {
                    state = 2;
                    isExploded = true;
                }
       }
       else if(state == 2) {
    	   explodedTimer++;
       }
    }

}
