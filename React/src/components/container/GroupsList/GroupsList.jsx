import React, { Component } from "react";
import "./groupsList.css";
import { Link, withRouter } from "react-router-dom";
import GroupView from "../GroupView/GroupView";
import CreateGroup from "../CreateGroup/CreateGroup";
import { hasRole } from "../../Auth";

class GroupsList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isCreationButtonClicked: false,
      groups: [
        {
          name: "Vadovybė",
          id: 1,
          isClicked: false
        },
        {
          name: "Sekretoriatas",
          id: 2,
          isClicked: false
        },
        {
          name: "Teisės skyrius",
          id: 3,
          isClicked: false
        },
        {
          name: "IT skyrius",
          id: 4,
          isClicked: false
        },
        {
          name: "Personalo skyrius",
          id: 5,
          isClicked: false
        }
      ],
      groupSelected: "",
      isGroupViewButtonClicked: false,
      isUser: false,
    };
  }

  handleClick = e => {
    this.setState({
      isCreationButtonClicked: true,
      isGroupViewButtonClicked: false
    });
  };

  handleOpenClick = groupSelected => {
    this.setState({
      groupSelected,
      isGroupViewButtonClicked: true,
      isCreationButtonClicked: false
    });
  };

  componentDidMount = () => {
    this.settingUserRole();
  }

  settingUserRole = () => {
    const userRole = localStorage.getItem('role')
    
    if(userRole === "ROLE_ADMIN"){
      this.setState({
        isUser: false
      })
    }else if(userRole === "ROLE_USER"){
      this.setState({
        isUser: true
      })
    }
    
  }

  render() {

    return (
      <div className="groupsView groups">
        <div className="groupTableContainer">
          {this.state.isCreationButtonClicked && <CreateGroup />}
          {this.state.isGroupViewButtonClicked && (
            <GroupView group={this.state.groupSelected} />
          )}
        </div>
        <div className="groupListContainer">
          <div className="list-group-doc">
            <div className="list-group-item bg-dark text-light">
              <h4>گروه</h4>
            </div>
            <div>
              <button
                type="button"
                className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
                disabled={this.state.isUser}
                style={{ fontWeight: "bold" }}
                onClick={this.handleClick}
              >
                + ساخت گروه جدید
              </button>
              {this.state.groups.map(groupname => (
                <button
                  type="button"
                  className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
                  disabled={false}
                  key={groupname.id}
                  onClick={() => this.handleOpenClick(groupname.name)}
                >
                  {groupname.name}
                </button>
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(GroupsList);
