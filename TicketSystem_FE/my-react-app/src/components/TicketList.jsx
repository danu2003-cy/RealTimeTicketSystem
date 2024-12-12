import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import { API_BASE_URL, endpoints } from '../api/config';

function TicketList() {
  const [tickets, setTickets] = useState([]);
  const [customerId, setCustomerId] = useState('');

  useEffect(() => {
    fetchTickets();
  }, []);

  const fetchTickets = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}${endpoints.ticket.getAvailable}`);
      setTickets(response.data.data); // Adjusted based on backend response
    } catch (error) {
      toast.error('Error fetching tickets');
    }
  };

  const handlePurchaseTicket = async (ticketId) => {
    if (!customerId) {
      toast.error('Please enter a customer ID');
      return;
    }

    try {
      await axios.post(`${API_BASE_URL}${endpoints.ticket.purchase}`, null, {
        params: {
          customerId: customerId,
          ticketId: ticketId,
        },
      });
      toast.success('Ticket purchased successfully');
      fetchTickets(); // Refresh the ticket list
    } catch (error) {
      toast.error('Error purchasing ticket');
      console.error(error);
    }
  };

  return (
    <div className="container mt-4">
      <h2>Available Tickets</h2>
      
      <div className="mb-3">
        <input
          type="text"
          className="form-control"
          placeholder="Enter Customer ID to Purchase"
          value={customerId}
          onChange={(e) => setCustomerId(e.target.value)}
        />
      </div>

      <div className="row">
        {tickets.map((ticket) => (
          <div key={ticket.id} className="col-md-4 mb-4"> {/* 'ticketId' changed to 'id' */}
            <div className="card">
              <div className="card-body">
                <h5 className="card-title">{ticket.name}</h5>
                <p className="card-text">
                  <strong>Ticket ID:</strong> {ticket.id}<br />
                  <strong>Type:</strong> {ticket.ticketType}<br />
                  <strong>Price:</strong> ${ticket.price}<br />
                  <strong>Status: </strong>AVAILABLE {/* Access 'vendorID' and display its 'name' */}
                </p>
                <button
                  className="btn btn-primary"
                  onClick={() => handlePurchaseTicket(ticket.id)} // 'ticketId' changed to 'id'
                  disabled={!customerId}
                >
                  Purchase Ticket
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default TicketList;
