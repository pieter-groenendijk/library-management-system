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
