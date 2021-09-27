import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntities } from './area.reducer';
import { IArea } from 'app/shared/model/area.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const Area = (props: RouteComponentProps<{ url: string }>) => {
  const dispatch = useAppDispatch();

  const areaList = useAppSelector(state => state.area.entities);
  const loading = useAppSelector(state => state.area.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  const { match } = props;

  return (
    <div>
      <h2 id="area-heading" data-cy="AreaHeading">
        <Translate contentKey="jpolenApp.area.home.title">Areas</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="jpolenApp.area.home.refreshListLabel">Refresh List</Translate>
          </Button>
        </div>
      </h2>
      <div className="table-responsive">
        {areaList && areaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="jpolenApp.area.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="jpolenApp.area.name">Name</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {areaList.map((area, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${area.id}`} color="link" size="sm">
                      {area.id}
                    </Button>
                  </td>
                  <td>{area.name}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${area.id}`} color="info" size="sm" data-cy="entityDetailsButton">
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
              <Translate contentKey="jpolenApp.area.home.notFound">No Areas found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Area;
