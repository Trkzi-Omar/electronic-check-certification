export const environment = {
  production: false,
  apiUrl: 'http://localhost:8888',  // This will be our mock API base URL
  endpoints: {
    checkVerification: '/api/commercant/checks/verify',
    checkHistory: '/api/commercant/checks/history',
    analytics: '/api/analytics/operations'
  }
}; 