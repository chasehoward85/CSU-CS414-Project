import React from 'react';
import { Container, Form, FormGroup, Label, Input, Row, Col, Button } from 'reactstrap';
import { useSignIn } from '../../hooks/useSignIn';

export default function SignIn(props) {
    const signInForm = useSignIn(props.showMessage);

	return (
		<Container>
            <Form>
                {props.unregister ? <h2><br/>Unregister</h2> :
                <Row>
                    <Col>
                        <h2>{signInForm.logIn ? 'Log In' : 'Sign Up'}</h2>
                        <Button color={'primary'} onClick={signInForm.toggleLogIn}>{signInForm.logIn ? 'Sign Up a New Account' : 'Log In with Existing Account'}</Button>
                    </Col>
                </Row>
                }


                <FormGroup row>
                    <Label for="emailInput" sm={2}>Email</Label>
                    <Col xs={8} sm={8} md={9} lg={10} xl={10}>
                        <Input
                            type="email"
                            name="emailInput"
                            id="emailInput"
                            placeholder="Email"
                            autoComplete={'email'}
                            invalid={!signInForm.validUserEmail}
                            onChange={event => signInForm.validateUserEmail(event.target.value)}
                            />
                    </Col>
                </FormGroup>
                <FormGroup row>
                    <Label for="passwordInput" sm={2}>Password</Label>
                    <Col xs={8} sm={8} md={9} lg={10} xl={10}>
                        <Input
                            type="password"
                            name="passwordInput"
                            id="passwordInput"
                            placeholder="Password"
                            autoComplete={signInForm.logIn ? 'current-password' : 'new-password'}
                            onChange={event => signInForm.validateUserPassword(event.target.value)}
                            />
                    </Col>
                </FormGroup>


                {!signInForm.logIn && !props.unregister && <>
                    <FormGroup row>
                        <Label for="passwordConfirmInput" sm={2}>Confirm Password</Label>
                        <Col xs={8} sm={8} md={9} lg={10} xl={10}>
                            <Input
                                type="password"
                                name="passwordConfirmInput"
                                id="passwordConfirmInput"
                                placeholder="Re-Enter Password"
                                autoComplete={'new-password'}
                                onChange={event => signInForm.confirmUserPassword(event.target.value)}
                                />
                        </Col>
                    </FormGroup>
                    
                    <FormGroup row>
                    <Label for="userNameInput" sm={2}>New User Name</Label>
                        <Col xs={8} sm={8} md={9} lg={10} xl={10}>
                            <Input
                                type="text"
                                name="userNameInput"
                                id="userNameInput"
                                placeholder="New User Name"
                                autoComplete={'email'}
                                invalid={!signInForm.validUserEmail}
                                onChange={event => signInForm.setNewUserName(event.target.value)}
                                />
                        </Col>
                    </FormGroup>
                </>}


                <Row xs={'auto'}>
                    <Col>
                        <Button color={props.unregister ? "danger" : "primary"} onClick={props.unregister ? signInForm.unregisterUser : signInForm.submit}>
                            {props.unregister ? "Unregister" : "Submit"}
                        </Button>
                    </Col>
                </Row>
            </Form>
		</Container>
	)
}
