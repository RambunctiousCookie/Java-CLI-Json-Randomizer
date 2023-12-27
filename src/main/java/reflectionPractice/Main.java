package reflectionPractice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import reflectionPractice.Helper.EnumDocHelper;
import reflectionPractice.Helper.GroupJsonCreator;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    //TODO: redistribute/randomize the character placement on unity side
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        //ClassHelper.getDocValues();
        EnumDocHelper.writeEnumDocs();

        String filePath = "RandomizerExample.json";

        int numberOfGroups = GroupJsonCreator.INSTANCE.getValidIntegerInput("Enter the number of groups: ");   //No upper limit for now
        for (int i = 0; i < numberOfGroups; i++) {
            String currGroupName = String.format("Group_%d", i+1);
            GroupJsonCreator.INSTANCE.addGroup(currGroupName);

            String formattedString = String.format("Enter the number of people for Group_%d: ", i+1);
            int numberOfPeople = GroupJsonCreator.INSTANCE.getValidIntegerInput(formattedString, 3);

            for (int j=0; j < numberOfPeople; j++){
                GroupJsonCreator.INSTANCE.addPerson(currGroupName, String.format("Person_%d", j+1));
            }
        }

        System.out.println("\nGroup Template Initialized.\n");
        boolean randomizeData = GroupJsonCreator.INSTANCE.getUserYesNo("Would you like to randomize the data? NO means all values will be assigned to 0 (assign variables manually)\nEnter YES or NO: ");

        if (randomizeData){
            GroupJsonCreator.INSTANCE.initializePersonAttributesRandomly();
        }
        else{
            GroupJsonCreator.INSTANCE.initializePersonAttributesToZero();
            //personConfiguration();
        }
        System.out.println(gson.toJson(GroupJsonCreator.INSTANCE.getNestedJson()));
        writeJsonFile(filePath, GroupJsonCreator.INSTANCE.getNestedJson());
        System.out.println("Check the relevant documentation for variables to update.");

    }

    // TODO: Check how to bias (check total to 100%?) autofill the rest?
    //  C:\ws\_selfnotes-GH\Thales-Unity\Task-Unity-Script\process bias concept.txt
    private static void personConfiguration(){

    }

    private static void writeJsonFile(String filePath ,JsonObject jsonObject){
        /**not pretty printed**/
//        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(filePath))) {
//            // Use the Gson instance to write the JsonObject to the file
//            gson.toJson(jsonObject, jsonWriter);
//
//            System.out.println("JSON written to file: " + filePath);
//        }
        /**pretty printed**/
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(jsonObject, fileWriter);
            System.out.println("JSON written to file successfully at: "+ filePath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}