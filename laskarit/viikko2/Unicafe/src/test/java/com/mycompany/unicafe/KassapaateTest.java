/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author stuomela
 */
public class KassapaateTest {
    Kassapaate kassa;
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void kassapaatteenLuontiToimii() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoToimiiOikeinJosRahaaTarpeeksi(){
        
        assertEquals(60, kassa.syoEdullisesti(300));
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisOstollaMyydytEdullisetKasvavat(){
        kassa.syoEdullisesti(500);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoEiToimiJosRahaaEiRiittavasti(){
        assertEquals(50, kassa.syoEdullisesti(50));
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisOstoToimiiMaukkaassaOikeinJosRahaaTarpeeksi(){
        
        assertEquals(100, kassa.syoMaukkaasti(500));
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisOstollaMyydytMaukkaatKasvavat(){
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisOstoEiToimiMaukkaallaJosRahaaEiRiittavasti(){
        assertEquals(50, kassa.syoMaukkaasti(50));
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maksuKortillaToimiiJosRahaaRiittavasti(){
        Maksukortti kortti = new Maksukortti(500);
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void maksuKortillaLisaaLounaittenmaaraa(){
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maksukortinRahamaaraEiMuutuJosEiRahaa(){
        Maksukortti kortti = new Maksukortti(50);
        assertEquals(false, kassa.syoEdullisesti(kortti));
        assertEquals(50, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maksukortillaOstettaessaKassanRahaEiMuutu(){
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    @Test
    public void maksuKortillaToimiiMaukasJosRahaaRiittavasti(){
        Maksukortti kortti = new Maksukortti(500);
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maksuKortillaLisaaMaukkaittenmaaraa(){
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksukortinRahamaaraEiMuutuJosEiRahaaMaukkaaseen(){
        Maksukortti kortti = new Maksukortti(50);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
        assertEquals(50, kortti.saldo());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maksukortillaOstettaessaMaukasKassanRahaEiMuutu(){
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortinLataaminenToimii(){
        Maksukortti kortti= new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassa.kassassaRahaa());
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void negatiivinenLataaminenEiToimi(){
        Maksukortti kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, -5);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kortti.saldo());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
