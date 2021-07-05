import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import LessonList from './LessonList';
import LessonEdit from './LessonEdit';
import Form from './Form';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/login' exact={true} component={Form}/>
            <Route path='/lections' exact={true} component={LessonList}/>
            <Route path='/lections/:id' component={LessonEdit}/>
          </Switch>
        </Router>
    )
  }
}

export default App;
