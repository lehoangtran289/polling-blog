import React, { Component } from "react";
import PostDataService from "../Services/PostDataService";
import AppNavbar from "../AppNavbar";

class searchPost extends Component {
	constructor(props) {
		super(props);
		this.state = {
			posts: [],
			content: ""
		};
	}

	componentDidMount() {
		PostDataService.getAllPosts().then(response => {
			// console.log("res", response);
			this.setState({
				posts: response.data
			});
		});
	}

	render() {
		console.log("search:", this.state.posts);
		return (
			<div>
				<AppNavbar />
				<div className="container mt-4">
					<h3>Search a Post by content</h3>
					<div className="form-group">
						<label>Content</label>
						<input
							className="form-control"
							type="text"
							onChange={event => {
								this.setState({
									content: event.target.value
								});
								console.log(event.target.value);
							}}
						/>
					</div>
				</div>
				<div className="container mt-5 ">
					<div className="table table-striped">
						<thead>
							<tr>
								<td>
									<h5>Id</h5>
								</td>
								<td className="px-5">
									<h5>Content</h5>
								</td>
							</tr>
						</thead>
						<tbody>
							{this.state.posts.map(post => {
								if (post.content.includes(this.state.content)) {
									return (
										<tr key={post.id}>
											<td>{post.id}</td>
											<td className="px-5">{post.content}</td>
										</tr>
									);
								}
							})}
						</tbody>
					</div>
				</div>
			</div>
		);
	}
}

export default searchPost;
