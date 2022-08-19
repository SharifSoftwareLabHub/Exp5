import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Finder {
    public static void main(String[] args) {
        int s = 100000;
        int sum = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Sum:");
        sum = scanner.nextInt();
        ArrayList<Integer> al = new ArrayList<>(s);
        for (int i = 0; i < s; i++) {
            if (i == s / 2) {
                al.add(3);
            } else if(i == s - 1) {
                al.add(5);
            } else {
                al.add(20);
            }
        }
        int[] result = alternative(sum, al);
        if (result == null) {
            System.out.println("Not found!");
        } else {
            System.out.printf("%d in index: %d,    %d in index: %d\n", al.get(result[0]), result[0], al.get(result[1]), result[1]);
        }
    }

    public static int[] findTwoNumberWithSpecialSum(int sum, ArrayList<Integer> al) {
        for (int i = 0; i < al.size(); i++) {
            for (int j = 0; j < al.size(); j++) {
                if (i != j && (al.get(i) + al.get(j) == sum)) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] alternative(int sum, ArrayList<Integer> al) {
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i) > sum)
                continue;
            for (int j = i + 1; j < al.size(); j++) {
                if (al.get(i) + al.get(j) == sum) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
