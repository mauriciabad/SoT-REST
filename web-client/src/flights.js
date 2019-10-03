import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  DateField,
  NumberField,
  ArrayField,
  SingleFieldList,
  ChipField,
  EditButton,
  Edit,
  SimpleForm,
  TextInput,
  DateInput,
  BooleanInput,
  NumberInput,
  ArrayInput,
  SimpleFormIterator
} from 'react-admin';

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
      {/* <ArrayField source="tickets">
            <Datagrid>
              <NumberField source="id" />
              <NumberField source="price" />
              <TextField source="seat" />
              <BooleanField source="forSale" />
            </Datagrid>
          </ArrayField> */}
      <ArrayField source="tickets"><SingleFieldList><ChipField source="seat" /></SingleFieldList></ArrayField>
      <EditButton />
    </Datagrid>
  </List>
);

export const FlightEdit = props => (
  <Edit {...props}>
    <SimpleForm>
      <TextInput source="airline" />
      <DateInput source="arrival" />
      <BooleanInput source="cheapestTicket.forSale" />
      <DateInput source="departure" />
      <TextInput source="destination" />
      <TextInput source="id" />
      <TextInput source="origin" />
      <NumberInput source="price" />
      <ArrayInput source="tickets"><SimpleFormIterator><BooleanInput source="forSale" />
        <TextInput source="id" />
        <NumberInput source="price" />
        <TextInput source="seat" /></SimpleFormIterator></ArrayInput>
    </SimpleForm>
  </Edit>
);