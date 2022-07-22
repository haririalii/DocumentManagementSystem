import React, {Component} from "react";
import "./docNavContainer.css";
import {Link, withRouter} from "react-router-dom";
import MyDocumentsList from "../MyDocumentsList/MyDocumentsList";

class DocumentsNavContainer extends Component {
  constructor(props) {
    super(props);
    this.state = {
      selectedStatus: "",
      documents: [],
      acceptedDocuments: [],
      pendingDocuments: [],
      declinedDocuments: [],
      selected: [],
      documentsDb: []
    };
  }

  openDocumentsTable = selected => {
    this.setState({ selected });
  };

  componentDidMount() {
    this.setDifferentDocType();
  }

  shouldComponentUpdate(nextProps, nextState, nextContext) {
    return true;
  }

  setDifferentDocType = () => {
    this.setApproved();
    this.setPending();
    this.setRejected();
    this.setAll();
  };

  setApproved = () => {
    this.getDocsFromDb("APPROVED").then(
        r => this.setState({
          acceptedDocuments: r
        })
    );
  }

  setPending = () => {
    this.getDocsFromDb("PENDING").then(
        r => this.setState({
          pendingDocuments: r
        })
    );
  }

  setRejected = () => {
    this.getDocsFromDb("REJECTED").then(
        r => this.setState({
          declinedDocuments: r
        })
    );
  }

  setAll = () => {
    this.getDocsFromDb("").then(
        r => this.setState({
          documents: r
        })
    );
  }

  getDocsFromDb = async (status) => {
    const token = localStorage.getItem('token')
    const docs = await fetch('http://localhost:8080/api/documents/all?status=' + status, {
      method: 'GET',
      headers: {
        'Authorization': 'Bearer' + token,
        'content-type':'application/json'
      }
    });
    if (docs) {
      const data = await docs.json();

      if (data._embedded)
        return data._embedded.documentResponseDtoList;
    }
    return [];
  }

  render() {
    const documentsStatusList = [
      {
        id: 1,
        status: "سند‌های در انتظار تایید",
        document: this.state.pendingDocuments
      },
      {
        id: 2,
        status: "سند‌های تایید شده",
        document: this.state.acceptedDocuments
      },
      {
        id: 3,
        status: "سند‌های رد شده",
        document: this.state.declinedDocuments
      },
      { id: 4, status: "تمام سند‌ها", document: this.state.documents }
    ];

    return (
      <div className="documentsContainer">
        <div className="mydocumentsList">
          <MyDocumentsList allDocs={this.state.selected} />
        </div>
        <div className="navigationList">
          <div className="list-group-doc">
            <div className="list-group-item bg-dark text-light headerNav">
              <h4>سند‌های من</h4>
            </div>
            <div>
              <Link to="/createdocument" style={{ textDecoration: "none" }}>
                <button
                  type="button"
                  className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"

                >
                  <strong>+ ساخت سند جدید</strong>
                </button>
              </Link>
              {documentsStatusList.map(doc => (
                <button
                  key={doc.id}
                  type="button"
                  onClick={() => this.openDocumentsTable(doc.document)}
                  className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
                >
                  {doc.status}
                  <span className="badge badge-dark badge-pill">
                    {doc.document.length}
                  </span>
                </button>
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(DocumentsNavContainer);
