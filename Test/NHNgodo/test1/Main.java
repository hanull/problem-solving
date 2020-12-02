package Test.NHNgodo.test1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] goods = {46, 62, 9};
        System.out.println(sol.solution(goods));
    }

}

//핵심 소스코드의 설명을 주석으로 작성하면 평가에 큰 도움이 됩니다.
class Solution{

    public int solution(int[] goods){
        int total = 0;
        Arrays.sort(goods);
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (goods[i] >= 50) cnt++;
        }
        if (cnt == 3) {
            total = goods[0] + goods[1] + goods[2] - 30;
        } else if (cnt == 2) {
            total = goods[0] + goods[1] + goods[2] - 20;
        } else if (cnt == 1) {
            int tmp = goods[0] + goods[1];
            tmp = tmp >= 50 ? tmp - 10 : tmp;
            total = tmp + goods[2] - 10;
        } else {
            total = goods[0] + goods[1] + goods[2];
            total = total >= 50 ? total - 10 : total;
        }
        return total;
    }
}
