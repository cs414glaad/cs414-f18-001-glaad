import React, {Component} from 'react';
import Panel from './Panel.js';
//import axios from "axios";
//import qs from "qs";

// this is to prevent errors. Uncomment when finished.
class Invites extends Component{
  constructor(props){
    super(props);
  }
  render() {
    return (
      <Panel name="Invitations" startCollapsed={true}>
      </Panel>
    );
  }
}

/*
class Invites extends Component{
  constructor(props){
    super(props);
    this.state = {userBox: null};
    this.handleChange = this.handleChange.bind(this);
    this.sendInvite = this.sendInvite.bind(this)
  }
  handleChange(event){
    this.setState({userBox: event.target.value})
  }
  sendInvite(){
    //TODO: API Call
      console.log(this.props.server)
    if( this.state.userBox ){
        axios.post(this.props.server + '/user', {
            type: "inv",
            toUser: this.state.userBox
        })
            .then(function (response) {
                alert(response.data.status)
            }.bind(this))
            .catch(function (error) {
                alert(error.response.data.msg)
            }.bind(this));
    }
  }
  acceptInvite(invite){
    //TODO: API Call
    console.log(invite)
  }
  rejectInvite(invite){
    //TODO: API Call
    console.log(invite)
  }
  cancelInvite(invite){
    //TODO: API Call
    console.log(invite)
  }
  getReceivedInvite(invite){
    return(
      <li className="list-group-item">
        <div className="row">
          <div className="col-8">{invite.fromUser} has invited you to a game!</div>
          <button className="btn btn-primary col-2" onClick={() => this.acceptInvite(invite)}>Accept</button>
          <button className="btn btn-danger col-2" onClick={() => this.rejectInvite(invite)}>Reject</button>
        </div>
      </li>
    )
  }
  getSentInvite(invite){
    let userString = "Invite sent to: ";
    let users = invite.toUsers;
    for( let idx in users ){
      userString += users[idx].username + ", ";
    }
    return(
      <li className="list-group-item">
        <div className="row">
          <div className="col-10">{userString.substr(0, userString.length-2)}.</div>
          <button className="btn btn-danger float-right col-2" onClick={() => this.cancelInvite(invite)}>Cancel</button>
        </div>
      </li>
    )
  }
  getInvites(invFunc) {
      //TODO: Make an API call to get the current user's invites.
          //getting the username
          axios.post(this.props.server + '/query', {
              type: "whoami"
          })
              .then(function (response) {
                  this.state.username = response.data.msg;
                  console.log(response.data.msg)
              }.bind(this))
              .catch(function (error) {
                  alert(error.response.data.msg)
              }.bind(this));

          //getting list of sent invites from username
          if(this.state.username) {
              axios.post(this.props.server + '/query', {
                  type: "user",
                  username: this.state.username
              })
                  .then(function (response) {
                      this.state.userObj = JSON.parse(response.data.msg);
                      console.log(this.state.userObj)
                  }.bind(this))
                  .catch(function (error) {
                      alert(error.response.data.msg)
                  }.bind(this));
          }
          /*
          let invites = [{fromUser:"aboiuc234",toUsers:[{username: "ripharambe"},{username: "xXxELITESNIPERxXx"}]},{fromUser:"banqiFreak123", toUsers:[{username: "noscope419xD"}]}];
          //this.state.userObj["invites"];
          let out = [];
          for (let idx in invites) {
              out.push(invFunc(invites[idx]));
          }
          *//*
      let out = [];
          return (
              <ul className="card list-group list-group-flush">
                  {out}
              </ul>
          );
    }
  render() {
      //{this.getInvites(this.getReceivedInvite)}
      //{this.getInvites(this.getSentInvite)}
    return (
      <Panel name="Invitations" startCollapsed={true}>
        <button className="btn btn-primary" type="button" onClick={this.getInvites(this.getReceivedInvite)}>Refresh Received Invites</button>
        <br/>
          <button className="btn btn-primary" type="button" onClick={this.getInvites(this.getSentInvite)}>Refresh Sent Invites</button>
        <br/>
        <div className="input-group">
          <div className="input-group-prepend">
            <span className="input-group-text">Create New Invite</span>
          </div>
          <input type="text" id="username" className="form-control" placeholder="User Name" onChange={this.handleChange}/>
          <div className="input-group-append">
            <button className="btn btn-primary" type="button" onClick={this.sendInvite}>Send</button>
          </div>
        </div>
      </Panel>
    )
  }
}
*/

export default Invites;

