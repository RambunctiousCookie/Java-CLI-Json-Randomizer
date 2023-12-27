package reflectionPractice.Helper;

import org.reflections.Reflections;

import java.util.Set;

public class ClassHelper {
    private static String packageName = "reflectionPractice.PersonValues";
    //TODO: figure out a way to change this programmatically
    private static Reflections reflections = new Reflections(packageName);

    public static String getClassName(Class<?> clazz){
        String classname = clazz.getName();
        int lastIndex = classname.lastIndexOf('.');
        return classname.substring(lastIndex + 1);
    }

    public static Set<Class<? extends Enum>> getDocValues(){
        Set<Class<? extends Enum>> enumClasses = reflections.getSubTypesOf(Enum.class);
//        for (Class<? extends Enum> enumClass : enumClasses) {
//            System.out.println("Found enum class: " + enumClass.getName());
//        }
        return enumClasses;
    }
}
