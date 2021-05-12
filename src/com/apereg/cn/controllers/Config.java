package com.apereg.cn.controllers;

import com.apereg.cn.exceptions.InvalidConfigException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Config {

    private static final Properties prop = new Properties();

    public static void init() throws InvalidConfigException {
        try {
            File file = new File("params.properties");
            if (!file.exists())
                throw new InvalidConfigException("No se encontro el fichero params.properties en la raiz del proyecto");
            prop.load(new FileInputStream(file));
        } catch (IOException e) {
            throw new InvalidConfigException("Error while loading params.properties");
        }
    }

    public static double getEa1() throws InvalidConfigException {
        try {
            return Double.parseDouble(prop.getProperty("Ea1"));
        } catch (Exception e) {
            throw new InvalidConfigException("Error al leer la propiedad 'Ea1' del fichero params.properties");
        }
    }

    public static double getEa2() throws InvalidConfigException {
        try {
            return Double.parseDouble(prop.getProperty("Ea2"));
        } catch (Exception e) {
            throw new InvalidConfigException("Error al leer la propiedad 'Ea2' del fichero params.properties");
        }
    }

    public static int getTMax1() throws InvalidConfigException {
        try {
            return Integer.parseInt(prop.getProperty("TMax1"));
        } catch (Exception e) {
            throw new InvalidConfigException("Error al leer la propiedad 'TMax1' del fichero params.properties");
        }
    }

    public static int getTMax2() throws InvalidConfigException {
        try {
            return Integer.parseInt(prop.getProperty("TMax2"));
        } catch (Exception e) {
            throw new InvalidConfigException("Error al leer la propiedad 'TMax2' del fichero params.properties");
        }
    }

    public static int[] getR() throws InvalidConfigException {
        try {
            return Arrays.stream(prop.getProperty("r").split(",")).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
                throw new InvalidConfigException("Error al leer la propiedad 'R' del fichero params.properties (Asegurate de separarlo con ',' sin espacios)");
        }
    }

    public static double getAlpha() throws InvalidConfigException {
        try {
            return Double.parseDouble(prop.getProperty("Alpha"));
        } catch (Exception e) {
            throw new InvalidConfigException("Error al leer la propiedad 'Alpha' del fichero params.properties");
        }
    }

}
