import React from 'react';
import ReactDOM from 'react-dom';
import { Form, Input, Button } from 'antd';
import 'antd/dist/antd.css';
import './component.css';

const FormItem = Form.Item;

export default class SchoolAddForm extends React.Component{

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        this.netRequest(values);
      }
    });
  }

  netRequest(values){

        var targetUrl = "http://192.168.0.144:8080/addSchool";
        fetch(targetUrl,{
            method: 'POST',
            headers: new Headers({
           'Content-Type': 'application/x-www-form-urlencoded' // 指定提交方式为表单提交
        }),
           body: new URLSearchParams([["schoolName",values.schoolName],
                                      ["cityName",values.cityName],
                                      ["scoreline",values.scoreline]]).toString()
        }).then(function(response){
            return response.json().then(function(data){
              if (data.scoreline == values.scoreline){
                alert("添加成功👏😎👏");
              }else{
                alert("添加失败😱😱😱");
              }
            });
        })
    }


  render(){
    const { getFieldDecorator } = this.props.form;
    return(

    <Form onSubmit={this.handleSubmit} className="handle-form">
        <FormItem>
          {getFieldDecorator('schoolName', {
            rules: [{ required: true, message: 'Please input a SchoolName!' }],
          })(
            <Input placeholder="School Name" />
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('cityName', {
            rules: [{ required: true, message: 'Please input the City School is in!' }],
          })(
            <Input placeholder="City" />
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('scoreline', {
            rules: [{ required: true, message: 'Please input scoreline of School!' }],
          })(
            <Input placeholder="scoreline" />
          )}
        </FormItem>
        <FormItem>
          <Button type="primary" htmlType="submit" className="handle-form-button">
            Add
          </Button>
        </FormItem>
      </Form>

    );
  }
}
