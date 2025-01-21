# Verbeteringen mogelijk op het ontwerp

## Producten <br>
#### Geimplementeerd: Template Pattern <br>
Momenteel worden producten gemaakt met behulp van het Template Design Pattern. <br>
Er is een superclass ProductTemplate, die wordt uitgebreid door twee subclasses: PhysicalProductTemplate en DigitalProductTemplate. <br>
De implementaties van de producten zelf, zoals PhysicalProduct en DigitalProduct, volgen deze templates. <br>

#### Verbetering: Strategy Pattern <br>
Een alternatief voor dit ontwerp zou het gebruik van het Strategy Pattern kunnen zijn, waarbij de verschillende producttypes worden behandeld door verschillende strategieën voor de implementatie, in plaats van via de inheritance van templates. <br>
Dit zou meer flexibiliteit bieden bij het wisselen van strategieën zonder de noodzaak van subclassing. <br>
Product (model): De hoofdklasse die gebruik maakt van een strategie om het product te creëren of te behandelen. Dit zou een verwijzing bevatten naar de ProductStrategy. <br>

ProductStrategy (interface): Een interface die de verschillende strategieën definieert voor het behandelen van producten. Elke strategie zou de specifieke implementatie bevatten voor de creatie of afhandeling van een product. <br>

PhysicalProductStrategy (concrete strategie): Een implementatie van de ProductStrategy interface, die de logica voor fysieke producten bevat. <br>

DigitalProductStrategy (concrete strategie): Een implementatie van de ProductStrategy interface, die de logica voor digitale producten bevat. <br>


## Lening en Reservering <br>
#### Geimplementeerd: Géén design pattern <br>
Leningen en Reserveringen zijn allebij een eigen superclass. Er wordt gebruik gemaakt van inheritance om de verschillende types van leningen en reserveringen te behandelen. <br>

#### Verbetering: Strategy Pattern <br>
Een alternatief voor het huidige ontwerp zou het gebruik van het Strategy Pattern kunnen zijn, waarbij de verschillende lening- en reserveringstypes worden behandeld door verschillende strategieën voor de implementatie, in plaats van via de inheritance van factories. <br>
Dit zou meer flexibiliteit bieden bij het wisselen van strategieën zonder de noodzaak van subclassing. <br> 

TransactieStrategy (context): De hoofdklassen die de gezamenlijke eigenschappen en logica voor leningen en reserveringen bevat. <br>
LeningStrategy of ReserveringStrategy om de transactie te creëren of te behandelen. Dit zou een verwijzing bevatten naar de TransactieStrategy. <br>
Lening (concrete strategie): Een implementatie van de Transactie interface, die de logica voor leningen bevat. <br>
Reservering (concrete strategie): Een implementatie van de Transactie interface, die de logica voor reserveringen bevat. <br>


## Notificaties i.v.m. bijv. Leningen
### Huidige Implementatie
Mogelijk zijn veel notificaties gekoppeld aan de staat waarin een lening[^1] zich in bevindt. Zo wordt er 
bijv. een notificatie gestuurd wanneer het leentermijn van een lening voorbij is. Binnen onze applicatie beschouwen
we een geleend product als een lening; ze zijn hetzelfde.

Het nadeel hiervan is dus dat wanneer men 5 producten in 1 keer leent, deze 5 aparte notificaties krijgt (per product) dat
het leentermijn van dat product over is.

Dit is voornamelijk een _ux_ probleem. Het is niet fijn voor een gebruiker om zo informatie dubbelop te ontvangen. Toont
geen respect naar de gebruiker zijn tijd en aandacht.

Naast dit is het ook te benoemen dat het niet efficiënt klinkt voor de server om dit per geleend product uit te voeren.

[^1]: In dit hoofdstuk wordt een lening als voorbeeld gebruikt als bron van notificaties. In werkelijkheid is dit
niet het enige entiteit dat dit probleem vertoont.

### Mogelijke Verbeteringen
#### 1. Bundelen van notificaties
Op de back-end worden leningen nog steeds als een individueel geleend product gezien. De verandering is hoe we er met
de presentatie laag mee omgaan. De presentatielaag zou dan de gestuurde notificaties kunnen bundelen in één notificatie.

##### Voordelen
- Notificaties worden nuttiger en concreter
- Back-end blijft hetzelfde. Er zal hier geen werk verricht moeten worden.

##### Nadelen
- Dit geldt enkel voor de presentatie van notificaties binnen ons eigen platform. _Third party_ presentatie hebben wij weinig
of minder invloed op. Denk aan bijv. aan mail en sms.
- Vrij _magicky_ om te implementeren. Wanneer een notificatie eenmaal gegeneerd is wordt het moeilijker om deze dynamisch
te matchen en te bundelen aangezien de beschikbare informatie voornamelijk in _plain text_ is.

#### 2. Verandering definitie lening 
We veranderen de definitie van een lening. Een lening zou dan een collectie van geleende producten kunnen zijn. Dit nieuwe
entiteit kan dan de bron zijn van de notificaties. Dit sluit ook niet uit dat je een notificatie kan krijgen voor één van
de producten uit de lening. 

##### Voordelen
- Notificaties kunnen zowel gebaseerd zijn op een lening, als een individueel geleend product daaruit.

##### Nadelen
- Voegt complexiteit toe. 
- Modificatie van _core_ functionaliteit. Hoewel grotendeels geminimaliseerd zal er nog steeds een kleine waterval van
aanpassingen komen.


## Atomaire Acties
### Huidige Implementatie
Momenteel is er matige, inefficiënte en onduidelijke transactie controle. Dat brengt data risico's met zich mee. De controle
die er momenteel is, is op data access niveau, namelijk bij de _repositories_. Dit is zeker niet genoeg, en in veel situaties
zelfs nutteloos.

### Mogelijke Verbeteringen
#### 1. Nested Transacties
Één mogelijke oplossing is door gebruik te maken van _nested transactions_, of realistischer: _savepoints_.

##### Voordelen
- De huidige implementatie van transactie controle op repository niveau kan behouden worden. Daarmee wordt er ook relatief
weinig complexiteit toegevoegd aan de simpele _crud_ acties. 

##### Nadelen
- De inefficiënte methode in de repositories wordt behouden. Graag ruimen we dit natuurlijk op.

#### 2. Verantwoordelijkheid verplaatsen naar service laag
We kunnen de verantwoordelijkheid van transactie controle verplaatsen naar de service laag, en natuurlijk deze code 
verbeteren. Sinds we Spring Boot gebruiken zouden we zelfs op een declaratieve manier dit aanpakken via _annotations_.

##### Voordelen
- Complexiteit vermindert. 
- Efficiënte en duidelijke transactie controle. 

##### Nadelen
- Modificatie op relatief veel punten. Hoewel het op service niveau, dus vrij veilig zou moeten kunnen gebeuren.


## Exclusiviteit Producten
### Huidige Implementatie
Momenteel is een product of een fysiek product of een digitaal product. Echter, terugkijkend, volgt dit compleet het 
domein. Een verfilming van een boek is zomaar een voorbeeld. Beide zijn gebaseerd op hetzelfde product.

In sommige situaties zou men misschien een product willen inzien binnen de catalogus, het is dan fijn dat het niet lijkt
alsof er dubbele _entries_ zijn. Het lijkt mij persoonlijk handiger om één pagina te kunnen presenteren voor zowel de 
verfilming als boek ervan. Er kunnen ook situaties bedacht worden waar die preferentie misschien verandert. Daarom zou
hier iets meer onderzoek naar gedaan moeten worden.

### Mogelijke Verbeteringen
#### 1. Exclusiviteit weghalen
Wanneer we de exclusiviteit uit het design halen is het snel opgelost.

##### Voordelen
- Relatief weinig code hoeft te veranderen
- De presentatielaag kan verschillende _views_ hebben van de data. Verfilming en boek op één pagina of juist niet.
- De presentatielaag wordt meer uitbreidbaar

##### Nadelen
- De presentatielaag moet expliciet definiëren hoe ze met zulke producten omgaan. _UI_ en _UX_ complexiteit neemt toe.


#### 2. Bundelen
We zouden deze producten kunnen proberen op te sporen in de presentatielaag, en daar dan de keuze te maken hoe te presenteren.

##### Voordelen
- Core functionaliteit blijft hetzelfde.

##### Nadelen
- Voegt veel complexiteit toe aan de presentatielaag.
- Vrij _magicky_. Het wordt een uitdaging om algoritmisch te detecteren voor welke producten dit geldt.


## Event types
### Huidige implementatie
Momenteel is het type van een _event_ aangegeven in de database en applicatie via een enum _EventType_. Het nadeel is dus
dat elk type hierin zal staan.