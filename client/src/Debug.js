import React, {Component} from 'react';
import Panel from './Panel.js';

class Debug extends Component{
  render() {
    return (
      <Panel name="Debug" startCollapsed={true}>
        <div className="row">
          <div className="col-12">
            <div className="form-group">
              <label htmlFor="server">Server</label>
              <input type="url" id="server" className="form-control" defaultValue="http://127.0.0.1:3001" onChange={this.props.updateServer}/>
            </div>
          </div>
        </div>
      </Panel>
    )
  }
}

export default Debug;