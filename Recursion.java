package 递归;

/**
 * @author 吕二宁
 * @version 1.0
 * @description: TODO
 * @date 2022/2/26 17:45
 */
public class Recursion {
    public static void main(String[] args) {
        System.out.println(sum(3));
    }

    public static int sum(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sum(n - 1);
    }
}
