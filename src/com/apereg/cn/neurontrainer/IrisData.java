package com.apereg.cn.neurontrainer;

import java.util.LinkedList;
import java.util.List;

public class IrisData {

    private final List<Double> vars;

    private double potential;

    private double predictedResult;

    private double result;

    public IrisData() {
        vars = new LinkedList<>();
    }

    public int getVarsLength() {
        return this.vars.size();
    }

    public double getVar(int index){
        return this.vars.get(index);
    }

    public void addVar(Double var) {
        this.vars.add(var);
    }

    public double getPotential() {
        return this.potential;
    }

    public void setPotential(double potential) {
        this.potential = potential;
    }

    public double getPredictedResult() {
        return this.predictedResult;
    }

    public void setPredictedResult(double predictedResult) {
        this.predictedResult = predictedResult;
    }

    public double getResult() {
        return this.result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Datos: [");
        for (Double data : vars)
            str.append(data).append(", ");
        return str.delete(str.length() - 2, str.length()).append("] --> Resultado: ").append(result).toString();
    }
}
