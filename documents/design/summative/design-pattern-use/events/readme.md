# Event design patterns

![](../../../artifacts/class-diagrams/events/events-class-diagram.drawio.svg)

## Observer Pattern
### Waar?
Terug te zien bij de `EventEmitter` _class_ en
de _interface_ `IEventListener`. 

### Waarom?
Door middel van dit patroon kunnen we gemakkelijk klassen laten reageren op veranderingen, in dit geval _events_. Als er
eer bepaalde gebeurtenis gebeurt kan daar automatisch een boete gedeclareerd worden, of een notificatie gestuurd worden.

Het voordeel van het gebruik van de _observer_ pattern hier is dat enkel en alleen de aanhangende wordt uitgevoerd wanneer
de bron aangeeft dat het is gebeurd. Er is dus geen constante _polling_ of conditionele statements nodig. 

Daarnaast kan men gemakkelijk nieuwe `IEventListener`'s aanmaken, en zo de functionaliteit van het systeem uit te breiden;
_Open/Closed_.


## Template Method Pattern
### Waar?
`DetachedLoanEventGenerator` i.v.m. `DetachedEventGenerator`.

### Waarom?
We willen onze entiteiten schoon houden. Voor de entiteiten zelf wordt er alleen onderscheid gemaakt tussen events op
basis van hun associatie; aan welk ander entiteit zit dit event gekoppeld; wat is de bron van deze event. Voor deze reden
wordt er _generators_ gebruikt om de daadwerkelijke verschillende type events aan te maken. 

Er zijn enkele verschillende stappen van het generen van een event. Deze stappen kunnen afwijken op basis van de 
groepering van het type dat aangemaakt moet worden. Zo'n groepering hangt af van de associatie; `LoanEvent` bijv. 
Al op basis van de groepering kan een stap al geabstraheerd worden.

Deze techniek voorkomt gedupliceerde code, door middel van een _superclass_. Dat is exact wat we willen, want in deze 
usecase zijn enkele stappen anders, niet allemaal.

In tegendeel tot de _strategy_ pattern kan zo _static_ verschillende strategieën bedacht worden voor een algoritme.