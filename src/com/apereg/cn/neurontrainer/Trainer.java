package com.apereg.cn.neurontrainer;

public class Trainer {

    private Dataset trainingDataset;

    private Dataset validationDataset;

    public Trainer(Dataset trainingDataset, Dataset validationDataset) {
        this.trainingDataset = trainingDataset;
        this.validationDataset = validationDataset;
    }

    public void train() {
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

        }*/
    }
}
