import java.util.*;

public class TrafficSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph();

        // Input graph
        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        for (int i = 0; i < edges; i++) {
            System.out.print("Enter source destination weight: ");
            String u = sc.next();
            String v = sc.next();
            int w = sc.nextInt();
            graph.addEdge(u, v, w);
        }

        // Source & destination
        System.out.print("\nEnter source: ");
        String source = sc.next();

        System.out.print("Enter destination: ");
        String dest = sc.next();

        // Dijkstra
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> dist = Dijkstra.shortestPath(graph, source, parent);

        System.out.println("\nShortest distance: " + dist.get(dest));

        List<String> path = PathUtil.getPath(parent, dest);
        System.out.println("Path: " + String.join(" -> ", path));

        // Traffic update
        System.out.print("\nUpdate traffic?: ");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("yes")) {

            System.out.print("Enter edge: ");
            String u = sc.next();
            String v = sc.next();
            int newWeight = sc.nextInt();

            graph.updateTraffic(u, v, newWeight);

            // Re-run Dijkstra
            parent.clear();
            dist = Dijkstra.shortestPath(graph, source, parent);

            System.out.println("\nAfter traffic update:");
            System.out.println("New distance: " + dist.get(dest));

            List<String> newPath = PathUtil.getPath(parent, dest);
            System.out.println("New Path: " + String.join(" -> ", newPath));
        }

        sc.close();
    }
}
