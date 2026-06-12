package coreo;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public final class ToScanner {

    private ToScanner() {}

    // --- Find ALL annotated fields ---
    public static List<Field> findAnnotatedFields(Class<?> type) {
        return Arrays.stream(getAllFields(type))
                .filter(f -> f.isAnnotationPresent(To.class))
                .collect(Collectors.toList());
    }

    // --- Find ALL annotated methods ---
    public static List<Method> findAnnotatedMethods(Class<?> type) {
        return Arrays.stream(getAllMethods(type))
                .filter(m -> m.isAnnotationPresent(To.class))
                .collect(Collectors.toList());
    }

    // --- Find annotated fields for specific destination ---
    public static List<Field> findFieldsForDestination(
            Class<?> type, Class<? extends Destination> dest) {

        return findAnnotatedFields(type).stream()
                .filter(f -> matchesDestination(f.getAnnotation(To.class), dest))
                .collect(Collectors.toList());
    }

    // --- Find annotated methods for specific destination ---
    public static List<Method> findMethodsForDestination(
            Class<?> type, Class<? extends Destination> dest) {

        return findAnnotatedMethods(type).stream()
                .filter(m -> matchesDestination(m.getAnnotation(To.class), dest))
                .collect(Collectors.toList());
    }

    // --- Generic: return both fields and methods ---
    public static List<AnnotatedElement> findAllAnnotatedMembers(Class<?> type) {
        List<AnnotatedElement> result = new ArrayList<>();
        result.addAll(findAnnotatedFields(type));
        result.addAll(findAnnotatedMethods(type));
        return result;
    }

    // --- Helper: match destination ---
    private static boolean matchesDestination(To to,
                                              Class<? extends Destination> dest) {
        return to.dest().equals(dest);
    }

    // --- Traverse class hierarchy for fields ---
    private static Field[] getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields.toArray(new Field[0]);
    }

    // --- Traverse class hierarchy for methods ---
    private static Method[] getAllMethods(Class<?> type) {
        List<Method> methods = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            methods.addAll(Arrays.asList(c.getDeclaredMethods()));
        }
        return methods.toArray(new Method[0]);
    }
}