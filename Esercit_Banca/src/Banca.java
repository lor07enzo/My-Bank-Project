import java.util.ArrayList;
import java.util.Scanner;
import models.Cliente;
import models.Conto;

public class Banca {
    
    private static ArrayList<Cliente> cliente = new ArrayList<Cliente>();
    private static ArrayList<Conto> conto = new ArrayList<Conto>();
    private static Scanner scanner = new Scanner(System.in);
    private static Boolean loggato = false;
    private static Cliente clienteLoggato = null;
    private static Conto contoSelezionato = null;

    public static void main(String[] args) throws Exception {

        int sceltaMenu;
        
        System.out.println("Benvenuto nel sistema bancario. Scegli un'operazione: ");
        
        do {
            System.out.println("\nScegli un'operazione: ");
            System.out.println("1. Registrati.");
            System.out.println("2. Accedi.");
            System.out.println("3. Crea nuovo conto.");
            System.out.println("4. Accedi al conto.");
            System.out.println("5. Esci.");
            
            sceltaMenu = scanner.nextInt();
            scanner.nextLine();
            
            
            switch (sceltaMenu) {
                case 1 -> {
                    // Codice per la registrazione di un nuovo cliente
                    Cliente nuovoCliente = nuovoCliente();
                    cliente.add(nuovoCliente);
                    System.out.println("\nCliente registrato con successo: ");
                    System.out.println(nuovoCliente);
                }
                case 2 -> // Codice per l'accesso dell'utente
                    loginCliente();
                case 3 -> {
                    // Codice per la creazione di un nuovo conto
                    Conto nuovoConto = nuovoConto();
                    conto.add(nuovoConto);
                }
                case 4 -> {
                    // Codice per l'accesso al conto
                    if (loggato && clienteLoggato != null) {
                        contoSelezionato = clienteLoggato.mostraSceltaConti(scanner);
                        
                        if (contoSelezionato == null) {
                            System.out.println("Nessun conto selezionato.");
                            break;
                        }
                    } else {
                        System.out.println("Devi accedere per visualizzare i tuoi conti.");
                    }

                    do{
                        System.out.println("\nScegli un'operazione: ");
                        System.out.println("1. Visualizza saldo del conto.");
                        System.out.println("2. Effettua un deposito.");
                        System.out.println("3. Effettua un prelievo.");
                        System.out.println("4. Esci.");

                        sceltaMenu = scanner.nextInt(); // riciclo la variabile
                        scanner.nextLine();

                        switch (sceltaMenu) {
                            case 1 -> // Visualizza saldo del conto
                                System.out.println("Saldo attuale: " + contoSelezionato.getSaldo());
                            case 2 -> {
                                // Effettua un deposito
                                System.out.print("Inserisci l'importo da depositare: ");
                                double imp = scanner.nextDouble();
                                scanner.nextLine();

                                contoSelezionato.deposita(imp);
                            }
                            case 3 -> {
                                // Effettua un prelievo
                                System.out.print("Inserisci l'importo da prelevare: ");
                                Double impPrelievo = scanner.nextDouble();
                                scanner.nextLine();

                                contoSelezionato.preleva(impPrelievo);
                            }
                            case 4 -> System.out.println("Uscita dal conto.");
                            default -> System.out.println("Scelta non valida. Riprova.");
                        }
                    } while (sceltaMenu != 4);
                }
                case 5 -> System.out.println("Uscita dal sistema. Arrivederci!");
                default -> System.out.println("Scelta non valida. Riprova.");
            }
        } while (sceltaMenu != 5);
        
        
        scanner.close();
    }


    private static Cliente nuovoCliente() {
        System.out.print("Inserisci il nome: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci il cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Inserisci l'email: ");
        String email = scanner.nextLine();
        
        return new Cliente(nome, cognome, email);
    }
    

    private static Cliente loginCliente(){
        System.out.print("Inserisci la tua email: ");
        String email = scanner.nextLine();

        for (Cliente c : cliente) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                loggato = true;
                clienteLoggato = c;
                System.out.println("Accesso effettuato con successo.");
                return c;
            }
        }
        System.out.println("Email non trovata. Riprova.");
        return null;
        
    }

    private static Conto nuovoConto() {
        if (loggato && clienteLoggato != null) {
            Conto nuovo = new Conto();
            clienteLoggato.addConto(nuovo);
            System.out.println("Nuovo conto creato con successo!");
            System.out.println("ID conto: " + nuovo.getIdConto());
            return nuovo;
        } else {
            System.out.println("Devi essere loggato per creare un conto.");
            return null;
        }
    }
}
