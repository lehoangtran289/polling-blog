import React, { Component } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import PostDataService from "../Services/PostDataService";

class PostComponent extends Component {
	constructor(props) {
		super(props);
		this.state = {
			id: this.props.match.params.id,
			content: ""
		};
		this.handleSubmit = this.handleSubmit.bind(this);
		this.validate = this.validate.bind(this);
	}

	//We would want to call the getPostById method in PostDataService on the load of PostComponent
	componentDidMount() {
		// console.log(this.state.id);
		if (this.state.id === -1) {
			return;
		}

		PostDataService.getPostById(this.state.id).then(response => {
			this.setState({
				content: response.data.content
			});
		});
	}

	handleSubmit = values => {
		let newPost = {
			id: values.id,
			content: values.content
		};
		if (this.state.id === -1) {
			PostDataService.createPost(newPost).then(() => {
				this.props.history.push(`/posts`);
			});
		} else {
			PostDataService.updatePostById(newPost.id, newPost).then(() => {
				this.props.history.push(`/posts`);
			});
		}

		console.log(values);
	};

	// references inside <Formik/> docs
	validate = values => {
		const errors = {};
		if (!values.content) {
			errors.content = "Content cannot be blank!";
			console.log(errors);
		} else if (values.content.trim().length == 0) {
			errors.content = "Content must be visible!";
		}

		return errors;
	};

	//enableReinitialize={true} is needed to ensure that we can reload the form for existing todo.
	render() {
		let id = this.state.id;
		let content = this.state.content;
		return (
			<div>
				<h3>Post Details</h3>
				<div className="container">
					<Formik
						initialValues={{ id, content }}
						enableReinitialize={true}
						onSubmit={this.handleSubmit}
						validateOnChange={false}
						validateOnBlur={false}
						validate={this.validate}
					>
						{props => (
							<Form>
								{/* ErrorMessage: name = A field's name in Formik state */}
								<ErrorMessage name="content" render={msg => <div className="alert alert-warning">{msg}</div>} />

								{this.state.id !== '-1' ? (
									<fieldset className="form-group">
										<label>Id</label>
										<Field className="form-control" type="text" name="id" disabled />
									</fieldset>
								) : null}

								<fieldset className="form-group">
									<label>Content</label>
									<Field className="form-control" type="text" name="content" />
								</fieldset>
								<button className="btn btn-success" type="submit">
									Save
								</button>
							</Form>
						)}
					</Formik>
				</div>
			</div>
		);
	}
}

export default PostComponent;
