# Scheduling Design Patterns

![](../../../artifacts/class-diagrams/scheduling/scheduling-class-diagram.drawio.svg)

## Template Method Pattern
### Waar?
`LongTermTaskScheduler` i.v.m. `EventScheduler` en `NotificationScheduler`.

### Waarom?
Deze techniek voorkomt gedupliceerde code door statisch een strategie te kiezen met behulp van een subklasse. Zo kan het
_grotere_ algoritme in de superklasse uitgevoerd waarbij een subklasse specifieke stappen in het algoritme kan beïnvloeden.
In dit voorbeeld geldt dat dan voor de methode `executeTask()`.

## Adapter Pattern
### Waar?
`TaskScheduler` i.v.m. `ScheduledExecutorService`.

### Waarom?
Er zou standaard gebruikt kunnen worden van de `ScheduledExecutorService`, echter zouden dan voor alle inplanningen
relatieve tijden gebruikt moeten worden. We willen graag de ingeplande tijden van onze taken opslaan in de database. Wij
vinden dat relatieve tijden dat verwarrend zouden maken. In plaats daarvan willen we absolute tijden in onze database en
applicatie zelf gebruiken. Daarom is de `TaskScheduler` aangemaakt. Nu kan er gebruik worden gemaakt van de ingebouwde
`ScheduledExecutorService`, maar dan op een manier dat ons daadwerkelijk dient.

## Strategy Pattern
### Waar?
`TaskStorage` i.v.m. dynamische functionele interface implementaties.

### Waarom?
Op deze manier kan er een strategie gekozen voor om voor type `T` een strategie te definiëren om de `task` op te slaan
om te gebruiken tijdens _runtime_. Dit ziet er niet uit als een traditioneel _strategy_ pattern. Dit komt doordat de 
daadwerkelijke implementaties van de strategies lijken te ontbreken. De strategie wordt dynamisch geselecteerd uit
een al bestaande _repository_ tijdens het generen van de `task`. Namelijk een reference naar een functie die de functionele
interface implementeert.