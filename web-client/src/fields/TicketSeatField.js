import React from 'react';
import { withStyles } from '@material-ui/core/styles';
import { ChipField } from 'react-admin';

const styles = {
    seat: {
        fontSize: '0.6rem',
    }
};

const TicketSeatField = ({ record = {}, source, classes }) =>
    <ChipField source={source} className={classes.seat} />;

export default withStyles(styles)(TicketSeatField);