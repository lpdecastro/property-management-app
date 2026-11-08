import {
  AppBar,
  Box,
  Button,
  Drawer,
  List,
  ListItemButton,
  ListItemText,
  Toolbar,
  Typography,
} from '@mui/material';
import { useState } from 'react';
import { Link as RouterLink, NavLink, Outlet } from 'react-router';

const drawerWidth = 240;

const navigation = [
  { label: 'Dashboard', to: '/dashboard' },
  { label: 'Properties', to: '/properties' },
  { label: 'Seasons', to: '/seasons' },
  { label: 'Investors', to: '/investors' },
  { label: 'Reports', to: '/reports' },
  { label: 'My Account', to: '/my-account' },
];

const AppLayout = () => {
  const [mobileOpen, setMobileOpen] = useState(false);

  const sidebar = (
    <>
      <Toolbar>
        <Typography variant='h6'>Property Manager</Typography>
      </Toolbar>

      <List component='nav' aria-label='Main navigation'>
        {navigation.map(({ label, to }) => (
          <ListItemButton
            key={to}
            component={NavLink}
            to={to}
            onClick={() => setMobileOpen(false)}
            sx={{
              '&.active': {
                bgcolor: 'action.selected',
                color: 'primary.main',
              },
            }}
          >
            <ListItemText primary={label} />
          </ListItemButton>
        ))}
      </List>
    </>
  );

  return (
    <Box sx={{ display: 'flex', minHeight: '100vh' }}>
      {/* Header */}
      <AppBar
        position='fixed'
        sx={{ width: { md: `calc(100% - ${drawerWidth}px)` } }}
      >
        <Toolbar sx={{ gap: 2 }}>
          <Button
            color='inherit'
            aria-label='Open navigation'
            onClick={() => setMobileOpen(true)}
            sx={{ display: { md: 'none' } }}
          >
            Menu
          </Button>

          <Typography sx={{ flexGrow: 1 }}>Property Management</Typography>

          <Button color='inherit' component={RouterLink} to='/my-account'>
            My Account
          </Button>
        </Toolbar>
      </AppBar>

      {/* Sidebar */}
      <Box component='aside' sx={{ width: { md: drawerWidth } }}>
        {/* Mobile */}
        <Drawer
          variant='temporary'
          open={mobileOpen}
          onClose={() => setMobileOpen(false)}
          sx={{
            display: { xs: 'block', md: 'none' },
            '& .MuiDrawer-paper': { width: drawerWidth },
          }}
        >
          {sidebar}
        </Drawer>

        {/* Desktop */}
        <Drawer
          variant='permanent'
          sx={{
            display: { xs: 'none', md: 'block' },
            '& .MuiDrawer-paper': { width: drawerWidth },
          }}
        >
          {sidebar}
        </Drawer>
      </Box>

      {/* Main */}
      <Box component='main' sx={{ flexGrow: 1, p: { xs: 2, md: 3 } }}>
        <Toolbar />
        <Outlet />
      </Box>
    </Box>
  );
};

export default AppLayout;
