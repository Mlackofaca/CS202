/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat;

import database.IgracCRUD;
import database.KlubCRUD;
import database.LigaCRUD;
import entitys.Igrac;
import entitys.Klub;
import entitys.Liga;
import exceptions.NedovoljnoNovcaZaIgracaException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import util.UtilMessage;

/**
 *
 * @author Racunar
 */
public class Projekat extends Application {

    Liga l1 = new Liga("Premijer liga");
    Liga l2 = new Liga("La liga");
    Klub k1 = new Klub("Mancester Junajted", 14, 5000, l1);
    Klub k2 = new Klub("Mancester Siti", 11, 3000, l1);
    Klub k3 = new Klub("Barselona", 29, 8000, l2);
    Klub k4 = new Klub("Real Madrid", 19, 7000, l2);
    Klub k5 = new Klub("Atletiko Madrid", 16, 4200, l2);
    Klub k6 = new Klub("Liverpul", 15, 2300, l1);
    Igrac i1 = new Igrac("Petar", 1998, k1, 3500);
    Igrac i2 = new Igrac("Mesi", 1998, k1, 5000);
    Igrac i3 = new Igrac("Leonardo", 1998, k2, 2300);
    Igrac i4 = new Igrac("Milos", 1998, k2, 1000);
    Igrac i5 = new Igrac("Pantelic", 1998, k3, 1900);
    Igrac i6 = new Igrac("Mbape", 1998, k3, 1600);
    Igrac i7 = new Igrac("Haaland", 1998, k4, 2600);
    Igrac i8 = new Igrac("Pedro", 1998, k4, 1300);
    Igrac i9 = new Igrac("Kante", 1998, k5, 4100);
    Igrac i10 = new Igrac("Tadic", 1998, k5, 3000);
    Igrac i11 = new Igrac("Jovic", 1998, k6, 2900);
    Igrac i12 = new Igrac("Pogba", 1998, k6, 2000);

    ArrayList<Liga> ligaList = new ArrayList<>();
    ArrayList<Klub> klubList = new ArrayList<>();
    ArrayList<Igrac> igracList = new ArrayList<>();
    ArrayList<Igrac> igracList1 = new ArrayList<>();
    ArrayList<Igrac> igracList2 = new ArrayList<>();
    ArrayList<Igrac> igracList3 = new ArrayList<>();
    ArrayList<Igrac> igracList4 = new ArrayList<>();
    ArrayList<Igrac> igracList5 = new ArrayList<>();
    ArrayList<Igrac> igracList6 = new ArrayList<>();

    private Label lbl1 = new Label("Unesi ime igraca : ");
    private Label lbl2 = new Label("Unesi Godinu rodjenja igraca : ");
    private Label lbl3 = new Label("Unesi Klub igraca : ");
    private Label lbl4 = new Label("Unesi cenu igraca : ");
    private Label lbl5 = new Label("Izaberite koju ligu zelite da prikazete : ");
    private Label lbl6 = new Label("Izaberite klub koji prodaje igraca");
    private Label lbl7 = new Label("Izaberite klub koji kupuje igraca");
    private Label lbl8 = new Label("Buzdet kluba : ");
    private Label lbl9 = new Label("Buzdet kluba : ");

    private TextField txt1 = new TextField();
    private TextField txt2 = new TextField();
    private TextField txt4 = new TextField();
    private TextField txt5 = new TextField();

    private Button btn = new Button();
    private Button btn1 = new Button("Nazad");
    private Button btn2 = new Button("Unesi igraca");
    private Button btn3 = new Button("Prikazi klubove");
    private Button btn4 = new Button("Transfer igraca");
    private Button btn5 = new Button("Prikazi igrace");
    private Button btn6 = new Button("Izvrsi transfer");
    private Button btn7 = new Button("Promeni ligu");

    private BorderPane root = new BorderPane();
    private BorderPane root1 = new BorderPane();

    private VBox vBox1 = new VBox();
    private HBox hb = new HBox();
    private HBox hb1 = new HBox();
    private HBox hb2 = new HBox();
    private VBox vb = new VBox();
    private VBox vb2 = new VBox();
    private VBox vb3 = new VBox();
    private VBox vboxic = new VBox();
    private HBox hbSlike = new HBox();
    private HBox hbSlike2 = new HBox();

    private ComboBox<String> kmb;
    private ComboBox<String> kmb1;
    private ComboBox<String> kmb2;
    private TableView<Klub> tabela = new TableView<>();
    private TableView<Igrac> tabela1 = new TableView<>();
    private TableView<Igrac> tabela2 = new TableView<>();

    private RadioButton rb1 = new RadioButton("La liga");
    private RadioButton rb2 = new RadioButton("Premijer liga");

    private ImageView slika;
    private ImageView slika2;

    /**
     * U ovoj metodi se vrsi cela logika. Rad sa bazom, pravimo graficki
     * korisnicki interfejs.
     *
     * @throws NedovoljnoNovcaZaIgracaException Ovaj izuzetak se poziva ukoliko
     * je budzet kluba manji od cene igraca kog klub zeli da kupi.
     * @throws SQLException Izuzetak koji nam daje informacije o gresci u radu
     * sa bazom.
     */
    @Override
    public void start(Stage primaryStage) throws SQLException, NedovoljnoNovcaZaIgracaException, IOException {
        //kreiramo bazu podataka
        //DbUtil.createDatabase();

        //unosimo sve lige, klubove i igrace u bazu
        LigaCRUD.insertLiga(l1);
        LigaCRUD.insertLiga(l2);
        KlubCRUD.insertKlub(k1);
        KlubCRUD.insertKlub(k2);
        KlubCRUD.insertKlub(k3);
        KlubCRUD.insertKlub(k4);
        KlubCRUD.insertKlub(k5);
        KlubCRUD.insertKlub(k6);
        IgracCRUD.insertIgrac(i1);
        IgracCRUD.insertIgrac(i2);
        IgracCRUD.insertIgrac(i3);
        IgracCRUD.insertIgrac(i4);
        IgracCRUD.insertIgrac(i5);
        IgracCRUD.insertIgrac(i6);
        IgracCRUD.insertIgrac(i7);
        IgracCRUD.insertIgrac(i8);
        IgracCRUD.insertIgrac(i9);
        IgracCRUD.insertIgrac(i10);
        IgracCRUD.insertIgrac(i11);
        IgracCRUD.insertIgrac(i12);

        igracList1 = IgracCRUD.readIgracByKlub(k1);
        igracList2 = IgracCRUD.readIgracByKlub(k2);
        igracList3 = IgracCRUD.readIgracByKlub(k3);
        igracList4 = IgracCRUD.readIgracByKlub(k4);
        igracList5 = IgracCRUD.readIgracByKlub(k5);
        igracList6 = IgracCRUD.readIgracByKlub(k6);

        //dodeljujemo liste klubovi koji su u njihovom klubu
        k1.setListaIgraca(igracList1);
        k2.setListaIgraca(igracList2);
        k3.setListaIgraca(igracList3);
        k4.setListaIgraca(igracList4);
        k5.setListaIgraca(igracList5);
        k6.setListaIgraca(igracList6);

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    IgracCRUD.deleteIgrac(i1);
                    IgracCRUD.deleteIgrac(i2);
                    IgracCRUD.deleteIgrac(i3);
                    IgracCRUD.deleteIgrac(i4);
                    IgracCRUD.deleteIgrac(i5);
                    IgracCRUD.deleteIgrac(i6);
                    IgracCRUD.deleteIgrac(i7);
                    IgracCRUD.deleteIgrac(i8);
                    IgracCRUD.deleteIgrac(i9);
                    IgracCRUD.deleteIgrac(i10);
                    IgracCRUD.deleteIgrac(i11);
                    IgracCRUD.deleteIgrac(i12);
                    KlubCRUD.deleteKlub(k1);;
                    KlubCRUD.deleteKlub(k2);
                    KlubCRUD.deleteKlub(k3);
                    KlubCRUD.deleteKlub(k4);
                    KlubCRUD.deleteKlub(k5);
                    KlubCRUD.deleteKlub(k6);
                    LigaCRUD.deleteLiga(l1);
                    LigaCRUD.deleteLiga(l2);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        });

        List<String> s = KlubCRUD.readAllNamesFromKlub();
        ObservableList<String> listaKlubova = FXCollections.observableArrayList(s);

        kmb = new ComboBox<String>(listaKlubova);

        vBox1.setSpacing(5);
        vBox1.setPadding(new Insets(10));

        vBox1.getChildren().addAll(lbl1, txt1, lbl2, txt2, lbl3, kmb, lbl4, txt4, btn2);

        root.setLeft(vBox1);

        klubList = KlubCRUD.readAllFromKlub();
        //btn2 unosi igraca u bazu. korisnik moze uneti samo brojeve
        //za cenu i godinu rodjenja igraca
        btn2.setOnAction(e -> {
            try {
                Igrac i = new Igrac();
                i.setIgracIme(txt1.getText());
                try {
                    i.setIgracGodinaRodjenja(Integer.parseInt(txt2.getText()));

                    try {
                        i.setIgracaCena(Double.parseDouble(txt4.getText()));

                        String s1 = kmb.getSelectionModel().getSelectedItem();
                        for (Klub k : klubList) {
                            int j = 0;
                            Klub k1 = new Klub();
                            k1.setKlubIme(s1);
                            if (k.getKlubIme().equals(k1.getKlubIme())) {
                                i.setKlub(k);
                            }

                            j++;
                        }

                        IgracCRUD.insertIgrac(i);
                        clear();
                        UtilMessage.showInfoMessage("Uneli ste igraca");
                    } catch (NumberFormatException ex1) {
                        UtilMessage.showErrMessage("Morate uneti broj za cenu igraca");
                        System.err.println("Morate uneti broj za cenu igraca");
                    }
                } catch (NumberFormatException ex2) {
                    UtilMessage.showErrMessage("Morate uneti broj za godinu rodjenja igraca");
                    System.err.println("Morate uneti broj za godinu rodjenja igraca");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                UtilMessage.showErrMessage("Neispravno uneti podaci");
            }

        });
        ToggleGroup tg = new ToggleGroup();
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);

        hb.setSpacing(20);
        vb.setSpacing(5);

        //pomocu btn3 prikazujemo klubove. na osnovu toga koji je radio button selektovan 
        //prikazuju se klubovi iz odredjene lige
        btn3.setOnAction(k -> {
            if (rb1.isSelected() == true) {
                try {
                    tabela.setItems(FXCollections.observableArrayList(KlubCRUD.readKlubByLigaId(l2)));
                    tabela.refresh();
                    tabela.requestLayout();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            } else if (rb2.isSelected() == true) {

                try {
                    tabela.setItems(FXCollections.observableArrayList(KlubCRUD.readKlubByLigaId(l1)));
                    tabela.refresh();
                    tabela.requestLayout();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        TableColumn<Klub, Integer> klubIdCol = new TableColumn<>("Id");
        TableColumn<Klub, String> klubImeCol = new TableColumn<>("Ime");
        TableColumn<Klub, Integer> klubBrPobedaCol = new TableColumn<>("Broj Pobeda");
        TableColumn<Klub, Double> klubBudzetCol = new TableColumn<>("Budzet");
        TableColumn<Klub, String> klubLigaCol = new TableColumn<>("Liga");
        tabela.getColumns().add(klubIdCol);
        tabela.getColumns().add(klubImeCol);
        tabela.getColumns().add(klubBrPobedaCol);
        tabela.getColumns().add(klubBudzetCol);
        tabela.getColumns().add(klubLigaCol);

        klubImeCol.setMinWidth(150);
        klubLigaCol.setMinWidth(100);
        klubBrPobedaCol.setMinWidth(100);
        klubIdCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getKlubId()));
        klubImeCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getKlubIme()));
        klubBrPobedaCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getKlubBrojPobeda()));
        klubBudzetCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getKlubBudzet()));
        klubLigaCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getLiga().getLigaIme()));

        tabela.refresh();
        tabela.requestLayout();

        ligaList = LigaCRUD.readAllFromLiga();
        //btn7 menja ligu klubu. Posto imamo 2 kluba ako klub pripada jednoj ligi 
        //menjamo mu ligu u onu drugu ligu.
        btn7.setOnAction(e -> {
            
                if (tabela.getSelectionModel().getSelectedItem().getLiga().getLigaId()==l2.getLigaId()) {
                    try {
                        Klub klub = tabela.getSelectionModel().getSelectedItem();
                        KlubCRUD.promeniLigu(klub, l1);
                        klub.setLiga(l1);
                        l1.setListaKlubova(KlubCRUD.readKlubByLigaId(l1));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }else if (tabela.getSelectionModel().getSelectedItem().getLiga().getLigaId()==l1.getLigaId()) {
                    try {
                        Klub klub = tabela.getSelectionModel().getSelectedItem();
                        KlubCRUD.promeniLigu(klub, l2);
                        klub.setLiga(l2);
                        l2.setListaKlubova(KlubCRUD.readKlubByLigaId(l2));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                btn3.fire();
            
            UtilMessage.showInfoMessage("Uspesno ste promenili ligu");
            tabela.refresh();
            tabela.requestLayout();
        });

        //druga scena
        vb2.setPadding(new Insets(10));
        vb3.setPadding(new Insets(10));
        vb2.setSpacing(10);
        vb3.setSpacing(10);

        kmb1 = new ComboBox<String>(listaKlubova);
        kmb2 = new ComboBox<String>(listaKlubova);
        //kreiranje tabela

        //na osnovu toga koji klubovi su selektovani u Combo box-u 
        //vrsimo transfer izmedju ta dva kluba onog igraca koji je selektovan u tabeli2
        //na kraju pozivamo btn5 za prikaz igraca
        btn6.setOnAction(e -> {
            for (int i = 0; i < klubList.size(); i++) {
                if (kmb1.getSelectionModel().getSelectedItem().equals(klubList.get(i).getKlubIme())) {
                    for (int j = 0; j < klubList.size(); j++) {
                        if (kmb2.getSelectionModel().getSelectedItem().equals(klubList.get(j).getKlubIme()))
            try {
                            Igrac igrac = tabela2.getSelectionModel().getSelectedItem();
                            klubList.get(i).kupiIgraca(klubList.get(j), igrac);
                            KlubCRUD.kupiIgraca(klubList.get(i), klubList.get(j), igrac);

                            lbl9.setText("Budzet kluba : " + klubList.get(j).getKlubBudzet());
                            lbl8.setText("Budzet kluba : " + klubList.get(i).getKlubBudzet());
                            UtilMessage.showInfoMessage("Uspesan transfer igraca");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } catch (NedovoljnoNovcaZaIgracaException ex) {
                            UtilMessage.showErrMessage(klubList.get(i).getKlubIme() + " nema dovoljno novca");
                            System.err.println(ex.getMessage());
                        }

                    }
                }
            }
            btn5.fire();
            tabela1.refresh();
            tabela2.refresh();
            tabela1.requestLayout();
            tabela2.requestLayout();
        });

        //na osnovu toga koji klub je selektovam u Combo box-ovima prikazujemo
        //igrace i grb tog klub
        btn5.setOnAction(e -> {

            if (kmb1.getSelectionModel().getSelectedItem().equals("Barselona")) {
                try {
                    hbSlike.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/barcelona-logo/").get();
                    Element el = doc.getElementsByClass("aligncenter wp-image-963 size-large").first();
                    String url1 = el.absUrl("src");
                    slika = new ImageView(url1);
                    hbSlike.getChildren().add(slika);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb1.getSelectionModel().getSelectedItem().equals("Liverpul")) {
                try {
                    hbSlike.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/liverpool-logo/").get();
                    Element el =doc.getElementsByClass("aligncenter wp-image-5552 size-large").first();
                    String url1 = el.absUrl("src");
                    slika = new ImageView(url1);
                    hbSlike.getChildren().add(slika);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb1.getSelectionModel().getSelectedItem().equals("Mancester Junajted")) {
                try {
                    hbSlike.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/manchester-united-logo/").get();
                    Element el = doc.select("#post-3772 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika = new ImageView(url1);
                    hbSlike.getChildren().add(slika);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb1.getSelectionModel().getSelectedItem().equals("Mancester Siti")) {
                try {
                    hbSlike.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/manchester-city-logo/").get();
                    Element el = doc.select("#post-3759 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika = new ImageView(url1);
                    hbSlike.getChildren().add(slika);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb1.getSelectionModel().getSelectedItem().equals("Atletiko Madrid")) {
                try {
                    hbSlike.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/atletico-madrid-logo/").get();
                    Element el = doc.select("#post-4118 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika = new ImageView(url1);
                    hbSlike.getChildren().add(slika);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb1.getSelectionModel().getSelectedItem().equals("Real Madrid")) {
                try {
                    hbSlike.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/real-madrid-logo/").get();
                    Element el = doc.select("#post-4149 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika = new ImageView(url1);
                    hbSlike.getChildren().add(slika);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb2.getSelectionModel().getSelectedItem().equals("Barselona")) {
                try {
                    hbSlike2.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/barcelona-logo/").get();
                    Element el = doc.select("#post-953 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika2 = new ImageView(url1);
                    hbSlike2.getChildren().add(slika2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb2.getSelectionModel().getSelectedItem().equals("Liverpul")) {
                try {
                    hbSlike2.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/liverpool-logo/").get();
                    Element el = doc.select("#post-3744 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika2 = new ImageView(url1);
                    hbSlike2.getChildren().add(slika2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb2.getSelectionModel().getSelectedItem().equals("Mancester Junajted")) {
                try {
                    hbSlike2.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/manchester-united-logo/").get();
                    Element el = doc.select("#post-3772 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika2 = new ImageView(url1);
                    hbSlike2.getChildren().add(slika2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb2.getSelectionModel().getSelectedItem().equals("Mancester Siti")) {
                try {
                    hbSlike2.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/manchester-city-logo/").get();
                    Element el = doc.select("#post-3759 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika2 = new ImageView(url1);
                    hbSlike2.getChildren().add(slika2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb2.getSelectionModel().getSelectedItem().equals("Atletiko Madrid")) {
                try {
                    hbSlike2.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/atletico-madrid-logo/").get();
                    Element el = doc.select("#post-4118 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika2 = new ImageView(url1);
                    hbSlike2.getChildren().add(slika2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (kmb2.getSelectionModel().getSelectedItem().equals("Real Madrid")) {
                try {
                    hbSlike2.getChildren().clear();
                    Document doc = Jsoup.connect("https://logos-world.net/real-madrid-logo/").get();
                    Element el = doc.select("#post-4149 > div.ast-post-format-.single-layout-1.ast-no-date-box > div > p:nth-child(2) > a > img").first();
                    String url1 = el.absUrl("src");
                    slika2 = new ImageView(url1);
                    hbSlike2.getChildren().add(slika2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            for (Klub k : klubList) {
                if (kmb1.getSelectionModel().getSelectedItem().equals(k.getKlubIme())) {
                    try {
                        tabela1.setItems(FXCollections.observableArrayList(IgracCRUD.readIgracByKlub(k)));
                        lbl8.setText("Budzet kluba : " + k.getKlubBudzet());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            for (Klub klub : klubList) {
                if (kmb2.getSelectionModel().getSelectedItem().equals(klub.getKlubIme())) {
                    try {
                        tabela2.setItems(FXCollections.observableArrayList(IgracCRUD.readIgracByKlub(klub)));
                        lbl9.setText("Budzet kluba : " + klub.getKlubBudzet());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });

        TableColumn<Igrac, Integer> igracIdCol = new TableColumn<>("Id");
        TableColumn<Igrac, String> igracImeCol = new TableColumn<>("Ime");
        TableColumn<Igrac, Integer> igracGodRodjCol = new TableColumn<>("Godina rodjenja");
        TableColumn<Igrac, String> igracKlubCol = new TableColumn<>("Klub");
        TableColumn<Igrac, Double> igracCenaCol = new TableColumn<>("Cena");

        igracGodRodjCol.setMinWidth(120);
        igracKlubCol.setMinWidth(150);
        tabela1.getColumns().add(igracIdCol);
        tabela1.getColumns().add(igracImeCol);
        tabela1.getColumns().add(igracGodRodjCol);
        tabela1.getColumns().add(igracKlubCol);
        tabela1.getColumns().add(igracCenaCol);

        igracIdCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getIgracId()));
        igracImeCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getIgracIme()));
        igracGodRodjCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getIgracGodinaRodjenja()));
        igracKlubCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getKlub().getKlubIme()));
        igracCenaCol.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getIgracCena()));

        TableColumn<Igrac, Integer> igracIdCol2 = new TableColumn<>("Id");
        TableColumn<Igrac, String> igracImeCol2 = new TableColumn<>("Ime");
        TableColumn<Igrac, Integer> igracGodRodjCol2 = new TableColumn<>("Godina rodjenja");
        TableColumn<Igrac, String> igracKlubCol2 = new TableColumn<>("Klub");
        TableColumn<Igrac, Double> igracCenaCol2 = new TableColumn<>("Cena");

        igracGodRodjCol2.setMinWidth(120);
        igracKlubCol2.setMinWidth(150);
        tabela2.getColumns().add(igracIdCol2);
        tabela2.getColumns().add(igracImeCol2);
        tabela2.getColumns().add(igracGodRodjCol2);
        tabela2.getColumns().add(igracKlubCol2);
        tabela2.getColumns().add(igracCenaCol2);

        igracIdCol2.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getIgracId()));
        igracImeCol2.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getIgracIme()));
        igracGodRodjCol2.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getIgracGodinaRodjenja()));
        igracKlubCol2.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getKlub().getKlubIme()));
        igracCenaCol2.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getIgracCena()));

        hb1.getChildren().addAll(kmb1, lbl8);
        hb1.setSpacing(10);
        vb2.getChildren().addAll(lbl7, hb1, tabela1, hbSlike);

        hb2.getChildren().addAll(kmb2, lbl9);
        hb2.setSpacing(10);
        vb3.getChildren().addAll(lbl6, hb2, tabela2, hbSlike2);

        vboxic.setSpacing(20);
        vboxic.getChildren().addAll(btn5, btn6, btn1);

        root1.setLeft(vb2);
        root1.setCenter(vboxic);
        root1.setRight(vb3);
        Scene scene1 = new Scene(root1, 1550, 800);
        //pomocu btn4 prelazimo na drugu scenu 
        btn4.setOnAction(e -> {

            primaryStage.setScene(scene1);
        });
     

        hb.getChildren().addAll(rb1, rb2, btn3, btn4, btn7);
        vb.getChildren().addAll(lbl5, hb, tabela);
        root.setCenter(vb);

        Scene scene = new Scene(root, 850, 350);
           btn1.setOnAction(e->{
    primaryStage.setScene(scene);
    });
        primaryStage.setTitle("Fudbal");
        primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Ova metoda nam cisti sva polja za unos podataka igraca, kog zelimo da
     * unesemo u bazu, kada kliknemo na dugme za unos igraca u bazu.
     *
     */
    public void clear() {
        txt1.clear();
        txt2.clear();
        txt4.clear();
        kmb.getSelectionModel().clearSelection();
    }
}
