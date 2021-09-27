import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    data-cy="entity"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <>{/* to avoid warnings when empty */}</>
    <MenuItem icon="asterisk" to="/efficiency-result">
      <Translate contentKey="global.menu.entities.efficiencyResult" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/area">
      <Translate contentKey="global.menu.entities.area" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/project">
      <Translate contentKey="global.menu.entities.project" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
