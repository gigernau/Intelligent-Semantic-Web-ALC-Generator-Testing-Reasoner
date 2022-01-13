package com.company;





import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import uk.ac.manchester.cs.jfact.JFactFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.company.Utility.*;


public class BatteryTest {
    public static void battery() throws myException, OWLOntologyCreationException, IOException {
        List<Boolean> listah = new ArrayList<>();
        List<Double> timeh = new ArrayList<>();
        List<Boolean> listaj = new ArrayList<>();
        List<Double> timej = new ArrayList<>();
        List<Boolean> listaa = new ArrayList<>();
        List<Double> timea = new ArrayList<>();

        HashMap<Integer,String> description = new HashMap<>();
        OWLClassExpression testExp;
        FileWriter w;
        w = new FileWriter("risultati.txt");

        int i=0;
        depth = 5;

        // insoddisfatto: insodisfacibilita valore in percentuale
        // MaxDepth: profondità ovvero numero di concetti
        // a: percentuale dell'uscita di un concetto atomico
        // b: percentuale dell'uscita di un'intersezione
        // c: percentuale dell'uscita di un'unione
        // d: percentuale dell'uscita di una restrizione universale
        // e: percentuale dell'uscita di una restrizione esisteziale

        for(int k=0;k<3;k++) {

            description.put(i, "albero pieno, probabilità bilanciate, ins:0%, alfabeti:3");
            numAtomic = 3;
            numRole = 3;
            testExp=firstChoose(0, depth - 1, 0, 25, 25, 25, 25);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);

            description.put(i, "op: esiste,per ogni, ins:0%, alfabeti:3");
            numAtomic = 3;
            numRole = 3;
            testExp=firstChoose(0, depth - 1, 0, 0, 0, 50, 50);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);

            description.put(i, "alta probabilita concetti atomici, ins:10%, alfabeti:15");
            numAtomic = 15;
            numRole = 15;
            testExp=firstChoose(10, depth - 1, 80, 5, 5, 5, 5);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);

            description.put(i, "albero pieno, probabilità bilanciate, ins:10%, alfabeti:30");
            numAtomic = 30;
            numRole = 30;
            testExp=firstChoose(10, depth - 1, 0, 25, 25, 25, 25);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);

            description.put(i, "albero pieno, probabilità bilanciate, ins:10%, alfabeti:100");
            numAtomic = 100;
            numRole = 100;
            testExp=firstChoose(10, depth - 1, 0, 25, 25, 25, 25);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);

            description.put(i, "albero pieno, soddisfacibile 100%, alfabeti:30");
            numAtomic = 30;
            numRole = 30;
            testExp=firstChoose(0, depth - 1, 0, 25, 25, 25, 25);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);

            description.put(i, "albero pieno, insoddisfacibile 100%, alfabeti:30");
            numAtomic = 30;
            numRole = 30;
            testExp=firstChoose(100, depth - 1, 0, 25, 25, 25, 25);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);
            description.put(i, "valori equilibrati, prob: 20%, alfabeti:30");
            numAtomic = 30;
            numRole = 30;
            testExp=firstChoose(10, depth - 1, 20, 20, 20, 20, 20);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);

            description.put(i, "op: unione,itersezione, alfabeti:30");
            numAtomic = 30;
            numRole = 30;
            testExp=firstChoose(10, depth - 1, 20, 40, 40, 0, 0);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);

            description.put(i, "op: esistenziale,universale, alfabeti:30");
            numAtomic = 30;
            numRole = 30;
            testExp=firstChoose(10, depth - 1, 20, 0, 0, 40, 40);
            reasonertest(i++,listah,listaj,timeh,timej,testExp);

            depth=depth+5;

        }


        depth=5;
        for(int m=0;m<listah.size();m++) {
            if(description.get(m).equals(description.get(0))&&m>0){
                depth=depth+10;
            }
            String a = "\n\nTest : " + description.get(m)+" con profondità : "+depth;
            String b = "\nHermit : " + listah.get(m) + " --- Time : " + timeh.get(m) + " millisecondi.";
            String c = "\nJFacT : " + listaj.get(m) + " --- Time : " + timej.get(m) + " millisecondi";

            System.out.println(a);
            w.write(a);
            System.out.println(b);
            w.write(b);
            System.out.println(c);
            w.write(c);

        }
        w.flush();
    }


    public static void reasonertest(int i, List h, List j, List timeh, List timej,
                                    OWLClassExpression c) throws OWLOntologyCreationException {
        OWLReasonerFactory rf3 = new ReasonerFactory();
        OWLReasoner hermit = rf3.createReasoner(Utility.o);
        JFactFactory reasonerFactoryJ = new JFactFactory();
        OWLReasoner jfact = reasonerFactoryJ.createReasoner(Utility.o);

        double timeiH = System.currentTimeMillis();
        boolean ris = hermit.isSatisfiable(c);
        double timeEndH = ((System.currentTimeMillis() - timeiH));
        double timeij = System.currentTimeMillis();
        boolean risj = jfact.isSatisfiable(c);
        double timeEndj = ((System.currentTimeMillis() - timeij));


        h.add(i,ris);
        j.add(i,risj);


        timeh.add(i,timeEndH);
        timej.add(i,timeEndj);


        manager.clearOntologies();
        o = manager.createOntology(iri);
    }
}
