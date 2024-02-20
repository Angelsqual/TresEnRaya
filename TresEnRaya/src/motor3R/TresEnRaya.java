/**
 * The `TresEnRaya` class represents the logic of a Tic-Tac-Toe game where a human player competes
 * against a computer player.
 */
package motor3R;

import java.util.Random;

/**
 * Clase que representa la lógica del juego Tres en Raya.
 */
public class TresEnRaya {
    private char[][] tablero;
    private char jugadorHumano;
    private char jugadorMaquina;
    private boolean turnoJugadorHumano;

    /**
     * Constructor de la clase TresEnRaya.
     * 
     * @param jugadorHumano  Símbolo del jugador humano (por ejemplo, 'X').
     * @param jugadorMaquina Símbolo del jugador máquina (por ejemplo, 'O').
     */
    public TresEnRaya(char jugadorHumano, char jugadorMaquina) {
        tablero = new char[3][3];
        this.jugadorHumano = jugadorHumano;
        this.jugadorMaquina = jugadorMaquina;
        turnoJugadorHumano = true; // El jugador humano empieza primero
        inicializarTablero();
    }

    // Métodos públicos

    /**
     * Realiza un movimiento en el tablero en la posición especificada.
     * 
     * @param fila    Fila del tablero (0, 1 o 2).
     * @param columna Columna del tablero (0, 1 o 2).
     * @return true si el movimiento se realizó con éxito, false si la posición
     *         está ocupada o si la partida ya ha terminado.
     */
    public boolean realizarMovimiento(int fila, int columna) {
        if (!esMovimientoValido(fila, columna) || haTerminadoPartida()) {
            return false;
        }

        char jugadorActual = (turnoJugadorHumano) ? jugadorHumano : jugadorMaquina;
        tablero[fila][columna] = jugadorActual;
        turnoJugadorHumano = !turnoJugadorHumano; // Cambiar turno
        return true;
    }

    /**
     * Comprueba si la partida ha terminado (hay ganador o empate).
     * 
     * @return true si la partida ha terminado, false de lo contrario.
     */
    public boolean haTerminadoPartida() {
        return hayGanador() || tableroLleno();
    }

    // Métodos privados

    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    /**
     * @param fila
     * @param columna
     * @return boolean
     */
    private boolean esMovimientoValido(int fila, int columna) {
        return fila >= 0 && fila < 3 && columna >= 0 && columna < 3 && tablero[fila][columna] == ' ';
    }

    private boolean tableroLleno() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hayGanador() {
        // Comprobar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true; // Hay ganador en una fila
            }
            if (tablero[0][i] != ' ' && tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) {
                return true; // Hay ganador en una columna
            }
        }
        // Comprobar diagonales
        if (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true; // Hay ganador en diagonal principal
        }
        if (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true; // Hay ganador en diagonal secundaria
        }
        return false;
    }

    public boolean esTurnoJugadorHumano() {
        return turnoJugadorHumano;
    }

    /**
     * Obtiene el estado actual del tablero.
     * 
     * @return Representación del tablero como una cadena de texto.
     */
    public String obtenerTablero() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(tablero[i][j]);
                if (j < 2) {
                    sb.append(" | ");
                }
            }
            sb.append("\n");
            if (i < 2) {
                sb.append("---------\n");
            }
        }
        return sb.toString();
    }

    /**
     * Realiza un movimiento aleatorio en el tablero.
     */
    public void realizarMovimientoMaquina() {
        Random random = new Random();
        int fila, columna;
        do {
            fila = random.nextInt(3);
            columna = random.nextInt(3);
        } while (!esMovimientoValido(fila, columna));
        realizarMovimiento(fila, columna);
    }
}
