import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    
    public static void dfs(int node, List<List<Integer>> graph, boolean[] visited){
        visited[node] = true;
        System.out.print(node + " ");

        for(int nei : graph.get(node)){
            if(!visited[nei]){
                dfs(nei, graph, visited);
            }
        }
    }
    public static void bfs(int start,List<List<Integer>> graph, boolean[] visited){
        Queue<Integer> q = new LinkedList<>();
        visited[start] = true;
        q.offer(start);

        while(!q.isEmpty()){
            int node = q.poll();
            System.out.print(node + " ");
            for (int nei : graph.get(node)) {
                if(!visited[nei]){
                    q.offer(nei);
                    visited[nei] = true;
                }
            }
        }
    }

    public static boolean hasCycle(
        int node, 
        int parent, 
        List<List<Integer>> graph, 
        boolean[] visited){
            visited[node] = true;
            for(int nei: graph.get(node)){
                if(!visited[nei]){
                    if(hasCycle(nei, node, graph, visited)){
                        return true;
                    }
                }
                else if(nei != parent){
                    return true;
                }
            }
            return false;
        }

    public static boolean hasCycleDirected(
        int node,  
        List<List<Integer>> graph, 
        boolean[] visited,
        boolean[] path){
            visited[node] = true;
            path[node] = true;
            for(int nei: graph.get(node)){
                if(!visited[nei]){
                    if(hasCycleDirected(nei,graph, visited, path)){
                        return true;
                    }
                }
                else if(path[nei]){
                    return true;
                }
            }

            path[node] = false;
            return false;
    }
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
            {0,1},
   
            {2,3},
            {1,3},
            {2,1}
        };

        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i<n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] e : edges){

            int u = e[0];
            int v = e[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(0, graph,new boolean[n]);
        System.out.println();
        bfs(0, graph, new boolean[n]);

        boolean[] visited = new boolean[n];
        System.out.println(hasCycle(0, -1, graph, new boolean[n]));

        // for (int i = 0; i < n; i++) {
        //     if (!visited[i]) {
        //         if (hasCycle(i, -1, graph, visited)) {
        //             System.out.println("Cycle exists");
        //             break;
        //         }
        //     }
        // }
        boolean[] path = n
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (hasCycle(i, -1, graph, visited, path)) {
                    System.out.println("Cycle exists");
                    break;
                }
            }
        }

    }
}
