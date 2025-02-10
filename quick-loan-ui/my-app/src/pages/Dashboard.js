import React from 'react';
import { Box, Typography } from '@mui/material';
import Sidebar from '../components/layout/Sidebar';

export default function Dashboard() {
  return (
    <Box sx={{ display: 'flex', height: '100vh', backgroundColor: '#f0f0f0' }}>
      {/* Sidebar */}
      <Sidebar />

      {/* Main Content Area */}
      <Box sx={{ flex: 1, marginLeft: '250px', padding: 4 }}>
        <Typography variant="h4" sx={{ fontWeight: 'bold', mb: 2 }}>
          Dashboard
        </Typography>
        <Typography variant="body1" sx={{ color: '#555' }}>
          Welcome to your dashboard! Here you can manage your data and settings.
        </Typography>
      </Box>
    </Box>
  );
}
