package it.polimi.ingsw.is24am14.server.network;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientLauncher {
    public static void main(String[] args) {
        //stampo il titolo del gioco
        String green = "\033[32m";
        String reset = "\033[0m";

        System.out.println(green + "                                                                                                                                                                                ");
        System.out.println("      * ***                    **                                     ***** *     **                                                               ***                        ");
        System.out.println("    *  ****  *                  **                                 ******  **    **** *                *                                            ***      *                ");
        System.out.println("   *  *  ****                   **                                **   *  * **    ****                **                                             **     ***               ");
        System.out.println("  *  **   **                    **                               *    *  *  **    * *                 **                                             **      *                ");
        System.out.println(" *  ***           ****          **              ***    ***           *  *    **   *                 ******** **   ****     ***  ****                 **               ****    ");
        System.out.println("**   **          * ***  *   *** **      ***    * ***  **** *        ** **    **   *        ****    ********   **    ***  *  **** **** *    ****      **    ***       * **** * ");
        System.out.println("**   **         *   ****   *********   * ***      *** *****         ** **     **  *       * ***  *    **      **     ****    **   ****    * ***  *   **     ***     **  ****  ");
        System.out.println("**   **        **    **   **   ****   *   ***      ***  **          ** **     **  *      *   ****     **      **      **     **          *   ****    **      **    ****       ");
        System.out.println("**   **        **    **   **    **   **    ***      ***             ** **      ** *     **    **      **      **      **     **         **    **     **      **      ***      ");
        System.out.println("**   **        **    **   **    **   ********      * ***            ** **      ** *     **    **      **      **      **     **         **    **     **      **        ***    ");
        System.out.println(" **  **        **    **   **    **   *******      *   ***           *  **       ***     **    **      **      **      **     **         **    **     **      **          ***  ");
        System.out.println("  ** *      *  **    **   **    **   **          *     ***             *        ***     **    **      **      **      **     **         **    **     **      **     ****  **  ");
        System.out.println("   ***     *    ******    **    **   ****    *  *       *** *      ****          **     **    **      **       ******* **    ***        **    **     **      **    * **** *   ");
        System.out.println("    *******      ****      *****      *******  *         ***      *  *****               ***** **      **       *****   **    ***        ***** **    *** *   *** *    ****    ");
        System.out.println("      ***                   ***        *****                     *     **                 ***   **                                        ***   **    ***     ***             ");
        System.out.println("                                                         *                                                                                                            ");
        System.out.println("                                                          **"+ reset);

        //chiedo all'utente se vuole giocare RMI o TCP

        //menu di scelta
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while(choice != 0 && choice != 1 && choice != 2){
            System.out.println("Digit 0 to play with RMI \n1 to play with TCP \n2 to exit:");
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        System.out.println("Starting RMI connection...");
                        RMIClientInterface client = null;
                         try {
                            client = new RMIClient();
                         } catch (Exception e) {
                            throw new RuntimeException(e);
                         }
                         break;
                    case 1:
                        System.out.println("Starting TCP connection...");
                        //va aggiunta la parte di connessione TCP
                        System.out.println("TCP connection not implemented yet.");
                        System.exit(0);
                        break;
                    case 2:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // discard the non-integer input
            }
        }
    }
}
