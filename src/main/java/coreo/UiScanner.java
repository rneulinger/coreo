package coreo;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public final class UiScanner {

    private UiScanner() {}

    // --- Find ALL annotated fields ---
    public static List<Field> findAnnotatedFields(Class<?> type) {
        return Arrays.stream(getAllFields(type))
                .filter(f -> f.isAnnotationPresent(Ui.class))
                .collect(Collectors.toList());
    }

    // --- Find ALL annotated methods ---
    public static List<Method> findAnnotatedMethods(Class<?> type) {
        return Arrays.stream(getAllMethods(type))
                .filter(m -> m.isAnnotationPresent(Ui.class))
                .collect(Collectors.toList());
    }

    // --- Find ALL annotated members (fields + methods) ---
    public static List<AnnotatedElement> findAllAnnotatedMembers(Class<?> type) {
        List<AnnotatedElement> result = new ArrayList<>();
        result.addAll(findAnnotatedFields(type));
        result.addAll(findAnnotatedMethods(type));
        return result;
    }

    // --- Filter fields by Ui value ---
    public static List<Field> findFieldsByValue(Class<?> type, String value) {
        return findAnnotatedFields(type).stream()
                .filter(f -> f.getAnnotation(Ui.class).value().equals(value))
                .collect(Collectors.toList());
    }

    // --- Filter methods by Ui value ---
    public static List<Method> findMethodsByValue(Class<?> type, String value) {
        return findAnnotatedMethods(type).stream()
                .filter(m -> m.getAnnotation(Ui.class).value().equals(value))
                .collect(Collectors.toList());
    }

    // --- Generic filter (fields + methods) ---
    public static List<AnnotatedElement> findMembersByValue(Class<?> type, String value) {
        return findAllAnnotatedMembers(type).stream()
                .filter(e -> e.getAnnotation(Ui.class).value().equals(value))
                .collect(Collectors.toList());
    }

    // --- Group fields by Ui value ---
    public static Map<String, List<Field>> groupFieldsByValue(Class<?> type) {
        return findAnnotatedFields(type).stream()
                .collect(Collectors.groupingBy(f -> f.getAnnotation(Ui.class).value()));
    }

    // --- Group methods by Ui value ---
    public static Map<String, List<Method>> groupMethodsByValue(Class<?> type) {
        return findAnnotatedMethods(type).stream()
                .collect(Collectors.groupingBy(m -> m.getAnnotation(Ui.class).value()));
    }

    // --- Group ALL members (fields + methods) by value ---
    public static Map<String, List<AnnotatedElement>> groupAllByValue(Class<?> type) {
        return findAllAnnotatedMembers(type).stream()
                .collect(Collectors.groupingBy(e -> e.getAnnotation(Ui.class).value()));
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