package com.apereg.cn;

import com.apereg.cn.controllers.Config;
import com.apereg.cn.exceptions.InvalidConfigException;
import com.apereg.cn.exceptions.MappingDatasetException;
import com.apereg.cn.neurontrainer.Dataset;
import com.apereg.cn.neurontrainer.Functions;
import com.apereg.cn.neurontrainer.Trainer;
import com.apereg.cn.utils.CSVMapper;

public class Main {

    public static final boolean DEBUG = false;

    public static Functions USED_FUNCTION = Functions.GAUSSIAN;

    public static int N;

    public static int S, S_VALIDATION;

    public static double EA1, EA2;

    public static int T_MAX1, T_MAX2;

    public static int[] R;

    public static double ALPHA;

    public static void main(String[] args) throws MappingDatasetException, InvalidConfigException {
        /* Se mapean ambos CSV. */
        Dataset trainingDataset = CSVMapper.getMapper().map("./resources/irisTrainingDataset.csv");
        Dataset validationDataset = CSVMapper.getMapper().map("./resources/irisValidationDataset.csv");

        /* Se calculan N y los tamaños de los dataset. */
        Main.N = trainingDataset.getDataVars() - 1;
        Main.S_VALIDATION = validationDataset.getSize();
        Main.S = trainingDataset.getSize() + S_VALIDATION;

        /* Se obtienen y comprueban los valores del archivo properties. */
        Main.getConfigValues();

        /* Se delega el entrenamiento en otra clase. */
        new Trainer(trainingDataset, validationDataset).run();
    }

    private static void getConfigValues() throws InvalidConfigException {
        Config.init();
        Main.EA1 = Config.getEa1();
        Main.EA2 = Config.getEa2();
        Main.T_MAX1 = Config.getTMax1();
        Main.T_MAX2 = Config.getTMax2();
        Main.R = Config.getR();
        Main.ALPHA = Config.getAlpha();
        Main.checkConfigValues();
    }

    private static void checkConfigValues() throws InvalidConfigException {
        /* Se comprueban todos los parametros en busca de inconsistencias. */
        StringBuilder exceptions = new StringBuilder();

        if (Main.EA1 < 0.0 || Main.EA1 > 1)
            exceptions.append("\tEl error aceptable de validacion debe estar en el intervalo [0,1]\n");
        if (Main.EA2 < 0.0 || Main.EA2 > 1)
            exceptions.append("\tEl error aceptable de entrenamiento debe estar en el intervalo [0,1]\n");
        if (Main.T_MAX1 < 1)
            exceptions.append("\tEl tiempo maximo del algoritmo de validacion debe ser positivo (Si no no se ejecutaria)\n");
        if (Main.T_MAX2 < 1)
            exceptions.append("\tEl tiempo maximo del algoritmo de entrenamiento debe ser positivo (Si no no se ejecutaria)\n");
        if (Main.ALPHA < 0 || Main.ALPHA > 1)
            exceptions.append("\tEl parametro ALPHA debe estar en el intervalo [0,1]\n");
        if (Main.R.length != Main.N + 1)
            exceptions.append("\tLa longitud de R debe ser igual al numero de pesos (").append(Main.N + 1).append(")");

        /* Si alguno de los parametros es inconsistente se muestra la información como excepcion. */
        if (exceptions.length() != 0)
            throw new InvalidConfigException("Se deben corregir los siguientes fallos en el fichero params.properties:\n" + exceptions);
    }

    public static Functions getUsedFunction() {
        return Main.USED_FUNCTION;
    }

    public static int getN() {
        return Main.N;
    }

    public static int getSTraining() {
        return Main.S - Main.S_VALIDATION;
    }

    public static double getEa1() {
        return Main.EA1;
    }

    public static double getEa2() {
        return Main.EA2;
    }

    public static int getTMax1() {
        return Main.T_MAX1;
    }

    public static int getTMax2() {
        return Main.T_MAX2;
    }

    public static int[] getR() {
        return Main.R;
    }

    public static double getAlpha() {
        return Main.ALPHA;
    }

    public static void debug(String text) {
        if (Main.DEBUG)
            System.out.println(text);
    }

}
