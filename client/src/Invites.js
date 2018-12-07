import React, {Component} from 'react';
import Panel from './Panel.js';
import axios from "axios";

//import qs from "qs";

class Invites extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userBox: null,
            rInvites: [],
            sInvites: []
        };

        this.handleChange = this.handleChange.bind(this);
        this.sendInvite = this.sendInvite.bind(this);
        this.getInvites = this.getInvites.bind(this);
        this.updateInvites = this.updateInvites.bind(this);
    }

    handleChange(event) {
        this.setState({userBox: event.target.value})
    }

    componentWillReceiveProps(props) {
        console.log("inside componentWillReceiveProps")
        console.log("info in componentWillReceive: username=" + props.user + " " + JSON.stringify(this.state))
        //do post requests, set rInvites and sInvites if need be
        if (props.user != null) {
            console.log("calling updateInvites")
            this.updateInvites(props.user);
        }

    }


    sendInvite() {
        //TODO: API Call
        console.log("sending invite")
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

    acceptInvite(invite) {
        //TODO: API Call
        console.log(invite)
    }

    rejectInvite(invite) {
        //TODO: API Call
        console.log(invite)
    }

    cancelInvite(invite) {
        //TODO: API Call
        console.log(invite)
    }

    getReceivedInvite(invite) {
        return (
            <li className="list-group-item">
                <div className="row">
                    <div className="col-8">{invite.fromUser} has invited you to a game!</div>
                    <button className="btn btn-primary col-2" onClick={() => this.acceptInvite(invite)}>Accept</button>
                    <button className="btn btn-danger col-2" onClick={() => this.rejectInvite(invite)}>Reject</button>
                </div>
            </li>
        )
    }

    getSentInvite(invite) {
        let userString = "Invite sent to: ";

        userString += invite.toUser[0] + ", ";

        return (
            <li className="list-group-item">
                <div className="row">
                    <div className="col-10">{userString.substr(0, userString.length - 2)}</div>
                    <button className="btn btn-danger float-right col-2"
                            onClick={() => this.cancelInvite(invite)}>Cancel
                    </button>
                </div>
            </li>
        )
    }

    updateInvites(username) {
        console.log("info inside updateInvites: username=" + username + " " + JSON.stringify(this.state))
        let resp1 = function (response) {
            //recInvites and sentInvites are arrays of objects
            console.log("setting state inside updateInvites")
            this.setState({
                rInvites: response.data.users[0].receivedInvites,
                sInvites: response.data.users[0].invites
            });
        }.bind(this);

        let err = function (error) {
            console.log(error)
        }.bind(this);
        axios.post(this.props.server + '/query', {
            type: "user",
            username: username
        }).then(resp1).catch(err);
    }

    getInvites() {
        console.log("info inside getInvites: username=" + this.props.user + " " + JSON.stringify(this.state))
        let rInvitesOut = [];
        for (let idx in this.state.rInvites) {
            rInvitesOut.push(this.getReceivedInvite(this.state.rInvites[idx]));
        }

        let sInvitesOut = [];
        for (let idx in this.state.sInvites) {
            sInvitesOut.push(this.getSentInvite(this.state.sInvites[idx]));
        }

        //avoid generating html if no invites are available
        if (this.state.rInvites.length != 0 || this.state.sInvites.length != 0) {
            console.log("building sent and received invites")
            return (
                <div>
                    <ul className="card list-group list-group-flush">
                        {rInvitesOut}
                    </ul>
                    <ul className="card list-group list-group-flush">
                        {sInvitesOut}
                    </ul>
                </div>
            );

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
                    <input type="text" id="username" className="form-control" placeholder="User Name"
                           onChange={this.handleChange}/>
                    <div className="input-group-append">
                        <button className="btn btn-primary" type="button" onClick={this.sendInvite}>Send</button>
                    </div>
                </div>
            </Panel>
        )
    }
}


export default Invites;

