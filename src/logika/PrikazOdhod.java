/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * @author    Jakub Skála (skaj06)
 * @version   ZS 2016/2017
 */
public class PrikazOdhod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "odhod";
    private HerniPlan plan;
    private Batoh batoh;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public PrikazOdhod(HerniPlan plan, Batoh batoh)
    {
        this.plan = plan;
        this.batoh = batoh;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     *  Metoda pro provedení příkazu "odhoď"
     *  
     *  Prohledá batoh, zda odebíraná věc je v něm.
     *  Pokud ano, věc bude odhozena do aktuálního prostoru.
     *  
     *  @param věc, která má být odhozena z batohu do aktuálního prostoru
     *  @return String vrácený text hráči
     */
    public String proved(String... parametry){
        String nazevOdhazovaneVeci = null; 
        String vracenyText = null;

        if(parametry.length == 0){        
            vracenyText = "Co chceš odhodit?";
        } 
        if (parametry.length == 1) {
            nazevOdhazovaneVeci = parametry[0];           
        }
        if (parametry.length == 2) {
            nazevOdhazovaneVeci = parametry[0] + " " + parametry[1];           
        }
        if (parametry.length == 3) {
            nazevOdhazovaneVeci = parametry[0] + " " + parametry[1] + " " + parametry[2];           
        }

        if(parametry.length != 0){   
            if (batoh.ziskejVec(nazevOdhazovaneVeci) instanceof Vec) {
                Prostor aktualni = plan.getAktualniProstor();        
                Vec odhazovanaVec = batoh.odeberVecZBatohu(nazevOdhazovaneVeci);
                aktualni.vlozVec(odhazovanaVec);
                vracenyText = "Ze svého batohu jsi odhodil věc | " + nazevOdhazovaneVeci + " | do prostoru | " + aktualni.getNazev() + " !";
            } else {
                vracenyText = "Můžeš odohodit pouze ty věci, které obsahují tvůj batoh.";
            }        
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
