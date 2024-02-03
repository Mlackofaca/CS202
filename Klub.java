package entitys;

import exceptions.NedovoljnoNovcaZaIgracaException;
import java.util.ArrayList;


public class Klub {

    private Integer klubId;
    private String klubIme;
    private Integer klubBrojPobeda;
    private double klubBudzet;
    private Liga liga;
    private ArrayList<Igrac> listaIgraca = new ArrayList<>();

    /**
     * Kreiramo prazan konstruktor.
     *
     */
    public Klub() {
    }

    /**
     * Kreiramo konstruktor sa svim parametrima.
     *
     * @param klubId Svaki klub ima svoj jedinstveni id.
     * @param klubIme Svaki klub ima svoje ime koje je jedinstveno.
     * @param klubBrojPobeda Broj pobeda koje ima klub.
     * @param klubBudzet Klub pomocu budzeta kupuje igrace.
     * @param liga Liga kojoj klub pripada.
     */
    public Klub(Integer klubId, String klubIme, Integer klubBrojPobeda, double klubBudzet, Liga liga) {
        this.klubId = klubId;
        this.klubIme = klubIme;
        this.klubBrojPobeda = klubBrojPobeda;
        this.klubBudzet = klubBudzet;
        this.liga = liga;
    }

    /**
     * Kreiramo konstruktor bez klubId,koji koristimo za kreiranje klubova, jer
     * baza dodeljuje taj parametar.
     *
     * @param klubIme Svaki klub ima svoje ime koje je jedinstveno.
     * @param klubBrojPobeda Broj pobeda koje ima klub.
     * @param klubBudzet Klub pomocu budzeta kupuje igrace.
     * @param liga Liga kojoj klub pripada.
     */
    public Klub(String klubIme, Integer klubBrojPobeda, double klubBudzet, Liga liga) {
        this.klubIme = klubIme;
        this.klubBrojPobeda = klubBrojPobeda;
        this.klubBudzet = klubBudzet;
        this.liga = liga;
    }

    public Integer getKlubId() {
        return klubId;
    }

    public void setKlubId(Integer klubId) {
        this.klubId = klubId;
    }

    public String getKlubIme() {
        return klubIme;
    }

    public void setKlubIme(String klubIme) {
        this.klubIme = klubIme;
    }

    public Integer getKlubBrojPobeda() {
        return klubBrojPobeda;
    }

    public void setKlubBrojPobeda(Integer klubBrojPobeda) {
        this.klubBrojPobeda = klubBrojPobeda;
    }

    public Double getKlubBudzet() {
        return klubBudzet;
    }

    public void setKlubBudzet(double klubBudzet) {
        this.klubBudzet = klubBudzet;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

    public ArrayList<Igrac> getListaIgraca() {
        return listaIgraca;
    }

    public void setListaIgraca(ArrayList<Igrac> listaIgraca) {
        this.listaIgraca = listaIgraca;
    }

    @Override
    public String toString() {
        return "Klub{" + "klubId=" + klubId + ", klubIme=" + klubIme + ", klubBrojPobeda=" + klubBrojPobeda + ", klubBudzet=" + klubBudzet + ", liga=" + liga.getLigaIme() + '}';
    }

    /**
     * Ova metoda prebacuje igraca iz kluba koji prodaje igraca u klub koji
     * kupuje igraca. Smanjuje budzet kluba koji kupuje igraca, odnosno povecava
     * budzet kluba koji prodaje igraca, za vrednost tog igraca.
     *
     * @param k Klub koji prodaje igraca.
     * @param i Igrac kog klub zeli da kupi.
     * @throws NedovoljnoNovcaZaIgracaException Ovaj izuzetak se poziva ukoliko
     * je budzet kluba manji od cene igraca kog klub zeli da kupi.
     */
    public void kupiIgraca(Klub k, Igrac i) throws NedovoljnoNovcaZaIgracaException {

        if (this.klubBudzet > i.getIgracCena()) {
            this.listaIgraca.add(i);
            i.setKlub(this);
            double p = this.klubBudzet - i.getIgracCena();
            this.setKlubBudzet(p);
            double a = k.klubBudzet + i.getIgracCena();
            k.setKlubBudzet(a);
            k.listaIgraca.remove(i);
        } else {
            throw new NedovoljnoNovcaZaIgracaException(this.klubIme + " nema dovoljno novca za ovog igraca!");
        }

    }

}
