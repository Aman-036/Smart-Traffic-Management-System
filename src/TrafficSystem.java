public class TrafficSystem {

    public static void main(String[] args) {

        Graph graph = new Graph();

        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "D", 2);
        graph.addEdge("C", "D", 4);

        // Dijkstra
        Map<String, Integer> dist = Dijkstra.shortestPath(graph, "A");

        System.out.println("Shortest distances from A:");
        for (String node : dist.keySet()) {
            System.out.println(node + " -> " + dist.get(node));
        }

        // Simulate traffic update
        System.out.println("\nUpdating traffic...");
        for (Edge e : graph.adjList.get("A")) {
            if (e.destination.equals("B")) {
                e.weight = 10; // congestion increased
            }
        }

        dist = Dijkstra.shortestPath(graph, "A");

        System.out.println("\nAfter traffic update:");
        for (String node : dist.keySet()) {
            System.out.println(node + " -> " + dist.get(node));
        }
    }
}
