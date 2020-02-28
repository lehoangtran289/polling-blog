import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ListPostsComponent from "./ListPostsComponent";
import PostComponent from "./PostComponent";

class InstructorApp extends Component {
	render() {
		return (
			<Router>
				<>
					<Switch>
						{/* <Route path="/" exact component={ListPostsComponent}></Route> */}
						<Route path="/posts" exact component={ListPostsComponent} />
						<Route path="/posts/:id" component={PostComponent} />
					</Switch>
				</>
			</Router>
		);
	}
}

export default InstructorApp;
