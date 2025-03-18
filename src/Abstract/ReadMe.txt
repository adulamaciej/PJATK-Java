Define the abstract class Singer. Each singer has a name and a starting number assigned
automatically when the object is created. The Singer class should have, among other things, the Singer(
String name) constructor and the following methods:
• abstract: abstract String sing(), which ultimately returns the lyrics sung by the singer
in the competition.
• public String toString() returning information about the singer.
• static:... najglosniej(...) accepting an array of objects/singers and returning the object/
singer whose lyrics contain the most capital letters (see the
singer() method).
The Singer class should be created in such a way that the following main method from the Main class:
1 public class Main {
2 public static void main ( String [ ] args ) {
3 Singer s1 = new Singer ( "Eminem" ) { /∗<− code ∗/ } ; 4 Spiewak s2 = new Spiewak ( "Eagles" ) { /∗<− code ∗/ } ;
5 Spiewak s3 = new Spiewak ( "Dzem" ) { /∗<− code ∗/ } ;
6 Spiewak sp [ ] = {s1 , s2 , s3 } ;
7 for ( Spiewak s : sp ) System . out . println ( s ) ;
8 System . out . println ( "\n" + Spiewak . most loudly ( sp ) ) ; 9 }
10 }
derived feedback as below:
Spiewak{id=0, name=’Eminem’}
Spiewak{id=1, name=’Eagles’}
Spiewak{id=2, name=’Dżem’}
Spiewak{id=1, name=’Eagles’}