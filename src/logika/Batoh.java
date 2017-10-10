/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Map;
import java.util.HashMap;


/*******************************************************************************
 * @author    Jakub Skála (skaj06)
 * @version   ZS 2016/2017
 */
public class Batoh
{
    private Map<String, Vec> batoh;
    private int objemBatohu;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Batoh ()
    {
        this.objemBatohu = 6;
        this.batoh = new HashMap<>(); 
    }
    
    /**
     *  Zobrazuje velikost batohu.
     *  
     *  @return int aktuální velikost (počet věcí v batohu) batohu
     */ 
    public int  velikostBatohu(){
        return batoh.size();
    }
    
    public int getObjemBatohu (){
        return this.objemBatohu;
    }
    
    /**
     *  Vkládá věc do batohu.
     *  
     *  @param název vkládané věci
     */     
    public void vlozVecDoBatohu(Vec neco){
        batoh.put(neco.getNazev(),neco);
    }        
    
    /**
     *  Odebírá věc z batohu.
     *  
     *  @param název odebírané věci
     *  @return Vec odebíraná věc
     */ 
    public Vec odeberVecZBatohu(String nazev){
        return batoh.remove(nazev);
    }
    
    /**
     *  Hledá věc v batohu.
     *  
     *  @param Vec hledaná věc
     *  @return true, pokud taková věc v batohu je.
     */
    public boolean hledejVec(Vec hledanaVec){ 
        boolean jeTam;
        jeTam = batoh.containsKey(hledanaVec);
        return jeTam;
    }        
    
    /**
     *  Vrátí věc z batohu
     *  
     *  @param String název věci k vrácení
     *  @return věc, která se má vrátit
     */
    public Vec ziskejVec(String nazevVeci){   
        Vec hledanaVec = null;
        for (String nazev : batoh.keySet()) {
            hledanaVec = batoh.get(nazevVeci);         
        }          
        return hledanaVec;
    }
    
    /**
     *  Zobrazí obsah batohu.
     *  
     *  @return String text obsahu
     */
    public String obsahBatohu(){
        String vracenyText = null;
        if (batoh.isEmpty()) {
            vracenyText = "Tvůj baťůžek pro přežití je prázdný!";
        } else {
            vracenyText = "Tvůj baťůžek obsahuje následující předměty: | ";
            for (String nazev : batoh.keySet()) {                
                vracenyText += nazev + " | ";            
            }
        }
        return vracenyText;
    }


    //== ABSTRAKTNÍ METODY =====================================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================
    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== TESTOVACÍ METODY A TŘÍDY ==============================================
    //
    //     /********************************************************************
    //      * Testovací metoda.
    //      */
    //     public static void test()
    //     {
    //         Batoh instance = new Batoh();
    //     }
    //     /** @param args Parametry příkazového řádku - nepoužívané. */
    //     public static void main(String[] args)  {  test();  }
}
