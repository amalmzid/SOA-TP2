# TP2 SOA – CalculatriceWS

## Énoncé
**CalculatriceWS** est une application Java qui expose un **service web SOAP**. Elle permet de faire des opérations simples : **somme, multiplication, soustraction et division**.


---

# Travail demandé

## Activité 1 : Configuration du projet Maven


### 1. Création du projet Maven
On ajoute les dépendances dans **pom.xml**. Maven les télécharge automatiquement.

![Dépendances du pom.xml](captures/Capture%20d%27%C3%A9cran%202026-10-29%20222115.png)

| Dépendance | Rôle |
|---|---|
| **jakarta.xml.ws-api** 4.0.2 | Les interfaces, comme **Endpoint** (qui sert à publier le service) |
| **jaxws-rt** 4.0.3 | L'implémentation : le moteur qui exécute le service |
| **jakarta.jws-api** 3.0.0 | Les annotations **@WebService**, **@WebMethod**, **@WebParam** |



### 2. Création des packages
Dans **src/main/java** :
- **service** : l'interface **ICalculatrice** et la classe **Calculatrice** ;
- **serveur** : la classe **ServeurJWS**.

---

## Activité 2 : Création de la classe métier
La classe **Calculatrice** **implémente** l'interface **ICalculatrice** : l'interface **déclare** les méthodes, la classe **écrit leur code**.

![Classe Calculatrice](captures/Capture%20d%27%C3%A9cran%202026-10-29%20222044.png)

- Chaque méthode contient un calcul simple en Java (**a + b**, **a - b**…).
- **division** lance une exception si **b == 0**. Le client reçoit alors une erreur SOAP.
- **endpointInterface** Relie la classe à son interface (le contrat).
- **portName** Noms du service et du point d'accès dans le WSDL.

---

## Activité 3 : Annotations avec Jakarta XML Web Services

![Interface ICalculatrice](captures/Capture%20d%27%C3%A9cran%202026-10-29%20222053.png)

### 1. **@WebService** sur l'interface et la classe
| Paramètre | Rôle |
|---|---|
| **name** | Nom du service dans le WSDL |
| **targetNamespace** | Namespace XML du service |


### 2. **@WebMethod** sur les méthodes
**@WebMethod(operationName = "somme")** : la méthode devient une **opération** qu'on peut appeler à distance.

### 3. **@WebParam** sur les paramètres
**@WebParam(name = "a")** : le paramètre s'appelle **\<a\>** dans le XML.

---

## Activité 4 : Publication du service web

### 1. Classe **ServeurJWS** dans le package **serveur**

![Classe ServeurJWS](captures/Capture%20d%27%C3%A9cran%202026-10-29%20222033.png)

### 2. Publication avec **Endpoint.publish**
**Endpoint.publish(URL, new Calculatrice())** démarre un petit serveur web sur le port **8585**, génère le WSDL et attend les requêtes.

**Lancer le serveur :** cliquez sur **Run** au-dessus de **main**.

Le terminal confirme la publication :

![Démarrage du serveur](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214748.png)

---

## Activité 5 : Test avec SoapUI


### 1. Configuration de SoapUI
Lancez SoapUI, puis faites **File → New SOAP Project**, avec **Project Name** = **tp2_soa_calculatrice**.

### 2. Importation du WSDL

![Nouveau projet SoapUI](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214133.png)

SoapUI lit le WSDL et affiche les 4 opérations du service :

![Opérations importées](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214251.png)

### 3. Test de l'opération choisie (**somme**)

![Requête générée](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214437.png)


![Requête somme avec valeurs](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214459.png)

### 4. Exécution de la demande

![Réponse somme](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214518.png)

le test est réussi.

---

### Soustraction :
| Requête | Réponse |
|---|---|
| ![Requête soustraction](captures/Capture%20d%27%C3%A9cran%202026-10-29%20215010.png) | ![Réponse soustraction](captures/Capture%20d%27%C3%A9cran%202026-10-29%20215018.png) |

### Multiplication :
| Requête | Réponse |
|---|---|
| ![Requête multiplication](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214933.png) | ![Réponse multiplication](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214945.png) |

### Division :
| Requête | Réponse |
|---|---|
| ![Requête division](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214607.png) | ![Réponse division](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214621.png) |

### Division par zéro
Avec **b = 0**, la méthode lance une **ArithmeticException**. Le serveur l'affiche dans la console et renvoie un **SOAP Fault** au client :

![Erreur division par zéro](captures/Capture%20d%27%C3%A9cran%202026-10-29%20214755.png)
