import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Area from './area';
import AreaDetail from './area-detail';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AreaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Area} />
    </Switch>
  </>
);

export default Routes;
