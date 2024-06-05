package roulette;

import java.util.Random;
import java.util.Scanner;

public class Roulette {

    public static void main(String[] args) throws InterruptedException {

        Scanner in = new Scanner(System.in);

        int help = 0;
        int[] fucile = new int[6];
        int cont = 0;

        boolean turno = true;
        int vitaGiocatore1 = 6;
        int vitaGiocatore2 = 6;

        int danno = 1;

        while (vitaGiocatore1 > 0 && vitaGiocatore2 > 0) {  // Corretto condizione del ciclo
            if (help == 0) {
                System.out.println("il fucile è scarico, \nricarica fucile");
                help = 6;
                cont = 0;
                fucile = ricarica();
            }

            if (turno) {  //se turno è true, gioca il giocatore1
                System.out.println("\n\n\n\n\nORA GIOCA IL GIOCATORE 1:");
            } else {  //gioca il giocatore2
                System.out.println("\n\n\n\n\nORA GIOCA IL GIOCATORE 2:");
            }

            int[] oggetti = scelta();

            System.out.println("ecco gli oggetti che hai:\n");
            int oggetto1 = oggetti[0];
            int oggetto2 = oggetti[1];

            descriviOggetto(oggetto1, "primo");
            descriviOggetto(oggetto2, "secondo");

            System.out.println("seleziona uno dei due oggetti e usalo!");
            System.out.println("premi 1 per prendere il primo oggetto o premi 2 per prendere il secondo oggetto");
            int sceltaGiocatore = in.nextInt();

            if (sceltaGiocatore == 1) {
                danno = usaOggetto(oggetto1, turno, fucile, cont);
            } else {
                danno = usaOggetto(oggetto2, turno, fucile, cont);
            }

            System.out.println("a chi vuoi sparare? (premi 1 per sparare a te o 2 per sparare all'altro giocatore)");
            int scelta = in.nextInt();

            if (scelta == 1) {
                if (fucile[cont] == 0) {
                    System.out.println("cartuccia vuota, sei salvo");
                } else {
                    if (turno) {
                        vitaGiocatore1 -= danno;
                        System.out.println("ti sei sparato, ora hai " + vitaGiocatore1 + " vite");
                    } else {
                        vitaGiocatore2 -= danno;
                        System.out.println("ti sei sparato, ora hai " + vitaGiocatore2 + " vite");
                    }
                }
            }

            if (scelta == 2) {
                if (fucile[cont] == 0) {
                    System.out.println("cartuccia vuota, l'altro giocatore è salvo");
                } else {
                    if (turno) {
                        vitaGiocatore2 -= danno;
                        System.out.println("hai sparato all'altro giocatore, ora ha " + vitaGiocatore2 + " vite");
                    } else {
                        vitaGiocatore1 -= danno;
                        System.out.println("hai sparato all'altro giocatore, ora ha " + vitaGiocatore1 + " vite");
                    }
                }
            }

            danno = 1;  // reset danno
            turno = !turno;  // cambio turno
            cont += 1;  // incremento contatore del fucile
            help -= 1;  // decremento contatore di aiuto
            
            System.out.println("\n\n\n\nVita Giocatore1 : " + vitaGiocatore1);
            System.out.println("\n\n\n\nVita Giocatore2 : " + vitaGiocatore2);
            
        }

        System.out.println("Gioco finito! " + (vitaGiocatore1 > 0 ? "Giocatore 1" : "Giocatore 2") + " vince!");

        in.close();
    }

    public static int[] ricarica() {
        Random proiettile = new Random();
        int[] fucile = new int[6];

        for (int i = 0; i < 6; i++) {
            fucile[i] = proiettile.nextInt(2);
        }
        return fucile;
    }

    public static int[] scelta() {
        Random oggetto = new Random();
        int[] oggetti = new int[2];

        for (int i = 0; i < 2; i++) {
            oggetti[i] = oggetto.nextInt(3);
        }

        return oggetti;
    }

    public static void descriviOggetto(int oggetto, String posizione) {
        if (oggetto == 0) {
            System.out.println("come " + posizione + " oggetto hai le sigarette (vita +1)");
        } else if (oggetto == 1) {
            System.out.println("come " + posizione + " oggetto hai la lente (vedi il prossimo proiettile)");
        } else if (oggetto == 2) {
            System.out.println("come " + posizione + " oggetto hai il seghetto (doppi danni)");
        }
    }

    public static int usaOggetto(int oggetto, boolean turno, int[] fucile, int cont) {
        if (oggetto == 0) {
            if (turno) {
                System.out.println("Giocatore 1 ottiene +1 vita");
            } else {
                System.out.println("Giocatore 2 ottiene +1 vita");
            }
        } else if (oggetto == 1) {
            if (fucile[cont] == 0) {
                System.out.println("in questo turno il fucile ha un proiettile SCARICO");
            } else {
                System.out.println("in questo turno il fucile ha un proiettile CARICO");
            }
        } else if (oggetto == 2) {
            System.out.println("Prossimo sparo farà il doppio del danno");
            return 2;
        }
        return 1;
    }
}
