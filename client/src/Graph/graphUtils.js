function arrayUnique(array) {
    var a = array.concat();
    for(var i=0; i<a.length; ++i) {
        for(var j=i+1; j<a.length; ++j) {
            if(a[i]['id'] === a[j]['id'])
                a.splice(j--, 1);
        }
    }

    return a;
}

const mergeGraphs = (graph1, graph2) => {
    let graphData = {
        nodes: [],
        links: []
    }

    graphData.nodes = arrayUnique(graph1.nodes.concat(graph2.nodes))
    graphData.links = graph1.links.concat(graph2.links)

    console.log('merged graph data')
    console.log(graphData)

    return graphData
}

export default mergeGraphs;