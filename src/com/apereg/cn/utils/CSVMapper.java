package com.apereg.cn.utils;

import com.apereg.cn.exceptions.MappingDatasetException;
import com.apereg.cn.neurontrainer.Dataset;
import com.apereg.cn.neurontrainer.IrisData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVMapper {

    public static CSVMapper instance;

    private CSVMapper() {
    }

    public static CSVMapper getMapper() {
        if (instance == null)
            instance = new CSVMapper();
        return instance;
    }

    public Dataset map(String csvPath) throws MappingDatasetException {

        BufferedReader br = null;
        Dataset fileMapped = new Dataset();

        try {
            br = new BufferedReader(new FileReader(csvPath));
            String line = br.readLine();
            while (line != null) {
                line = line.replaceAll(",", ".");
                String[] fields = line.split(";");
                IrisData data = new IrisData();
                for (int i = 0; i < fields.length - 1; i++)
                    data.addVar(Double.parseDouble(fields[i]));
                data.setResult(Integer.parseInt(fields[fields.length - 1]));
                System.out.println(data);
                fileMapped.addRecord(data);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            if (br != null) try { br.close(); } catch (IOException ignored) {}
            throw new MappingDatasetException();
        }

        return fileMapped;
    }
}
