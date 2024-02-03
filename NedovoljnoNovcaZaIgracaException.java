/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;




public class NedovoljnoNovcaZaIgracaException extends Exception {
/**
 * NedovoljnoNovcaZaIgracaException Ovaj izuzetak se poziva ukoliko je budzet
 * kluba manji od cene igraca kog klub zeli da kupi.
 *@param message poruka koja se prikazuje kada se pozove izuzetak
 */
    public NedovoljnoNovcaZaIgracaException(String message) {
        super(message);
    }

}
