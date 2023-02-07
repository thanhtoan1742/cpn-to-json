import java.io.File;
import java.io.FileWriter;

import org.cpntools.accesscpn.model.PetriNet;
import org.cpntools.accesscpn.model.importer.DOMParser;
import org.cpntools.accesscpn.model.importer.NetDeclarationException;

public class Converter {
    public static void main(String[] args) {
        final String inFileName = args.length > 0 ? args[0] : Utils.addCurrentWorkingDirectory("data\\case3_1.cpn");
        final File inFile = new File(inFileName);
        try {
            final PetriNet petriNet = DOMParser.parse(inFile.toURI().toURL());
            final String jsonString = ProcessedJSONMarshaller.marshal(petriNet);
            final String outFileName = args.length > 1 ? args[1]
                    : inFile.getParent().concat("\\").concat(inFile.getName()).concat(".json");
            final FileWriter fileWriter = new FileWriter(outFileName);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (NetDeclarationException e) {
            System.out.println("remove real decl in cpn file");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
