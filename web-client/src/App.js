// in src/App.js
import React from 'react';
import { Admin, Resource } from 'react-admin';
import jsonServerProvider from 'ra-data-json-server';

import { FlightList, FlightEdit, FlightCreate } from './flights';
import { UserList, UserEdit, UserCreate } from './users';

import FlightIcon from '@material-ui/icons/Flight';
import UserIcon from '@material-ui/icons/Group';

const dataProvider = jsonServerProvider('http://localhost:8080/airline/v1');
const App = () => (
    <Admin dataProvider={dataProvider}>
        <Resource name="flights" list={FlightList} icon={FlightIcon} edit={FlightEdit} create={FlightCreate} />
        <Resource name="users" list={UserList} icon={UserIcon} edit={UserEdit} create={UserCreate} />
    </Admin>
);
export default App;