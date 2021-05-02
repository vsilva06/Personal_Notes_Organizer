import java.util.Scanner;

public class Main {
    private static double a;
    private static double b;
    static Scanner tec = new Scanner(System.in);
    static Calculadora calc1 = new Calculadora();

    public static void main(String[] args) {
        cambiarValoresAB();
        menu();


    }

    private static void menu() {
        String resp = "";
        Outer1:
        while (true) {
            resp = tec.nextLine();
            switch (resp) {
                case "1":
                    System.out.print("Suma entre: "+ a + " y  "+ b + " = " + calc1.sumar(a, b));
                    break;
                case "2":
                    System.out.print("Resta entre: "+ a + " y "+ b + " = " + calc1.restar(a, b));
                    break;
                case "3":
                    System.out.print("Multiplicacion entre: "+ a + " y "+ b + " = " + calc1.multiplicar(a, b));
                    break;
                case "4":
                    if (validarNum()) {
                        System.out.print("division entre: "+ a + " y "+ b + " = " + calc1.dividir(a, b));
                    }else{
                        System.out.print("Cambie el valor de B");
                        break;
                    }

                    break;
                case "5":
                    cambiarValoresAB();
                    break;
                case "0":
                    System.out.println("Adios, gracias");
                    break Outer1;

                default:
                    System.out.println("Ingrese una opcion disponible\n");
                    break;


            }
            opcionCalculadora();
        }
    }

    private static boolean validarNum() {
        if (b == 0) {
            System.out.println("Error: El denominador no puede ser 0 \n");
            return false;

        }else {
            return true;
        }
    }

    private static void opcionCalculadora() {
        System.out.println("1: Sumar A y B \n" +
                           "2: Restar A y B \n" +
                           "3: Multiplicar A y B \n" +
                           "4: Dividir A y B \n" +
                           "5: Cambiar valores de A y B \n" +
                           "0: Salir \n");
    }

    private static void cambiarValoresAB() {
        try {
            System.out.println("Ingrese el valor de A");
            a = tec.nextDouble();
            System.out.println("Ingrese el valor de B");
            b = tec.nextDouble();
        } catch (Exception e) {
            System.out.println("Error: Entrada no valida");
            cambiarValoresAB();
        }
    }
}
