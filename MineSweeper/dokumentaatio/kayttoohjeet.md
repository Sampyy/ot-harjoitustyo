# Käyttöohje

Lataa tiedosto [Release](https://github.com/Sampyy/ot-harjoitustyo/releases/tag/1.0)

Pura pakatun tiedoston jar tiedosto minefield sekä kansio resources samaan paikkaan. Minefield toimii vain, mikäli samassa hakemistossa on kuvat sisältävä resources tiedosto.

### Vaatimukset

Ohjelma olettaa, että sen käynnistyshakemistossa on kansio "Resources", joka sisältää miinaharavan ruutujen kuvat.
Ohjelma käynnistetään komennolla
...
java -jar minesweeper.jar
...

### Pelaaminen

Peli alkaa oletuksena 10x10 kokoisesta ruudukosta, jossa on 10 miinaa. Pelin vaikeutta voi säätää käyttämällä vasemmalla ja ylhäällä olevia slidereita.

Vasemmalla oleva slideri kasvattaa pelikentän kokoa välillä 5-50 ruutua. Ylhäällä oleva slideri taas lisää miinojen määrää pelikentällä, välillä 5-50% kentästä. Reset nappulalla saa resetoitua kentän, sekä slidereilla tehdyt muutokset voimaan.

Pelin tavoitteena on saada kaikki miinat merkattua. Miinoja merkataan oikealla klikkauksella, ja ruutuja "avataan" vasemmalla klikkauksella. Mikäli avaa miinan, häviää pelin. Peli päättyy kun täsmälleen kaikki miinat ovat merkittynä. Mikäli tarpeettomia miinoja on merkittynä, ei peli pääty ennen kuin tarpeettomat merkit on poistettu.