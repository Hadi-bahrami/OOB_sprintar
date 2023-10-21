package sprint2.inlämning2;

import javax.naming.InvalidNameException;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class BestGymEver {

    public BestGymEver() {
    }
    protected File file = new File("src/sprint2/inlämning2/kundData.txt");
    protected String kundDatafil = "src/sprint2/inlämning2/kundData.txt";
    protected String förstaRaden;
    protected String andraRaden;
    protected String personnr;
    protected String namn;
    protected LocalDate datum;
    protected ArrayList<Kunder> kunderList = new ArrayList<>();



    // Vi kollar om personen är medlem och därefter loggar vi varje gång dem tränar i egen fil.
    public void isAtGym(){
        System.out.println("Välkommen till gymmet, vad heter du?");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if (name == null || name.isEmpty()){
            throw new NullPointerException("Namn kan inte vara null/tomt");
        }
        String namnLowerCase = name.toLowerCase().replace(" ", "_");
        try (scanner){

            boolean isMember = false;
            for (Kunder k : kunderList) {
                if (k.getName().contains(name)){
                    isMember = true;
                    if (gymMemberDate(k.getDatum().toString()).contains("nuvarande")){
                        File kundUppföljningFile = new File("src/sprint2/inlämning2/kunduppföljning/" +namnLowerCase + "_" + k.getPersonnr() + ".txt");
                        BufferedWriter bw = new BufferedWriter(new FileWriter(kundUppföljningFile, true));
                        if (kundUppföljningFile.exists()){
                            bw.write( k.getName() + " " + k.getPersonnr() + " tränade: " + LocalDate.now() + "\n");
                            System.out.println(k.getName().trim()  + "s träningslogg uppdaterat");
                            bw.close();
                            break;
                        }else{
                            boolean a = kundUppföljningFile.createNewFile();
                            if (a){
                                bw.write( k.getName() + " " + k.getPersonnr() + " tränade: " + LocalDate.now() + "\n");
                                System.out.println(k.getName() + "s träningslogg uppdaterat");
                                bw.close();
                                break;
                            }
                        }
                    }

                }
            }
            if (!isMember) {
                System.out.println("Personen finns inte i filen och har sålunda aldrig varit medlem och är obehörig.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            System.out.println("Det får inte vara tomt/null");
        }catch (IllegalArgumentException e){
            System.out.println("Använd bokstäver och inga andra tecken");
        }
    }

    // de två metoderna nedan kontrollerar om texten som söks i searchInList är text eller siffror.
    private boolean containsText(String string){
        return string.matches("[a-zA-ZÅÄÖåäö ]+");
    }
    private boolean containsNumber(String string){
        return string.matches("\\d+");
    }

    //Våran sökfunktion. Vi söker på antingen namn eller personnr.
    public void searchInList(){
        System.out.println("Ange namn eller personnummer.");
        Scanner scanner = new Scanner(System.in);
        String namnEllerPersonnr;
        try (scanner){
            namnEllerPersonnr = scanner.nextLine().toLowerCase().trim();
            if (containsText(namnEllerPersonnr)){
                gymMemberText(namnEllerPersonnr);

            }else if (containsNumber(namnEllerPersonnr)){
                if (namnEllerPersonnr.length() == 10){
                    gymMemberText(namnEllerPersonnr);
                }else {
                    throw new InvalidNameException("Personnummret måste vara 10 siffrig utan tecken.");
                }
            }
        } catch (Exception e) {
            System.out.println("Försök igen!");
            e.printStackTrace();
        }

    }

    // Vi kontrollerar om kunden är medlem eller föredetta medlem med hjälp av datumet som vi ger när vi anropar metoden.
    private String gymMemberDate(String date) {

        LocalDate customersDate = LocalDate.parse(date);
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearAgo = currentDate.minusYears(1);
        try {
            if (customersDate.isEqual(oneYearAgo) || customersDate.isAfter(oneYearAgo)){
                System.out.println("Kunden är en nuvarande medlem.");
                return "Kunden är en nuvarande medlem.";
            } else {
                System.out.println("Kunden är en FÖRE DETTA kund.");
                return "Kunden är en FÖRE DETTA kund.";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void gymMemberText(String namn) {
        try {
            namn = namn.toLowerCase();
            boolean b = false;
            for (Kunder k : kunderList) {
                if (k.getName().toLowerCase().contains(namn)) {
                    gymMemberDate(k.getDatum().toString());
                    b = true;
                    break;
                }else if (k.getPersonnr().toLowerCase().contains(namn)){
                    gymMemberDate(k.getDatum().toString());
                    b = true;
                    break;
                }
            }
            if (!b) {
                System.out.println("Personen finns inte i filen och har sålunda aldrig varit medlem och är obehörig.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Vi läser från filen och lagrar data i en lista med klassobjekt.
    public void readFromFile(String kundDatafil){
        if (kundDatafil == null){
            throw new NullPointerException("Filvägen kan ej vara null");
        }
        try (Scanner scanner = new Scanner(new FileReader(kundDatafil))){
            while (scanner.hasNext()){
                förstaRaden = scanner.nextLine().trim();
                String[]förstaRadenArray = förstaRaden.split(",");
                personnr = förstaRadenArray[0];
                namn = förstaRadenArray[1];
                if (scanner.hasNext()){
                    andraRaden = scanner.nextLine().trim();
                    datum  = LocalDate.parse(andraRaden);
                }
                Kunder kunder = new Kunder(namn,personnr,datum);
                kunderList.add(kunder);
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

}
