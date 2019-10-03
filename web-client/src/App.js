// // in src/App.js
// import React from 'react';
// import { Admin, Resource, ListGuesser } from 'react-admin';
// import jsonServerProvider from 'ra-data-json-server';

// const dataProvider = jsonServerProvider('http://localhost:8080/airline/v1');
// const App = () => (
//     <Admin dataProvider={dataProvider}>
//         <Resource name="flights" list={ListGuesser} />
//     </Admin>
// );
// export default App;


// in src/App.js
import React from 'react';
import { Admin, Resource, EditGuesser } from 'react-admin';
import jsonServerProvider from 'ra-data-json-server';

import { FlightList } from './flights';
import { UserList } from './users';

import FlightIcon from '@material-ui/icons/Flight';
import UserIcon from '@material-ui/icons/Group';

const dataProvider = jsonServerProvider('http://localhost:8080/airline/v1');
const App = () => (
    <Admin dataProvider={dataProvider}>
        <Resource name="flights" list={FlightList} icon={FlightIcon} edit={EditGuesser} />
        <Resource name="users" list={UserList} icon={UserIcon} edit={EditGuesser} />
    </Admin>
);
export default App;