# Belangrijke Design keuzes
## Entiteiten
Onze entiteiten hebben bewust geen domeinlogica. Wij behoren domeinlogica de verantwoordelijkheid van de _service_ laag.
Het enige gedrag op entiteiten zijn _getters_ en _setters_.

Data en gedrag is dus apart. Dit zorgt voor een grote flexibiliteit doordat de gegevens in verschillende contexten, met 
verschillend gedrag, kunnen worden gebruikt. Nadeel is dat duplicatie van code makkelijker ontstaat. Daar moet dus opgelet
worden.

### Alternatief: Active Record 
We hadden ook gebruik kunnen maken van een _Active Record_ structuur. 

Het voordeel hiervan is de verminderde complexiteit.
De data en zijn gedrag zit grotendeels bij elkaar. Het is eenmaal makkelijker te bouwen. 

Een nadeel ervan is dat, om je leven makkelijk te houden, de objectstructuur erg op de relationele structuur zal 
moeten lijken. Dit betekent dat de objectstructuur een hoge koppeling heeft aan de relationele structuur. Je kan moeilijk
één kant _refactoren_. 

Daarnaast zou door de complexiteit van onze domeinlogica de entiteiten extreem veel verantwoordelijkheid krijgen. Het
verliest dan een beetje aan het _Single Responsibility Principle_.

Het zal ook lastiger worden om te testen, de domeinlaag en data-access laag. Doordat de verantwoordelijkheden mengen, 
kun je ze moeilijk geïsoleerd testen.


## Inplanning van taken
Taken binnen ons systeem hebben vaak een tijdsgebonden natuur. Enkele voorbeelden:
- Sturen van een notificatie, na of voor een bepaalde gebeurtenis.
- Declareren van boetes, na een bepaalde gebeurtenis.

In het systeem draagt één module deze tijdsgebonden vereisten op zich, namelijk de _scheduling_ module. Een aparte 
module voor deze functionaliteit helpt ons op de volgende vlakken:
- **Voorkomt duplicatie** van code.
- **Hoge cohesie**, en daarmee **één doel**. **Volgt _single responsibility_ principe**

![](../../artifacts/class-diagrams/scheduling/scheduling-class-diagram.drawio.svg)

De module is zo gebouwd dat dit ook de andere letters van de _SOLID_ principes handhaaft, namelijk:
- **Open/Closed principe**:. Andere modules binnen het systeem zoals _events_ en _notifications_ breiden uit op de 
functionaliteit. Zonder een grote requirements verandering op de horizon kan ik met zekerheid zeggen dat de openbare API
van de code niet snel zou hoeven te veranderen.
- **Liskov's Substitution Principle**: De _classes_ uit andere modules implementeren en of breiden uit op de structuur. Daarmee
kunnen de afgeleide _classes_ gebruikt worden als de basis _classes_.
- **Interface Segregation Principle**: 
  - Interfaces zijn gedefinieerd met één doel.
  - Interfaces worden alleen geïmplementeerd als deze een daadwerkelijke implementatie gaat krijgen, geen stubs.
- **Dependency Inversion**: Klassen binnen de module zijn afhankelijk van definities zo laag mogelijk in de hiërarchie. 
In de praktijk betekent dit dat _classes_ afhankelijk zijn van abstracties, niet van concrete implementaties.
Zo ontstaat er geen artificiële koppeling.

### Alternatief: Duplicatie
Zowel de _notificatie_ als _boete_ implementeren hun eigen manier van taken inplannen. Hierdoor zijn ze meer ontkoppeld, 
en zouden ze dus compleet onafhankelijk gebouwd kunnen worden.

Nadeel is natuurlijk dat er duplicatie gaat optreden. Er is namelijk duidelijk terugkerende functionaliteit in de modules.
Vaak is de koppeling die ontstaat door de externe module juist wat je wilt. Daardoor zal een verandering in het algoritme
terugkomen automatisch in beide modules.

## Centralisatie door _events_
Zoals eerder besproken zijn er meerdere requirements die tijdsgebonden zijn. Deze momenten hebben vaak overlap tussen
de verschillende requirements. Denk bijv. aan een situatie waarbij het leentermijn van een product is verlopen, en dus
het product _te laat_ is. Zowel notificaties en boetes zitten gekoppeld aan dit moment.

Hieruit bleek dat er vraag is naar een aparte module voor het kunnen definiëren van deze momenten; _events_. 

![](../../artifacts/class-diagrams/events/events-class-diagram.drawio.svg)

Ook de _events_ structuur is in elkaar gezet met _SOLID_ in het achterhoofd.

De volgende events, gebaseerd op leningen, zijn voor nu bedacht:
![](../../artifacts/events/assets/loan-event-timeline.excalidraw.svg)

De events zorgen momenteel voor de volgende acties:
![](../../artifacts/events/assets/loan-event-actions.excalidraw.svg)

### Alternatief: Directe koppeling
Het is ook mogelijk om de _event_ stap over te slaan. Dan gebruiken we aanroepingen direct in de functie, i.p.v. dat we
een _event_ laten _emitten_.

Het voordeel hiervan is dat die gehele tussenstap van _events_ dus weggaat. Dat is een directe vermindering van
complexiteit. 

Toch denk ik niet dat dit het makkelijker maakt om mee te werken, van een developer's perspectief. Met 
_events_ hoeft de developer niet na te denken over hoe of wanneer de _event_ wordt _emit_, het maakt hem alleen uit
dat die wordt _emit_. Elk nieuwe functionaliteit die eigenlijk bij een _event_ zou horen, moet nu voor specifiek door de 
code gespookt worden om te kijken waar het zou passen.

Een ander voordeel is dat doordat er directe koppeling ontstaat error handling over een gehele situatie makkelijker wordt.
Er kan simpelweg een try/catch omheen gezet worden.

Een groot nadeel is de vermindering in flexibiliteit. Zoals de naam van dit alternatief noemt, er ontstaat directe 
koppeling. Bij _events_ is er sprake van een _middleman_. 