import React, { Component } from "react";
import "./App.css";

class AppNavbar extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
            value: '',
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleChange(event) {
		this.setState({ 
            value: event.target.value 
        });
    }
    
    handleSubmit(event) {
        alert('A name was submitted: ' + this.state.value);
        event.preventDefault();
      }

	render() {
		return (
			<div className="navbar navbar-expand-md navbar-dark bg-dark">
				<a className="navbar-brand" href="/">
					<h3>Post Managing Application</h3>
				</a>
				<button
					className="navbar-toggler"
					type="button"
					data-toggle="collapse"
					data-target="#navbarsExampleDefault"
					aria-controls="navbarsExampleDefault"
					aria-expanded="false"
					aria-label="Toggle navigation"
				>
					<span className="navbar-toggler-icon"></span>
				</button>
				<div className="collapse navbar-collapse" id="navbarsExampleDefault">
					<ul className="navbar-nav mr-auto">
						<li className="nav-item active">
							<a className="nav-link my-2 my-lg-0" href="/posts">
								<div className="Home">Home</div> <span className="sr-only">(current)</span>
							</a>
						</li>
						<li className="nav-item">
							<a className="nav-link" href="/posts/-1">
								Add a post
							</a>
						</li>
					</ul>
					<form className="form-inline mt-2 mt-md-0" onSubmit={this.handleSubmit}>
						<input class="form-control mr-sm-2" type="text" placeholder="Search a post" onChange={this.handleChange} />
						<button class="btn btn-outline-success my-2 my-sm-0" type="submit">
							Search
						</button>
					</form>
				</div>
			</div>
		);
	}
}

export default AppNavbar;
