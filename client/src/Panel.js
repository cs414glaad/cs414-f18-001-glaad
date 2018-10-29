import React, { Component } from 'react';

class Panel extends Component {
  constructor(props){
    super(props);
    this.state = {collapsed:this.props.startCollapsed};
    this.toggleCollapse = this.toggleCollapse.bind(this)
  }
  toggleCollapse(){
    this.setState({collapsed:!this.state.collapsed})
  }
  renderButton(){
    let symbol = '-';
    let text = 'Collapse Section';
    if( this.state.collapsed ){
      symbol = '+';
      text = 'Expand Section';
    }
    return(
      <button title={text} className="btn btn-primary float-right" onClick={this.toggleCollapse}>
        {symbol}
      </button>
    )
  }
  renderChildren(){
    if( !this.state.collapsed ){
      return(
        <div className="card-body">
          {this.props.children}
        </div>
      )
    }
  }
  render(){
    return(
      <div className="card">
        <h3 className="card-header">
          {this.props.name}
          {this.renderButton()}
        </h3>
        {this.renderChildren()}
      </div>
    )
  }
}

export default Panel;