public class l001 {

  // A) printAll path -> floodfill we use dfs
  public static int floodFill(
    int sr,
    int sc,
    int er,
    int ec,
    int[][] dir,
    String[] dirS,
    boolean[][] vis,
    String psf
  ) {
    if (sr == er && sc == ec) {
      System.out.println(psf);
      return 1;
    }

    int count = 0;
    vis[sr][sc] = true;
    for (int d = 0; d < dir.length; d++) {
      int r = sr + dir[d][0];
      int c = sc + dir[d][1];

      if (r >= 0 && c >= 0 && r <= er && c <= ec && !vis[r][c]) {
        //   !vis[r][c]) -> means jidr jare hai vo false ho
        count += floodFill(r, c, er, ec, dir, dirS, vis, psf + dirS[d] + " ");
      }
    }
    vis[sr][sc] = false;
    return count;
  }

  //B) Shortest and longestPath return
  public static class pathPair {

    int len = 0; //len -> means jiski sabse jyda length hogi uske base par compare krege and path return krege
    String psf = "";
    int count = 0;

    pathPair(int len, String psf,int count) {
      this.len = len;
      this.psf = psf;
      this.count = count;
    }
  }

  public static pathPair floodFill_longestPath(
    int sr,
    int sc,
    int er,
    int ec,
    int[][] dir,
    String[] dirS,
    boolean[][] vis
  ) {
    //faith -> sr + 1 to dest tak longest path dedenge and uski length then usko sabse compare krege,
    if (sr == ec && sc == ec) {
      return new pathPair(0, "", 1); //1 means 1 path mila
    }

    vis[sr][sc] = true;

    pathPair myAns = new pathPair(0, "", 0);

    for (int d = 0; d < dir.length; d++) {
      int r = sr + dir[d][0];
      int c = sc + dir[d][1];

      if (r > 0 && c > 0 && r <= er && c <= ec && !vis[r][c]) {
        pathPair recAns = floodFill_longestPath(r, c, er, ec, dir, dirS, vis);
        if (recAns.len + 1 > myAns.len) {
          myAns.len = recAns.len + 1;
          myAns.psf = dirS[d] + recAns.psf; //dirS[d] -> issse direction pta lgegi konsi direction me call lgai
        }
        myAns.count += recAns.count;
      }
    }
    vis[sr][sc] = false;
    return myAns;
  }

   public static pathPair floodFill_shortestPath(
    int sr,
    int sc,
    int er,
    int ec,
    int[][] dir,
    String[] dirS,
    boolean[][] vis
  ) {
    //faith -> sr + 1 to dest tak longest path dedenge and uski length then usko sabse compare krege,
    if (sr == ec && sc == ec) {
      return new pathPair(0, "", 1); //1 means 1 path mila
    }

    vis[sr][sc] = true;

    pathPair myAns = new pathPair((int)1e8, "", 0);

    for (int d = 0; d < dir.length; d++) {
      int r = sr + dir[d][0];
      int c = sc + dir[d][1];

      if (r > 0 && c > 0 && r <= er && c <= ec && !vis[r][c]) {
        pathPair recAns = floodFill_shortestPath(r, c, er, ec, dir, dirS, vis);
        if (recAns.len + 1 < myAns.len) {
          myAns.len = recAns.len + 1;
          myAns.psf = dirS[d] + recAns.psf; //dirS[d] -> issse direction pta lgegi konsi direction me call lgai
        }
        myAns.count += recAns.count;
      }
    }
    vis[sr][sc] = false;
    return myAns;
  }

  public static void main(String[] args) {
    int n = 4, m = 4;
    int[][] dir = {
      { -1, 0 },
      { -1, 1 },
      { 0, 1 },
      { 1, 1 },
      { 1, 0 },
      { 1, -1 },
      { 0, -1 },
      { -1, -1 },
    };
    String[] dirS = { "u", "e", "l", "s", "d", "w", "r", "n" };
    boolean[][] vis = new boolean[n][m];
    // System.out.println(floodFill(0, 0, n - 1, m - 1, dir, dirS, vis, ""));
   
     pathPair ans =  floodFill_shortestPath(0, 0, n - 1, m - 1, dir, dirS, vis);
    System.out.println("path:" + ans.psf + "\n" + "len:"+ans.len + "\n" + "count" +ans.count);
  }
}
