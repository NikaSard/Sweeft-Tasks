import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
}

public class Main {
    public static void main(String[] args) {
        // Task 1
        System.out.println(evaluateExpression("5+200-38+5"));


        // Task 2
        List<String> strs = new ArrayList<String>();
        strs.add("abc");
        strs.add("abbcc");
        strs.add("cabcbb");
        strs.add("abcabc");
        System.out.println(numberOfHappyStrings(strs));


        // Task 3
        reverseList(null);

        // Task 4
        int[] nums1 = {1, 2, 3, 3, 4, 5};
        int[] nums2 = {3, 4, 4, 5, 6, 7};
        int[] result = findIntersection(nums1, nums2);

        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Task 5
        int[] array = {6, 2, 2, 3, 4, 1};
        int k = 8;
        int result1 = lenOfLongSubarr(array, k);
        System.out.println(result1);

        // Task 6
        int[] array1 = {5, 1, 22, 25, 6, -1, 8, 10};
        int[] sequence = {1, 6, -1, 10};

        boolean result2 = isValidSequence(array1, sequence);
        System.out.println(result2);

    }

    static int evaluateExpression(String expression) {
        int lastNumIdx = 0, sum = 0, operator = 1;
        for(int i = 0; i < expression.length(); i++) {
            while(i < expression.length() && expression.charAt(i) != '+' && expression.charAt(i) != '-') {
                i++;
            }

            String numberStr = expression.substring(lastNumIdx, i);
            int num = Integer.parseInt(numberStr);
            sum += num * operator;

            if(i < expression.length() && expression.charAt(i) == '+') operator = 1;
            else operator = -1;

            lastNumIdx = i + 1;

        }
        return sum;
    }

    static boolean isHappyString(String string){
        for(int i = 1; i < string.length(); i++) {
            if(string.charAt(i) == string.charAt(i-1)) return false;
        }
        return true;
    }
    static int numberOfHappyStrings(List<String> strings){
        int sum = 0;
        for(String str : strings){
            if(isHappyString(str)) sum++;
        }
        return sum;
    }

    static ListNode reverseList(ListNode head) {
        ListNode prevNode = null, currNode = head;
        while (currNode != null) {
            ListNode next = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = next;
        }
        return prevNode;
    }

    static int[] findIntersection(int[] nums1, int[] nums2){
        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for(int i = 0; i < nums1.length; i++){
            map.put(nums1[i], true);
        }
        List<Integer> intersection = new ArrayList<>();
        for(int i = 0; i < nums2.length; i++){
            if(map.containsKey(nums2[i])){
                intersection.add(nums2[i]);
                map.remove(nums2[i]);
            }
        }
        return intersection.stream().mapToInt(i -> i).toArray();
    }

    // using dynamic programming method, we can find for every i from 1 to k what is the maximum
    // number of elements which sums up to i
    // for space optimization I used 1D array, instead of 2D
    static int lenOfLongSubarr(int[] array, int k) {
        int n = array.length;
        int[] dp = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            dp[i] = -1;
        }
        dp[0] = 0;

        for (int num : array) {
            for (int i = k; i >= num; i--) {
                if (dp[i - num] != -1) {
                    dp[i] = Math.max(dp[i], dp[i - num] + 1); // checking if answer for (i - sum) + 1 is more than current sum and writing best in dp[i]
                }
            }
        }

        if(dp[k] == -1) return 0;
        return dp[k];
    }

    static boolean isValidSequence(int[] array, int[] sequence){
        int i = 0, j = 0;
        while(i < array.length && j < sequence.length){
            if(array[i] == sequence[j]){
                j++;
            }
            i++;
        }
        if(j == sequence.length) return true;
        return false;
    }
}