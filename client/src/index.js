import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Test from './Test';
import NavBar from './NavBar';
import SideBar from './SideBar';
import miserablesdata from './miserables.json';
import mockdata from './mockdata.json';
import axios from 'axios';

// ReactDOM.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>,
//   document.getElementById('root')
// );

const selectionToIdDict = {
  1: '1234',
  2: '5678',
  3: '3423'
}

const parseResponse = (response) => {
  let graphData = {
    nodes: [],
    links: []
  }

  for (const val in response.data.graph.adjVertices) {
    console.log('Processing '.concat(val))
    // add to vertices
    graphData.nodes.push({id: val, group: 1})
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
      <Test data={ mockdata }/>,
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
  <Test data={ miserablesdata }/>,
  document.getElementById('graph')
);
