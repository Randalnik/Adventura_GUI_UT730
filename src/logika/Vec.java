package logika;

import javafx.scene.image.Image;

/*******************************************************************************
 * @author    Jakub Skála (skaj06)
 * @version   ZS 2016/2017
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;    
    private Prostor prostorPouziti;
    private boolean jeCitelna;
    private boolean muzuZvednout;
    private boolean jePouzitelna;
    private boolean jeProhledatelna;
    private Vec vecPouziti;
    private Vec vecZiskanaProhledanim;
    private Vec vecZiskanaPouzitim;    
    private Image img;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, Prostor prostorPouziti, boolean jeCitelna, boolean muzuZvednout, boolean jePouzitelna, boolean jeProhledatelna, Vec vecPouziti, Vec vecZiskanaProhledanim, Vec vecZiskanaPouzitim, String imgName)
    {
        this.nazev = nazev;
        this.prostorPouziti = prostorPouziti;
        this.jeCitelna = jeCitelna;
        this.muzuZvednout = muzuZvednout;
        this.jePouzitelna = jePouzitelna;
        this.jeProhledatelna = jeProhledatelna;
        this.vecPouziti = vecPouziti;
        this.vecZiskanaProhledanim = vecZiskanaProhledanim;
        this.vecZiskanaPouzitim = vecZiskanaPouzitim;    
        this.img = new Image(Vec.class.getResourceAsStream("/zdroje/" + imgName), 50, 50, false, false);
    
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * @return String název
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * @return String prostor pro použití
     */
    public Prostor prostorPouziti(){
        return prostorPouziti;
    }
    
    /**
     * @return true/false pro čitelnost
     */
    public boolean jeCitelna(){
        return jeCitelna;
    }
    
    /**
     * @return true/false pro možnost předmět zvednout
     */
    public boolean muzuZvednout(){
        return muzuZvednout;
    }
    
    /**
     * @return true/false pro možnost použití věci
     */
    public boolean jePouzitelna(){
        return jePouzitelna;
    }
    
    /**
     * @return true/false pro možnost prohledat věc
     */
    public boolean jeProhledatelna(){
        return jeProhledatelna;
    }
    
    /**
     * @return vrátí věc použití
     */
    public Vec vecPouziti(){
        return vecPouziti;
    }
    
    /**
     * @return vrátí získanou věc prohledáním
     */
    public Vec vecZiskanaProhledanim() {
        return vecZiskanaProhledanim;
    }
    
    /**
     * @return vrátí věc získanou použitím
     */
    public Vec vecZiskanaPouzitim() {
        return vecZiskanaPouzitim;
    }  
    
    public void setJeCitelna(boolean jeCitelna) {
        this.jeCitelna = jeCitelna;
    }
    
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
    
    public void setProstorPouziti(Prostor prostorPouziti) {
        this.prostorPouziti = prostorPouziti;
    }
    
    public void setMuzuZvednout(boolean muzuZvednout) {
        this.muzuZvednout = muzuZvednout;
    }
    
    public void setJePouzitelna(boolean jePouzitelna) {
        this.jePouzitelna = jePouzitelna;
    }
    
    public void setJeProhledatelna(boolean jeProhledatelna) {
        this.jeProhledatelna = jeProhledatelna;
    }
    
    public void setVecPouziti(Vec vecPouziti) {
        this.vecPouziti = vecPouziti;
    }
    
    public void setVecZiskanaProhledanim(Vec vecZiskanaProhledanim) {
        this.vecZiskanaProhledanim = vecZiskanaProhledanim;
    }
    
    public void setVecZiskanaPouzitim(Vec vecZiskanaPouzitim) {
        this.vecZiskanaPouzitim = vecZiskanaPouzitim;
    }
        public Image getImg() {
        return img;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}