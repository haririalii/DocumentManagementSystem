import React, { Component } from "react";
import { Link, withRouter } from "react-router-dom";
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHome } from "@fortawesome/free-solid-svg-icons";
import "./header.css";

library.add(faHome);

class Header extends Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    return (
      <nav className="headerNavBar navbar fixed-top bg-dark">
        <div className="logo">
          <Link to="/">
            <FontAwesomeIcon icon="home" className="text-light" />
          </Link>
        </div>
        <div className="navBar">
          <ul className="nav justify-content-end">
            <li className="nav-item">
              <Link className="nav-link text-light" to="/login">
                ورود
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link text-light" to="/signup">
                ثبت نام
              </Link>
            </li>
          </ul>
        </div>
      </nav>
    );
  }
}

export default withRouter(Header);
