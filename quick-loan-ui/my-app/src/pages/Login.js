import React from 'react';
import { Box, Typography, Paper, TextField, Button } from '@mui/material';

export default function Login() {
  return (
    <div>

     
      <Box sx={{ mt: 4, p: 4 }}>
      {/* About Section */}
      <Box sx={{ mb: 4, textAlign: 'center', backgroundColor: '#f4f4f4', borderRadius: '12px', boxShadow: 2, p: 4 }}>
        <Typography variant="h5" sx={{ fontWeight: 'bold', mb: 2 }}>
          About Us
        </Typography>
        <Typography variant="body1" sx={{ color: '#555' }}>
          We are dedicated to providing the best experience for our users. Our goal is to continuously improve and innovate.
        </Typography>
      </Box>

      {/* Login Section */}
      <Paper sx={{ p: 4, maxWidth: '400px', mx: 'auto', textAlign: 'center', boxShadow: 3 }}>
        <Typography variant="h6" sx={{ mb: 2 }}>
          Login
        </Typography>
        <TextField label="Username" variant="outlined" fullWidth sx={{ mb: 2 }} />
        <TextField label="Password" type="password" variant="outlined" fullWidth sx={{ mb: 2 }} />
        <Button variant="contained" color="primary" fullWidth>
          Login
        </Button>
      </Paper>
    </Box>
      
        

    </div>
  );
}
