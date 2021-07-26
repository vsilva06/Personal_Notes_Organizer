package Complementos;

public class Calculadora {

    public Calculadora() {
    }

    public double sumarTotal(double[] numeros) {
        double total = 0;
        for (int i = 0; i < numeros.length; i++) {
            total += numeros[i];
        }
        return total;
    }
}
