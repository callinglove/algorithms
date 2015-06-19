/*
 * http://my.oschina.net/luckid/blog/382615
 *
 * 单源顶点最短路径问题求解:
 * 最短路径问题:给定带权有向图G和源点v0，求从v0到图中其他各顶点的最短距离.
 * Dijkstra算法：按路径长度递增的方法计算某一点到其余各点的最短距离。
 */
public class Dijkstra {

	/*
	 * max:给出的极大值，表示顶点之间无法到达 dist[]:存储最短路径长度的数组 prve[]:存储当前顶点的前驱顶点
	 * a[][]:给定测试的邻接矩阵表
	 */
	private static int max = Integer.MAX_VALUE;
	private static int dist[] = new int[6];
	private static int prev[] = new int[6];
	private static int a[][] = {
		{0, max, 10, max, 30, 100},
		{max, 0, 5, max, max, max},
		{max, max, 0, 50, max, max},
		{max, max, max, 0, 20, 10},
		{max, max, max, max, 0, 60},
		{max, max, max, max, max, 0}
	};

	/*
	 * dijkstra算法 v表示源点位置
	 */
	public void dijkstra(int source, int[][] a, int dist[], int prve[]) {
		int len = dist.length;
		// s[]:存储已经找到最短路径的顶点，false为未求得
		boolean[] isVisited = new boolean[len + 1];

		for (int i = 0; i < len; i++) {
			dist[i] = a[source][i];
			isVisited[i] = false;
			/*
			 * prve[]数组存储源点到顶点vi之间的最短路径上该顶点的前驱顶点, 若从源点到顶点vi之间无法到达，则前驱顶点为-1
			 */
			if (dist[i] < Integer.MAX_VALUE)
				prve[i] = source;
			else
				prve[i] = -1;
		}

		dist[source] = 0; // 初始化v0源点属于s集
		isVisited[source] = true; // 表示v0源点已经求得最短路径
		prve[source] = source;

		for (int i = 0; i < len; i++) {
			int min = Integer.MAX_VALUE; // temp暂存v0源点到vi顶点的最短路径
			int u = source;
			for (int j = 0; j < len; j++) {
				if ((!isVisited[j]) && dist[j] < min) { // 顶点vi不属于s集当前顶点不属于s集(未求得最短路径)并且距离v0更近
					u = j; // 更新当前源点,当前vi作为下一个路径的源点
					min = dist[j]; // 更新当前最短路径
				}
			}
			isVisited[u] = true; // 顶点vi进s集
			// 更新当前最短路径以及路径长度
			for (int j = 0; j < len; j++) {
				if ((!isVisited[j]) && a[u][j] < Integer.MAX_VALUE) { // 当前顶点不属于s集(未求得最短路径)并且当前顶点有前驱顶点
					int newDist = dist[u] + a[u][j]; // 累加更新最短路径
					if (newDist < dist[j]) {
						dist[j] = newDist; // 更新后的最短路径
						prve[j] = u; // 当前顶点加入前驱顶点集
					}
				}
			}
		}
	}

	/*
	 * m:源点 []p:更新结果后的前驱顶点集 []d:更新结果后的最短路径集
	 */
	public void outPath(int source, int[] p, int[] d) {
		for (int i = 0; i < dist.length; i++) {
			// 当前顶点已求得最短路径并且当前顶点不等于源点
			if (d[i] < Integer.MAX_VALUE && i != source) {
				System.out.print("v" + i + "<--");
				int next = p[i]; // 设置当前顶点的前驱顶点
				while (next != source) { // 若前驱顶点不为一个，循环求得剩余前驱顶点
					System.out.print("v" + next + "<--");
					next = p[next];
				}
				System.out.println("v" + source + ":" + d[i]);
			}
			// 当前顶点未求得最短路径的处理方法
			else if (i != source)
				System.out.println("v" + i + "<--" + "v" + source + ":no path");
		}
	}

	public static void main(String[] args) {
		int source = 3;
		Dijkstra D = new Dijkstra();
		D.dijkstra(source, a, dist, prev);
		D.outPath(source, prev, dist);
	}
}