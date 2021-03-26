# Kruskal-s-algorithm-Implementation-in-Java
import com.sun.javafx.geom.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class KruskalsImplentation
{

    static Scanner s = new Scanner(System.in);
    static int[] arr;
    static int[] size;
    static int n1;
    KruskalsImplentation(int n1)
    {
        this.n1=n1;
        arr=new int[n1];
        size=new int[n1];
        for(int i=0;i<arr.length;i++)
        {
            arr[i]=i;
            size[i]=1;
        }
    }
    public static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight)
        {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    public static int root(int a)
    {
        while(arr[a] != a)           //chase parent of current element until it reaches root.
        {
            a = arr[a];
        }
        return a;
    }
    public static boolean Find(int u,int v)
    {
        if (root(u)==root(v))
            return true;
        else
            return false;
    }
    public  static void Weighted_Union(int u,int v)
    {
        int root_A = root(u);
        int root_B = root(v);
        if(size[root_A] < size[root_B ])
        {
            arr[ root_A ] = arr[root_B];
            size[root_B] += size[root_A];
        }
        else
        {
            arr[ root_B ] = arr[root_A];
            size[root_A] += size[root_B];
        }

    }
    public static void main(String[] args)
    {
        KruskalsImplentation k = new KruskalsImplentation(10);
        System.out.print("Enter no of vertices and edge :  ");
        int V = s.nextInt();
        int E = s.nextInt();
        System.out.println();

        ArrayList<Edge> array = new ArrayList<Edge>();
        System.out.println("Enter Graph Details ");
        for (int i = 0; i < E; i++)
        {
            System.out.print("Enter source and destination of Edge :  " + (i + 1) + "  :  ");
            int source = s.nextInt();
            int destination = s.nextInt();
            System.out.print("Enter weight :  ");
            int weight = s.nextInt();
            array.add(new Edge(source, destination, weight));
            System.out.println();
        }
        int l = 0;
        Collections.sort(array, new Comparator<Edge>()
                {
                    @Override
                    public int compare(Edge o1, Edge o2)
                    {
                        Integer i1 = (Integer) o1.weight;
                        Integer i2 = (Integer) o2.weight;
                        return i1.compareTo(i2);
                    }
                }
        );
        System.out.println( "Weight        Source          Destination   ");
        for(int i=0;i<E;i++)
        {
            int source = array.get(l).source;
            int destination=array.get(l).destination;
            int weight=array.get(l).weight;
            if(weight<=9)
            {
                System.out.println(" "+weight+"             "+source+"                "+destination);
            }
            else
            System.out.println(" "+weight+"            "+source+"                "+destination);
            l++;
        }

        int count=0;
        int i=0;
        ArrayList<Edge> al=new ArrayList<Edge>();
        while (count != V-1)
        {
            Edge a= array.get(i);
            //System.out.println("i = "+i+" weight : "+a.weight + "   source :  "+a.source+ "  destination :  "+a.destination);
            int source=a.source;
            int destination=a.destination;
            boolean b=Find(source,destination);
            if(b == true)
            {
             //   System.out.println("Edge with source  " + a.source + "  and  destination " + a.destination + " forms cycle .");
            }
            else
                {
                Weighted_Union(source, destination);
                al.add(a);
                count++;
            }
            i++;
           // System.out.println();
           // System.out.println("");
        }

        System.out.println("");
        System.out.println("Edges of MINIMUM spanning tree : ");
        System.out.println( "Weight        Source          Destination   ");
        int totalweight=0;
        int mst_ni1=0;
        for (int k1=0;k1<al.size();k1++)
        {
            int source = al.get(k1).source;
            int destination=al.get(k1).destination;
            int weight=al.get(k1).weight;
            totalweight+=weight;
            if(weight<=9)
            {
                System.out.println(" "+weight+"             "+source+"                "+destination);
            }
            else
                System.out.println(" "+weight+"            "+source+"                "+destination);
        }
        System.out.println();
        System.out.println("Total weight of minimum spanning tree is :  "+totalweight);
    }
}
// By Ravi Kumar Gupta
