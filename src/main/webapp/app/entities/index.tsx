import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EfficiencyResult from './efficiency-result';
import Area from './area';
import Project from './project';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}efficiency-result`} component={EfficiencyResult} />
      <ErrorBoundaryRoute path={`${match.url}area`} component={Area} />
      <ErrorBoundaryRoute path={`${match.url}project`} component={Project} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
