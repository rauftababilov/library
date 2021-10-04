import { useEffect, useState } from 'react';
import { useLocation } from 'react-router';

import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import List from '@mui/material/List';

import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import HomeIcon from '@mui/icons-material/Home';
import BookIcon from '@mui/icons-material/Book';
import AuthorIcon from '@mui/icons-material/Create';
import PublisherIcon from '@mui/icons-material/Print';
import PeopleAltIcon from '@mui/icons-material/PeopleAlt';

import { ListItemRouterLink } from '../list-item-router-link';
import { AppBar } from './app-bar';
import { Drawer } from './drawer';

import { routesURLs } from 'common/constants';

const pageNameToRouteMap = {
  [routesURLs.books]: 'Books',
  [routesURLs.authors]: 'Authors',
  [routesURLs.publishers]: 'Publishers',
};

export function AppLayout({ children }) {
  const { pathname } = useLocation();
  const [drawerIsOpen, setDrawerIsOpen] = useState(true);
  const [matchingPageName, setMatchingPageName] = useState(null);

  useEffect(() => {
    const matchingRoute = Object.keys(pageNameToRouteMap).find((route) => pathname.includes(route));

    if (!matchingRoute) {
      setMatchingPageName(null);
      return;
    }

    setMatchingPageName(pageNameToRouteMap[matchingRoute]);
  }, [pathname]);

  const toggleDrawer = () => {
    setDrawerIsOpen(!drawerIsOpen);
  };

  return (
    <Box sx={{ display: 'flex' }}>
      <AppBar position="absolute" open={drawerIsOpen}>
        <Toolbar
          sx={{
            pr: '24px',
          }}
        >
          <IconButton
            edge="start"
            color="inherit"
            aria-label="open drawer"
            onClick={toggleDrawer}
            sx={{
              marginRight: '36px',
              ...(drawerIsOpen && { display: 'none' }),
            }}
          >
            <MenuIcon />
          </IconButton>
          <Typography component="h1" variant="h6" color="inherit" noWrap sx={{ flexGrow: 1 }}>
            Library {matchingPageName ? `| ${matchingPageName}` : ''}
          </Typography>
        </Toolbar>
      </AppBar>
      <Drawer variant="permanent" open={drawerIsOpen}>
        <Toolbar
          sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'flex-end',
            px: [1],
          }}
        >
          <IconButton onClick={toggleDrawer}>
            <ChevronLeftIcon />
          </IconButton>
        </Toolbar>
        <Divider />
        <List>
          <ListItemRouterLink to="/books" icon={<BookIcon />}>
            Books
          </ListItemRouterLink>
          <ListItemRouterLink to="/authors" icon={<AuthorIcon />}>
            Authors
          </ListItemRouterLink>
          <ListItemRouterLink to="/publishers" icon={<PublisherIcon />}>
            Publishers
          </ListItemRouterLink>
          <ListItemRouterLink to="/clients" icon={<PeopleAltIcon />}>
            Clients
          </ListItemRouterLink>
        </List>
      </Drawer>
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          height: '100vh',
          overflow: 'auto',
          background: '#eee',
        }}
      >
        <Toolbar />
        {children}
      </Box>
    </Box>
  );
}
