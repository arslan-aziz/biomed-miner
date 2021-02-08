import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import Test from './Test';
import NavBar from './NavBar';
import SideBar from './SideBar';
import data from './miserables.json';
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

const selectData = (selector) => {
  console.log(selector);

  // get resource id from selection
  let articleId = selectionToIdDict[selector];

  const options = {
    url: 'localhost:8080/article',
    method: 'GET',
    headers: {
      'Accept': 'application/json'
    },
    params: {
      id: articleId
    }
  }

  axios(options)
  .then(response => {
    console.log(response)
    // TODO: format response data
    ReactDOM.render(
      <Test data={ data }/>,
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
  <Test data={ data }/>,
  document.getElementById('graph')
);
