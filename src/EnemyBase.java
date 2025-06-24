
public class EnemyBase extends Enemy{
	public void draw(MyFrame f) {
		f.setColor(0, 180, 0);
		f.fillOval(x, y, 32, 32);
		f.setColor(200, 200, 200);
		f.fillOval(x-16, y+8, 64, 16);
	}
	public EnemyBase(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		//EnemyBaseの耐久値を20
		life = GameWorld.stage * 10;
		score = 10;
	}
	public void move() {
		super.move();
		if (x>300) {
			vx = - GameWorld.stage;
		}
		if(x<100) {
			vx = GameWorld.stage;
		}
		//GameWordにStraightEnemyを出現させる
		if (Math.random()<0.05) {
			GameWorld.enemies.add(new StraightEnemy(x, y, 0, 1 + GameWorld.stage));
		}
		//GameWordにRandomEnemyを出現させる
		if(Math.random()<0.05) {
			GameWorld.enemies.add(new RandomEnemy(x, y, 0, GameWorld.stage));
		}
		//GameWordにDropEnemyを出現させる
		if(Math.random()<0.05) {
			GameWorld.enemies.add(new DropEnemy(x, y, 0, GameWorld.stage));
		}
		//GameWordにCurveEnemyを出現させる
		if(Math.random()<0.05) {
			GameWorld.enemies.add(new CurveEnemy(x, y, 0, GameWorld.stage));
		}
	}

}
