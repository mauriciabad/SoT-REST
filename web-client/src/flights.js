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
  SimpleFormIterator,
  DisabledInput,
  ReferenceInput,
  SelectInput,
  Create,
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
      <DisabledInput source="id" />
      <TextInput source="destination" />
      <TextInput source="origin" />
      <DateInput source="departure" />
      <DateInput source="arrival" />
      <TextInput source="airline" />
      <NumberInput source="price" />
      <ArrayInput source="tickets">
        <SimpleFormIterator>
          <DisabledInput source="id" />
          <NumberInput source="price" />
          <TextInput source="seat" />
          <ReferenceInput source="userId" reference="users">
            <SelectInput optionText="name" />
          </ReferenceInput>
          <BooleanInput source="forSale" />
        </SimpleFormIterator>
      </ArrayInput>
    </SimpleForm>
  </Edit>
);

export const FlightCreate = props => (
  <Create {...props}>
    <SimpleForm>
      <TextInput source="destination" />
      <TextInput source="origin" />
      <DateInput source="departure" />
      <DateInput source="arrival" />
      <TextInput source="airline" />
      <ArrayInput source="tickets">
        <SimpleFormIterator>
          <NumberInput source="id" />
          <NumberInput source="price" />
          <TextInput source="seat" />
          <ReferenceInput source="userId" reference="users">
            <SelectInput optionText="name" />
          </ReferenceInput>
          <BooleanInput source="forSale" />
        </SimpleFormIterator>
      </ArrayInput>
    </SimpleForm>
  </Create>
);