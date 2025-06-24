
public class CurveEnemy extends Enemy{
	public void draw(MyFrame f) {
		f.setColor(0, 0, 0);
		f.fillOval(x, y, 30, 30);
		f.setColor(288, 288, 288);
		f.fillOval(x+5, y, 20, 30);
	}
	public CurveEnemy (double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		life = 3;
	}
	public void move() {
		super.move();
		//自分がプレイヤーより左にいたら
		if(x<GameWorld.player.x) {
			//右に移動する
			x += GameWorld.stage;
		}
		//自分がプレイヤーより右にいたら
		if (x>GameWorld.player.x) {
			//左に移動する
			x -= GameWorld.stage;
		}
	}

}
