import java.util.Vector;

public class GameFrame extends MyFrame{
	public void run() {
		GameWorld.player= new Player(100,300,0,0);
		addKeyListener(GameWorld.player);
		//面の初期状態を1にセットする
		GameWorld.stage = 1;
		GameWorld.score = 0;
		
		while(true) {
			GameWorld.player.x=100;
			GameWorld.player.y=300;
			GameWorld.playerBullets=new Vector<PlayerBullet>();
			GameWorld.enemies=new Vector<Enemy>();
			GameWorld.enemies.add(new EnemyBase(100,50,GameWorld.stage,0));
			GameWorld.enterPressed = false;

			while(true) {
				clear();
				setColor(0, 0, 0);
				drawString("Stage = " + GameWorld.stage, 300, 50, 15);
				drawString("Score = " + GameWorld.score, 300, 80, 15);
				GameWorld.player.draw(this);
				GameWorld.player.move();
				movePlayerBullets();
				moveEnemies();
				checkPlayerAndEnemies();
				checkPlayerBulletsAndEnemies();
				//敵がすべていなくなったら「クリア」を表示
				if (GameWorld.enemies.size() ==0) {
					setColor(0,0,0);
					drawString("クリア！",100,200,40);
					//Enterキーが押されたらwhileループを終了(キャラクターの動きを止める)
					if (GameWorld.enterPressed) {
						//クリアしたら次の面に進む
						GameWorld.stage++;
						break;
					}
				}
				//プレイヤーが画面外に消えたら「ゲームオーバー」を表示
				else if(GameWorld.player.y<0)
				{
					setColor(0, 0, 0);
					drawString("ゲームオーバー！", 50, 200, 40);
					//Enterキーが押されたらwhileループを終了(キャラクターの動きを止める)
					if (GameWorld.enterPressed) {
						//ゲームオーバーになったら1面に戻る
						GameWorld.stage = 1;
						GameWorld.score = 0;
						break;
					}
				}
				repaint();
				sleep(0.03);
			}
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
		int i=0;
		//敵が画面外に出たら削除する処理
		while(i<GameWorld.enemies.size()) {
			Enemy e=GameWorld.enemies.get(i);
			if(e.y>400) {
				GameWorld.enemies.remove(i);
			}
			else
			{
				i++;
			}
		}
	}
	public void checkPlayerAndEnemies() {
		for (int i=0 ; i<GameWorld.enemies.size(); i++) {
			Enemy e=GameWorld.enemies.get(i);
			//checkHitメソッドを呼び出す
			if(checkHit(GameWorld.player,e)) {
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
				if(checkHit(e,b)) {
					System.out.println("あたり");
					hits++;
					//敵のライフを減らす
					e.life--;
				}
				//敵のライフが0以下になったら敵を消す
				if (e.life<=0){
					GameWorld.score += e.score;
					GameWorld.enemies.remove(j);
				}
				//そうでなければ次の敵の判定へ
				else
				{
				j++;
				}
			}
			//プレイヤー弾が当たっていたら弾を消す
			if(hits>0) {
				GameWorld.playerBullets.remove(i);
			}
			//そうでなければ次の弾の判定へ
			else
			{
				i++;
			}
			
		}
	}
	//キャラクタaとキャラクタbが衝突しているかどうかを判定
	public boolean checkHit (Character a, Character b) {
		//キャラクタaの位置からキャラクタbの位置を引いた数の絶対値が30以下かどうか
		if(Math.abs(a.x-b.x)<=15 && Math.abs(a.y-b.y)<=15) {
			//もし30以下だったら衝突している(trueを返す)
			return true;
		}
		else {
			//もし30より大きかったらだったら衝突していない(falseを返す)
			return false;
		}
	}
}