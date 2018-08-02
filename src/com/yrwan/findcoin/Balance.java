package com.yrwan.findcoin;

public class Balance {
    public int[] data;  
    public Status in,out1,out2,out3;   
    public int unresolved = 3;  

    public Balance(int[] data) {  
        this.data = data.clone();  
    }  
    public void weight() {//称重量，推理出三种可能的结果   
        int[] temp;  
        // 一样重   
        temp = in.data.clone();  
        for (int i=1; i<4; i++) { //所有参与称重的硬币都移入正常硬币集合   
            temp[0] = temp[0] + data[i] + data[i+4];  
            temp[i] = temp[i] - data[i] - data[i+4];  
        }  
        out1 = new Status(temp);  
        out1.addParent(this);  

        //左边重   
        temp = in.data.clone();  
        for (int i=1; i<4; i++) {  
            temp[0] = temp[0] + temp[i] - data[i] - data[i+4]; //未参与称重的硬币  -->> 正常硬币   
        }  
        temp[0] += data[3] + data[6]; //左边的疑似轻硬币、右边的疑似重硬币  -->> 正常硬币   
        temp[1] = 0;  
        temp[2] = data[1] + data[2]; //左边的不明轻重硬币移入疑似重硬币集合   
        temp[3] = data[5] + data[7]; //右边的不明轻重硬币移入疑似轻硬币集合   
        out2 = new Status(temp);  
        out2.addParent(this);  

        //右边重   
        temp = in.data.clone();  
        for (int i=1; i<4; i++) {  
            temp[0] = temp[0] + temp[i] - data[i] - data[i+4]; //未参与称重的硬币  -->> 正常硬币   
        }  
        temp[0] += data[2] + data[7]; //左边的疑似重硬币、右边的疑似轻硬币  -->> 正常硬币   
        temp[1] = 0;  
        temp[2] = data[5] + data[6]; //右边的不明轻重硬币移入疑似重硬币集合   
        temp[3] = data[1] + data[3]; //左边的不明轻重硬币移入疑似轻硬币集合   
        out3 = new Status(temp);  
        out3.addParent(this);  
    }  
    public String toString(){  
        return "（" + (data[0]>0?"正常硬币×"+data[0]+"个 ":"") + (data[1]>0?"不明硬币×"+data[1]+"个 ":"")   
        +(data[2]>0?"疑似重硬币×"+data[2]+"个 ":"") + (data[3]>0?"疑似轻硬币×"+data[3]+"个 ":"")      
        + "） --天平-- （"  
        + (data[4]>0?"正常硬币×"+data[4]+"个 ":"") + (data[5]>0?"不明硬币×"+data[5]+"个 ":"")   
        +(data[6]>0?"疑似重硬币×"+data[6]+"个 ":"") + (data[7]>0?"疑似轻硬币×"+data[7]+"个 ":"") + "）";     
    }  
    public void prop() {  
        if (unresolved <= 0) return;  
        unresolved--;  
        if (unresolved == 0) in.setConclusion(Status.RESOLVABLE);  
    }  
}