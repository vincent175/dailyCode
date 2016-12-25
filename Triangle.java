package vincent.triangle;

public class Triangle {

	public static void main(String[] args) {
//		draw(0, 5, 4);
//		nestDraw(6);
		loopDraw(20);
	}
	
	
	//循环画出嵌套三角形
	private static void loopDraw(int time) {
		for (int i = 1; i <= time; i++) {
			nestDraw(i);
		}
	}

	//画嵌套三角形
	public static void nestDraw(int h) {
		int level = h / 5 + 1;
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < 2 * h - 1; x++) {
				boolean b = judgeTriangle(h-1, 0, h, x, y);
				int count = 1;
				while(count<level){
					b = b || judgeTriangle(h-1, (count*3), h-count*5, x, y);
					count++;
				}
				System.out.print(b?"* ":"  ");
			}
			System.out.println();
		}
	}
	
	
	//画空心三角形
	public static void draw(int tranX, int tranY, int h) {
		for (int y = 0; y < h + tranY; y++) {
			for (int x = 0; x < 2 * h - 1 + tranX; x++) {// 这里x的范围判断好像不太对,回头再看一下
				boolean b = judgeTriangle(tranX, tranY, h, x, y);
				System.out.print(b ? "* " : "  ");
			}
			System.out.println();
		}

	}

	
	//三角形的逻辑判断,可以画出任意位置的空心三角形
	public static boolean judgeTriangle(int tranX, int tranY, int h, int x,
			int y) {
		boolean side = Math.abs(x - tranX) == y - tranY
				&& Math.abs(x - tranX) <= h - 1;
		boolean bottom = y == h + tranY - 1 && Math.abs(x - tranX) <= y - tranY;
		return side || bottom;
	}

}
