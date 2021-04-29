package com.apereg.cn;

import com.apereg.cn.exceptions.MappingDatasetException;
import com.apereg.cn.neurontrainer.Dataset;
import com.apereg.cn.utils.CSVMapper;

public class Main {

    public static void main(String[] args) throws MappingDatasetException {
        //TODO mapear
        int n; // Dimension del espacio de entrada (4)
        int s; // cardinal del espacio de busquea 150
        int s1; // cardinal del espacio de validacion 15
        int s2; // cardinal del espacio de entrenamiento 15
        int dk; // ultimas columnas
        double Ea1 = 0.1; // Error minimo de validacion
        double Ea2 = 0.1; // Error minimo de entrenamiento
        int t_max1 = 1; //Tiempo maximo para el conjunto de validacion
        int t_max2 = 2; // Tiempo maximo para el conjunto de entrenamiento
        float e = (float) 2.7; //...
        float alpha = 0.5f;
        float a = 0f; //a definir
        int t = 0; // a definir
        //int gammaT = -1 / (1 + e^(- alpha * (t - a)));
        Dataset trainingDataSet = CSVMapper.getMapper().map("./resources/iris_dataset.csv");

    }

    public void algoritmoAprendizaje(){
        int T;
        int t;
        /* Desde j=0 a n inicializar pesos aleatoriamente entre -1 y 1 cerrado
        calcular en potencial para cada muestra p = sum(wj * xkj para j desde 0 a n)
        calculamos la salida y(k) = f(p(k))
        calcular errores en K = 0,5 * (salida para k - salida esperada para k (yk - dk))
        calcular error de validacion que es et1 = sum(E(k), PARA K DE 0 A S1-1
        y error de validacion igual pero a S2-1
        em1 = et1 / s1 y lo mismo pal 2
         */

        /*while(Em1 > Ea1 && T > t_max1)
        entrena
        calcula em1
        T++
         */

        /* Entrenamiento
        while (EM2 > Ea2 && t > t_max2) {
        for k = o to s2 -1{
        calculamos p(k) e y(k)
        y se recorren los pesos for j = 0 to n
        w[j] += -gamma(t) * la derivada correspondiente (0,5 * 2 * yk - salida esperada (d[k] *deryp * derp wj)

        Una vez que pasa por todas las muestras se calcula el eror medio para el conjunto de entrenamiento
        em2 con los ultimos pesos obtenidos:
        for k = 0 to s2 -1 (todas las muestras del conjunto de entrenamiento)
            se calcula potencial p(k) y y(k)
            Et2 (error total enel entrenamiento) += error en k (0,2 * (y(k) - d[k]) ^ 2
            t++;
        Em2 = Et2 / s2;
         */
    }

    public void derivadas(){
        /* Tod o para cada k)
        int opcionf = 0; // 0 identidad; 1 senPk; 2 sigmoide; 3 gaussiana (la campana)
        int opciong = 0; // 0 habitual, 1 impulsos potenciados y ponderados
        /* deryp para f = 0 ( lo de la hoja)
            derpwj = xj ^ rj
         */
    }
}
