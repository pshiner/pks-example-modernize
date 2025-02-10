import React from 'react';
import {Typography, Box, Container } from '@mui/material';


export default function Footer() {
    return (
        <Box component="footer" sx={{ background: 'linear-gradient(to right, #3b82f6, #9333ea)', color: 'white', textAlign: 'center', py: 2, mt: 4, borderRadius: '16px', boxShadow: 3 }}>
          <Typography variant="body2" sx={{ fontSize: '0.875rem' }}>
            &copy; {new Date().getFullYear()} My Website. All rights reserved.
          </Typography>
        </Box>
      );
}
