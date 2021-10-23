package Test.NHN.test2021.test1;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Operation[] operations = new Operation[7];
        operations[0] = new Operation(OperationType.branch, 0);
        operations[1] = new Operation(OperationType.branch, 0);
        operations[2] = new Operation(OperationType.branch, 0);
        operations[3] = new Operation(OperationType.branch, 0);
        operations[4] = new Operation(OperationType.branch, 0);
        operations[5] = new Operation(OperationType.branch, 0);
        operations[6] = new Operation(OperationType.branch, 0);
//        operations[1] = new Operation(OperationType.merge, 2);
//        operations[2] = new Operation(OperationType.branch, 0);
//        operations[3] = new Operation(OperationType.merge, 2);
//        operations[4] = new Operation(OperationType.branch, 0);
//        operations[5] = new Operation(OperationType.merge, 2);
//        operations[6] = new Operation(OperationType.branch, 0);
        solution(6, operations);
    }

    private static void solution(int num, Operation[] operations) {
        TreeSet<Integer> branches = new TreeSet<>();
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        for (int i = 2; i <= num + 2; i++) {
            numbers.add(i);
        }
        for (int i = 0; i < num; i++) {
            if (operations[i].type == OperationType.branch) {
                branches.add(numbers.poll());
            } else {
                int value = operations[i].value;
                branches.remove(value);
                numbers.add(value);
            }
        }
        branches.add(1);
        for (int branch : branches) {
            System.out.print(branch + " ");
        }
    }

    private  static class Operation {
        OperationType type;
        Integer value;

        public Operation(OperationType type, Integer value) {
            this.type = type;
            this.value = value;
        }
    }

    private static enum OperationType {
        branch,
        merge;
    }

}
