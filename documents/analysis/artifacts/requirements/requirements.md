# Requirements

## Actoren
| Gebruikerstype  | Beschrijving                                                                           |
|:----------------|:---------------------------------------------------------------------------------------|
| Bezoeker        | Een bezoeker van de website zonder abonnement.                                         |
| Lid             | Een klant van de bibliotheek. Komt in de vorm van verschillende abonnementen.          |
| Editor          | Content beheerder. Zorgt dat het domein blijft kloppen.                                |
| Baliemedewerker | Bedient een balie binnen de bibliotheek. Fysiek aanspreekpunt voor leden en bezoekers. |
| Beheerder       | Beheerder van de bibliotheek                                                           |

## Requirements
### Abonnement

| Actor                                         |                                                                | Prioriteit | Code   | Implementatie |
|:----------------------------------------------|:---------------------------------------------------------------|------------|--------|---------------|
| Als **bezoeker** in bezit van een abonnement, | wil ik kunnen inloggen. Hierna val ik in de rol _lid_.         | M          | FR-002 | Nee           |
| Als **bezoeker**,                             | wil ik een _volwassen abonnement_ kunnen aanschaffen.          | M          | FR-003 | Ja            |
| Als **bezoeker**,                             | wil ik een _selectief digitaal abonnement_ kunnen aanschaffen. | S          | FR-004 | Ja            |
| Als **bezoeker** met een kind,                | wil ik een _kinder abonnement_ kunnen aanschaffen.             | C          | FR-001 | Nee           |
| Als **lid**,                                  | wil ik mijn abonnement kunnen stopzetten.                      | C          | FR-063 | Nee           |
### Catalogus

| Actor                 |                                                                                                                                      | Prioriteit | Code   | Implementatie |
|:----------------------|:-------------------------------------------------------------------------------------------------------------------------------------|------------|--------|---------------|
| Als **bezoeker**,[^2] | wil ik de catalogus kunnen doorzoeken, door eventueel te filteren op basis van: titel, auteur/maker, beschikbaarheid en producttype. | M          | FR-005 | Ja            |
| Als **bezoeker**,[^2] | wil ik informatie kunnen inzien van een specifiek product.                                                                           | M          | FR-006 | Ja            |
| Als **editor**,       | wil ik een product kunnen uitlichten.                                                                                                | S          | FR-007 | Nee           |
| Als **editor**,       | wil ik een product kunnen toevoegen.                                                                                                 | S          | FR-008 | Nee           |
| Als **editor**,       | wil ik een product zijn gegevens kunnen aanpassen.                                                                                   | S          | FR-009 | Nee           |
| Als **editor**,       | wil ik een product kunnen verwijderen.                                                                                               | S          | FR-012 | Nee           |
| Als **bezoeker/lid**, | wil ik kunnen inzien waar een fysiek product staat binnen de bibliotheek                                                             | C          | FR-045 | Ja            |

[^2]: Alle andere rollen kunnen dit dus automatisch ook.
[^5]: Men belandt op de catalogus pagina. Hier ziet men dan een sectie met de uitgelichte producten.

### Accountbeheer

| Actor                                           |                                                                                             | Prioriteit | Code   | Implementatie |
|:------------------------------------------------|:--------------------------------------------------------------------------------------------|------------|--------|---------------|
| Als **lid**,                                    | wil ik kunnen uitloggen. Hierna val ik in de rol _bezoeker_.                                | M          | FR-013 | Nee           |
| Als **lid**[^1],                                | wil ik de leenhistorie (per abonnement) kunnen inzien.                                      | S          | FR-014 | Ja            |
| Als **beheerder** of **baliemedewerker**,       | wil ik een lid kunnen toevoegen.                                                            | S          | FR-020 | Nee           |
| Als **baliemedeweker**,                         | wil ik een overzicht van een account van een lid (d.m.v. een bibliotheekpas) kunnen inzien. | C          | FR-051 | Ja            |
| Als **beheerder** of **baliemedewerker**,       | wil ik een lid zijn gegevens kunnen aanpassen.                                              | S          | FR-021 | Ja            |
| Als **beheerder** of **baliemedewerker**,       | wil ik een lid kunnen verwijderen.                                                          | S          | FR-022 | Ja            |
| Als **beheerder**,                              | wil ik een lid zijn account kunnen blokkeren. Het lid kan niet meer bij producten.          | S          | FR-023 | Ja            |
| Als **lid** met een gekoppeld kinderabonnement, | wil ik de leenhistorie van mijn kind kunnen inzien.                                         | C          | FR-019 | Nee           |
| Als **beheerder** of **baliemedewerker**,       | wil ik een abonnement kunnen toevoegen aan een account van een lid.                         | C          | FR-060 | Ja            |
| Als **beheerder** of **baliemedewerker**,       | wil ik een abonnement kunnen verwijderen aan een account van een lid.                       | C          | FR-061 | Ja            |
| Als **lid**,                                    | wil ik mijn gegevens kunnen aanpassen.                                                      | C          | FR-062 | Nee           |

[^1]: De leenhistorie is per abonnement. Een lid kan meerdere abonnementen hebben.

### Lenen

| Actor                    |                                                                        | Prioriteit | Code   | Implementatie |
|:-------------------------|:-----------------------------------------------------------------------|------------|--------|---------------|
| Als **lid**,[^1]         | wil ik een fysiek boek kunnen lenen.                                   | M          | FR-024 | Ja            |
| Als **lid**,[^1]         | wil ik een digitaal boek (ebook) kunnen lenen.                         | M          | FR-025 | Ja            |
| Als **lid**,[^1]         | wil ik een fysiek tijdschrift kunnen lenen.                            | M          | FR-026 | Ja            |
| Als **lid**,[^1]         | wil ik een digitaal tijdschrift kunnen lenen.                          | M          | FR-027 | Ja            |
| Als **lid**,             | wil ik _on demand_ een film kunnen bekijken.                           | S          | FR-028 | Nee           |
| Als **lid**,             | wil ik _on demand_ een audioboek kunnen beluisteren.                   | S          | FR-029 | Nee           |
| Als **lid**,[^1]         | wil ik het leentermijn van een geleend fysiek product kunnen verlengen | S          | FR-044 | Ja            |
| Als **beheerder**,       | wil ik het leentermijn van de fysieke producten kunnen aanpassen       | C          | FR-046 | Nee           |
| Als **baliemedewerker**, | wil ik de fysieke product uitleningen kunnen beheren. (CRUD)           | C          | FR-054 | Nee           |

[^1]: Als lid zelf of met behulp van de baliemedewerker.
[^4]: Een lid kan zijn uitlening (oneindige keren) verlengen zolang dit product niet gereserveerd wordt.

### Reserveren

| Actor                    |                                                                                                                       | Prioriteit | Code   | Implementatie |
|:-------------------------|:----------------------------------------------------------------------------------------------------------------------|------------|--------|---------------|
| Als **lid**,             | wil ik een momenteel uitgeleend fysiek product kunnen reserveren indien deze nog niet door een ander is gereserveerd. | M          | FR-030 | Ja            |
| Als **beheerder**,       | wil ik het reserveertermijn van de fysieke producten kunnen aanpassen                                                 | C          | FR-047 | Nee           |
| Als **baliemedewerker**, | wil ik de fysieke product reservaties van een lid kunnen beheren (CRUD)                                               | C          | FR-053 | Nee           |

### Boetes

| Actor                    |                                                                                                             | Prioriteit | Code   | Implementatie |
|:-------------------------|:------------------------------------------------------------------------------------------------------------|------------|--------|---------------|
| Als **lid**,             | ben ik een opbouwende boete schuldig wanneer ik een geleend product niet binnen het leentermijn inlever.    | M          | FR-031 | Ja            |
| Als **lid**,             | verlies ik toegang van het gehele productaanbod wanneer mijn schuld een vastgesteld bedrag bereikt.         | M          | FR-033 | Ja            |
| Als **lid**,             | wil ik mijn schuld kunnen betalen                                                                           | M          | FR-034 | Ja            |
| Als **lid**,             | ben ik een boete schuldig wanneer ik een vastgesteld aantal in serie niet gebruik maak van een reservering. | S          | FR-032 | Ja            |
| Als **beheerder**,       | wil ik het boete bedrag van FR-032 kunnen aanpassen.                                                        | C          | FR-049 | Nee           |
| Als **beheerder**,       | wil ik het boete bedrag per dag van FR-031 kunnen aanpassen.                                                | C          | FR-050 | Nee           |
| Als **baliemedewerker**, | wil ik een lid zijn schuld laten betalen.                                                                   | C          | FR-052 | Ja            |

### Limieten

| Actor              |                                                                                                                  | Prioriteit | Code   | Implementatie |
|:-------------------|:-----------------------------------------------------------------------------------------------------------------|------------|--------|---------------|
| Als **lid**,       | geldt er voor mij een limiet (wat afhankelijk is van abonnement) voor geleende fysieke producten.                | M          | FR-015 | Ja            |
| Als **lid**,       | geldt er voor mij een limiet (wat afhankelijk is van abonnement) voor gereserveerde fysieke producten.           | M          | FR-017 | Ja            |
| Als **lid**,       | geldt er voor mij een limiet voor geleende fysieke producten uit een categorie (bijv. Harry Potter).[^3]         | S          | FR-016 | Ja (Genre)    |
| Als **lid**,       | geldt er voor mij een limiet voor gereserveerde fysieke producten vanuit een categorie (bijv. Harry Potter).[^3] | S          | FR-018 | Ja (Genre)    |
| Als **beheerder**, | wil ik het geleende fysieke producten limiet kunnen aanpassen.                                                   | S          | FR-035 | Ja            |
| Als **beheerder**, | wil ik het geleende fysieke producten uit een categorie limiet kunnen aanpassen.                                 | S          | FR-036 | Nee           |
| Als **beheerder**, | wil ik het gereserveerde fysieke producten limiet kunnen aanpassen.                                              | S          | FR-037 | Nee           |
| Als **beheerder**, | wil ik het gereserveerde fysieke producten uit een categorie limiet kunnen aanpassen.                            | S          | FR-038 | Nee           |

[^3]: Wanneer er meerdere sprake is van meerdere limieten tegelijkertijd geldt het laagste.

### Notificaties

| Actor        |                                                                                                                                                     | Prioriteit | Code   | Implementatie |
|:-------------|:----------------------------------------------------------------------------------------------------------------------------------------------------|------------|--------|---------------|
| Als **lid**, | wil ik een notificatie ontvangen wanneer het leentermijn van een geleend product bijna verloopt.                                                    | M          | FR-039 | Ja            |
| Als **lid**, | wil ik een notificatie ontvangen wanneer het leentermijn van een geleend product verlopen is.                                                       | M          | FR-040 | Ja            |
| Als **lid**, | wil ik een notificatie ontvangen wanneer mijn totale boete (schuld) opbouwt tot bepaalde bedragen.                                                  | S          | FR-042 | Ja            |
| Als **lid**, | wil ik een notificatie ontvangen wanneer ik niet gebruik heb gemaakt van een reservering. Ik wordt geïnformeerd van een mogelijk toekomstige boete. | S          | FR-044 | Nee           |
| Als **lid**, | wil ik wekelijks een notificatie ontvangen wanneer ik een _te laat_ boete schuldig ben van een geleend product.                                     | C          | FR-041 | Ja            |
| Als **lid**, | wil ik een notificatie ontvangen wanneer een gereserveerd product beschikbaar is geworden om te lenen                                               | C          | FR-043 | Nee           |

### Elders

| Actor              |                                                          | Prioriteit | Code   | Implementatie |
|:-------------------|:---------------------------------------------------------|------------|--------|---------------|
| Als **beheerder**, | wil ik kunnen inzien welke instellingen ik kan aanpassen | M          | FR-048 | Nee           |

## Definities
### Kinderabonnement
Dit abonnement is exclusief beschikbaar om aan te schaffen voor bezoekers niet ouder dan 16 jaar.

Dit abonnement geeft alleen toegang tot de kinderproducten.

Een account kan alleen bestaan voor een volwassene. Het kind abonnement kan dan aangeschaft worden binnen het account
van de volwassen die eindverantwoordelijk is.

### Selectief digitaal abonnement
Dit abonnement is goedkoper dan een _normaal_ volwassen abonnement. 

Dit abonnement geeft alleen toegang tot digitale producten.
