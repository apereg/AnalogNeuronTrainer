package com.apereg.cn.controllers;

import com.apereg.cn.exceptions.InvalidConfigException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties prop = new Properties();

    public static void init() throws InvalidConfigException {
        try {
            File file = new File("params.properties");
            if (!file.exists())
                throw new InvalidConfigException();
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

    public static double getTMax1() throws InvalidConfigException {
        try {
            return Double.parseDouble(prop.getProperty("TMax1"));
        } catch (Exception e) {
            throw new InvalidConfigException("Error al leer la propiedad 'TMax1' del fichero params.properties");
        }
    }

    public static double getTMax2() throws InvalidConfigException {
        try {
            return Double.parseDouble(prop.getProperty("TMax2"));
        } catch (Exception e) {
            throw new InvalidConfigException("Error al leer la propiedad 'TMax2' del fichero params.properties");
        }
    }

    public static double getGamma() throws InvalidConfigException {
        try {
            return Double.parseDouble(prop.getProperty("Gamma"));
        } catch (Exception e) {
            throw new InvalidConfigException("Error al leer la propiedad 'Gamma' del fichero params.properties");
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
