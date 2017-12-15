/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val + "");
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode curr = queue.remove();
            sb.append(";" + curr.val);
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
        return sb.toString();
        
    }

    // Decodes encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] nodes = data.split(";");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        for (int i = 1; i < nodes.length; i++){
            insert(root, new TreeNode(Integer.parseInt(nodes[i])));
        }
        return root;
    }
    
    public void insert(TreeNode root, TreeNode newNode){
        if (root.val == newNode.val) return;
        if (newNode.val < root.val){
            if (root.left == null) root.left = newNode;            
            else insert(root.left, newNode);
        }
        else {
            if (root.right == null) root.right = newNode;            
            else insert(root.right, newNode);
        }
    }
}
