import React, {Component} from 'react';
import './square.css';

class Cell extends Component{
  constructor(props){
    super(props);
    this.clickMove = this.clickMove.bind(this);
    this.clickFlip = this.clickFlip.bind(this);
  }
  clickMove(){
    this.props.move(this.props.x, this.props.y);
  }
  clickFlip(){
    this.props.flip(this.props.x, this.props.y);
  }
  render() {
    if(!this.props.isFlipped && this.props.piece)
    {
      return (
        <button className="btn btn-secondary square" onClick={this.clickFlip}/>
      )
    }
    else if(this.props.piece){
      return (
        <button className="btn btn-primary square" onClick={this.clickMove}><div className="square-content">{this.props.piece}</div></button>
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