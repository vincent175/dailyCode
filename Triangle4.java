class Triangle4 
{

	//public static int h = 0,w = 0;
	//public static int deviation = 0;

	public static void main(String[] args) 
	{
		printStar(18);
	}


	public static void printStar(int n)
	{
		//h,w为画单个三角形画布的长和宽
		//deviation为偏移量(也就是三角形定点所在的横坐标)
		int h = 0,w = 0;
		int deviation = 0;
		for (h=0;h<n ;h++ )
		{
			w = h * 2;
			deviation = w / 2;

			draw(h,w,deviation);
		}
	}


	/*
		调用figure方法画出 嵌套的三角形
	*/

	public static void draw(int h,int w,int deviation)
	{
		for (int y=0;y<=h ;y++ )
		{
			for (int x=0;x<=w ;x++ )
			{
				//z是三角形纵向平移量
				int z = 0;
				boolean b = figure(x-deviation,y,0,h,w,deviation);
				
				//判断需要嵌套几个三角形
				//3是相嵌套的两个三角形，两个定点的距离
				//5是顶部距离和底部距离相加的和
				for (int i=1;(z=y-(i*3))>=0 ;i++ )
				{
					b = b || figure(x-deviation,z,5*i,h,w,deviation);
				}

				//画出三角形
				System.out.print(b?"* ":"  ");
			}
			System.out.println();
		}
	}

	/*

		画出单个空心三角形
 
		x为横坐标
		y为纵坐标
		z为三角形向下平移量
		(Math.abs(x)==y)&&(y<=h-z) 控制的是画出三角形的两个腰
		(y == h-z)&&(Math.abs(x)<=y) 控制的是画出三角形的底
	*/
	public static boolean figure(int x,int y,int z,int h,int w,int deviation)
	{
		return (Math.abs(x)==y)&&(y<=h-z)||((y == h-z)&&(Math.abs(x)<=y));
	}
}
