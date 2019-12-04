package controller;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.JanusGraphFactory;



public class Controller {
    JanusGraph graph;
    GraphTraversalSource g;

    public Controller(){
        this.graph = JanusGraphFactory.open("conf/janusgraph-cassandra-elasticsearch.properties");
        this.g = graph.traversal();

    }

}
