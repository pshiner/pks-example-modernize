import React, { useState } from 'react';
import { Box, List, ListItem, ListItemText, Collapse, Divider } from '@mui/material';
import ExpandLessIcon from '@mui/icons-material/ExpandLess';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { Link } from 'react-router-dom';

export default function Sidebar() {
  const [expandMore1, setExpandMore1] = useState(false);
  const [expandMore2, setExpandMore2] = useState(false);

  const toggleSubgroup1 = () => setExpandMore1(!expandMore1);
  const toggleSubgroup2 = () => setExpandMore2(!expandMore2);

  return (
    <Box
      component="nav"
      sx={{
        width: '240px',
        height: 'calc(100vh - 95px)',
        color: 'black',
        position: 'fixed',
        top: '95px',
        left: 0,
        display: 'flex',
        flexDirection: 'column',
        padding: 2,
        zIndex: 1200,
      }}
    >
      <Divider sx={{ backgroundColor: 'rgba(0, 0, 0, 0.1)', mb: 2 }} />
      <List>
        {/* Group 1 */}
        <ListItem button onClick={toggleSubgroup1} sx={{ padding: '12px 20px', borderRadius: '8px', '&:hover': { backgroundColor: 'rgba(0, 0, 0, 0.05)' } }}>
          <ListItemText primary="More Options 1" />
          {expandMore1 ? <ExpandLessIcon /> : <ExpandMoreIcon />}
        </ListItem>
        <Collapse in={expandMore1} timeout="auto" unmountOnExit>
          <List component="div" disablePadding>
            <ListItem button component={Link} to="/programcommitmentlist" sx={{ pl: 4, '&:hover': { backgroundColor: 'rgba(0, 0, 0, 0.05)' } }}>
              <ListItemText primary="Program Commmitment" />
            </ListItem>
            <ListItem button component={Link} to="/installation" sx={{ pl: 4, '&:hover': { backgroundColor: 'rgba(0, 0, 0, 0.05)' } }}>
              <ListItemText primary="Installation" />
            </ListItem>
          </List>
        </Collapse>

        {/* Group 2 */}
        <ListItem button onClick={toggleSubgroup2} sx={{ padding: '12px 20px', borderRadius: '8px', mt: 1, '&:hover': { backgroundColor: 'rgba(0, 0, 0, 0.05)' } }}>
          <ListItemText primary="More Options 2" />
          {expandMore2 ? <ExpandLessIcon /> : <ExpandMoreIcon />}
        </ListItem>
        <Collapse in={expandMore2} timeout="auto" unmountOnExit>
          <List component="div" disablePadding>
            <ListItem button component={Link} to="/components" sx={{ pl: 4, '&:hover': { backgroundColor: 'rgba(0, 0, 0, 0.05)' } }}>
              <ListItemText primary="Components" />
            </ListItem>
            <ListItem button component={Link} to="/faq" sx={{ pl: 4, '&:hover': { backgroundColor: 'rgba(0, 0, 0, 0.05)' } }}>
              <ListItemText primary="FAQ" />
            </ListItem>
          </List>
        </Collapse>
      </List>
    </Box>
  );
}
