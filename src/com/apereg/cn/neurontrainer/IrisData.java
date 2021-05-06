package com.apereg.cn.neurontrainer;

import java.util.LinkedList;
import java.util.List;

public class IrisData {

    private final List<Double> vars;

    private final List<Double> weights;

    private int result;

    public IrisData() {
        vars = new LinkedList<>();
        weights = new LinkedList<>();
    }

    public void addVar(Double var){
        vars.add(var);
    }

    public void setResult(int result){
        this.result = result;
    }

    public void setWeight(int index, double weight){
        this.weights.set(index, weight);
    }

    public double getWeight(int index){
        return this.weights.get(index);
    }

    public int getVarsLength(){
        return this.vars.size();
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("Datos: [");
        for (Double data: vars)
            str.append(data).append(", ");
        return str.delete(str.length() - 2, str.length()).append("] --> Resultado: ").append(result).toString();
    }
}
