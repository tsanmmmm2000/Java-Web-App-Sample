import React from 'react';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import Button from "@material-ui/core/Button";
import { Alert, AlertTitle } from '@material-ui/lab';

const initialState = {
    contact: {
        firstName: "",
        lastName: "",
        address: ""
    },
    result: {}
}

export default class Content extends React.Component {
    constructor(props) {
        super(props);
        this.state = initialState;
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleSubmit() {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: 'cmd=create&first_name=' + this.state.contact.firstName +
            '&last_name=' + this.state.contact.lastName +
            '&address=' + this.state.contact.address
        };
        fetch('http://localhost:8080/Java-Web-App-Sample/contact', requestOptions)
          .then(response  => response.json())
          .then(data => { this.setState({ result: data }); })
    };

    handleChange(event) {
        const contact = this.state.contact;
        contact[event.target.name] = event.target.value;
        this.setState({ contact : contact });
    }

    render() {

        let message;
        if (this.state.result.success != undefined && this.state.result.success) {
            message = <Alert severity="success">Success</Alert>
        } else if (this.state.result.success != undefined && !this.state.result.success) {
            message = <Alert severity="error">
              <AlertTitle>Error</AlertTitle>
              {this.state.result.message}
            </Alert>
        }

        return(
            <React.Fragment>
                <Grid container spacing={3}>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            required
                            name="firstName"
                            value={this.state.contact.firstName}
                            label="First name"
                            onChange={this.handleChange}
                            fullWidth
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            required
                            name="lastName"
                            value={this.state.contact.lastName}
                            label="Last name"
                            onChange={this.handleChange}
                            fullWidth
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            required
                            name="address"
                            value={this.state.contact.address}
                            label="Address"
                            onChange={this.handleChange}
                            fullWidth
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <Button variant="contained" color="primary" onClick={this.handleSubmit}>Submit</Button>
                    </Grid>
                    <Grid item xs={12}>{message}</Grid>
                </Grid>
            </React.Fragment>
        );       
    }
}