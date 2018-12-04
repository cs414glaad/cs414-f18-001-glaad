import React, {Component} from 'react';
import Panel from './Panel.js';
import axios from 'axios';
import qs from 'qs';

class GameSelect extends Component{
  constructor(props){
    super(props);
    this.state = {gameList: [], userdata: null, games: []};
    this.getGame = this.getGame.bind(this);
    this.setUser = this.setUser.bind(this);
    this.setUserData = this.setUserData.bind(this);
    this.setGameList = this.setGameList.bind(this);
    this.getGames = this.getGames.bind(this);
    this.load = this.load.bind(this);

    //this.componentWillReceiveProps(props); // call this?
  }
  componentWillReceiveProps(props) {
    this.setState({user: props.user, gameList: [], userdata: null, games: []});
    this.load()
  }
  load() {
    this.setUser();
    if (this.state.user === null || this.state.user === undefined) {
      return;
    }
    this.setUserData();
    console.log('this.state.userdata');
    console.log(this.state.userdata);
    if (this.state.userdata === null || this.state.userdata === undefined) {
      return;
    }
    for (let idx in this.state.userdata.games) {
      let gameId = this.state.userdata.games[idx];
      console.log('game found: '+gameId);
      this.setGameList(gameId);
    }
    for (let idx in this.state.gameList) {
      let user1 = {username: this.state.gameList[idx].user1};
      let user2 = {username: this.state.gameList[idx].user2};
      let gameId = this.state.gameList[idx].id;
      if (this.state.gameList[idx].user1Color === "red") {
        this.games.push({blackUser: user2, redUser: user1, gameId: gameId});
      } else {
        this.games.push({blackUser: user1, redUser: user2, gameId: gameId});
      }
    }
  }
  getGame(game){
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
  setUser() {
    let err = (function (error) {
      alert(error.response.data.msg)
    }).bind(this);
    axios.post(this.props.server + '/query', qs.stringify({
      type: "whoami"
    }))
    .then(function (response) {
      console.log('getting user data');
      console.log(response);
      let user = response.data.user;
      if (user === "") {
        user = null;
      }
      this.setState({user: user});
      console.log('userdata: '+this.state.userdata);
    }.bind(this))
    .catch(err);
  }
  setUserData() {
    console.log('getting user data, pre post call');
    console.log(this.state.user)
    let then = (function (response) {
      this.setState({userdata: response.data.users[0]});
      console.log('userdata: '+this.state.userdata);
    }).bind(this);
    let err = (function (error) {
      alert(error.response.data.msg)
    }).bind(this);
    axios.post(this.props.server + '/query', qs.stringify({
      type: "user",
      username: this.state.user,
    }))
    .then(then)
    .catch(err);
  }
  setGameList(gameId) {
    let then = (function(response) {
      this.state.gameList.push(response.data[0]);
      console.log('game');
      console.log(response.data[0]);
    }).bind(this);
    let err = (function(error) {
      alert(error.response.data.msg)
    }).bind(this);
    axios.post(this.props.server + '/game', qs.stringify({
      type: "game",
      gameId: gameId
    }))
    .then(then)
    .catch(err);
  }
  getGames(){
    if (this.state.user === null) {
        return (
          <div>Not logged in</div>
        );
    }
    let out = [];
    for( let idx in this.state.games ){
      out.push(this.getGame(this.state.games[idx]));
    }
    return (
      <ul className="card list-group list-group-flush">
        {out}
      </ul>
    );
  }
  render() {
    return (
      <Panel name="Select Game" startCollapsed={true}>
        {this.getGames()}
      </Panel>
    )
  }
}

export default GameSelect;