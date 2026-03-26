class Graph {
    Map<String, List<Edge>> adjList = new HashMap<>();

    void addEdge(String src, String dest, int weight) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());

        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight)); 
    }
}
