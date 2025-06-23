import java.util.Vector;

public class GameFrame extends MyFrame{
	public void run() {
		GameWorld.player= new Player(100,300,0,0);
		addKeyListener(GameWorld.player);
		GameWorld.playerBullets=new Vector<PlayerBullet>();
		GameWorld.enemies=new Vector<Enemy>();
		GameWorld.enemies.add(new EnemyBase(100,50,1,0));
		while(true) {
			clear();
			GameWorld.player.draw(this);
			GameWorld.player.move();
			movePlayerBullets();
			moveEnemies();
			checkPlayerAndEnemies();
			checkPlayerBulletsAndEnemies();
			sleep(0.03);
		}
	}
	public void movePlayerBullets() {
			int i=0;
			while (i<GameWorld.playerBullets.size()) {
				PlayerBullet b=GameWorld.playerBullets.get(i);
				b.draw(this);
				b.move();
				if (b.y<0) {
					GameWorld.playerBullets.remove(i);
				} else {
					i++;
				}
			}
	}
	public void moveEnemies() {
		for (int i=0 ; i<GameWorld.enemies.size(); i++) {
			Enemy e=GameWorld.enemies.get(i);
			e.draw(this);
			e.move();
		}
	}
	public void checkPlayerAndEnemies() {
		for (int i=0 ; i<GameWorld.enemies.size(); i++) {
			Enemy e=GameWorld.enemies.get(i);
			//敵の位置からプレイヤーの位置を引いた数の絶対値が30以内のとき
			if (Math.abs(e.x-GameWorld.player.x)<=30 && Math.abs(e.y-GameWorld.player.y)<=30) {
				//当たった判定にする
				System.out.println("やられた！");
				GameWorld.player.y=-1000;
			}
		}
		
	}
	public void checkPlayerBulletsAndEnemies() {
		int i=0;
		while (i<GameWorld.playerBullets.size()) {
			//プレイヤー弾の1つ1つについて、変数bに入れて繰り返し実行する
			PlayerBullet b =GameWorld.playerBullets.get(i);
			int j =0;
			int hits=0;
			while( j<GameWorld.enemies.size()) {
				//敵1つ1つについて、変数eに入れて繰り返し実行する
				Enemy e=GameWorld.enemies.get(j);
				//敵eとプレイヤー弾bが衝突していたら「あたり」と表示
				if(Math.abs(e.x-b.x)<=30 && Math.abs(e.x-b.y)<=30) {
					System.out.println("あたり");
					hits++;
					GameWorld.enemies.remove(j);
				}
				else
				{
				j++;
				}
			}
			if(hits>0) {
				GameWorld.playerBullets.remove(i);
			}
			else
			{
				i++;
			}
			
		}
	}
}