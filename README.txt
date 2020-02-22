================Tema 2 POO README==================

Clasa Instance

In clasa Instance vom avea tipul de entitate al instantei, timestamp-ul al care a fost creat,primary key-ul si 
o lista de obiecte in care vom avea stocate atributele.Implementam metode de tip get si set pentru atributele clasei.
Realizam constructorul cu parametri si suprascriem metoda clone din interfata Cloneable (implementata de clasa Instance).
Folosim metoda clone pentru duplicarea instantelor in rf noduri.

Clasa Node

In clasa Node numarul nodului ( de care vom avea nevoie pentru ca vom sorta nodurile), o lista de instante, metode de tip get
precum si constructorul cu parametri.

Clasa Entity

In clasa Entity com avea numele entitatii,factorul de replicare rf,numarul de atribute ,cheia primara precum si 2 liste.
In cele 2 liste vom stoca ,numele atributelor ce apartin entitatii si respectiv tipul atributelor (Intefer Float sau String).
Cele 2 vor fi realizate paralel (atributul cu index ul i din lista attr_name va avea tipul de la index-ul i din lista attr_type.
Avem metode de get si set pentru atributele clasei si constructorul cu parametri.

Clasa DataBase

In clasa DataBase vom avea numele acesteia,numarul de noduri,capacitatea maxima a nodurilor si 2 liste.In lista ent vom salva entitatile 
rezultate dupa comanda Create,iar in nodes vom salva nodurile si continutul acestora. Realizam metode de get si set iar in constructor,
vom si forma nodurile. Facem nodurile goale si le atasam un index de inceput(numarul fiecarui nod).

Clasa Tema2

In metoda main vom incepe prin declararea unei date pentru fixarea timestamp-ului si a unui format pentru afisarea numerelor.
Obtinem numele fisierelor de input si output din linie de comanda apoi declaram un Scanner si un PrintWriter pentru citirea,respectiv scrierea
din/in fisierele respective.Declaram o baza de date ce va fi null la inceput apoi vom citi comentile linie cat linie pan ajungem la sfarsitul fisierului
Vom folosi StringTokenizer pentru parsarea comenzilor.Comanda intreaga o stocam in operation si vom parsa fiecare atribut in op.
Verificam mereu primul cuvant de pe linie.

CREATEDB
In cazul crearii bazei de date,vom citi cu nextToken() fiecare atribut al bazei de date si apelam constructorul clasei.

CREATE
In crearii unei entitati, obtinem cu nextToken() numele ,rf ul si numarul de atribute si convertim la int acolo unde este cazul.
Formam o entitate goala(listele de atribute sunt goale la inceput). Inseram numele atributelor si tipul acestora in listele corespunzatoare din 
clasa Entity.Setam cheia primara ca fiind primul atribut dupa care adaugam entitatea la lista de enitati din baza de date.

INSERT
In cazul inserarii unei instante in baza de date,vom sorta nodurile descrescator dupa nr de instante (pentru a insera in cel mai ocupat nod)
si credcator dupa numarul lor in cazul in acre au un numar de elemente egal.Obtinem tipul instantei ,dupa care gasim ce fel de entitate este instanta noastra.
Formam o instanta cu datele obtinute si cu timestamp ul sistemului de la momentul actual. Am salvat entitatea in e.Vom seta cheia primara o singura data(prima oara), iar apoi vom verifica ce tip de atribut urmeaza sa fie citit
parcurgand lista attr_type din entitatea e.Adaugam la instanta format avalorile atributelor citite si convertite in functie de tipul din entitate.Vom forma rf clone
ale instantei si vom adauga copiile formate in noduri.Vom trece la nodul urmator daca cel actual este plin.

DELETE
Obtinem tipul entitatii si cheia primara a instantei ce trebuie stearsa.Vom cauta in lista de instante a fiecarui nod dupa cheia primara si 
tipul entitatii.Dupa ce am gasit-o o stergem din nodul respectiv.Daca nu gasim nicio instanta afisam mesajul corespunzator.

UPDATE
Obtinem tipul entitatii si cheia primara , dupa care vom cauta in toate nodurile instanta cautata dupa cheie si tipul entitii si salvam instanta
in "in".Cat timp exita atribute de modificat ,citim ce trebuie modificat si valoarea noua. Cautam in lista ded entitati ce tip are atributul pe care
trebie sa il modificam dupa care in functie de acesta ,modificam atributul respectiv din lista de atribute a instantei.
Dupa care vom modifica pozitia instantei modificate in fiecare nod.Vom modifica pozitia astfel incat sa fie cea mai recenta intrare.

GET
Obtinem cheia primara si tipul etitatii si vom afla entitatea in mod asemanator.Vom salva in array numarul nodurilor in care se afla instanta cautata.
si gasim instanta din fiecare nod.Sortam array-ul dupa indecsii nodurilor pentru afisare.Vom afisa numele atributelor din lista de entitati 
si afisam valoarea atributelor respective din lista instantei respective.Afisam mesajul corespunzatro daca nu exista instanta.

SNAPSHOTDB
Vom verifica daca baza de date este goala. Daca nu este, vom afisa nodurile ce nu sunt goale.Vom afisa instantele din noduri astfel:
Cautam numele fiecarui atribut din lista attr_name din entitatea corespunzatoare , il afisam si apoi afisam valoarea atribului din lista de atribute
din instanta noastra.Facem acest lucru pentru fiecare atribut.Afisam EMPTY DB daca aceasta este goala.

CLEANUP 
Obtinem numele bazei de date si timestamp-ul cautat. Vom cauta in instantele din fiecare nod acele instante si verificam care din ele au timestamp-ul
mai mic decat cel cerut si eliminam acele instante.

Vom prinde exceptiile  in functie de caz. Inchidem fisierul de output inainte de a prinde exceptiile si iesim din program. 
