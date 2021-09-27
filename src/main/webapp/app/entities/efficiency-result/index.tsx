import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EfficiencyResult from './efficiency-result';
import EfficiencyResultDetail from './efficiency-result-detail';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EfficiencyResultDetail} />
      <ErrorBoundaryRoute path={match.url} component={EfficiencyResult} />
    </Switch>
  </>
);

export default Routes;
