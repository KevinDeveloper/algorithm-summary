package com.xiaomi.browser.security.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author liufangtao
 * @date 2023-12-20 14:21
 */
public class Alg1ForMergeArray {


    public static void main(String[] args) {

        int[] arrayA = new int[]{2,0};
        int[] arrayB = new int[]{1};
        //int[] mergedArray = getMergedArray(arrayA, arrayB);

        getMergedArrayV2(arrayA, 1, arrayB, 1);
        System.out.println(Arrays.toString(arrayA));

//        List<Integer> listA = Lists.newArrayList(1, 3, 5, 7, 9, 11, 13, 15, 17);
//        List<Integer> listB = Lists.newArrayList(0, 2, 4, 6, 8, 10, 12);
//        List<Integer> listMerged = getMergedList(listA, listB);
//        System.out.println(JsonUtil.toJsonIgnoreIsSet(listMerged));
    }

    private static List<Integer> getMergedList(List<Integer> listA, List<Integer> listB) {
        List<Integer> listMerged = new ArrayList<>(listA.size() + listB.size());
        int totalSize = listA.size() + listB.size();
        int rightIndex = totalSize - 1;
        int rightIndexA = listA.size() - 1;
        int rightIndexB = listB.size() - 1;
        while (rightIndex >= 0) {
            if (listA.get(rightIndexA) > listB.get(rightIndexB)) {
                listMerged.add(rightIndex, listA.get(rightIndexA));
                if (rightIndexA > 0) {
                    rightIndexA--;
                }
            } else {
                listMerged.add(rightIndex, listB.get(rightIndexB));
                if (rightIndexB > 0) {
                    rightIndexB--;
                }
            }
            rightIndex--;
        }

        return listMerged;
    }


    private static int[] getMergedArray(int[] arrayA, int[] arrayB) {
        if (Objects.isNull(arrayA) || arrayA.length < 1) {
            return arrayB;
        }
        if (Objects.isNull(arrayB) || arrayB.length < 1) {
            return arrayA;
        }
        int[] mergedArray = new int[arrayA.length + arrayB.length];
        int indexMerged = 0;

        int indexA = 0;
        int indexB = 0;

        while (indexMerged < mergedArray.length) {
            if (arrayA[indexA] <= arrayB[indexB]) {
                mergedArray[indexMerged] = arrayA[indexA];

                if (indexA == arrayA.length - 1) {
                    indexMerged++;
                    break;
                } else {
                    indexA++;
                }
            } else {
                mergedArray[indexMerged] = arrayB[indexB];
                if (indexB == arrayB.length - 1) {
                    indexMerged++;
                    break;
                }
                indexB++;
            }
            indexMerged++;
        }
        if (indexA == arrayA.length - 1) {
            while (indexB < arrayB.length) {
                mergedArray[indexMerged++] = arrayB[indexB++];
            }
        }
        if (indexB == arrayB.length - 1) {
            while (indexA < arrayA.length) {
                mergedArray[indexMerged++] = arrayA[indexA++];
            }
        }
        return mergedArray;

    }

    private static void getMergedArrayV2(int[] num1, int m, int[] num2, int n) {
        int rightIndex1 = m - 1 ;
        int rightIndex2 = n - 1;
        int rightIndex = num1.length - 1;
        int curValue;
        while (rightIndex1 >= 0 || rightIndex2 >= 0) {

            if(rightIndex1 < 0){
                curValue = num2[rightIndex2--];
            }else if( rightIndex2 < 0){
                curValue = num1[rightIndex1--];
            }else if(num1[rightIndex1] > num2[rightIndex2]) {
                curValue = num1[rightIndex1--];
            }else{
                curValue =num2[rightIndex2--];
            }
            num1[rightIndex--] = curValue;
        }
    }


}
