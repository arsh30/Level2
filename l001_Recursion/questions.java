import java.util.Arrays;

public class questions {

  //ques1 -> https://practice.geeksforgeeks.org/problems/special-matrix4201/1
  //gfg -> apply dp memoization

  static int mod = (int) 1e9 + 7;

  public static int dfs(
    int sr,
    int sc,
    int er,
    int ec,
    boolean[][] vis,
    int[][] dp
  ) {
    if (sr == er && sc == ec) return dp[sr][sc] = 1; //step 1

    if (dp[sr][sc] != -1) return dp[sr][sc]; //step2

    int count = 0;
    if (sc + 1 <= ec && !vis[sr][sc + 1]) {
      count = (count % mod + dfs(sr, sc + 1, er, ec, vis, dp) % mod) % mod;
    }
    if (sr + 1 <= er && !vis[sr + 1][sc]) {
      count = (count % mod + dfs(sr + 1, sc, er, ec, vis, dp) % mod) % mod;
    }
    return dp[sr][sc] = count; //step3
  }

  public int FindWays(int n, int m, int[][] blocked_cells) { //it is a flood fill
    n++;
    m++;
    boolean[][] vis = new boolean[n][m];
    //fill the blocked cell in vis
    for (int[] b : blocked_cells) { //iss line se 1,2 aaya
      vis[b[0]][b[1]] = true; //1 or 2 ko true mark kra
    }

    if (vis[1][1] || vis[n - 1][m - 1]) return 0;

    int[][] dp = new int[n][m];
    for (int[] d : dp) {
      Arrays.fill(d, -1);
    }
    return dfs(1, 1, n - 1, m - 1, vis, dp);
  }

  //ques2 -> https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1

  //ques3 -> leet code ques 39
  public int combinationSum(
    int[] arr,
    int tar,
    int idx,
    List<Integer> smallAns,
    List<List<Integer>> res
  ) {
    if (tar == 0) {
      // res.add(smallAns);  //if we simply add like in this, return type recursion to vo base case me add krega
      List<Integer> base = new ArrayList<>(smallAns);
      res.add(base);
      return 1;
    }
    int count = 0;
    for (int i = idx; i < arr.length; i++) {
      if (tar - arr[i] >= 0) {
        smallAns.add(arr[i]); //upr jate waqt add krege
        count += combinationSum(arr, tar - arr[i], i, smallAns, res);
        smallAns.remove(smallAns.size() - 1); //last wala remove krra
      }
    }
    return count;
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<Integer> smallAns = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    combinationSum(candidates, target, 0, smallAns, res);
    return res;
  }

  //leetcode 40
  public int combinationSum2(
    int[] arr,
    int tar,
    int idx,
    List<Integer> smallAns,
    List<List<Integer>> res
  ) {
    if (tar == 0) {
      List<Integer> base = new ArrayList<>(smallAns);
      res.add(base);
      return 1;
    }

    int count = 0;
    //to avoid duplicacy we take boolean[] vis to hm arguments me nhi lenge, because arguments wala jo visited hai vo permanent block krta hai ie depth visited , but we want width visited

    boolean[] vis = new boolean[51]; //constraint dekh kr pta lga ki boolean kitne size ka bnana
    for (int i = idx; i < arr.length; i++) {
      if (!vis[arr[i]] && tar - arr[i] >= 0) {
        vis[arr[i]] = true; //isko wapas flase mark krne ki need nhi hai, to make width visited
        smallAns.add(arr[i]);
        count += combinationSum2(arr, tar - arr[i], i + 1, smallAns, res);
        smallAns.remove(smallAns.size() - 1);
      }
    }
    return count;
  }

  //to avoid duplicacy -> approach 2 -> we take -1 and compare kra next next element se
  public int combinationSum2_01(
    int[] arr,
    int tar,
    int idx,
    List<Integer> smallAns,
    List<List<Integer>> res
  ) {
    if (tar == 0) {
      List<Integer> base = new ArrayList<>(smallAns);
      res.add(base);
      return 1;
    }

    int count = 0;
    int prev = -1;
    for (int i = idx; i < arr.length; i++) {
      if (prev != arr[i] && tar - arr[i] >= 0) {
        smallAns.add(arr[i]);
        count += combinationSum2_01(arr, tar - arr[i], i + 1, smallAns, res);
        smallAns.remove(smallAns.size() - 1);
      }
      prev = arr[i];
    }
    return count;
  }

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<Integer> smallAns = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    //ans is in sorted order, so array ko sort krke bhjege
    Arrays.sort(candidates);

    combinationSum2(candidates, target, 0, smallAns, res);
    return res;
  }

  //note: whenever duplicacy is not allowed then we use mutiple ways:
  /* 1) take boolean vis array -> ye agar argument me pass kro to depth visited hai means uss number ko puri trah block krdega
      
      2) note: agar duplicate bchane ho to always hm usko width pr block krege eg: 
      boolean[] vis=  new boolean[51];
      for() {
        if(!vis[arr[i]]) && tar- arr[i] >= 0){  //!vis[arr[i]] -> agr false hai
          vis[arr[i]] = true; isko true mark krege, or isme wapas jate hoye false mark krne ki need nhi hai       
          }
      }
      */

  //ques -> 77
  // k -> target, n-> is array assume
  public int combine(
    int n,
    int k,
    int idx,
    List<Integer> smallAns,
    List<List<Integer>> res
  ) {
    if (k == 0) {
      List<Integer> base = new ArrayList<>(smallAns);
      res.add(base);
      return 1;
    }
    int count = 0;
    for (int i = idx; i <= n; i++) {
      smallAns.add(i);
      count += combine(n, k - 1, i + 1, smallAns, res);
      smallAns.remove(smallAns.size() - 1);
    }
    return count;
  }

  public List<List<Integer>> combine(int n, int k) {
    List<Integer> smallAns = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    combine(n, k, 1, smallAns, res);
    return res;
  }

  //216
  //n basically target,  k -> 3[3 digit no bnana]
  public int combinationSum3(
    int tar,
    int k,
    int idx,
    List<Integer> smallAns,
    List<List<Integer>> res
  ) {
    if (k == 0 || tar == 0) {
      if (tar == 0 && k == 0) {
        List<Integer> base = new ArrayList<>(smallAns);
        res.add(base);
        return 1;
      }
      return 0;
    }

    int count = 0;
    for (int i = idx; i <= 9; i++) {
      if (tar - i >= 0) {
        smallAns.add(i);
        count += combinationSum3(tar - i, k - 1, i + 1, smallAns, res);
        smallAns.remove(smallAns.size() - 1);
      }
    }
    return count;
  }

  public List<List<Integer>> combinationSum3(int k, int tar) {
    List<Integer> smallAns = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    combinationSum3(tar, k, 1, smallAns, res);
    return res;
  }

  //ques 46 ->
  //for unique -> we take a visited matrix
  public int permut(
    int[] arr,
    int tne,
    List<Integer> smallAns,
    List<List<Integer>> res
  ) {
    if (tne == 0) {
      List<Integer> base = new ArrayList<>(smallAns);
      res.add(base);
      return 1;
    }

    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      //permutation loop starts from 0 and combination loop starts from idx
      if (arr[i] > -11) { // because duplicate print ni krre so mark krke jare h, constraint ko dekh kr vimp
        int val = arr[i]; //step 1
        arr[i] = -11; //step 2
        smallAns.add(val);

        count += permut(arr, tne - 1, smallAns, res);

        smallAns.remove(smallAns.size() - 1);
        arr[i] = val; //step 3
      }
    }
    return count;
  }

  public List<List<Integer>> permute(int[] nums) {
    List<Integer> smallAns = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    permut(nums, nums.length, smallAns, res);
    return res;
  }

  //ques 47;
  public int permuteUnique(
    int[] arr,
    int tel,
    List<Integer> smallAns,
    List<List<Integer>> res
  ) {
    if (tel == 0) {
      List<Integer> base = new ArrayList<>(smallAns);
      res.add(base);
      return 1;
    }
    int count = 0;
    int prev = -12; //yeh tab lenge jab duplicate combination avoid krne ho
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > -11 && prev != arr[i]) { //agar match na kre to hi call lgaye
        int val = arr[i];
        arr[i] = -11;
        smallAns.add(val);
        count += permuteUnique(arr, tel - 1, smallAns, res);
        smallAns.remove(smallAns.size() - 1);
        arr[i] = val;

        prev = arr[i];
      }
    }
    return count;
  }

  public List<List<Integer>> permuteUnique(int[] nums) {
    List<Integer> smallAns = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    permuteUnique(nums, nums.length, smallAns, res);
    return res;
  }


  //ques 52 NQUEEN
    boolean[] rows;
    boolean[] cols;
    boolean[] diag;
    boolean[] adiag;
    
    public int totalNQueen(int n, int m, int tnq, int idx){
        if(tnq == 0){
            return 1;
        }
        
        int count = 0;
        for(int i = idx; i < n * m; i++) {
            //check it is visited or not
            int r = i / m;
            int c = i % m;
            if(!rows[r] && !cols[c] && !diag[r + c] && !adiag[r - c + m - 1]){
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = true;
                count += totalNQueen(n,m,tnq - 1, i + 1);
                rows[r] = cols[c] = diag[r + c] = adiag[r - c + m - 1] = false;
            }
        }
        return count;
    }
    public int totalNQueens(int n) {
        //placce n queen
        int m = n;
        rows =  new boolean[n];
        cols =  new boolean[m];
        diag = new boolean[n + m - 1];
        adiag = new boolean[n + m - 1];
        
        return totalNQueen(n,m,n,0);
        
    }
}
