import java.util.ArrayList;

public class l001 {

  public static class Edge {

    int v = 0, w = 0;

    Edge(int v, int w) {
      this.v = v;
      this.w = w;
    }
  }

  public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
    graph[u].add(new Edge(v, w));
    graph[v].add(new Edge(u, w));
  }

  //
  public static void display(ArrayList<Edge>[] graph) {
    int N = graph.length;
    for (int i = 0; i < N; i++) { //travelling on every vtex
      System.out.print(i + " -> ");
      for (Edge e : graph[i]) { //graph[i] isse mili hmko ik arrayList usme edge store hai
        System.out.print("(" + e.v + "," + e.w + ")");
      }
      System.out.println();
    }
  }

  //time complexity -> O()
  public static int findEdge(ArrayList<Edge>[] graph, int u, int v) { //return the index
      ArrayList<Edge> list = graph[u]; //bring the whole list jo chaiye thi
      for (int i = 0; i < list.size(); i++) {
          Edge e = list.get(i); // eg: 0 -> (1,10) (3,10)
          if (e.v == v) {
              return i;
          }
      }
      return -1;
  }
  
  public static void removeEdge(ArrayList<Edge>[] graph, int u, int v) {
      int idx = findEdge(graph, u, v);
      graph[u].remove(idx);

      int idx2 = findEdge(graph, v, u);
      graph[v].remove(idx2);
    }


  //has Path
  public static boolean dfs_findPath(
    ArrayList<Edge>[] graph,
    int src,
    int dest,
    boolean[] vis
  ) {
    if (src == dest) {
      return true;
    }

    vis[src] = true;
    boolean res = false;
    for (Edge e : graph[src]) { // 0 -> (1,10) (3,10) nbr pr lgaya 1,3
      if (!vis[e.v]) {
        res = res || dfs_findPath(graph, e.v, dest, vis);
      }
    }
    return res;
  }

  public static int printAllPath(
    ArrayList<Edge>[] graph,
    int src,
    int dest,
    String psf,
    int wsf,
    boolean[] vis
  ) {
      if (src == dest) {
          System.out.println(psf + dest + "@" + wsf);
          return 1;
      }
      int count = 0;
      vis[src] = true;
      for (Edge e : graph[src]) {
          if (!vis[e.v]) {
              count += printAllPath(graph, e.v, dest, psf + src, wsf + e.w, vis);
          }
      }
      vis[src] = false;
      return count;
  }

  public static void dfs_GCC(ArrayList<Edge>[] graph, int src, boolean[] vis) {
      vis[src] = true;
      for (Edge e : graph[src]) {
          if (!vis[e.v]) {
              dfs_GCC(graph, e.v, vis);
          }
      }
  }
  
  public static void gcc(ArrayList<Edge>[] graph, boolean[] vis) {
      int components = 0;
      int N = graph.length;
      for (int i = 0; i < N; i++) { //call gcc from every vtex
          if (!vis[i]) {
              components++;
              dfs_GCC(graph, i, vis);
          }

      }
      System.out.println(components);
      
  }

  //construct a graph
  public static void constructGraph() {
    int N = 7;
    ArrayList<Edge>[] graph = new ArrayList[N];
    for (int i = 0; i < N; i++) {
      graph[i] = new ArrayList<>();
    }
    addEdge(graph, 0, 1, 10);
    addEdge(graph, 3, 0, 10);
    addEdge(graph, 1, 2, 10);
    addEdge(graph, 2, 3, 40);
    addEdge(graph, 3, 4, 2);
    addEdge(graph, 4, 5, 2);
    addEdge(graph, 5, 6, 3);
    addEdge(graph, 4, 6, 8);

    display(graph);

    boolean[] vis = new boolean[N];
    // removeEdge(graph, 3, 4);
    // System.out.println(dfs_findPath(graph, 0, 6, vis));
    // System.out.println(printAllPath(graph, 0, 6, "", 0, vis));
    // gcc(graph, vis);
  }

  public static void main(String[] args) {
    constructGraph();
  }
}
