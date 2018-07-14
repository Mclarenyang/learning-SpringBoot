import React from 'react';
import ReactDOM from 'react-dom';
import { Form, Input, Button } from 'antd';
import 'antd/dist/antd.css';
import './component.css';

const FormItem = Form.Item;

export default class SchoolSearchForm extends React.Component{

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        this.netRequest(values)
      }
    });
  }

  netRequest = values => {

        var that = this;
        var targetUrl = "http://192.168.0.133:8080/findSchool";
        fetch(targetUrl,{
            method: 'POST',
            headers: new Headers({
           'Content-Type': 'application/x-www-form-urlencoded' // 指定提交方式为表单提交
        }),
           body: new URLSearchParams([["schoolId",values.schoolId],
                                      ["schoolName",values.schoolName],
                                      ["cityName",values.cityName],
                                      ["chara",values.chara],
                                      ["scoreline",values.scoreline],
                                      ["pageSize",values.pageSize],
                                      ["pageNum",0]]),
        },
        ).then(function(response){
            return response.json().then(function(data){
              console.log('datacontent',data.content);
              if (data.numberOfElements == 0) {
                 alert("没有相关数据🤪🙃🤔");
              }
              that.props.pushData2P(data);
              //this.setState({data: data});
            });
        })
    }


    //构造一个解析函数 然后子组件->父亲组件->子组件传递



	render(){
    const { getFieldDecorator } = this.props.form;
		return(

		<Form onSubmit={this.handleSubmit} className="login-form">
        <FormItem>
        {getFieldDecorator('schoolId',{
            rules: [{ required: false }],
          })(
            <Input placeholder="School ID" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('schoolName',{
            rules: [{ required: false }],
          })(
            <Input placeholder="School Name" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('cityName',{
            rules: [{ required: false }],
          })(
            <Input placeholder="City" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('scoreline',{
            rules: [{ required: false }],
          })(
            <Input placeholder="scoreline <=" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('chara',{
            rules: [{ required: false }],
          })(
            <Input placeholder="CityChara" />
          )}
        </FormItem>
        <FormItem>
        {getFieldDecorator('pageSize',{
            rules: [{ required: false }],
          })(
            <Input placeholder="PageSize" />
          )}
        </FormItem>
        <FormItem>
          <Button type="primary" htmlType="submit" className="login-form-button">
            Search
          </Button>
        </FormItem>
      </Form>

		);
	}
}
