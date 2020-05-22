import React from 'react';
import ReactDOM from "react-dom";
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Content from './content';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles((theme) => ({   
    layout: {
        width: 'auto',
        marginLeft: theme.spacing(2),
        marginRight: theme.spacing(2),
        [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
          width: 600,
          marginLeft: 'auto',
          marginRight: 'auto',
        },
    },    
    paper: {
        marginTop: theme.spacing(3),
        marginBottom: theme.spacing(3),
        padding: theme.spacing(2),
        [theme.breakpoints.up(600 + theme.spacing(3) * 2)]: {
          marginTop: theme.spacing(6),
          marginBottom: theme.spacing(6),
          padding: theme.spacing(3),
        },
    },
}));

function Person() {

    const classes = useStyles();

    return(
        <main className={classes.layout}>      
            <Paper className={classes.paper}>
                <Typography variant="h6" gutterBottom>Personal Information</Typography>
                <Content />
            </Paper>
        </main>              
    );
}

ReactDOM.render(<Person />, document.querySelector("#person"));
