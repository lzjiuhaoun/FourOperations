package cn.lzj66.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Node {
    String value;
    Node leftChild;
    Node rightChild;
    Node(String value){
        this.value = value;
    }
    public void display(String value){
        System.out.print(value);
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * 后序遍历
     * @param node
     */
    public void postOrderTraverse(Node node){
        if(node == null){
            return ;
        }
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        node.display(node.value);
    }

    /**
     * 后序遍历非递归
     */
    public List<String> postOrderByStack(Node node){
        Stack<Node> stack = new Stack<Node>();
        Node current = node;
        Node preNode = null;
        List<String> list = new ArrayList<String>();
        while(current != null || !stack.isEmpty()){
            while (current != null){
                stack.push(current);
                current = current.leftChild;
            }
            if(!stack.isEmpty()){
                current = stack.peek().rightChild;
                if(current == null || current == preNode){
                    current = stack.pop();
                    list.add(current.value);
                    preNode = current;
                    current = null;
                }
            }
        }
        return list;
    }
}
