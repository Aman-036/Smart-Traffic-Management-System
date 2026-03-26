import java.util.*;

class Dijkstra {

    public static Map<String, Integer> shortestPath(
            Graph graph,
            String start,
            Map<String, String> parent
    ) {

        Map<String, Integer> dist = new HashMap<>();

        for (String node : graph.adjList.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
            parent.put(node, null);
        }

        dist.put(start, 0);

        PriorityQueue<String> pq = new PriorityQueue<>(
                Comparator.comparingInt(dist::get)
        );

        pq.add(start);

        while (!pq.isEmpty()) {
            String current = pq.poll();

            for (Edge edge : graph.adjList.get(current)) {
                int newDist = dist.get(current) + edge.weight;

                if (newDist < dist.get(edge.destination)) {
                    dist.put(edge.destination, newDist);
                    parent.put(edge.destination, current);
                    pq.add(edge.destination);
                }
            }
        }

        return dist;
    }
}
