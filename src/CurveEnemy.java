
public class CurveEnemy extends Enemy{
	public CurveEnemy (double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
	}
	public void move() {
		super.move();
		//自分がプレイヤーより左にいたら
		if(x<GameWorld.player.x) {
			//右に移動する
			x++;
		}
		//自分がプレイヤーより右にいたら
		if (x>GameWorld.player.x) {
			//左に移動する
			x--;
		}
	}

}
