package com.apereg.cn.neurontrainer;

import java.util.LinkedList;
import java.util.List;

public class IrisData {

    private final List<Double> x;

    private double p;

    private double y;

    private double d;

    public IrisData() {
        x = new LinkedList<>();
    }

    public int getVarsLength() {
        return this.x.size();
    }

    public double getX(int index){
        return this.x.get(index);
    }

    public void addX(Double xj) {
        this.x.add(xj);
    }

    public double getP() {
        return this.p;
    }

    public void setP(double p) {
        this.p = p;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getD() {
        return this.d;
    }

    public void setD(double d) {
        this.d = d;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Datos: [");
        for (Double data : x)
            str.append(data).append(", ");
        return str.delete(str.length() - 2, str.length()).append("] --> Resultado: ").append(d).toString();
    }
}
