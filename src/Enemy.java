
public class Enemy extends Character{
	//敵のライフ数
	int life = GameWorld.stage;
	int score = 1;
	public Enemy (double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		
	}
}
