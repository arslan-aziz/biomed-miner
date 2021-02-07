import React from 'react';
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom'

import Layout from './Layout';
import Home from './Home';
import About from './About';
import Account from './Account';
import Explore from './Explore';
import LogIn from './LogIn';
import NoMatch from './NoMatch';

import './App.css';

function App() {
  return (
    <React.Fragment>
      <Layout>
        <Router>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route path="/about" component={About} />
            <Route path="/account" component={Account} />
            <Route path="/explore" component={Explore} />
            <Route path="/login" component={LogIn} />
            <Route component={NoMatch} />
          </Switch>
        </Router>
      </Layout>
    </React.Fragment>
  );
}




export default App;
