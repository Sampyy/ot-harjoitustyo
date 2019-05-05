# Miinaharava

Sovellus on perinteinen miinaharava.
## [Release 0.1](https://github.com/Sampyy/ot-harjoitustyo/releases/tag/viikko5)

[Vaatimusmäärittely](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/dokumentaatio/vaatimusmaarittely.md)

[Käyttöohje](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/dokumentaatio/kayttoohjeet.md)

[Testausdokumentti](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/dokumentaatio/testausdokumentti.md)

[Tuntikirjanpito](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/dokumentaatio/tuntikirjanpito.md)

[Arkktehtuurikuvaus](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/dokumentaatio/arkkitehtuurikuvaus.md)



## Komentorivitoiminnot

### Testaus

JUnit testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti saa luotua komennolla 

```
mvn jacoco:report
```
Testikattavuusraportin löytää kohteesta target/site/jacoco/index.html 

```
mvn package
```

generoi hakemistoon target suoritettavan jar-tiedoston MineSweeper-1.0-SNAPSHOT.jar


JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```
javadoc löytyy reittiä target/site/apidocs  


Checkstyle raportin saa komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```
reportti löytyy kansiosta /target/site/checkstyle.html
