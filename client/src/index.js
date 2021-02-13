import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Test from './Graph';
import NavBar from './NavBar';
import SideBar from './SideBar';
import defaultGraph from './data/default_graph.json';
import axios from 'axios';
import selectionToIdDict from './config/selection_to_id_dict';

const parseResponse = (response) => {
  let graphData = {
    nodes: [],
    links: []
  }

  console.log(response.data.graph.idToVertexMap)

  // add vertices
  for (const val in response.data.graph.idToVertexMap) {
    graphData.nodes.push({id: val, group: 1})
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

const selectData = (selector) => {
  console.log(selector);

  // get resource id from selection
  let articleId = selectionToIdDict[selector];
  let url = 'http://localhost:8080/article/'.concat(articleId);
  console.log(url) 

  const options = {
    url: url,
    method: 'GET'
  }

  axios(options)
  .then(response => {
    let parsedData = parseResponse(response)
    ReactDOM.render(
      <Test data={ parsedData }/>,
      document.getElementById('graph')
    );
  })
  .catch(error => {
    console.log(error)
    // if error, display mock data
    ReactDOM.render(
      <Test data={ defaultGraph }/>,
      document.getElementById('graph')
    );
  })
}

ReactDOM.render(
  <NavBar />,
  document.getElementById('navbar')
);

ReactDOM.render(
  <SideBar selectData={ selectData }/>,
  document.getElementById('sidebar')
);

// display mock data by default
ReactDOM.render(
  <Test data={ defaultGraph }/>,
  document.getElementById('graph')
);
