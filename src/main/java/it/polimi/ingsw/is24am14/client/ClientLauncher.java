package it.polimi.ingsw.is24am14.client;

import java.util.Scanner;

public class ClientLauncher {
    public static void main(String[] args) {

        //stampo il titolo del gioco e lascio qualche spazio
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

        //faccio scegliere all'utente se vuole giocare con RMI o TCP
        String red = "\033[31m";
        System.out.println();
        System.out.println();
        System.out.println(red + "Select 0 to play with RMI, 1 to play with TCP"+ reset);

        Scanner inputUtente = new Scanner(System.in);

        int scelta = inputUtente.nextInt();
        ClientConnection connection;
        if(scelta == 0) {
            try {
                connection = new RMIClient();
                connection.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
        }
        }else if(scelta == 1){
            try{
                connection = new TCPClient();
                connection.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("Invalid choice");
        }
    }
}
