package reflectionPractice.Helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class EnumDocHelper {
    private static String filePath = "enum_mapping_doc.txt";

    public static void writeEnumDocs(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Set<Class<? extends Enum>> enumClasses = ClassHelper.getDocValues();

            writer.write("**Enum Mapping Information**\n");

            for (Class<? extends Enum> enumClass : enumClasses) {
                writer.write("\t"+ClassHelper.getClassName(enumClass)+"\n");
                Enum[] enumConstants = enumClass.getEnumConstants();
                for (Enum enumeratedValue : enumConstants) {
                    writer.write("\t\t" +enumeratedValue.ordinal() + " represents " + enumeratedValue.name() + "\n");
                }
            }

//            writer.write("\t"+ClassHelper.getClassName(PersonType.class)+"\n");
//            for (PersonType personType : PersonType.values()) {
//                // Write mapping information
//                writer.write("\t\t" +personType.ordinal() + " represents " + personType.name() + "\n");
//            }

            System.out.println("Documentation/Enum mapping information has been written to: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
