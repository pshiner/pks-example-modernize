import React from 'react';
import { AppBar, Toolbar, Typography, Box, Container } from '@mui/material';
import { ReactComponent as ReactLogo } from '../../logo.svg';

export default function Header() {
    return (
      <AppBar position="static" sx={{ background: 'linear-gradient(to right, #3b82f6, #9333ea)', borderRadius: '16px', boxShadow: 3 }}>
        <Container maxWidth="md">
          <Toolbar sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', py: 2 }}>
            <ReactLogo style={{ width: '60px', height: '60px', marginRight: '16px' }} />
            <Typography variant="h4" component="h1" sx={{ fontWeight: 'bold', color: 'white' }}>
              Welcome to FFB
            </Typography>
          </Toolbar>
        </Container>
      </AppBar>
    );
  }
  