import React, {Component} from 'react';
import Panel from './Panel.js';
import Cell from './Cell.js'
import axios from 'axios';

class Game extends Component{
  constructor(props){
    super(props);
    this.state = {start: null, width: window.innerWidth, board: null, game: null, cancel: false};

    // bindings
    this.move = this.move.bind(this);
    // board is horizontal row by col
    this.cancelChoice = this.cancelChoice.bind(this);
    this.updateBoard = this.updateBoard.bind(this);
    this.updateGame = this.updateGame.bind(this);
  }

  // props.game must have gameId
  componentWillReceiveProps(props) {
    if (props.user != null && props.game != null) {
      this.updateBoard(props.game.gameId);
      this.updateGame(props.game.gameId);
    }
  }

  updateGameState() {
    let gameId = this.state.game.id;
    if (gameId != null) {
      this.updateGame(gameId);
      this.updateBoard(gameId);
    }
  }

  updateGame(gameId) {
    axios.post(this.props.server + '/query', {
      type: "game",
      gameId: gameId,
    }).then(function(response) {
      let game = response.data.games[0];
      this.setState({game: game});
    }.bind(this)).catch(this.props.error);
  }

  updateBoard(gameId) {
    axios.post(this.props.server + '/query', {
      type: "board",
      gameId: gameId,
    }).then(function(response) {
      let board = response.data.boards[0];
      this.setState({board: board.cells});
    }.bind(this)).catch(this.props.error);
  }

  updateDimensions() {
    this.setState({ width: window.innerWidth });
  }
  componentDidMount() {
    this.updateDimensions();
    window.addEventListener("resize", this.updateDimensions.bind(this));
  }
  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions.bind(this));
  }

  move(id) {
    if (this.state.start != null) {
      console.log(this.state.game);
      axios.post(this.props.server + '/game', {
        type: 'move',
        gameId: this.state.game.id,
        fromCell: this.state.start,
        toCell: id,
      }).then(function(response) {
        this.setState({start: null});
        this.updateBoard(this.state.game.id);
      }.bind(this)).catch(function(error) {
        this.setState({start: null});
        this.props.error(error);
      }.bind(this));
    } else {
      this.setState({start: id});
    }
    this.setState({cancel: false});
  }

  getRowVert(y, board){
    let out = [];
    for( let i = 0; i < 4; i++ ){
      out.push(<div className="col-3 col-md-2"><Cell piece={board[3-i][y]} id={board[3-i][y].id} move={this.move}/></div>)
    }
    return <div className="row"><div className="d-none d-md-block col-md-2"/>{out}<div className="d-none d-md-block col-md-2"/></div>;
  }

  getRowHoriz(y, row){
    let out = [];
    for( let i = 0; i < 8; i++ ){
      out.push(<div className="col-1"><Cell piece={row[i]} id={row[i].id} move={this.move}/></div>)
    }
    return <div className="row"><div className="col-2"/> {out} <div className="col-2"/></div>;
  }

  getBoardVert(board){
    return(
      <div>
        {this.getRowVert(0, board)}
        <br/>
        {this.getRowVert(1, board)}
        <br/>
        {this.getRowVert(2, board)}
        <br/>
        {this.getRowVert(3, board)}
        <br/>
        {this.getRowVert(4, board)}
        <br/>
        {this.getRowVert(5, board)}
        <br/>
        {this.getRowVert(6, board)}
        <br/>
        {this.getRowVert(7, board)}
      </div>
    )
  }

  getBoardHoriz(board){
    return(
    <div>
      {this.getRowHoriz(0, board[0])}
      <br/>
      {this.getRowHoriz(1, board[1])}
      <br/>
      {this.getRowHoriz(2, board[2])}
      <br/>
      {this.getRowHoriz(3, board[3])}
    </div>
    )
  }

  getBoard(){
    if(this.state.width > 1000) {
      return this.getBoardHoriz(this.state.board);
    } else {
      return this.getBoardVert(this.state.board);
    }
  }

  cancelChoice() {
    if (this.state.start != null) {
      if (this.state.cancel === true) {
        this.setState({start: null});
      } else {
        this.setState({cancel: true});
      }
    }
  }

  formatColor(color) {
    if (color === "") {
      return "color not assigned yet";
    } else {
      return color;
    }
  }

  formatPlayers() {
    let players = {};
    if (this.props.user === this.state.game.user1) {
      players.user = this.state.game.user1;
      players.userColor = this.formatColor(this.state.game.user1Color);
      players.opp = this.state.game.user2;
      players.oppColor = this.formatColor(this.state.game.user2Color);
    } else {
      players.user = this.state.game.user2;
      players.userColor = this.formatColor(this.state.game.user2Color);
      players.opp = this.state.game.user1;
      players.oppColor = this.formatColor(this.state.game.user1Color);
    }
    return players;
  }

  getGameDetails() {
    if (this.state.game == null) {
      return null;
    }
    let players = this.formatPlayers();
    return (
      <div>
        <div>
          Turn: {this.state.game.turn}
        </div>
        <div>
          You: {players.user}
        </div>
        <div>
          Your color: {players.userColor}
        </div>
        <div>
          Opponent: {players.opp}
        </div>
        <div>
          Opponent color: {players.oppColor}
        </div>
      </div>
    );
  }

  getInstructions() {
    return (
      <div>
        Double click pieces to flip them.
      </div>
    );
  }

  renderGame() {
    let refresh = null;
    if (this.state.board != null) {
      refresh = (
        <button className="btn btn-primary col-2" onClick={() => this.updateGameState()}>
          Refresh
        </button>
      );
    }
    return (
      <div onClick={this.cancelChoice}>
        <div>
          {this.getGameDetails()}
        </div>
        <div>
          {this.getInstructions()}
        </div>
        <br />
        <div style={{textAlign: "center"}}>
          {refresh}
        </div>
        <br />
        <div>
          {this.getBoard()}
        </div>
      </div>
    )
  }

  render() {
    let board = null;
    if (this.props.user == null) {
      board = null;
    } else if (this.state.board == null) {
      board = (<div>No active games</div>);
    } else {
      board = this.renderGame();
    }
    return (
      <Panel name="Game" startCollapsed={true}>
      {board}
      </Panel>
    );
  }
}

export default Game
