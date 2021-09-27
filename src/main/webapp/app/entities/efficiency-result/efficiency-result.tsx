import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './efficiency-result.reducer';
import { IEfficiencyResult } from 'app/shared/model/efficiency-result.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const EfficiencyResult = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const efficiencyResultList = useAppSelector(state => state.efficiencyResult.entities);
  const loading = useAppSelector(state => state.efficiencyResult.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="efficiency-result-heading" data-cy="EfficiencyResultHeading">
        <Translate contentKey="jpolenApp.efficiencyResult.home.title">Efficiency Results</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="jpolenApp.efficiencyResult.home.refreshListLabel">Refresh List</Translate>
          </Button>
        </div>
      </h2>
      <div className="table-responsive">
        {efficiencyResultList && efficiencyResultList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="jpolenApp.efficiencyResult.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="jpolenApp.efficiencyResult.name">Name</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {efficiencyResultList.map((efficiencyResult, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${efficiencyResult.id}`} color="link" size="sm">
                      {efficiencyResult.id}
                    </Button>
                  </td>
                  <td>{efficiencyResult.name}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${efficiencyResult.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="jpolenApp.efficiencyResult.home.notFound">No Efficiency Results found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default EfficiencyResult;
