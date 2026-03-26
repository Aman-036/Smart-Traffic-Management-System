import java.util.*;

public class TrafficSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph();

        // 🔹 Dynamic Graph Input
        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        for (int i = 0; i < edges; i++) {
            System.out.print("Enter source destination weight: ");
            String u = sc.next();
            String v = sc.next();
            int w = sc.nextInt();

            graph.addEdge(u, v, w);
        }

        // 🔹 Source input
        System.out.print("\nEnter source node: ");
        String source = sc.next();

        // 🔹 Run Dijkstra
        Map<String, Integer> dist = Dijkstra.shortestPath(graph, source);

        System.out.println("\nShortest distances from " + source + ":");
        for (String node : dist.keySet()) {
            System.out.println(node + " -> " + dist.get(node));
        }

        // 🔹 Dynamic Traffic Update
        System.out.print("\nDo you want to update traffic? (yes/no): ");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("yes")) {

            System.out.print("Enter edge to update (u v newWeight): ");
            String u = sc.next();
            String v = sc.next();
            int newWeight = sc.nextInt();

            // Update weight
            for (Edge e : graph.adjList.get(u)) {
                if (e.destination.equals(v)) {
                    e.weight = newWeight;
                }
            }

            // Re-run Dijkstra
            dist = Dijkstra.shortestPath(graph, source);

            System.out.println("\nAfter traffic update:");
            for (String node : dist.keySet()) {
                System.out.println(node + " -> " + dist.get(node));
            }
        }

        sc.close();
    }
}
