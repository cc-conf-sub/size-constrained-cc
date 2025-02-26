import java.util.*;

public class Hybrid {

    public static ArrayList<ArrayList<Integer>> large_graph_fix_clusters(ArrayList<ArrayList<Integer>> cur_clustering, ArrayList<ArrayList<Integer>> prob_matrix, int k, boolean not_small) {
    /*
        Apply DeterministicNode to clusters resulting from pKwik algorithm

    */

    ArrayList<ArrayList<Integer>> new_clustering = new ArrayList<ArrayList<Integer>>();
    for (int c = 0; c < cur_clustering.size(); c++) {
        ArrayList<Integer> cluster = cur_clustering.get(c);
        int c_size = cluster.size();

        if (c_size <= k && not_small) {
            new_clustering.add(cluster);
        } else if (c_size == 1) {
            new_clustering.add(cluster);
        }
        
        else { 

            HashMap<Integer, Integer> relabelling = new HashMap<Integer, Integer>();
	    ArrayList<Integer> new_cluster = new ArrayList<Integer>();
            for (int i = 0; i < c_size; i++) {
                relabelling.put(cluster.get(i), i);
	        new_cluster.add(i);
	    }

            ArrayList<ArrayList<Integer>> cur_prob_matrix = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < c_size; i++) {
                ArrayList<Integer> new_edges = new ArrayList<Integer>();
                ArrayList<Integer> cur_edges = prob_matrix.get(cluster.get(i));
                for (int j = 0; j < cur_edges.size(); j++){
                    int cur_edge = cur_edges.get(j);
                    if (cluster.contains(cur_edge))
                        new_edges.add(relabelling.get(cur_edge));
                }
                cur_prob_matrix.add(new_edges);
            }
        
	    ArrayList<ArrayList<Integer>> input_clustering = new ArrayList<ArrayList<Integer>>();
	    input_clustering.add(new_cluster);
            ArrayList<ArrayList<Integer>> adjusted_clustering = DNode.max_k_random_node_network(cur_prob_matrix, k);

            // replace node labels
            for (int j = 0; j < adjusted_clustering.size(); j++) {
                ArrayList<Integer> adj_cluster = adjusted_clustering.get(j);
                for (int i = 0; i < adj_cluster.size(); i++)
                    adj_cluster.set(i, cluster.get(adj_cluster.get(i)));
                new_clustering.add(adj_cluster);
            }  

        } /* else {           

            double [][] cur_prob_matrix = new double[c_size][c_size];
                        
            // form new probability matrix
            for (int i = 0; i < c_size; i++) {
                cur_prob_matrix[i][i] = 1;
                for (int j = i+1; j < c_size; j++) {
                    int neighbor;
                    if (prob_matrix.get(cluster.get(i)).contains(cluster.get(j))) 
                        neighbor = 1;
                    else
                        neighbor = 0;                 
                    
                    cur_prob_matrix[i][j] = neighbor;
                    cur_prob_matrix[j][i] = neighbor;
                    
                }
            }
        
            ArrayList<ArrayList<Integer>> adjusted_clustering = DNode.adapted_node(cur_prob_matrix);

            // replace node labels
            for (int j = 0; j < adjusted_clustering.size(); j++) {
                ArrayList<Integer> adj_cluster = adjusted_clustering.get(j);
                for (int i = 0; i < adj_cluster.size(); i++)
                    adj_cluster.set(i, cluster.get(adj_cluster.get(i)));
                new_clustering.add(adj_cluster);
            }            
        }*/
    }
    return new_clustering;

    }

    // Run Hybrid with Fewer Samples

    public static ArrayList<ArrayList<Integer>> large_graph_fix_clusters_reps(ArrayList<ArrayList<Integer>> cur_clustering, ArrayList<ArrayList<Integer>> prob_matrix, int MAX_REPS) {
    /*
        Apply DeterministicNode to clusters resulting from pKwik algorithm

    */

    ArrayList<ArrayList<Integer>> new_clustering = new ArrayList<ArrayList<Integer>>();
    for (int c = 0; c < cur_clustering.size(); c++) {
        ArrayList<Integer> cluster = cur_clustering.get(c);
        int c_size = cluster.size();

        if (c_size <= 3) {
            new_clustering.add(cluster);
        } else if (c_size >= MAX_REPS) { // avoid n^2 matrix for large clusters 

            HashMap<Integer, Integer> relabelling = new HashMap<Integer, Integer>();
            for (int i = 0; i < c_size; i++)
                relabelling.put(cluster.get(i), i);

            ArrayList<ArrayList<Integer>> cur_prob_matrix = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < c_size; i++) {
                ArrayList<Integer> new_edges = new ArrayList<Integer>();
                ArrayList<Integer> cur_edges = prob_matrix.get(cluster.get(i));
                for (int j = 0; j < cur_edges.size(); j++){
                    int cur_edge = cur_edges.get(j);
                    if (cluster.contains(cur_edge))
                        new_edges.add(relabelling.get(cur_edge));
                }
                cur_prob_matrix.add(new_edges);
            }
        
            ArrayList<ArrayList<Integer>> adjusted_clustering = DNode.adapted_node_network(cur_prob_matrix, MAX_REPS);

            // replace node labels
            for (int j = 0; j < adjusted_clustering.size(); j++) {
                ArrayList<Integer> adj_cluster = adjusted_clustering.get(j);
                for (int i = 0; i < adj_cluster.size(); i++)
                    adj_cluster.set(i, cluster.get(adj_cluster.get(i)));
                new_clustering.add(adj_cluster);
            }  

        } else {           

            double [][] cur_prob_matrix = new double[c_size][c_size];
                        
            // form new probability matrix
            for (int i = 0; i < c_size; i++) {
                cur_prob_matrix[i][i] = 1;
                for (int j = i+1; j < c_size; j++) {
                    int neighbor;
                    if (prob_matrix.get(cluster.get(i)).contains(cluster.get(j))) 
                        neighbor = 1;
                    else
                        neighbor = 0;                 
                    
                    cur_prob_matrix[i][j] = neighbor;
                    cur_prob_matrix[j][i] = neighbor;
                    
                }
            }
        
            ArrayList<ArrayList<Integer>> adjusted_clustering = DNode.adapted_node(cur_prob_matrix);

            // replace node labels
            for (int j = 0; j < adjusted_clustering.size(); j++) {
                ArrayList<Integer> adj_cluster = adjusted_clustering.get(j);
                for (int i = 0; i < adj_cluster.size(); i++)
                    adj_cluster.set(i, cluster.get(adj_cluster.get(i)));
                new_clustering.add(adj_cluster);
            }            
        }
    }
    return new_clustering;

    }


    // INPUT: probability matrix instead of adjacency list
    public static ArrayList<ArrayList<Integer>> large_graph_fix_clusters_prob(ArrayList<ArrayList<Integer>> cur_clustering, ArrayList<ArrayList<Double[]>> prob_matrix) {
        /*
            Apply DeterministicNode to clusters resulting from pKwik algorithm    
        */
    
        ArrayList<ArrayList<Integer>> new_clustering = new ArrayList<ArrayList<Integer>>();
        for (int c = 0; c < cur_clustering.size(); c++) {
            ArrayList<Integer> cluster = cur_clustering.get(c);
            int c_size = cluster.size();
    
            if (c_size <= 3) {
                new_clustering.add(cluster);
            } else {           
    
                double [][] cur_prob_matrix = new double[c_size][c_size];
                            
                // form new probability matrix
                for (int i = 0; i < c_size; i++) {
                    cur_prob_matrix[i][i] = 1;
                    for (int j = i+1; j < c_size; j++) {
                        ArrayList<Double[]> cur_cluster = prob_matrix.get(cluster.get(i));
                        double neighbor = 0;
                        for (int k = 0; k < cur_cluster.size(); k++) {
                            double other_node = cur_cluster.get(k)[0];
                            if (cluster.get(j) == (int) other_node)
                                neighbor = cur_cluster.get(k)[1];
                        }                
                        
                        cur_prob_matrix[i][j] = neighbor;
                        cur_prob_matrix[j][i] = neighbor;
                        
                    }
                }

                /*
                for (int i = 0; i < c_size; i++) {
                    cur_prob_matrix[i][i] = 1;
                    for (int j = i+1; j < c_size; j++) {
                        double neighbor = prob_matrix[cluster.get(i)][cluster.get(j)];               
                        cur_prob_matrix[i][j] = neighbor;
                        cur_prob_matrix[j][i] = neighbor;
                        
                    }
                }
                */
            
                ArrayList<ArrayList<Integer>> adjusted_clustering = DNode.adapted_node(cur_prob_matrix);
    
                // replace node labels
                for (int j = 0; j < adjusted_clustering.size(); j++) {
                    ArrayList<Integer> adj_cluster = adjusted_clustering.get(j);
                    for (int i = 0; i < adj_cluster.size(); i++)
                        adj_cluster.set(i, cluster.get(adj_cluster.get(i)));
                    new_clustering.add(adj_cluster);
                }            
            }
        }
        return new_clustering;
    
    }



    
}
