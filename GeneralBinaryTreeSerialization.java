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
        serializeWithDFS(sb,root);
        return sb.toString();
    }
    
    public void serializeWithDFS(StringBuilder sb, TreeNode root) {
        if (root == null) sb.append(",#");
        else {
            sb.append("," + root.val);
            serializeWithDFS(sb,root.left);
            serializeWithDFS(sb,root.right);
        }
        return;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] dataArr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(dataArr[1]));
        int rightIndex = buildWithDFS(dataArr, root, 2, true);
        buildWithDFS(dataArr, root, rightIndex, false);
        return root;
    }
    
    public int buildWithDFS(String[] arr, TreeNode root, int index, boolean isLeftChild) {
        if (index >= arr.length) return -1;
        if (arr[index].equals("#")){
            return index + 1;
        }
        TreeNode newNode = new TreeNode(Integer.parseInt(arr[index]));
        if (isLeftChild){
            root.left = newNode;
        }
        else {
            root.right = newNode;
        }
        int rightIndex = buildWithDFS(arr, newNode, index + 1, true);
        int last = buildWithDFS(arr, newNode, rightIndex, false);
        return last;
    }
}
