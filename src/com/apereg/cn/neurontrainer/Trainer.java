package com.apereg.cn.neurontrainer;

import com.apereg.cn.Main;
import com.apereg.cn.utils.Utils;

import java.util.Arrays;

public class Trainer {

    private final Dataset trainingDataset;

    private final Dataset validationDataset;

    private final double[] weights;

    private final int tMax1;
    private final int tMax2;
    private double em2;
    private final double ea1;
    private final double ea2;
    private double c;
    private final double alpha;

    public Trainer(Dataset trainingDataset, Dataset validationDataset) {
        this.trainingDataset = trainingDataset;
        this.validationDataset = validationDataset;
        this.weights = new double[Main.N + 1];
        this.tMax1 = Main.getTMax1();
        this.tMax2 = Main.getTMax2();
        this.ea1 = Main.getEa1();
        this.ea2 = Main.getEa2();
        this.alpha = Main.getAlpha();
    }

    public void run() {

        /* Se imprimen los parametros. */
        System.out.println("-------------------------------------------------------");
        System.out.println("Parametros de ejecucion:");
        System.out.println("\tTiempo maximo de validacion: " + this.tMax1);
        System.out.println("\tError aceptable de validacion: " + this.ea1);
        System.out.println("\tTiempo maximo de entrenamiento: " + this.tMax2);
        System.out.println("\tError aceptable de entrenamiento: " + this.ea2);
        System.out.println("-------------------------------------------------------");

        Main.debug("Inicializacion del algoritmo");

        /* Se inicializan los pesos. */
        for (int i = 0; i <= Main.getN(); i++)
            this.weights[i] = Utils.generateRandom(-1, 1);
        Main.debug("Pesos iniciales " + Arrays.toString(weights));

        /* Se calcula c. */
        c = ((double) (7 * tMax2)) / 8;

        /* Se calculan los errores medios iniciales. */
        this.calculatePAndY(Datasets.VALIDATION);
        this.calculatePAndY(Datasets.TRAINING);
        /* Se calcula em1 como et1 / s1 y em2 como et2 / s2. */
        double em1 = this.getEt(Datasets.VALIDATION) / validationDataset.getSize();
        this.em2 = getEt(Datasets.TRAINING) / trainingDataset.getSize();
        System.out.println("Error medio de validacion inicial: " + em1);
        System.out.println("Error medio de entrenamiento inicial: " + this.em2);

        Main.debug("-------------------------------------------------------");
        Main.debug("Algoritmo de aprendizaje");
        int tVal = 0;
        while ((em1 > this.ea1) && (tVal < this.tMax1)) {

            Main.debug("-------------------------------------------------------");
            Main.debug("Comienza la iteracion " + tVal + " del entrenamiento");

            /* Se delega el entrenamiento en una funcion auxiliar. */
            this.train();

            /* Se recalcula Em1. */
            Main.debug("\nAcaba la iteracion " + tVal + " del entrenamiento");
            this.calculatePAndY(Datasets.VALIDATION);
            em1 = getEt(Datasets.VALIDATION) / this.validationDataset.getSize();
            Main.debug("Error medio de validacion: " + em1);
            Main.debug("-------------------------------------------------------");

            /* Se aumenta la variable controladora del tiempo. */
            tVal++;
        }

        System.out.println("Acaba el algoritmo de aprendizaje tras " + tVal + " iteracion / iteraciones.");
        System.out.println((tVal == this.tMax1) ? "Acaba por tiempo" : "Acaba porque el error medio es igual o menor que el error aceptable");
        System.out.println("Pesos finales: " + Arrays.toString(this.weights));
        System.out.println("Error medio de validacion: " + em1);
        System.out.println("Error medio de entrenamiento: " + this.em2);
        System.out.println("-------------------------------------------------------");
    }

    private void train() {
        int tTrain = 0;
        while (em2 > ea2 && tTrain < tMax2) {

            /* Se actualizan los pesos por cada muestra del dataset de entrenamiento. */
            for (int i = 0; i < Main.getSTraining(); i++) {

                /* Se calcula el potencial y la funcion y(k) con los pesos actuales. */
                this.calculatePAndY(Datasets.TRAINING);

                /* Se actualizan los pesos para cada x de la muestra i. */
                IrisData data = trainingDataset.getIrisData(i);
                Main.debug("Se entrena la red con la muestra " + i);
                Main.debug("Pesos antes: " + Arrays.toString(this.weights));
                for (int j = 0; j <= Main.getN(); j++)
                    this.weights[j] += -(gamma(tTrain) * 0.5 * 2 * (data.getY() - data.getD()) * deryp(data.getP(), data.getY()) * derpwj(data.getX(j), j));
                Main.debug("Pesos despues: " + Arrays.toString(this.weights));
            }

            /* Se calcula em2 con los ultimos pesos obtenidos. */
            this.calculatePAndY(Datasets.TRAINING);
            this.em2 = getEt(Datasets.TRAINING) / trainingDataset.getSize();
            System.out.println("Error medio al acabar la iteracion " + tTrain + " del entrenamiento: " + this.em2);
            tTrain++;
        }
    }

    private void calculatePAndY(Datasets chosenDataset) {
        Dataset dataset;
        if (chosenDataset == Datasets.VALIDATION)
            dataset = validationDataset;
        else
            dataset = trainingDataset;

        /* Se calcula el potencial y el y(k) para cada entrada del conjunto de validacion. */
        for (int i = 0; i < dataset.getSize(); i++) {
            IrisData data = dataset.getIrisData(i);
            double potential = 0;
            /* p(k) := sum( wj * xkj, j,0,n) */
            for (int j = 0; j < data.getVarsLength(); j++)
                potential += (weights[j] * data.getX(j));
            data.setP(potential);
            data.setY(this.calculateY(potential));
        }
    }

    private double calculateY(double potential) {
        /* En base a la funcion escogida se devuelve la y(k). */
        return switch (Main.getUsedFunction()) {
            case IDENTITY -> potential;
            case SEN -> Math.sin(potential);
            case SIGMOIDAL -> 2 / (1 + Math.exp(-potential)) - 1;
            case GAUSSIAN -> 2 * Math.exp(-Math.pow(potential, 2)) - 1;
        };
    }

    private double gamma(double t) {
        return (-1 / (1 + Math.exp(-alpha * (t - c)))) + 1;
    }

    private double deryp(double p, double y) {
        /* En base a la funcion escogida se devuelve la derivada. */
        return switch (Main.getUsedFunction()) {
            case IDENTITY -> 1;
            case SEN -> Math.cos(p);
            case SIGMOIDAL -> 2 * y * (1 - y);
            case GAUSSIAN -> 2 * Math.exp(-Math.pow(p, 2)) * (-2 * p);
        };
    }

    private double derpwj(double xkj, int j) {
        return Math.pow(xkj, Main.getR()[j]);
    }

    private double getEt(Datasets chosenDataset) {
        Dataset dataset;
        if (chosenDataset == Datasets.VALIDATION)
            dataset = validationDataset;
        else
            dataset = trainingDataset;

        /* Se calcula cada E(k) como 0.5 * (( y(k) - d[k] ) ** 2). */
        double[] errors = new double[dataset.getSize()];
        for (int i = 0; i < dataset.getSize(); i++) {
            IrisData data = dataset.getIrisData(i);
            errors[i] = 0.5 * Math.pow((data.getY() - data.getD()), 2);
        }

        /* Se calcula Et como sum(E(k), k,0,s-1). */
        double error = 0;
        for (int i = 0; i < dataset.getSize(); i++)
            error += errors[i];
        return error;
    }

}
