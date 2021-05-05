package com.apereg.cn;

import com.apereg.cn.exceptions.MappingDatasetException;
import com.apereg.cn.neurontrainer.Dataset;
import com.apereg.cn.neurontrainer.Trainer;
import com.apereg.cn.utils.CSVMapper;

public class Main {

    public static final int N = 4;

    public static final int S = 150;

    public static final int S_VALIDATION = 15;

    public static void main(String[] args) throws MappingDatasetException {
        Dataset trainingDataset = CSVMapper.getMapper().map("./resources/iris_dataset.csv");
        Dataset validationDataset = CSVMapper.getMapper().map("./resources/iris_train.csv");

        Trainer trainer = new Trainer(trainingDataset, validationDataset);
        trainer.train();
    }

    public static int getN() {
        return Main.N;
    }

    public static int getS() {
        return Main.S;
    }

    public static int getSValidation() {
        return Main.S_VALIDATION;
    }

    public static int getSTraining() {
        return Main.S - Main.S_VALIDATION;
    }
}
