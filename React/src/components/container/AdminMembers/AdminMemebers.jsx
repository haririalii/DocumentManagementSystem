import React, {Component} from 'react';
import BootstrapTable from "react-bootstrap-table-next";
import ToolkitProvider, {Search} from "react-bootstrap-table2-toolkit";
import filterFactory from "react-bootstrap-table2-filter";
import paginationFactory from "react-bootstrap-table2-paginator";
import "react-bootstrap-table-next/dist/react-bootstrap-table2.min.css";
import "react-bootstrap-table2-filter/dist/react-bootstrap-table2-filter.min.css";
import "react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css";
import "./adminMembers.css";
import {withRouter} from "react-router-dom";


class AdminMembers extends Component {
    constructor(props) {
        super(props);
        this.state = { 
            members: []
         }
    }

    shouldComponentUpdate(nextProps, nextState, nextContext) {
        return true;
    }

    componentDidMount = async () => {
    const token = localStorage.getItem("token");
    try {
    const response = await fetch(`http://localhost:8080/api/users/`, {
    method: "GET",
    headers: {
        Authorization: "Bearer" + token,
        "content-type": "application/json"
    }
    });
    const data = await response.json();
    this.setState({ members: data });
    } catch (error) {
    console.error(error);
    }
    console.log(this.state.members);
    }

    render() { 
    const bgStyle = {
        backgroundColor: "#519e8a"
    };

    const columns = [{
        dataField: 'id',
        text: 'شناسه',
        headerStyle: { backgroundColor: "#519e8a", width: "130px" }
    }, {
        dataField: 'name',
        text: 'نام و نام خانوادگی',
        headerStyle: bgStyle
    }, {
        dataField: 'username',
        text: 'نام کاربری',
        headerStyle: bgStyle
    }];
        
    const expandRow = {
    renderer: row => (
    <div className="itemDetails">
        <p>{ `نقش کاربر: ${row.roles[0].name}` }</p>
        <p>{ `تاریخ آخرین تغییرات: ${row.last_modification_date}` }</p>
        <p>{ `تاریخ ساخت حساب کاربری: ${row.creation_date}` }</p>
        <p>{ `گروه‌های کاربر:` }</p>
    </div>
    ),
    showExpandColumn: true
    };

    const { SearchBar } = Search;

    return ( 
    <div className="listContainer">
        <div className="listBox">
            <ToolkitProvider
                keyField="id"
                data={this.state.members}
                columns={columns}
                search
                >
                {props => (
                <div>
                    <SearchBar
                    {...props.searchProps}
                    style={{ width: "40%", marginBottom: "10px", float: "right" }}
                    />
                    <BootstrapTable
                    {...props.baseProps}
                    filter={filterFactory()}
                    // selectRow={selectRow}
                    pagination={paginationFactory()}
                    expandRow={ expandRow }
                    />
                </div>
                )}
            </ToolkitProvider>
        </div>
    </div>
        );
    }
}
 
export default withRouter(AdminMembers);