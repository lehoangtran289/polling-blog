import React, { Component } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ListPostsComponent from "./ListPostsComponent";
import PostComponent from "./PostComponent";
import searchPost from './searchPost.js';

class InstructorApp extends Component {
	render() {
		return (
			<Router>
					<Switch>
						<Route path="/" exact component={ListPostsComponent}></Route>
						<Route path="/posts" exact component={ListPostsComponent} />
						<Route path="/posts/:id" component={PostComponent} />
						<Route path="/search" component={searchPost}/>
					</Switch>
			</Router>
		);
	}
}

export default InstructorApp;
