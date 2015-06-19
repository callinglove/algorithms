/*
 * http://my.oschina.net/luckid/blog/382615
 *
 * ��Դ�������·���������:
 * ���·������:������Ȩ����ͼG��Դ��v0�����v0��ͼ���������������̾���.
 * Dijkstra�㷨����·�����ȵ����ķ�������ĳһ�㵽����������̾��롣
 */
public class Dijkstra {

	/*
	 * max:�����ļ���ֵ����ʾ����֮���޷����� dist[]:�洢���·�����ȵ����� prve[]:�洢��ǰ�����ǰ������
	 * a[][]:�������Ե��ڽӾ����
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
	 * dijkstra�㷨 v��ʾԴ��λ��
	 */
	public void dijkstra(int source, int[][] a, int dist[], int prve[]) {
		int len = dist.length;
		// s[]:�洢�Ѿ��ҵ����·���Ķ��㣬falseΪδ���
		boolean[] isVisited = new boolean[len + 1];

		for (int i = 0; i < len; i++) {
			dist[i] = a[source][i];
			isVisited[i] = false;
			/*
			 * prve[]����洢Դ�㵽����vi֮������·���ϸö����ǰ������, ����Դ�㵽����vi֮���޷������ǰ������Ϊ-1
			 */
			if (dist[i] < Integer.MAX_VALUE)
				prve[i] = source;
			else
				prve[i] = -1;
		}

		dist[source] = 0; // ��ʼ��v0Դ������s��
		isVisited[source] = true; // ��ʾv0Դ���Ѿ�������·��
		prve[source] = source;

		for (int i = 0; i < len; i++) {
			int min = Integer.MAX_VALUE; // temp�ݴ�v0Դ�㵽vi��������·��
			int u = source;
			for (int j = 0; j < len; j++) {
				if ((!isVisited[j]) && dist[j] < min) { // ����vi������s����ǰ���㲻����s��(δ������·��)���Ҿ���v0����
					u = j; // ���µ�ǰԴ��,��ǰvi��Ϊ��һ��·����Դ��
					min = dist[j]; // ���µ�ǰ���·��
				}
			}
			isVisited[u] = true; // ����vi��s��
			// ���µ�ǰ���·���Լ�·������
			for (int j = 0; j < len; j++) {
				if ((!isVisited[j]) && a[u][j] < Integer.MAX_VALUE) { // ��ǰ���㲻����s��(δ������·��)���ҵ�ǰ������ǰ������
					int newDist = dist[u] + a[u][j]; // �ۼӸ������·��
					if (newDist < dist[j]) {
						dist[j] = newDist; // ���º�����·��
						prve[j] = u; // ��ǰ�������ǰ�����㼯
					}
				}
			}
		}
	}

	/*
	 * m:Դ�� []p:���½�����ǰ�����㼯 []d:���½��������·����
	 */
	public void outPath(int source, int[] p, int[] d) {
		for (int i = 0; i < dist.length; i++) {
			// ��ǰ������������·�����ҵ�ǰ���㲻����Դ��
			if (d[i] < Integer.MAX_VALUE && i != source) {
				System.out.print("v" + i + "<--");
				int next = p[i]; // ���õ�ǰ�����ǰ������
				while (next != source) { // ��ǰ�����㲻Ϊһ����ѭ�����ʣ��ǰ������
					System.out.print("v" + next + "<--");
					next = p[next];
				}
				System.out.println("v" + source + ":" + d[i]);
			}
			// ��ǰ����δ������·���Ĵ�����
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