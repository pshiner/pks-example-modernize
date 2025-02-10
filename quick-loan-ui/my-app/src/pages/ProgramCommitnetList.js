import React from 'react';
import { Box, Typography } from '@mui/material';
import Sidebar from '../components/layout/Sidebar';
import ProgramCommitmentTable from '../components/ProgramCommitment/ProgramCommitmentTable'

export default function Dashboard() {
  return (
    <Box sx={{ display: 'flex', height: '100vh', backgroundColor: '#f0f0f0' }}>
      {/* Sidebar */}
      <Sidebar />
      <ProgramCommitmentTable/>
    </Box>
  );
}


  
  