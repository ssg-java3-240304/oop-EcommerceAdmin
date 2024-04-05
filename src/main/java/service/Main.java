package service;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int pivot=0;
        int max=Integer.MIN_VALUE;
        int[] a=new int[9];

        for(int i=0;i<9;i++)
        {
            a[i]=sc.nextInt();
            sc.nextLine();
            if(a[i]>max)
            {
                max=a[i];
                pivot=i+1;
            }

        }
        System.out.printf("%d\n%d",max,pivot);
    }
}
