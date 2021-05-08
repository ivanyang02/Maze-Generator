package Dungeon;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DungeonGenerator extends JPanel {
	static char[][] map = new char[100][100];
	static int height;
	static int width;
	static int scale;

	public void paint(Graphics g) {
		if (scale == 1) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					g.setColor(Color.YELLOW);
					if (map[i][j] == 't') {
						g.fillRect(j * 10, i * 10, 10, 10);
					}
					g.setColor(Color.WHITE);
					if (map[i][j] == ' ') {
						g.fillRect(j * 10, i * 10, 10, 10);
					}
					g.setColor(Color.BLACK);
					if (map[i][j] == '#') {
						g.fillRect(j * 10, i * 10, 10, 10);
					}
					g.setColor(Color.RED);
					if (map[i][j] == 'a' || map[i][j] == 'b') {
						g.fillRect(j * 10, i * 10, 10, 10);
					}
					g.setColor(Color.BLUE);
					if (map[i][j] == '.') {
						g.fillRect(j * 10, i * 10, 10, 10);
					}
					if (map[i][j] == 'q') {
						g.setColor(new Color(200, 200, 200));
						g.fillRect(j * 10, i * 10, 10, 10);
						if (map[i - 1][j] == '#') {
							g.setColor(new Color(150, 150, 150));
							g.fillRect(j * 10, i * 10, 10, 4);
						}
						g.setColor(new Color(100, 50, 0));
						if (map[i][j - 1] == '#' && map[i][j + 1] == '#') {
							g.fillRect(j * 10, i * 10 + 3, 10, 4);
							g.setColor(new Color(150, 100, 50));
							g.fillRect(j * 10, i * 10 + 7, 10, 4);
						} else {
							g.fillRect(j * 10 + 3, i * 10, 4, 10);
						}
					}
					if (map[i][j] == 'd' && map[i - 1][j] == '#') {
						g.setColor(new Color(150, 150, 150));
						g.fillRect(j * 10, i * 10, 10, 4);
						g.setColor(new Color(200, 200, 200));
						g.fillRect(j * 10, i * 10 + 4, 10, 6);
					} else if (map[i][j] == 'd') {
						g.setColor(new Color(200, 200, 200));
						g.fillRect(j * 10, i * 10, 10, 10);
					}
				}
			}
		} else if (scale == 0) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					g.setColor(Color.YELLOW);
					if (map[i][j] == 't') {
						g.fillRect(j * 5, i * 5, 5, 5);
					}
					g.setColor(Color.WHITE);
					if (map[i][j] == ' ') {
						g.fillRect(j * 5, i * 5, 5, 5);
					}
					g.setColor(Color.BLACK);
					if (map[i][j] == '#') {
						g.fillRect(j * 5, i * 5, 5, 5);
					}
					g.setColor(Color.RED);
					if (map[i][j] == 'a' || map[i][j] == 'b') {
						g.fillRect(j * 5, i * 5, 5, 5);
					}
					g.setColor(Color.BLUE);
					if (map[i][j] == '.') {
						g.fillRect(j * 5, i * 5, 5, 5);
					}
					if (map[i][j] == 'q') {
						g.setColor(new Color(200, 200, 200));
						g.fillRect(j * 5, i * 5, 5, 5);
						if (map[i - 1][j] == '#') {
							g.setColor(new Color(150, 150, 150));
							g.fillRect(j * 5, i * 5, 5, 2);
						}
						g.setColor(new Color(150, 100, 50));
						if (map[i][j - 1] == '#' && map[i][j + 1] == '#') {
							g.fillRect(j * 5, i * 5 + 1, 5, 3);
						} else {
							g.fillRect(j * 5 + 1, i * 5, 3, 5);
						}
					}
					if (map[i][j] == 'd' && map[i - 1][j] == '#') {
						g.setColor(new Color(150, 150, 150));
						g.fillRect(j * 5, i * 5, 5, 2);
						g.setColor(new Color(200, 200, 200));
						g.fillRect(j * 5, i * 5 + 2, 5, 3);
					} else if (map[i][j] == 'd') {
						g.setColor(new Color(200, 200, 200));
						g.fillRect(j * 5, i * 5, 5, 5);
					}
				}
			}
		}
	}

	public static class Connector {
		int x;
		int y;

		public Connector(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Enter scale of dungeon. (0 for large dungeons, 1 for small dungeons)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		scale = Integer.parseInt(st.nextToken());
		System.out.println("Enter size of dungeon. (x, y)");
		st = new StringTokenizer(br.readLine());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		System.out.println("Enter attempted number of rooms.");
		st = new StringTokenizer(br.readLine());
		int rooms = Integer.parseInt(st.nextToken());
		map = new char[height][width];
		JFrame frame = new JFrame("Map");
		System.out.println("Enter 0 for full maze, 1 for reduced dungeon");
		int deadends = Integer.parseInt(br.readLine());
		DungeonGenerator game = new DungeonGenerator();
		frame.add(game);
		frame.setSize(1054, 1108);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.toFront();
		frame.setAlwaysOnTop(true);
		frame.requestFocus();
		frame.setAlwaysOnTop(false);
		for (int i = 0; i < height; i++) {
			Arrays.fill(map[i], '#');
		}
		game.repaint();
		Thread.sleep(500);
		boolean overlap = false;
		for (int i = 0; i < rooms; i++) {
			int xx = (int) (((int) (Math.random() * (width / 2 - 6))) * 2 + 1);
			int yy = (int) (((int) (Math.random() * (height / 2 - 6))) * 2 + 1);
			int ww = xx + (int) (((int) (Math.random() * 4)) * 2 + 5);
			int hh = yy + (int) (((int) (Math.random() * 4)) * 2 + 5);
			overlap = false;
			loop: for (int j = yy; j < hh; j++) {
				for (int k = xx; k < ww; k++) {
					if (map[j - 1][k - 1] == '.' || map[j - 1][k] == '.' || map[j - 1][k + 1] == '.'
							|| map[j][k - 1] == '.' || map[j][k] == '.' || map[j][k + 1] == '.'
							|| map[j + 1][k - 1] == '.' || map[j + 1][k] == '.' || map[j + 1][k + 1] == '.') {
						overlap = true;
						break loop;
					}
				}
			}
			if (overlap == false) {
				for (int j = yy; j < hh; j++) {
					for (int k = xx; k < ww; k++) {
						map[j][k] = '.';
					}
				}
				game.repaint();
				Thread.sleep(100);
			}
		}
		LinkedList<Integer> hqueue = new LinkedList<Integer>();
		LinkedList<Integer> vqueue = new LinkedList<Integer>();
		int counter = 0;
		int curh = 0;
		int curv = 0;
		boolean started = true;
		int startr = (height / 4) * 2 + 1;
		int startc = (width / 4) * 2 + 1;
		for (int i = startr; i < height - 3; i += 2) {
			for (int j = startc; j < width - 3; j += 2) {
				if ((map[i - 1][j - 1] != '.' && map[i - 1][j] != '.' && map[i - 1][j + 1] != '.'
						&& map[i][j - 1] != '.' && map[i][j] != '.' && map[i][j + 1] != '.' && map[i + 1][j - 1] != '.'
						&& map[i + 1][j] != '.' && map[i + 1][j + 1] != '.')
						&& (map[i - 1][j - 1] != ' ' && map[i - 1][j] != ' ' && map[i - 1][j + 1] != ' '
								&& map[i][j - 1] != ' ' && map[i][j] != ' ' && map[i][j + 1] != ' '
								&& map[i + 1][j - 1] != ' ' && map[i + 1][j] != ' ' && map[i + 1][j + 1] != ' ')
						&& (map[i - 1][j - 1] != 't' && map[i - 1][j] != 't' && map[i - 1][j + 1] != 't'
								&& map[i][j - 1] != 't' && map[i][j] != 't' && map[i][j + 1] != 't'
								&& map[i + 1][j - 1] != 't' && map[i + 1][j] != 't' && map[i + 1][j + 1] != 't')) {
					if (started == true) {
						map[i][j] = ' ';
					} else {
						map[i][j] = 't';
					}
					hqueue.add(i - 1);
					vqueue.add(j);
					hqueue.add(i + 1);
					vqueue.add(j);
					hqueue.add(i);
					vqueue.add(j - 1);
					hqueue.add(i);
					vqueue.add(j + 1);
					if (started == true) {
						while (!hqueue.isEmpty() && !vqueue.isEmpty()) {
							game.repaint();
							Thread.sleep(1);
							int cur = (int) (Math.random() * hqueue.size());
							curh = hqueue.get(cur);
							curv = vqueue.get(cur);
							hqueue.remove(cur);
							vqueue.remove(cur);
							if (curh >= 1 && curh < height - 2 && curv >= 1 && curv < width - 2) {
								if (map[curh][curv] == '#' && map[curh - 1][curv - 1] != '.'
										&& map[curh - 1][curv] != '.' && map[curh - 1][curv + 1] != '.'
										&& map[curh][curv - 1] != '.' && map[curh][curv + 1] != '.'
										&& map[curh + 1][curv - 1] != '.' && map[curh + 1][curv] != '.'
										&& map[curh + 1][curv + 1] != '.') {
									if (map[curh - 1][curv] == ' ') {
										if (map[curh + 1][curv] == '#') {
											map[curh + 1][curv] = ' ';
											map[curh][curv] = ' ';
											hqueue.add(curh + 2);
											vqueue.add(curv);
											hqueue.add(curh + 1);
											vqueue.add(curv - 1);
											hqueue.add(curh + 1);
											vqueue.add(curv + 1);
										}
									}
									if (map[curh + 1][curv] == ' ') {
										if (map[curh - 1][curv] == '#') {
											map[curh - 1][curv] = ' ';
											map[curh][curv] = ' ';
											hqueue.add(curh - 2);
											vqueue.add(curv);
											hqueue.add(curh - 1);
											vqueue.add(curv - 1);
											hqueue.add(curh - 1);
											vqueue.add(curv + 1);
										}
									}
									if (map[curh][curv + 1] == ' ') {
										if (map[curh][curv - 1] == '#') {
											map[curh][curv - 1] = ' ';
											map[curh][curv] = ' ';
											hqueue.add(curh);
											vqueue.add(curv - 2);
											hqueue.add(curh - 1);
											vqueue.add(curv - 1);
											hqueue.add(curh + 1);
											vqueue.add(curv - 1);
										}
									}
									if (map[curh][curv - 1] == ' ') {
										if (map[curh][curv + 1] == '#') {
											map[curh][curv + 1] = ' ';
											map[curh][curv] = ' ';
											hqueue.add(curh);
											vqueue.add(curv + 2);
											hqueue.add(curh + 1);
											vqueue.add(curv + 1);
											hqueue.add(curh - 1);
											vqueue.add(curv + 1);
										}
									}
								}
							}
						}
						startr = 1;
						startc = 1;
						i = 1;
						j = 1;
					} else {
						while (!hqueue.isEmpty() && !vqueue.isEmpty()) {
							game.repaint();
							Thread.sleep(1);
							int cur = (int) (Math.random() * hqueue.size());
							curh = hqueue.get(cur);
							curv = vqueue.get(cur);
							hqueue.remove(cur);
							vqueue.remove(cur);
							if (curh >= 1 && curh < height - 2 && curv >= 1 && curv < width - 2) {
								if (map[curh][curv] == '#' && map[curh - 1][curv - 1] != '.'
										&& map[curh - 1][curv] != '.' && map[curh - 1][curv + 1] != '.'
										&& map[curh][curv - 1] != '.' && map[curh][curv + 1] != '.'
										&& map[curh + 1][curv - 1] != '.' && map[curh + 1][curv] != '.'
										&& map[curh + 1][curv + 1] != '.') {
									if (map[curh - 1][curv] == 't') {
										if (map[curh + 1][curv] == '#') {
											map[curh + 1][curv] = 't';
											map[curh][curv] = 't';
											hqueue.add(curh + 2);
											vqueue.add(curv);
											hqueue.add(curh + 1);
											vqueue.add(curv - 1);
											hqueue.add(curh + 1);
											vqueue.add(curv + 1);
										}
									}
									if (map[curh + 1][curv] == 't') {
										if (map[curh - 1][curv] == '#') {
											map[curh - 1][curv] = 't';
											map[curh][curv] = 't';
											hqueue.add(curh - 2);
											vqueue.add(curv);
											hqueue.add(curh - 1);
											vqueue.add(curv - 1);
											hqueue.add(curh - 1);
											vqueue.add(curv + 1);
										}
									}
									if (map[curh][curv + 1] == 't') {
										if (map[curh][curv - 1] == '#') {
											map[curh][curv - 1] = 't';
											map[curh][curv] = 't';
											hqueue.add(curh);
											vqueue.add(curv - 2);
											hqueue.add(curh - 1);
											vqueue.add(curv - 1);
											hqueue.add(curh + 1);
											vqueue.add(curv - 1);
										}
									}
									if (map[curh][curv - 1] == 't') {
										if (map[curh][curv + 1] == '#') {
											map[curh][curv + 1] = 't';
											map[curh][curv] = 't';
											hqueue.add(curh);
											vqueue.add(curv + 2);
											hqueue.add(curh + 1);
											vqueue.add(curv + 1);
											hqueue.add(curh - 1);
											vqueue.add(curv + 1);
										}
									}
								}
							}
						}
					}
					started = false;
					// break OUTER_LOOP;
				}
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (map[i][j] == 't') {
					map[i][j] = '.';
					game.repaint();
					Thread.sleep(1);
				}
			}
		}
		game.repaint();
		Thread.sleep(1);
		LinkedList<Connector> connectors = new LinkedList<Connector>();
		connectors.add(new Connector(-1, -1));
		for (int i = 1; i < height - 1; i++) {
			for (int j = 1; j < width - 1; j++) {
				if (map[i][j] == '#') {
					if (((map[i - 1][j] == ' ') && map[i + 1][j] == '.')
							|| ((map[i][j + 1] == ' ') && map[i][j - 1] == '.')
							|| ((map[i][j - 1] == ' ') && map[i][j + 1] == '.')
							|| ((map[i + 1][j] == ' ') && map[i - 1][j] == '.')) {
						// map[i][j] = 'a';
						// connectors.add(new Connector(j, i));
						// game.repaint();
						// Thread.sleep(10);
					}
				}
			}
		}

		int curx = 0;
		int cury = 0;
		loop: for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (map[i][j] == ' ') {
					hqueue.add(j);
					vqueue.add(i);
					curx = j;
					cury = i;
					break loop;
				}
			}
		}
		while (!connectors.isEmpty()) {
			if (connectors.get(0).x == -1) {
				connectors.remove(0);
			}
			while (!hqueue.isEmpty()) {
				curh = hqueue.poll();
				curv = vqueue.poll();
				if (curv < height - 2 && (map[curv + 2][curh] == '.') && map[curv + 1][curh] == '#') {
					map[curv + 1][curh] = 'a';
					connectors.add(new Connector(curv + 1, curh));
				}
				if (curv > 1 && (map[curv - 2][curh] == '.') && map[curv - 1][curh] == '#') {
					map[curv - 1][curh] = 'a';
					connectors.add(new Connector(curv - 1, curh));
				}
				if (curh < width - 2 && (map[curv][curh + 2] == '.') && map[curv][curh + 1] == '#') {
					map[curv][curh + 1] = 'a';
					connectors.add(new Connector(curv, curh + 1));
				}
				if (curh > 1 && (map[curv][curh - 2] == '.') && map[curv][curh - 1] == '#') {
					map[curv][curh - 1] = 'a';
					connectors.add(new Connector(curv, curh - 1));
				}
				if (map[curv - 1][curh] == '.' || map[curv - 1][curh] == ' ') {
					map[curv - 1][curh] = 'd';
					hqueue.add(curh);
					vqueue.add(curv - 1);
				}
				if (map[curv + 1][curh] == '.' || map[curv + 1][curh] == ' ') {
					map[curv + 1][curh] = 'd';
					hqueue.add(curh);
					vqueue.add(curv + 1);
				}
				if (map[curv][curh - 1] == '.' || map[curv][curh - 1] == ' ') {
					map[curv][curh - 1] = 'd';
					hqueue.add(curh - 1);
					vqueue.add(curv);
				}
				if (map[curv][curh + 1] == '.' || map[curv][curh + 1] == ' ') {
					map[curv][curh + 1] = 'd';
					hqueue.add(curh + 1);
					vqueue.add(curv);
				}
				game.repaint();
				Thread.sleep(1);
			}
			for (int j = 0; j < 4; j++) {
				for (int i = 0; i < connectors.size(); i++) {
					if ((map[connectors.get(i).x + 1][connectors.get(i).y] == 'd'
							&& map[connectors.get(i).x - 1][connectors.get(i).y] == 'd')
							|| (map[connectors.get(i).x][connectors.get(i).y + 1] == 'd'
									&& map[connectors.get(i).x][connectors.get(i).y - 1] == 'd')
							|| map[connectors.get(i).x][connectors.get(i).y - 1] == 'q'
							|| map[connectors.get(i).x][connectors.get(i).y + 1] == 'q'
							|| map[connectors.get(i).x - 1][connectors.get(i).y] == 'q'
							|| map[connectors.get(i).x + 1][connectors.get(i).y] == 'q') {
						map[connectors.get(i).x][connectors.get(i).y] = '#';
						// if (Math.random() * 20 < 1 && Math.abs(connectors.get(i).x - curx) > 2
						// && Math.abs(connectors.get(i).y - cury) > 2) {
						// map[connectors.get(i).y][connectors.get(i).x] = 'q';
						// }
						connectors.remove(i);
						game.repaint();
						Thread.sleep(1);

					}
				}
			}
			Collections.shuffle(connectors);
			if (connectors.size() > 1) {
				Connector curc = connectors.poll();
				map[curc.x][curc.y] = 'q';
				hqueue.add(curc.y);
				vqueue.add(curc.x);
			}
		}
		// game.repaint();
		// Thread.sleep(5);
		game.repaint();
		Thread.sleep(5);

		for (int k = 0; k < 10; k++) {
			for (int i = 1; i < height - 1; i++) {
				for (int j = 1; j < width - 1; j++) {
					counter = 0;
					if (map[i - 1][j] == '#') {
						counter++;
					}
					if (map[i + 1][j] == '#') {
						counter++;
					}
					if (map[i][j - 1] == '#') {
						counter++;
					}
					if (map[i][j + 1] == '#') {
						counter++;
					}
					if (counter >= 3) {
						// map[i][j] = '#';
						j++;
						game.repaint();
						// Thread.sleep(1);
					}
				}
			}
		}

		if (deadends == 1) {
			for (int q = 0; q < 7; q++) {
				int[][] step = new int[height][width];
				for (int i = 0; i < height; i++) {
					Arrays.fill(step[i], Integer.MAX_VALUE);
				}
				step[0][0] = 0;
				step[height - 1][width - 1] = 0;
				step[0][width - 1] = 0;
				step[height - 1][0] = 0;
				hqueue.add(0);
				vqueue.add(0);
				hqueue.add(height - 1);
				vqueue.add(width - 1);
				hqueue.add(0);
				vqueue.add(width - 1);
				hqueue.add(height - 1);
				vqueue.add(0);
				while (!hqueue.isEmpty()) {
					curh = hqueue.poll();
					curv = vqueue.poll();
					counter = 0;
					if (curh < height && curv < width && curh > 0 && curv > 0 && map[curh][curv] == 'd') {
						if (map[curh - 1][curv] == '#') {
							counter++;
						}
						if (map[curh + 1][curv] == '#') {
							counter++;
						}
						if (map[curh][curv - 1] == '#') {
							counter++;
						}
						if (map[curh][curv + 1] == '#') {
							counter++;
						}
						if (counter >= 3) {
							map[curh][curv] = '#';
						}
						game.repaint();
						Thread.sleep(0);
					}

					if (curh > 1 && curh < height - 2 && curv > 1 && curv < width - 2) {
						if (step[curh - 1][curv] > step[curh][curv] + 1) {
							step[curh - 1][curv] = step[curh][curv] + 1;
							hqueue.add(curh - 1);
							vqueue.add(curv);
						}
						if (step[curh + 1][curv] > step[curh][curv] + 1) {
							step[curh + 1][curv] = step[curh][curv] + 1;
							hqueue.add(curh + 1);
							vqueue.add(curv);
						}
						if (step[curh][curv - 1] > step[curh][curv] + 1) {
							step[curh][curv - 1] = step[curh][curv] + 1;
							hqueue.add(curh);
							vqueue.add(curv - 1);
						}
						if (step[curh][curv + 1] > step[curh][curv] + 1) {
							step[curh][curv + 1] = step[curh][curv] + 1;
							hqueue.add(curh);
							vqueue.add(curv + 1);
						}
					}
				}
				step = new int[height][width];
				for (int i = 0; i < height; i++) {
					Arrays.fill(step[i], Integer.MAX_VALUE);
				}
				step[height / 2][width / 2] = 0;
				hqueue.add(height / 2 - 1);
				vqueue.add(width / 2);
				hqueue.add(height / 2 + 1);
				vqueue.add(width / 2);
				hqueue.add(height / 2);
				vqueue.add(width / 2 - 1);
				hqueue.add(height / 2);
				vqueue.add(width / 2 + 1);
				while (!hqueue.isEmpty()) {
					curh = hqueue.poll();
					curv = vqueue.poll();
					counter = 0;
					if (map[curh][curv] == 'd') {
						if (map[curh - 1][curv] == '#') {
							counter++;
						}
						if (map[curh + 1][curv] == '#') {
							counter++;
						}
						if (map[curh][curv - 1] == '#') {
							counter++;
						}
						if (map[curh][curv + 1] == '#') {
							counter++;
						}
						if (counter >= 3) {
							map[curh][curv] = '#';
						}
						game.repaint();
						Thread.sleep(0);
					}
					if (curh > 0 && step[curh - 1][curv] > step[curh][curv] + 1) {
						step[curh - 1][curv] = step[curh][curv] + 1;
						hqueue.add(curh - 1);
						vqueue.add(curv);
					}
					if (curh < height - 1 && step[curh + 1][curv] > step[curh][curv] + 1) {
						step[curh + 1][curv] = step[curh][curv] + 1;
						hqueue.add(curh + 1);
						vqueue.add(curv);
					}
					if (curv > 0 && step[curh][curv - 1] > step[curh][curv] + 1) {
						step[curh][curv - 1] = step[curh][curv] + 1;
						hqueue.add(curh);
						vqueue.add(curv - 1);
					}
					if (curv < width - 1 && step[curh][curv + 1] > step[curh][curv] + 1) {
						step[curh][curv + 1] = step[curh][curv] + 1;
						hqueue.add(curh);
						vqueue.add(curv + 1);
					}
				}
			}
		}

		int[][] step = new int[height][width];
		for (int i = 0; i < height; i++) {
			Arrays.fill(step[i], Integer.MAX_VALUE);
		}
		loop: for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (map[i][j] == ' ') {
					hqueue.add(i);
					vqueue.add(j);
					step[i][j] = 0;
					break loop;
				}
			}
		}
		while (!hqueue.isEmpty()) {
			curh = hqueue.poll();
			curv = vqueue.poll();
			map[curh][curv] = 'd';
			if (step[curh - 1][curv] > step[curh][curv] + 1
					&& (map[curh - 1][curv] == ' ' || map[curh - 1][curv] == 'q')) {
				step[curh - 1][curv] = step[curh][curv] + 1;
				hqueue.add(curh - 1);
				vqueue.add(curv);
			}
			if (step[curh + 1][curv] > step[curh][curv] + 1
					&& (map[curh + 1][curv] == ' ' || map[curh + 1][curv] == 'q')) {
				step[curh + 1][curv] = step[curh][curv] + 1;
				hqueue.add(curh + 1);
				vqueue.add(curv);
			}
			if (step[curh][curv - 1] > step[curh][curv] + 1
					&& (map[curh][curv - 1] == ' ' || map[curh][curv - 1] == 'q')) {
				step[curh][curv - 1] = step[curh][curv] + 1;
				hqueue.add(curh);
				vqueue.add(curv - 1);
			}
			if (step[curh][curv + 1] > step[curh][curv] + 1
					&& (map[curh][curv + 1] == ' ' || map[curh][curv + 1] == 'q')) {
				step[curh][curv + 1] = step[curh][curv] + 1;
				hqueue.add(curh);
				vqueue.add(curv + 1);
			}
			game.repaint();
			Thread.sleep(1);
		}
		int doorcounter = 0;
		for (int i = 1; i < height - 1; i++) {
			for (int j = 1; j < width - 1; j++) {
				doorcounter = 0;
				if (map[i][j] == 'q') {
					if (map[i - 1][j] == '#') {
						doorcounter++;
					}
					if (map[i + 1][j] == '#') {
						doorcounter++;
					}
					if (map[i][j - 1] == '#') {
						doorcounter++;
					}
					if (map[i][j + 1] == '#') {
						doorcounter++;
					}
					if (doorcounter == 3) {
						map[i][j] = '#';
					}
				}
			}
		}
		game.repaint();
		Thread.sleep(200);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
	}
}
