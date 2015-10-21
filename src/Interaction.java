
public class Interaction {
	public static void interaction(Agent x, Agent y){
		//timeout mechanism to create leaders when they think there are no leaders
		x.timer_L = y.timer_L = max(x.timer_L-1,  y.timer_L, 0);
		if (x.DoA || y.DoA) {		//どちらかがリーダならライフタイマをリセット
			x.timer_L = y.timer_L = P_RD_RandomWaypoint.t_max;
		}
		else if (x.timer_L == 0) {	//どちらもリーダじゃなくてinitiatorのライフタイマがゼロならinitiatorをリーダにする
			x.DoA = true;
			x.timer_L = y.timer_L = P_RD_RandomWaypoint.t_max;
		}
		//virus-war mechanism to remove mechanism
		x.timer_v = y.timer_v = max(x.timer_v-1, y.timer_v-1, 0);
		x.timer_s = max(0,  x.timer_s-1);	//シールドをデクリメント
		if (x.DoA) {
			
		}
		if (x.timer_v > 0 && x.timer_s ==0) x.DoA = false;		//ウイルスに感染していてシールドがなければagent死亡
		if (y.timer_v > 0 && y.timer_s ==0) y.DoA = false;
	}
	
	
	//交流に用いるメソッド
	private static int max (int a, int b) {
		return a >= b ? a : b;
	}
	private static int max (int a, int b, int c) {
		return max (a, b) > max(b, c) ? max(a, b) : max(b, c); 
	}
}
