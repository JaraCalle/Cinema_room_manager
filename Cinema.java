package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean exit = true;

        int beneficio, beneficio1;
        int ticketsBuys = 0;
        int ganancias = 0;

        System.out.println("Enter the number of rows: ");
        System.out.print("> ");
        int filas = sc.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        System.out.print("> ");
        int asientos = sc.nextInt();
        System.out.println();

        //Comprobación de beneficios
        if (filas * asientos < 60) {
            beneficio = (filas * asientos) * 10 ;
        } else {
            if (filas % 2 == 0) {
                beneficio1 = ((filas / 2) * asientos) * 8;
            } else {
                beneficio1 = (((filas / 2) + 1) * asientos) * 8;
            }
            beneficio = (((filas / 2) * asientos) * 10) + beneficio1;
        }

        //Creacion de sala de cine
        char[][] sala = new char[filas + 1][asientos + 1];
        for (int i = 0; i <= filas; i++) {
            for (int j = 0; j <= asientos; j++) {
                if (i == 0 && j == 0) {
                    sala[i][j] = ' ';
                } else if (i == 0) {
                    int x = '0' + j;
                    sala[i][j] = (char) x;
                } else if (j == 0) {
                    int x = '0' + i;
                    sala[i][j] = (char) x;
                } else {
                    sala[i][j] = 'S';
                }
            }
        }

        while (exit) {

            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            System.out.print("> ");
            int var = sc.nextInt();
            switch (var) {
                case 1:
                    //imprimir cine
                    sala(filas, asientos, sala);
                    break;
                case 2:
                    //Comprar ticket
                    int fila;
                    int asiento;
                    while (true) {
                        System.out.println();
                        System.out.println("Enter a row number:");
                        System.out.print("> ");
                        fila = sc.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        System.out.print("> ");
                        asiento = sc.nextInt();
                        if (fila > filas || asiento > asientos) {
                            System.out.println("\nWrong input!");
                        } else {
                            if (sala[fila][asiento] == 'B') {
                                System.out.println("\nThat ticket has already been purchased!");
                            } else {
                                break;
                            }
                        }
                    }
                    System.out.println();
                    System.out.printf("Ticket price: $%d", ticket(filas, asientos, fila, asiento, sala));
                    System.out.println("\n");
                    ganancias += ticket(filas, asientos, fila, asiento, sala);
                    ticketsBuys++;
                    break;
                case 3:
                    //Mostrar estadisticas
                    System.out.println();
                    System.out.printf("Number of purchased tickets: %d", ticketsBuys);
                    System.out.println();
                    System.out.printf("Percentage: %.2f%s", percentage(filas, asientos, ticketsBuys), "%");
                    System.out.println();
                    System.out.printf("Current income: $%d", ganancias);
                    System.out.println();
                    System.out.printf("Total income: $%d", beneficio);
                    System.out.println("\n");
                    break;
                case 0:
                    exit = false;
                    break;
            }
        }
    }

    //Imprimir cine
    private static void sala(int filas, int asientos, char[][] sala) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i <= filas; i++) {
            for (int j = 0; j <= asientos; j++) {
                System.out.print(sala[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private static int ticket(int filas, int asientos, int fila, int asiento, char[][] sala) {

        int precio;
        if (filas * asientos < 60) {
            precio = 10;
        } else {
            if (filas % 2 == 0) {
                if (fila < filas / 2) {
                    precio = 10;
                } else {
                    precio = 8;
                }
            } else {
                if (fila < filas / 2 + 1) {
                    precio = 10;
                } else {
                    precio = 8;
                }
            }
        }
        sala[fila][asiento] = 'B';
        return precio;
    }
    private static float percentage(int filas, int asientos, int ticketsBuy) {
        float x = filas * asientos;
        return 100F / (x / ticketsBuy);
    }
}