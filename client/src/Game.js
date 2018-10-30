import React, {Component} from 'react';
import Panel from './Panel.js';
import Cell from './Cell.js'

class Game extends Component{
  constructor(props){
    super(props);
    this.state = {startX: null, startY: null};
    this.move = this.move.bind(this);
    this.flip = this.flip.bind(this)
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
  getRow(y, contents){
    let out = [];
    for( let i = 0; i < 8; i++ ){
      out.push(<div className="col-1"><Cell piece={contents[i].piece} isFlipped={contents[i].isFlipped} x={i} y={y} move={this.move} flip={this.flip}/></div>)
    }
    return <div className="row"><div className="col-2"/> {out} <div className="col-2"/></div>;
  }
  getBoard(){
    //TODO: Use API call to get actual contents of rows and pass them along
    let fakeContents = [{piece:"S", isFlipped:true},{piece:"S",isFlipped:false},{},{},{},{},{},{}];
    return(
    <div>
      {this.getRow(0, fakeContents)}
      <br/>
      {this.getRow(1, fakeContents)}
      <br/>
      {this.getRow(2, fakeContents)}
      <br/>
      {this.getRow(3, fakeContents)}
    </div>
    )
  }
  render() {
    return (
      <Panel name="Game" startCollapsed={true}>
        {this.getBoard()}
      </Panel>
    )
  }
}

export default Game;