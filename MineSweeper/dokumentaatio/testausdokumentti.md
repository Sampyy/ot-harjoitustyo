# Testausdokumentti

### Yksikkötestaus

Sovelluslogiikkaa on testattu automatisoiduilla JUnit-testeillä. Testit ovat [Table](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/src/main/java/minesweeper/domain/Table.java) luokasta [TableTest](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/src/test/java/minesweeper/domain/TableTest.java). Cell luokkaa en katsonut tarpeelliseksi kirjoittaa testejä, koska se sisältää vain yksinkertaisia gettereitä ja settereitä.

#### Testauskattavuus

Sovelluslogiikan testauksen rivikattavuus on 98% ja haarautumakattavuus 94%.

![](https://raw.githubusercontent.com/Sampyy/ot-harjoitustyo/master/MineSweeper/dokumentaatio/kuvat/testikattavuus.png)


Haarautumakattavuudesta jää tarkistamatta että chooseCell kutsun yhteydessä tarkistetaan, onko voitettu tai hävitty. Kuitenkin voiton toiminta on tarkistettu toisessa testissä, ja häviön toiminta on tarkistettu sadoilla häviöillä.

### Järjestelmätestaus

Sovellus on manuaalisesti todettu toimivaksi käyttöohjeen mukaan ainakin windowsilla, sekä linuxilla.