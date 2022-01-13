# Intelligent-Semantic-Web-ALC-Generator-Testing-Reasoner
Intelligent-Semantic-Web-ALC-Generator-Testing-Reasoner 


# 1) Introduzione
The following project consists of three main phases:

- Generation of an ***ALC*** concept in Negative Normal Form (***NNF***)
- Feasibility test with ***Hermit*** and ***JFact*** reasoners
- Comparison between the times of the two reasoners

To generate the concept, the user is asked for a percentage with which to choose the degree of probability that a satisfactory concept is desired or not.

After a first random choice conditioned by probability, a tree is generated whose nodes represent the various operators of intersection, union, complement, universal and existential restriction.

Recursively, each level of the tree is generated up to the required depth. In the leaves, with a random function at 50%, a literal negated or not is generated

All this has been implemented with the OWL-API 5.0.3 and the Java 8 language. To test whether a concept is satisfactory or not, the Hermit reasoner was used compared with the time of the JFact reasoner.

## 1.1 GUI

![Schermata da 2022-01-13 18-22-30](https://user-images.githubusercontent.com/10176197/149379014-a503ccc4-9622-4c9e-a6e5-14dafe63c9ea.png)

Initial window in which the user can choose whether to perform a single test or a preset battery.

![Schermata da 2022-01-13 18-22-50](https://user-images.githubusercontent.com/10176197/149379066-33190c5d-4cca-46c4-9032-e0ee2f2aa36b.png)

Single test window in which the user can choose the depth of the tree, the percentage of dissatisfacibility, the number of roles and concept names, and the distribution of probabilities related to various operators.

# 2) Classes and functions

The classes of the project are Main, Menu, InputForm, BatteryTest, SingleTest, Utilities.

## 2.1 CRC Cards

![Schermata da 2022-01-13 18-23-17](https://user-images.githubusercontent.com/10176197/149379971-31b78de4-6640-4930-b3a0-70345582a14b.png)

![Schermata da 2022-01-13 18-23-17](https://user-images.githubusercontent.com/10176197/149379296-79e419d3-22f1-45c2-b60c-ec5532d1738b.png)

![Schermata da 2022-01-13 18-23-23](https://user-images.githubusercontent.com/10176197/149379319-03dde5c7-4dcb-43f2-88e2-c6c506074dea.png)

![Schermata da 2022-01-13 18-23-28](https://user-images.githubusercontent.com/10176197/149379387-97a65c41-d3ab-4d5e-817a-bb3ec618a9b9.png)

## 2.2 Main functions

![Schermata da 2022-01-13 18-23-50](https://user-images.githubusercontent.com/10176197/149380300-bc45f53a-e785-4d77-b748-cd69de7a3cd9.png)

***firstChoose:*** initial choice, through probability, to generate an unsatisfiable or satisfying concept.

![Schermata da 2022-01-13 18-23-58](https://user-images.githubusercontent.com/10176197/149380325-beb4bff0-db8a-4b35-a3c3-dd16da2a4f04.png)

   ***generateSatisfacibleConcept:*** receiving by parameters the depth of the tree and the probability percentages of each operator type, it generates a concept that randomly, based on the probabilities, recursively the type of operator is chosen to get to the leaves where atomic concepts are generated.

# 3) Testing

## 3.1 Case Test 

The tests carried out:

  - Varying the depth of the tree
  - Varying the number of concepts and role names
  - Varying the probabilities of the operators

# 4) Conclusion

The ***HermiT*** reasoner and the ***JFact*** reasoner both have excellent performance for controlling the satisfaction of a concept.

It is advisable to use Hermit for:

  - Full and balanced tree
  - Few atomic concepts
  - Large size of alphabets
  - For unsatisfiable concepts

While it is convenient to use JFact to:

  - Full shaft with operators: ⱻ , Ɐ
  - Many atomic concepts
  - Small size of alphabets

To conclude, it follows that both are excellent reasoners to use.

# Language program 
  Java 8 
  SDK 5
 
# Resouces
  OwlApi ---> https://github.com/owlcs/owlapi
