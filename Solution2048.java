//============================================================================
// Name        : Solution2048.java
// Author      : Rakesh Malviya
// Version     : First
// Copyright   : Don't change the above and below commented information
// Description : My 2048 Game
// Note        : You may find "bugX" (behaviour not similar to original game).
//               Such bugX are features :)
//============================================================================



import java.util.*;
import java.io.*;


class Game2048
{
	private int[][] matrix;
	private int score;
	Random rand;
	Game2048()
	{
		score = 0;
		rand = new Random();
		matrix = new int[4][];

		for(int i=0; i< 4;i++)
		{
			matrix[i] = new int[4];
			for(int j=0; j< 4;j++)
			{

				matrix[i][j]=0;
			}
		}

		generate();
		generate();
	}

	private void printHelp()
	{
		System.out.println("Help:");
		System.out.println("-----------");
		System.out.println("Move up    : w");
		System.out.println("Move down  : s");
		System.out.println("Move left  : a");
		System.out.println("Move right : d");
		System.out.println("Exit       : x");
		System.out.println();
		System.out.println();

	}

	private int getNextNum()
	{
		int temp = rand.nextInt(3);
		if(temp==0 || temp==1)
		{
			return 2;
		}
		else
		{
			return 4;
		}
	}

	int generate()
	{
		int posCheck;
		int position = 	rand.nextInt(16);
		int num = getNextNum();
		posCheck = position;
		while(position>=0)
		{
			int i=0;
			int j=0;

			for(i=0;i<4 && (position>=0);i++)
			{
				for(j=0;j<4 && (position>=0);j++)
				{
					if(matrix[i][j]==0)
					{
						if(position==0)
						{
							matrix[i][j] = num;
						}
						position--;
					}
				}
			}


			if(position==posCheck)
				return -1;


			if(i==4 && j==4)
			{
				i=0;
				j=0;
			}
		}

		return 0;
	}

	void print()
	{
		printHelp();
		System.out.println("Score : "+score);
		System.out.println();
		for(int i=0; i< 4;i++)
		{
			System.out.println();
			System.out.println(" ------------------------------");
			System.out.print("|");
			for(int j=0; j< 4;j++)
			{
				if(matrix[i][j]!=0)
					System.out.printf("%5d | ",matrix[i][j]);
				else
					System.out.printf("      | ",matrix[i][j]);
			}
		}
		System.out.println();
		System.out.println(" ------------------------------");
		System.out.println();
	}

	int up()
	{
		int i=0;
		int j=0;
		int retVal = 0;
		for(j=0;j<4;j++)
		{
			int temp = 0;
			int index = 0;

			for(i=0;i<4;i++)
			{
				//System.out.printf("\n\n[%d,%d]\n",i,j);
				//print();
				if(matrix[i][j]!=0)
				{
					if(temp==0)
					{
						temp = matrix[i][j];
						matrix[i][j] = 0;
					}
					else
					{
						if(temp == matrix[i][j])
						{
							matrix[index][j] = temp*2;
							score += temp*2;
							if(temp*2 == 2048)
								retVal = 2048;
							index++;
							temp = 0;
							matrix[i][j] = 0;
						}
						else
						{
							matrix[index][j] = temp;
							index++;
							temp = matrix[i][j];
							matrix[i][j]=0;
						}
					}
				}
			}

			if(temp!=0)
				matrix[index][j]= temp;
		}
		return retVal;
	}

	int down()
	{
		int i=0;
		int j=0;
		int retVal = 0;
		for(j=0;j<4;j++)
		{
			int temp = 0;
			int index = 0;

			for(i=0;i<4;i++)
			{
				//System.out.printf("\n\n[%d,%d]\n",i,j);
				//print();
				if(matrix[3-i][j]!=0)
				{
					if(temp==0)
					{
						temp = matrix[3-i][j];
						matrix[3-i][j] = 0;
					}
					else
					{
						if(temp == matrix[3-i][j])
						{
							matrix[3-index][j] = temp*2;
							score += temp*2;
							if(temp*2 == 2048)
								retVal = 2048;
							index++;
							temp = 0;
							matrix[3-i][j] = 0;
						}
						else
						{
							matrix[3-index][j] = temp;
							index++;
							temp = matrix[3-i][j];
							matrix[3-i][j]=0;
						}
					}
				}
			}

			if(temp!=0)
				matrix[3-index][j]= temp;
		}

		return retVal;
	}

	int left()
		{
			int i=0;
			int j=0;
			int retVal = 0;
			for(i=0;i<4;i++)
			{
				int temp = 0;
				int index = 0;

				for(j=0;j<4;j++)
				{
					//System.out.printf("\n\n[%d,%d]\n",i,j);
					//print();
					if(matrix[i][j]!=0)
					{
						if(temp==0)
						{
							temp = matrix[i][j];
							matrix[i][j] = 0;
						}
						else
						{
							if(temp == matrix[i][j])
							{
								matrix[i][index] = temp*2;
								score += temp*2;
								if(temp*2 == 2048)
									retVal = 2048;
								index++;
								temp = 0;
								matrix[i][j] = 0;
							}
							else
							{
								matrix[i][index] = temp;
								index++;
								temp = matrix[i][j];
								matrix[i][j]=0;
							}
						}
					}
				}

				if(temp!=0)
					matrix[i][index]= temp;
			}

			return retVal;
		}

		int right()
			{
				int i=0;
				int j=0;
				int retVal = 0;
				for(i=0;i<4;i++)
				{
					int temp = 0;
					int index = 0;

					for(j=0;j<4;j++)
					{
						//System.out.printf("\n\n[%d,%d]\n",i,j);
						//print();
						if(matrix[i][3-j]!=0)
						{
							if(temp==0)
							{
								temp = matrix[i][3-j];
								matrix[i][3-j] = 0;
							}
							else
							{
								if(temp == matrix[i][3-j])
								{
									matrix[i][3-index] = temp*2;
									score += temp*2;
									if(temp*2 == 2048)
										retVal = 2048;
									index++;
									temp = 0;
									matrix[i][3-j] = 0;
								}
								else
								{
									matrix[i][3-index] = temp;
									index++;
									temp = matrix[i][3-j];
									matrix[i][3-j]=0;
								}
							}
						}
					}

					if(temp!=0)
						matrix[i][3-index]= temp;
				}
				return retVal;
			}



}

public class Solution2048
{



	static void cls()
	{
		for(int clear = 0; clear < 100; clear++) {
 		   System.out.println("\b") ;
		}
	}



	public static void main(String args[])
	{
		Game2048 game = new Game2048();
		game.print();

		char c = ' ';

		while(c!='x')
		{

			try
			{
				c = (char)(System.in.read());
			}
			catch(IOException e)
			{
				System.out.println("Error reading char!");
			}

			if(c=='w') //UP
			{
				cls();
				int retVal = game.up();

				if(retVal == 2048)
					System.out.println("Congrats you made 2048 :)");

				int temp = game.generate();
				if(temp==-1)
				{
					System.out.println("Game over :(");
					break;
				}
				game.print();

			}

			else if(c=='a') //left
			{
				cls();
				int retVal = game.left();

				if(retVal == 2048)
					System.out.println("Congrats you made 2048 :)");


				int temp = game.generate();
				if(temp==-1)
				{
					System.out.println("Game over :(");
					break;
				}
				game.print();

			}

			else if(c=='d') // right
			{
				cls();
				int retVal = game.right();

				if(retVal == 2048)
					System.out.println("Congrats you made 2048 :)");

				int temp = game.generate();
				if(temp==-1)
				{
					System.out.println("Game over :(");
					break;
				}
				game.print();

			}

			else if(c=='s') // down
			{
				cls();
				int retVal = game.down();

				if(retVal == 2048)
					System.out.println("Congrats you made 2048 :)");

				int temp = game.generate();
				if(temp==-1)
				{
					System.out.println("Game over :(");
					break;
				}
				game.print();

			}

		}
	}

}
