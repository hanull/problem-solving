package Basic.binarySearch;


import java.util.Arrays;

public class BinarySearchTest {

    public static void main(String[] args) {
        // 이진 탐색을 할 떄는 반드시 정렬된 상태여야 한다.
        int[] arr = {3, 5, 6, 11, 15, 19, 21, 23, 25};

        System.out.println(binarySearch(arr, 15));
        System.out.println(binarySearch(arr, 15, 0, arr.length - 1));
        System.out.println(Arrays.binarySearch(arr, 15));

        // Arrays.binarySearch() : 원소를 못찻았을 경우
        // 예상되는 위치의 -값을 붙여 출력
        System.out.println(Arrays.binarySearch(arr, 14));
    }

    static int binarySearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        int mid = (start + end) / 2;

        while (start <= end) {
            if (arr[mid] == key) return mid;
            else if (arr[mid] < key) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }

    static int binarySearch(int[] arr, int key, int start, int end) {
        if (start > end) return -1;

        int mid = (start + end) / 2;
        if (arr[mid] == key) return mid;
        else if (arr[mid] < key) return binarySearch(arr, key, mid + 1, end);
        else return binarySearch(arr, key, start, mid - 1);
    }

}
