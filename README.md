# Miinaharava

Sovellus on perinteinen miinaharava.
## [Release 0.1](https://github.com/Sampyy/ot-harjoitustyo/releases/tag/viikko5)

[Vaatimusmäärittely](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/dokumentaatio/vaatimusmaarittely.md)

[Käyttöohje](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/dokumentaatio/kayttoohjeet.md)

[Tuntikirjanpito](https://github.com/Sampyy/ot-harjoitustyo/blob/master/MineSweeper/dokumentaatio/tuntikirjanpito.md)



## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _MineSweeper-1.0-SNAPSHOT.jar_


JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

Checkstyle raportni saa komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

