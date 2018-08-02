package com.yrwan.findcoin;

import java.util.ArrayList;
import java.util.List;

public class Status {
    public static int RESOLVED=1, UNKNOWN=2, REDICULOUS=3, RESOLVABLE=4;  
    public int count=0;  
    public int[] data;  
    public List<Balance> parents = new ArrayList<Balance>();  
    public List<Balance> bls = new ArrayList<Balance>();  
    private int conclusion;  

    public Status(int c) {  
        count = c;  
        int[] data1 = {0,c,0,0};  
        data = data1;  
        int conc = data[0]<count-1?UNKNOWN:(data[0]==count-1?RESOLVED:REDICULOUS);  
        setConclusion(conc);  
    }  
    public Status(int[] is) {  
        data = is;  
        for (int i=0; i<is.length; i++) count+=is[i];  
        int conc = data[0]<count-1?UNKNOWN:(data[0]==count-1?RESOLVED:REDICULOUS);  
        setConclusion(conc);  
    }  
    public void addParent(Balance bl) {  
        parents.add(bl);  
        if (conclusion==RESOLVED || conclusion==RESOLVABLE || conclusion==REDICULOUS) bl.prop();  
    }  
    public String toString() {  
        return "正常" + data[0] + "、不明" + data[1] + "、或重" + data[2] + "、或轻" + data[3];  
    }  
    public void setConclusion(int conc) {  
        if (conclusion == conc) return;  
        conclusion = conc;  
        if (conclusion==RESOLVED || conclusion==RESOLVABLE || conclusion==REDICULOUS)   
            for (int i=0; i<parents.size(); i++)  
                parents.get(i).prop();  
    }  
    public int getConclusion() {return conclusion;}  
    public boolean succeed() {return conclusion==RESOLVED || conclusion==RESOLVABLE;}  
    public boolean isUnknown(){return conclusion==UNKNOWN;}  

    public void produceBalances() {//得到当前状况下所有可能的称重方案   
        List<int[]> bldata = getBalanceDataArray(data);  
        bls = new ArrayList<Balance>();  
        for (int i=0; i<bldata.size(); i++) {  
            Balance bl = new Balance(bldata.get(i));  
            bl.in = this;  
            bls.add(bl);  
        }  
    }  
    private List<int[]> getBalanceDataArray(int[] src) {  
        List<int[]> list = new ArrayList<int[]>();  
        list.add(new int[src.length*2]);  
        return getBalanceDataArray(src,0,list);  
    }  
    private List<int[]> getBalanceDataArray(int[] src, int id, List<int[]> list) {  
        int total=0,left,right;  
        if (id>=src.length) {  
            for (int i=list.size()-1; i>=0; i--) {  
                int[] is = list.get(i);  
                left=0;  
                right=0;  
                for (int j=0; j<src.length; j++) left+=is[j];  
                for (int j=src.length; j<src.length*2; j++) right+=is[j];  
                if (left!=right || left==0 || is[0]>0&&is[is.length/2]>0)  
                    list.remove(i);  
            }  
            return list;  
        }  
        List<int[]> r = new ArrayList<int[]>();  
        for (int i=0; i<src.length; i++) total += src[i];  
        int half = total/2;  
        for (int i=0; i<list.size(); i++) {  
            int[] is = list.get(i);  
            left=0;  
            right=0;  
            for (int j=0; j<src.length; j++) left+=is[j];  
            for (int j=src.length; j<src.length*2; j++) right+=is[j];  
            for (int j=0; j<=Math.min(half-left, src[id]); j++) {  
                for (int k=0; k<=Math.min(half-right, src[id]-j); k++) {  
                    int[] iis = list.get(i).clone();  
                    iis[id] = j;  
                    iis[id+src.length] = k;  
                    r.add(iis);  
                }  
            }  
        }  
        return getBalanceDataArray(src,id+1,r);  
    }  
}