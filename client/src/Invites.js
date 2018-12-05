import React, {Component} from 'react';
import Panel from './Panel.js';
import axios from "axios";
//import qs from "qs";

class Invites extends Component{
  constructor(props){
    super(props);
    this.state = {
        userBox: null
    };
    this.handleChange = this.handleChange.bind(this);
    this.sendInvite = this.sendInvite.bind(this);
    this.getInvites = this.getInvites.bind(this);
  }
  handleChange(event){
    this.setState({userBox: event.target.value})
  }
  sendInvite() {
      //TODO: API Call
      if (this.state.userBox) {
      axios.post(this.props.server + '/user', {
          type: "inv",
          toUser: this.state.userBox
      })
          .then(function (response) {
              alert(response.data.status)
          })
          .catch(function (error) {
              alert(error.response.data.msg)
          });
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
  getInvites() {
      //TODO: Make an API call to get the current user's invites.
      //modifies state -> triggers rerender

      //making sure we arrive at the method
      console.log("attempting to get invites")

      //username should be passed in props my app.js as this.props.user
        let resp1 = function(response){
            const userObj = JSON.parse(response.data.msg);
            console.log(userObj)
            let recvInvites = userObj.users.receivedInvites;
            let sentInvites = userObj.users.invites;
            // INVITES TEMPLATE: {{fromUser:"aboiuc234",toUsers:[{username: "ripharambe"},{username: "xXxELITESNIPERxXx"}]},{fromUser:"banqiFreak123", toUsers:[{username: "noscope419xD"}]}];

            let recvInvitesOut = [];
            for (let idx in recvInvites) {
                recvInvitesOut.push(this.getReceivedInvite(recvInvites[idx]));
            }
            let sentInvitesOut = [];
            for (let idx in sentInvites) {
                recvInvitesOut.push(this.getSentInvite(sentInvites[idx]));
            }

            return (
                <div>
                <ul className="card list-group list-group-flush">
                    {recvInvitesOut}
                </ul>
                <ul className="card list-group list-group-flush">
                     {sentInvitesOut}
                </ul>
                </div>
            );
        }.bind(this);

        let err = function(error){
          alert(error.response.data.msg)
        }.bind(this);

      //getting list of sent invites from username
          if(this.props.user==null) {
              console.log("no user logged in")
          return;
          }
          else{
              axios.post(this.props.server + '/query', {
                  type: "user",
                  username: this.props.user
              }).then(resp1).catch(err);
          }
    }
  render() {
      console.log("hey what's up")
    return (
      <Panel name="Invitations" startCollapsed={true}>
          {this.getInvites()}
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