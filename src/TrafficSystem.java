import java.util.*;

public class TrafficSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph();
        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        for (int i = 0; i < edges; i++) {
            System.out.print("Enter source destination weight: ");
            String u = sc.next();
            String v = sc.next();
            int w = sc.nextInt();

            graph.addEdge(u, v, w);
        }
        System.out.print("\nEnter source node: ");
        String source = sc.next();
        Map<String, Integer> dist = Dijkstra.shortestPath(graph, source);

        System.out.println("\nShortest distances from " + source + ":");
        for (String node : dist.keySet()) {
            System.out.println(node + " -> " + dist.get(node));
        }

        System.out.print("\nDo you want to update traffic? (yes/no): ");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter edge to update (u v newWeight): ");
            String u = sc.next();
            String v = sc.next();
            int newWeight = sc.nextInt();
            graph.updateTraffic(u, v, newWeight);
            System.out.println("\nTraffic updated successfully!");
            Map<String, Integer> updatedDist = Dijkstra.shortestPath(graph, source);

            System.out.println("\nAfter traffic update:");
            for (String node : updatedDist.keySet()) {
                System.out.println(node + " -> " + updatedDist.get(node));
            }
        }

        sc.close();
    }
}
