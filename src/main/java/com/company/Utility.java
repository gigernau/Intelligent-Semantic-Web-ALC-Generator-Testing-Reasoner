package com.company;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.util.Random;

public class Utility {


    public static int depth;
    public static int insod;
    public static int atomic;
    public static int numAtomic;
    public static int numRole;
    public static int unione;
    public static int intersezione;
    public static int esiste;
    public static int perOgni;
    public static int countAtom=0;
    public static IRI iri = IRI.create("http://test-Ontology/new.owl");
    public static OWLOntologyManager manager= OWLManager.createOWLOntologyManager();
    public static OWLOntology o;
    public static OWLDataFactory df = OWLManager.getOWLDataFactory();

    static {
        try {
            o = manager.createOntology(iri);
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
    }

    static class myException extends Exception {

        myException(String p){
            if (p=="probabilità")
                System.out.println("Errore somma di probabilità");
            if (p=="err")
                System.out.println("Errore generico");
        }

    }
    static Boolean coinflip() {
        Random rnd = new Random();
        return rnd.nextBoolean();
    }


    public static String generateConceptName(Integer length) {
        final String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String uc = uCase;
        int mod = numAtomic%26;
        int div = numAtomic/26;
        int taglio = 25;
        if(mod!=0 ){
            div++;
        }
        taglio = (numAtomic/div);
        //GESTIONE LUNGHEZZA CARATTERI
        if(numAtomic <= 26){
            length=1;
            uc = uCase.substring(0,numAtomic);
        }else{
            uc = uCase.substring(0,taglio-1);
            //lunght random
            Random ra = new Random();
            int spot1 = ra.nextInt(div);
            length = spot1;
            if(length==0)
                length++;
        }
        String name = "";
        Random r = new Random();
        while (name.length() < length) {
            int spot = r.nextInt(taglio-1);
            name += uc.charAt(spot);

        }
        return name;
    }

    public static String generateRoleName(Integer length) {
        final String dCase = "abcdefghijklmnopqrstuvwxyz";
        String uc;
        int mod = numRole%26;
        int div = numRole/26;
        int taglio = 26;
        if(mod!=0 ){
            div++;
        }
        taglio = (numRole/div);
        //GESTIONE LUNGHEZZA CARATTERI
        if(numRole <= 25){
            length=1;
            uc = dCase.substring(0,numRole);
        }else{
            uc = dCase.substring(0,taglio-1);
            //lunght random
            Random ra = new Random();
            int spot1 = ra.nextInt(div);
            length = spot1;
            if(length==0)
                length++;

        }

        String name = "";
        Random r = new Random();

        while (name.length() < length) {
            int spot = r.nextInt(taglio-1);
            name += uc.charAt(spot);

        }

        return "Role(" + name + ")";
    }

    static OWLClassExpression generateUnsatisfiableConcept(int MaxDepth,
                                                           int a, int b, int c, int d, int e) throws myException {
        OWLClassExpression ret;
        if (coinflip()) {
            OWLClassExpression C =  generateSatisfiableConcept(MaxDepth,a,b,c,d,e);
            OWLClassExpression notC = df.getOWLObjectComplementOf(C);
            ret = df.getOWLObjectIntersectionOf(C,notC);
            return ret;
        }else{
            OWLClassExpression C1 =  generateSatisfiableConcept(MaxDepth,a,b,c,d,e);
            ret = df.getOWLObjectIntersectionOf(C1, df.getOWLNothing());
            return ret;
        }
    }
    static OWLClassExpression firstChoose(int insoddisfatto, int MaxDepth,
                                          int a, int b, int c, int d, int e) throws myException {
        OWLClassExpression ret;
        Random roll = new Random();
        int r = roll.nextInt(100);

        if (r>=0 && r<=insoddisfatto){
            ret = generateUnsatisfiableConcept(MaxDepth,a,b,c,d,e);
        }else {
            ret = generateSatisfiableConcept(MaxDepth,a,b,c,d,e);
        }
        return ret;
    }
    static OWLClassExpression atomicConcept(Integer lenght) {

        OWLClass concetto = df.getOWLClass(IRI.create(iri + "#" + generateConceptName(lenght)));

        if (coinflip()) {
            OWLClassExpression notC = df.getOWLObjectComplementOf(concetto);
            return notC;
        }else
            return concetto;
    }
    static OWLClassExpression intersezione(int MaxDepth,
                                           int a, int b, int c, int d, int e) throws myException {
        OWLClassExpression ramoA= generateSatisfiableConcept(MaxDepth,a,b,c,d,e);
        OWLClassExpression ramoB= generateSatisfiableConcept(MaxDepth,a,b,c,d,e);
        OWLClassExpression intersezione =  df.getOWLObjectIntersectionOf(ramoA,ramoB);
        return intersezione;

    }
    static OWLClassExpression unione(int MaxDepth,
                                     int a, int b, int c, int d, int e) throws myException {


        OWLClassExpression ramoA= generateSatisfiableConcept(MaxDepth,a,b,c,d,e);
        OWLClassExpression ramoB= generateSatisfiableConcept(MaxDepth,a,b,c,d,e);
        OWLClassExpression unione =  df.getOWLObjectUnionOf(ramoA,ramoB);
        return unione;

    }

    static OWLClassExpression exists( int MaxDepth,
                                      int a, int b, int c, int d, int e) throws myException {
        OWLObjectProperty role= df.getOWLObjectProperty(IRI.create(iri + "#" + generateRoleName(1)));
        o.add(df.getOWLDeclarationAxiom(role));
        OWLClassExpression exConcept = generateSatisfiableConcept(MaxDepth,a,b,c,d,e);
        OWLClassExpression ex = df.getOWLObjectSomeValuesFrom(role,exConcept);


        return ex;

    }

    static OWLClassExpression forAll( int MaxDepth,
                                      int a, int b, int c, int d, int e) throws myException {
        OWLObjectProperty role= df.getOWLObjectProperty(IRI.create(iri + "#" + generateRoleName(1)));
        o.add(df.getOWLDeclarationAxiom(role));
        OWLClassExpression exConcept = generateSatisfiableConcept(MaxDepth,a,b,c,d,e);
        OWLClassExpression ex = df.getOWLObjectAllValuesFrom(role,exConcept);

        return ex;

    }
    static OWLClassExpression generateSatisfiableConcept(Integer MaxDepth,
                                                         int a, int b, int c, int d, int e) throws myException {

       // System.out.println(com.company.main.contapassi);


        OWLClassExpression ret;
        Random roll = new Random();
        if((a+b+c+d+e) == 100) {

            int arr[] = {a, b, c, d, e};
            int[] out = new int[arr.length + 2];
            out[0] = 0;
            for (int i = 1; i < 6; i++) {
                out[i] = out[i - 1] + arr[i - 1];
            }
            int r = roll.nextInt(100);

            if (MaxDepth <= 0) {
                r = 0;
            }

            if (r >= 0 && r <= out[1]) {
                //concetto foglia
                ret = atomicConcept(1);

            } else if (r >= out[1] && r <= out[2]) {

                ret = intersezione(MaxDepth - 1,a, b, c, d, e);

            } else if (r >= out[2] && r <= out[3]){

                ret = unione(MaxDepth - 1, a, b, c, d, e);

            }
            else if (r >= out[3] && r <= out[4]) {
                ret= forAll(MaxDepth - 1, a, b, c, d, e);

            } else {
                ret= exists(MaxDepth - 1, a, b, c, d, e);

            }

        }
        else{
            throw new myException("probabilità");
        }

        return ret;

    }

}


