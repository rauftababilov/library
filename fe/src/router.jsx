import { Switch, Route } from 'react-router-dom';

import HomePage from './pages/home';
import LoginPage from './pages/login';
import BooksPage from './pages/books';
import AuthorsPage from './pages/authors';
import PublishersPage from './pages/publishers';
import ClientsPage from 'pages/clients';

import { AppLayout } from 'components/ui/layout';
import { routesURLs } from 'common/constants';

export function Router() {
  return (
    <Switch>
      <Route path={routesURLs.login} component={LoginPage} />

      <AppLayout>
        <Switch>
          <Route path={routesURLs.books} component={BooksPage} />
          <Route path={routesURLs.authors} component={AuthorsPage} />
          <Route path={routesURLs.publishers} component={PublishersPage} />
          <Route path={routesURLs.clients} component={ClientsPage} />
          <Route path={routesURLs.home} component={HomePage} />
        </Switch>
      </AppLayout>
    </Switch>
  );
}
