/**
 *
 * @author adamb
 */
package com.example.restservice;

import java.util.Scanner;  // import package(Scanner class), um user input zu bekommen
import java.util.HashMap;
import java.util.Random;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class RestServiceApplication {
    
    public static int unentschieden;
    public static int gewinnen;
    public static int verloren;
    
    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);

        //Willkommen Nachricht
        System.out.println("\n" + "\n" + "Willkommen beim Spiel: Stein, Schere, Papier, und/oder brunnen."
         + " Bitte, schreiben Sie erst 'A' oder 'B' im Terminal wenn Sie mit Variante A, oder B spielen wollen. Jenach welche Spial Variante wählen Sie eine Option aus eine der folgenden zwei Spiel Varianten: A:{stein, schere, papier}, B:{stein, schere, papier, brunnen}"
         +"\n" + "schreiben Sie die gewählte Option auf," + " und schauen Sie, ob Sie den Computer besiegen werden.");

        //Variablen definieren um gewinnnen, verloren, unentscheieden Ereignisse zu zählen
        unentschieden = 0;
        gewinnen = 0;
        verloren = 0;
        playGame();         
    }

    public static void playGame() {
        Scanner user_input = new Scanner(System.in);

        // user input
        String variante = user_input.nextLine();
        String option = user_input.nextLine();

        
        // Erstellung von HashMap 
        HashMap<Integer, String> my_hm
            = new HashMap<Integer, String>(); 
        
        // Optionen in hm hinfügen
        my_hm.put(1, "stein"); 
        my_hm.put(2, "papier"); 
        my_hm.put(3, "schere");   
        
        // Erzeuger eines zufälligen Auswahls von HashMap
        Object[] values = my_hm.values().toArray();
        Object computer_Auswahl = values[new Random().nextInt(values.length)];
        
        // computer_Auswahl Object zum String umwandeln
        String computer_String= computer_Auswahl.toString();
        
        // ordne jede zufällige computer_Auswahl eine Nummer zu
        int computer = wert(computer_String);

        // für Spiel Variante B (computer Wahl) - Erstellung von zufälligen Nummer(1-4)
        int computerB = (int) (Math.random()*4 + 1);

        // für Spiel Variante B für jede Nummer die richtige Option zuorden 
        String computer_Auswahl_B =  toString(computerB);
        
        // Spiel Variante - A
        if (varianteA(variante)) {
            // weiter spielen nur wenn User Input gueltig ist
            if (gueltigA(option)) {
                int spieler = wert(option);
                System.out.println("Computer wählte " + computer_Auswahl);
                System.out.println("Sie wählten " + option);
                System.out.println(konzeptA(spieler,computer));
            }
            // wenn nicht, dann kein gültig Input Nachricht zeigen 
            else {
                System.out.println("Ihre Eingabe ist nicht gueltig, bitte fügen Sie eine Option aus der Liste ein");

            }
        }
        // Spiel Variante - B
        else if (varianteB(variante)) {
            if (gueltigB(option)) {
                int spieler = wert(option);
                System.out.println("Computer wählte " + computer_Auswahl_B);
                System.out.println("Sie wählten " + option);
                System.out.println(konzeptB(spieler,computerB));
            }
            // wenn nicht, dann kein gültig Input Nachricht zeigen 
            else {
                System.out.println("Ihre Eingabe ist nicht gueltig, bitte fügen Sie eine Option aus der Liste ein");

            }
        }
        else {
            System.out.println("Bitte, wählen Sie die Variante des Spiels A, oder B");
        }
        // rekursive Function um Spieler zu erlauben, noch mal zu spielen
        playGame();
    }
    
    // Spiel Regeln für Variante A
    public static String konzeptA(int spieler, int computer) {   

        //stein=1 & papier=2 & schere=3
        // wenn Spiel_Auswhal=computer_Auswahl
        if(spieler == computer){
            unentschieden++;
            return "Unentschieden";
        }

        // wenn Spiel=stein & computer=papier
        if(spieler == 1 && computer == 2){
            verloren++;
            return "Sie haben verloren";
        }

        // wenn Spiel=stein & computer=schere
        if(spieler == 1 && computer == 3){
            gewinnen++;
            return "Glückwunsch, Sie haben gewonnen";
        }

        // wenn Spiel=papier & computer=schere
        if(spieler == 2 && computer == 3){
            verloren++;
            return "Sie haben verloren";
        }

        // wenn Spiel=papier & computer=stein
        if(spieler == 2 && computer == 1){
            gewinnen++;
            return "Glückwunsch, Sie haben gewonnen";
        }

        // wenn Spiel=schere & computer=stein
        if(spieler == 3 && computer == 1){
            verloren++;
            return "Sie haben verloren";
        }

        // wenn Spiel=schere & computer=papier
        if(spieler == 3 && computer == 2){
            gewinnen++;
            return "Glückwunsch, Sie haben gewonnen";
        }
        return "Err";
    }

    // Spiel Regeln für Variante B
    public static String konzeptB(int spieler, int computerB) {   

        //stein=1 & papier=2 & schere=3 & brunnen =4
        // wenn Spiel_Auswhal=computer_Auswahl
        if(spieler == computerB){
            unentschieden++;
            return "Unentschieden";
        }

        // wenn Spiel=stein & computer=papier
        if(spieler == 1 && computerB == 2){
            verloren++;
            return "Sie haben verloren";
        }

        // wenn Spiel=stein & computer=schere
        if(spieler == 1 && computerB == 3){
            gewinnen++;
            return "Glückwunsch, Sie haben gewonnen";
        }

        // wenn Spiel=stein & computer=brunnen
        if(spieler == 1 && computerB == 4){
            verloren++;
            return "Sie haben verloren";
        }        

        // wenn Spiel=papier & computer=schere
        if(spieler == 2 && computerB == 3){
            verloren++;
            return "Sie haben verloren";
        }

        // wenn Spiel=papier & computer=stein
        if(spieler == 2 && computerB == 1){
            gewinnen++;
            return "Glückwunsch, Sie haben gewonnen";
        }

        // wenn Spiel=papier & computer=brunnen
        if(spieler == 2 && computerB == 4){
            gewinnen++;
            return "Glückwunsch, Sie haben gewonnen";
        }     

        // wenn Spiel=schere & computer=stein
        if(spieler == 3 && computerB == 1){
            verloren++;
            return "Sie haben verloren";
        }

        // wenn Spiel=schere & computer=papier
        if(spieler == 3 && computerB == 2){
            gewinnen++;
            return "Glückwunsch, Sie haben gewonnen";
        }

        // wenn Spiel=schere & computer=brunnen
        if(spieler == 3 && computerB == 4){
            verloren++;
            return "Sie haben verloren";
        } 
        
        // wenn Spiel=brunnen & computer=stein
        if(spieler == 4 && computerB == 1){
            gewinnen++;
            return "Glückwunsch, Sie haben gewonnen";
        }

        // wenn Spiel=brunnen & computer=papier
        if(spieler == 4 && computerB == 2){
            verloren++;
            return "Sie haben verloren";
        }

        // wenn Spiel=brunnen & computer=schere
        if(spieler == 4 && computerB == 3){
            gewinnen++;
            return "Glückwunsch, Sie haben gewonnen";
        }
        return "Err";
    }

    // spieler muss erst wählen welche Spiel Variante - A
    public static boolean varianteA(String string) {
        if (string.equalsIgnoreCase("A")) {
            return true;
        } 
        else {
            return false;
        }
    }

    // spieler muss erst wählen welche Spiel Variante - B
    public static boolean varianteB(String string) {
        if (string.equalsIgnoreCase("B")) {
            return true;
        } 
        else {
            return false;
        }
    }
    
    // nur Inputs aus der Liste:{stein, schere, papier} sind gueltig für Nutzer 
    public static boolean gueltigA(String string) {
        if (string.equalsIgnoreCase("stein")) {
             return true;
        } 
        if (string.equalsIgnoreCase("schere")) {
             return true;
        } 
        if (string.equalsIgnoreCase("papier")) {
             return true;
        }
        else {
            return false;
        }
    }
    
    // nur Inputs aus der Liste:{stein, schere, papier, brunnen} sind gueltig für Nutzer 
    public static boolean gueltigB(String string) {
        if (string.equalsIgnoreCase("stein")) {
            return true;
        } 
        if (string.equalsIgnoreCase("schere")) {
            return true;
        } 
        if (string.equalsIgnoreCase("papier")) {
            return true;
        }
        if (string.equalsIgnoreCase("brunnen")) {
            return true;
        }
        else {
            return false;
        }
   }
    // um es einfacher zu machen, werde ich hier eine Nummer für jede Option zuordnen
    //stein=1 & papier=2 & schere=3
    public static int wert(String string) {

        if (string.equalsIgnoreCase("stein")) {
            return 1;
        }
        if (string.equalsIgnoreCase("papier")) {
            return 2;
        }
        if (string.equalsIgnoreCase("schere")) {
            return 3;
        }
        if (string.equalsIgnoreCase("brunnen")) {
            return 4;
        }
        else {
            return 5;
        }       
    }  
    
    // für jede Nummer die richtige Option zuordnen -variante B
    //stein=1 & papier=2 & schere=3 & Brunnen = 4
    public static String toString(int i){
        if (i == 1) {
            return "stein";
        }
        if (i == 2) {
            return "papier";    
        }
        if (i == 3) {
            return "schere";
        }
        if (i == 4) {
            return "Brunnen";
        }
        else {
            return "Err";
        }       
    }  
    
    // gewinnen score in JSON (siehe: scoresController.java) 
    public static int getCountWin() {
        return gewinnen;
    }

    // Verlieren score in JSON (siehe: scoresController.java) 
    public static int getCountLose() {
        return verloren;
    }

    // unentschieden score in JSON (siehe: scoresController.java) 
    public static int getCountTie() {
        return unentschieden;
    }
}
    