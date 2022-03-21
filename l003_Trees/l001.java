import java.util.ArrayList;

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

  public static void Traversal() {
    ArrayList<Integer> ans = new ArrayList<>();
    preOrderTraversal(root, ans);
  }

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
    return ans;
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
  public static int countExactlyOneChild(TreeNode node) {
    if (node == null || (node.left == null && node.right == null)) return 0; //means it is leaf
    //faith -> left apna count le aayega and right apna le aayega
    int left = countExactlyOneChild(node.left);
    int right = countExactlyOneChild(node.right);
    int ans = left + right;
    if (node.left == null || node.right == null) count++;
    return count;
  }

  public static void main(String[] args) {
    // Traversal();
  }
}
