import React, {Component} from 'react';
import './square.css';

class Cell extends Component{
  constructor(props){
    super(props);
    this.clickMove = this.clickMove.bind(this);
  }
  clickMove(){
    this.props.move(this.props.id);
  }
  render() {
    //Type coercion - to prevent null pieces being displayed as unflipped pieces.
    if(this.props.piece.isFlipped === false)
    {
      return (
        <button className="btn btn-secondary square" onClick={this.clickMove}/>
      )
    }
    else if(this.props.piece.type){
      let className = "btn square ";
      if(this.props.piece.color === "RED"){
        className += "btn-danger";
      }
      else{
        className += "btn-dark"
      }
      return (
        <button className={className} onClick={this.clickMove}><div className="square-content">{this.props.piece.type}</div></button>
      )
    }
    else{
      return (
        <button className="btn btn-outline-secondary square" onClick={this.clickMove}/>
      )
    }
  }
}

export default Cell;