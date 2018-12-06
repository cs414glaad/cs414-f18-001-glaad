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
    this.loadBoard = this.loadBoard.bind(this);
    this.cancelChoice = this.cancelChoice.bind(this);
    this.updateBoard = this.updateBoard.bind(this);
  }

  componentWillReceiveProps(props) {
    let newState = {user: props.user};
    if (props.user != null) {
      newState.game = props.game;
      this.loadBoard(props.game);
    }
    this.setState(newState);
  }

  loadBoard(game) {
    if (game == null) {
      return;
    }
    this.updateBoard(game.gameId);
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
      axios.post(this.props.server + '/game', {
        type: 'move',
        gameId: this.state.game.gameId,
        fromCell: this.state.start,
        toCell: id,
      }).then(function(response) {
        this.setState({start: null});
        this.updateBoard(this.state.game.gameId)
      }.bind(this)).catch(this.props.error);
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

  renderGame() {
    return (
      <div onClick={this.cancelChoice}>
        <div>
          game state
        </div>
        <div>
          {this.getBoard()}
        </div>
      </div>
    )
  }

  render() {
    let board = null;
    if (this.state.user == null) {
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
