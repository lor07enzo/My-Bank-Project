package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    
    private String nome;
    private String cognome;
    private String email;
    private ArrayList<Conto> conti;

    public Cliente(String nome, String cognome, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.conti = new ArrayList<>();
    }

    // Getters and Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Conto> getConti() { 
        return conti; 
    }

    public void addConto(Conto c) { 
        conti.add(c); 
    }

    public Conto mostraSceltaConti( Scanner scanner) {
        int i = 1;
        if (conti.isEmpty()) {
            System.out.println("Non hai nessun conto.");
        } else {
            for (Conto c : conti) {
                System.out.println(i + ". " + c);
                i++;
            }
        }
        System.out.print("Digita l'indice del conto a cui vuoi accedere: ");
        int scelta = scanner.nextInt();
        scanner.nextLine();

        if (scelta < 1 || scelta > conti.size()) {
            System.out.println("Errore: scelta non valida.");
            return null;
        }

        Conto contoSelezionato = conti.get(scelta - 1);
        System.out.println("Hai selezionato il conto: " + contoSelezionato);
        return contoSelezionato;
    }

    

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

      
    
}
