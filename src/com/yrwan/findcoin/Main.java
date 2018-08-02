package com.yrwan.findcoin;

import java.util.ArrayList;  
import java.util.List;  
import java.util.Scanner;

public class Main {
    static int round = 1;  
    static int maxSteps;  
    public static void run(Status root, List<Status> list) { //求解  
//        long time = System.currentTimeMillis();  
        List<Status> newlist = new ArrayList<Status>();   
        for (int i=0; i<list.size(); i++) {  
            Status status = list.get(i);  
            status.produceBalances();  
            for (int j=0; j<status.bls.size(); j++) {  
                Balance bl = status.bls.get(j);  
                bl.weight();  
                if (root.succeed()) {  
//                    System.out.println("第" + round + "轮: 计算至上轮第" + (i+1) + "个节点得解，之前获得节点" + newlist.size() + "个，用时" + (double)(System.currentTimeMillis()-time)/1000 + "秒");
                    return;  
                }  
                if (bl.out1.isUnknown()) newlist.add(bl.out1);  
                if (bl.out2.isUnknown()) newlist.add(bl.out2);  
                if (bl.out3.isUnknown()) newlist.add(bl.out3);  
            }  
        }  
  //      System.out.println("第" + round + "轮: 获得节点" + newlist.size() + "个，用时" + (double)(System.currentTimeMillis()-time)/1000 + "秒");  
        round++;  
        run(root, newlist);   
    }  
    public static void print(Status st, int depth) { //输出结果  
        String indent="";  
        for (int i=0; i<depth-1; i++) indent = indent+"t";   
        Balance bl=null;  
        for (int i=0; i<st.bls.size(); i++)   
            if (st.bls.get(i).unresolved==0) bl=st.bls.get(i);  
        if (bl!=null) {  
            if (depth>maxSteps) maxSteps=depth;  
            System.out.println(indent + "第" + depth + "步称重: " + bl + "rn");  
            System.out.println(indent + "如果一样重: " + bl.out1 + (bl.out1.getConclusion()==Status.RESOLVED?"  *解决*":(bl.out1.getConclusion()==Status.REDICULOUS?"  ×不可能×":"")) + "rn");  
            print(bl.out1, depth+1);  
            System.out.println(indent + "如果左边重: " + bl.out2 + (bl.out2.getConclusion()==Status.RESOLVED?"  *解决*":(bl.out2.getConclusion()==Status.REDICULOUS?"  ×不可能×":"")) + "rn");  
            print(bl.out2, depth+1);  
            System.out.println(indent + "如果右边重: " + bl.out3 + (bl.out3.getConclusion()==Status.RESOLVED?"  *解决*":(bl.out3.getConclusion()==Status.REDICULOUS?"  ×不可能×":"")) + "rn");  
            print(bl.out3, depth+1);  
        }  
    }  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  
        System.out.println("请输入硬币个数：");  
        int n = sc.nextInt();  
        sc.close();
        Status root = new Status(n);  
        ArrayList<Status> list = new ArrayList<Status>();  
        list.add(root);  
//        System.out.println("***** 开始求解*****");  
        run(root, list);  
        System.out.println("***** 求解步骤*****");  
        maxSteps = 0;  
        print(root, 1);  
        System.out.println("***** 最少" + maxSteps + "步可找出假币*****");  
    }
}
