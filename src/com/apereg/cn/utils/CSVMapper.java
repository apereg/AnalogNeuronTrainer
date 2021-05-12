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

    /* Clase Singleton. */
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
                /* Se cambian las comas por puntos por el formato de numero decimal. */
                line = line.replaceAll(",", ".");
                String[] fields = line.split(";");
                /* Se van creando objetos de tipo data por cada fila del csv. */
                IrisData data = new IrisData();
                for (int i = 0; i < fields.length - 1; i++)
                    data.addX(Double.parseDouble(fields[i]));
                data.setD(Integer.parseInt(fields[fields.length - 1]));
                fileMapped.addRecord(data);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            if (br != null) try { br.close(); } catch (IOException ignored) {}
            throw new MappingDatasetException("Fallo en el mapeo de " + csvPath);
        }

        return fileMapped;
    }
}
