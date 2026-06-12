package coreo;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public final class OverloadScanner {

    private OverloadScanner() {}

    // --- Find ALL annotated fields ---
    public static List<Field> findAnnotatedFields(Class<?> type) {
        return Arrays.stream(getAllFields(type))
                .filter(f -> f.isAnnotationPresent(Overload.class))
                .collect(Collectors.toList());
    }

    // --- Find ALL annotated methods ---
    public static List<Method> findAnnotatedMethods(Class<?> type) {
        return Arrays.stream(getAllMethods(type))
                .filter(m -> m.isAnnotationPresent(Overload.class))
                .collect(Collectors.toList());
    }

    // --- Find ALL annotated members (fields + methods) ---
    public static List<AnnotatedElement> findAllAnnotatedMembers(Class<?> type) {
        List<AnnotatedElement> result = new ArrayList<>();
        result.addAll(findAnnotatedFields(type));
        result.addAll(findAnnotatedMethods(type));
        return result;
    }

    // --- Convenience: check if any @Overload exists ---
    public static boolean hasOverloads(Class<?> type) {
        return !findAllAnnotatedMembers(type).isEmpty();
    }

    // --- Helpers (include inheritance) ---
    private static Field[] getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields.toArray(new Field[0]);
    }

    private static Method[] getAllMethods(Class<?> type) {
        List<Method> methods = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            methods.addAll(Arrays.asList(c.getDeclaredMethods()));
        }
        return methods.toArray(new Method[0]);
    }
}
