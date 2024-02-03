package entitys;

public class Igrac {

    private Integer igracId;
    private String igracIme;
    private int igracGodinaRodjenja;
    private Klub klub;
    private double igracCena;

    /**
     * Kreiramo prazan konstruktor.
     *
     */
    public Igrac() {
    }

    /**
     * Kreiramo konstruktor sa svim parametrima.
     *
     * @param igracId Svaki igrac ima jedinstven id.
     * @param igracIme Ime igraca.
     * @param igracGodinaRodjenja Podatak kada je igrac rodjen.
     * @param klub Klub za koji igrac nastupa.
     * @param igracCena Trenutna vrednost igraca.
     */
    public Igrac(Integer igracId, String igracIme, int igracGodinaRodjenja, Klub klub, double igracCena) {
        this.igracId = igracId;
        this.igracIme = igracIme;
        this.igracGodinaRodjenja = igracGodinaRodjenja;
        this.klub = klub;
        this.igracCena = igracCena;
    }

    /**
     * Kreiramo konstruktor bez igracId,koji koristimo za kreiranje igrac, jer
     * ce nam baza dodeliti taj parametar.
     *
     * @param igracIme Ime igraca.
     * @param igracGodinaRodjenja Podatak kada je igrac rodjen.
     * @param klub Klub za koji igrac nastupa.
     * @param igracCena Trenutna vrednost igraca.
     */
    public Igrac(String igracIme, int igracGodinaRodjenja, Klub klub, double igracCena) {
        this.igracIme = igracIme;
        this.igracGodinaRodjenja = igracGodinaRodjenja;
        this.klub = klub;
        this.igracCena = igracCena;
    }

    public Integer getIgracId() {
        return igracId;
    }

    public void setIgracId(Integer igracId) {
        this.igracId = igracId;
    }

    public String getIgracIme() {
        return igracIme;
    }

    public void setIgracIme(String igracIme) {
        this.igracIme = igracIme;
    }

    public int getIgracGodinaRodjenja() {
        return igracGodinaRodjenja;
    }

    public void setIgracGodinaRodjenja(int igracGodinaRodjenja) {
        this.igracGodinaRodjenja = igracGodinaRodjenja;
    }

    public Klub getKlub() {
        return klub;
    }

    public void setKlub(Klub klub) {
        this.klub = klub;
    }

    public double getIgracCena() {
        return igracCena;
    }

    public void setIgracaCena(double igracCena) {
        this.igracCena = igracCena;
    }

    @Override
    public String toString() {
        return "Igrac{" + "igracId=" + igracId + ", igracIme=" + igracIme + ", igracGodinaRodjenja=" + igracGodinaRodjenja + ", klub=" + klub.getKlubIme() + ", igracCena=" + igracCena + '}';
    }

}
