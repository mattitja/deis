# DEIS - Digitales Einsatz-InformationsSystem

# Einleitung

Um die vielseitigen Einsätze der Feuerwehr zu unterstützen, existiert die im folgenden beschriebene Tablet-App 
`DEIS - Digitales Einsatz-InformationsSystem`. 

Sie ist ein auf den Feuerwehreinsatz optimierter File-Manager, der auf die individuellen Anforderungen jeder Feuerwehr angepasst werden kann.

## Rettungskarten für den Feuerwehreinsatz

Unfallbergung, aber auch die klassische Brandbekämpfung sind in Feuerwehren an der Tagesordnung. Hierfür ist es unerlässlich, genauestens informiert zu sein, wie das Menschenleben schnellstmöglich gerettet werden kann. Dafür gibt es zum Beispiel sogenannte *Rettungskarten*. Eine mögliche Rettungskarte ist eine Informationskarte über verschiedene Automodelle, in denen beschrieben ist, wie das jeweilige Automodell aufgebaut ist, wo man das Auto im Ernstfall aufschneiden kann um einen Menschen zu bergen, wo sich Tragsäulen des Autos befinden und vieles mehr.

Eine andere Rettungskarte ist eine Landkarte, auf der alle Hydranten der Umgebung eingetragen sind. Sie kann auch Informationen über die Kanalisation des Einsatzgebiets enthalten, sowie genaue Straßen- und U-Bahn Karten. Eine wieder andere Rettungskarte könnte  Gebäudepläne enthalten, die im Falle eines Brandes Leben retten – da die Einsatzkräfte genau wissen, wo sie im Gebäude hinmüssen. Dies könnte zum Beispiel ein Gebäudeplan eines Krankenhauses, Altenheime oder einer Schule sein.

## Einsatzzweck von DEIS

All diese Dokumente liegen oftmals nicht digital vor, sondern werden als tatsächliche Papier Karten bei Einsätzen in unzähligen Ordnern mitgeführt. Es bedarf dann eines echten Profis, die jeweilige Rettungskarte schnellstmöglich herauszusuchen und den Einsatzkräften zur Verfügung zu stellen.

Mittlerweile sind viele dieser Dokumente aber tatsächlich schon digital verfügbar oder können mit einfachen Mitteln digitalisiert werden, sodass sie beispielsweise auf einem Tablet von einer `File Manager`-App aufgerufen werden könnten.

In der Praxis stellt sich aber heraus, dass bisher kein solcher `File Manager` existiert, der den besonderen Anforderungen eines Feuerwehreinsatzes gerecht wird. An diesem Punkt setzt DEIS ein.

### Kernfunktionen

DEIS ist auf jeden Einsatz speziell ausgelegt, d.h. es kann innerhalb von Sekunden die jeweils richtigen Dokumente bereitstellen. Dies funktioniert durch 
- eine einfache, übersichtliche und unmissverständliche Oberfläche
- große Schaltflächen (die auch im Eifer des Gefechts unfehlbar sind)
- klare Schrift
- starken Kontraste und ohne Schnörkeleien
- einfache Menüführung
- kein versehentliches Schließen der App (dies wäre im Ernstfall fatal)
- keine Medienbrüche (wie Sprünge von File Manager in PDF Viewer)
- keine unerwarteten Zustände
- absolut robust
- offline-only, keine Netzverbindung nötig.

### Individuell auf die eigene Feuerwehr zuschneidbar

Die App ist durch die Ordner- und Dokumentenstruktur individuell auf die Anforderungen der eigenen Feuerwehr anpassbar und durch die Konfigurationsdateien können sehr einfach Layout und Funktion verändert werden. So können sind beispielsweise auch sehr simpel neue Schaltflächen erstellt werden.

Die Anpassungen können (außerhalb des Einsatzes natürlich) schnell durchgeführt werden und so ist die App immer an die Bedürfnisse der jeweiligen Feuerwehr abgestimmt.

# Bedienungsanleitung

Die Verwendung der DEIS-App erfolgt grundsätzlich in zwei Phasen. Der *Konfigurationsphase* und der *Einsatzphase*. Die App kann erst mit auf den Einsatz genommen werden, wenn sie konfiguriert wurde.

## Konfiguration
Die App besteht aus drei Bestandteilen. 
- Der Applikationsdatei (kurz APK), die auf dem Android-Tablet installiert werden kann, 
- den anzuzeigenden Nutzdaten in Form von Ordnern und PDFs, 
- und den Konfigurationsdateien, die für die visuelle Darstellung der App und der einzelnen Elemente verantwortlich sind.

Die aktuellste APK und Beispielinhalte für Nutz- und Konfigurationsdaten (Inhalte der Feuerwehr Aumühle) können hier heruntergeladen werden: 
https://drive.google.com/drive/folders/1cy1U9ckWzk7hf8zfkomYB0bUvwmgA1Y9?usp=sharing

### APK
Zunächst wird das Tablet per USB-Kabel an einen Rechner angeschlossen. Nun kann per Android Device Bridge (ADB) die APK auf dem Gerät installiert werden. Dazu wird in der Kommandozeile des Rechners in das Verzeichnis der APK gewechselt und dann der Befehl

`adb install deis.apk`

ausgeführt. Die App installiert sich nun auf dem Gerät. Nach erfolgreicher Installation öffnet sich die App und fragt den Benutzer nach der Berechtigung, auf den Telefonspeicher zugreifen zu dürfen. Dies sollte mit 
Ja” beantwortet werden.

Die App meldet nun, dass sie keine passende Ordnerstruktur im Telefonspeicher finden kann und fordert den Nutzer auf, die entsprechende Ordnerstruktur anzulegen. Der Nutzer nimmt nun das der APK beiliegende .zip-Archiv und entpackt es auf dem Rechner. Darin befindet sich ein Ordner `deis`, der wiederum zwei Unterordner hat: `data` und `config`

### Nutzdaten
Der Nutzer legt die Daten, die er als Nutzdaten auf dem Tablet verwenden möchte, im Ordner `data` ab. Diese sind beispielsweise Rettungskarten in pdf-Format (siehe Abb.1) oder neue Ordner Er kann dabei beliebig viele verschachtelte Unterordner einrichten und PDFs einkopieren. Jeder (Unter-)Ordner wird in der App intuitiv als eine Menüebene dargestellt.

![Rettungskarte eines Audis](https://i.imgur.com/v66pB8g.png)

### Konfigurationsdateien
Zunächst wird unterschieden zwischen globalen und dateispezifischen Konfigurationsdateien. Im Ordner `config` liegen die beiden globalen XML-Dateien `colors.cfg` und `defaults.cfg`. 

#### colors.cfg
In der Datei `colors.cfg` können Farbwerte in Form von HEX-Werten abgespeichert werden und zugehörige Alias vergeben werden. Das dient dazu, dass ein festes Farbenset für eine App definiert werden kann, welches dann in allen anderen Konfigurationsdateien verwendet werden kann, ohne dass sich der genaue HEX-Wert gemerkt werden muss (aus `#FFFFFF` wird z.B. `white`). 

Des Weiteren kann so an einer zentralen Stelle eine Farbe geändert werden, die dann Auswirkungen auf alle Elemente der App hat, die diese Farbe referenzieren. 

#### default.cfg
In der `defaults.cfg` werden Standard-Einstellungen für die App getroffen, die teilweise von dateispezifischen Konfigurationsdateien überschrieben werden können, aber nicht müssen. So müssen hier Farbwerte (als HEX oder Alias) für Schaltflächenfarben, Textfarben und Schaltflächen-Icons definiert werden. Diese Werte können später überschrieben werden. Des Weiteren werden hier aber auch die Hintergrundfarben für die Menüleiste und den allgemeinen Hintergrund festgelegt. Diese Datei muss existieren und gültige Werte enthalten, da sie als „Fallback“ genutzt werden, falls keine dateispezifische Konfiguration vorliegt.

#### datei_abc.cfg
Dateispezifische Konfigurationsdateien werden nicht im Ordner `config` abgelegt, sondern direkt in die Nutzdatenstruktur `data` selbst, damit die Konfiguration von einzelnen Dateien möglichst intuitiv ist. Eine Datei (ein Ordner, ein PDF oder ein App-Link) zieht dann eine Konfiguration an, wenn eine Datei mit dem exakt gleichen Namen existiert, nur als Endung .cfg besitzt.

*Beispiel: `HydrantenkarteHamburg2018.pdf` wird konfiguriert durch die Datei `HydrantenkarteHamburg2018.cfg`.*

Der Aufbau dieser Konfigurationsdatei ähnelt dem der `defaults.cfg`. Hier können die Schaltflächenfarbe, die Textfarbe und das Icon der Schaltfläche individuell für jede Datei angepasst werden. Zusätzlich kann ein `displayName` vergeben werden. Der `displayName` gibt an, unter welchem Namen die Datei angezeigt werden soll. Dies dient dem Zweck, dass man die Dateinamen oftmals anders nennen möchte, als die Anzeigenamen, zum Beispiel ohne Leerzeichen und mit Datum im Dateinamen. In der App wird - falls ein `displayName` vergeben wurde - nur dieser angezeigt.

#### Vererbung
Wie schon angedeutet, verwendet DEIS das Prinzip der Vererbung. Die Werte in der defaults.cfg müssen angelegt werden, sodass sie als Fallback genutzt werden können. Alle dateispezifischen Konfigurationen sind lediglich optional und können die Defaults überschreiben. Hier können einzelne Attribute (wie beispielsweise nur die Textfarbe) überschrieben werden, während andere Attribute nicht überschrieben werden. Es ist auch möglich, keine dateispezifische Konfigurationsdatei anzulegen. In diesem Fall werden für das Element nur die default-Werte beachtet.

### Icons
Jede Schaltfläche kann mit einem individuellen Icon versehen werden. Die Icons müssen im .png-Format im Ordner `config/icons` abgelegt werden und können dann aus den Konfigurationsdateien mit dem Dateinamen ohne Endung referenziert werden. Dies sieht so aus:

```
<item>
  <icon>notausgang</icon>
  …
</item>
```

Hier wird die Datei `config/icons/notausgang.png` referenziert. Das Format PNG wird verwendet, damit Transparenzen möglich sind.

### Link-Dateien
Ein weiteres Feature von DEIS ist, andere auf dem Gerät befindliche Apps öffnen zu können, ohne dass es sich so anfühlt, als würde der Nutzer die App verlassen.
Dazu muss eine leere Datei angelegt werden, die im Dateinamen den Package-Namen der zu öffnenden Datei beinhaltet. Der Package-Name einer Datei kann im Google-Play-Store herausgefunden werden.
Beispiel für einen Link der auf Google Maps verweist:

`z...com.google.android.apps.maps.link`

Dabei ist `z` ein beliebig langer String, der einfach nur dazu dient, die Möglichkeit zu bieten, die Schaltfläche in eine alphabetische Reihenfolge innerhalb des Ordners zu bringen.
Der Marker `...` markiert den Anfang des Package-Namens. Die Endung `.link` markiert das Ende des Package-Namens.
Selbstverständlich kann auch für eine `.link`-Datei eine `.cfg`-Datei angelegt werden, die das Aussehen der Schaltfäche bestimmt.

### Reihenfolge von Elementen einer Menüebene
Wie bereits angedeutet, werden die Elemente einer Menüebene alphabetisch sortiert. Das bezieht sich aber ausschließlich auf die Dateinamen und nicht auf die Anzeige-Namen. Somit ist es möglich mit Zahlen oder Buchstaben die Elemente in eine beliebige Reihenfolge zu bringen (zum Beispiel: `0_hydranten.pdf`, `1_kanäle.pdf`) und gleichzeitig die anzuzeigenden Namen nicht zu verfälschen.

### Übertragung auf das Tablet
Wenn die Ordnerstruktur `/deis` wie gewünscht angepasst wurde, kann der Ordner auf das Rootverzeichnis der SD-Karte des Tablets kopiert werden. Dazu muss in der Kommandozeile im Elternordner von `/deis` folgender Befehl eingegeben werden:

`adb push /deis /sdcard/`

Der gesamte Ordner wird nun übertragen. Alternativ kann das Tablet (unter Windows) im Datei-Explorer unter “Computer” angewählt und geöffnet werden. Dann kann per Drag and Drop der Ordner übertragen werden. Wichtig dabei ist, dass der Ordner sich im Root-Verzeichnis der SD-Karte befindet. (Hinweis: Jedes Android-Telefon hat den Ordner `/sdcard/`, auch wenn das Gerät gar keinen SD-Karten-Slot besitzt!)

### Aktualisieren der Ordnerstruktur
Wenn irgendwelche Änderungen an der Ordnerstruktur vorgenommen werden und die App bereits läuft, muss sie, bevor die Änderungen übernommen werden, einmal hart beendet und wieder neugestartet werden. Denn erst beim App-Start wird die Ordnerstruktur komplett neu eingeladen.

## Einsatzphase
Das Tablet ist nun für den Einsatz konfiguriert. Dieses Kapitel beschreibt die Benutzung der fertig konfigurierten App.

Nach Öffnen der App erscheint ein 2-Sekündiger Start-Screen, der das Logo von DEIS trägt, sowie den Namen der App und die Entwickler-Copyrights.

![](https://i.imgur.com/joqxjsE.png)

Anschließend öffnet sich die Schaltflächenübersicht der untersten Menüebene. Die zuvor konfigurierten Buttons sind nun hier zu sehen, mit zugehörigen dahinterliegenden Ordnern, Dokumenten und Links (siehe Abb. 3).

![](https://i.imgur.com/gLLW7zQ.png)

### Menüleiste
Die Menüleiste besteht aus folgenden Komponenten:
- dem Zurückbutton, der in die vorige Menüebene wechselt
- dem Menütitel, der angibt, in welchem Element man sich momentan befindet.
- einem Home-Button, der einen unmittelbar in die Hauptmenüebene zurückbefördert.

Der Zurück-Button und der Hauptmenü-Button wird in allen Menüebenen und auch im PDF-Reader angezeigt, außer im Hauptmenü selbst. 

### Schaltflächen
Die App ist so programmiert, dass im Querformat des Tablets (Landscape) fünf quadratische Schaltflächen nebeneinander angezeigt werden und im Hochformat (Portrait) drei nebeneinander.

Eine Schaltfläche besteht aus folgenden Komponenten:

- dem Anzeigenamen
- der Button-Farbe
- der Textfarbe
- dem Icon
- dem Dokumentenart-Icon (klein links oben)

Dies würde man in der `RettungskartenOrdnername.cfg` so realisieren:

```
<?xml version="1.0" encoding="utf-8"?>
  <item>
    <displayName>Rettungskarten</displayName>
    <buttonColor>yellow</buttonColor>
    <textColor>black</textColor>
  <icon>autounfall</icon>
</item>
```

Daraus entsteht die gelbe `Rettungskarten` Schaltfläche in folgender Abbildung.

![](https://i.imgur.com/D2V9a6j.png)

### PDF-Reader
Öffnet man ein PDF-Dokument öffnet sich der integrierte PDF-Reader im Lesemodus.

Bei großen Dokumenten mit vielen Seitenzahlen gibt es die Möglichkeit über eine Scrollbar in ergonomischer Größe rechts im Screen durch die Seiten zu fahren. Es gibt außerdem die Möglichkeit in eine auswählbare Seitenzahl zu springen.

Eine Zoom-Funktion ist ebenfalls Bestandteil im Lesemodus. Hierfür müssen einfach zwei Finger auf die Stelle gelegt werden, die heran gezoomt werden soll und auseinandergezogen werden. Wie beim handelsüblichen Smartphone auch, vergrößert sich nun dieser Ausschnitt. Diese Funktion nennt man „pitch-to-zoom“.
Will man den Lesemodus verlassen, kann man dies über zwei verschiedene Navigationsmöglichkeiten tun. Zum einen gibt es den „Zurückbutton“, mit dem man in der Ordnerstruktur jeweils eine Ebene nach oben zurückkommt und den „Home“-Button.

Wenn PDF’s mehrere Seiten haben, gibt es im Lesemodus zusätzlich den „scroll-to-top“-Button, mit dem man wieder ganz an den Anfang des Dokuments springen kann:

![](https://i.imgur.com/ffoKzjR.png)

### Links
Beim Öffnen eines Links zu einer anderen auf dem Telefon installierten App gibt es nicht viel zu beachten. Die App wird direkt geöffnet, insofern sie installiert ist. Andersfalls wird ein Fehler ausgegeben, dass das konfigurierte Package nicht auf dem Telefon gefunden werden kann:

![](https://i.imgur.com/ARd8TCg.png)

Mit der neusten Version von DEIS wird zusätzlich darauf hingewiesen, dass man jetzt eine externe App öffnet und ob man fortfahren möchte, damit man nicht aus Versehen aus der DEIS App springt:

![](https://i.imgur.com/Cd13sMm.png)


# Infrastruktur

## Activities

### Start Acitivity
Die StartActivity wird bei App-Start geöffnet und zeigt zum einen einen 2-Sekündigen Start-Bildschirm an, um den Nutzer darauf einzustimmen, in was für einer App er gelandet ist. Des Weiteren triggert die Activity das Laden der kompletten Ordnerstruktur. Bei einem erfolgreichen Laden (keine Exceptions) wird in die MenuActivity weitergeleitet und die App ist funktionsbereit. Bei einem Fehler beim Laden wird in die ErrorActivity umgeleitet und der Fehler wird ausgegeben.

Bevor die Ordnerstruktur geladen wird, wird einmalig die Berechtigung für das Lesen aus dem externen Speicher abgefragt. Wird diese Erlaubnis nicht erteilt, schließt sich die App und die Erlaubnis wird beim nächsten Start erneut erfragt.

### Error Activity
Die ErrorActivity dient nur dazu etwaige Fehler auszugeben. Sie akzeptiert einen Intent (Parameter an die Activity) mit einer Message und einem Exception-Stacktrace und gibt diesen mit weißer Schrift auf schwarzen Hintergrund aus. Wird sie beendet, beendet sich die App.

### Menu Activity
Die MenuActivity stellt die Elemente einer Ordnerebene als Schaltflächen da. Die eigentliche Konvertierung aus Dokument in ein Schaltflächenelement passiert dabei im MenuItemAdapter, der der Adapter für das GridView der MenuActivity ist. Bei einem Klick auf eine Schaltfläche ruft die MenuActivity sich selbst rekursiv auf und übergibt dabei den gegangenen Pfad in der baumartigen Ordnerstruktur.

Wird die unterste MenuActivity beendet, beendet sich die App, ansonsten wird nur die Menüebene geschlossen und die darunterliegende kommt erneut zum Vorschein. So ergibt sich eine intuitive Menüführung, die man auch aus herkömmlichen File Managern kennt.

## sonstige Komponenten

### DEIS Application
Die Klasse DEISApplication erbt von der Android Application Klasse und wird initiiert, sobald die App gestartet wird. In dieser Klasse sind die Pfade hinterlegt, auf die die App auf der SD-Karte zugreifen soll. Sie dient als zentrale Informationsquelle für Klassen wie bspw. den File Manager.

### File Manager
Um auf die `data`-Dateien im File-System zugreifen zu können, wurde ein Klasse FileManager geschrieben. Diese Klasse wird wie beschrieben in der StartActivity initiiert und zum Laden der `data`-Ordnerstruktur angestoßen. Konkret übersetzt sie die Data-Files im Filesystem in sogenannte `Items`. Das Laden erfolgt folgendermaßen:

zunächst werden alle Konfigurationsdateien (`.cfg`) aus dem `data` Verzeichnis geladen. Diese Files werden im ItemParser, der das XML in ein Java-POJO (namens `ItemConfiguration`) überträgt, geparst. Alle ItemConfigurations werden zunächst in einer Hashmap im Speicher gehalten, dann werden alle restlichen Dateien aus dem `data`-Ordner geladen und es wird versucht, für jedes `Item` eine passende `ItemConfiguration` in der HashMap zu finden. Falls das gelingt, werden die Werte wie `textColor` oder `buttonColor` in das `Item` übertragen. Falls nicht, wird auf die Standardwerte zurückgegriffen.

### GlobalConfigurationManager
Dieser Manager geht ähnlich wie der FileManager vor, nur liest er nur zwei konkrete Dateien ein: Die `colors.cfg` und die `defaults.cfg`. Er parst beide Dateien und hält sie als static-Variablen im Speicher. Zugleich ist der Manager ein Singleton und ermöglicht damit den Zugriff von überall aus allen anderen Klassen der App.
Die Farben werden in einer HashMap gespeichert, mit dem Alias als Key (String) und dem HEX-Wert als Value (ebenfalls String).

## PDF-Reader
Beim PDF-Reader wird auf eine externe Lösung gesetzt. Der Reader stammt von github (https://github.com/barteksc/AndroidPdfViewer) und wird per gradle-Dependency ins Projekt reingezogen.
Die Klasse, die den PDF-Reader verwendet, wurde aus dem Beispiel-Projekt kopiert und dann entsprechend auf die DEIS-App angepasst. So wurde bspw. ein individueller `ScrollHandle` implementiert, der die aktuelle Seitenanzahl anzeigt. Außerdem wurden diverse Parameter beim Aufruf des PDF-Readers individualisiert.

## Laden der Icons im Adapter
Das Laden der Icons geschieht im `MenuItemAdapter`. Dieser erstellt aus Items Schaltflächen. In einem Item ist der icon-Name hinterlegt und mit dessen Hilfe wird versucht, ein passendes Bild im Ordner `config/icons` zu finden. Dabei wird die Endung `.png` vorausgesetzt, damit auch transparente Bilder zum Einsatz kommen können. Falls kein Icon gefunden werden kann, wird das default-Icon geladen.

## Schaltflächen als Links
In der Bedienungsanleitung wird beschrieben, wie man einen App-Link konfiguriert. Wenn der packageName der App erfolgreich ausgelesen wurde, wird ein externer Intent erstellt, der auf die App verweist. Den Rest erledigt Android selbst.
