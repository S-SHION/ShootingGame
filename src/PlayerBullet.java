
public class PlayerBullet extends Character{
	public void draw(MyFrame f) {
		f.setColor(245, 0, 0);
		f.fillOval(x+10, y-20, 10, 10);
	}
	public PlayerBullet(double x, double y, double vx, double vy) {
		//Characterクラスのコンストラクタを呼び出し
		super(x, y, vx, vy);
	}

}
