package com.example.alidemoorder.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Export {


    public static void main(String[] args) {

        String csvFile = "C:\\Users\\zytw\\Desktop\\房价.csv";
        String line;
        String csvSplitBy = ",";

        List<String[]> data = new ArrayList<>();

        BufferedReader br;

        {
            try {
                br = new BufferedReader(new FileReader(csvFile));
                br.readLine();
                while ((line=br.readLine()) !=null){
                    String[] row = line.split(csvSplitBy);
                    data.add(row);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        double[] y=new double[10];
        double[] s=new double[10];
        // 打印读取的数据
        for (String[] row : data) {
            y[data.indexOf(row)]= Double.parseDouble(row[0]);
            s[data.indexOf(row)]= Double.parseDouble(row[1]);
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        double x=1;
        double b=0;
        double learningA=0.01;
        double learningB=0.01;
        double[][] learningArrays=new double[5][5];
        //学习数组
        double[] train= Arrays.copyOfRange(s,0,4);
        //测试数组
        double[] test= Arrays.copyOfRange(s,5,10);
        for (int i = 0; i < train.length; i++) {
            while (s[i]/train[i]>0.9&&1>s[i]/train[i]){

            }
        }

    }
}
