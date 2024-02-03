package entitys;

import java.util.ArrayList;

public class Liga {

    private int ligaId;
    private String ligaIme;
    private ArrayList<Klub> listaKlubova = new ArrayList<>();

    /**
     * Kreiramo prazan konstruktor.
     *
     */
    public Liga() {
    }

    /**
     * Kreiramo konstruktor bez ligaId, koji koristimo za kreiranje lige, jer
     * nam taj parametar odredjuje baza.
     *
     * @param ligaIme Ime lige.
     */
    public Liga(String ligaIme) {
        this.ligaIme = ligaIme;
    }

    /**
     * Kreiramo konstruktor sa svim parametrima.
     *
     * @param ligaId Svaka liga ima jedinstven ligaId.
     * @param ligaIme Ime lige.
     *
     */
    public Liga(int ligaId, String ligaIme) {
        this.ligaId = ligaId;
        this.ligaIme = ligaIme;
    }

    public int getLigaId() {
        return ligaId;
    }

    public void setLigaId(int ligaId) {
        this.ligaId = ligaId;
    }

    public String getLigaIme() {
        return ligaIme;
    }

    public void setLigaIme(String ligaIme) {
        this.ligaIme = ligaIme;
    }

    public ArrayList<Klub> getListaKlubova() {
        return listaKlubova;
    }

    public void setListaKlubova(ArrayList<Klub> listaKlubova) {
        this.listaKlubova = listaKlubova;
    }

    @Override
    public String toString() {
        return "Liga{" + "ligaId=" + ligaId + ", ligaIme=" + ligaIme + ", listaKlubova=" + listaKlubova + '}';
    }

}
