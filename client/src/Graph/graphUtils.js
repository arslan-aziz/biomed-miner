const mergeGraphs = (graph1, graph2) => {
    let graphData = {
        nodes: [],
        links: []
    }

    graphData.nodes = graph1.nodes.concat(graph2.nodes)
    graphData.links = graph1.links.concat(graph2.links)

    return graphData
}

export default mergeGraphs;