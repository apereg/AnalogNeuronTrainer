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

    private int tVal = 0, tTrain = 0;
    private int tMax1, tMax2;
    private double em1, em2;
    private double ea1, ea2;
    private double c;
    private double alpha;

    public Trainer(Dataset trainingDataset, Dataset validationDataset) {
        this.trainingDataset = trainingDataset;
        this.validationDataset = validationDataset;
        this.weights = new LinkedList<>();
        this.tMax1 = Main.getTMax1();
        this.tMax2 = Main.getTMax2();
        this.ea1 = Main.getEa1();
        this.ea2 = Main.getEa2();
    }

    public void run() {
        System.out.println("Parametros de ejecucion:");
        System.out.println("\tTiempo maximo de validacion: " +tMax1);
        System.out.println("\tError aceptable de validacion:" +ea1);
        System.out.println("\tTiempo maximo de entrenamiento: " +tMax2);
        System.out.println("\tError aceptable de entrenamiento:" +ea2);
        Main.debug("-- Inicializacion del algoritmo --");
        /* Se inicializan los pesos y se calculan Em1 y Em2. */
        for (int i = 0; i <= Main.getN(); i++)
            weights.add(Utils.generateRandom(-1, 1));
        Main.debug("Pesos iniciales " +Arrays.toString(weights.toArray()));

        /* Se calculan los errores medios iniciales. */
        this.calculatePAndY(Datasets.VALIDATION);
        double et1 = getEt(Datasets.VALIDATION);
        em1 = et1 / validationDataset.getSize();
        this.calculatePAndY(Datasets.TRAINING);
        double et2 = getEt(Datasets.TRAINING);
        em2 = et2 / trainingDataset.getSize();
        Main.debug("Errores iniciales: Em1: " +em1+ ", Em2: " +em2);
        Main.debug("-- FIN Inicializacion del algoritmo --");

        Main.debug("-- Bucle algoritmo de aprendizaje --");
        while (em1 > ea1 && tVal < tMax1) {
            /* Se delega el entrenamiento en una funcion auxiliar. */
            this.train();
            tVal++;
            /* Se recalcula Em1. */
            et1 = getEt(Datasets.VALIDATION);
            em1 = et1 / validationDataset.getSize();
            Main.debug("Acaba la iteracion " +tVal+ " del entrenamiento");
            Main.debug("Error medio de validacion: " +em1);
        }
        Main.debug("-- FIN Bucle algoritmo de aprendizaje --");
        System.out.println("Acaba el algoritmo de aprendizaje:");
        System.out.println("\tPesos finales: " +Arrays.toString(weights.toArray()));
        System.out.println("\tError medio de validacion: " +em1);
        System.out.println("\tError medio de entrenamiento: " +em2);
    }

    private void train() {
        while (em2 > ea2 && tTrain < tMax2) {
            for (int i = 0; i < Main.getSTraining(); i++) {
                IrisData data = trainingDataset.getIrisData(i);
                //for (int j = 0; j < Main.getN(); j++) {
                    /* Regla del descenso del gradiente y aplicacion del momentum. */

                //}
            }
        }
    }

    private void calculatePAndY(Datasets chosenDataset){
        Dataset dataset;
        if(chosenDataset == Datasets.VALIDATION)
            dataset = validationDataset;
        else
            dataset = trainingDataset;

        /* Se calcula el potencial y el y(k) para cada entrada del conjunto de validacion. */
        for (int i = 0; i < dataset.getSize(); i++) {
            IrisData data = dataset.getIrisData(i);
            double potential = 0;
            for (int j = 0; j < data.getVarsLength(); j++) {
                potential += (weights.get(j) * data.getVar(j));
            }
            potential += weights.get(weights.size() - 1 ) * 1;
            data.setPotential(potential);
            data.setPredictedResult(this.calculateY(potential));
        }
    }

    private double getEt(Datasets chosenDataset) {
        Dataset dataset;
        if(chosenDataset == Datasets.VALIDATION)
            dataset = validationDataset;
        else
            dataset = trainingDataset;

        double error = 0;
        for (int i = 0; i < dataset.getSize(); i++) {
            IrisData data = dataset.getIrisData(i);
            error += 0.5 * Math.pow((data.getPredictedResult() * data.getResult()), 2);
        }
        return error;
    }

    private double calculateY(double potential) {
        /* En base a la funcion escogida se devuelve la y(k). */
        return switch (Main.getUsedFunction()){
            case IDENTITY -> potential;
            case SEN -> Math.sin(potential);
            case SIGMOIDAL -> 2 / (1 + Math.exp(-potential) - 1);
            case GAUSSIAN -> 2 * Math.exp(- Math.pow(potential, 2)) - 1;
        };
    }

    private double deryp(double value){
        /* En base a la funcion escogida se devuelve la derivada. */
        return switch (Main.getUsedFunction()){
            case IDENTITY -> value;
            case SEN -> Math.cos(value);
            case SIGMOIDAL -> 2 * value * (1 - value);
            case GAUSSIAN -> 2 * Math.exp(- Math.pow(value, 2)) * (-2 * value);
        };
    }

}
