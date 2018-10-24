package com.kevin.algorithm.algosummary.algoDemo;

import java.util.Arrays;

/**
 * @ClassName: SortUtilTest
 * @Description:
 * @Author: Kevin
 * @Date: 2018/9/25 10:59
 */
public class SortUtilTest {

    /**
     * 冒泡排序，每次都把第i大的数排到后面，相邻两个进行比较，时间复杂度是 n*n
     *
     * @param data
     */
    public static void buddleSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }

    }

    /**
     * 选择排序，每次都把数组中比较小的数选出来，然后和第i位进行交换，时间复杂度为n*n
     *
     * @param data
     */
    public static void selectSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[minIndex] > data[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }

    }

    /**
     * 插入排序，每次都把一个数当作需要插入的数，然后把待插入的数据都往后移动一位，空出一位待插入的数据来。 时间复杂度：n*n
     *
     * @param data
     */
    public static void insertSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < i; j++) {
                if (data[i] < data[j]) {
                    // 保存插入元素
                    int temp = data[i];
                    for (int m = i; m > j; m--) {
                        data[m] = data[m - 1];
                    }
                    // 插入元素
                    data[j] = temp;
                }
            }
        }
    }

    /**
     * 快速排序算法，总思路是设定一个中间值，把整个数据分成左右大小两部分，然后左右大小分别递归如此。
     * 具体实现：设置两个指针，一头一尾，分别通过与设定的中间值向中间遍历，找到第一个比中间大的，与第一个比中间小的，然后交换它们两个的值。
     * 时间复杂度为O(nlog2n)
     *
     * @param data
     * @param start
     * @param end
     */
    public static void quickSort(int[] data, int start, int end) {
        if (start >= end) {
            return;
        }
        int midIndex = partition(data, start, end);
        System.out.println("midIndex = "+midIndex+", data="+ Arrays.toString(data));
        // 递归左子序列
        quickSort(data, start, midIndex - 1);
        // 递归右子序列
        quickSort(data, midIndex + 1, end);

    }


    public static int partition(int[] data, int start, int end){
        int middleNum = data[start];
        int low = start;
        int high = end;
        while (low < high) {
            while (low < high && middleNum <= data[high] ) {
                high--;
            }
            if (low < high) {
                data[low] = data[high];
                low++;
            }
            while (low < high && middleNum >= data[low] ) {
                low++;
            }
            if (low < high) {
                data[high] = data[low];
                high--;
            }
        }
        data[high] = middleNum;
        return high;
    }

    /**
     * 归并排序就是把按中间分成两部分，然后进行排序，最后把左右两部分排好序的数组再合成一个。 时间复杂度为:N * log N
     *
     * @param data
     */
    public static void mergeSort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    private static void sort(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        int center = (left + right) / 2;
        // 对左边数组进行递归
        sort(data, left, center);
        // 对右边数组进行递归
        sort(data, center + 1, right);
        // 合并
        merge(data, left, center, right);
    }

    private static void merge(int[] data, int left, int center, int right) {
        int[] tempData = new int[data.length];
        int mid = center + 1;
        int index = left;
        int startLeft = left;
        while (left <= center && mid <= right) {
            if (data[left] <= data[mid]) {
                tempData[index] = data[left];
                index++;
                left++;
            } else if (data[left] > data[mid]) {
                tempData[index] = data[mid];
                index++;
                mid++;
            }
        }
        // 剩余部分依次放入中间数组
        //左边数组有剩余
        while (left <= center) {
            tempData[index] = data[left];
            index++;
            left++;
        }
        //右边数组有剩余
        while (mid <= right) {
            tempData[index] = data[mid];
            index++;
            mid++;
        }
        //将排好序的临时数组赋值到原数组的对应下标中
        while (startLeft <= right) {
            data[startLeft] = tempData[startLeft];
            startLeft++;
        }

    }

    // ==========================================网络
    public static void quickSort_net(int[] data, int start, int end) {

        if (start < end) {
            // 以第一个元素为分界值
            int middleNum = data[start];
            int i = start;
            int j = end;
            while (i < j) {
                // 找到大于分界值的元素的索引或者已经到了end处
                while (i < end && data[i] <= middleNum) {
                    i++;
                }
                ;
                // 找到小于分界值的元素的索引或者已经到了start处
                while (j > start && data[j] >= middleNum) {
                    j--;
                }
                ;
                if (i < j) {
                    // 交换
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
            data[j] = middleNum;
            // 递归左子序列
            quickSort_net(data, start, j - 1);
            // 递归右子序列
            quickSort_net(data, j + 1, end);
        }
    }

    public static void mergeSort_net(int[] data) {
        sort_net(data, 0, data.length - 1);
    }

    public static void sort_net(int[] data, int left, int right) {
        if (left < right) {
            // 找出中间索引
            int center = (left + right) / 2;
            // 对左边数组进行递归
            sort_net(data, left, center);
            // 对右边数组进行递归
            sort_net(data, center + 1, right);
            // 合并
            merge_net(data, left, center, right);
        }
    }

    private static void merge_net(int[] data, int left, int center, int right) {
        // 定义一个与待排序序列长度相同的临时数组
        int[] tmpArr = new int[data.length];
        int mid = center + 1;
        // third记录中间数组的索引
        int third = left;
        int tmp = left;
        while (left <= center && mid <= right) {
            // 从两个数组中取出小的放入中间数组
            if (data[left] <= data[mid]) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }
        // 剩余部分依次放入中间数组
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }
        // 将中间数组中的内容复制拷回原数组
        // (原left～right范围的内容复制回原数组)
        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
    }

    public static void main(String[] args) {
        int[] dd={9,7,5,3,1,2,4,6,8,10};
        quickSort(dd,0, dd.length-1);
        System.out.println("init dd="+ Arrays.toString(dd));
        //mergeSort(dd);
        //System.out.println("dd="+ Arrays.toString(dd));
    }



}
