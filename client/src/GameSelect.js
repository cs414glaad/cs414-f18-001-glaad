import React, {Component} from 'react';
import Panel from './Panel.js';

class GameSelect extends Component{
  getGame(game){
    return (
      <li className="list-group-item">
        <div className="row">
          <div className="col-10">
            {game.blackUser.username} vs {game.redUser.username}
          </div>
          <button className="btn btn-primary col-2">Join</button>
        </div>
      </li>
    )
  }
  getGames(){
    //TODO: Make an API call to get the current user's invites.
    let games = [{blackUser: {username: "1337h4x0r"}, redUser: {username: "banquiFreak123"}}, {blackUser: {username: "urMom"}, redUser: {username: "1337h4x0r"}}];
    let out = [];
    for( let idx in games ){
      out.push(this.getGame(games[idx]));
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