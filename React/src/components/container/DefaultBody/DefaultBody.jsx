import React, {Component} from "react";
import "./defaultBody.css";

class DefaultBody extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    return (
      <div className="containerDefaultBody">
        <div className="defaultBodyBox">
          <div className="mainInfo">
            <h1>به سامانه مدیریت اسناد خوش‌آمدید!</h1>
          </div>
          <div className="intro">
            <img
              // src="React/src/images/dms.png"
              alt="اسناد، قلب سازمان"
            />
          </div>
        </div>
      </div>
    );
  }
}

export default DefaultBody;
