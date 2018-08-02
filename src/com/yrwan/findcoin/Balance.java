package com.yrwan.findcoin;

public class Balance {
    public int[] data;  
    public Status in,out1,out2,out3;   
    public int unresolved = 3;  

    public Balance(int[] data) {  
        this.data = data.clone();  
    }  
    public void weight() {//����������������ֿ��ܵĽ��   
        int[] temp;  
        // һ����   
        temp = in.data.clone();  
        for (int i=1; i<4; i++) { //���в�����ص�Ӳ�Ҷ���������Ӳ�Ҽ���   
            temp[0] = temp[0] + data[i] + data[i+4];  
            temp[i] = temp[i] - data[i] - data[i+4];  
        }  
        out1 = new Status(temp);  
        out1.addParent(this);  

        //�����   
        temp = in.data.clone();  
        for (int i=1; i<4; i++) {  
            temp[0] = temp[0] + temp[i] - data[i] - data[i+4]; //δ������ص�Ӳ��  -->> ����Ӳ��   
        }  
        temp[0] += data[3] + data[6]; //��ߵ�������Ӳ�ҡ��ұߵ�������Ӳ��  -->> ����Ӳ��   
        temp[1] = 0;  
        temp[2] = data[1] + data[2]; //��ߵĲ�������Ӳ������������Ӳ�Ҽ���   
        temp[3] = data[5] + data[7]; //�ұߵĲ�������Ӳ������������Ӳ�Ҽ���   
        out2 = new Status(temp);  
        out2.addParent(this);  

        //�ұ���   
        temp = in.data.clone();  
        for (int i=1; i<4; i++) {  
            temp[0] = temp[0] + temp[i] - data[i] - data[i+4]; //δ������ص�Ӳ��  -->> ����Ӳ��   
        }  
        temp[0] += data[2] + data[7]; //��ߵ�������Ӳ�ҡ��ұߵ�������Ӳ��  -->> ����Ӳ��   
        temp[1] = 0;  
        temp[2] = data[5] + data[6]; //�ұߵĲ�������Ӳ������������Ӳ�Ҽ���   
        temp[3] = data[1] + data[3]; //��ߵĲ�������Ӳ������������Ӳ�Ҽ���   
        out3 = new Status(temp);  
        out3.addParent(this);  
    }  
    public String toString(){  
        return "��" + (data[0]>0?"����Ӳ�ҡ�"+data[0]+"�� ":"") + (data[1]>0?"����Ӳ�ҡ�"+data[1]+"�� ":"")   
        +(data[2]>0?"������Ӳ�ҡ�"+data[2]+"�� ":"") + (data[3]>0?"������Ӳ�ҡ�"+data[3]+"�� ":"")      
        + "�� --��ƽ-- ��"  
        + (data[4]>0?"����Ӳ�ҡ�"+data[4]+"�� ":"") + (data[5]>0?"����Ӳ�ҡ�"+data[5]+"�� ":"")   
        +(data[6]>0?"������Ӳ�ҡ�"+data[6]+"�� ":"") + (data[7]>0?"������Ӳ�ҡ�"+data[7]+"�� ":"") + "��";     
    }  
    public void prop() {  
        if (unresolved <= 0) return;  
        unresolved--;  
        if (unresolved == 0) in.setConclusion(Status.RESOLVABLE);  
    }  
}