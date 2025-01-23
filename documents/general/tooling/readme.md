# Tooling en infrastructuur
Tijdens de implementatie, en dus niet de implementatie zelf, is de volgende tooling gebruikt.

## Project management
Deze verantwoordelijkheid wordt gedeeld op het platform _Github_, namelijk _Github Projects_. 

## Versiebeheer
Het project staat in zijn geheel (alle services) onder versiebeheer in één repo[^1]. Hiervoor wordt _git_ gebruikt.

Deze repo wordt gehost op [_github_](https://github.com/pieter-groenendijk/oose-library-management-system).

## Development Omgeving
### Containerisatie
Met behulp van docker kunnen er een geïsoleerde omgevingen gemaakt worden om met consistentie de individuele services
uit te voeren.

Met behulp van docker kan er een geïsoleerde omgeving van elke individuele service aangemaakt worden op een consistente
manier, los van het besturingssysteem[^3].

### Orchestratie
Docker compose zorgt ervoor dat de individuele containers gemaakt met docker als één eenheid gereproduceerd kan worden
met zijn eigen netwerk, configuratie, etc.

### Scripting
Voor sommige doeleinden is er gebruik gemaakt van scripting. Hiervoor
is _bash_ gebruikt[^2]. Hierdoor is het gemakkelijk om met één knop in de IDE de workflow met de development omgeving
te automatiseren.

[^1]: Dit zou potentieel in de toekomst opgesplitst kunnen worden.  
[^2]: _Powershell_ zou respectievelijk een betere keuze kunnen zijn i.v.m. besturingsysteem _compatibility_.
[^3]: In de praktijk is dit niet helemaal waar. Er zijn nog steeds kleine discrepanties.
