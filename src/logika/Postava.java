/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * @author    Jakub Skála(skaj06)
 * @version   ZS 2016/2017
 */
public class Postava
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Postava(String nazev, String popis)
    {
        this.nazev = nazev;
        this.popis = popis;
        
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * @return String název postavy
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * @return String popis postavy
     */
    public String getPopis(){
        return popis;
    }
    
    public void setPopis(String popis){
        this.popis = popis;
    }
    

    //== Soukromé metody (instancí i třídy) ========================================

}
