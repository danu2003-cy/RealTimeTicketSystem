import React from 'react';
import { Link } from 'react-router-dom';
import  './Dashboard.css';

import bg_img from '../assets/bg-img_new.png'

function Dashboard() {
  return (
    <div className="container-fluid">

      <div className="bg-img_new">
        <img src={bg_img} alt="" />
      </div>

      <h1 className="text-center ts-dashboard text-white mb-4">Ticket System Dashboard</h1>
      
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
              <p className="card-text">Manage vendors, control vendor threads and View vendors.</p>
              <Link to="/vendors" className="btn btn-primary">Go to Vendors</Link>
            </div>
          </div>
        </div>
        
        <div className="col-md-4 mb-4">
          <div className="card">
            <div className="card-body">
              <h5 className="card-title">Tickets</h5>
              <p className="card-text">Browse the available tickets in the pool and complete your purchase.</p>
              <Link to="/tickets" className="btn btn-primary">Go to Tickets</Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;