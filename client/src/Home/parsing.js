const parseResponse = (entityGraph) => {
    let graphData = {
        nodes: [],
        links: []
    }

    // add vertices
    for (const vertexVal in entityGraph.idToVertexMap) {
        graphData.nodes.push({id: entityGraph.idToVertexMap[vertexVal]["nameId"], name: entityGraph.idToVertexMap[vertexVal]["nomenclature"], group: 1})
    }

    // add edges
    for (const val in entityGraph.adjVertices) {
        // add corresponding edges
        entityGraph.adjVertices[val].forEach(other => {
        graphData.links.push({source: val, target: Object.keys(other)[0], value: 1})
        })
    }

    return graphData;
}

export default parseResponse;