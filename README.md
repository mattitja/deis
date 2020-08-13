# DEIS - Digitales Einsatz-InformationsSystem

# Einleitung

Um die vielseitigen Einsätze der Feuerwehr ein Stück weit einfacher zu machen, existiert die im folgenden beschriebene App „DEIS – DIGITALES EINSATZ INFORMATIONS SYSTEM“. Feuerwehreinsätze drehen sich meist um die Rettung von Menschenleben. Dies kann jedoch ganz unterschiedlicher Natur sein. 
Zum einen sind Unfallbergung aber auch die klassische Brandbekämpfung an der Tagesordnung. Hierfür ist es unerlässlich, genauestens informiert zu sein, wie das Menschenleben schnellstmöglich gerettet werden kann. Dafür gibt es zum Beispiel sogenannte Rettungskarten. Eine mögliche Rettungskarte ist eine Informationskarte über verschiedene Automodelle, in denen beschrieben ist, wie das jeweilige Automodell aufgebaut ist, wo man das Auto im Ernstfall aufschneiden kann um einen Menschen zu bergen, wo sich Tragsäulen des Autos befinden und vieles mehr.

Eine andere Rettungskarte ist eine Landkarte, auf der alle Hydranten der Umgebung eingetragen sind. Sie kann auch Informationen über die Kanalisation des Einsatzgebiets enthalten, sowie genaue Straßen- und U-Bahn Karten. Eine andere Rettungskarte könnte wiederum Gebäudepläne enthalten, die im Falle eines Brandes Leben retten – da die Einsatzkräfte genau wissen, wo sie im Gebäude hinmüssen. Dies könnte zum Beispiel ein Gebäudeplan eines Krankenhauses, Altenheime oder einer Schule sein.

All diese Dokumente liegen oftmals nicht digital vor, sondern werden als tatsächliche Papier Karten bei Einsätzen in unzähligen Ordnern mitgeführt. Es bedarf dann eines echten Profis, die jeweilige Rettungskarte schnellstmöglich herauszusuchen und den Einsatzkräften zur Verfügung zu stellen.
Mittlerweile sind viele dieser Dokumente aber tatsächlich schon digital verfügbar oder können mit einfachen Mitteln digitalisiert werden, sodass sie beispielsweise auf einem Tablet von einem File Manager aufgerufen werden könnten.

In der Praxis stellt sich aber heraus, dass bisher kein solcher File Manager existiert, der den besonderen Anforderungen eines Feuerwehreinsatzes gerecht wird. An diesem Punkt setzt DEIS ein.

DEIS ist auf jeden Einsatz speziell ausgelegt, d.h. es kann innerhalb von Sekunden die jeweils richtigen Dokumente bereitstellen. Dies funktioniert durch eine einfache, übersichtliche und unmissverständliche Oberfläche, mit großen Schaltflächen (die auch im Eifer des Gefechts unfehlbar sind), klarer Schrift, starker Kontraste und ohne Schnörkeleien, die nur zu Platzverschwendung und Ablenkung führen würden. Die Menüführung ist einfach und intuitiv. Die App kann sich nicht aus Versehen schließen (dies wäre im Ernstfall fatal) und enthält keine Medienbrüche (wie Sprünge von File Manager in PDF Viewer). Sie ist robust und darf keine unerwarteten Zustände einnehmen.

Ein weiterer wichtiger Punkt ist die offline-Fähigkeit der App. Diese ist von äußerster Notwendigkeit, da es immer noch in vielen Teilen des Landes kein ausreichend gutes bis gar kein Netz gibt, wie zum Beispiel in Wäldern oder unterirdisch in Kanalsystemen.
Die App ist außerdem durch die Ordner- und Dokumentenstruktur individuell anpassbar und durch die Konfigurationsdateien können sehr einfach Layout und Funktion verändert werden. So können sind beispielsweise auch sehr simpel neue Schaltflächen erstellt werden.
Die Anpassungen können (außerhalb des Einsatzes natürlich) schnell durchgeführt werden und so ist die App immer an die Bedürfnisse der jeweiligen Feuerwehr abgestimmt.

# Bedienungsanleitung

Die Verwendung der DEIS-App erfolgt grundsätzlich in zwei Phasen. Der Konfigurationsphase und der Einsatzphase. Die App kann erst mit auf den Einsatz genommen werden, wenn sie konfiguriert wurde.

## Konfiguration
Die App besteht aus drei Bestandteilen. Der Applikationsdatei (kurz APK), die auf dem Android-Tablet installiert werden kann, den anzuzeigenden Nutzdaten in Form von Ordnern und PDFs, und den Konfigurationsdateien, die für die visuelle Darstellung der App und der einzelnen Elemente verantwortlich sind.

### APK
Zunächst wird das Tablet per USB-Kabel an einen Rechner angeschlossen. Nun kann per Android Device Bridge (ADB) die APK auf dem Gerät installiert werden. Dazu wird in der Kommandozeile des Rechners in das Verzeichnis der APK gewechselt und dann der Befehl

adb install deis.apk

ausgeführt. Die App installiert sich nun auf dem Gerät. Nach erfolgreicher Installation öffnet sich die App und fragt den Benutzer nach der Berechtigung, auf den Telefonspeicher zugreifen zu dürfen. Dies sollte mit “Ja” beantwortet werden.
Die App meldet nun, dass sie keine passende Ordnerstruktur im Telefonspeicher finden kann und fordert den Nutzer auf, die entsprechende Ordnerstruktur anzulegen. Der Nutzer nimmt nun das der APK beiliegende .zip-Archiv und entpackt es auf dem Rechner. Darin befindet sich ein Ordner “deis”, der wiederum zwei Unterordner hat: “data” und “config”

### Nutzdaten
Der Nutzer legt die Daten, die er als Nutzdaten auf dem Tablet verwenden möchte, im Ordner “data” ab. Diese sind beispielsweise Rettungskarten in pdf-Format (siehe Abb.1) oder neue Ordner Er kann dabei beliebig viele verschachtelte Unterordner einrichten und PDFs einkopieren. Jeder (Unter-)Ordner wird in der App intuitiv als eine Menüebene dargestellt.

![GitHub Logo](https://i.imgur.com/v66pB8g.png)
Format: ![Rettungskarte eines Audis](url)

### Konfigurationsdateien
Zunächst wird unterschieden zwischen globalen und dateispezifischen Konfigurationsdateien. Im Ordner “config” liegen die beiden globalen XML-Dateien “colors.cfg” und “defaults.cfg”. 

##### colors.cfg
In der Datei „colors.cfg“ können Farbwerte in Form von HEX-Werten abgespeichert werden und zugehörige Alias vergeben werden. Das dient dazu, dass ein festes Farbenset für eine App definiert werden kann, welches dann in allen anderen Konfigurationsdateien verwendet werden kann, ohne dass sich der genaue HEX-Wert gemerkt werden muss (aus #FFFFFF wird z.B. white). 
Des Weiteren kann so an einer zentralen Stelle eine Farbe geändert werden, die dann Auswirkungen auf alle Elemente der App hat, die diese Farbe referenzieren. 

#### default.cfg
In der „defaults.cfg“ werden Standard-Einstellungen für die App getroffen, die teilweise von dateispezifischen Konfigurationsdateien überschrieben werden können, aber nicht müssen. So müssen hier Farbwerte (als HEX oder Alias) für Schaltflächenfarben, Textfarben und Schaltflächen-Icons definiert werden. Diese Werte können später überschrieben werden. Des Weiteren werden hier aber auch die Hintergrundfarben für die Menüleiste und den allgemeinen Hintergrund festgelegt. Diese Datei muss existieren und gültige Werte enthalten, da sie als „Fallback“ genutzt werden, falls keine dateispezifische Konfiguration vorliegt.
#### datei_abc.cfg
Dateispezifische Konfigurationsdateien werden nicht im Ordner “config” abgelegt, sondern direkt in die Nutzdatenstruktur “data” selbst, damit die Konfiguration von einzelnen Dateien möglichst intuitiv ist. Eine Datei (ein Ordner, ein PDF oder ein App-Link) zieht dann eine Konfiguration an, wenn eine Datei mit dem exakt gleichen Namen existiert, nur als Endung .cfg besitzt.

Beispiel: “HydrantenkarteHamburg2018.pdf” wird konfiguriert durch die Datei “HydrantenkarteHamburg2018.cfg”.

Der Aufbau dieser Konfigurationsdatei ähnelt dem der “defaults.cfg”. Hier können die Schaltflächenfarbe, die Textfarbe und das Icon der Schaltfläche individuell für jede Datei angepasst werden. Zusätzlich kann ein „displayName” vergeben werden. Der „displayName“ gibt an, unter welchem Namen die Datei angezeigt werden soll. Dies dient dem Zweck, dass man die Dateinamen oftmals anders nennen möchte, als die Anzeigenamen, zum Beispiel ohne Leerzeichen und mit Datum im Dateinamen. In der App wird - falls ein „displayName” vergeben wurde - nur dieser angezeigt.

2.1.3.4 Vererbung
Wie schon angedeutet, verwendet DEIS das Prinzip der Vererbung. Die Werte in der defaults.cfg müssen angelegt werden, sodass sie als Fallback genutzt werden können. Alle dateispezifischen Konfigurationen sind lediglich optional und können die Defaults überschreiben. Hier können einzelne Attribute (wie beispielsweise nur die Textfarbe) überschrieben werden, während andere Attribute nicht überschrieben werden. Es ist auch möglich, keine dateispezifische Konfigurationsdatei anzulegen. In diesem Fall werden für das Element nur die default-Werte beachtet.

2.1.4 Icons
Jede Schaltfläche kann mit einem individuellen Icon versehen werden. Die Icons müssen im .png-Format im Ordner “config/icons” abgelegt werden und können dann aus den Konfigurationsdateien mit dem Dateinamen ohne Endung referenziert werden. Dies sieht so aus:

<item>
<icon>notausgang</icon>
…
</item>

Hier wird die Datei “config/icons/notausgang.png” referenziert. Das Format PNG wird verwendet, damit Transparenzen möglich sind.

2.1.5 Link-Dateien
Ein weiteres Feature von DEIS ist, andere auf dem Gerät befindliche Apps öffnen zu können, ohne dass es sich so anfühlt, als würde der Nutzer die App verlassen.
Dazu muss eine leere Datei angelegt werden, die im Dateinamen den Package-Namen der zu öffnenden Datei beinhaltet. Der Package-Name einer Datei kann im Google-Play-Store herausgefunden werden.
Beispiel für einen Link der auf Google Maps verweist:

z...com.google.android.apps.maps.link

Dabei ist „z“ ein beliebig langer String, der einfach nur dazu dient, die Möglichkeit zu bieten, die Schaltfläche in eine alphabetische Reihenfolge innerhalb des Ordners zu bringen.
Der Marker “…” markiert den Anfang des Package-Namens. Die Endung “.link” markiert das Ende des Package-Namens.
Selbstverständlich kann auch für eine “.link”-Datei eine “.cfg”-Datei angelegt werden, die das Aussehen der Schaltfäche bestimmt.

2.1.6 Reihenfolge von Elementen einer Menüebene
Wie bereits angedeutet, werden die Elemente einer Menüebene alphabetisch sortiert. Das bezieht sich aber ausschließlich auf die Dateinamen und nicht auf die Anzeige-Namen. Somit ist es möglich mit Zahlen oder Buchstaben die Elemente in eine beliebige Reihenfolge zu bringen (zum Beispiel: 0_hydranten.pdf, 1_kanäle.pdf) und gleichzeitig die anzuzeigenden Namen nicht zu verfälschen.

2.1.7 Übertragung auf das Tablet
Wenn die Ordnerstruktur “/deis” wie gewünscht angepasst wurde, kann der Ordner auf das Rootverzeichnis der SD-Karte des Tablets kopiert werden. Dazu muss in der Kommandozeile im Elternordner von “deis” folgender Befehl eingegeben werden:

adb push /deis /sdcard/

Der gesamte Ordner wird nun übertragen. Alternativ kann das Tablet (unter Windows) im Datei-Explorer unter “Computer” angewählt und geöffnet werden. Dann kann per Drag and Drop der Ordner übertragen werden. Wichtig dabei ist, dass der Ordner sich im Root-Verzeichnis der SD-Karte befindet. (Hinweis: Jedes Android-Telefon hat den Ordner /sdcard/, auch wenn das Gerät gar keinen SD-Karten-Slot besitzt!)

2.1.8 Aktualisieren der Ordnerstruktur
Wenn irgendwelche Änderungen an der Ordnerstruktur vorgenommen werden und die App bereits läuft, muss sie, bevor die Änderungen übernommen werden, einmal hart beendet und wieder neugestartet werden. Denn erst beim App-Start wird die Ordnerstruktur komplett neu eingeladen.















2.2 Einsatzphase
Das Tablet ist nun für den Einsatz konfiguriert. Dieses Kapitel beschreibt die Benutzung der fertig konfigurierten App.

Nach Öffnen der App erscheint ein 2-Sekündiger Start-Screen, der das Logo von DEIS trägt, sowie den Namen der App und die Entwickler-Copyrights (siehe Abb. 2)


Abb. 2: Startscreen

Anschließend öffnet sich die Schaltflächenübersicht der untersten Menüebene. Die zuvor konfigurierten Buttons sind nun hier zu sehen, mit zugehörigen dahinterliegenden Ordnern, Dokumenten und Links (siehe Abb. 3).


Abb. 3: Hauptebene

2.2.1 Menüleiste
Die Menüleiste besteht aus folgenden Komponenten:
dem Zurückbutton, der in die vorige Menüebene wechselt
dem Menütitel, der angibt, in welchem Element man sich momentan befindet.
einem Home-Button, der einen unmittelbar in die Hauptmenüebene zurückbefördert.

Der Zurück-Button und der Hauptmenü-Button wird in allen Menüebenen und auch im PDF-Reader angezeigt, außer im Hauptmenü selbst. 

2.2.2 Schaltflächen
Die App ist so programmiert, dass im Querformat des Tablets (Landscape) fünf quadratische Schaltflächen nebeneinander angezeigt werden und im Hochformat (Portrait) drei nebeneinander.
Eine Schaltfläche besteht aus folgenden Komponenten:

dem Anzeigenamen
der Button-Farbe
der Textfarbe
dem Icon
dem Dokumentenart-Icon (klein links oben)
Dies würde man in der „RettungskartenOrdnername.cfg“ so realisieren:
<?xml version="1.0" encoding="utf-8"?>
<item>
<displayName>Rettungskarten</displayName>
<buttonColor>yellow</buttonColor>
<textColor>black</textColor>
<icon>autounfall</icon>
</item>

Daraus entsteht die gelbe „Rettungskarten“ Schaltfläche in Abb. 4.


Abb. 4: Schaltflächen im Hochformat
2.2.3 PDF-Reader
Öffnet man ein PDF-Dokument öffnet sich der integrierte PDF-Reader im Lesemodus.
Bei großen Dokumenten mit vielen Seitenzahlen gibt es die Möglichkeit über eine Scrollbar in ergonomischer Größe rechts im Screen durch die Seiten zu fahren. Es gibt außerdem die Möglichkeit in eine auswählbare Seitenzahl zu springen.
Eine Zoom-Funktion ist ebenfalls Bestandteil im Lesemodus. Hierfür müssen einfach zwei Finger auf die Stelle gelegt werden, die heran gezoomt werden soll und auseinandergezogen werden. Wie beim handelsüblichen Smartphone auch, vergrößert sich nun dieser Ausschnitt. Diese Funktion nennt man „pitch-to-zoom“.
Will man den Lesemodus verlassen, kann man dies über zwei verschiedene Navigationsmöglichkeiten tun. Zum einen gibt es den „Zurückbutton“, mit dem man in der Ordnerstruktur jeweils eine Ebene nach oben zurückkommt und den „Home“-Button.

Wenn PDF’s mehrere Seiten haben, gibt es im Lesemodus zusätzlich den „scroll-to-top“-Button, mit dem man wieder ganz an den Anfang des Dokuments springen kann (siehe Abb.5)


Abb. 5: Scroll-to-top Button in der Menüleiste

2.2.4 Links
Beim Öffnen eines Links zu einer anderen auf dem Telefon installierten App gibt es nicht viel zu beachten. Die App wird direkt geöffnet, insofern sie installiert ist. Andersfalls wird ein Fehler ausgegeben, dass das konfigurierte Package nicht auf dem Telefon gefunden werden kann (siehe Abb. 6.1)
Mit der neusten Version von DEIS wird zusätzlich darauf hingewiesen, dass man jetzt eine externe App öffnet und ob man fortfahren möchte, damit man nicht aus Versehen aus der DEIS App springt (siehe Abb. 6.2). 

Abb.  6.1: Fehlermeldung, wenn App nicht installiert ist


Abb.6.2: Abfrage ob externe App gestartet werden soll


3. Entwicklung

Die DEIS-App wurde in enger Zusammenarbeit mit der Freiwilligen Feuerwehr Aumühle (bei Hamburg) entwickelt. Der dortige Ansprechpartner ist der Bruder von Matthias Miro - Joachim Miro. Dieser wird fortan als Kunde bezeichnet.

3.1 Tools

3.1.1 Framework und IDE
Die App DEIS ist eine Android-App. Android ist ein Java-Framework und Applikationen dafür werden mit der Entwicklungsumgebung “Android Studio” entwickelt, die ein Ableger der IDEA IntelliJ ist. Android Studio ermöglicht in einem Wizard das Bereitstellen der notwendigen Datei-Infrastruktur, um eine ausführbare Applikationsdatei (APK) zu erstellen.
Des Weiteren kommt mit Android Studio auch das Android SDK, das Tools beinhaltet, die die App-Entwicklung erleichtern. Dazu gehören die Android Device Bridge (ADB) und der Android Device Manager (ADM).

3.1.2 Android Device Manager
Da dem Entwicklerteam kein Tablet zur Verfügung stand, dem Kunden allerdings schon, haben die Entwickler die Spezifikationen (Auflösung, Android-Version, Größe des Displays) vom Kundentablet aufgenommen und im Android Device Manager, der mit Android Studio ausgeliefert wird, ein virtuelles Tablet mit den gleichen Spezifikationen erzeugt. Auf diesem virtuellen Tablet konnten die Entwickler schnell und einfach ihre APK installieren und testen.
Das emulierte Device ermöglicht zudem weitere Features wie das direkte Erstellen von Screenshots, das emulierte Drehen des Devices und vieles mehr.

3.1.3 Android Device Bridge
Per ADB kann in der Kommandozeile eine Verbindung mit dem emulierten Gerät (theoretisch auch mit einem realen Gerät) hergestellt werden. Sie ermöglicht das Installieren einer APK-Datei (adb install) sowie das Kopieren von Dateien (adb push).

3.1.4 Versionsverwaltung
Zur Versionsverwaltung wurde GIT eingesetzt. Da der meiste Code im Pair Programming zwischen Britta und Matthias entstanden ist, wurde auf branches verzichtet. Es wurde versucht, mit jedem Commit die App lauffähig sein zu lassen, damit Rollbacks möglich sind. GIT lässt sich leicht in Android Studio integrieren, sodass ein Committen und Pushen direkt aus der Entwicklungsumgebung möglich ist. Bei der Erstellung eines neuen Android-Projektes in Android Studio wird eine passende .gitignore gleich miterstellt, die rechnerspezifische Konfigurationen von Android Studio ignoriert und nur die wichtigen Source-Dateien in GIT berücksichtigt.

3.1.5 Trello
Im Kanban-Board Trello wurde für jeden Entwicklungstask ein eigenes kleines Ticket erstellt. Auf dieses Board hatten sowohl die Entwickler als auch der Kunde Zugriff, sodass das Board der zentrale Ort der Kommunikation war. Ohne, dass eine direkte Kommunikation stattfinden musste, konnte der Kunde, hatte er eine neue Version erhalten und getestet, direkt Feedback geben und Verbesserungsvorschläge einreichen. So ist eine gute, effektive Kommunikation zwischen Kunde und Entwicklern zustande gekommen (siehe Abb. 7).


Abb. 7: Trello Board






3.2 Software Architektur
Es wurde die Standard-Software-Architektur von Android-Apps genutzt. Die Programmiersprache ist Java. 

3.2.1 Screens/Activities
Ein Screen in einer App besteht immer aus zwei Komponenten:
Der visuellen Darstellung, die durch das Layout-File (XML) beschrieben wird. Hier werden alle View-Elemente angelegt und näher definiert. Falls ein View-Element eine dynamische Komponente enthält, wird ihr eine ID zugewiesen, die dann im Code referenziert werden kann.
Der programmatischen Java-Klasse, die im Android-Kontext als Activity bezeichnet wird. Eine Activity hat einen vordefinierten Lifecycle, an den man sich als Entwickler halten muss und darf. Für mehr Informationen zum Lifecycle: https://developer.android.com/guide/components/activities/activity-lifecycle.html
In der Activity können dynamisch Views verändert und manipuliert werden.

Die DEIS-App hat verschiedene Activities:

3.2.1.1 Start Acitivity
Die StartActivity wird bei App-Start geöffnet und zeigt zum einen einen 2-Sekündigen Start-Bildschirm an, um den Nutzer darauf einzustimmen, in was für einer App er gelandet ist. Des Weiteren triggert die Activity das Laden der kompletten Ordnerstruktur. Bei einem erfolgreichen Laden (keine Exceptions) wird in die MenuActivity weitergeleitet und die App ist funktionsbereit. Bei einem Fehler beim Laden wird in die ErrorActivity umgeleitet und der Fehler wird ausgegeben.
Bevor die Ordnerstruktur geladen wird, wird einmalig die Berechtigung für das Lesen aus dem externen Speicher abgefragt. Wird diese Erlaubnis nicht erteilt, schließ sich die App und die Erlaubnis wird beim nächsten Start erneut erfragt.

3.2.1.2 Error Activity
Die ErrorActivity dient nur dazu etwaige Fehler auszugeben. Sie akzeptiert einen Intent (Parameter an die Activity) mit einer Message und einem Exception-Stacktrace und gibt diesen mit weißer Schrift auf schwarzen Hintergrund aus. Wird sie beendet, beendet sich die App.

3.2.1.3 Menu Activity
Die MenuActivity stellt die Elemente einer Ordnerebene als Schaltflächen da. Die eigentliche Konvertierung aus Dokument in ein Schaltflächenelement passiert dabei im MenuItemAdapter, der der Adapter für das GridView der MenuActivity ist. Bei einem Klick auf eine Schaltfläche ruft die MenuActivity sich selbst rekursiv auf und übergibt dabei den gegangenen Pfad in der baumartigen Ordnerstruktur.
Wird die unterste MenuActivity beendet, beendet sich die App, ansonsten wird nur die Menüebene geschlossen und die darunterliegende kommt erneut zum Vorschein. So ergibt sich eine intuitive Menüführung, die man auch aus herkömmlichen File Managern kennt.

3.2.2 DEIS Application
Die Klasse DEISApplication erbt von der Android Application Klasse und wird initiiert, sobald die App gestartet wird. In dieser Klasse sind die Pfade hinterlegt, auf die die App auf der SD-Karte zugreifen soll. Sie dient als zentrale Informationsquelle für Klassen wie bspw. den File Manager.

3.2.3 File Manager
Um auf die “data”-Dateien im File-System zugreifen zu können, wurde ein Klasse FileManager geschrieben. Diese Klasse wird wie beschrieben in der StartActivity initiiert und zum Laden der “Data”-Ordnerstruktur angestoßen. Konkret übersetzt sie die Data-Files im Filesystem in sogenannte “Items”. Das Laden erfolgt folgendermaßen:
zunächst werden alle Konfigurationsdateien (.cfg) aus dem “data” Verzeichnis geladen. Diese Files werden im ItemParser, der das XML in ein Java-POJO (namens ItemConfiguration) überträgt, geparst. Alle ItemConfigurations werden zunächst in einer Hashmap im Speicher gehalten
dann werden alle restlichen Dateien aus dem “data”-Ordner geladen und es wird versucht, für jedes Item eine passende ItemConfiguration in der HashMap zu finden. Falls das gelingt, werden die Werte wie TextColor oder ButtonColor in das Item übertragen. Falls nicht, wird auf die Standardwerte zurückgegriffen.

3.2.4 GlobalConfigurationManager
Dieser Manager geht ähnlich wie der FileManager vor, nur liest er nur zwei konkrete Dateien ein: Die colors.cfg und die defaults.cfg. Er parst beide Dateien und hält sie als static-Variablen im Speicher. Zugleich ist der Manager ein Singleton und ermöglicht damit den Zugriff von überall aus allen anderen Klassen der App.
Die Farben werden in einer HashMap gespeichert, mit dem Alias als Key (String) und dem HEX-Wert als Value (ebenfalls String).

3.2.5 PDF-Reader
Beim PDF-Reader wird auf eine externe Lösung gesetzt. Der Reader stammt von github (https://github.com/barteksc/AndroidPdfViewer) und wird per gradle-Dependency ins Projekt reingezogen.
Die Klasse, die den PDF-Reader verwendet, wurde aus dem Beispiel-Projekt kopiert und dann entsprechend auf die DEIS-App angepasst. So wurde bspw. ein individueller ScrollHandle implementiert, der die aktuelle Seitenanzahl anzeigt. Außerdem wurden diverse Parameter beim Aufruf des PDF-Readers individualisiert.

3.2.6 Laden der Icons im Adapter
Das Laden der Icons geschieht im MenuItemAdapter. Dieser erstellt aus Items Schaltflächen. In einem Item ist der icon-Name hinterlegt und mit dessen Hilfe wird versucht, ein passendes Bild im Ordner “config/icons” zu finden. Dabei wird die Endung .png vorausgesetzt, damit auch transparente Bilder zum Einsatz kommen können. Falls kein Icon gefunden werden kann, wird das default-Icon geladen.

3.2.7 Schaltflächen als Links
In der Bedienungsanleitung wird beschrieben, wie man einen App-Link konfiguriert. Wenn der packageName der App erfolgreich ausgelesen wurde, wird ein externer Intent erstellt, der auf die App verweist. Den Rest erledigt Android selbst.







4. Projektauswertung

4.1 Idee
Die Idee zu DEIS wurde vom Kunden Joachim Miro an Matthias Miro herangetragen. Zunächst stand die Frage im Raum, ob es eine solche App auf dem Markt gibt, die für den Einsatz geeignet sein könnte. Der Kunde hatte bereits einige ausgetestet und war mit keiner zufrieden, da sie sich allesamt als zu sperrig, unintuitiv und zu wenig individuell anpassbar erwiesen.
Daher kam die Idee für die eigene App “DEIS”.

4.2 Planung
Zunächst begann Matthias Miro Anfang 2017 einen ersten kleinen Prototyp zu entwickeln. Der Kunde testet ihn und gab das OK. Das erste Ergebnis ging in die richtige Richtung. Allerdings musste Matthias Miro die Arbeit dann zunächst unterbrechen, da seine Bachelorarbeit anstand. Als er das Projekt im Winter 2017 wiederaufnahm, hatte er die Idee das Projekt C daraus zu machen und suchte sich dafür noch eine Partnerin, die er in Britta Walter (Projekt B) fand. Da sie schon viele Kurse zusammen bestanden hatten, erwies sich diese Zusammenstellung als Team als sehr gut. 

4.3 Umsetzung

4.3.1 Was lief gut
Da die beiden Entwickler sich gut kennen, verlief auch die Umsetzungsphase gut. Über Trello konnte sowohl mit dem Kunden als auch untereinander gut kommuniziert werden, wenn neue Ideen aufkamen oder Verbesserungsvorschläge zu bereits Gecodetem gemacht wurden. Matthias kommunizierte natürlich auch außerhalb dessen viel mit seinem Bruder, die wichtigsten Erkenntnisse wurden jedoch stets über Trello geteilt.
Außerdem gab es regelmäßige Treffen einmal in der Woche, die sich oft bis in die Nacht erstreckten, um zusammen weiter zu programmieren. In der Endphase wurde aus dem einwöchigen Rhythmus mehr Tage in der Woche, dies unterschied sich jedoch von Woche zu Woche. Beide Entwickler nahmen dies sehr ernst und es fand sich fast immer ein oder mehrere Tage, an denen weiterentwickelt wurde. 
Zwischen den regelmäßigen Treffen wurden neue Ideen gesammelt, Code reviewed und Notizen zusammengetragen. Die Notizen der zwei Entwickler wurden an jedem Treffen aus- und bewertet. Auf Grundlage dessen wurde dann weiter entwickelt. Dies stellte sich als eine sehr gute Idee heraus, da so nie die Aufgaben und das Ziel aus den Augen verloren ging und gut vorbereitet weiter zusammen gearbeitet werden konnte.
Davon abgesehen kam nach einiger Zeit eine regelrechte Begeisterung auf, da beiden sehr bewusst war, wie wichtig eine App wie DEIS einmal sein könnte. Diese schwappte zum großen Teil von Matthias‘ Bruder herüber und spornte zusätzlich an.

4.3.2 Was hätte besser laufen können
Da zu Beginn des Projekts Matthias alleine entwickelte, war die Anfangsphase für Britta mit ein paar Stolpersteinen gespickt. Diese spiegelten sich unter anderem in der Entwicklungsumgebung Android Studio wieder. Der Britta zur Verfügung stehende Laptop-PC konnte zunächst Android Studio nicht bewältigen, da dies doch ein sehr großes Programm ist und Brittas Laptop schon bessere Zeiten gesehen hatte. Nach einigen nervenaufreibenden Tagen konnte dann doch installiert werden und der aller erste Prototyp aus Anfang 2017 gepullt werden. Von nun an Begann die Zusammenarbeit zu zweit.
Zunächst war geplant, dass jeder auf seinen eigenen Branches arbeitet. Dies erwies sich aber recht schnell als der falsche Weg, da zu oft auf den jeweils anderen hätte gewartet werden müssen. Hier war die Aufgabenverteilung nicht optimal, da nicht darauf geachtet wurde, dass unabhängig voneinander weiter programmiert werden konnte. Außerdem hatte Brittas PC hin und wieder Schwierigkeiten mit Android Studio. Die suboptimale Aufgabenverteilung fiel recht schnell auf. Auf Grundlage dessen und der Android Studio Schwierigkeiten, entschied das Team, auf verschiedene Entwickler-Branches zu verzichten und gemeinsam „an einem Strang“ zu ziehen. Dies spiegelte sich durch Pair-Programming wider. Es wurde ein Tag pro Woche vereinbart, an dem sich getroffen, diskutiert und weiter entwickelt werden sollte. An den anderen Tagen sollte kein Code allein geschrieben werden, sondern nur Notizen, die im jeweils nächsten Treffen miteinander besprochen werden sollten. Zu Beginn der Zusammenarbeit lief dies nicht immer wie es sollte, da beide Entwickler unabhängig voneinander nicht die Zeit zum Treffen aufbringen konnten. Dies hatte zum einen private als auch berufliche Gründe.
So verlief die Anfangsphase etwas schleifend, bis Anfang Dezember schließlich der nötige Wille und die nötige Zeit beiderseits aufgebracht werden und die regelmäßigen Treffen stattfinden konnten.

4.3.3. Tatsächlicher Aufwand

Aufgabe
Schätzung
Tatsächlich
Technischer Unterbau der Android App mit Dokumenten Analyse
40 Personentage
~43 Personentage
Gestaltung Benutzeroberfläche
15 PT
~14 PT
Einbindung PDF Reader und Anpassung
10 PT
~6 PT
Kommunikation mit dem Kunden
5 PT
~ 4 PT
Erstellen der Dokumentation 
5 PT
~ 3 PT


Testen und Bug Fixes
5 PT
~ 7 PT    






GESAMT
80 PT
~77 PT*

*davon ~46 PT von Matthias Miro durch Aufsetzen und Prototypisierung und ~31 PT von Britta Walter



4.4 Ausblick
Wir sehen in DEIS ein großes Potenzial tatsächlich Menschenleben retten zu können und den Feuerwehren ihre Arbeit zumindest ein wenig zu erleichtern. 
Wir und die Feuerwehr in Aumühle können uns gut vorstellen, dass diese App auch bei anderen Feuerwehren reges Interesse wecken könnte und wollen uns der Möglichkeit öffnen, dies zur Verfügung zu stellen.
Die App wird derzeit von der Feuerwehr Aumühle benutzt und getestet. Sofern der Langzeit Test ebenfalls gut verläuft, werden wir eine Publikation weiter verfolgen.








