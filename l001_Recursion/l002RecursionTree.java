public class l002RecursionTree {

  public static int permutationWithInfiniteCoins(
    int[] arr,
    int tar,
    String ans
  ) {
    if (tar == 0) {
      System.out.println(ans);
      return 1;
    }
    int count = 0;
    for (int ele : arr) {
      if (tar - ele >= 0) {
        count += permutationWithInfiniteCoins(arr, tar - ele, ans + ele);
      }
    }
    return count;
  }

  public static int combinationWithInfiniteCoins(
    int[] arr,
    int tar,
    int idx,
    String ans
  ) {
    if (tar == 0) {
      System.out.println(ans);
      return 1;
    }
    int count = 0;
    for (int i = idx; i < arr.length; i++) {
      if (tar - arr[i] >= 0) count +=
        combinationWithInfiniteCoins(arr, tar - arr[i], i, ans + arr[i]);
    }
    return count;
  }

  public static int combinationWithSingleCoin(
    int[] arr,
    int tar,
    int idx,
    String ans
  ) {
    //means agar kisi ik coin ko use kiya to usse next coin use krege, because 1 time use kr skte only
    if (tar == 0) {
      System.out.println(ans);
      return 1;
    }
    int count = 0;
    for (int i = idx; i < arr.length; i++) {
      if (tar - arr[i] >= 0) {
        count +=
          combinationWithSingleCoin(arr, tar - arr[i], i + 1, ans + arr[i]);
      }
    }
    return count;
  }

  public static int permutationWithSingleCoin(int[] arr, int tar, String ans) {
    if (tar == 0) {
      System.out.println(ans);
      return 1;
    }
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > 0 && tar - arr[i] >= 0) { //using the same array
        int val = arr[i]; //1st copy th value
        arr[i] = -arr[i]; //mark negative
        count += permutationWithSingleCoin(arr, tar - val, ans + val);
        arr[i] = -arr[i]; //mark again positive
      }
    }
    return count;
  }

  public static int permutationWithSingleCoin(
    int[] arr,
    int tar,
    boolean[] vis,
    String ans
  ) {
    if (tar == 0) {
      System.out.println(ans);
      return 1;
    }
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      if (!vis[i] && tar - arr[i] >= 0) { //agar visited nhi h
        vis[i] = true; //mark negative
        count +=
          permutationWithSingleCoin(arr, tar - arr[i], vis, ans + arr[i]);
        vis[i] = false; //mark again positive
      }
    }
    return count;
  }

  // =========================================================================

  public static int combinationWithSingleSubseq(
    int[] arr,
    int tar,
    int idx,
    String ans
  ) {
    if (tar == 0 || idx == arr.length) {
      if (tar == 0) { //here we print the answer
        System.out.println(ans);
        return 1;
      }
      return 0;
    }

    int count = 0;
    if (tar - arr[idx] >= 0) {
      //call 1 ->  aane ki
      count +=
        combinationWithSingleSubseq(
          arr,
          tar - arr[idx],
          idx + 1,
          ans + arr[idx]
        );
      //call 2 -> na aane ki
      count += combinationWithSingleSubseq(arr, tar, idx + 1, ans);
    }
    return count;
  }

  public static int combinationWithInfiniteSubseq(
    int[] arr,
    int tar,
    int idx,
    String ans
  ) {
    if (tar == 0 || idx == arr.length) {
      if (tar == 0) {
        System.out.println(ans);
        return 1;
      }
      return 0;
    }
    int count = 0;

    //check krke jaege
    if (tar - arr[idx] >= 0) {
      count +=
        combinationWithInfiniteSubseq(arr, tar - arr[idx], idx, ans + arr[idx]); //aane ki call, jb agar aara hai to usko dubara chance do because infinite supply h
      count += combinationWithInfiniteSubseq(arr, tar, idx + 1, ans); //na aane ki call, jo nahi aara to atleast uske aage wale ko to chance do
    }
    return count;
  }

  public static int permutationWithInfiniteSubsequence(
    int[] arr,
    int tar,
    int idx,
    String ans
  ) {
    if (tar == 0 || idx == arr.length) {
      if (tar == 0) {
        System.out.println(ans);
        return 1;
      }
      return 0;
    }
    int count = 0;
    if (tar - arr[idx] >= 0) {
      count +=
        permutationWithInfiniteSubsequence(
          arr,
          tar - arr[idx],
          0,
          ans + arr[idx]
        );
      count += permutationWithInfiniteSubsequence(arr, tar, idx + 1, ans); //na aane ki choice
    }
    return count;
  }

  public static int permutationWithSingleSubsequence(
    int[] arr,
    int tar,
    int idx,
    String ans
  ) { //note: in s.s we need idx
    if (tar == 0 || idx == arr.length) {
      if (tar == 0) {
        System.out.println(ans);
        return 1;
      }
      return 0;
    }

    int count = 0;
    if (arr[idx] > 0 && tar - arr[idx] >= 0) { //means positive honi chaiye
      int val = arr[idx];
      arr[idx] = -arr[idx];
      count += permutationWithSingleSubsequence(arr, tar - val, 0, ans + val);
      arr[idx] = -arr[idx];
    }
    count += permutationWithSingleSubsequence(arr, tar, idx + 1, ans);
    return count;
  }

  //1D QueenSet -> =========================================================

  //total boxes- > 6, total queen -> 4, queen place so far -> abhi tk kitni queen bitha di hai, bn -> box number
  public static int queenCombination(
    int tboxes,
    int tqueen,
    int qpsf,
    int bn,
    String ans
  ) {
    //ncr way -> ie for loop wala recursion
    if (qpsf == tqueen) { //means qpsf -> 4 queen place hogyi total queen k equal
      System.out.println(ans);
      return 1;
    }
    int count = 0;
    for (int i = bn; i < tboxes; i++) {
      count +=
        queenCombination(
          tboxes,
          tqueen,
          qpsf + 1,
          i + 1,
          ans + "b" + i + "q" + qpsf + " "
        );
    }
    return count;
  }

  public static int queenPermutation(
    boolean[] tboxes,
    int tqueen,
    int qpsf,
    int bn,
    String ans
  ) {
    //ncr way -> ie for loop wala recursion
    if (qpsf == tqueen) { //means qpsf -> 4 queen place hogyi total queen k equal
      System.out.println(ans);
      return 1;
    }
    int count = 0;
    for (int i = bn; i < tboxes.length; i++) {
      if (!tboxes[i]) {
        tboxes[i] = true;
        count +=
          queenPermutation(
            tboxes,
            tqueen,
            qpsf + 1,
            0,
            ans + "b" + i + "q" + qpsf + " "
          );
        tboxes[i] = false;
      }
    }
    return count;
  }

  //2D QueenSet -> =========================================================

  //total boxes- > 6, total queen -> 4, queen place so far -> abhi tk kitni queen bitha di hai, bn -> box number
  public static int queenCombination2D(
    boolean[][] boxes,
    int tqueen,
    // int qpsf,
    int bn,
    String ans
  ) {
    //ncr way -> ie for loop wala recursion
    // if (qpsf == tqueen) { //means qpsf -> 4 queen place hogyi total queen k equal
    if (tqueen == 0) {
      System.out.println(ans);
      return 1;
    }
    int n = boxes.length, m = boxes[0].length;
    int count = 0;
    for (int i = bn; i < n * m; i++) { //jb sare element visit hojege
      int r = i / m;
      int c = i % m;
      count +=
        queenCombination2D(
          boxes,
          tqueen - 1,
          // qpsf + 1,
          i + 1,
          ans + "(" + "b" + r + "," + c + ") "
        );
    }
    return count;
  }

  public static int queenPermutation2D(
    boolean[][] boxes,
    int tqueen,
    // int qpsf,
    int bn,
    String ans
  ) {
    //ncr way -> ie for loop wala recursion
    // if (qpsf == tqueen) { //means qpsf -> 4 queen place hogyi total queen k equal
    if (tqueen == 0) {
      System.out.println(ans);
      return 1;
    }
    int n = boxes.length, m = boxes[0].length;
    int count = 0;
    for (int i = bn; i < n * m; i++) { //jb sare element visit hojege
      int r = i / m;
      int c = i % m;
      //permuation me 1 baar jis coin ko use krliya usko dubara use ni kr sktee
      if (!boxes[r][c]) {
        boxes[r][c] = true;
        count +=
          queenPermutation2D(
            boxes,
            tqueen - 1,
            // qpsf + 1,
            0,
            ans + "(" + "b" + r + "," + c + ") "
          );
        boxes[r][c] = false;
      }
    }
    return count;
  }

  //NQUEEN SERIES ==================================================================

  public static boolean isSafeToPlaceQueen(boolean[][] boxes, int r, int c) {
    // int[][] dir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
    int[][] dir = {
      { 0, -1 },
      { -1, -1 },
      { -1, 0 },
      { -1, 1 },
      { 0, 1 },
      { 1, 1 },
      { 1, 0 },
      { 1, -1 },
    };
    int n = boxes.length, m = boxes[0].length;

    //call in 8 directions
    for (int d = 0; d < dir.length; d++) {
      for (int rad = 1; rad < m; rad++) {
        int x = r + rad * dir[d][0];
        int y = c + rad * dir[d][1];

        //check we are in the boundary or not
        if (x >= 0 && y >= 0 && x < n && y < m) {
          if (boxes[x][y]) return false;
        } else break;
      }
    }
    return true;
  }

  public static int n_queenCombination1(
    boolean[][] boxes,
    int tnq,
    int idx,
    String ans
  ) {
    //1st write combination to place queen on 16 boxes
    if (tnq == 0) {
      System.out.println(ans);
      return 1;
    }

    int n = boxes.length, m = boxes[0].length, count = 0;
    for (int i = idx; i < n * m; i++) {
      int r = i / m; //this is used to go 1d to 2d, bss visulaise krre hai 1d ki trah, and it gives index
      int c = i % m;

      //then call lgaege check krke ki udr valid hai ya nahi
      if (isSafeToPlaceQueen(boxes, r, c)) { //and jihdr queen place krni hai vo jgh false h
        boxes[r][c] = true; //if yes -> then mark true
        count +=
          n_queenCombination1(
            boxes,
            tnq - 1,
            i + 1,
            ans + "(" + r + "," + c + ")"
          );
        boxes[r][c] = false; //back track because if we place queen to its position
      }
    }

    return count;
  }

  static boolean[] rows;
  static boolean[] columns;
  static boolean[] diagonal;
  static boolean[] antidiagonal;

  public static int n_queenCombination2(int n,int m,int tnq,int idx,String ans) {
   //in combination we have done optimised way

    //1st write combination to place queen on 16 boxes
    if (tnq == 0) {
      System.out.println(ans);
      return 1;
    }

    int count = 0;
    for (int i = idx; i < n * m; i++) {
      int r = i / m; //this is used to go 1d to 2d, bss visulaise krre hai 1d ki trah, and it gives index
      int c = i % m;

      //then call lgaege check krke ki udr valid hai ya nahi
      if (!rows[r] && !columns[c] && !diagonal[r + c] && !antidiagonal[r - c + m - 1]) { //and jihdr queen place krni hai vo jgh false h
        rows[r] = columns[c] = diagonal[r + c] = antidiagonal[r - c + m - 1] = true; //if yes -> then mark true
        count += n_queenCombination2(n, m, tnq - 1, i + 1, ans + "(" + r + ", " + c + ") ");
        rows[r] = columns[c] = diagonal[r + c] = antidiagonal[r - c + m - 1] = false; //back track because if we place queen to its position
      }
    }

    return count;
  }

  public static int nqueen_combination3_optimised(int floor, int tnq, int m, String ans) {
      if(tnq == 0){
        System.out.println(ans);
        return 1;
      }    
      int count = 0;
      for(int room = 0; room < m; room++) {  //m is columns in 1 row, loop doesnot start from idx, here loop is for columns
        int r = floor, c = room;
        //udr place krege jidr bethna safe
        if(!rows[r] && !columns[c] && !diagonal[r + c] && !antidiagonal[r-c + m - 1]){
          rows[r] = columns[c] = diagonal[r + c] = antidiagonal[r - c + m - 1] = true;
          count += nqueen_combination3_optimised(floor + 1, tnq - 1, m , ans + "(" + r + "," + c + ")");
          rows[r] = columns[c] = diagonal[r + c] = antidiagonal[r - c + m - 1] = false;
        }
      }
      return count;
  }

   public static int nqueen_permut3_optimised(int floor, int tnq, int m, String ans) {
      if(tnq == 0){
        System.out.println(ans);
        return 1;
      }    
      int count = 0;
      for(int room = 0; room < m; room++) {  //m is columns in 1 row, loop doesnot start from idx, here loop is for columns
        int r = floor, c = room;
        //udr place krege jidr bethna safe
        if(!rows[r] && !columns[c] && !diagonal[r + c] && !antidiagonal[r-c + m - 1]){
          rows[r] = columns[c] = diagonal[r + c] = antidiagonal[r - c + m - 1] = true;
          count += nqueen_permut3_optimised(0, tnq - 1, m , ans + "(" + r + "," + c + ")");
          rows[r] = columns[c] = diagonal[r + c] = antidiagonal[r - c + m - 1] = false;
        }
      }
      count += nqueen_permut3_optimised(floor + 1, tnq , m , ans);
      return count;
  }

 

  public static int n_queenPermutation1(
    boolean[][] boxes,
    int tqn,
    int idx,
    String ans
  ) {
    if (tqn == 0) {
      System.out.println(ans);
      return 1;
    }
    int n = boxes.length, m = boxes[0].length, count = 0;
    for (int i = idx; i < n * m; i++) {
      int r = i / m;
      int c = i % m;
      //We will check if anyone is already sitting there
      if (!boxes[r][c] && isSafeToPlaceQueen(boxes, r, c)) {
        boxes[r][c] = true;
        count +=
          n_queenPermutation1(boxes, tqn - 1, 0, ans + "(" + r + "," + c + ")");
        boxes[r][c] = false;
      }
    }
    return count;
  }

  public static int n_queenCombination_subseq_1(
    boolean[][] boxes,
    int tnq,
    int idx,
    String ans
  ) {
    int n = boxes.length, m = boxes[0].length, count = 0;
    //1st write combination to place queen on 16 boxes
    if (tnq == 0 || idx == n * m) {
      if (tnq == 0) {
        System.out.println(ans);
        return 1;
      }
      return 0;
    }

    int r = idx / m; //this is used to go 1d to 2d, bss visulaise krre hai 1d ki trah, and it gives index
    int c = idx % m;

    //then call lgaege check krke ki udr valid hai ya nahi
    if (isSafeToPlaceQueen(boxes, r, c)) { //and jihdr queen place krni hai vo jgh false h
      boxes[r][c] = true; //if yes -> then mark true
      count +=
        n_queenCombination_subseq_1(
          boxes,
          tnq - 1,
          idx + 1,
          ans + "(" + r + "," + c + ")"
        );
      boxes[r][c] = false; //back track because if we place queen to its position
    }
    count += n_queenCombination_subseq_1(boxes, tnq, idx + 1, ans);

    return count;
  }

  public static int n_queenPermutation_subseq_1(
    boolean[][] boxes,
    int tqn,
    int idx,
    String ans
  ) {
    int n = boxes.length, m = boxes[0].length, count = 0;
    if (tqn == 0 || idx == n * m) {
      if (tqn == 0) {
        System.out.println(ans);
        return 1;
      }
      return 0;
    }
    int r = idx / m;
    int c = idx % m;
    //We will check if anyone is already sitting there
    if (!boxes[r][c] && isSafeToPlaceQueen(boxes, r, c)) {
      boxes[r][c] = true;
      count +=
        n_queenPermutation_subseq_1(
          boxes,
          tqn - 1,
          0,
          ans + "(" + r + "," + c + ")"
        );
      boxes[r][c] = false;
    }
    count += n_queenPermutation_subseq_1(boxes, tqn, idx + 1, ans);
    return count;
  }

  public static void coinChange() {
    int[] arr = { 2, 3, 5, 7 };
    int tar = 10;
    // System.out.println(permutationWithInfiniteCoins(arr, tar, ""));
    // System.out.println(combinationWithInfiniteCoins(arr, tar, 0, ""));
    // System.out.println(combinationWithSingleCoin(arr, tar, 0, ""));
    //  System.out.println(permutationWithSingleCoin(arr, tar, ""));

    // System.out.println(permutationWithInfiniteSubsequence(arr, tar, 0 ,""));
    // System.out.println(combinationWithInfiniteSubseq(arr, tar, 0 ,""));
    // System.out.println(combinationWithSingleSubseq(arr, tar, 0,""));
    // System.out.println(permutationWithSingleSubsequence(arr, tar, 0, ""));
  }

  public static void Nqueen() {
    int n = 4, m = 4, q = 4;
    boolean[][] boxes = new boolean[n][m];
    // System.out.println(n_queenCombination1(boxes, q, 0, ""));
    // System.out.println(n_queenPermutation1(boxes, q, 0, ""));
    // System.out.println(n_queenCombination_subseq_1(boxes, q, 0, ""));
    // System.out.println(n_queenPermutation_subseq_1(boxes, q, 0, ""));
    rows = new boolean[n];
    columns = new boolean[m];
    diagonal = new boolean[n + m - 1];
    antidiagonal = new boolean[n + m - 1];

    // System.out.println(n_queenCombination2(n, m, q, 0, ""));
    // System.out.println(nqueen_combination3_optimised(0, q, 4, ""));
    System.out.println(nqueen_permut3_optimised(0, q, 4, ""));
    
  }

  public static void queenSet() {
    // int[] arr = {1,1,1,1,1,1}; arr ki need ni hai

    boolean[][] boxes = new boolean[4][4];
    // System.out.println(queenPermutation(boxes, 4, 0, 0, "")); //6 boxes h, ncr wale krre hai
    // System.out.println(queenCombination(16, 4, 0, 0, "")); //6 boxes h, ncr wale krre hai
    // System.out.println(queenCombination2D(boxes, 4, 0, 0, "")); //6 boxes h, ncr wale krre hai
    // System.out.println(queenCombination2D(boxes, 4, 0, "")); //6 boxes h, ncr wale krre hai
    // System.out.println(queenPermutation2D(boxes, 4, 0, "")); //6 boxes h, ncr wale krre hai
  }

  public static void main(String[] args) {
    // coinChange();
    // // queenSet();
    Nqueen();
  }
}
