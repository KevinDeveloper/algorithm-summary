package com.kevin.algorithm.algosummary.algoDemo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName: TreeNodeTest
 * @Description:
 * @Author: Kevin
 * @Date: 2018/10/24 18:06
 */
public class TreeNodeTest {
    static class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }


    /**
     * 获取最大深度
     * 使用递归，分别求出左子树的深度、右子树的深度，两个深度的较大值+1即可
     *
     * @param root
     * @return
     */
    public static int getMaxDepth(TreeNode root) {
        if (root == null)
            return 0;
        else {
            int left = getMaxDepth(root.left);
            int right = getMaxDepth(root.right);
            return 1 + Math.max(left, right);
        }
    }


    /**
     * 二叉树宽度
     * 使用队列，层次遍历二叉树。在上一层遍历完成后，下一层的所有节点已经放到队列中，此时队列中的元素个数就是下一层的宽度。以此类推，依次遍历下一层即可求出二叉树的最大宽度。
     *
     * @param root
     * @return
     */
    public static int getMaxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        // 最大宽度
        int maxWitdth = 1;
        // 入队
        queue.add(root);
        while (true) {
            // 当前层的节点个数
            int len = queue.size();
            if (len == 0) {
                break;
            }
            // 如果当前层，还有节点
            while (len > 0) {
                TreeNode t = queue.poll();
                len--;
                if (t.left != null) {
                    // 下一层节点入队
                    queue.add(t.left);
                }
                if (t.right != null) {
                    // 下一层节点入队
                    queue.add(t.right);
                }
            }
            maxWitdth = Math.max(maxWitdth, queue.size());
        }
        return maxWitdth;
    }

    /**
     * 输出根节点到叶子的路径
     * 方法一：使用递归算法，把经过的路径作为参数往下传
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> result = new LinkedList<String>();
        if (root == null) {
            return result;
        }
        getPath(root, result, root.val + "");
        return result;
    }

    public void getPath(TreeNode root, LinkedList<String> result, String str) {
        if (root.left == null && root.right == null) {
            result.add(str);
            return;
        }
        if (root.left != null) {
            getPath(root.left, result, str + "->" + root.left.val);
        }
        if (root.right != null) {
            getPath(root.right, result, str + "->" + root.right.val);
        }
    }

    /**
     * 输出根节点到叶子的路径
     * 方法二：可以不用辅助函数，直接在路径集合上操作，把当前节点添加到下级返回的每一个路径上
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> paths = new LinkedList<>();
        if (root == null) {
            return paths;
        }
        if (root.left == null && root.right == null) {
            paths.add(root.val + "");
            return paths;
        }
        for (String path : binaryTreePaths(root.left)) {
            paths.add(root.val + "->" + path);
        }
        for (String path : binaryTreePaths(root.right)) {
            paths.add(root.val + "->" + path);
        }
        return paths;
    }

    /**
     * 输出根节点到叶子的路径
     * 方法三：不用递归，而使用队列（BFS）或者栈（深度优先），维持节点和字符串两个队列（或栈），每访问一个节点，相应字符串进行同步更改
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths3(TreeNode root) {
        List<String> list = new ArrayList<String>();
        Queue<TreeNode> qNode = new LinkedList<TreeNode>();
        Queue<String> qStr = new LinkedList<String>();
        if (root == null) {
            return list;
        }
        qNode.add(root);
        qStr.add("");
        while (!qNode.isEmpty()) {
            TreeNode curNode = qNode.remove();
            String curStr = qStr.remove();
            if (curNode.left == null && curNode.right == null) {
                list.add(curStr + curNode.val);
            }
            if (curNode.left != null) {
                qNode.add(curNode.left);
                qStr.add(curStr + curNode.val + "->");
            }
            if (curNode.right != null) {
                qNode.add(curNode.right);
                qStr.add(curStr + curNode.val + "->");
            }
        }
        return list;
    }

    /**
     * 后序编历二叉树  - 递归
     *
     * @param node
     */
    public void LRDTreeNode(TreeNode node) {
        if (Objects.isNull(node)) {
            return;
        }
        LRDTreeNode(node.left);
        LRDTreeNode(node.right);
        System.out.print(node.val + "-");

    }
    /**
     * 后序遍历二叉树  - 非递归
     *
     * @param node
     */
    public void LRDTreeNodeForLoop(TreeNode node) {
        if (Objects.isNull(node)) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> resultList = new ArrayList<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode t = stack.pop();
            boolean endLeft = false;
            boolean endRight = false;
            if (Objects.isNull(t.left)) {
                endLeft = true;
            } else if (resultList.contains(t.left)) {
                endLeft = true;
            }
            if (Objects.isNull(t.right)) {
                endRight = true;
            } else if (resultList.contains(t.right)) {
                endRight = true;
            }
            //两种情况： 1、两个子节点都为空， 2、子节点不为空但在结果数组中存在
            if (endLeft && endRight) {
                resultList.add(t);
                continue;
            }
            stack.push(t);
            if (!endRight) {
                stack.push(t.right);
            }
            if (!endLeft) {
                stack.push(t.left);
            }
        }
        resultList.forEach(treeNode -> {
            System.out.print(treeNode.val + "-");
        });

    }

    public static void main(String[] args) {
        TreeNodeTest treeNodeTest = new TreeNodeTest();
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        root.left = n2;
        root.right = n3;

        n2.left = n4;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n4.left = n8;

        //treeNodeTest.LRDTreeNode(root);
        treeNodeTest.LRDTreeNodeForLoop(root);


    }


}
