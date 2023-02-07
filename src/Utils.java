import java.nio.file.Paths;

public class Utils {
    public static String addCurrentWorkingDirectory(final String path) {
        final String currentWorkingDirectory = System.getProperty("user.dir");
        return Paths.get(currentWorkingDirectory, path).toString();
    }

    public static String trimAnnotation(final Object annotation) {
        final String str = annotation.toString();
        final int idx = str.indexOf(": ");
        if (idx == -1)
            return str;
        return str.substring(idx + 2);
    }
}
