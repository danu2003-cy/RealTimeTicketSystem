import React from 'react';
import { Link } from 'react-router-dom';
import  './Dashboard.css';

function Dashboard() {
  return (
    <div className="container-fluid">
      <h1 className="text-center mb-4">Ticket System Dashboard</h1>
      
      <div className="row">
        <div className="col-md-4 mb-4">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Customers</h5>
              <p className="card-text">Manage customer information and view customer list.</p>
              <Link to="/customers" className="btn btn-primary">Go to Customers</Link>
            </div>
          </div>
        </div>
        
        <div className="col-md-4 mb-4">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Vendors</h5>
              <p className="card-text">Manage vendors and control vendor threads.</p>
              <Link to="/vendors" className="btn btn-primary">Go to Vendors</Link>
            </div>
          </div>
        </div>
        
        <div className="col-md-4 mb-4">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Tickets</h5>
              <p className="card-text">View and purchase available tickets.</p>
              <Link to="/tickets" className="btn btn-primary">Go to Tickets</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;