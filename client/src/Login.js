import React, {Component} from 'react';
import Panel from './Panel.js';
import axios from 'axios';
import qs from 'qs';

class Login extends Component {
  constructor(props){
    super(props);
    this.state = {username:null, email:null, password:null};
    this.formUpdate = this.formUpdate.bind(this);
    this.logIn = this.logIn.bind(this);
    this.logOut = this.logOut.bind(this);
    this.createAccount = this.createAccount.bind(this)
  }
  formUpdate(event){
    this.setState({[event.target.id]: event.target.value});
  }
  logIn(){
    axios.post(this.props.server + '/login', qs.stringify({
      username: this.state.username,
      password: this.state.password,
      email: this.state.email
    }))
      .then(function (response) {
        this.props.updateUser({name: this.state.username});
      })
      .catch(function (error) {
        alert(error.response.data.message)
      });
  }
  logOut(){
    axios.get(this.props.server + '/logout');
    this.props.updateUser(null);
  }
  createAccount(){
    axios.post(this.props.server + '/user', {
      type: "user",
      username: this.state.username,
      password: this.state.password,
      email: this.state.email
    })
      .catch(function (error) {
        alert(error.response.data.message)
      });
  }
  getForms() {
    return (
      <div className="row">
        <div className="col-4">
          <div className="form-group">
            <label htmlFor="username">User Name</label>
            <input type="text" id="username" className="form-control" placeholder="Username" onChange={this.formUpdate}/>
          </div>
        </div>
        <div className="col-4">
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input type="email" id="email" className="form-control" placeholder="email@example.com" onChange={this.formUpdate}/>
          </div>
        </div>
        <div className="col-4">
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input type="password" id="password" className="form-control" onChange={this.formUpdate}/>
          </div>
        </div>
      </div>
    )
  }

  getButtons() {
    return (
      <div className="row">
        <div className="col-4">
          <button className="btn btn-primary btn-block" disabled={!((this.state.email || this.state.username) && this.state.password) || this.props.user} onClick={this.logIn}>Log In</button>
        </div>
        <div className="col-4">
          <button className="btn btn-primary btn-block" disabled={!this.props.user} onClick={this.logOut}>Log Out</button>
        </div>
        <div className="col-4">
          <button className="btn btn-primary btn-block" disabled={!(this.state.email && this.state.username && this.state.password)} onClick={this.createAccount}>Create Account</button>
        </div>
      </div>
    )
  }

  render() {
    return (
      <Panel name="Login">
        {this.getForms()}
        <hr/>
        {this.getButtons()}
      </Panel>
    )
  }
}

export default Login;