package _Test;

import java.util.LinkedList;
import java.util.Stack;

public class StackTest {

    public static void stack0() {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public static void stack01() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    // 尾部追加尾部弹出
    public static void stack1() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addLast(1);
        stack.addLast(2);
        stack.addLast(3);
        while(!stack.isEmpty()) {
            System.out.print(stack.pollLast() + " ");
        }
        System.out.println();
    }

    // 头部追加, 头部弹出
    public static void stack2() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    // 头部追加, 头部弹出
    public static void stack22() {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);
        while(!stack.isEmpty()) {
            System.out.print(stack.pollFirst() + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        stack0();
        stack01();
        stack1();
        stack2();
        stack22();
    }
}
