/**
 * @file graph_tools.cpp
 * This is where you will implement several functions that operate on graphs.
 * Be sure to thoroughly read the comments above each function, as they give
 *  hints and instructions on how to solve the problems.
 */

#include "graph_tools.h"
#include <map>

using namespace std;

/**
 * Returns the shortest distance (in edges) between the Vertices
 *  start and end.
 * THIS FUNCTION IS GRADED.
 *
 * @param graph - the graph to search
 * @param start - the vertex to start the search from
 * @param end - the vertex to find a path to
 * @return the minimum number of edges between start and end
 *
 * @todo Label each edge "MINPATH" if it is part of the minimum path
 *
 * @note Remember this is the shortest path in terms of edges,
 *  not edge weights.
 * @note Again, you may use the STL stack and queue.
 * @note You may also use the STL's unordered_map, but it is possible
 *  to solve this problem without it.
 *
 * @hint In order to draw (and correctly count) the edges between two
 *  vertices, you'll have to remember each vertex's parent somehow.
 */
int GraphTools::findShortestPath(Graph & graph, Vertex start, Vertex end)
{
	queue<Vertex> vertexQ;
	map<Vertex,Vertex> vertexMap;
	setUnexplored(graph);
	graph.setVertexLabel(start,"VISITED");
	vertexQ.push(start);

	while(vertexQ.empty() == false){
		Vertex v = vertexQ.front();
		vector<Vertex> adjacent = graph.getAdjacent(v);
		vertexQ.pop();
		int size = adjacent.size();
		for(int i = 0; i<size; i++){
			Vertex k = adjacent[i];
			if(graph.getVertexLabel(k).compare("UNEXPLORED") == 0){
				vertexMap[k] = v;
				graph.setEdgeLabel(v,k,"DISCOVERY");
				graph.setVertexLabel(k,"VISITED");
				vertexQ.push(k);
			}
			else if(graph.getEdgeLabel(v,k).compare("UNEXPLORED") == 0)
				graph.setEdgeLabel(v,k,"CROSS");
		}
	}
	
	int c = 0;
	while(start != end){
		graph.setEdgeLabel(end, vertexMap[end], "MINPATH");
		end = vertexMap[end];
		c++;
	}
	
	return c;
}

/**
 * Finds the minimum edge weight in the Graph graph.
 * THIS FUNCTION IS GRADED.
 *
 * @param graph - the graph to search
 * @return the minimum weighted edge
 *
 * @todo Label the minimum edge as "MIN". It will appear blue when
 *  graph.savePNG() is called in minweight_test.
 *
 * @note You must do a traversal.
 * @note You may use the STL stack and queue.
 * @note You may assume the graph is connected.
 *
 * @hint Initially label vertices and edges as unvisited.
 */
int GraphTools::findMinWeight(Graph & graph)
{
	setUnexplored(graph);
	Vertex s = graph.getStartingVertex();
	graph.setVertexLabel(s, "VISITED");
	vector<Vertex> adjacent = graph.getAdjacent(s);
	int minWeight = graph.getEdgeWeight(s,adjacent[0]);
	queue<Vertex> q;
	q.push(s);
	Vertex minV, minK;
	
	while(q.empty() == false){
		Vertex v = q.front();
		vector<Vertex> adjacent1 = graph.getAdjacent(v);
		q.pop();
		int size = adjacent1.size();
		for(int i = 0; i<size; i++){
			Vertex k = adjacent1[i];
			if(graph.getVertexLabel(k).compare("UNEXPLORED") == 0){
				graph.setEdgeLabel(v,k,"DISCOVERY");
				graph.setVertexLabel(k,"VISITED");
				q.push(k);
			}
			else if(graph.getEdgeLabel(v,k).compare("UNEXPLORED") == 0)
				graph.setEdgeLabel(v,k,"CROSS");
			if(graph.getEdgeWeight(v,k) <= minWeight){
				minWeight = graph.getEdgeWeight(v,k);
				minK = k;
				minV = v;
			}
		}
	}
	graph.setEdgeLabel(minV, minK, "MIN");
	return minWeight;
	
	
}

/**
 * Finds a minimal spanning tree on a graph.
 * THIS FUNCTION IS GRADED.
 *
 * @param graph - the graph to find the MST of
 *
 * @todo Label the edges of a minimal spanning tree as "MST"
 *  in the graph. They will appear blue when graph.savePNG() is called.
 *
 * @note Use your disjoint sets class from MP 7.1 to help you with
 *  Kruskal's algorithm. Copy the files into the libdsets folder.
 * @note You may call std::sort (http://www.cplusplus.com/reference/algorithm/sort/)
 *  instead of creating a priority queue.
 */
void GraphTools::findMST(Graph & graph)
{
	vector<Edge> edgeList = graph.getEdges();
	vector<Edge>::iterator it;
	DisjointSets vertices;
	vector<Vertex> vertexList = graph.getVertices();
	vertices.addelements(vertexList.size());
	sort(edgeList.begin(), edgeList.end(), sortEdge);
	
	int size = edgeList.size();
	for(int i = 0; i<size; i++){
		Vertex v = edgeList[i].source;
		Vertex k = edgeList[i].dest;
		if(vertices.find(v) != vertices.find(k)){
			vertices.setunion(v,k);
			graph.setEdgeLabel(v,k,"MST");
		}
	}
}

bool GraphTools::sortEdge(Edge x, Edge y){
	return x.weight < y.weight;
}

void GraphTools::setUnexplored(Graph& graph){
	Vertex s = graph.getStartingVertex();
	queue<Vertex> q;
	graph.setVertexLabel(s,"UNEXPLORED");
	q.push(s);
	
	while(q.empty() == false){
		Vertex v = q.front();
		vector<Vertex> adjacent = graph.getAdjacent(v);
		q.pop();
		int size = adjacent.size();
		for(int i = 0; i<size; i++){
			Vertex k = adjacent[i];
			if(graph.getVertexLabel(k).compare("UNEXPLORED") !=0){
				graph.setEdgeLabel(v,k,"UNEXPLORED");
				graph.setVertexLabel(k,"UNEXPLORED");
				q.push(k);
			}
			else if(graph.getEdgeLabel(v,k).compare("UNEXPLORED") != 0)
				graph.setEdgeLabel(v,k,"UNEXPLORED");
		}
	}
}
