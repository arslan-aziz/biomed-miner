import React from 'react';
import ReactDOM from 'react-dom';
import Graph from './Graph';
import NavBar from './NavBar';
import SideBar from './SideBar';
import defaultGraph from './data/default_graph.json';
import axios from 'axios';
import selectionToIdDict from './config/selection_to_id_dict';
import net_config from './config/net_config.json'

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

const openQuery = (evt) => {
  evt.preventDefault()
  let queryValue = evt.target[0].value
  console.log(queryValue)

  let url = 'http://127.0.0.1:8080/nlpextraction'
  const options = {
    url: url,
    method: 'GET',
    params: { querykey: queryValue }
  }

  axios(options)
  .then(response => {
    console.log(response)
  })
  .catch(error => {
    console.log(error)
  })
}

const selectData = (selector) => {
  console.log(selector);

  // get resource id from selection
  let articleId = selectionToIdDict[selector];
  let url = `http://${net_config.server_ip}/article/${articleId}`
  console.log(url) 

  const options = {
    url: url,
    method: 'GET'
  }

  axios(options)
  .then(response => {
    let parsedData = parseResponse(response)
    ReactDOM.render(
      <Graph data={ parsedData }/>,
      document.getElementById('graph')
    );
  })
  .catch(error => {
    console.log(error)
    // if error, display mock data
    ReactDOM.render(
      <Graph data={ defaultGraph }/>,
      document.getElementById('graph')
    );
  })
}

ReactDOM.render(
  <NavBar />,
  document.getElementById('navbar')
);

ReactDOM.render(
  <SideBar selectData={ selectData } queryData={ openQuery }/>,
  document.getElementById('sidebar')
);

// display mock data by default
ReactDOM.render(
  <Graph data={ defaultGraph }/>,
  document.getElementById('graph')
);
