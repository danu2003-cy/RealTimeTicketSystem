import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import { API_BASE_URL, endpoints } from '../api/config';

function CustomerList() {
  const [customers, setCustomers] = useState([]);
  const [newCustomer, setNewCustomer] = useState({ 
    customerId: '', 
    name: '', 
    vip: 'false' 
  });

  const [editingCustomer, setEditingCustomer] = useState(null);

  useEffect(() => {
    fetchCustomers();
  }, []);

  const fetchCustomers = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}${endpoints.customer.getAll}`);
      const tableData = response.data.data.map((customer)=>({...customer, type: customer.vip ? "VIP" : "REGULAR"}));
      setCustomers(tableData);
      console.log(tableData);
    } catch (error) {
      toast.error('Error fetching customers');
    }
  };

  const handleAddCustomer = async (e) => {
    e.preventDefault();
    try {
      const customerData = {
        ...newCustomer,
        vip: newCustomer.vip === 'VIP'  // Convert the 'VIP' string to a boolean value
      };

      await axios.post(`${API_BASE_URL}${endpoints.customer.add}`, customerData);
      toast.success('Customer added successfully');
      setNewCustomer({ customerId: '', name: '', vip: 'REGULAR' });
      fetchCustomers();
    } catch (error) {
      toast.error('Error adding customer');
      console.error(error.response?.data || error);
    }
  };

  const handleDeleteCustomer = (customerId) => {
    if (window.confirm("Are you sure you want to delete this customer?")) {
      axios
        .delete(`${API_BASE_URL}${endpoints.customer.delete}?customerID=${customerId}`)
        .then((response) => {
          if (response.status === 201) {
            toast.success('Customer deleted successfully');
            // Remove the deleted customer from the list
            setCustomers(customers.filter((customer) => customer.customerId !== customerId));
          } else {
            toast.error('Error deleting customer');
          }
        })
        .catch((error) => {
          console.error('Error deleting customer:', error);
          toast.error('Failed to delete customer. Please try again later.');
        });
    }
  };


  const handleUpdateCustomer = async () => {
    try {
      console.log(editingCustomer,'Dhanushi')
      const payload= {...editingCustomer,vip: editingCustomer.vip==='VIP'? true:false}
      console.log(payload,'Dewmindi')
      await axios.put(`${API_BASE_URL}${endpoints.customer.update}`, payload);
      toast.success('Customer updated successfully');
      setEditingCustomer(null); // Close the editing mode
      fetchCustomers();
    } catch (error) {
      toast.error('Error updating customer');
    }
  };

  const handleEditClick = (customer) => {
    setEditingCustomer({ ...customer, type:undefined,vip:customer.vip===true? 'VIP':'REGULAR' });
  };

  const handleCancelEdit = () => {
    setEditingCustomer(null);
  };


  return (
    <div className="container mt-4">
      <h2>Customers</h2>
      
      <div className="card mb-4">
        <div className="card-body">
          <h5 className="card-title">Add New Customer</h5>
          <form onSubmit={handleAddCustomer}>
            <div className="mb-3">
              <input
                type="text"
                className="form-control"
                placeholder="Customer ID"
                value={newCustomer.customerId}
                onChange={(e) => setNewCustomer({ ...newCustomer, customerId: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <input
                type="text"
                className="form-control"
                placeholder="Full Name"
                value={newCustomer.name}
                onChange={(e) => setNewCustomer({ ...newCustomer, name: e.target.value })}
              />
            </div>
            <div className="mb-3">
              <select
                className="form-control"
                value={newCustomer.vip}
                onChange={(e) => setNewCustomer({ ...newCustomer, vip: e.target.value })}
              >
                <option value="REGULAR">Regular</option>
                <option value="VIP">VIP</option>
              </select>
            </div>
            <button type="submit" className="btn btn-primary">Add Customer</button>
          </form>
        </div>
      </div>


      {editingCustomer && (
        <div className="card mb-4">
          <div className="card-body">
            <h5 className="card-title">Edit Customer</h5>
            <form onSubmit={(e) => { e.preventDefault(); handleUpdateCustomer(); }}>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Customer ID"
                  value={editingCustomer.customerId}
                  disabled
                />
              </div>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Full Name"
                  value={editingCustomer.name}
                  onChange={(e) => setEditingCustomer({ ...editingCustomer, name: e.target.value })}
                />
              </div>
              <div className="mb-3">
                <select
                  className="form-control"
                  value={editingCustomer.vip}
                  onChange={(e) => setEditingCustomer({ ...editingCustomer, vip: e.target.value })}
                >
                  <option value="REGULAR">Regular</option>
                  <option value="VIP">VIP</option>
                </select>
              </div>
              <button type="submit" className="btn btn-success me-2">Update Customer</button>
              <button type="button" className="btn btn-secondary ml-2" onClick={handleCancelEdit}>Cancel</button>
            </form>
          </div>
        </div>
      )}



      <div className="table-responsive">
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Type</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {customers.map((customer) => (
              <tr key={customer.customerId}>
                <td>{customer.customerId}</td>
                <td>{customer.name}</td>
                <td>{customer.type}</td>
                <td>
                  <button
                    className="btn btn-sm btn-info me-2"
                    onClick={() => handleEditClick(customer)}
                  >
                    Update
                  </button>

                  <button
                    className="btn btn-sm btn-danger"
                    onClick={() => handleDeleteCustomer(customer.customerId)}
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

export default CustomerList;