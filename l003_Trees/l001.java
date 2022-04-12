import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class l001 {

  public static class TreeNode {

    int val = 0;
    TreeNode left = null;
    TreeNode right = null; //it stores the address of right node

    TreeNode(int val) {
      this.val = val;
    }
  }

  //Trees are recursion way Up
  //Binary tree -> aisa tree jiske atmost 2 child hai ie 0,1,2

  //Traversal of trees only in Binary Search
  public static void preOrderTraversal(TreeNode root, ArrayList<Integer> ans) {
    if (root == null) {
      return;
    }

    ans.add(root.val);
    preOrderTraversal(root.left, ans);
    preOrderTraversal(root.right, ans);
  }

  public static void inOrderTraversal(TreeNode root, ArrayList<Integer> ans) {
    if (root == null) {
      return;
    }
    inOrderTraversal(root.left, ans);
    ans.add(root.val);
    inOrderTraversal(root.right, ans);
  }

  public static void postOrderTraversal(TreeNode root, ArrayList<Integer> ans) {
    if (root == null) {
      return;
    }

    postOrderTraversal(root.left, ans);
    postOrderTraversal(root.right, ans);
    ans.add(root.val);
  }

  // public static void Traversal() {
  //   ArrayList<Integer> ans = new ArrayList<>();
  //   preOrderTraversal(root, ans);
  // }

  //   ==========================================================================
  public static int size(TreeNode root) {
    if (root == null) return 0;

    int left = size(root.left);
    int right = size(root.right);

    return left + right + 1;
  }

  public static int heightInTermsOfEdges(TreeNode root) {
    if (root == null) return -1; //in terms of Nodes , so then return 0

    int left = heightInTermsOfEdges(root.left);
    int right = heightInTermsOfEdges(root.right);

    return Math.max(left, right) + 1;
  }

  public static int maximum(TreeNode root) {
    if (root == null) {
      return -(int) 1e9; //most negative -> isse krege always jb max bole toh
    }

    int left = maximum(root.left);
    int right = maximum(root.right);

    return Math.max(Math.max(left, right), root.val);
  }

  public static int minimum(TreeNode root) {
    if (root == null) {
      return (int) 1e9;
    }

    int left = minimum(root.left);
    int right = minimum(root.right);

    return Math.min(Math.min(left, right), root.val);
  }

  public static int sum(TreeNode root) { //full tree sum
    if (root == null) return 0;

    int leftSum = sum(root.left);
    int rightSum = sum(root.right);

    return leftSum + rightSum + root.val;
  }

  //IMPORTANT FUNCTIONS ===============================================
  /*
   important -> findData -> follows root to node path 
   1) && -> this operator says ki sari conditions ka true hona necesary hai
   2) || -> jese hi ik true milega to aaage ki koi bhi statements check nahi krega
   */

  public static boolean findData(TreeNode root, int data) {
    if (root == null) return false;

    if (root.val == data) return true;

    return findData(root.left, data) || findData(root.right, data);
  }

  public static boolean findData01(TreeNode root, int data) {
    if (root == null) return false;

    boolean res = root.val == data;

    return res || findData01(root.left, data) || findData01(root.right, data);
  }

  public static boolean findData02(TreeNode root, int data) {
    if (root == null) return false;

    if (root.val == data) return true;
    boolean left = findData02(root.left, data);
    boolean right = findData02(root.left, data);

    return left || right;
  }

  public static ArrayList<TreeNode> NodeToRootPath(TreeNode root, int data) {
    if (root == null) {
      return new ArrayList<>();
    }
    if (root.val == data) {
      ArrayList<TreeNode> base = new ArrayList<>();
      base.add(root);
      return base;
    }
    ArrayList<TreeNode> left = NodeToRootPath(root.left, data);
    if (left.size() != 0) {
      left.add(root);
      return left;
    }

    ArrayList<TreeNode> right = NodeToRootPath(root.right, data);
    if (right.size() != 0) {
      left.add(root);
      return left;
    }

    return new ArrayList<>();
  }

  public static boolean NodeToRootPath(
    TreeNode root,
    int data,
    ArrayList<TreeNode> ans
  ) {
    if (root == null) return false;

    if (root.val == data) {
      ans.add(root);
      return true;
    }
    boolean res =
      NodeToRootPath(root.left, data, ans) ||
      NodeToRootPath(root.right, data, ans);
    if (res) {
      ans.add(root);
    }
    return res;
  }

  public static void rootToAllLeafPath(
    TreeNode root,
    ArrayList<Integer> smallAns,
    ArrayList<ArrayList<Integer>> ans
  ) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) { //means leaf
      ArrayList<Integer> base = new ArrayList<>(smallAns);
      base.add(root.val); //isme ik data
      ans.add(base);
      return;
    }

    smallAns.add(root.val);

    rootToAllLeafPath(root.left, smallAns, ans);
    rootToAllLeafPath(root.right, smallAns, ans);

    smallAns.remove(smallAns.size() - 1);
  }

  public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    ArrayList<Integer> smallAns = new ArrayList<>(); //smallAns act as a bucket

    rootToAllLeafPath(root, smallAns, ans);
    return ans;
  }

  public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
    if (root == null || root.left == null && root.right == null) {
      return; //hande the leaf part here also
    }
    if (root.left == null || root.right == null) { //means single child
      ans.add(root.val); //return nhi krege coz uske niche bhi tree exist kr skta
    }
    exactlyOneChild(root.left, ans); //simply travel in preorder
    exactlyOneChild(root.right, ans);
  }

  public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
    ArrayList<Integer> ans = new ArrayList<>();
    exactlyOneChild(root, ans);
    return ans;
  }

  // count single child NODES with taking static
  static int count = 0;

  public static void ExactlyOneChild(TreeNode root) {
    if (root == null || (root.left == null && root.right == null)) return;
    if (root.left == null || root.right == null) count++;
    countExactlyOneChild(root.left);
    countExactlyOneChild(root.right);
  }

  public static int countExactlyOneChild(TreeNode node) {
    ExactlyOneChild(node);
    return count;
  }

  //single child Node with Not static way
  public static int countExactlyOneChild_01(TreeNode node) {
    if (node == null || (node.left == null && node.right == null)) return 0; //means it is leaf
    //faith -> left apna count le aayega and right apna le aayega
    int left = countExactlyOneChild_01(node.left);
    int right = countExactlyOneChild_01(node.right);
    int ans = left + right;
    if (node.left == null || node.right == null) ans++;
    return ans;
  }

  public static void printAtDeptK(
    TreeNode root,
    int k,
    ArrayList<Integer> ans
  ) {
    if (root == null || k < 0) return;

    if (k == 0) {
      ans.add(root.val);
      return;
    }
    printAtDeptK(root.left, k - 1, ans);
    printAtDeptK(root.right, k - 1, ans);
  }

  //leetcode 863 kdistance away

  public ArrayList<TreeNode> rootToNodePath_01(TreeNode root, int data) {
    if (root == null) {
      return new ArrayList<>();
    }

    if (root.val == data) {
      ArrayList<TreeNode> base = new ArrayList<>();
      base.add(root);
      return base;
    }
    ArrayList<TreeNode> left = rootToNodePath_01(root.left, data);
    if (left.size() != 0) {
      left.add(root);
      return left;
    }
    ArrayList<TreeNode> right = rootToNodePath_01(root.right, data);
    if (right.size() != 0) {
      right.add(root);
      return right;
    }
    return new ArrayList<>();
  }

  //or we use this boolean array
  //  public static boolean NodeToRootPath02(TreeNode root, int data, ArrayList<TreeNode> ans) {
  //       if (root == null)
  //           return false;
  //       if (root.val == data) {
  //           ans.add(root);
  //           return true;
  //       }

  //       boolean res = NodeToRootPath02(root.left, data, ans) || NodeToRootPath02(root.right, data, ans);
  //       if (res)
  //           ans.add(root);
  //       return res;
  //   }

  public static void kdown(
    TreeNode root,
    int k,
    TreeNode blockNode,
    List<Integer> ans
  ) {
    if (root == null || root == blockNode || k < 0) return;

    if (k == 0) {
      ans.add(root.val);
      return;
    }
    kdown(root.left, k - 1, blockNode, ans);
    kdown(root.right, k - 1, blockNode, ans);
  }

  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    List<Integer> ans = new ArrayList<>();
    ArrayList<TreeNode> path = rootToNodePath_01(root, target.val);
    TreeNode blockNode = null;
    for (int i = 0; i < path.size(); i++) {
      kdown(path.get(i), k - i, blockNode, ans);
      blockNode = path.get(i); //update the blockNode
    }
    return ans;
  }

  // public List<Integer> distanceK_01(TreeNode root, TreeNode target, int k) {
  //   List<Integer> ans = new ArrayList<>();
  //   TreeNode blockNode = null;
  // }

  public static int distanceK_01(
    TreeNode root,
    TreeNode target,
    int k,
    ArrayList<Integer> ans
  ) {
    //k is kitna niche jakr print krna hai
    if (root == null) return -1;
    if (root == target) {
      kdown(root, k, null, ans);
      return 1;
    }
    int leftDistance = distanceK_01(root.left, target, k, ans);
    if (leftDistance != -1) { //means data left side me mila hai
      kdown(root, k - leftDistance, root.left, ans);
      return leftDistance + 1;
    }
    int rightDistance = distanceK_01(root.left, target, k, ans);
    if (rightDistance != -1) { //means data left side me mila hai
      kdown(root, k - rightDistance, root.right, ans);
      return rightDistance + 1;
    }
    return -1;
  }

  // ==================================

  public static void kdownBurn(
    TreeNode root,
    int time,
    TreeNode blockNode,
    ArrayList<ArrayList<Integer>> ans
  ) {
    if (root == null || root == blockNode) {
      return;
    }
    if (time == ans.size()) ans.add(new ArrayList<>());

    ans.get(time).add(root.val);

    kdownBurn(root.left, time + 1, blockNode, ans); //visit in preorder
    kdownBurn(root.right, time + 1, blockNode, ans);
  }

  public static int burningTree(
    TreeNode root,
    int target,
    ArrayList<ArrayList<Integer>> ans
  ) {
    if (root == null) return -1;
    if (root.val == target) {
      kdownBurn(root, 0, null, ans);
      return 1;
    }
    int leftTime = burningTree(root.left, target, ans); //left se agar answer mila to apne andr 1 dalega
    if (leftTime != -1) {
      kdownBurn(root, leftTime, root.left, ans);
      return leftTime + 1;
    }
    int rightTime = burningTree(root.right, target, ans);
    if (rightTime != -1) {
      kdownBurn(root, rightTime, root.right, ans);
      return rightTime + 1;
    }
    return -1;
  }

  public static void burningTree(TreeNode root, int target) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    burningTree(root, target, ans);
  }

  // CLASS 02 JUN 13  ==========================================================
  public static void kdownBurnWithWater(
    TreeNode root,
    int time,
    TreeNode blockNode,
    ArrayList<ArrayList<Integer>> ans,
    HashSet<Integer> water
  ) {
    if (root == null || root == blockNode || water.contains(root.val)) return;
    if (time == ans.size()) {
      ans.add(new ArrayList<>());
    }
    ans.get(time).add(root.val);

    kdownBurnWithWater(root.left, time + 1, blockNode, ans, water);
    kdownBurnWithWater(root.right, time + 1, blockNode, ans, water);
  }

  public static int burningTreeWithWater(
    TreeNode root,
    int fireNode,
    ArrayList<ArrayList<Integer>> ans,
    HashSet<Integer> water
  ) {
    if (root == null) return -1;

    if (root.val == fireNode) {
      if (!water.contains(root.val)) { //udr paani nahi hai
        kdownBurnWithWater(root, 0, null, ans, water);
        return 1;
      } else return -2;
    }

    int ld = burningTreeWithWater(root.left, fireNode, ans, water);
    if (ld > 0) {
      //check jidr burn krre hai udr water to nhi hai
      if (!water.contains(root.val)) {
        kdownBurnWithWater(root, ld, root.left, ans, water);
        return ld + 1;
      }
      return -2;
    }
    if (ld == -2) return -2;

    int rd = burningTreeWithWater(root.right, fireNode, ans, water);
    if (rd > 0) {
      if (!water.contains(root.val)) {
        kdownBurnWithWater(root, rd, root.right, ans, water);
      }
      return -2;
    }

    if (rd == -2) {
      return -2;
    }

    return -1;
  }

  public static ArrayList<ArrayList<Integer>> burningTreeWithWater(
    TreeNode root,
    int fireNode,
    ArrayList<Integer> water
  ) {
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    HashSet<Integer> set = new HashSet<>();
    for (int ele : water) {
      set.add(ele);
    }
    burningTreeWithWater(root, fireNode, ans, set);
    return ans;
  }

  // ==============================================

  // public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {}

  // ====CLASS 3==============================================================

  //BFS is used to find level OrderTraversal but isme hmko true mark krne ki need nhi hai
  public static void levelOrderTraversal(TreeNode root) {
    LinkedList<TreeNode> que = new LinkedList<>();
    que.addLast(root);

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    int level = 0;
    while (que.size() != 0) {
      int size = que.size();
      ArrayList<Integer> smallAns = new ArrayList<>();
      System.out.println(level);
      while (size-- > 0) {
        TreeNode rNode = que.removeFirst(); //remove and call for unvisited nbrs
        smallAns.add(rNode.val);

        if (rNode.left != null) que.addLast(rNode.left);
        if (rNode.right != null) que.addLast(rNode.right);
      }
      ans.add(smallAns);
      level++;
    }
    int count = 0;
    for (var list : ans) {
      System.out.println(count++ + "->" + list);
    }
  }

  public static List<Integer> leftView(TreeNode root) {
    LinkedList<TreeNode> que = new LinkedList<>();
    que.addLast(root);

    List<Integer> ans = new ArrayList<>();

    while (que.size() != 0) {
      int size = que.size();
      ans.add(que.getFirst().val);
      while (size-- > 0) {
        TreeNode rNode = que.removeFirst();

        if (rNode.left != null) que.addLast(rNode.left);
        if (rNode.right != null) que.addLast(rNode.right);
      }
    }
    return ans;
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null) return ans;
    LinkedList<TreeNode> que = new LinkedList<>();
    que.addLast(root);

    while (que.size() != 0) {
      int size = que.size();
      ans.add(que.getFirst().val);
      while (size-- > 0) {
        TreeNode rNode = que.removeFirst();

        if (rNode.right != null) que.addLast(rNode.right);
        if (rNode.left != null) que.addLast(rNode.left);
      }
    }
    return ans;
  }

  //{minvtlevel, maxVtlevel}, vertical order for top view bottom view we need a class
  public static void widthofShadow(TreeNode root, int[] minMax, int vlevel) {
    if (root == null) return;

    minMax[0] = Math.min(minMax[0], vlevel);
    minMax[1] = Math.max(minMax[1], vlevel);

    widthofShadow(root.left, minMax, vlevel - 1);
    widthofShadow(root.right, minMax, vlevel + 1);
  }

  // public static int width(TreeNode root) {
  //   int[] ans = new int[2];
  //   widthofShadow(root, ans, 0);
  //   return ans[1] - ans[0] + 1; //b - a + 1
  // }

  public static class vPair {

    TreeNode node = null;
    int vl = 0;

    vPair(TreeNode node, int vl) {
      this.vl = vl;
      this.node = node;
    }
  }

  public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(
    TreeNode root
  ) {
    LinkedList<vPair> que = new LinkedList<>();
    int[] minMax = new int[2];
    widthofShadow(root, minMax, 0);

    int len = minMax[1] - minMax[0] + 1; //re - le + 1 [including a and b]

    que.addLast(new vPair(root, Math.abs(minMax[0]))); // new vPair(node, vl) vl kaise pta lgega width of shadown se

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    for (int i = 0; i < len; i++) {
      ans.add(new ArrayList<>());
    }

    while (que.size() != 0) {
      int size = que.size();
      while (size-- > 0) {
        vPair rp = que.removeFirst();
        int vl = rp.vl;
        TreeNode node = rp.node;

        ans.get(vl).add(node.val);

        if (node.left != null) que.addLast(new vPair(node.left, vl - 1));

        if (node.right != null) que.addLast(new vPair(node.right, vl + 1));
      }
    }
    return ans;
  }

  public static ArrayList<Integer> bottomView(TreeNode root) {
    LinkedList<vPair> que = new LinkedList<>();
    int[] minMax = new int[2];
    widthofShadow(root, minMax, 0);

    int len = minMax[1] - minMax[0] + 1;

    ArrayList<Integer> ans = new ArrayList<>();

    que.addLast(new vPair(root, Math.abs(minMax[0]))); //jo 1st wala hoa vo correct position par ajega jo [most negative hogi taki origin shift hoje]

    for (int i = 0; i < len; i++) {
      ans.add(null); //by default sabme null fill krdiya
    }

    while (que.size() != 0) {
      int size = que.size();
      while (size-- > 0) {
        vPair rp = que.removeFirst();
        int vlevel = rp.vl;
        TreeNode node = rp.node;

        ans.set(vlevel, node.val);  //means over riding the values

        if (node.left != null) que.addLast(new vPair(node.left, vlevel - 1));
        if (node.right != null) que.addLast(new vPair(node.right, vlevel + 1));
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    // Traversal();
  }
}
