import java.util.Scanner;

import motor3R.TresEnRaya;

public class InterfaceConsola {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char jugadorHumano = 'X';
        char jugadorMaquina = 'O';
        TresEnRaya juego = new TresEnRaya(jugadorHumano, jugadorMaquina);

        System.out.println("¡Bienvenido al juego Tres en Raya!");
        System.out.println(juego.obtenerTablero());

        while (!juego.haTerminadoPartida()) {
            if (juego.esTurnoJugadorHumano()) {
                System.out.println("Turno del jugador humano. Introduce fila y columna (por ejemplo, 1 2):");
                int fila = scanner.nextInt();
                int columna = scanner.nextInt();
                if (!juego.realizarMovimiento(fila, columna)) {
                    System.out.println("Movimiento inválido. Inténtalo de nuevo.");
                }
            } else {
                System.out.println("Turno de la máquina:");
                juego.realizarMovimientoMaquina();
            }
            System.out.println(juego.obtenerTablero());
        }

        if (juego.hayGanador()) {
            System.out.println("¡Ha ganado el jugador " + ((juego.esTurnoJugadorHumano()) ? "máquina!" : "humano!"));
        } else {
            System.out.println("¡La partida ha terminado en empate!");
        }

        scanner.close();
    }
}
