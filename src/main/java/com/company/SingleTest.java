package com.company;

import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.dlsyntax.renderer.DLSyntaxObjectRenderer;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;
import uk.ac.manchester.cs.jfact.JFactFactory;

import static com.company.Utility.*;

public class SingleTest {
    public static void singletest() throws myException {
        OWLClassExpression owlClass=
                Utility.firstChoose(insod,depth-1,atomic,intersezione,unione,perOgni,esiste);

        OWLReasonerFactory rf3 = new ReasonerFactory();
        OWLReasoner hermit = rf3.createReasoner(Utility.o);
        JFactFactory reasonerFactoryJ = new JFactFactory();
        OWLReasoner jfact = reasonerFactoryJ.createReasoner(Utility.o);
        double timeiH = System.currentTimeMillis();
        boolean risH = hermit.isSatisfiable(owlClass);
        double timeEndH =  ((System.currentTimeMillis() - timeiH));
        double timeij = System.currentTimeMillis();
        boolean risj = jfact.isSatisfiable(owlClass);
        double timeEndj = ((System.currentTimeMillis() - timeij));

        OWLObjectRenderer renderer = new DLSyntaxObjectRenderer();
        renderer.setShortFormProvider(new SimpleShortFormProvider());
        System.out.println("\nConcetto : " + renderer.render(owlClass) + "\n");
        System.out.println("Soddisfacibile hermit: " + risH+ " Time : " + timeEndH+ " millisecondi.");
        System.out.println("Soddisfacibile JFact : " + risj + " Time : " + timeEndj + " millisecondi.");



    }
}
