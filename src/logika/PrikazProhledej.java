/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Map;
import java.util.HashMap;
/*******************************************************************************
 * Instance třídy PrikazSeber představují ...
 * @author    Jakub Skála (skaj06)
 * @version   ZS 2015/2016
 */
public class PrikazProhledej implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "prohledej";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazProhledej(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     *  Provádí příkaz "prohledej". 
     *  
     *  Věc musí být prohledatelná, aby bylo možné příkaz použít.
     *  Po úspěšném prohledání se věc vloží do baťůžky za podmínky neplnosti baťůžku.
     *  Většinu věcí nelze prohledat.
     *  Může se prohledat pouze věc (nikoliv lokace či osoba).
     *  
     *  @param parametry - jako parametr se zadává věc, která se má prohledat.
     *  @return zpráva, kterou vypíše hra hráči
     *  
     *  
     *  
     */ 

    public String proved(String... parametry){
        String vracenyText = null;        
        Prostor aktualni = plan.getAktualniProstor();  
        String nazevVeciKProhledani = null;
        Vec vecKPRohledani = null;

        if (parametry.length == 0){        
            vracenyText =  "Co mám prohledat?";
        }
        if (parametry.length == 1) {     
            nazevVeciKProhledani = parametry[0];
            vecKPRohledani = aktualni.ziskejVec(nazevVeciKProhledani);
            if (vecKPRohledani instanceof Vec) {
                if ((plan.getAktualniProstor().ziskejVec("Lano") != null) && plan.getAktualniProstor().ziskejVec("Lano").muzuZvednout() == false) {
                    return "Visíš na laně hlavou dolu a proto nemůžeš nic prohledávat. Nejdříve musíš přeříznout lano, pak budeš volný!";
                } else {
                    if (vecKPRohledani.jeProhledatelna()) {
                        if (batoh.velikostBatohu() < batoh.getObjemBatohu()) {
                            batoh.vlozVecDoBatohu(vecKPRohledani.vecZiskanaProhledanim());
                            vracenyText =  "Prohledal jsi věc |"+ nazevVeciKProhledani +"| a našel si v ní předmět |"+ vecKPRohledani.vecZiskanaProhledanim().getNazev() +"|, který byl přidán do batohu.";    
                        } else {
                            vracenyText = "Tvůj baťůžek pro přežití je plný!";
                        }
                    } else {
                        vracenyText =  "Tato věc se nedá prohledat!";                        
                    }
                }
            } else {
                vracenyText = "Můžeš prohledat pouze věci!";
            }
        }

        if (parametry.length > 1) { 
            vracenyText =  "Nemusíš zadávat tolik parametrů.";
        }                                                                         
        return vracenyText;
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
        return NAZEV;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
