import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import { API_BASE_URL, endpoints } from '../api/config';

function VendorList() {
  const [vendors, setVendors] = useState([]);
  const [newVendor, setNewVendor] = useState({
    id: '',
    name: '',
    ticketReleaseRate: ''
  });

  const [threadConfig, setThreadConfig] = useState({
    ticketCount: 5,
    price: 10,
    ticketType: 'EXHIBITION'
  });

  const [editingVendor, setEditingVendor] = useState(null);

  useEffect(() => {
    fetchVendors();
  }, []);

  const fetchVendors = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}${endpoints.vendor.getAll}`);
      setVendors(response.data.data);
    } catch (error) {
      toast.error('Error fetching vendors');
    }
  };

  const handleAddVendor = async (e) => {
    e.preventDefault();
    try {
      await axios.post(`${API_BASE_URL}${endpoints.vendor.add}`, newVendor);
      toast.success('Vendor added successfully');
      setNewVendor({ id: '', name: '', ticketReleaseRate: '' });
      fetchVendors();
    } catch (error) {
      toast.error('Error adding vendor');
    }
  };

  const handleDeleteVendor = (vendorID) => {
    if (window.confirm("Are you sure you want to delete this vendor?")) {
      axios
        .delete(`${API_BASE_URL}${endpoints.vendor.delete}?vendorID=${vendorID}`)
        .then(() => {
          toast.success('Vendor deleted successfully');
          // Remove the deleted vendor from the list
          setVendors(vendors.filter((vendor) => vendor.id !== vendorID));
        })
        .catch((error) => {
          toast.error('Error deleting vendor');
          console.error('Error deleting vendor:', error);
        });
    }
  };

  const startVendorThread = async (vendorId) => {

    try {
      // Ensure that the necessary data is included in the request
      const config = { 
        vendorID: vendorId, 
        ticketCount: threadConfig.ticketCount,
        price: threadConfig.price,
        ticketType: threadConfig.ticketType
      };
  
      // Send POST request to the backend to start the thread
      await axios.post(`${API_BASE_URL}${endpoints.vendor.startThread}?vendorID=${vendorId}`, config);
      toast.success('Vendor thread started successfully');
    } catch (error) {
      toast.error('Error starting vendor thread');
      console.error('Error starting thread:', error);
    }
  };

  const stopVendorThread = async (vendorId) => {
    try {
      await axios.get(`${API_BASE_URL}${endpoints.vendor.stopThread}?vendorID=${vendorId}`);
      toast.success('Vendor thread stopped successfully');
    } catch (error) {
      toast.error('Error stopping vendor thread');
    }
  };

  const handleEditClick = (vendor) => {
    setEditingVendor({ ...vendor }); // Open edit form with vendor details
  };

  const handleUpdateVendor = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`${API_BASE_URL}${endpoints.vendor.update}?vendorID=${editingVendor.id}`, editingVendor);
      toast.success('Vendor updated successfully');
      setEditingVendor(null); // Close edit form
      fetchVendors(); // Reload vendors after update
    } catch (error) {
      toast.error('Error updating vendor');
    }
  };

  return (
    <div className="container mt-4">
      <h2>Vendors</h2>

      {/* Add Vendor Form */}
      <div className="card mb-4">
        <div className="card-body">
          <h5 className="card-title">Add New Vendor</h5>
          <form onSubmit={handleAddVendor}>
            <div className="mb-3">
              <input
                type="text"
                className="form-control"
                placeholder="Vendor ID"
                value={newVendor.id}
                onChange={(e) => setNewVendor({ ...newVendor, id: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <input
                type="text"
                className="form-control"
                placeholder="Full Name"
                value={newVendor.name}
                onChange={(e) => setNewVendor({ ...newVendor, name: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <input
                type="number"
                className="form-control"
                placeholder="Ticket Release Rate (ms)"
                value={newVendor.ticketReleaseRate}
                onChange={(e) => setNewVendor({ ...newVendor, ticketReleaseRate: e.target.value })}
              />
            </div>
            <button type="submit" className="btn btn-primary">Add Vendor</button>
          </form>
        </div>
      </div>

    {/* Start Vendor Thread Form */}
      <div className="card mb-4">
        <div className="card-body">
          <h5 className="card-title">Start Vendor Thread</h5>
          <form onSubmit={(e) => e.preventDefault()}>
            <div className="mb-3">
              <label className="form-label">Ticket Count</label>
              <input
                type="number"
                className="form-control"
                placeholder="Ticket Count"
                value={threadConfig.ticketCount}
                onChange={(e) => setThreadConfig({ ...threadConfig, ticketCount: Number(e.target.value) })}
                required
                min="1"
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Ticket Price</label>
              <input
                type="number"
                className="form-control"
                placeholder="Ticket Price"
                value={threadConfig.price}
                onChange={(e) => setThreadConfig({ ...threadConfig, price: Number(e.target.value) })}
                required
                min="0.01"
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Ticket Type</label>
              <select
                className="form-select"
                value={threadConfig.ticketType}
                onChange={(e) => setThreadConfig({ ...threadConfig, ticketType: e.target.value })}
                required
              >
                <option value="SPORTS">Sports</option>
                <option value="CONCERT">Concert</option>
                <option value="THEATER">Theater</option>
                <option value="CONFERENCE">Conference</option>
                <option value="EXHIBITION">Exhibition</option>

              </select>
            </div>
            <button 
              type="button" 
              className="btn btn-primary"
              onClick={() => startVendorThread(vendors[0]?.id)}  // Start the thread for the first vendor for simplicity
            >
              Start Thread
            </button>
          </form>
        </div>
      </div>


      {/* Vendor Update Form */}
      {editingVendor && (
        <div className="card mb-4">
          <div className="card-body">
            <h5 className="card-title">Edit Vendor</h5>
            <form onSubmit={handleUpdateVendor}>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  value={editingVendor.name}
                  onChange={(e) => setEditingVendor({ ...editingVendor, name: e.target.value })}
                  required
                />
              </div>
              <div className="mb-3">
                <input
                  type="number"
                  className="form-control"
                  value={editingVendor.ticketsPerRelease}
                  onChange={(e) => setEditingVendor({ ...editingVendor, ticketReleaseRate: e.target.value })}
                  required
                />
              </div>
              <button type="submit" className="btn btn-primary">Update Vendor</button>
              <button
                type="button"
                className="btn btn-secondary ms-2"
                onClick={() => setEditingVendor(null)} // Reset editing state
              >
                Cancel
              </button>
            </form>
          </div>
        </div>
      )}

  {/* Vendor Table */}
      <div className="table-responsive">
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Release Rate</th>
              <th>Thread Control</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {vendors.map((vendor) => (
              <tr key={vendor.id}>
                <td>{vendor.id}</td>
                <td>{vendor.name}</td>
                <td>{vendor.ticketsPerRelease}ms</td>
                <td>
                  <button
                    className="btn btn-sm btn-success me-2"
                    onClick={() => startVendorThread(vendor.id)}
                  >
                    Start Thread
                  </button>
                  <button
                    className="btn btn-sm btn-warning"
                    onClick={() => stopVendorThread(vendor.id)}
                  >
                    Stop Thread
                  </button>
                </td>
                <td>
                <button
                    className="btn btn-sm btn-info me-2"
                    onClick={() => handleEditClick(vendor)} // Open edit form
                  >
                    Update
                  </button>
                  <button
                    className="btn btn-sm btn-danger"
                    onClick={() => handleDeleteVendor(vendor.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default VendorList;