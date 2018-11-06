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
        List<String> list = new ArrayList<>();
        Queue<TreeNode> qNode = new LinkedList<>();
        Queue<String> qStr = new LinkedList<>();
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
     * 定义一个栈中间遍历栈，一个数组-结果数组
     * 遍历的时候将节点加入到栈，然后弹出判断该节点的子节点是否为空，或者两个子节点都在结果数据中存在，则将该节点加入到结果数组中。否则继续将该节点加入到栈，同时按照后序遍历的顺序反序（因为是中间栈，所以相反），将子节点push到中间栈中，继续以上逻辑。
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


    /**
     * 获取二叉树的全部路径
     *
     * @param node
     * @return
     */
    public List<List<TreeNode>> findAllPaths(TreeNode node) {
        List<List<TreeNode>> allPaths = new ArrayList<>();
        List<TreeNode> path = new ArrayList<>();
        findAllPathCore(node, path, allPaths);
        return allPaths;
    }

    /**
     * 定义两个数组，一个中间数组，保存遍历过程中的中间路径；
     * 一个结果数组，将符合条件的中间路径加入该数组。
     * 遍历树节点，若节点不为空，则节点加入中间数组，当判断该节点的左右孩子都为空时，则该节点到根节点的路径加入到结果数组中。若不满足，则左右孩子继续遍历。左右孩子遍历之后 ，再从中间数组中移除当前节点
     *
     * @param node
     * @param nodeList
     * @param allPaths
     */
    public void findAllPathCore(TreeNode node, List<TreeNode> nodeList, List<List<TreeNode>> allPaths) {
        if (node == null) {
            return;
        }
        nodeList.add(node);
        if (node.left == null && node.right == null) {
            List<TreeNode> path = new ArrayList<>();
            path.addAll(nodeList);
//            for(TreeNode t : nodeList){
//                path.add(t);
//            }
            allPaths.add(path);
        }
        if (node.left != null) {
            findAllPathCore(node.left, nodeList, allPaths);
        }
        if (node.right != null) {
            findAllPathCore(node.right, nodeList, allPaths);
        }
        nodeList.remove(nodeList.size() - 1);
    }

    /**
     * 求二叉树的路径和
     *
     * @param node
     * @param sum
     * @return
     */
    public List<List<TreeNode>> findSumPaths(TreeNode node, int sum) {
        List<List<TreeNode>> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        int curSum = 0;
        findSumPathCore(node, sum, curSum, stack, list);
        return list;
    }

    public void findSumPathCore(TreeNode node, int sum, int currSum, Stack<TreeNode> stack, List<List<TreeNode>> list) {
        if (node == null) {
            return;
        }
        currSum += node.val;
        stack.push(node);
        boolean isLeaf = node.left == null && node.right == null;
        if (currSum == sum && isLeaf) {
            List<TreeNode> tempPath = new ArrayList<>();
            tempPath.addAll(stack);
//            for(TreeNode t : stack){
//                tempPath.add(t);
//            }
            list.add(tempPath);
        }
        if (node.left != null) {
            findSumPathCore(node.left, sum, currSum, stack, list);
        }
        if (node.right != null) {
            findSumPathCore(node.right, sum, currSum, stack, list);
        }
        currSum -= node.val;
        stack.pop();
    }

    /**
     * 按层遍历树
     *
     * @param node
     * @return
     */
    public List<TreeNode> findNodesForLayer(TreeNode node) {
        List<TreeNode> list = new ArrayList<>();
        if (node != null) {
            ArrayDeque<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
            findNodesForLayerCore(node, nodeQueue, list);
        }
        return list;
    }

    public void findNodesForLayerCore(TreeNode node, ArrayDeque<TreeNode> nodeQueue, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        nodeQueue.add(node);
        while (!nodeQueue.isEmpty()) {
            TreeNode tempNode = nodeQueue.poll();
            list.add(tempNode);
            if (null != tempNode.left) {
                nodeQueue.add(tempNode.left);
            }
            if (null != tempNode.right) {
                nodeQueue.add(tempNode.right);
            }
        }
    }

    /**
     * 按层遍历树 , 按层分隔
     *
     * @param node
     * @return
     */
    public List<TreeNode> findNodesForLayerNewLine(TreeNode node) {
        List<TreeNode> list = new ArrayList<>();
        if (node != null) {
            ArrayDeque<TreeNode> nodeQueue = new ArrayDeque<TreeNode>();
            findNodesForLayerNewLineCore(node, nodeQueue, list);
        }
        return list;
    }

    public void findNodesForLayerNewLineCore(TreeNode node, ArrayDeque<TreeNode> nodeQueue, List<TreeNode> list) {
        if (node == null) {
            return;
        }
        int curLayerNodeCount = 1;
        int layerNodeCount = 0;
        nodeQueue.add(node);
        while (!nodeQueue.isEmpty()) {
            TreeNode tempNode = nodeQueue.poll();
            list.add(tempNode);
            curLayerNodeCount--;
            if (null != tempNode.left) {
                nodeQueue.add(tempNode.left);
                layerNodeCount++;
            }
            if (null != tempNode.right) {
                nodeQueue.add(tempNode.right);
                layerNodeCount++;
            }
            if (curLayerNodeCount == 0 && !nodeQueue.isEmpty()) {
                curLayerNodeCount = layerNodeCount;
                layerNodeCount = 0;
                //用-1标记换行
                list.add(new TreeNode(-1));
            }
        }
    }


    /**
     * 判断是否为平衡二叉树
     * 方法1：通过判断树的深度。该方法会有节点重复计算的问题
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getTreeDepth(root.left);
        int right = getTreeDepth(root.right);
        int diff = left - right;
        if (diff > 1 || diff < -1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getTreeDepth(root.left);
        int right = getTreeDepth(root.right);
        return 1 + (right > left ? right : left);
    }

    /**
     * 判断是否为平衡二叉树
     * 方法2：通过类似于后序遍历的方式
     *
     * @param root
     * @return
     */
    Boolean isBalance = true;
    public boolean isBalanced2(TreeNode root) {
        getTreeDepth2(root);
        return isBalance;
        //isBalance 会在 TreeDepth1(root)中赋值。
    }

    public int getTreeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getTreeDepth2(root.left);
        //左子树高度
        int right = getTreeDepth2(root.right);
        //右子树高度
        if (Math.abs(left - right) > 1) {
            //只要有一个子树的左右子树的高度绝对值大于 1 isBalance=false
            isBalance = false;
        }
        return Math.max(left, right) + 1;
    }
    /**
     * 判断是否为平衡二叉树
     * 方法3：通过类似于后序遍历的方式, 通过创建对象引用的方式，将值传递进去
     *
     * @return
     */
    class TreeDepth {
        int depth;

        public TreeDepth(int depth) {
            this.depth = depth;
        }


        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
    }

    public boolean isBalanced3(TreeNode root) {
        TreeDepth depth = new TreeDepth(0);
        return isBalanced3Core(root, depth);
    }

    public boolean isBalanced3Core(TreeNode root, TreeDepth depth) {
        if (root == null) {
            depth.setDepth(0);
            return true;
        }
        TreeDepth left = new TreeDepth(0);
        TreeDepth right = new TreeDepth(0);
        if (isBalanced3Core(root.left, left) && isBalanced3Core(root.right, right)) {
            int diff = left.getDepth() - right.getDepth();
            if (diff <= 1 && diff >= -1) {
                depth.setDepth(1 + Math.max(left.getDepth(),right.getDepth()));
                return true;
            }
        }
        return false;
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
        //treeNodeTest.LRDTreeNodeForLoop(root);

        List<List<TreeNode>> lists = new ArrayList<>();
        lists = treeNodeTest.findAllPaths(root);
        lists = treeNodeTest.findSumPaths(root, 8);
        System.out.println("============");

        List<TreeNode> nodes = treeNodeTest.findNodesForLayer(root);
        nodes = treeNodeTest.findNodesForLayerNewLine(root);
        System.out.println("============");
        boolean isBalance1 = treeNodeTest.isBalanced(root);
        boolean isBalance2 = treeNodeTest.isBalanced2(root);
        boolean isBalance3 = treeNodeTest.isBalanced3(root);
        System.out.println("============");
    }


}
