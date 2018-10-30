import React, { Component } from 'react';
import Login from './Login.js';
import Invites from './Invites.js';
import GameSelect from './GameSelect.js';
import Game from './Game.js'
import Debug from './Debug.js'

class App extends Component {
  constructor(props){
    super(props);
    this.state = {server: "http://localhost:3001", user: null, game: null};
    this.updateServer = this.updateServer.bind(this);
    this.updateUser = this.updateUser.bind(this);
    this.updateGame = this.updateGame.bind(this);
  }
  updateServer(event){
    this.setState({server: event.target.value})
  }
  updateUser(newUser){
    this.setState({user: newUser})
  }
  updateGame(newGame){
    this.setState({game: newGame})
  }
  render() {
    return (
      <div className="container-fluid">
        <Login user={this.state.user} updateUser={this.updateUser}/>
        <Invites user={this.state.user}/>
        <GameSelect user={this.state.user} updateGame={this.updateGame}/>
        <Game user={this.state.user} game={this.state.game}/>
        <Debug updateServer={this.updateServer}/>
      </div>
    );
  }
}

export default App;
