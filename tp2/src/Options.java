import java.util.Arrays;

public class Options {

    public static String pathToDictionary = "resources/dico.txt";
    public static String pathToFile = "resources/fautes.txt";
    public static String word = "ababab";

    public static void parseCommandLine(String[] args) {

        if (args.length == 0) {
            return;
        }

        int index = 0;
        String[] helpStrs = {"-h", "--help"};
        if (Arrays.asList(helpStrs).contains(args[index])) {
            usage();
            System.exit(0);
        }

        String[] dictStrs = {"-d", "--dict", "--dico"};
        if (Arrays.asList(dictStrs).contains(args[index])) {
            index++;
            try {
                pathToDictionary = args[index++];
            } catch (Exception e) {
                System.out.println("I need the name of the file containing the dictionary");
                System.exit(1);
            }

        }

        if (args.length > index) {
            pathToFile = args[index];
            word = args[index];
        }
    }

    private static void usage() {
        System.out.println("Usage : java -jar Spell.jar [-d dictionary] [file|word]");
    }


    public static void print() {
        System.out.println(String.format("Dictionnaire : %s", Options.pathToDictionary));
        System.out.println(String.format("Fichier : %s", Options.pathToFile));
        System.out.println(String.format("Mot : %s", Options.word));
    }

    public static void reset() {
        pathToDictionary = "resources/dico.txt";
        pathToFile = "resources/fautes.txt";
        word = "ababab";
    }

}
