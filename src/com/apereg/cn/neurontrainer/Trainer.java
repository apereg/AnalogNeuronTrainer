package com.apereg.cn.neurontrainer;

import com.apereg.cn.Main;
import com.apereg.cn.utils.Utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Trainer {

    private Dataset trainingDataset;

    private Dataset validationDataset;

    private List<Double> weights;

    private int t = 0;
    private int tMax1 = Main.getTMax1();
    private int tMax2 = Main.getTMax2();
    private double Em1 = 0, Em2;
    private double Ea1 = Main.getEa1();
    private double Ea2 = Main.getEa2();

    public Trainer(Dataset trainingDataset, Dataset validationDataset) {
        this.trainingDataset = trainingDataset;
        this.validationDataset = validationDataset;
        this.weights = new LinkedList<>();
    }

    public void run() {
        System.out.println(Arrays.toString(Main.getR()));
        /* Se inicializan los pesos y se calculan Em1 y Em2. */
        for (int i = 0; i <= Main.getN(); i++)
            weights.add(Utils.generateRandom(-1, 1));

        Em1 = 0;
        Em2 = 0;

        while (Em1 > Ea1 && t < tMax1) {
            this.train();
            t++;
            /* Se recalcula Em1. */
            Em1 = 0;
        }

    }

    private void train() {
        while (Em2 > Ea2 && t < tMax2) {
            for (int i = 0; i < Main.getSTraining(); i++) {
                IrisData data = trainingDataset.getIrisData(i);
                //for (int j = 0; j < Main.getN(); j++) {
                    /* Regla del descenso del gradiente y aplicacion del momentum. */

                //}
            }
        }
    }

    private double deryp(double value){
        /* En base a la funcion escogida se devuelve la derivada. */
        return switch (Main.getUsedFunction()){
            case IDENTITY -> value;
            case SEN -> Math.cos(value);
            case SIGMOIDAL -> 0;
            case GAUSSIAN -> 1;
        };
    }


}
