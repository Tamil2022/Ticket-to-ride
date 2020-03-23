import java.util.*;
class Node
{
	int v,val;
	Node(int a,int b)
	{
		v=a;
		val=b;
	}
}
class Ticket
{
	static ArrayList<ArrayList<Node>> path,cost;  //adjacency list for distance and cost
	static int n,m,ans;
	static int[] visit;                             //visit[i] will be 0 if the city is not yet visited
	static void dfs(int cur,int st,int sd)
	{
		if(st-sd>ans)
			ans=st-sd;
		int u,temp;
		for(int i=0;i<path.get(cur).size();i++)
		{
			u=path.get(cur).get(i).v;
			if(visit[u]==0)                            //if the city you not visited
			{
				visit[u]=1;                             //mark it as visited
				temp=0;
				for(int j=0;j<cost.get(u).size();j++) //for all the cost edge associated with you
				{
					if(visit[cost.get(u).get(j).v]==1)  //add the cost if the other node is also visited
						temp+=cost.get(u).get(j).val;
				}
				dfs(u,st+temp,sd+path.get(cur).get(i).val);  //recur to the next node
				visit[u]=0;
			}
		}
	}
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		path=new ArrayList<ArrayList<Node>>();
		cost=new ArrayList<ArrayList<Node>>();
		n=s.nextInt();
		visit=new int[n];
		int a,b,c;
		for(int i=0;i<n;i++)
		{
			path.add(new ArrayList<Node>());
			cost.add(new ArrayList<Node>());
			visit[i]=0;
		}
		//get the input and store them in the adjacency list
		for(int i=0;i<n-1;i++)
		{
			a=s.nextInt();
			b=s.nextInt();
			c=s.nextInt();
			path.get(--a).add(new Node(--b,c));
			path.get(b).add(new Node(a,c));
		}
		m=s.nextInt();
		for(int i=0;i<m;i++)
		{
			a=s.nextInt();
			b=s.nextInt();
			c=s.nextInt();
			cost.get(--a).add(new Node(--b,c));
			//if u and v are same,don't add the cost twice
			if(a!=b)
				cost.get(b).add(new Node(a,c));
		}
		ans=0;
		//the path can start from any city
		for(int i=0;i<n;i++)
		{
			visit[i]=1;
			dfs(i,0,0);
			visit[i]=0;
		}
		System.out.println(ans);

	}
}
