package LeetCode.JZOffer;

import java.util.LinkedList;

public class Problem_JZ9_Cqueue {

    private LinkedList<Integer> stack1;
    private LinkedList<Integer> stack2;

    public Problem_JZ9_Cqueue() {
        stack1 = new LinkedList<>();
        stack2= new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.addLast(value);
    }

    public int deleteHead() {
        if(stack1.isEmpty()&&stack2.isEmpty()) {
            return -1;
        }
        if(stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.addLast(stack1.pollLast());
            }
        }
        return stack2.pollLast();
    }
}
