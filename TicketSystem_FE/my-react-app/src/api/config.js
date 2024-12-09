export const API_BASE_URL = 'http://localhost:8081';

export const endpoints = {
  customer: {
    getAll: '/customer/get-all-customer',
    add: '/customer/add-customer',
    update: '/customer/update-customer',
    delete: '/customer/delete-customer'
  },
  vendor: {
    getAll: '/vendor/get-all-vendors',
    add: '/vendor/add-vendor',
    update: '/vendor/update-vendor',
    delete: '/vendor/delete-vendor',
    startThread: '/vendor/start-vendor-thread',
    stopThread: '/vendor/stop-vendor-thread'
  },
  ticket: {
    getAvailable: '/ticket/get-available-tickets',
    purchase: '/ticket/purchase-ticket'
  }
};