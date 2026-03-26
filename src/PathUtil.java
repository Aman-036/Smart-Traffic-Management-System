import java.util.*;

class PathUtil {

    public static List<String> getPath(Map<String, String> parent, String dest) {
        List<String> path = new ArrayList<>();

        while (dest != null) {
            path.add(dest);
            dest = parent.get(dest);
        }

        Collections.reverse(path);
        return path;
    }
}
