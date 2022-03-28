import java.util.ArrayList;
import java.util.LinkedList;

public class l002BST {

  public static class TreeNode {

    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
      this.val = val;
    }
  }

  //BST Inorder is always sorted, time complexity of BST is O(log n), do BST Ques iteratively

  public static int size(TreeNode root) { // size, height function same as in BINARY TREE
    if (root == null) return 0;
    int leftSize = size(root.left);
    int rightSize = size(root.right);

    return leftSize + rightSize + 1;
  }

  public static int height(TreeNode root) {
    if (root == null) return -1; //in terms of edges
    int leftheight = height(root.left);
    int rightheight = height(root.right);

    return Math.max(leftheight, rightheight) + 1;
  }

  //maximum and minimum of BST are diff

  public static int maximum(TreeNode root) {
    TreeNode curr = root;
    while (curr.right != null) { //rightMost
      curr = curr.right;
    }
    return curr.val;
  }

  public static int minimum(TreeNode root) {
    TreeNode curr = root;
    while (curr.left != null) { //leftMost
      curr = curr.left;
    }
    return curr.val;
  }

  public static boolean find(TreeNode root, int data) {
    TreeNode curr = root;
    while (curr != null) {
      if (curr.val == data) return true; else if (curr.val < data) {
        curr = curr.right;
      } else {
        curr = curr.left;
      }
    }
    return false;
  }

  public static ArrayList<TreeNode> rootToNodePath(TreeNode root, int data) {
    ArrayList<TreeNode> ans = new ArrayList<>();
    TreeNode curr = root;
    while (curr != null) {
      if (curr.val == data) {
        ans.add(curr);
      } else if (curr.val > data) {
        ans.add(curr);
        curr = curr.left;
      } else {
        ans.add(curr);
        curr = curr.right;
      }
    }

    return ans;
  }

  public static ArrayList<TreeNode> rootToNodePath_01(TreeNode root, int data) {
    ArrayList<TreeNode> ans = new ArrayList<>();
    TreeNode curr = root;
    boolean flag = false;
    while (curr != null) {
      ans.add(curr);
      if (curr.val == data) {
        flag = true;
        break;
      } else if (curr.val > data) {
        curr = curr.left;
      } else {
        curr = curr.right;
      }
    }

    if (!flag) ans.clear(); //means data nahi mila

    return ans;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode curr = root;
    while (curr != null) {
      if (curr.val < p.val && curr.val < q.val) curr = curr.right; else if (
        curr.val > p.val && curr.val > q.val
      ) curr = curr.left; else return curr; //means data divide hogya idr se
    }
    return null; //data mila hi nahi
  }

  //leetcode 173 INORDER TRAVERSAL
  class BSTIterator {

    // private ArrayDeque<TreeNode> st = new ArrayDeque<>(); //we make stack : addfirst,removefirst
    private LinkedList<TreeNode> st = new LinkedList<>();

    //Time complexity : if tree is balanced then it is log(n)
    public BSTIterator(TreeNode root) {
      addAllLeft(root); // it add right and add all left
    }

    private void addAllLeft(TreeNode node) {
      while (node != null) {
        this.st.addFirst(node);
        node = node.left;
      }
    }

    public int next() { // stack ka top element dega
      TreeNode removeNode = st.removeFirst();
      addAllLeft(removeNode.right);
      return removeNode.val;
    }

    public boolean hasNext() { //yeh dekhega ki usse just bda number hai stack me
      return st.size() != 0;
    }
  }
}
