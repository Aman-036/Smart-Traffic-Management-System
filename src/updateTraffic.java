void updateTraffic(Graph graph, String u, String v, int newWeight) {
    for (Edge e : graph.adjList.get(u)) {
        if (e.destination.equals(v)) {
            e.weight = newWeight;
        }
    }
}
