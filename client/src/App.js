import React from 'react';
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom'

import Home from './Home/Home';
import About from './About/About';

function App() {

  return (
    <React.Fragment>
      <Router>
        <Switch>
          <Route exact path="/" component={Home} />
          <Route path="/about" component={About} />
        </Switch>
      </Router>
    </React.Fragment>
  );
}

export default App;
