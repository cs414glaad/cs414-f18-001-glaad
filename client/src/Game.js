import React, {Component} from 'react';
import Panel from './Panel.js';
import Cell from './Cell.js'
import axios from 'axios';

class Game extends Component{
  constructor(props){
    super(props);
    this.state = {start: null, width: window.innerWidth, board: null, game: null, cancel: false, err: null};

    // bindings
    this.move = this.move.bind(this);
    // board is horizontal row by col
    this.cancelChoice = this.cancelChoice.bind(this);
    this.updateBoard = this.updateBoard.bind(this);
    this.updateGame = this.updateGame.bind(this);
    this.updateGameState = this.updateGameState.bind(this);
  }

  // props.game must have gameId
  componentWillReceiveProps(props) {
    if (props.user != null && props.game != null) {
      this.updateBoard(props.game.gameId);
      this.updateGame(props.game.gameId);
    }
  }

  updateGameState() {
    if (this.state.game == null || this.state.board == null) {
      return;
    }
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
      console.log(board.cells);
    }.bind(this)).catch(this.props.error);
  }

  updateDimensions() {
    this.setState({ width: window.innerWidth });
  }

  componentDidMount() {
    this.updateDimensions();
    window.addEventListener("resize", this.updateDimensions.bind(this));
    this.timer = setInterval(this.updateGameState, 2000);
  }

  componentWillUnmount() {
    window.removeEventListener("resize", this.updateDimensions.bind(this));
    clearInterval(this.timer);
  }

  move(id) {
    if (this.state.start != null) {
      axios.post(this.props.server + '/game', {
        type: 'move',
        gameId: this.state.game.id,
        fromCell: this.state.start,
        toCell: id,
      }).then(function(response) {
        this.setState({start: null, err: null});
        this.updateGameState();
      }.bind(this)).catch(function(error) {
        this.setState({start: null, err: error.response.data.msg});
      }.bind(this));
    } else {
      this.setState({start: id, err: null});
    }
    this.setState({cancel: false, err: null});
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
        this.setState({start: null, err: null});
      } else {
        this.setState({cancel: true});
      }
    }
  }

  formatColor(color) {
    if (color === "") {
      return "badge";
    } else if (color === "black") {
      return "badge badge-dark";
    } else {
      return "badge badge-danger";
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
    let players = this.formatPlayers();
    return (
      <div>
        <div className={players.userColor}>{players.user} (you)</div> vs <div className={players.oppColor}>{players.opp}</div>
      </div>
    );
  }

  getInstructions() {
    return (
      <div>
        <h4>
          Rules:
        </h4>
        <ul>
          <li>Double click pieces to flip them.</li>
          <li>Click on the background to cancel move.</li>
          <li>Click on Refresh to refresh the game.</li>
        </ul>
        <button className="btn btn-primary col-2" onClick={() => this.updateGameState()}>
          Refresh
        </button>
      </div>
    );
  }

  getGameHeader() {
    if (this.state.board == null || this.state.game == null) {
      return null;
    }
    let msg = null;
    if (this.state.game.turn === this.props.user) {
      msg = "Status: Your turn!";
    } else {
      msg = "Status: Wait for opponent's move!";
    }
    if (this.state.err != null) {
      msg = "Error: "+this.state.err;
    }
    return (
      <div>
        <div> {this.getGameDetails()} </div>
        <br />
        <div> {msg} </div>
      </div>
    );
  }

  renderGame() {
    return (
      <div onClick={this.cancelChoice}>
        <div> {this.getInstructions()} </div>
        <br />
        <div style={{textAlign: "center"}}> {this.getGameHeader()} </div>
        <br />
        <div> {this.getBoard()} </div>
      </div>
    );
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
