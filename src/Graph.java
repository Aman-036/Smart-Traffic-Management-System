import java.util.*;

class Graph {
    Map<String, List<Edge>> adjList = new HashMap<>();

    void addEdge(String src, String dest, int weight) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());

        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight)); 
    }
    void updateTraffic(String u, String v, int newWeight) {
        for (Edge e : adjList.get(u)) {
            if (e.destination.equals(v)) {
                e.weight = newWeight;
            }
        }
        for (Edge e : adjList.get(v)) {
            if (e.destination.equals(u)) {
                e.weight = newWeight;
            }
        }
    }
}
