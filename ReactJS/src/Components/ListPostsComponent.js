import React, { Component } from "react";
import PostDataService from "../Services/PostDataService";

class ListPostsComponent extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			posts: [],
			message: null
		};
		this.refreshPosts = this.refreshPosts.bind(this);
        this.handleDeletePost = this.handleDeletePost.bind(this);
        this.handleUpdatePost = this.handleUpdatePost.bind(this);
        this.handleAddPost = this.handleAddPost.bind(this);
	}

	componentDidMount() {
		this.refreshPosts();
	}

	handleDeletePost = id => {
		PostDataService.deletePostById(id).then(response => {
			this.setState({
				message: `IDpost = ${id} deleted`
			});
			this.refreshPosts();
		});
    };
    
    handleUpdatePost = (id) => {
        console.log('updated: ' + id);
        this.props.history.push(`/posts/${id}`); // redirect to localhost:3000/posts/id
    }

    handleAddPost = () => {
        this.props.history.push(`/posts/-1`); // redirect to localhost:3000/posts/-1 to add
    }

	refreshPosts = () => {
		PostDataService.getAllPosts().then(response => {
			console.log(response);
			this.setState({
				posts: response.data
			});
		});
	};

	render() {
		return (
			<div className="container">
				<h3>All Posts</h3>
				{this.state.message ? <div className="alert alert-success">{this.state.message}</div> : null}
				<div className="container">
					<table className="table">
						<thead>
							<tr>
								<th>Id</th>
								<th>Content</th>
								<th>Update</th>
								<th>Delete</th>
							</tr>
						</thead>
						<tbody>
							{this.state.posts.map(post => {
								return (
									<tr key={post.id}>
										<td>{post.id}</td>
										<td>{post.content}</td>
										<td>
											<button
												className="btn btn-primary"
												onClick={() => {
													this.handleUpdatePost(post.id);
												}}
											>
												Update
											</button>
										</td>
										<td>
											<button
												className="btn btn-danger"
												onClick={() => {
													this.handleDeletePost(post.id);
												}}
											>
												Delete
											</button>
										</td>
									</tr>
								);
							})}
						</tbody>
					</table>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.handleAddPost}>Add Post</button>
                    </div>
				</div>
			</div>
		);
	}
}

export default ListPostsComponent;
