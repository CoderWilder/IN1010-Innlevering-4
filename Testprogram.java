// Programmer tester diverse fra oppgaven 

public class Testprogram {
    public static void main(String[] args) {
        
        Legesystem test = new Legesystem(); 
        test.lesFraFil("legedata.txt"); 
        /*System.out.println("\n-----Test av pasientliste-----\n");
        System.out.println(test.pasientListe);

        System.out.println("\n-----Test av legemiddelliste-----\n");
        System.out.println(test.legemiddelListe);
        
        System.out.println("\n-----Test av legeliste-----\n");
        System.out.println(test.legeListe);

        System.out.println("\n-----Test av legeliste - Utskrevne resepter-----\n");
        for (Lege lege : test.legeListe) {
            System.out.println(lege.utskrevneResepter);
        }*/

        test.skrivUt();
    }
}
