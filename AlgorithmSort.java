/**
 * Created by dell on 2017/8/2.
 */
public class AlgorithmSort {
    public static void main(String[] args) {
        int[] arr = {59, 20, 17, 13, 28, 14, 23, 83};
        System.out.println("原始数组为：");
        showArr(arr);
        //selectionSort(arr);
        //insertSort(arr);
        //shellSort(arr);
        //bubbleSort(arr);
        //quickSort(arr);
        //mergeSort(arr);
        heapSort(arr);
    }

    /**
     * 堆排序，每次建立最大堆，将堆顶元素与数组最后元素交换，对前面元素重新进行堆排序得到第二大值，以此类推
     * O(nlogn)  O(nlogn)  O(nlogn) O(1) 不稳定
     */
    private static int heapsize;

    private static void heapSort(int[] arr) {
        int N = arr.length;
        heapSortMain(arr, N);
        System.out.println("堆排序结果：");
        showArr(arr);
    }

    private static void heapSortMain(int[] arr, int n) {
        buildHeap(arr, n);
        for (int i = n - 1; i >= 1; i--) {
            exchange(arr, 0, i);
            heapsize--;
            heapify(arr, 0);
        }
    }

    private static void buildHeap(int[] arr, int n) {
        heapsize = n;
        //对每个非叶子结点，不断的堆调整
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
    }

    private static void heapify(int[] arr, int i) {
        int leftchild = 2 * i + 1;
        int rightchild = 2 * i + 2;
        int largest = i;
        if (leftchild < heapsize && arr[leftchild] > arr[largest])
            largest = leftchild;
        if (rightchild < heapsize && arr[rightchild] > arr[largest])
            largest = rightchild;
        if (largest != i) {
            exchange(arr, i, largest);
            heapify(arr, largest);
        }
    }

    /**
     * 递归将数组分为两个子数组分别排序，并将有序的子数组归并从而使得整个数组有序
     * O(nlogn)  O(nlogn)  O(nlogn) O(n) 稳定
     */
    private static void mergeSort(int[] arr) {
        int N = arr.length;
        mergeSortRecursion(arr, 0, N - 1);
        System.out.println("归并排序结果：");
        showArr(arr);
    }

    //递归实现的归并算法
    private static void mergeSortRecursion(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRecursion(arr, left, mid);
            mergeSortRecursion(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int len = right - left + 1;
        int[] tmp = new int[len];
        int index = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            tmp[index++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid)
            tmp[index++] = arr[i++];
        while (j <= right)
            tmp[index++] = arr[j++];
        for (int k = 0; k < len; k++)
            arr[left++] = tmp[k++];
    }

    /**
     * 快速排序，分治思想，选一元素做基准pivot
     * partition划分原数组为两个子数组（分别比基准值大和小），递归进行排序
     * O(nlogn)  O(nlogn)  O(n^2)  O(logn)-O(n) 不稳定
     * 应用最广泛，快速简单
     */
    private static void quickSort(int[] arr) {
        int N = arr.length;
        quickSortMain(arr, 0, N - 1);
        System.out.println("快速排序结果：");
        showArr(arr);
    }

    private static void quickSortMain(int[] arr, int left, int right) {
        int pivot_index;
        if (left < right) {
            pivot_index = partition(arr, left, right);
            quickSortMain(arr, left, pivot_index - 1);
            quickSortMain(arr, pivot_index + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int tail = left - 1;
        for (int i = left; i < right; i++) {
            if (arr[i] <= pivot) {
                tail++;
                exchange(arr, tail, i);
            }
        }
        exchange(arr, tail + 1, right);
        return tail + 1;
    }

    /**
     * 冒泡排序，依次比较相邻元素，每趟将大元素交换至末尾
     * 共N-1趟，每趟比较次数N-1-i（第i趟）
     * O(n^2)  O(n)  O(n^2)  O(1) 稳定
     */
    private static void bubbleSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    exchange(arr, j, j + 1);
                }
            }
        }
        System.out.println("冒泡排序结果：");
        showArr(arr);
    }

    /**
     * 为插入排序的改进。数组基本有序时，插入排序更有效。
     * 增量划分若干子序列，对子序列插入，逐渐将增量减小直至为1。
     * O(nlogn)-O(n^2)  O(n^1.3)  O(n^2)  O(1) 不稳定
     * 由于简单选择排序和直接插入排序，适用中等的N
     */
    private static void shellSort(int[] arr) {
        int N = arr.length;
        int h = 1;
        while (h < N / 3)
            h = h * 3 + 1;
        while (h >= 1) {
            //System.out.println(h);
            for (int i = h; i < N; i++) {
                int curr = arr[i];
                int j = i - h;
                while (j >= 0 && arr[j] > curr) {
                    arr[j + h] = arr[j];
                    j -= h;
                }
                arr[j + h] = curr;
            }
            h = h / 3;
        }
        System.out.println("希尔排序结果：");
        showArr(arr);
    }

    /**
     * 直接插入排序，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     * 最差时间复杂度 ---- 最坏情况为输入序列是降序排列的,此时时间复杂度O(n^2)
     * 最优时间复杂度 ---- 最好情况为输入序列是升序排列的,此时时间复杂度O(n)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 稳定
     * 适用部分有序数组和小规模数组。
     */
    private static void insertSort(int[] arr) {
        int N = arr.length;
        for (int i = 1; i < N; i++) {
            int curr = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > curr) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = curr;
        }
        System.out.println("直接插入排序结果：");
        showArr(arr);
    }

    /**
     * 简单选择排序，每次记录最小元素的位置，每轮完成后与前面元素交换
     * 最差时间复杂度 ---- O(n^2)
     * 最优时间复杂度 ---- O(n^2)
     * 平均时间复杂度 ---- O(n^2)
     * 所需辅助空间 ------ O(1)
     * 稳定性 ------------ 不稳定
     */
    private static void selectionSort(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            exchange(arr, i, min);
        }
        System.out.println("简单选择排序结果：");
        showArr(arr);
    }

    /**
     * 交换数组arr中索引为i和j的元素
     */
    private static void exchange(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 输出数组元素，空格隔开
     */
    private static void showArr(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
}
