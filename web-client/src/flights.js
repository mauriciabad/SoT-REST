import React from 'react';
import { List, Datagrid, TextField, DateField, NumberField, ArrayField, SingleFieldList, ChipField } from 'react-admin';

export const FlightList = props => (
  <List {...props}>
      <Datagrid rowClick="edit">
          <NumberField source="id" />
          <TextField source="origin" />
          <TextField source="destination" />
          <DateField source="arrival" />
          <DateField source="departure" />
          <NumberField source="price" />
          <ChipField source="cheapestTicket.seat" />
          <TextField source="airline" />
          <ArrayField source="tickets"><SingleFieldList><ChipField source="seat" /></SingleFieldList></ArrayField>
      </Datagrid>
  </List>
);