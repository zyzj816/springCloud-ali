package com.example.alidemoorder.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class HouseForecast {

    public static void main(String[] args) throws InterruptedException {
        String csvFile = "C:\\Users\\zytw\\Desktop\\房价.csv";
        String line;
        String csvSplitBy = ",";
        List<Double> housePrices = new ArrayList<>();
        List<Double> areas = new ArrayList<>();
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            //第一行标签不要
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] row = line.split(csvSplitBy);
                double housePrice = Double.parseDouble(row[0]);
                double area = Double.parseDouble(row[1]);
                housePrices.add(housePrice);
                areas.add(area);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 打印读取的数据
        for (String[] row : data) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        double learningRate = 0.5; // 学习率
        double w = 0;               // 权重
        double b = 0;               // 偏执
        int n = housePrices.size();
        double loss = Double.MAX_VALUE;
        double avgLoss = Double.MAX_VALUE;
        double derect = -1;
        while (avgLoss >= 20) {
            for (int i = 0; i < n; i++) {
                double area = areas.get(i);
                double price = housePrices.get(i);
                double predPrice = pred(area, w, b);
                loss = (predPrice - price) * (predPrice - price);
                double w_gradient = 0;
                double b_gradient = 0;
                if (predPrice - price > 0) {
                    derect = -1;
                } else {
                    derect = 1;
                }

                w_gradient = (predPrice - price) / (w + (derect * 0.0001) * area + b - price);
                b_gradient = Math.abs((predPrice - price)) / (w * area + b + (derect * 0.0001) - price);
                System.out.println("实际值：" + price + "--->预测值" + predPrice);
                System.out.println("损失：" + loss);
                System.out.println("更新权重 w：" + w + "->" + (w + learningRate * w_gradient));
                System.out.println("更新权重 b：" + b + "->" + (b + learningRate * b_gradient));
                w = w + learningRate * w_gradient;
                b = b + learningRate * b_gradient;
            }
            avgLoss = getAvgLoss(w, b, areas, housePrices);
            System.out.println("平均损失：" + avgLoss + "\n");
        }


        // 打印最优w和b
        System.out.println("最优w：" + w);
        System.out.println("最优b：" + b);
        for (int i = 0; i < n; i++) {
            double area = areas.get(i);
            double price = housePrices.get(i);
            double predPrice = pred(area, w, b);
            loss = (predPrice - price) * (predPrice - price);
            System.out.println("实际值：" + price + "--->预测值" + predPrice + "，损失--->" + loss);
        }
    }

    static double getAvgLoss(double w, double b, List<Double> areas, List<Double> housePrices) {
        double sumLoss = 0;
        for (int i = 0; i < areas.size(); i++) {
            double area = areas.get(i);
            double price = housePrices.get(i);
            sumLoss += (w * area + b - price) * (w * area + b - price);
        }
        return sumLoss / areas.size();
    }

    static double pred(double area, double w, double b) {
        return w * area + b;
    }
}