package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
    @Test
    public void saldoLuonnissaOikein()  {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaminenToimiiOikein() {
        kortti.lataaRahaa(90);
        
        assertEquals("saldo: 1.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimiiKunSaldoaOnRiittavasti(){
        assertEquals(true, kortti.otaRahaa(5));
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void rahanOttaminenEiMeneNegatiiviseksi(){
        assertEquals(false, kortti.otaRahaa(15));
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosRahatRiittavat() {
        assertEquals(true, kortti.otaRahaa(10));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosRahatEivatRiita()   {
        assertEquals(false, kortti.otaRahaa(15));
    }
    
    @Test
    public void saldonPalautusToimiiOikein(){
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaToimiiOikein()    {
        kortti.lataaRahaa(90);
        
        assertEquals(kortti.saldo(), 100);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
}
