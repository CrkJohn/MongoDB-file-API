import React from 'react';
import { Todo } from './Todo'
import './Cards.css'
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import uuid from "uuid";
import axios from 'axios';


const obj = {
    'Jan': '01',
    'Feb': '02',
    'Mar': '03',
    'Apr': '04',
    'May': '05',
    'Jun': '06',
    'Jul': '07',
    'Aug': '08',
    'Sep': '09',
    'Oct': '10',
    'Nov': '11',
    'Dec': '02',
}


export class Cards extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            todoList: []
        };

        this.axios = axios.create({
            baseURL: 'http://localhost:8080/v1/',
            timeout: 1000,
            headers: { 'Authorization': 'Bearer ' + localStorage.getItem("Bearer") }
        });
    }

    Eq(month) {
        let res = Object.entries(obj).map(([key, value]) => {
            if (key === month) {
                return value;
            }
        })
        let result;
        res.forEach(element => {
            if (element !== undefined) {
                result = element;
            }
        });
        return result.toString();
    }

    async getTaskList() {
        var self = this;
        this.axios.get('/api/task')
            .then(function (response) {
                //console.log(response.data)
                var data = response.data;
                let tasksList = [];
                data.forEach(function (task) {
                    console.log(task)
                    const newItem = {
                        text: task.description,
                        name: task.members[0].name,
                        email: task.members[0].email,
                        dueDate: task.expirationDate,
                        status: task.state,
                        id: Date.now(),
                        fileUrl : task.file
                    };
                    tasksList.push(newItem)
                });

                self.setState({ todoList: tasksList });
                console.log(tasksList);
            }).catch(function (error) {
                console.log(error);
            });
 

    }

    componentDidMount() {
        this.getTaskList();
    }


    render() {
        const listTodo = this.state.todoList.map((td) => {
            if (this.props.filter !== undefined && (this.props.filter.name !== '' ||
                this.props.filter.status !== '' || this.props.filter.dueDate !== null)) {
                let x = null, newDate = null;
                if (this.props.filter.dueDate !== null) {
                    x = this.props.filter.dueDate.toString()
                    newDate = x.split(" ")[3] + "-" + this.Eq(x.split(" ")[1]) + "-" + x.split(" ")[2];
                }
                if (td.email === (this.props.filter.name) ||
                    td.status === (this.props.filter.status) || td.dueDate.split("T")[0] == newDate) {
                    return (<div key={uuid.v4()}>
                        <Card>
                            <CardActionArea>
                                <Todo email={td.email} status={td.status} text={td.text} priority={td.priority} dueDate={td.dueDate} ></Todo>
                            </CardActionArea>
                        </Card>
                        <br></br>
                    </div>);
                }

            } else {
                return (<div key={uuid.v4()}>
                    <Card>
                        <CardActionArea>
                            <Todo fileUrl={td.fileUrl} email={td.email} status={td.status} text={td.text} priority={td.priority} dueDate={td.dueDate} ></Todo>
                        </CardActionArea>
                    </Card>
                    <br></br>
                </div>);
            }
        });
        return (
            <div>{listTodo}</div>

        );
    }


}