const parseResponse = (response) => {
    let graphData = {
        nodes: [],
        links: []
    }

    console.log(response.data.graph.idToVertexMap)

    // add vertices
    for (const val in response.data.graph.idToVertexMap) {
        graphData.nodes.push({id: val, name: response.data.graph.idToVertexMap[val].nomenclature, group: 1})
    }

    // add edges
    for (const val in response.data.graph.adjVertices) {
        // add corresponding edges
        response.data.graph.adjVertices[val].forEach(other => {
        graphData.links.push({source: val, target: Object.keys(other)[0], value: 1})
        })
    }

    return graphData;
}

export default parseResponse;