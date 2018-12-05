import React, {Component} from 'react';
import Panel from './Panel.js';
import axios from 'axios';

class GameSelect extends Component{
  constructor(props){
    super(props);
    this.state = {user: null, games: []};

    // bindings
    this.addGame = this.addGame.bind(this);
    this.loadGame = this.loadGame.bind(this);
    this.loadUserData = this.loadUserData.bind(this);
  }

  componentWillReceiveProps(props) {
    this.setState({user: props.user, games: []});
    this.loadUserData(props.user);
  }

  addGame(game) {
    this.setState((prevState) => {
      prevState.games.push(game);
      return {games: prevState.games};
    });
  }

  loadGame(gameId) {
    axios.post(this.props.server + '/query', {
      type: "game",
      gameId: gameId,
    }).then(function(response) {
      let game = response.data.games[0];
      let procGame = GameSelect.procGame(game);
      this.addGame(procGame);
    }.bind(this)).catch(this.props.error);
  }

  loadUserData(user) {
    if (user == null) {
      return;
    }
    axios.post(this.props.server + '/query', {
      type: "user",
      username: user,
    }).then(function(response) {
      let user = response.data.users[0];
      let gameIds = user.games;
      for (let idx in gameIds) {
        this.loadGame(gameIds[idx]);
      }
    }.bind(this)).catch(this.props.error);
  }

  static procGame(game) {
    let user1 = {username: game.user1};
    let user2 = {username: game.user2};
    if (game.user1Color === "red") {
      return {blackUser: user2, redUser: user1, gameId: game.id};
    } else {
      return {blackUser: user1, redUser: user2, gameId: game.id};
    }
  }

  renderGames(){
    if (this.state.user === null) {
        return (
          <div>Not logged in</div>
        );
    }
    let out = [];
    for( let idx in this.state.games ){
      out.push(this.renderGame(this.state.games[idx]));
    }
    return (
      <ul className="card list-group list-group-flush">
        {out}
      </ul>
    );
  }

  renderGame(game){
    return (
      <li className="list-group-item">
        <div className="row">
          <div className="col-10">
            <div className="badge badge-dark">{game.blackUser.username}</div> vs <div className="badge badge-danger">{game.redUser.username}</div>
          </div>
          <button className="btn btn-primary col-2" onClick={() => this.props.updateGame(game)}>Join</button>
        </div>
      </li>
    )
  }

  render() {
    return (
      <Panel name="Select Game" startCollapsed={true}>
        {this.renderGames()}
      </Panel>
    )
  }
}

export default GameSelect;
