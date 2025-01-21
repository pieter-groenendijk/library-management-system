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
- **Presentatie laag**: Traditionele web server
  - _Page Controller_ patroon. In theorie één module die een _request_ voor een pagina afhandelt.
  - _Template View_ patroon. We gebruiken templates met markers in combinatie met _thymeleaf_ om onze html te generen.
- **Data Access laag**: Communiceert met de core service.

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
- **Presentatie laag**: REST API communiceert door middel van entiteiten en dto's.
- **Business Logica Laag**: 
  - Service laag: Geïmplementeerd met dunne _facade_'s
- **Data Access Laag**: Communiceert met de database service.
  - _Data Mapper_ patroon wordt bij ons gedaan bij het ORM.
  - _Table Data Gateway_ om naar het ORM, stateless, te communiceren op _table_ niveau.
  - _Layer Supertype_. In theorie wordt er gewerkt met een repository superklasse. [^2]

[^2]: In de praktijk bestaan er nog repositories die zelfstandig werken. Echter is er de intentie om deze in de toekomst
te gaan verbinden.

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