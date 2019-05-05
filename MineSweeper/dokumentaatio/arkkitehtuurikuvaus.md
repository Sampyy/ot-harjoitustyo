## Arkkitehtuurikuvaus

### Rakenne
Ohjelman rakenne kerrosarkkitehtuurina on kaksikerroksinen, se sisältää pakkaukset minesweeper.domain sekä minesweeper.ui. Minesweeper.domain huolehtii sovelluslogiikasta, ja minesweeper.ui taas huolehtii sovelluksen käyttöliittymän elementeistä.

<img src="https://github.com/Sampyy/ot-harjoitustyo/tree/master/MineSweeper/dokumentaatio/kuvat/kerroskaavio.png", width="150">


### Käyttöliittymä 

Käyttöliittymässä on muutama eri elementti: 
- keskellä on itse pelikenttä. Tämä reagoi vasempaan ja oikeaan klikkaukseen.
- Vasemmalla on slideri, joka muuttaa ruudukon kokoa. Pienin vaikeustaso on 5x5 ruudukko, suurin on 50x50 ruudukko.
- Ylhäällä on slideri, joka muuttaa miinojen määrää ruudukossa. Pienimmässä määrässä 5% ruudukosta on miinoja, suurimmassa 50%
- Alhaalla on nappula "reset", joka aloittaa pelin alusta sliderien osoittamalla vaikeustasolla (sama kuin edellisessä pelissä mikäli niihin ei ole koskettu)

Jokaisen pelikentän klikkauksen jälkeen kutsutaan Tablen metodia Refresh, joka kutsuu JPanelin metodia "repaint" joka renderöi uudelleen pelikentän.


### Sovelluslogiikka

Sovelluslogiikasta huolehtii luokka [Table](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/src/main/java/minesweeper/domain/Table.java), joka käyttää luokan [Cell](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/src/main/java/minesweeper/domain/Cell.java) olioita taulukossa kuvaamaan pelin tilaa. 

[Table](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/src/main/java/minesweeper/domain/Table.java) sisältää metodit pelikentän tilan ja toimintojen tarkastelemiseen, kuten chooseCell(int x, int y), joka tarkistaa solun sekä tarvittaessa kutsuu metodia checkempty(int x, int y) tarkistamaan kaikki yhteydessä olevat tyhjät solut, tai asettaa pelin loppumaan mikäli on voitettu tai hävitty peli.

### Päätoiminnallisuudet

Esimerkkinä jonkin ruudun, joka sisältää numeron, klikkaus vasemmalla hiirenpainikkeella: <img src=https://github.com/Sampyy/ot-harjoitustyo/tree/master/MineSweeper/dokumentaatio/kuvat/sekvenssikaavio.png" width="502">

Actionsin mouseClicked metodi reagoi klikkaukseen, ja laskee ikkunan x ja y koordinaatteien sekä piirrettyjen solujen koon avulla mitä taulukon solua kyseinen solu tarkoittaa. Tämän jälkeen se kutsuu [Tablen](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/src/main/java/minesweeper/domain/Table.java) metodia chooseCell(). ChooseCell tarkastaa aluksi, onko kyseisessä ruudussa lippua. Mikäli on, ei se tee mitään. Nyt kuitenkin ruudussa ei ole lippua, joten taulukon solun checked arvo asetetaan trueksi. Pelikentältä piirretään ne solut, jotka on tarkistettu, ja nyt siis chooseCellin vielä kutsuessa Tablen metodia refresh(), piirretään taulukko uusiksi [Boardin](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/src/main/java/minesweeper/ui/Board.java) repaint metodia käyttäen.

Sovelluksen toiminnallisuudet toimivat tällä samalla periaatteella, jossa jokaisen pelikentän klikkauksen jälkeen kutsutaan refresh metodia, jotta pidetään käyttäjälle valmiina aina päivitettyä kuvaa pelikentästä. Lisäksi jokaisella vasemmalla klikkauksella tarkistetaan, mikäli klikkaus johtaa häviämiseen, tai pelin päättymiseen.




