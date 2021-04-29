package com.apereg.cn.neurontrainer;

import java.util.LinkedList;
import java.util.List;

public class Dataset {

    private List<IrisData> irisDataset;

    public Dataset(){
        this.irisDataset = new LinkedList<>();
    }

    public void addRecord(IrisData data) {
        irisDataset.add(data);
    }

    public int getDatasetLength() {
        return irisDataset.size();
    }

    public IrisData getIrisData(int index){
        return this.irisDataset.get(index);
    }

}
