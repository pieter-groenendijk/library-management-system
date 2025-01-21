# Architectuur
## Services
In een productieomgeving zullen we drie duidelijke services hebben, namelijk:
- **web-ui** service
- **core** service
- **database** service

Door dit te splitsen van elkaar creëren we onder andere een betere schaalbaarheid.
 

### Web-ui service
Hier komen de _requests_ binnen om een bepaalde _view_ te regelen. Het communiceert enkel met de _core_ service om alle
domeinlogica te regelen.

De scheiding tussen _core_ en _web-ui_ zorgt voor een grote flexibiliteit. Hierdoor zou in de toekomst ook een andere
presentatie kunnen worden gemaakt. Denk bijv. aan een android app.

Deze service volgt een lagen architectuur:
- **Presentatie laag**: Traditionele web server
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

## Algemene tooling en infrastructuur
### Versiebeheer
Het project staat in zijn geheel (alle services) onder versiebeheer in één repo[^1]. Hiervoor wordt _git_ gebruikt.

Deze repo wordt gehost op [_github_](https://github.com/pieter-groenendijk/oose-library-management-system).

### Development Omgeving
#### Containerisatie
Met behulp van docker kunnen er een geïsoleerde omgevingen gemaakt worden om met consistentie de individuele services
uit te voeren.

Met behulp van docker kan er een geïsoleerde omgeving van elke individuele service aangemaakt worden op een consistente
manier, los van het besturingssysteem[^3].

#### Orchestratie
Docker compose zorgt ervoor dat de individuele containers gemaakt met docker als één eenheid gereproduceerd kan worden
met zijn eigen netwerk, configuratie, etc.

#### Scripting
Voor sommige doeleinden is er gebruik gemaakt van scripting. Hiervoor
is _bash_ gebruikt[^2]. Hierdoor is het gemakkelijk om met één knop in de IDE de workflow met de development omgeving
te automatiseren.

[^1]: Dit zou potentieel in de toekomst opgesplitst kunnen worden.  
[^2]: _Powershell_ zou respectievelijk een betere keuze kunnen zijn i.v.m. besturingsysteem _compatibility_.
[^3]: In de praktijk is dit niet helemaal waar. Er zijn nog steeds kleine discrepanties.