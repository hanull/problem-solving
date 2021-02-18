package Basic.Lamda;

public class lamdaTest {

    public static void main(String[] args) {
        My my = () -> {
            int res = 0;
            return res;
        };
        System.out.println(my.test());
    }
}
