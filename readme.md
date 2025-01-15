Flipperautomat-Simulation

Author: Paul Kogler
FH-Abschlussprojekt der Lehrveranstaltung Softwarearchitektur

Ein konsolenbasierter Flipperautomaten-Simulator, der mithilfe gängiger Software-Architektur- und Designmuster erstellt wurde. Dieses Projekt dient als Demonstration für den Einsatz von Entwurfsmustern wie State, Command, Composite, Visitor, Mediator, Abstract Factory und Singleton. Programmiersprache ist Java.

--Projektübersicht--

Dieses Programm simuliert den Ablauf eines Flipperautomaten, bei dem der Spieler:

    ->Kredit hinzufügen und das Spiel starten kann.

    ->Mehrere Bälle durch verschiedene Flipperelemente wie Bumper, Targets und Rampen schießen kann.

    ->Zusätzliche Boni wie einen Extra Ball erhalten kann.

    ->Punkte sammelt, die am Ende jeder Runde zusammengezählt und angezeigt werden.
    
Verwendete Entwurfsmuster

1. State-Pattern

    Der Flipperautomat hat vier Zustände:

    NoCreditState: Kein Kredit verfügbar.

    ReadyState: Kredit verfügbar, Spiel kann gestartet werden.

    PlayingState: Das Spiel ist aktiv.

    EndState: Spiel beendet.

    Die Zustandswechsel werden durch die Klasse FlipperMachine verwaltet.

2. Command-Pattern

    Befehle steuern Aktionen wie das Hinzufügen von Punkten (ScoreCommand) oder das Öffnen der Rampe (OpenRampCommand).

    Das Command-Pattern ermöglicht eine entkoppelte Steuerung von Aktionen.

3. Composite-Pattern

    Targets und andere Flipperelemente sind in einer FlipperGroup organisiert, die die Verwaltung und Interaktion mit mehreren Elementen vereinfacht.

4. Visitor-Pattern

    Der PunkteVisitor berechnet die Punkte basierend auf den getroffenen Elementen.

    Der ResetVisitor setzt den Zustand aller Elemente am Spielende oder bei einem Neustart zurück.

5. Mediator-Pattern

    Der FlipperMediator koordiniert die Interaktion zwischen verschiedenen Flipperelementen:

    Öffnet die Rampe, wenn alle Targets getroffen wurden.

    Synchronisiert Aktionen zwischen Elementen.

6. Abstract Factory

    ASCII-Output für verschiedene Anzeigen:

    SlantFactory: Für Ballnummern.

    PoisonFactory: Für spezielle Meldungen wie "Start Game" und "Game Over".

7. Singleton-Pattern

    Der GameManager verwaltet den Punktestand zentral und stellt sicher, dass es nur eine Instanz gibt.

Funktionen

Targets und Rampen:

    Targets können gruppiert werden.

    Die Rampe öffnet sich, wenn alle Targets getroffen wurden. Erst jetzt kann die Rampe getroffen werden.
    
    bei geöffneter Rampe wird ein Bonus für alle getroffene Bumper aktiviert.

Bumper:

    Jeder Treffer erhöht die Punkte

Extra Ball:

    Mit geringer Wahrscheinlichkeit wird ein Extra Ball vergeben, der nur einmal pro Spiel verfügbar ist. Er rettet einen verlorenen Ball für das laufende Spiel und erhöht die Anzahl der Bälle +1.

Menü:

    Hauptmenü zur Steuerung des Spiels:

        Kugel schießen.

        Punkte anzeigen (zeigt auch den aktuellen State an).

        Spiel beenden.
