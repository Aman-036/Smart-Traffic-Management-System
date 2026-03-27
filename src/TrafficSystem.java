import java.util.*;

public class TrafficSystem {

    static final int INF = 99999;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Graph graph = new Graph();

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        int[][] dist = new int[V][V];
        int[][] next = new int[V][V];

        
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = INF;

                next[i][j] = -1;
            }
        }

        Map<String, Integer> nodeIndex = new HashMap<>();
        int index = 0;
        boolean hasNegative = false;

        
        for (int i = 0; i < edges; i++) {

            System.out.print("Enter source destination weight: ");
            String u = sc.next();
            String v = sc.next();
            int w = sc.nextInt();

            graph.addEdge(u, v, w);

            if (!nodeIndex.containsKey(u)) nodeIndex.put(u, index++);
            if (!nodeIndex.containsKey(v)) nodeIndex.put(v, index++);

            int ui = nodeIndex.get(u);
            int vi = nodeIndex.get(v);

            dist[ui][vi] = w;
            dist[vi][ui] = w;

            next[ui][vi] = vi;
            next[vi][ui] = ui;

            if (w < 0) hasNegative = true;
        }

        
        if (hasNegative) {

            System.out.println("\n Negative edge detected → Using Floyd-Warshall");

            floydWarshall(dist, next, V);

            
            for (int i = 0; i < V; i++) {
                if (dist[i][i] < 0) {
                    System.out.println("Negative cycle detected! Paths not valid.");
                    return;
                }
            }

            
            System.out.println("\nShortest Paths:");

            for (String u : nodeIndex.keySet()) {
                for (String v : nodeIndex.keySet()) {

                    int i = nodeIndex.get(u);
                    int j = nodeIndex.get(v);

                    if (i != j && dist[i][j] != INF) {

                        List<Integer> path = getPath(i, j, next);

                        System.out.print(u + " -> " + v + " = " + dist[i][j] + " : ");

                        for (int k = 0; k < path.size(); k++) {
                            System.out.print(getKey(nodeIndex, path.get(k)));
                            if (k != path.size() - 1) System.out.print(" -> ");
                        }

                        System.out.println();
                    }
                }
            }

        } else {

            System.out.print("\nEnter source: ");
            String source = sc.next();

            System.out.print("Enter destination: ");
            String dest = sc.next();

            Map<String, String> parent = new HashMap<>();
            Map<String, Integer> result = Dijkstra.shortestPath(graph, source, parent);

            System.out.println("\nShortest distance: " + result.get(dest));

            List<String> path = PathUtil.getPath(parent, dest);
            System.out.println("Path: " + String.join(" -> ", path));
        }

        sc.close();
    }

    
    static void floydWarshall(int[][] dist, int[][] next, int V) {

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {

                    if (dist[i][k] != INF &&
                        dist[k][j] != INF &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {

                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    
    static List<Integer> getPath(int u, int v, int[][] next) {

        if (next[u][v] == -1) return new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        path.add(u);

        while (u != v) {
            u = next[u][v];
            path.add(u);
        }

        return path;
    }

    
    static String getKey(Map<String, Integer> map, int value) {
        for (String key : map.keySet()) {
            if (map.get(key) == value) return key;
        }
        return null;
    }
}
