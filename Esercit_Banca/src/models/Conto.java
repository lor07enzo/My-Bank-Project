package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Conto {

    private UUID idConto;
    private Double saldo;
    private List<String> storico;

    // Constructor
    public Conto() {
        this.idConto = UUID.randomUUID();
        this.saldo = 0.0;
        this.storico = new ArrayList<>();
        this.storico.add(timestamp() + "Conto creato: saldo 0.0");
    }

    public String timestamp(){
        return "(" + LocalDateTime.now() + ") ";
    }

    // Getters and Setters
    public UUID getIdConto() {
        return idConto;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public List<String> getStorico() {
        return storico;
    }

    public void setStorico(List<String> storico) {
        this.storico = storico;
    }

    public void deposita(Double importo) {
        if (importo <= 0.0 | importo == null) {
            System.out.println("Importo di deposito non valido.");
        } else {
            this.saldo += importo;
            this.storico.add(timestamp() + "Deposito di: +" + importo);
            System.out.println("Saldo attuale: " + this.saldo);
        }
    }

    public void preleva(Double importo) {
        if(importo > this.saldo | importo == null) {
            System.out.println("Operazione non valida.");
        } else {
            this.saldo -= importo;
            this.storico.add(timestamp() + "Prelievo di: -" + importo);
            System.out.println("Saldo attuale: " + this.saldo);
        }
    }

    @Override
    public String toString() {
        return "Conto{" +
                "numero='" + idConto + '\'' +
                ", saldo=" + saldo +
                '}';
    }

}