import React, {Component} from 'react';
import Panel from './Panel.js';

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
    if( this.state.userBox ){
      console.log("Inviting " + this.state.userBox);
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
  getInvites(invFunc){
    //TODO: Make an API call to get the current user's invites.
    let invites = [{fromUser:"urMom",toUsers:[{username: "ripharambe"},{username: "xXxELITESNIPERxXx"}]},{fromUser:"banquiFreak123", toUsers:[{username: "noscope420xD"}]}];
    let out = [];
    for( let idx in invites ){
      out.push(invFunc(invites[idx]));
    }
    return (
      <ul className="card list-group list-group-flush">
        {out}
      </ul>
    );
  }
  render() {
    return (
      <Panel name="Invitations" startCollapsed={true}>
        {this.getInvites(this.getReceivedInvite)}
        <br/>
        {this.getInvites(this.getSentInvite)}
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

export default Invites;