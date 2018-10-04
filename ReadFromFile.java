import java.util.*;
import java.io.*;
import java.lang.Math.*;
public class ReadFromFile 
{
	public static void main(String args[]) throws IOException
	{
		 char arr[][] = new char[1000][1000];
		 int rr = 0 , cc = 0;
		 BufferedReader fileInput = new BufferedReader(new FileReader("C:\\Users\\AN20023993\\Downloads\\map.txt"));
		 String s;
		 while ((s = fileInput.readLine()) != null) 
		 {
			 cc = 0;
			 
			 for (char c : s.toCharArray()) 
			 {
				 if(c!=' ')
				 {
					 arr[rr][cc++]=c;
				 }
		     }
			 rr++;
		  }
		    fileInput.close();
		    //display
		  for(int i = 0 ; i<rr ;i++)
		  {
			  for(int j = 0 ; j<cc ;j++)
			  {
				  System.out.printf("%-2c", arr[i][j]);
			  }
			  System.out.println();
		  }
		  System.out.println();
		  
		    //finding start and end point
		    int sx= 0 ,sy=0,ex=0,ey=0,m1=0,m2=0;
		 for(int i=0;i<rr;i++)
		 {
			 for(int j=0;j<cc;j++)
			 {
				 if(arr[i][j] == 'S')
				 {
					 sx = i;
					 sy = j;
				 }
				 if(arr[i][j] == 'E')
				 {
					 ex = i;
					 ey = j;
					 m1 = ex;
					 m2 = ey;
				 }

			 }
		 }
		 
		 ReadFromFile r = new ReadFromFile();
		 int dis[][] = new int[arr.length][arr[0].length];
		 boolean visited[][]=new boolean[arr.length][arr[0].length];

		 int dist = r.findShortestPathBFS(arr,sx,sy,ex,ey,rr,cc, dis,visited);
		 
		 for(int i = 0 ; i<rr ;i++)
		 {
			 for(int j = 0 ; j<cc ;j++)
			 {
				 if(dis[i][j]>0 && dis[i][j]!=Integer.MAX_VALUE)
						 {
					 		arr[i][j] = '"';
					 		if(dis[i][j]==1)
					 			arr[i][j]='S';
					 	  }

			 }
		 }
		 arr[m1][m2]='E';
		 
		 r.actual_path_taken(arr, sx, sy, ex, ey, rr, cc, dist);
	}
	
	public void actual_path_taken(char arr[][], int sx, int sy,int ex,int ey,int rr,int cc,int dist)
	{
		for(int i = 0 ; i<rr ;i++)
		{
			for(int j = 0; j<cc ;j++)
			{
				System.out.printf("%-2c", arr[i][j]);
			}
			System.out.println();
		}
	}
	
	
		//distance
	int findShortestPathBFS(char arr[][], int sx, int sy,int ex,int ey,int rr,int cc,int dis[][],boolean visited[][])
	{
		if(arr[sx][sy]=='E') 
			return 0;
		int moveX[]=new int[]{0,0,1,-1};
		int moveY[]=new int[]{1,-1,0,0};

		dis[sx][sy]=1;
		
		PriorityQueue<QNode> qNodes = new PriorityQueue<QNode>(10,new NodeComparator());
		qNodes.add(new QNode(sx,sy,1,Math.abs(ex-sx)+Math.abs(ey-sy) ));
		while(!qNodes.isEmpty())
		{
			QNode currNode=qNodes.remove();
			int currX=currNode.x;
			int currY=currNode.y;
			int currDistance = currNode.distance;
			visited[currX][currY]=true;

			if(arr[currX][currY]=='E') 
			{
				while((ex!=sx || ey!=sy))
				{
					int x,y;
					x=ex;
					y=ey;
					int nx=dis[ex][ey];
					if(dis[ex-1][ey]<nx && dis[ex-1][ey]!=0 )
						{
							x=ex-1;
							y=ey;
							nx=dis[ex-1][ey];
						}
			
					if(dis[ex+1][ey]<nx && dis[ex+1][ey]!=0 )
					{
						x=ex+1;
						y=ey;
						nx=dis[ex+1][ey];
					}
					
					if(dis[ex][ey-1]<nx && dis[ex][ey-1]!=0 )
					{
						x=ex;
						y=ey-1;
						nx=dis[ex][ey-1];
					}
					if(dis[ex][ey+1]<nx && dis[ex][ey+1]!=0 )
					{
						x=ex;
						y=ey+1;
						nx=dis[ex][ey+1];
					}
					
					
					if(nx==1)
						{
							dis[ex][ey]=Integer.MAX_VALUE;
							break;
						}
					ex=x;
					ey=y;
					dis[x][y]=Integer.MAX_VALUE;
					arr[x][y] = '*';

				}
				
				return currDistance;
			}
			
			for (int i = 0; i < moveX.length; i++) 
			{
				int newX=currX+moveX[i];
				int newY=currY+moveY[i];

				if(newX>=0 && newX<rr && newY>=0 &&newY<cc && visited[newX][newY]==false && arr[newX][newY]!='W' && arr[newX][newY]!='w')
				{
					qNodes.add(new QNode(newX,newY,currDistance+1,Math.abs(ex-newX)+Math.abs(ey-newY)  ));
					visited[newX][newY] = true;
					dis[newX][newY] = currDistance+1   ;
				}
			}
			
		}
		
		return -1;
		
	}
			
	
}


class NodeComparator implements Comparator<QNode>
{ 
    
    // Overriding compare()method of Comparator  
                // for ascending order of f i.e. f = distance+h 
    public int compare(QNode s1, QNode s2) { 
        if (s1.distance+s1.h < s2.distance+s2.h) 
            return -1; 
        else if (s1.distance+s1.h > s2.distance+s2.h) 
            return 1; 
                        return 0; 
        } 
} 
