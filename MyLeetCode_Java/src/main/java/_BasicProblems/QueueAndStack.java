package _BasicProblems;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class QueueAndStack {

    public static void Print(List<Integer> list) {
        for(int val : list) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    // Stack继承自Vector，使用简单数组存储结构
    public static void orgStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        for(int val : stack) {
            System.out.print(val + " ");
        }
        System.out.println();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    public static void myStack1() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);
        stack.addFirst(4);
        stack.addFirst(5);
        Print(stack);
        System.out.println(stack.pollFirst());
        System.out.println(stack.pollFirst());
    }

    public static void myStack2() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        Print(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    public static void main(String[] args) {
        orgStack();
        System.out.println("===========");
        myStack1();
        System.out.println("===========");
        myStack2();
    }
}
