# Architectuur
## Services
In een productieomgeving zullen we drie duidelijke services hebben [^1], namelijk:
- **web-ui** service
- **core** service
- **database** service

Door dit te splitsen van elkaar creëren we onder andere een betere schaalbaarheid.
 
[^1]: In realiteit zijn er meer services runnende in zowel de development als productieomgeving. Echter, deze zeggen weinig
over de architectuur van het project zelf; het zijn technische details.

### Web-ui service
Hier komen de _requests_ binnen om een bepaalde _view_ te regelen. Het communiceert enkel met de _core_ service om alle
domeinlogica te regelen.

De scheiding tussen _core_ en _web-ui_ zorgt voor een grote flexibiliteit. Hierdoor zou in de toekomst ook een andere
presentatie kunnen worden gemaakt. Denk bijv. aan een android app.

Deze service volgt een lagen architectuur:

#### Lagen
##### Presentatie
Traditionele web server.

De volgende patronen worden gebruikt:
###### Page Controller
Een patroon dat lijkt op het _MVC_-patroon. In theorie is er één module voor elk mogelijke pagina _request_. Deze module
pakt deze aanvraag dan zelfstandig aan [^3].

[^3]: In de praktijk is er soms een kleine overlap. Soms in het dynamisch welke pagina je voor je gaat krijgen.

###### Template View
Met de templating engine _thymeleaf_ kunnen we met markers (_thymeleaf syntax_) binnen een statische html-pagina aangeven
waar we gegevens uit het model dynamisch presenteren. Dit kan heel simplistisch maar ook redelijk complex.

Een groot voordeel van dit patroon is dat men tijdens het opstellen van de pagina heel duidelijk de structuur kan zien.
Het is makkelijker om pagina's op deze manier te implementeren voor meeste mensen.

Door de kracht van _thymeleaf_ kunnen we complexere patronen negeren (zoals een _Two Step View_) door gebruik te maken
van de ingebouwde _fragments_ en andere functionaliteiten.

##### Data Access
Communiceert met de core service.

#### Data Transfer Object
We gebruiken _dto_'s voor de aanroepingen naar de _core_ REST API efficiënt te maken. Zo wordt latency vermeden.

#### Technologieën
- Java 21 (Programmeertaal)
- Spring Boot (Framework)
- JUnit (Testing Framework)
- Maven (Build automation tool)
- Thymeleaf (Templating engine)
- HTML
- CSS
- Javascript

### Core service
Behandelt de domeinlogica. Het communiceert met de _database_ service. Deze service volgt een lagen architectuur:

#### Lagen
##### Presentatie Laag
Een REST API die communiceert met middel van entiteiten en dto's

##### Business Logica Laag
###### Service Laag
Over het algemeen zijn de _service_ _classes_ dunne _facade_'s. De meeste echte business logica wordt dus naar andere _classes_
gedelegeerd.

Het gebruik van een service laag zorgt voor een handige modulariteit. Er is daarnaast een duidelijke coördinatie hoe een
specifieke behoefte geregeld gaat worden.

##### Data Access Laag
###### Data Mapper
Via _annotations_ geven wij aan ons ORM door hoe de relationele wereld met de objectwereld moet _gemapt_ worden. Onze repositories
zorgen ervoor dat code uit van het framework deze met elkaar te mappen.

Het originele patroon gebruikt dit om SQL-logica te isoleren van de applicatie logica. In feite gebeurt dat bij ons ook,
alleen wordt de sql generatie gedelegeerd naar het ORM. In die zin betekent het meer: het scheiden van ORM-logica met 
applicatie logica.

Dit heeft als voordeel dat de entiteiten (_annotated_ _classes_) een onafhankelijke structuur kunnen hebben van de 
daadwerkelijke database implementatie. Zo kan de applicatie met data op een object-georiënteerde manier werken. Bij complexere
applicaties kunnen deze verschillen best groot worden. Daarom is het verstandig om dit patroon te gebruiken.

###### Layer Supertype
In theorie wordt er gewerkt met een repository superklasse [^2]. Zo wordt duplicatie vermeden.

[^2]: In de praktijk bestaan er nog repositories die zelfstandig werken. Echter is er de intentie om deze in de toekomst
te gaan verbinden.

#### Data Transfer Objects
We gebruiken _dto_'s om onze aanroepingen naar onze API efficiënt te maken. Zo wordt latency vermeden.

#### Technologieën
- Java 21 (Programmeertaal)
- Spring Boot (Framework)
- JUnit (Testing Framework)
- Hibernate (ORM framework)
- Maven (Build automation tool)


### Database service
De persistentie van het gehele systeem. 

#### Technologieën
- PostgreSQL (Relationele database)