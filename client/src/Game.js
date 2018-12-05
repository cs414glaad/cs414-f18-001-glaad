import React, {Component} from 'react';
import Panel from './Panel.js';
import Cell from './Cell.js'

class Game extends Component{
  constructor(props){
    super(props);
    this.state = {startX: null, startY: null, width: window.innerWidth};
    this.move = this.move.bind(this);
    this.flip = this.flip.bind(this)
  }

  componentWillReceiveProps(props) {
    this.setState({game: props.game});
    this.loadUserData(props.user);
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

  move(x,y){
    if( this.state.startX === x && this.state.startY === y ){
      //TODO: Throw error/show warning/do something - moving a piece on to itself.
    }
    else if( this.state.startX != null && this.state.startY != null) {
      //TODO: Replace this with an API call.
      console.log("Moving (" + this.state.startX + ", " + this.state.startY + ") to (" + x + ", " + y + ")");
      this.setState({startX: null, startY: null})
    }
    else{
      this.setState({startX: x, startY: y})
    }
  }
  flip(x,y){
    //Do not allow a flip if another piece is trying to move.
    if( this.state.startX == null && this.state.startY == null ) {
      //TODO: Replace this with an API call.
      console.log("Flipping (" + x + ", " + y + ")");
    }
  }
  //Vertical rows require the entire board's state.
  getRowVert(y, contents){
    //For demo purposes, just return the target element as the whole row.
    let out = [];
    for( let i = 0; i < 4; i++ ){
      out.push(<div className="col-3 col-md-2"><Cell piece={contents[y]} x={i} y={y} move={this.move} flip={this.flip}/></div>)
    }
    return <div className="row"><div className="d-none d-md-block col-md-2"/>{out}<div className="d-none d-md-block col-md-2"/></div>;
  }
  getRowHoriz(y, row){
    let out = [];
    for( let i = 0; i < 8; i++ ){
      out.push(<div className="col-1"><Cell piece={row[i]} x={i} y={y} move={this.move} flip={this.flip}/></div>)
    }
    return <div className="row"><div className="col-2"/> {out} <div className="col-2"/></div>;
  }
  getBoardVert(board){
    //Replace this hardcoding with a loop when API is hooked up.
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
    //Replace this hardcoding with a loop when API is hooked up.
    return(
    <div>
      {this.getRowHoriz(0, board)}
      <br/>
      {this.getRowHoriz(1, board)}
      <br/>
      {this.getRowHoriz(2, board)}
      <br/>
      {this.getRowHoriz(3, board)}
    </div>
    )
  }
  getBoard(){
    //TODO: Use API call to get actual contents of rows and pass them along
    let fakeContents = [{type:"S", isFlipped:true, color: "RED"},{type:"S", isFlipped:true, color: "BLACK"},{isFlipped:false},{},{},{},{},{}];
    if(this.state.width > 1000) {
      return this.getBoardHoriz(fakeContents);
    } else {
      return this.getBoardVert(fakeContents);
    }
  }
  render() {
    let board = null;
    if (this.state.game == null) {
      board = this.getBoard();
    } else {
      board = (<div>Accept invite or  </div>);
    }
    return (
      <Panel name="Game" startCollapsed={true}>
        {board}
      </Panel>
    );
  }
}

export default Game