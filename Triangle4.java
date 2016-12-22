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
		//h,wΪ�����������λ����ĳ��Ϳ�
		//deviationΪƫ����(Ҳ���������ζ������ڵĺ�����)
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
		����figure�������� Ƕ�׵�������
	*/

	public static void draw(int h,int w,int deviation)
	{
		for (int y=0;y<=h ;y++ )
		{
			for (int x=0;x<=w ;x++ )
			{
				//z������������ƽ����
				int z = 0;
				boolean b = figure(x-deviation,y,0,h,w,deviation);
				
				//�ж���ҪǶ�׼���������
				//3����Ƕ�׵����������Σ���������ľ���
				//5�Ƕ�������͵ײ�������ӵĺ�
				for (int i=1;(z=y-(i*3))>=0 ;i++ )
				{
					b = b || figure(x-deviation,z,5*i,h,w,deviation);
				}

				//����������
				System.out.print(b?"* ":"  ");
			}
			System.out.println();
		}
	}

	/*

		������������������
 
		xΪ������
		yΪ������
		zΪ����������ƽ����
		(Math.abs(x)==y)&&(y<=h-z) ���Ƶ��ǻ��������ε�������
		(y == h-z)&&(Math.abs(x)<=y) ���Ƶ��ǻ��������εĵ�
	*/
	public static boolean figure(int x,int y,int z,int h,int w,int deviation)
	{
		return (Math.abs(x)==y)&&(y<=h-z)||((y == h-z)&&(Math.abs(x)<=y));
	}
}
