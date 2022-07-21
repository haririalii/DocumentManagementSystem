import React, { Component } from "react";
import { Link, withRouter  } from "react-router-dom";
import BootstrapTable from "react-bootstrap-table-next";
import ToolkitProvider, { Search } from "react-bootstrap-table2-toolkit";
import filterFactory from "react-bootstrap-table2-filter";
import paginationFactory from "react-bootstrap-table2-paginator";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";
import "react-bootstrap-table2-filter/dist/react-bootstrap-table2-filter.min.css";
import "react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css";
import "./myDocumentsList.css";
import Modal from "react-bootstrap/Modal";
import "../../richTextEditor/RichTextEditor";
import { Router } from "react-router";

import { EditorState, convertFromRaw, convertToRaw } from "draft-js";
import { Editor } from "react-draft-wysiwyg";
import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";

class DocumentsList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      modalShow: false,
      documentsList: [],
      selected: {}
    };
  }

  handleOnSelect = (row, isSelect) => {
    if (isSelect) {
      window.setTimeout(
        function() {
          this.setState({ selected: row });
        }.bind(this),
        0
      );
    }
  };

  render() {
    const bgStyle = {
      backgroundColor: "#519e8a"
    };
    const { SearchBar } = Search;
    const columns = [
      {
        dataField: "id",
        text: "شناسه",
        sort: true,
        headerStyle: { backgroundColor: "#519e8a", width: "150px" }
      },
      {
        dataField: "name",
        text: "نام",
        sort: true,
        headerStyle: bgStyle
      },
      {
        dataField: "type",
        text: "نوع",
        sort: true,
        headerStyle: bgStyle
      },
      {
        dataField: "status",
        text: "وضعیت",
        sort: true,
        headerStyle: { backgroundColor: "#519e8a", width: "200px" }
      },
      {
        dataField: "date",
        text: "تاریخ",
        sort: true,
        headerStyle: { backgroundColor: "#519e8a", width: "150px" }
      }
    ];
    const selectRow = {
      mode: "radio",
      clickToSelect: true,
      bgColor: "#edeeeebe",
      onSelect: (row, isSelect) => {
        this.handleOnSelect(row, isSelect);
      }
    };

    let modalClose = () => this.setState({ modalShow: false });

    return (
      <div className="myDocsListContainer">
        <div className="docsListBox">
          <ToolkitProvider
            keyField="id"
            data={this.props.allDocs}
            columns={columns}
            search
          >
            {props => (
              <div>
                <button
                  className="btn btn-dark"
                  style={{ marginRight: "5px" }}
                  onClick={() => this.setState({ modalShow: true })}
                >
                  بررسی
                </button>
                <button className="btn btn-dark" style={{ marginRight: "5px" }}>
                  تایید
                </button>
                <button className="btn btn-danger" style={{ marginRight: "5px" }}>
                  رد
                </button>
                <Modal
                  size="lg"
                  show={this.state.modalShow}
                  onHide={modalClose}
                  aria-labelledby="contained-modal-title-vcenter"
                  centered
                >
                  <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                      سند در دست بررسی
                    </Modal.Title>
                  </Modal.Header>
                  <Modal.Body>
                    {/* <Editor
                      editorState={this.state.editorState}
                      wrapperClassName="demo-wrapper"
                      editorClassName="editer-content"
                      onEditorStateChange={this.onChange}
                      spellCheck={true}
                      readOnly
                    /> */}
                    <p>شناسه سند: {this.state.selected.id}</p>
                    <p>نام سند: {this.state.selected.name}</p>
                    <p>ارسال کننده سند: {this.state.selected.surname}</p>
                    <p>نوع سند: {this.state.selected.type}</p>
                    <p>وضعیت سند: {this.state.selected.status}</p>
                    <p>تاریخ انتخاب سند: {this.state.selected.date}</p>
                    <p>مند انتخابی سند: {this.state.selected.text}</p>
                  </Modal.Body>
                  <Modal.Footer>
                    <button className="btn btn-dark" onClick={modalClose}>
                      ویرایش
                    </button>
                  </Modal.Footer>
                </Modal>
                <SearchBar
                  {...props.searchProps}
                  style={{ width: "40%", marginBottom: "10px", float: "right" }}
                />
                <BootstrapTable
                  {...props.baseProps}
                  filter={filterFactory()}
                  selectRow={selectRow}
                  pagination={paginationFactory()}
                />
              </div>
            )}
          </ToolkitProvider>
        </div>
      </div>
    );
  }
}

export default withRouter(DocumentsList);
