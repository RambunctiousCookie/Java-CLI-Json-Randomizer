package unityCLI.Helper;

import com.google.gson.JsonObject;

import java.util.*;

public enum GroupJsonCreator {
    /**
     * Enum Singleton
     **/
    INSTANCE;

    /**
     * Final Variables
     **/
    private final JsonObject nestedJson = new JsonObject();
    private final Scanner scanner = new Scanner(System.in);
    private final Random rand = new Random();

    /**
     * Person Variables
     **/
    private Map<String, Integer> personVariableMap;
    //process- Cannot have these re-randomize in Unity, configure here- unless connect to Unity
    //maybe make configurable in the ui??

    // Private constructor for initialization
    private GroupJsonCreator() {
        personVariableMap = new HashMap<>();
        Set<Class<? extends Enum>> enumClasses = ClassHelper.getDocValues();

        for (Class<? extends Enum> enumClass : enumClasses) {
            Enum[] enumConstants = enumClass.getEnumConstants();
            personVariableMap.put(
                    ClassHelper.getClassName(enumClass),
                    enumConstants.length);
        }
        //https://chat.openai.com/share/8262c4ce-25cb-4070-9937-aa5e5dee3d4a
    }

    public JsonObject getNestedJson() {
        return nestedJson;
    }

    public Map<String, Integer> getPersonVariableMap() {
        return personVariableMap;
    }


    public void addGroup(String group) {
        nestedJson.add(group, new JsonObject());
    }

    public void addPerson(String group, String person) {
        JsonObject personJson = new JsonObject();
        nestedJson.getAsJsonObject(group).add(person, personJson);
    }

    public void initializePersonAttributesRandomly() {
        for (String groupKey : nestedJson.keySet()) {
            JsonObject group = nestedJson.getAsJsonObject(groupKey);
            for (String personKey : group.keySet()) {
                JsonObject person = group.getAsJsonObject(personKey);

                for (String attributeKey : personVariableMap.keySet())
                    person.addProperty(attributeKey, rand.nextInt(personVariableMap.get(attributeKey)));
            }
        }
    }

    public void initializePersonAttributesToZero() {
        for (String groupKey : nestedJson.keySet()) {
            JsonObject group = nestedJson.getAsJsonObject(groupKey);
            for (String personKey : group.keySet()) {
                JsonObject person = group.getAsJsonObject(personKey);

                for (String attributeKey : personVariableMap.keySet())
                    person.addProperty(attributeKey, 0);
            }
        }
    }

    public void changePersonAttributesCustom(JsonObject jsonObject, int action, int personType) {
    }

    //UTILITY METHODS--- --- ---

    public int getValidIntegerInput(String prompt) {
        //ONLY POSITIVE INT ACCEPTED
        int value;
        System.out.print(prompt);

        while (!scanner.hasNextInt()) {
            scanner.next();

            System.out.print("\tInvalid input. " + prompt);
        }

        value = scanner.nextInt();

        while (value <= 0) {
            System.out.println("\tInvalid input. Please enter a positive integer.");
            value = getValidIntegerInput(prompt);   //recursion
        }

        return value;
    }

    public int getValidIntegerInput(String prompt, int upperLimit) {
        //ONLY POSITIVE INT ACCEPTED
        int value;
        System.out.print(prompt);

        while (!scanner.hasNextInt()) {
            scanner.next();

            System.out.print("\tInvalid input. " + prompt);
        }

        value = scanner.nextInt();

        while (value <= 0 || value > upperLimit) {
            System.out.println(String.format("\tInvalid input. Please enter a positive integer between 0 and %d", upperLimit));
            value = getValidIntegerInput(prompt, upperLimit);   //recursion
        }

        return value;
    }

    public boolean getUserYesNo(String prompt) {
        String input;
        System.out.print(prompt);
        while (true) {
            input = scanner.next();
            if (input.equalsIgnoreCase("yes"))
                return true;
            else if (input.equalsIgnoreCase("no"))
                return false;
            System.out.print("\tInvalid input. " + prompt);
        }
    }

    public static void printGroupInfo(List<Integer> groupPersonCount) {
        for (int i = 0; i < groupPersonCount.size(); ++i) {
            System.out.println("Group " + (i + 1) + " has " + groupPersonCount.get(i) + " people.");
        }
    }

    public static int generateBiasedRandom(int biasedValue, double biasProbability) {
        Random rand = new Random();
        double randomValue = rand.nextDouble(); // Generate a random value between 0.0 (inclusive) and 1.0 (exclusive)

        if (randomValue < biasProbability) {
            // Bias towards the specified value
            return biasedValue;
        } else {
            // Generate a random value within the range [0, 5) for demonstration purposes
            return rand.nextInt(5);
        }
    }
}
