import React, {Component} from 'react';
import Panel from './Panel.js';

class Invites extends Component{
  getReceivedInvite(invite){
    return(
      <li className="list-group-item">
        <div className="row">
          <div className="col-8">{invite.fromUser} has invited you to a game!</div>
          <button className="btn btn-primary col-2">Accept</button>
          <button className="btn btn-danger col-2">Reject</button>
        </div>
      </li>
    )
  }
  getSentInvite(invite){
    let userString = "Invite sent to: ";
    let users = invite.toUsers;
    console.log(users);
    for( let idx in users ){
      userString += users[idx].username + ", ";
    }
    return(
      <li className="list-group-item">
        <div className="row">
          <div className="col-10">{userString.substr(0, userString.length-2)}.</div>
          <button className="btn btn-danger float-right col-2">Cancel</button>
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
          <input type="text" id="username" className="form-control" placeholder="User Name" onChange={this.props.updateServer}/>
          <div className="input-group-append">
            <button className="btn btn-primary" type="button">Send</button>
          </div>
        </div>
      </Panel>
    )
  }
}

export default Invites;