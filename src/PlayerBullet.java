
public class PlayerBullet extends Character{
	public void draw(MyFrame f) {
		f.setColor(245, 0, 0);
		f.fillOval(x+7, y-20, 15, 15);
	}
	public PlayerBullet(double x, double y, double vx, double vy) {
		//Characterクラスのコンストラクタを呼び出し
		super(x, y, vx, vy);
	}

}
