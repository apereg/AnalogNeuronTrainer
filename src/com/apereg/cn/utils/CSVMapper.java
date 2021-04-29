package com.apereg.cn.utils;

import com.apereg.cn.exceptions.MappingDatasetException;
import com.apereg.cn.neurontrainer.Dataset;

import java.io.BufferedReader;
import java.io.FileReader;

public class CSVMapper {

    public static CSVMapper instance;

    private CSVMapper(){}

    public static CSVMapper getInstance() {
        if(instance == null)
            instance = new CSVMapper();
        return instance;
    }

    public Dataset map(String csvPath) throws MappingDatasetException {
        BufferedReader br = null;

        try {

            br =new BufferedReader(new FileReader("files/Libro1.csv"));
            String line = br.readLine();
            while (line != null) {
                String [] fields = line.split(";");
                System.out.println(fields);
                line = br.readLine();
            }
            br.close();

        } catch (Exception e) {
            try{br.close();}catch (Exception ignored){}
            throw new MappingDatasetException();
        }
        return new Dataset();
    }
}
