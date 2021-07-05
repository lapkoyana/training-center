import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class LessonList extends Component {

    constructor(props) {
        super(props);
        this.state = {lessons: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/lections')
            .then(response => response.json())
            .then(data => this.setState({lessons: data}));
    }

    async remove(id) {
        await fetch('/lections/${id}', {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedLessons = [...this.state.lessons].filter(i => i.id !== id);
            this.setState({lessons: updatedLessons});
        });
    }

    render() {
        const {lessons} = this.state;

        const lessonList = lessons.map(lesson => {
            return <tr key={lesson.id}>
                <td style={{whiteSpace: 'nowrap'}}>{lesson.topic}</td>
                <td>{lesson.dateOfClass}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/lections/" + lesson.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(lesson.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/lections/new">Add Lesson</Button>
                    </div>
                    <h3>Lessons</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Тема</th>
                            <th width="30%">Дата</th>
                            <th width="40%">Действия</th>
                        </tr>
                        </thead>
                        <tbody>
                        {lessonList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default LessonList;