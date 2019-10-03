import React from 'react';
import { List, Datagrid, TextField, NumberField } from 'react-admin';

export const UserList = props => (
  <List {...props}>
      <Datagrid rowClick="edit">
          <NumberField source="id" />
          <TextField source="name" />
      </Datagrid>
  </List>
);