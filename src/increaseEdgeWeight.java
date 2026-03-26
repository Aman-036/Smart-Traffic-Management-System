void increaseEdgeWeight(Graph graph, String u, String v, int increase) {
    for (Edge e : graph.adjList.get(u)) {
        if (e.destination.equals(v)) {
            e.weight += increase;
        }
    }
}
