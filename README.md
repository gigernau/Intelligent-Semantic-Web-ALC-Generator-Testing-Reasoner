# Intelligent-Semantic-Web-ALC-Generator-Testing-Reasoner
Intelligent-Semantic-Web-ALC-Generator-Testing-Reasoner 


# 1 Introduzione
## 1.1 Generazione e Test
Il seguente progetto consta di tre fasi principali:
  - Generazione di un concetto ALC in NNF
  - Test di soddisfacibilità con reasoner Hermit e JFact
  - Confronto tra i tempi dei due reasoner

Per generare il concetto è richiesta all’utente una percentuale con cui scegliere il grado di
probabilità che si voglia un concetto soddisfacibile o meno.
Dopo una prima scelta randomica condizionata dalla probabilità, si avvia la generazione di
un albero i cui nodi rappresentano i vari operatori di intersezione, unione, complemento,
restrizione universale ed esistenziale.
Ricorsivamente si genera ogni livello dell’albero fino alla profondità richiesta.
Nelle foglie, con una funzione randomica al 50% ,si genera un letterale negato o meno.
Il tutto è stato implementato con le OWL-API 5.0.3 e il linguaggio Java 8.
Per testare se un concetto sia o meno soddisfacibile, si è utilizzato il reasoner Hermit messo
a confronto con i tempi del reasoner JFact.

## 1.2 GUI

![Schermata da 2022-01-13 18-22-30](https://user-images.githubusercontent.com/10176197/149379014-a503ccc4-9622-4c9e-a6e5-14dafe63c9ea.png)

Finestra iniziale in cui l’utente può scegliere se effettuare un singolo test o una batteria
preimpostata.

![Schermata da 2022-01-13 18-22-50](https://user-images.githubusercontent.com/10176197/149379066-33190c5d-4cca-46c4-9032-e0ee2f2aa36b.png)

Finestra di test singolo in cui l’utente può scegliere la profondità dell’albero, la percentuale
di insoddisfacibilità, il numero di role e concept names, e la distribuzione delle probabilità
relative a vari operatori.

# 2 Classi e funzioni

Le classi del progetto sono Main, Menu, InputForm, BatteryTest, SingleTest, Utility.

## 2.1 CRC Cards

![Schermata da 2022-01-13 18-23-17](https://user-images.githubusercontent.com/10176197/149379971-31b78de4-6640-4930-b3a0-70345582a14b.png)

![Schermata da 2022-01-13 18-23-17](https://user-images.githubusercontent.com/10176197/149379296-79e419d3-22f1-45c2-b60c-ec5532d1738b.png)

![Schermata da 2022-01-13 18-23-23](https://user-images.githubusercontent.com/10176197/149379319-03dde5c7-4dcb-43f2-88e2-c6c506074dea.png)

![Schermata da 2022-01-13 18-23-28](https://user-images.githubusercontent.com/10176197/149379387-97a65c41-d3ab-4d5e-817a-bb3ec618a9b9.png)

## 2.2 Funzioni principali

![Schermata da 2022-01-13 18-23-50](https://user-images.githubusercontent.com/10176197/149380300-bc45f53a-e785-4d77-b748-cd69de7a3cd9.png)

firstChoose: scelta iniziale, tramite probabilità, di generare un
concetto insoddisfacibile o soddisfacibile.

![Schermata da 2022-01-13 18-23-58](https://user-images.githubusercontent.com/10176197/149380325-beb4bff0-db8a-4b35-a3c3-dd16da2a4f04.png)

generateSatisfacibleConcept: ricevendo per parametri la
profondità dell’albero e le percentuali di probabilità di ogni tipo
operatore, genera un concetto che in maniera randomica, in base
alle probabilità, ricorsivamente viene scelto il tipo di operatore per
arrivare fino alle foglie dove vengono generati dei concetti atomici.

# 3 Testing
## 3.1 Concetti di test

I test effettuati:
  - Variando la profondità dell’albero
  - Variando il numero di concept e role name
  - Variando le probabilità degli operatori

## 3.2 Conclusioni
Il ragionatore HermiT e del ragionatore JFact hanno entrambi ottime prestazioni per il controllo
della sodisfacibilità di un concetto.

Conviene l’uso di Hermit per:
   Albero pieno e bilanciato
   Pochi atomic concept
   Grandi dimensioni degli alfabeti
   Per concetti insoddisfacibili
  
Mentre conviene utilizzare JFact per:
   Albero pieno con gli operatori: ⱻ , Ɐ
   Molti atomic concept
   Piccole dimensioni degli alfabeti
  
In conclusione se ne deriva che entrambi sono ottimi reasoner da utilizzare.

## Language program 
  Java 8 
  SDK 5
  OwlApi 
